/*******************************************************************************
 *
 * The authorship of this code and the accompanying materials is held by
 * medshare GmbH, Switzerland. All rights reserved.
 * http://medshare.net
 *
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
 *
 * This code is are made available under the terms of the
 * Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the
 * Creative Commons Attribution-ShareAlike 3.0 Switzerland License.
 *
 * Year of publication: 2014
 *
 *******************************************************************************/

package org.ehc.communication.ch;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ehc.cda.ch.CdaCh;
import org.ehc.cda.ch.CdaChVacd;
import org.ehc.common.Code;
import org.ehc.common.DateUtil;
import org.ehc.common.Identificator;
import org.ehc.common.XdsUtil;
import org.ehc.communication.Destination;
import org.ehc.communication.DocumentMetadata;
import org.ehc.communication.Response;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.ch.VACD;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.datatype.DatatypeFactory;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.openhealthtools.ihe.atna.auditor.XDSSourceAuditor;
import org.openhealthtools.ihe.atna.auditor.codes.ihe.IHETransactionEventTypeCodes.ProvideAndRegisterDocumentSet;
import org.openhealthtools.ihe.atna.auditor.codes.ihe.IHETransactionEventTypeCodes.ProvideAndRegisterDocumentSetB;
import org.openhealthtools.ihe.common.ebxml._2._1.rim.InternationalStringType;
import org.openhealthtools.ihe.common.hl7v2.CX;
import org.openhealthtools.ihe.utils.OID;
import org.openhealthtools.ihe.xds.document.DocumentDescriptor;
import org.openhealthtools.ihe.xds.document.XDSDocument;
import org.openhealthtools.ihe.xds.document.XDSDocumentFromByteArray;
import org.openhealthtools.ihe.xds.document.XDSDocumentFromFile;
import org.openhealthtools.ihe.xds.document.XDSDocumentFromStream;
import org.openhealthtools.ihe.xds.metadata.AuthorType;
import org.openhealthtools.ihe.xds.metadata.CodedMetadataType;
import org.openhealthtools.ihe.xds.metadata.DocumentEntryType;
import org.openhealthtools.ihe.xds.metadata.LocalizedStringType;
import org.openhealthtools.ihe.xds.metadata.MetadataFactory;
import org.openhealthtools.ihe.xds.metadata.SubmissionSetType;
import org.openhealthtools.ihe.xds.metadata.extract.InputStreamDocumentEntryExtractor;
import org.openhealthtools.ihe.xds.metadata.extract.MetadataExtractionException;
import org.openhealthtools.ihe.xds.metadata.extract.cdar2.CDAR2Extractor;
import org.openhealthtools.ihe.xds.response.XDSResponseType;
import org.openhealthtools.ihe.xds.source.B_Source;
import org.openhealthtools.ihe.xds.source.SubmitTransactionCompositionException;
import org.openhealthtools.ihe.xds.source.SubmitTransactionData;

import sun.font.CreatedFontTracker;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;


/**
 * <p>
 * Die ConvenienceCommunication Klasse stellt Methoden für die Kommunikation von
 * CDA-Dokumenten mit IHE Transaktionen von und zu einer beliebigen Destination
 * bereit.
 * 
 * Die Klasse implementiert folgende IHE Akteure und Transaktionen:
 * <ul>
 * <li><b>IHE ITI Document Source Akteur</b></li>
 * <ul>
 * <li>[ITI-41] Provide & Register Document Set – b</li>
 * </ul>
 * <li><b>IHE ITI Document Consumer Akteur</b></li>
 * <ul>
 * <li>[ITI-18] Registry Stored Query</li>
 * <li>[ITI-43] Retrieve Document Set</li>
 * </ul>
 * <li><b>IHE ITI Portable Media Creator und Media Importer Akteur</b></li>
 * <ul>
 * <li>[ITI-32] Distribute Document Set on Media</li>
 * </ul>
 * <li><b>IHE PCC Care Manager und Decision Support Service Akteur</b></li>
 * <ul>
 * <li>[PCC-12] Request for Clinical Guidance</li>
 * </ul>
 * </ul>
 * </p>
 */
public class DocumentSourceClient {
	public static final String absResFolder = "F:/ihe/org.openhealthtools.ihe.xds.source/";
	public static final boolean CATMode = true;

	// NIST Repository (query interface)
	//public static final String NIST = "http://hcxw2k1.nist.gov:8080/xdsServices2/registry/soap/portals/yr3a/repository";
	public static final String NIST = "http://ihexds.nist.gov/tf6/services/xdsrepositoryb";

	// NIST SECURED epository (query interface)
	//public static final String NIST_SECURED = "https://hcxw2k1.nist.gov:8443/xdsServices2/registry/soap/portals/yr3a/repository";
	public static final String NIST_SECURED = "http://ihexds.nist.gov:12091/tf6/services/xdsrepositoryb";

	// IBM Audit Repository
	//public static final String IBM_ARR ="syslog://lswin10.dfw.ibm.com:515";

