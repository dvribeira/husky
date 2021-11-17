package org.husky.emed.cda.generated.artdecor;

import java.util.List;
import javax.annotation.processing.Generated;

import org.husky.common.hl7cdar2.*;
import org.husky.common.hl7cdar2.ObjectFactory;
import org.husky.common.hl7cdar2.POCDMT000040Observation;

/**
 * IHEProblemEntry
 * <p>
 * Template description: This section makes use of the linking, severity, clinical status and comment content specifications defined elsewhere in the technical framework. In HL7 RIM parlance, observations about a problem, complaint, symptom, finding, diagnosis, or functional limitation of a patient is the event (moodCode='EVN') of observing (&lt;observation classCode='OBS'&gt;) that problem. The &lt;value&gt; of the observation comes from a controlled vocabulary representing such things. The &lt;code&gt; contained within the &lt;observation&gt; describes the method of determination from yet another controlled vocabulary. An example appears below in the figure below.Parent TemplateThis template is compatible with the ASTM/HL7 Continuity of Care Document template: 2.16.840.1.113883.10.20.1.28<br>
 * Element description: The basic pattern for reporting a problem uses the CDA &lt;observation&gt; element, setting the classCode='OBS' to represent that this is an observation of a problem, and the moodCode='EVN', to represent that this is an observation that has in fact taken place. The negationInd attribute, if true, specifies that the problem indicated was observed to not have occurred (which is subtly but importantly different from having not been observed).The value of negationInd should not normally be set to true. Instead, to record that there is "no prior history of chicken pox", one would use a coded value indicated exactly that. However, it is not always possible to record problems in this manner, especially if using a controlled vocabulary that does not supply pre-coordinated negations, or which do not allow the negation to be recorded with post-coordinated coded terminology.<br>
 * <p>
 * Identifier: 1.3.6.1.4.1.19376.1.5.3.1.4.5<br>
 * Effective date: 2016-09-26 09:50:55<br>
 * Version: 2014<br>
 * Status: active
 */
@Generated(value = "org.ehealth_connector.codegenerator.cda.ArtDecor2JavaGenerator", date = "2021-09-08")
public class IheproblemEntry extends POCDMT000040Observation {

    public IheproblemEntry() {
        super.setMoodCode(XActMoodDocumentObservation.EVN);
        super.getTemplateId().add(createHl7TemplateIdFixedValue("1.3.6.1.4.1.19376.1.5.3.1.4.5"));
        super.getTemplateId().add(createHl7TemplateIdFixedValue("2.16.840.1.113883.10.20.1.28"));
    }

    /**
     * Adds a hl7Value
     */
    public void addHl7Value(CD value) {
        getValue().add(value);
    }

    /**
     * Adds a hl7Value
     */
    public void clearHl7Value() {
        getValue().clear();
    }

    /**
     * Creates fixed contents for CDA Element hl7EntryRelationship
     *
     * @param typeCode the desired fixed value for this argument.
     * @param inversionInd the desired fixed value for this argument.
     */
    private static POCDMT000040EntryRelationship createHl7EntryRelationshipFixedValue(String typeCode, String inversionInd) {
        ObjectFactory factory = new ObjectFactory();
        POCDMT000040EntryRelationship retVal = factory.createPOCDMT000040EntryRelationship();
        retVal.setTypeCode(XActRelationshipEntryRelationship.fromValue(typeCode));
        if (inversionInd != null) {
            retVal.setInversionInd(Boolean.parseBoolean(inversionInd));
        }
        return retVal;
    }

    /**
     * Creates fixed contents for CDA Element hl7StatusCode
     *
     * @param code the desired fixed value for this argument.
     */
    private static CS createHl7StatusCodeFixedValue(String code, String codeSystem, String codeSystemName, String displayName) {
        ObjectFactory factory = new ObjectFactory();
        CS retVal = factory.createCS();
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
    private static II createHl7TemplateIdFixedValue(String root) {
        ObjectFactory factory = new ObjectFactory();
        II retVal = factory.createII();
        retVal.setRoot(root);
        return retVal;
    }

    /**
     * Gets the hl7Code
     */
    public CD getHl7Code() {
        return code;
    }

    /**
     * Gets the hl7EffectiveTime
     */
    public IVLTS getHl7EffectiveTime() {
        return effectiveTime;
    }

    /**
     * Gets the hl7EntryRelationship
     */
    public List<POCDMT000040EntryRelationship> getHl7EntryRelationship() {
        return entryRelationship;
    }

    /**
     * Gets the hl7Id
     */
    public List<II> getHl7Id() {
        return id;
    }

    /**
     * Gets the hl7StatusCode
     * A clinical document normally records only those condition observation events that have been completed, not observations that are in any other state. Therefore, the &lt;statusCode&gt; shall always have code='completed'.
     */
    public CS getHl7StatusCode() {
        return statusCode;
    }

    /**
     * Gets the hl7TemplateId
     */
    public List<II> getHl7TemplateId() {
        return templateId;
    }

    /**
     * Gets the hl7Text
     * <div>The &lt;text&gt; element is required and points to the text describing the problem being recorded; <span style="line-height: 1.22;">including any dates, comments, et cetera. The &lt;reference&gt; contains a URI in value attribute. This </span><span style="line-height: 1.22;">URI points to the free text description of the problem in the document that is being described</span></div>
     */
    public ED getHl7Text() {
        return text;
    }

    /**
     * Adds a predefined org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040EntryRelationship, filled by: "REFR", null
     * @return the predefined element.
     */
    public static POCDMT000040EntryRelationship getPredefinedEntryRelationshipRefrNull() {
        return createHl7EntryRelationshipFixedValue("REFR",
                                                    null);
    }

    /**
     * Adds a predefined org.ehealth_connector.emed.cda.generated.hl7cdar2.CS, filled by: "completed", null, null, null
     * @return the predefined element.
     */
    public static CS getPredefinedStatusCodeCompletedNullNullNull() {
        return createHl7StatusCodeFixedValue("completed",
                                             null,
                                             null,
                                             null);
    }

    /**
     * Sets the hl7Code
     */
    public void setHl7Code(CD value) {
        this.code = value;
    }

    /**
     * Sets the hl7EffectiveTime
     */
    public void setHl7EffectiveTime(IVLTS value) {
        this.effectiveTime = value;
    }

    /**
     * Sets the hl7EntryRelationship
     */
    public void setHl7EntryRelationship(POCDMT000040EntryRelationship value) {
        getEntryRelationship().clear();
        getEntryRelationship().add(value);
    }

    /**
     * Sets the hl7Id
     */
    public void setHl7Id(II value) {
        getId().clear();
        getId().add(value);
    }

    /**
     * Sets the hl7StatusCode
     * A clinical document normally records only those condition observation events that have been completed, not observations that are in any other state. Therefore, the &lt;statusCode&gt; shall always have code='completed'.
     */
    public void setHl7StatusCode(CS value) {
        this.statusCode = value;
    }

    /**
     * Sets the hl7TemplateId
     */
    public void setHl7TemplateId(II value) {
        getTemplateId().clear();
        getTemplateId().add(value);
    }

    /**
     * Sets the hl7Text
     * <div>The &lt;text&gt; element is required and points to the text describing the problem being recorded; <span style="line-height: 1.22;">including any dates, comments, et cetera. The &lt;reference&gt; contains a URI in value attribute. This </span><span style="line-height: 1.22;">URI points to the free text description of the problem in the document that is being described</span></div>
     */
    public void setHl7Text(ED value) {
        this.text = value;
    }
}
