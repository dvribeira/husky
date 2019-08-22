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
 * This line is intended for UTF-8 encoding checks, do not modify/delete: �����
 *
 */
package org.ehealth_connector.cda.ch.lrep.v133;

import javax.xml.bind.annotation.XmlTransient;
import org.ehealth_connector.common.hl7cdar2.ObjectFactory;

/**
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.2.56
 * Template description: A LOINC based document type of a CDA document instance including a translation to the Swiss EPR XDS.b metadata.
 * - Multidisciplinary laboratory findings:The LOINC code of the document MUST read: 11502-2 (LABORATORY REPORT.TOTAL)
 * - Laboratory reports of a single laboratory discipline:The LOINC code of the document MUST be taken from the value set 'Laboratory Specialties'
 *
 * Element description: A LOINC based document type of a CDA document instance including a translation to the Swiss EPR XDS.b metadata.
 * - Multidisciplinary laboratory findings:The LOINC code of the document MUST read: 11502-2 (LABORATORY REPORT.TOTAL)
 * - Laboratory reports of a single laboratory discipline:The LOINC code of the document MUST be taken from the value-set 'Laboratory Specialties'
 */
public class CdachlrepHeaderDocumentCode extends org.ehealth_connector.common.hl7cdar2.CE {

	public CdachlrepHeaderDocumentCode() {
		super.setCodeSystem("2.16.840.1.113883.6.1");
		super.setCodeSystemName("LOINC");
		super.getTranslation().add(createHl7TranslationFixedValue("4241000179101", "2.16.840.1.113883.6.96", "SNOMED CT", "Laboratory report"));
	// cdachlrep_header_DocumentCode/hl7:code:cs valueSet = valueSet("1.3.6.1.4.1.19376.1.3.11.1");
	// cdachlrep_header_DocumentCode/hl7:code:cs valueSet = valueSet("1.3.6.1.4.1.19376.1.3.11.1");
	// cdachlrep_header_DocumentCode/hl7:code:oid codeSystem = "2.16.840.1.113883.6.1";
	// cdachlrep_header_DocumentCode/hl7:code:st codeSystemName = "LOINC";
	// cdachlrep_header_DocumentCode/hl7:code:st displayName = valueSet("2.16.756.5.30.1.127.3.10.1.27");
	// cdachlrep_header_DocumentCode/hl7:translation:st displayName = valueSet("2.16.756.5.30.1.127.3.10.1.27");
	// cdachlrep_header_DocumentCode/hl7:translation:cs code = "4241000179101";
	// cdachlrep_header_DocumentCode/hl7:translation:oid codeSystem = "2.16.840.1.113883.6.96";
	// cdachlrep_header_DocumentCode/hl7:translation:st codeSystemName = "SNOMED CT";
	// cdachlrep_header_DocumentCode/hl7:translation:st displayName = "Laboratory report";
	}

	@XmlTransient()
	private String myCodeSystem;

	@XmlTransient()
	private String myCodeSystemName;

	@XmlTransient()
	private org.ehealth_connector.cda.ch.lrep.v133.enums.DocumentEntryTypeCode myDisplayName;

	@XmlTransient()
	private org.ehealth_connector.cda.ch.lrep.v133.enums.LaboratorySpecialties myValueSet;

	/**
	 * Creates fixed contents for CDA Attribute codeSystem
	 */
	private void createCodeSystemFixedValue(String value) {
		this.myCodeSystem = value;
	}

	/**
	 * Creates fixed contents for CDA Attribute codeSystemName
	 */
	private void createCodeSystemNameFixedValue(String value) {
		this.myCodeSystemName = value;
	}

	/**
	 * Creates fixed contents for CDA Attribute displayName
	 */
	private void createDisplayNameFixedValue(org.ehealth_connector.cda.ch.lrep.v133.enums.DocumentEntryTypeCode value) {
		this.myDisplayName = value;
	}

	/**
	 * Creates fixed contents for CDA Element hl7Translation
	 *
	 * @param code the desired fixed value for this argument.
	 * @param codeSystem the desired fixed value for this argument.
	 * @param codeSystemName the desired fixed value for this argument.
	 * @param displayName the desired fixed value for this argument.
	 */
	public org.ehealth_connector.common.hl7cdar2.CD createHl7TranslationFixedValue(String code, String codeSystem, String codeSystemName, String displayName) {
		ObjectFactory factory = new ObjectFactory();
		org.ehealth_connector.common.hl7cdar2.CD retVal = factory.createCD();
		retVal.setCode(code);
		retVal.setCodeSystem(codeSystem);
		retVal.setCodeSystemName(codeSystemName);
		retVal.setDisplayName(displayName);
		return retVal;
	}

	/**
	 * Creates fixed contents for CDA Attribute valueSet
	 */
	private void createValueSetFixedValue(org.ehealth_connector.cda.ch.lrep.v133.enums.LaboratorySpecialties value) {
		this.myValueSet = value;
	}

	/**
	 * Gets the hl7Translation
	 * The translation to the Swiss EPR XDS.b metadata attribute typeCode.
	 */
	public org.ehealth_connector.common.hl7cdar2.CD getHl7Translation() {
		org.ehealth_connector.common.hl7cdar2.CD retVal = null;
		if (getTranslation() != null)
			if (getTranslation().size() > 0)
				retVal = getTranslation().get(0);
		return retVal;
	}

	/**
	 * Gets the member myCodeSystem
	 */
	public String getPredefinedCodeSystem() {
		return myCodeSystem;
	}

	/**
	 * Gets the member myCodeSystemName
	 */
	public String getPredefinedCodeSystemName() {
		return myCodeSystemName;
	}

	/**
	 * Gets the member myDisplayName
	 */
	public org.ehealth_connector.cda.ch.lrep.v133.enums.DocumentEntryTypeCode getPredefinedDisplayName() {
		return myDisplayName;
	}

	/**
	 * Gets the member myValueSet
	 */
	public org.ehealth_connector.cda.ch.lrep.v133.enums.LaboratorySpecialties getPredefinedValueSet() {
		return myValueSet;
	}

	/**
	 * Sets the hl7Translation
	 * The translation to the Swiss EPR XDS.b metadata attribute typeCode.
	 */
	public void setHl7Translation(org.ehealth_connector.common.hl7cdar2.CD value) {
		getTranslation().clear();
		getTranslation().add(value);
	}
}
