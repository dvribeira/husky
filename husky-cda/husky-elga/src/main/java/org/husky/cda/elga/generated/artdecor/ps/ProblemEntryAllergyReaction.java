/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.cda.elga.generated.artdecor.ps;

import java.util.List;

import javax.annotation.processing.Generated;

import org.husky.common.hl7cdar2.ANY;
import org.husky.common.hl7cdar2.II;
import org.husky.common.hl7cdar2.POCDMT000040EntryRelationship;
import org.husky.common.hl7cdar2.POCDMT000040Observation;
import org.husky.common.hl7cdar2.XActMoodDocumentObservation;

/**
 * ProblemEntryAllergy_Reaction
 * <p>
 * <p>
 * Identifier: 1.2.40.0.34.11.13.3.15<br>
 * Effective date: 2017-07-13 21:39:19<br>
 * Version: 0.1<br>
 * Status: draft
 */
@Generated(value = "org.husky.codegenerator.cda.ArtDecor2JavaGenerator", date = "2022-03-01")
public class ProblemEntryAllergyReaction extends POCDMT000040Observation {

    public ProblemEntryAllergyReaction() {
		super.getClassCode().add("OBS");
		super.setMoodCode(XActMoodDocumentObservation.EVN);
		super.getTemplateId().add(createHl7TemplateIdFixedValue("1.2.40.0.34.11.13.3.15"));
        super.setCode(createHl7CodeFixedValue());
        super.setStatusCode(createHl7StatusCodeFixedValue("completed",
                                                          null,
                                                          null,
                                                          null));
    }

    /**
     * Adds a hl7Id
     */
	public void addHl7Id(org.husky.common.hl7cdar2.II value) {
		id.add(value);
    }

    /**
     * Creates fixed contents for CDA Element hl7Code
     */
    private static org.husky.common.hl7cdar2.CE createHl7CodeFixedValue() {
        org.husky.common.hl7cdar2.CE retVal = new org.husky.common.hl7cdar2.CE();
        return retVal;
    }

    /**
     * Creates fixed contents for CDA Element hl7StatusCode
     *
     * @param code the desired fixed value for this argument.
     */
    private static org.husky.common.hl7cdar2.CS createHl7StatusCodeFixedValue(String code, String codeSystem, String codeSystemName, String displayName) {
        org.husky.common.hl7cdar2.CS retVal = new org.husky.common.hl7cdar2.CS();
        retVal.setCode(code);
        retVal.setCodeSystem(codeSystem);
        retVal.setCodeSystemName(codeSystemName);
        retVal.setDisplayName(displayName);
        return retVal;
    }

    /**
     * Creates fixed contents for CDA Element hl7TemplateId
     *
     * @param root the desired fixed value for this argument.
     */
    private static org.husky.common.hl7cdar2.II createHl7TemplateIdFixedValue(String root) {
        org.husky.common.hl7cdar2.II retVal = new org.husky.common.hl7cdar2.II();
        retVal.setRoot(root);
        return retVal;
    }

    /**
     * Gets the hl7Code
     */
	public org.husky.common.hl7cdar2.CD getHl7Code() {
		return this.code;
    }

    /**
     * Gets the hl7EffectiveTime
     */
    public org.husky.common.hl7cdar2.IVLTS getHl7EffectiveTime() {
		return this.effectiveTime;
    }

    /**
     * Gets the hl7EntryRelationship
     */
	public List<POCDMT000040EntryRelationship> getHl7EntryRelationship() {
		return this.entryRelationship;
    }

    /**
     * Gets the hl7StatusCode
     */
    public org.husky.common.hl7cdar2.CS getHl7StatusCode() {
		return this.statusCode;
    }

    /**
     * Gets the hl7TemplateId
     */
	public List<II> getHl7TemplateId() {
		return this.templateId;
    }

    /**
     * Gets the hl7Text
     */
    public org.husky.common.hl7cdar2.ED getHl7Text() {
		return this.text;
    }

    /**
     * Gets the hl7Value
     */
	public List<ANY> getHl7Value() {
		return this.value;
    }

    /**
     * Sets the hl7Code
     */
	public void setHl7Code(org.husky.common.hl7cdar2.CD value) {
		this.code = value;
    }

    /**
     * Sets the hl7EffectiveTime
     */
    public void setHl7EffectiveTime(org.husky.common.hl7cdar2.IVLTS value) {
		this.effectiveTime = value;
    }

    /**
     * Sets the hl7EntryRelationship
     */
	public void setHl7EntryRelationship(org.husky.common.hl7cdar2.POCDMT000040EntryRelationship value) {
		this.entryRelationship.clear();
		this.entryRelationship.add(value);
    }

    /**
     * Sets the hl7StatusCode
     */
    public void setHl7StatusCode(org.husky.common.hl7cdar2.CS value) {
		this.statusCode = value;
    }

    /**
     * Sets the hl7TemplateId
     */
    public void setHl7TemplateId(org.husky.common.hl7cdar2.II value) {
		this.templateId.clear();
		this.templateId.add(value);
    }

    /**
     * Sets the hl7Text
     */
    public void setHl7Text(org.husky.common.hl7cdar2.ED value) {
		this.text = value;
    }

    /**
     * Sets the hl7Value
     */
    public void setHl7Value(org.husky.common.hl7cdar2.ANY value) {
		this.value.clear();
		this.value.add(value);
    }
}