	// logger
	private static final Logger logger = Logger.getLogger(DocumentSourceClient.class);

	// Source instance
	private B_Source source = null;

	private String organizationalId;

	SubmitTransactionData txnData;

	//	/**
	//	 * <p>
	//	 * Sendet ein CDA Dokument an einen Empfänger (Repository Akteur gemäss IHE
	//	 * XDR oder IHE XDS). Die Kommunikation zum Kommunikations-Endpunkt erfolgt
	//	 * gemäss <b>IHE [ITI-41] Provide & Register Document Set – b</b>.
	//	 * </p>
	//	 * <p>
	//	 * Rolle der API resp. der aufrufenden Anwendung für diese Methode: <b>IHE ITI
	//	 * Document Source Actor</b>
	//	 * </p>
	//	 * 
	//	 * @param destination
	//	 *          Ziel der Übertragung (Kommunikations-Endpunkt)
	//	 * @param doc
	//	 *          das CDA-Dokument, welches verschickt werden soll
	//	 * @return status der XDS-Übertragung
	//	 * @throws Exception
	//	 */
	//	public Response sendCdaDocument(Destination destination, CdaCh doc) throws Exception {
	//		setUp(NIST, false, "./rsc/log4j.xml");
	//		try {
	//			//testWithPrecookedMetadata();
	//			//testWithCdaDoc(ORGANIZATIONAL_ID,absResFolder+"./resources/sample_files/test2/ScanSample.xml");
	//		} catch (Throwable e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		
	//		SubmitTransactionData txnData = new SubmitTransactionData();
	//		// invoke transformation for metadata extraction on test file
	//		logger.debug("Adding input document, and metadata.");
	//		
	//		//From Bytestream
	//		ByteArrayOutputStream baos = doc.getOutputStream();
	//		XDSDocument clinicalDocument = new XDSDocumentFromByteArray(DocumentDescriptor.CDA_R2, baos.toByteArray());
	//		
	//		//From File
	//		//XDSDocument clinicalDocument = new XDSDocumentFromFile(DocumentDescriptor.CDA_R2,"F:/temp/DemoVACD.xml");
	//		String docEntryUUID = txnData.addDocument(clinicalDocument);
	//		txnData.saveMetadataToFile("C:/temp/demoVACDXDSMetadataExtracted.xml");
	//		
	//		//Metadata by object creation
	//		//CodedMetadataType cmt = MetadataFactory.eINSTANCE.createCodedMetadataType();
	//		
	//		logger.debug("Supplementing Document Entry metadata");
	//		File docEntryFile = new File (absResFolder+"./resources/sample_files/test2/docEntry.xml");
	//		FileInputStream fis = new FileInputStream(docEntryFile);
	//		InputStreamDocumentEntryExtractor deExtractor = new InputStreamDocumentEntryExtractor(fis);
	//		DocumentEntryType docEntryFixes = deExtractor.extract();
	//		fis.close();
	//		
	//		DocumentEntryType docEntry = txnData.getDocumentEntry(docEntryUUID);
	//		// set classCode - Ersetzt den Loinc Code durch eine ConnectAthon Beschreibung  
	//		//txnData.getDocumentEntry(docEntryUUID).setClassCode(docEntryFixes.getClassCode());
	//		CodedMetadataType cmt = docEntry.getClassCode();
	//		//docEntry.getClassCode().setSchemeName("1.3.6.1.4.1.21367.100.1");
	//		//docEntry.getClassCode().setDisplayName(createInternationalString("Summarization of Episode Note"));
	//		
	//		//re-set conf code - Ergänzt den DisplayName
	//		txnData.getDocumentEntry(docEntryUUID).getConfidentialityCode().clear();
	//		txnData.getDocumentEntry(docEntryUUID).getConfidentialityCode().add(docEntryFixes.getConfidentialityCode().get(0));
	//		// format code - Ist nach CDAExtraction nicht gesetzt. Kann fest gesetzt werden (in diesem Fall auf CDA R2)
	//		txnData.getDocumentEntry(docEntryUUID).setFormatCode(docEntryFixes.getFormatCode());
	//		// healthcare facilty code  - Ist nach CDAExtraction nicht gesetzt. 
	//		txnData.getDocumentEntry(docEntryUUID).setHealthCareFacilityTypeCode(docEntryFixes.getHealthCareFacilityTypeCode());
	//		// patient Id - Kommt von Oliver
	//		txnData.getDocumentEntry(docEntryUUID).setPatientId(docEntryFixes.getPatientId());
	//		// prac setting code - Kann wahrscheinlich einfach auf "General Medicine" gesetzt werden
	//		txnData.getDocumentEntry(docEntryUUID).setPracticeSettingCode(docEntryFixes.getPracticeSettingCode());
	//		// type code - Typ des Dokuments. Hier kann der Type Code des CDA Dokuments verwendet werden.
	//		txnData.getDocumentEntry(docEntryUUID).setTypeCode(docEntryFixes.getTypeCode());
	//		// set uniqueID
	//		// say that you are assigned an organizational oid of "1.2.3.4"
	//		// added length limit for NIST registry of 64
	//		txnData.getDocumentEntry(docEntryUUID).setUniqueId(OID.createOIDGivenRoot(organizationalId,64));
	//		logger.debug("Done setting documentEntry metadata for: " +txnData.getDocumentEntry(docEntryUUID).toString());
	//		txnData.saveMetadataToFile("C:/temp/metadata2.xml");
	//
	//		// add submission set metadata
	//		logger.debug("Applying Submission Set Metadata to the Submission.");
	//		File submissionSetFile = new File(absResFolder+"./resources/sample_files/test2/submissionSet.xml");
	//		fis = new FileInputStream(submissionSetFile);
	//		txnData.loadSubmissionSet(fis);
	//		fis.close();
	//		txnData.saveMetadataToFile("C:/temp/metadata3.xml");
	//		
	//		// set uniqueID
	//		// say that you are assigned an organizational oid of TestConfiguration.ORGANIZATIONAL_OID
	//		// added length limit for NIST registry of 64
	//		txnData.getSubmissionSet().setUniqueId(OID.createOIDGivenRoot(organizationalId,64));
	//		// set submission time
	//		txnData.getSubmissionSet().setSubmissionTime(DateUtil.nowAsTS().getValue());
	//		txnData.saveMetadataToFile("C:/temp/metadata.xml");
	//		
	//		// set submission set source id
	//		txnData.getSubmissionSet().setSourceId(organizationalId);
	//		
	//		logger.debug("Submitting Document.");
	//		XDSResponseType response = source.submit(txnData);
	//		logger.debug("Response status: " + response.getStatus().getName());
	//		if(response.getErrorList() != null){
	//			if(response.getErrorList().getError() != null){
	//				logger.debug("Returned " + response.getErrorList().getError().size() + " errors.");
	//			}
	//		}
	//		logger.debug("DONE MESA 12049 with metadata extraction from CDA");
	//		
	//		
	//		//logger.debug("Submitting Document.");
	//		//XDSResponseType response1 = source.submit(txnData);
	//		return null;
	//		
	//		//return sendCdaDocument(metadata, destination, doc);
	//	}

