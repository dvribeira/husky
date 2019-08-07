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
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.4.18
 * Template description: Multimedia objects (e.g., PDF representations of the CDA document, pictures, Reiber diagrams, electrophoresis, etc.) MAY be integrated into a CDA document, either by reference to external multimedia objects or by means of XML embedding.This template defines only the embedding of multimedia objects in the CDA document. References to external documents can be created with the ExternalDocument template.For embedding in XML, the multimedia objects Base-64 must be encoded.Due to the amount of data, only light objects should be embedded.Heavy objects should be integrated using links to external documents.
 */
public class ChpalmEntryMultimediaEmbeddedContent extends org.ehealth_connector.common.hl7cdar2.POCDMT000040ObservationMedia {

	/**
	 * This template defines only the embedding of multimedia objects in the CDA document.
	 */
	private org.ehealth_connector.common.hl7cdar2.POCDMT000040EntryRelationship hl7EntryRelationship;

	/**
	 * IDs for this item CAN be filled for traceability.
	 */
	private ArrayList<org.ehealth_connector.common.hl7cdar2.II> hl7Id;

	/**
	 * The RFC 1766 (ISO-639-1 and ISO 3166) based language in which the multimedia object is written. If it isn't known or not available (e.g. for pictures), use nullFlavor instead.
	 */
	private org.ehealth_connector.common.hl7cdar2.CS hl7LanguageCode;

	/**
	 * This template defines only the embedding of multimedia objects in the CDA document.
	 */
	private org.ehealth_connector.common.hl7cdar2.POCDMT000040Reference hl7Reference;

	private org.ehealth_connector.common.hl7cdar2.II hl7TemplateId;

	private org.ehealth_connector.common.hl7cdar2.II hl7TemplateId1;

	/**
	 * The Base-64 encoded multimedia object.
	 */
	private org.ehealth_connector.common.hl7cdar2.ED hl7Value;

	/**
	 * Adds a hl7Id
	 * IDs for this item CAN be filled for traceability.
	 */
	public void addHl7Id(org.ehealth_connector.common.hl7cdar2.II value) {
		if (hl7Id == null)
			hl7Id = new ArrayList<org.ehealth_connector.common.hl7cdar2.II>();
		hl7Id.add(value);
	}

	/**
	 * Adds a hl7Id
	 * IDs for this item CAN be filled for traceability.
	 */
	public void clearHl7Id() {
		hl7Id.clear();
	}

	/**
	 * Gets the hl7EntryRelationship
	 * This template defines only the embedding of multimedia objects in the CDA document.
	 */
	public org.ehealth_connector.common.hl7cdar2.POCDMT000040EntryRelationship getHl7EntryRelationship() {
		return hl7EntryRelationship;
	}

	/**
	 * Gets the hl7LanguageCode
	 * The RFC 1766 (ISO-639-1 and ISO 3166) based language in which the multimedia object is written. If it isn't known or not available (e.g. for pictures), use nullFlavor instead.
	 */
	public org.ehealth_connector.common.hl7cdar2.CS getHl7LanguageCode() {
		return hl7LanguageCode;
	}

	/**
	 * Gets the hl7Reference
	 * This template defines only the embedding of multimedia objects in the CDA document.
	 */
	public org.ehealth_connector.common.hl7cdar2.POCDMT000040Reference getHl7Reference() {
		return hl7Reference;
	}

	/**
	 * Gets the hl7TemplateId
	 */
	public org.ehealth_connector.common.hl7cdar2.II getHl7TemplateId() {
		return hl7TemplateId;
	}

	/**
	 * Gets the hl7TemplateId1
	 */
	public org.ehealth_connector.common.hl7cdar2.II getHl7TemplateId1() {
		return hl7TemplateId1;
	}

	/**
	 * Gets the hl7Value
	 * The Base-64 encoded multimedia object.
	 */
	public org.ehealth_connector.common.hl7cdar2.ED getHl7Value() {
		return hl7Value;
	}

	/**
	 * Loads the CDA document from file.
	 * @param inputFileName the full path and filename of the sourcefile.
	 * @return the CDA document\n@throws JAXBException\n@throws IOException Signals that an I/O exception has occurred.
	 */
	public static ChpalmEntryMultimediaEmbeddedContent loadFromFile(String inputFileName) throws JAXBException, IOException {
		return loadFromFile(new File(inputFileName));
	}

	/**
	 * Loads the CDA document from file.
	 * @param inputFile the source file.
	 * n@return the CDA document\n@throws JAXBException\n@throws IOException Signals that an I/O exception has occurred.
	 */
	public static ChpalmEntryMultimediaEmbeddedContent loadFromFile(File inputFile) throws JAXBException, IOException {
		ChpalmEntryMultimediaEmbeddedContent retVal;
		JAXBContext context = JAXBContext.newInstance(ChpalmEntryMultimediaEmbeddedContent.class);
		Unmarshaller mar = context.createUnmarshaller();
		StreamSource source = new StreamSource(inputFile);
		JAXBElement<ChpalmEntryMultimediaEmbeddedContent> root = mar.unmarshal(source, ChpalmEntryMultimediaEmbeddedContent.class);
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
	 * Sets the hl7EntryRelationship
	 * This template defines only the embedding of multimedia objects in the CDA document.
	 */
	public void setHl7EntryRelationship(org.ehealth_connector.common.hl7cdar2.POCDMT000040EntryRelationship value) {
		hl7EntryRelationship = value;
	}

	/**
	 * Sets the hl7LanguageCode
	 * The RFC 1766 (ISO-639-1 and ISO 3166) based language in which the multimedia object is written. If it isn't known or not available (e.g. for pictures), use nullFlavor instead.
	 */
	public void setHl7LanguageCode(org.ehealth_connector.common.hl7cdar2.CS value) {
		hl7LanguageCode = value;
	}

	/**
	 * Sets the hl7Reference
	 * This template defines only the embedding of multimedia objects in the CDA document.
	 */
	public void setHl7Reference(org.ehealth_connector.common.hl7cdar2.POCDMT000040Reference value) {
		hl7Reference = value;
	}

	/**
	 * Sets the hl7TemplateId
	 */
	public void setHl7TemplateId(org.ehealth_connector.common.hl7cdar2.II value) {
		hl7TemplateId = value;
	}

	/**
	 * Sets the hl7TemplateId1
	 */
	public void setHl7TemplateId1(org.ehealth_connector.common.hl7cdar2.II value) {
		hl7TemplateId1 = value;
	}

	/**
	 * Sets the hl7Value
	 * The Base-64 encoded multimedia object.
	 */
	public void setHl7Value(org.ehealth_connector.common.hl7cdar2.ED value) {
		hl7Value = value;
	}
}