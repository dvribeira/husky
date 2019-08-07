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
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.ehealth_connector.common.CdaNamespacePrefixMapper;
import org.ehealth_connector.common.hl7cdar2.POCDMT000040ClinicalDocument;

/**
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.4.29
 * Template description: External documents can be included in a CDA document, either by means of references or by means of XML embedding.This template defines a reference to an external document, only.Embedded documents can be created with the template Observation Media (or derived templates).
 *
 * Element description: Reference to an external document.
 */
public class CdachEntryExternalDocument extends org.ehealth_connector.common.hl7cdar2.POCDMT000040ExternalDocument {

	private org.ehealth_connector.common.hl7cdar2.CD hl7Code;

	/**
	 * MUST contain the id of the external document.
	 */
	private ArrayList<org.ehealth_connector.common.hl7cdar2.II> hl7Id;

	private org.ehealth_connector.common.hl7cdar2.II hl7TemplateId;

	/**
	 * The external document MUST be declared as a link.The link MUST be declared as an URL, which points to the referenced document.The same link MUST be used in the human readable part (narrative text) using the &lt;linkHTML&gt; element.
	 */
	private org.ehealth_connector.common.hl7cdar2.ED hl7Text;

	/**
	 * Adds a hl7Id
	 * MUST contain the id of the external document.
	 */
	public void addHl7Id(org.ehealth_connector.common.hl7cdar2.II value) {
		if (hl7Id == null)
			hl7Id = new ArrayList<org.ehealth_connector.common.hl7cdar2.II>();
		hl7Id.add(value);
	}

	/**
	 * Adds a hl7Id
	 * MUST contain the id of the external document.
	 */
	public void clearHl7Id() {
		hl7Id.clear();
	}

	/**
	 * Gets the hl7Code
	 */
	public org.ehealth_connector.common.hl7cdar2.CD getHl7Code() {
		return hl7Code;
	}

	/**
	 * Gets the hl7TemplateId
	 */
	public org.ehealth_connector.common.hl7cdar2.II getHl7TemplateId() {
		return hl7TemplateId;
	}

	/**
	 * Gets the hl7Text
	 * The external document MUST be declared as a link.The link MUST be declared as an URL, which points to the referenced document.The same link MUST be used in the human readable part (narrative text) using the &lt;linkHTML&gt; element.
	 */
	public org.ehealth_connector.common.hl7cdar2.ED getHl7Text() {
		return hl7Text;
	}

	/**
	 * Loads the CDA document from file.
	 * @param inputFileName the full path and filename of the sourcefile.
	 * @return the CDA document\n@throws JAXBException\n@throws IOException Signals that an I/O exception has occurred.
	 */
	public static CdachEntryExternalDocument loadFromFile(String inputFileName) throws JAXBException, IOException {
		return loadFromFile(new File(inputFileName));
	}

	/**
	 * Loads the CDA document from file.
	 * @param inputFile the source file.
	 * n@return the CDA document\n@throws JAXBException\n@throws IOException Signals that an I/O exception has occurred.
	 */
	public static CdachEntryExternalDocument loadFromFile(File inputFile) throws JAXBException, IOException {
		CdachEntryExternalDocument retVal;
		JAXBContext context = JAXBContext.newInstance(CdachEntryExternalDocument.class);
		Unmarshaller mar = context.createUnmarshaller();
		StreamSource source = new StreamSource(inputFile);
		JAXBElement<CdachEntryExternalDocument> root = mar.unmarshal(source, CdachEntryExternalDocument.class);
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
	 * Sets the hl7Code
	 */
	public void setHl7Code(org.ehealth_connector.common.hl7cdar2.CD value) {
		hl7Code = value;
	}

	/**
	 * Sets the hl7TemplateId
	 */
	public void setHl7TemplateId(org.ehealth_connector.common.hl7cdar2.II value) {
		hl7TemplateId = value;
	}

	/**
	 * Sets the hl7Text
	 * The external document MUST be declared as a link.The link MUST be declared as an URL, which points to the referenced document.The same link MUST be used in the human readable part (narrative text) using the &lt;linkHTML&gt; element.
	 */
	public void setHl7Text(org.ehealth_connector.common.hl7cdar2.ED value) {
		hl7Text = value;
	}
}