	public DocumentSourceClient(String organizationalId, String repositoryUri, boolean auditorEnabled, String log4jConfigPath) throws Exception {
		txnData = new SubmitTransactionData();
		this.organizationalId = organizationalId;
		if (log4jConfigPath==null) {
			log4jConfigPath = "./rsc/log4j.xml";
		}
		setUp(repositoryUri, auditorEnabled, log4jConfigPath);
	}

	/**
	 * Test set up
	 */
	protected void setUp(String repositoryUri, boolean auditorEnabled, String log4jConfigPath) throws Exception {
		//super.setUp();
		File conf = new File(log4jConfigPath);
		org.apache.log4j.xml.DOMConfigurator.configure(conf.getAbsolutePath());
		logger.debug("***************** SUBMIT DOCUMENT TEST ************************");
		//logger.debug(TestUtils.formTimestamp());
		java.net.URI repositoryURI = null;
		try {
			repositoryURI = new java.net.URI(repositoryUri);
		} catch (URISyntaxException e) {
			logger.fatal("SOURCE URI CANNOT BE SET: \n" + e.getMessage());
			throw e;
		}
		source = new B_Source(repositoryURI);
		XDSSourceAuditor.getAuditor().getConfig().setAuditorEnabled(auditorEnabled);
	}

	public DocumentMetadata addDocument(DocumentDescriptor desc, String filePath) throws Exception {
		XDSDocument doc = new XDSDocumentFromFile(desc,filePath);
		String docEntryUUID = txnData.addDocument(doc);
		DocumentMetadata docMetadata = new DocumentMetadata(txnData.getDocumentEntry(docEntryUUID));

		return docMetadata;
	}

	/**
	 * <p>
	 * Anfrage einer Impfempfehlung (pseudonymisiert das Dokument vor dem
	 * eigentlichen Versand ans Expertensystem). Die Kommunikation zum
	 * Kommunikations-Endpunkt erfolgt gemäss <b>IHE [PCC-12] Request for Clinical
	 * Guidance</b>.
	 * </p>
	 * <p>
	 * Rolle der API resp. der aufrufenden Anwendung für diese Methode: <b>IHE PCC
	 * Care Manager Akteur</b>
	 * </p>
	 * 
	 * @param destination
	 *          Ziel der Übertragung (Kommunikations-Endpunkt)
	 * @param doc
	 *          CDA-CH-VACD Dokument mit den Impfungen und anderen für die
	 *          Impfempfehlung relevanten Angaben eines Patienten.
	 * @return CDA-CH-VACD Dokument, welches mit dem Parameter doc übergeben
	 *         worden ist und nun zusätzlich die Section mit den Impfempfehlungen
	 *         enthält
	 * 
	 * @throws Exception
	 *           Fehler während der Übertragung
	 */
	public static CdaChVacd getImmunizationRecommendationRequest(
			Destination destination, CdaChVacd doc) throws Exception {
		return null;
	}

