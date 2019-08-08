/*
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
 * For exact developer information, please refer to the commit history of the forge.
 *
 * This code is made available under the terms of the Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 4.0 License.
 *
 * This line is intended for UTF-8 encoding checks, do not modify/delete: äöüéè
 *
 */
package org.ehealth_connector.cda.ch.lrep;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.ehealth_connector.common.CdaNamespacePrefixMapper;

/**
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.2.27
 * Template description: ClinicalDocument/recordTarget SHALL be present and SHALL conform to the Human Patient, Non-Human Subject or Human Patient with Non-Human Subject templates defined below. There are three varieties of laboratory reports:
 * - Human (patient): The document reports laboratory observations produced on specimens collected exclusively from the patient.
 * - Non-Human Subject: The document reports laboratory observations produced on specimens collected from a non-human material (e.g. water, milk…) or living subject (e.g. animal).
 * - Human (patient) paired with Non-Human Subject: The document reports laboratory observations produced on a non-human specimen with a relationship to a human patient, (e.g. peanut butter eaten by a patient, a ferret that bit a patient).Human Patient with Non-Human SubjectWhen the report assembles observations for a human (patient) with observations produced using a non-human specimen, the recordTarget SHALL represent the human patient. In accordance with the HL7 CDA R2 standard and further constrained by this specification, the presence of name, addr and telecom is required for all entities in the document including the human patient. Additionally, the following SHALL be present. "1.3.6.1.4.1.19376.1.3.3.1.3"
 * - &lt;templateId root="1.3.6.1.4.1.19376.1.3.3.1.3"/&gt; - The templateId element identifies this recordTarget as a human patient directly impacted by a non-human subject of laboratory testing. The templateId SHALL have root="1.3.6.1.4.1.19376.1.3.3.1.3".
 * - &lt;id/&gt; - recordTarget/patientRole/id SHALL be present. It SHALL be representative of the id of the human patient. In this template, the id of the non-human subject is not provided in the header. On a special note, at present, if the document contains a patient and a subject (as in the case of rabies, for example), documentation of the id of the subject cannot be accomplished without an extension to CDA.
 * - &lt;administrativeGenderCode/&gt; - The patientRole/patient/administrativeGenderCode SHALL be present.
 * - &lt;birthTime/&gt; - The patientRole/patient/birthTime SHALL be present.
 * - &lt;structuredBody&gt; mark-up - In addition to the elements specified in the CDA header for the patient, the non-human subject SHALL be represented in a Subject element in level 3 entries in the structuredBody as described in template Non-Human Subject 1.3.6.1.4.1.19376.1.3.3.1.2.1.
 *
 * Element description: Human Patient with Non-Human Subject.
 */
public class ChpalmHeaderRecordTargetHumanPatientWithNonHumanSubject extends org.ehealth_connector.common.hl7cdar2.POCDMT000040RecordTarget {

	/**
	 * Gets the hl7PatientRole
	 * Exactly one patient MUST be declared.
	 */
	public org.ehealth_connector.common.hl7cdar2.POCDMT000040PatientRole getHl7PatientRole() {
		return super.patientRole;
	}

	/**
	 * Gets the hl7TemplateId
	 */
	public org.ehealth_connector.common.hl7cdar2.II getHl7TemplateId() {
		org.ehealth_connector.common.hl7cdar2.II retVal = null;
		if (super.getTemplateId() != null)
			if (super.getTemplateId().size() > 0)
				retVal = super.getTemplateId().get(0);
		return retVal;
	}

	/**
	 * Loads the CDA document from file.
	 * @param inputFileName the full path and filename of the sourcefile.
	 * @return the CDA document\n@throws JAXBException\n@throws IOException Signals that an I/O exception has occurred.
	 */
	public static ChpalmHeaderRecordTargetHumanPatientWithNonHumanSubject loadFromFile(String inputFileName) throws JAXBException, IOException {
		return loadFromFile(new File(inputFileName));
	}

	/**
	 * Loads the CDA document from file.
	 * @param inputFile the source file.
	 * n@return the CDA document\n@throws JAXBException\n@throws IOException Signals that an I/O exception has occurred.
	 */
	public static ChpalmHeaderRecordTargetHumanPatientWithNonHumanSubject loadFromFile(File inputFile) throws JAXBException, IOException {
		ChpalmHeaderRecordTargetHumanPatientWithNonHumanSubject retVal;
		JAXBContext context = JAXBContext.newInstance(ChpalmHeaderRecordTargetHumanPatientWithNonHumanSubject.class);
		Unmarshaller mar = context.createUnmarshaller();
		StreamSource source = new StreamSource(inputFile);
		JAXBElement<ChpalmHeaderRecordTargetHumanPatientWithNonHumanSubject> root = mar.unmarshal(source, ChpalmHeaderRecordTargetHumanPatientWithNonHumanSubject.class);
		retVal = root.getValue();
		return retVal;
	}

	/**
	 * Saves the current CDA document to file.
	 * @param outputFileName the full path and filename of the destination file.
	 * @throws JAXBException
	 */
	public void saveToFile(String outputFileName) throws JAXBException {
		saveToFile(new File(outputFileName));
	}

	/**
	 * Saves the current CDA document to file.
	 * @param outputFile the destination file.
	 * @throws JAXBException
	 */
	public void saveToFile(File outputFile) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(this.getClass());
		Marshaller mar = context.createMarshaller();
		mar.setProperty("com.sun.xml.bind.namespacePrefixMapper", new CdaNamespacePrefixMapper());
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		mar.marshal(this, outputFile);
	}

	/**
	 * Sets the hl7PatientRole
	 * Exactly one patient MUST be declared.
	 */
	public void setHl7PatientRole(org.ehealth_connector.common.hl7cdar2.POCDMT000040PatientRole value) {
		super.patientRole = value;
	}

	/**
	 * Sets the hl7TemplateId
	 */
	public void setHl7TemplateId(org.ehealth_connector.common.hl7cdar2.II value) {
		super.getTemplateId().clear();
		super.getTemplateId().add(value);
	}
}
