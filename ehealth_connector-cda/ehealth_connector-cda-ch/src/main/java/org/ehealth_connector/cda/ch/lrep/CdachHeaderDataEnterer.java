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
import org.ehealth_connector.common.hl7cdar2.POCDMT000040ClinicalDocument;

/**
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.2.7
 * Template description: Information about the person that entered information in this CDA document. It SHALL be declared, when data recorded in this document has been entered by a person other than the author but only when this is relevant for some reason. All CDA-CH V2 derivatives, i.e. Swiss exchange formats MUST reference this template.
 *
 * Element description: Information about the person that entered information in this CDA document. It SHALL be declared, when data recorded in this document has been entered by a person other than the author but only when this is relevant for some reason.
 */
public class CdachHeaderDataEnterer extends org.ehealth_connector.common.hl7cdar2.POCDMT000040DataEnterer {

	private org.ehealth_connector.common.hl7cdar2.POCDMT000040AssignedEntity hl7AssignedEntity;

	private org.ehealth_connector.common.hl7cdar2.II hl7TemplateId;

	/**
	 * Timestamp of the data input.
	 */
	private org.ehealth_connector.common.hl7cdar2.TS hl7Time;

	/**
	 * Gets the hl7AssignedEntity
	 */
	public org.ehealth_connector.common.hl7cdar2.POCDMT000040AssignedEntity getHl7AssignedEntity() {
		return hl7AssignedEntity;
	}

	/**
	 * Gets the hl7TemplateId
	 */
	public org.ehealth_connector.common.hl7cdar2.II getHl7TemplateId() {
		return hl7TemplateId;
	}

	/**
	 * Gets the hl7Time
	 * Timestamp of the data input.
	 */
	public org.ehealth_connector.common.hl7cdar2.TS getHl7Time() {
		return hl7Time;
	}

	/**
	 * Loads the CDA document from file.
	 * @param inputFileName the full path and filename of the sourcefile.
	 * @return the CDA document\n@throws JAXBException\n@throws IOException Signals that an I/O exception has occurred.
	 */
	public static CdachHeaderDataEnterer loadFromFile(String inputFileName) throws JAXBException, IOException {
		return loadFromFile(new File(inputFileName));
	}

	/**
	 * Loads the CDA document from file.
	 * @param inputFile the source file.
	 * n@return the CDA document\n@throws JAXBException\n@throws IOException Signals that an I/O exception has occurred.
	 */
	public static CdachHeaderDataEnterer loadFromFile(File inputFile) throws JAXBException, IOException {
		CdachHeaderDataEnterer retVal;
		JAXBContext context = JAXBContext.newInstance(CdachHeaderDataEnterer.class);
		Unmarshaller mar = context.createUnmarshaller();
		StreamSource source = new StreamSource(inputFile);
		JAXBElement<CdachHeaderDataEnterer> root = mar.unmarshal(source, CdachHeaderDataEnterer.class);
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
	 * Sets the hl7AssignedEntity
	 */
	public void setHl7AssignedEntity(org.ehealth_connector.common.hl7cdar2.POCDMT000040AssignedEntity value) {
		hl7AssignedEntity = value;
	}

	/**
	 * Sets the hl7TemplateId
	 */
	public void setHl7TemplateId(org.ehealth_connector.common.hl7cdar2.II value) {
		hl7TemplateId = value;
	}

	/**
	 * Sets the hl7Time
	 * Timestamp of the data input.
	 */
	public void setHl7Time(org.ehealth_connector.common.hl7cdar2.TS value) {
		hl7Time = value;
	}
}