	/**
	 * <p>
	 * Lädt CDA-Dokumente von einem Medium (Datenträger oder Pfad im Dateisystem;
	 * gemäss IHE XDM). Die Verarbeitung des Mediums erfolgt gemäss <b>[ITI-32]
	 * Distribute Document Set on Media</b>.
	 * </p>
	 * <p>
	 * Rolle der API resp. der aufrufenden Anwendung für diese Methode: <b>IHE ITI
	 * Portable Media Importer Akteur</b>
	 * </p>
	 * 
	 * @param destination
	 *          Pfad zum Datenträger von dem XDM-konforme Daten geladen werden
	 * @return eine Liste von CDA-Dokumenten
	 * @throws Exception
	 */
	public static ArrayList<ClinicalDocument> parseStoredCdaChVacd(File destination)
			throws Exception {
		return null;
	}

	// Übermittlung per XDM (Speichern und Laden von einem Datenträger) - A10, A11

	/**
	 * Pseudonymisierung eines ClinicalDocument nach der CDA-CH-VACD Spezifikation
	 * (siehe CDA-CH-VACD, UseCases ab Kapitel 6.3 und insbesondere Kapitel
	 * "7.4 CDA Header", Regel <CH-VACD-HPAT> )
	 * 
	 * @return das pseudonymisierte CdaChVacd
	 */
	public static CdaChVacd pseudonymize(CdaChVacd doc) {
		return null;
	}

	// XDS: Interaktion mit einer IHE Registry - A8

	/**
	 * <p>
	 * Sendet ein CDA Dokument an einen Empfänger (Repository Akteur gemäss IHE
	 * XDR oder IHE XDS). Die Kommunikation zum Kommunikations-Endpunkt erfolgt
	 * gemäss <b>IHE [ITI-41] Provide & Register Document Set – b</b>.
	 * </p>
	 * <p>
	 * Rolle der API resp. der aufrufenden Anwendung für diese Methode: <b>IHE ITI
	 * Document Source Actor</b>
	 * </p>
	 * 
	 * @param metadata
	 *          Zusätzliche Metadaten für die Übertragung
	 * @param destination
	 *          Ziel der Übertragung (Kommunikations-Endpunkt)
	 * @param doc
	 *          das CDA-Dokument, welches verschickt werden soll
	 * @return status der XDS-Übertragung
	 * @throws Exception
	 */
	public static Response sendCdaDocument(XdsMetadata metadata,
			Destination destination, CdaCh doc) throws Exception {
		return null;
	}

	// XDS: Herunterladen eines Impfdokuments von einem IHE XDS Repository - A9

	/**
	 * <p>
	 * Sendet eine beliebige Datei an einen Empfänger (Repository Akteur gemäss IHE
	 * XDR oder IHE XDS). Die Kommunikation zum Kommunikations-Endpunkt erfolgt
	 * gemäss <b>IHE [ITI-41] Provide & Register Document Set – b</b>.
	 * </p>
	 * <p>
	 * Rolle der API resp. der aufrufenden Anwendung für diese Methode: <b>IHE ITI
	 * Document Source Actor</b>
	 * </p>
	 * 
	 * @param metadata
	 *          Metadaten zum Dokument
	 * @param destination
	 *          Ziel der Übertragung (Kommunikations-Endpunkt)
	 * @param filePath
	 *          Datei (inkl. Pfad), welche verschickt werden soll
	 * @return status der XDS-Übertragung
	 * @throws Exception
	 */
	public Response sendDocument(DocumentMetadata metadata, DocumentDescriptor docDesc, String filePath) throws Exception {
		txnData = new SubmitTransactionData();
		// invoke transformation for metadata extraction on test file
		logger.debug("Adding input document, and metadata.");
		XDSDocument document = new XDSDocumentFromFile(docDesc, filePath);
		txnData.addDocument(document);

		//From Bytestream
		//		ByteArrayOutputStream baos = doc.getOutputStream();
		//		XDSDocument clinicalDocument = new XDSDocumentFromByteArray(DocumentDescriptor.CDA_R2, baos.toByteArray());

		//From File
		XDSDocument clinicalDocument = new XDSDocumentFromFile(docDesc,filePath);
		String docEntryUUID = txnData.addDocument(clinicalDocument);

		txnData.saveMetadataToFile("C:/temp/demoVACDXDSMetadataExtractedPDF.xml");

		//txnData.getDocumentEntry(docEntryUUID).

		//Metadata by object creation
		//CodedMetadataType cmt = MetadataFactory.eINSTANCE.createCodedMetadataType();

		return null;
	}

