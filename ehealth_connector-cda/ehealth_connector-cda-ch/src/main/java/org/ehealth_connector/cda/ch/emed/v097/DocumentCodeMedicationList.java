/*
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://gitlab.com/ehealth-connector/api/wikis/Team/
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
package org.ehealth_connector.cda.ch.emed.v097;

import java.util.ArrayList;
import javax.annotation.processing.Generated;
import org.ehealth_connector.common.Code;
import org.ehealth_connector.common.basetypes.CodeBaseType;

/**
 * DocumentCodeMedicationList
 *
 * Template description: A LOINC based document type of a CDA document instance including a translation to the Swiss EPR XDS.b metadata.
 *
 * Element description: IHE PHARM PML document code
 *
 * <!-- @formatter:off -->
 * Identifier: 2.16.756.5.30.1.1.10.2.50
 * Effective date: 2019-12-02 14:35:36
 * Version: 2020
 * Status: pending
 * <!-- @formatter:on -->
 */
@Generated(value = "org.ehealth_connector.codegenerator.cda.ArtDecor2JavaGenerator", date = "2021-03-05")
public class DocumentCodeMedicationList extends org.ehealth_connector.common.hl7cdar2.CE {

	public DocumentCodeMedicationList() {
		super.setCode("56445-0");
		super.setCodeSystem("2.16.840.1.113883.6.1");
		super.setCodeSystemName("LOINC");
		super.setDisplayName("Medication summary");
		vocabTranslationCode.add(new Code(CodeBaseType.builder().withCode("721912009")
				.withCodeSystem("2.16.840.1.113883.6.96").withCodeSystemName("SNOMED CT")
				.withDisplayName("Medication summary document (record artifact)").build()));
		super.getTranslation().add((new Code(CodeBaseType.builder().withCode("721912009")
				.withCodeSystem("2.16.840.1.113883.6.96").withCodeSystemName("SNOMED CT")
				.withDisplayName("Medication summary document (record artifact)").build()))
						.getHl7CdaR2Cd());
	}

	private ArrayList<org.ehealth_connector.common.Code> vocabTranslationCode = new ArrayList<org.ehealth_connector.common.Code>();

	/**
	 * Gets the hl7Translation Translation to the Swiss EPR XDS.b metadata.
	 */
	public java.util.List<org.ehealth_connector.common.hl7cdar2.CD> getHl7Translation() {
		return translation;
	}

	/**
	 * Returns a list of vocab codes as defined in the ART-DECOR model
	 */
	public ArrayList<org.ehealth_connector.common.Code> getVocabTranslationCode() {
		return vocabTranslationCode;
	}

	/**
	 * Sets the hl7Translation Translation to the Swiss EPR XDS.b metadata.
	 */
	public void setHl7Translation(org.ehealth_connector.common.hl7cdar2.CD value) {
		getTranslation().clear();
		getTranslation().add(value);
	}
}