	// Anfrage einer Immunization Recommendation (Senden der Anfrage und Empfangen
	// der Antwort) - A4, A5, A6

	/**
	 * Speichert ein CDA Dokument für den Versand zu einer beliebigen Destination
	 * auf einem Medium
	 * 
	 * @param destination
	 *          Ziel der Übertragung (Kommunikations-Endpunkt)
	 * @param doc
	 *          CDA-CH Dokument
	 */
	public static void storeOnMedia(Destination destination, CdaCh doc) {
		// TODO Auto-generated method stub

	}

	/**
	 * <p>
	 * Speichert ein CDA-Dokument inkl. der benötigten Metadaten auf einem
	 * Datenträger (gemäss IHE XDM). Die Speicherung auf dem Medium erfolgt gemäss
	 * <b>[ITI-32] Distribute Document Set on Media</b>.
	 * </p>
	 * <p>
	 * Rolle der API resp. der aufrufenden Anwendung für diese Methode: <b>IHE ITI
	 * Portable Media Creator Akteur</b>
	 * </p>
	 * 
	 * @param destination
	 *          Pfad zum Datenträger auf dem XDM-konforme Daten gespeichert werden
	 * @param doc
	 *          das CDA-Dokument, welches gespeichert werden soll
	 * @return true, wenn das Dokument erfolgreich gespeichert wurde. Sonst:
	 *         false.
	 * @throws Exception
	 */
	public static boolean storeOnMedia(File destination, ClinicalDocument doc)
			throws Exception {
		return false;
	}
	
	private void generateMissingDocEntryAttributes(String docEntryUuid) throws Exception {

		DocumentMetadata docMetadata = new DocumentMetadata(txnData.getDocumentEntry(docEntryUuid));
		DocumentDescriptor desc = txnData.getDocument(docEntryUuid).getDescriptor();
		
		//Automatically create the formatCode of the Document according to the DocumentDescriptor
		if (DocumentDescriptor.PDF.equals(desc)) {
			Code formatCode = new Code("1.3.6.1.4.1.19376.1.2.3", "urn:ihe:iti:xds-sd:pdf:2008", "1.3.6.1.4.1.19376.1.2.20 (Scanned Document)");
			docMetadata.getMdhtDocumentEntryType().setFormatCode(XdsUtil.convertCode(formatCode));
		}
	
		//If the given doc is a CDA Doc load it for further processing
		if (desc.equals(DocumentDescriptor.CDA_R2)) {
			generateMissingDocEntryAttributesCda(docEntryUuid);
		}

		//Derive MimeType from DocumentDescriptor
		if (docMetadata.getMdhtDocumentEntryType().getMimeType()==null) {
			docMetadata.setMimeType(desc.getMimeType());
		}
		
		//Generate the UUID
		if (docMetadata.getMdhtDocumentEntryType().getUniqueId()==null) {
			docMetadata.setUniqueId(OID.createOIDGivenRoot(organizationalId,64));
		}
		
		//Set ConfidentiallyCode to Normal ("N");
		if (docMetadata.getMdhtDocumentEntryType().getConfidentialityCode().isEmpty() || docMetadata.getMdhtDocumentEntryType().getConfidentialityCode()==null) {
			docMetadata.getMdhtDocumentEntryType().getConfidentialityCode().clear();
			docMetadata.getMdhtDocumentEntryType().getConfidentialityCode().add(XdsUtil.createCodedMetadata("2.16.840.1.113883.5.25", "N", null, null));
		}
		
		//Generate Creation Time with the current time
		if (docMetadata.getMdhtDocumentEntryType().getCreationTime() == null) {
			docMetadata.setCreationTime(DateUtil.nowAsDate());
		}
		
		//Use the TypeCode for ClassCode
		if (docMetadata.getMdhtDocumentEntryType().getClassCode() == null && docMetadata.getMdhtDocumentEntryType().getTypeCode() != null){
			docMetadata.getMdhtDocumentEntryType().setClassCode(EcoreUtil.copy(docMetadata.getMdhtDocumentEntryType().getTypeCode()));
		}
	}

	private void generateMissingDocEntryAttributesCda(String docEntryUuid) throws Exception {
		ClinicalDocument cda = CDAUtil.load(txnData.getDocument(docEntryUuid).getStream());
		DocumentMetadata docMetadata = new DocumentMetadata(txnData.getDocumentEntry(docEntryUuid));
		
		//PatientId from recordTarget/patientRole
		if (cda.getPatientRoles()!=null && docMetadata.getMdhtDocumentEntryType().getPatientId()==null) {
			if (cda.getPatientRoles().get(0).getIds()!=null) {
				docMetadata.getMdhtDocumentEntryType().setPatientId(XdsUtil.convertII(cda.getPatientRoles().get(0).getIds().get(0)));
			}
		}
		
		//TODO Später: Kann bei CDA Dokumenten gemacht werden, indem die TemplateIDs mit dieser Liste (als Enum) verglichen werden: http://wiki.ihe.net/index.php?title=IHE_Format_Codes
		//Currently only mapping to CDA-CH-VACD
		II medicalDocumentII = DatatypesFactory.eINSTANCE.createII("1.3.6.1.4.1.19376.1.5.3.1.1.18.1.2");
		II ii = org.ehc.common.Util.findII(cda.getTemplateIds(), medicalDocumentII);
		if (ii!=null) {
			Code formatCode = new Code("1.3.6.1.4.1.19376.1.2.3", "urn:ihe:pcc:ic:2009");
			docMetadata.setFormatCode(formatCode);
		}
		
		//Set the Document Code as SubmissionSet ContentType -- Affinity Domain specific!
		
		//Set the Document Code as TypeCode
		if (cda.getCode()!=null) {
			Code code = new Code(cda.getCode());
			docMetadata.setTypeCode(code);
		}
		
		//Fix the OHT CDAExtraction bug(?), that authorTelecommunication is not a known Slot for the NIST Registry by deleting all authorTelecommunications
		for (Object object: docMetadata.getMdhtDocumentEntryType().getAuthors()) {
			AuthorType at = (AuthorType) object;
			at.getAuthorTelecommunication().clear();
		}
		
		//Fix the OHT CDAExtraction bug(?) that generates Unique Ids, which are to long for the registry (EXT part is larger than the allowed 16 characters)
		docMetadata.setUniqueId(OID.createOIDGivenRoot(cda.getId().getRoot()));
		
//		if (cda instanceof VACD) {
//			
//		}
	}

	public XDSResponseType submit() throws Exception {
		//generate missing information for all documents
		for (XDSDocument xdsDoc : txnData.getDocList()) {
			generateMissingDocEntryAttributes(xdsDoc.getDocumentEntryUUID());
		}
		
		//Create SubmissionSet
		SubmissionSetType subSet = txnData.getSubmissionSet();
		subSet.setUniqueId(OID.createOIDGivenRoot((organizationalId),64));
		
		// set submission set source id
		subSet.setSourceId(organizationalId);

		// set submission time
		subSet.setSubmissionTime(DateUtil.nowAsTS().getValue());
		txnData.saveMetadataToFile("C:/temp/metadata.xml");

		//Use the PatientId of the first Document for the SubmissionSet/patientId
		String uuid = txnData.getDocList().get(0).getDocumentEntryUUID();
		//CX testCx = XdsUtil.createCx("TESTAuthority", "TestId");
		//subSet.setPatientId(testCx);
		CX testCx = txnData.getDocumentEntry(uuid).getPatientId();
		subSet.setPatientId(EcoreUtil.copy(testCx));

		// set ContentTypeCode
		subSet.setContentTypeCode(XdsUtil.createCodedMetadata("2.16.840.1.113883.6.1", "34133-9", "Summary of Episode Note" , null));

		txnData.saveMetadataToFile("C:/temp/meta.xml");
		return source.submit(txnData);
	}

	//	/**
	//	 * <p>
	//	 * Sendet ein CDA Dokument an einen Empfänger (Repository Akteur gemäss IHE
	//	 * XDR oder IHE XDS). Die Kommunikation zum Kommunikations-Endpunkt erfolgt
	//	 * gemäss <b>IHE [ITI-41] Provide & Register Document Set – b</b>.
	//	 * </p>
	//	 * <p>
	//	 * Rolle der API resp. der aufrufenden Anwendung für diese Methode: <b>IHE ITI
	//	 * Document Source Actor</b>
	//	 * </p>
	//	 * 
	//	 * @param destination
	//	 *          Ziel der Übertragung (Kommunikations-Endpunkt)
	//	 * @param doc
	//	 *          das CDA-Dokument, welches verschickt werden soll
	//	 * @return status der XDS-Übertragung
	//	 * @throws Exception
	//	 */
	//	public Response sendCdaDocument(Destination destination, CdaCh doc) throws Exception {
	//		setUp(NIST, false, "./rsc/log4j.xml");
	//		try {
	//			//testWithPrecookedMetadata();
	//			//testWithCdaDoc(ORGANIZATIONAL_ID,absResFolder+"./resources/sample_files/test2/ScanSample.xml");
	//		} catch (Throwable e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		
	//		SubmitTransactionData txnData = new SubmitTransactionData();
	//		// invoke transformation for metadata extraction on test file
	//		logger.debug("Adding input document, and metadata.");
	//		
	//		//From Bytestream
	//		ByteArrayOutputStream baos = doc.getOutputStream();
	//		XDSDocument clinicalDocument = new XDSDocumentFromByteArray(DocumentDescriptor.CDA_R2, baos.toByteArray());
	//		
	//		//From File
	//		//XDSDocument clinicalDocument = new XDSDocumentFromFile(DocumentDescriptor.CDA_R2,"F:/temp/DemoVACD.xml");
	//		String docEntryUUID = txnData.addDocument(clinicalDocument);
	//		txnData.saveMetadataToFile("C:/temp/demoVACDXDSMetadataExtracted.xml");
	//		
	//		//Metadata by object creation
	//		//CodedMetadataType cmt = MetadataFactory.eINSTANCE.createCodedMetadataType();
	//		
	//		logger.debug("Supplementing Document Entry metadata");
	//		File docEntryFile = new File (absResFolder+"./resources/sample_files/test2/docEntry.xml");
	//		FileInputStream fis = new FileInputStream(docEntryFile);
	//		InputStreamDocumentEntryExtractor deExtractor = new InputStreamDocumentEntryExtractor(fis);
	//		DocumentEntryType docEntryFixes = deExtractor.extract();
	//		fis.close();
	//		
	//		DocumentEntryType docEntry = txnData.getDocumentEntry(docEntryUUID);
	//		// set classCode - Ersetzt den Loinc Code durch eine ConnectAthon Beschreibung  
	//		//txnData.getDocumentEntry(docEntryUUID).setClassCode(docEntryFixes.getClassCode());
	//		CodedMetadataType cmt = docEntry.getClassCode();
	//		//docEntry.getClassCode().setSchemeName("1.3.6.1.4.1.21367.100.1");
	//		//docEntry.getClassCode().setDisplayName(createInternationalString("Summarization of Episode Note"));
	//		
	//		//re-set conf code - Ergänzt den DisplayName
	//		txnData.getDocumentEntry(docEntryUUID).getConfidentialityCode().clear();
	//		txnData.getDocumentEntry(docEntryUUID).getConfidentialityCode().add(docEntryFixes.getConfidentialityCode().get(0));
	//		// format code - Ist nach CDAExtraction nicht gesetzt. Kann fest gesetzt werden (in diesem Fall auf CDA R2)
	//		txnData.getDocumentEntry(docEntryUUID).setFormatCode(docEntryFixes.getFormatCode());
	//		// healthcare facilty code  - Ist nach CDAExtraction nicht gesetzt. 
	//		txnData.getDocumentEntry(docEntryUUID).setHealthCareFacilityTypeCode(docEntryFixes.getHealthCareFacilityTypeCode());
	//		// patient Id - Kommt von Oliver
	//		txnData.getDocumentEntry(docEntryUUID).setPatientId(docEntryFixes.getPatientId());
	//		// prac setting code - Kann wahrscheinlich einfach auf "General Medicine" gesetzt werden
	//		txnData.getDocumentEntry(docEntryUUID).setPracticeSettingCode(docEntryFixes.getPracticeSettingCode());
	//		// type code - Typ des Dokuments. Hier kann der Type Code des CDA Dokuments verwendet werden.
	//		txnData.getDocumentEntry(docEntryUUID).setTypeCode(docEntryFixes.getTypeCode());
	//		// set uniqueID
	//		// say that you are assigned an organizational oid of "1.2.3.4"
	//		// added length limit for NIST registry of 64
	//		txnData.getDocumentEntry(docEntryUUID).setUniqueId(OID.createOIDGivenRoot(organizationalId,64));
	//		logger.debug("Done setting documentEntry metadata for: " +txnData.getDocumentEntry(docEntryUUID).toString());
	//		txnData.saveMetadataToFile("C:/temp/metadata2.xml");
	//
	//		// add submission set metadata
	//		logger.debug("Applying Submission Set Metadata to the Submission.");
	//		File submissionSetFile = new File(absResFolder+"./resources/sample_files/test2/submissionSet.xml");
	//		fis = new FileInputStream(submissionSetFile);
	//		txnData.loadSubmissionSet(fis);
	//		fis.close();
	//		txnData.saveMetadataToFile("C:/temp/metadata3.xml");
	//		
	//		// set uniqueID
	//		// say that you are assigned an organizational oid of TestConfiguration.ORGANIZATIONAL_OID
	//		// added length limit for NIST registry of 64
	//		txnData.getSubmissionSet().setUniqueId(OID.createOIDGivenRoot(organizationalId,64));
	//		// set submission time
	//		txnData.getSubmissionSet().setSubmissionTime(DateUtil.nowAsTS().getValue());
	//		txnData.saveMetadataToFile("C:/temp/metadata.xml");
	//		
	//		// set submission set source id
	//		txnData.getSubmissionSet().setSourceId(organizationalId);
	//		
	//		logger.debug("Submitting Document.");
	//		XDSResponseType response = source.submit(txnData);
	//		logger.debug("Response status: " + response.getStatus().getName());
	//		if(response.getErrorList() != null){
	//			if(response.getErrorList().getError() != null){
	//				logger.debug("Returned " + response.getErrorList().getError().size() + " errors.");
	//			}
	//		}
	//		logger.debug("DONE MESA 12049 with metadata extraction from CDA");
	//		
	//		
	//		//logger.debug("Submitting Document.");
	//		//XDSResponseType response1 = source.submit(txnData);
	//		return null;
	//		
	//		//return sendCdaDocument(metadata, destination, doc);
	//	}

	/**
	 * TEST 2: XDS.b Submission test with automatic metadata extraction from CDA R2
	 * Data for this test is in ./resources/sample_files/test2/
	 * <br>
	 * Corresponds to Connectathon 2008-2009 Test: 12049
	 * <br>
	 * http://ihewiki.wustl.edu/wiki/index.php/XDS_Test_Kit_2007-2008_Test_Descriptions#12049
	 * @throws Throwable
	 */
	public void nistTest(String organizationalOid, String filePath) throws Throwable {
		logger.debug("BEGIN MESA 12049 with metadata extraction from CDA");

		SubmitTransactionData txnData = new SubmitTransactionData();
		// invoke transformation for metadata extraction on test file
		logger.debug("Adding input document, and metadata.");
		XDSDocument clinicalDocument = new XDSDocumentFromFile(DocumentDescriptor.CDA_R2,filePath);
		String docEntryUUID = txnData.addDocument(clinicalDocument);

		txnData.saveMetadataToFile("C:/temp/metadata1.xml");

		logger.debug("Supplementing Document Entry metadata");
		File docEntryFile = new File (absResFolder+"./resources/sample_files/test2/docEntry.xml");
		FileInputStream fis = new FileInputStream(docEntryFile);
		InputStreamDocumentEntryExtractor deExtractor = new InputStreamDocumentEntryExtractor(fis);
		DocumentEntryType docEntryFixes = deExtractor.extract();
		fis.close();

		// set classCode - Ersetzt den Loinc Code durch eine ConnectAthon Beschreibung  
		txnData.getDocumentEntry(docEntryUUID).setClassCode(docEntryFixes.getClassCode());
		//re-set conf code - Ergänzt den DisplayName
		txnData.getDocumentEntry(docEntryUUID).getConfidentialityCode().clear();
		txnData.getDocumentEntry(docEntryUUID).getConfidentialityCode().add(docEntryFixes.getConfidentialityCode().get(0));
		// format code - Ist nach CDAExtraction nicht gesetzt. Kann fest gesetzt werden (in diesem Fall auf CDA R2)
		txnData.getDocumentEntry(docEntryUUID).setFormatCode(docEntryFixes.getFormatCode());
		// healthcare facilty code  - Ist nach CDAExtraction nicht gesetzt. 
		txnData.getDocumentEntry(docEntryUUID).setHealthCareFacilityTypeCode(docEntryFixes.getHealthCareFacilityTypeCode());
		// patient Id - Kommt von Oliver
		txnData.getDocumentEntry(docEntryUUID).setPatientId(docEntryFixes.getPatientId());
		// prac setting code - Kann wahrscheinlich einfach auf "General Medicine" gesetzt werden
		txnData.getDocumentEntry(docEntryUUID).setPracticeSettingCode(docEntryFixes.getPracticeSettingCode());
		// type code - Typ des Dokuments. Hier kann der Type Code des CDA Dokuments verwendet werden.
		txnData.getDocumentEntry(docEntryUUID).setTypeCode(docEntryFixes.getTypeCode());
		// set uniqueID
		// say that you are assigned an organizational oid of "1.2.3.4"
		// added length limit for NIST registry of 64
		txnData.getDocumentEntry(docEntryUUID).setUniqueId(OID.createOIDGivenRoot(organizationalOid,64));
		logger.debug("Done setting documentEntry metadata for: " +txnData.getDocumentEntry(docEntryUUID).toString());
		txnData.saveMetadataToFile("C:/temp/metadata2.xml");

		// add submission set metadata
		logger.debug("Applying Submission Set Metadata to the Submission.");
		File submissionSetFile = new File(absResFolder+"./resources/sample_files/test2/submissionSet.xml");
		fis = new FileInputStream(submissionSetFile);
		txnData.loadSubmissionSet(fis);
		fis.close();
		txnData.saveMetadataToFile("C:/temp/metadata3.xml");

		// set uniqueID
		// say that you are assigned an organizational oid of TestConfiguration.ORGANIZATIONAL_OID
		// added length limit for NIST registry of 64
		txnData.getSubmissionSet().setUniqueId(OID.createOIDGivenRoot(organizationalOid,64));
		// set submission time
		txnData.getSubmissionSet().setSubmissionTime(DateUtil.nowAsTS().getValue());
		txnData.saveMetadataToFile("C:/temp/metadata.xml");

		// set submission set source id
		txnData.getSubmissionSet().setSourceId(organizationalOid);

		logger.debug("Submitting Document.");
		XDSResponseType response = source.submit(txnData);
		logger.debug("Response status: " + response.getStatus().getName());
		if(response.getErrorList() != null){
			if(response.getErrorList().getError() != null){
				logger.debug("Returned " + response.getErrorList().getError().size() + " errors.");
			}
		}
		logger.debug("DONE MESA 12049 with metadata extraction from CDA");
	}

}