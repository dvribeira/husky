package org.husky.emed.cda.generated.artdecor;

import java.util.List;
import javax.annotation.processing.Generated;

import org.husky.common.hl7cdar2.*;

/**
 * cdach_other_Author
 * <p>
 * Template description: Information about the author of a CDA document, section or entry. An author MAY be a person or a device. All CDA-CH V2 derivatives, i.e. Swiss exchange formats MUST use this template at least in the CDA Header by either reference or specialisation.<br>
 * Element description: Information about the author of a CDA document, section or entry. An author MAY be a person or a device.<br>
 * <p>
 * Identifier: 2.16.756.5.30.1.1.10.9.23<br>
 * Effective date: 2019-09-12 13:48:28<br>
 * Version: 2020<br>
 * Status: active
 */
@Generated(value = "org.ehealth_connector.codegenerator.cda.ArtDecor2JavaGenerator", date = "2021-09-08")
public class CdachOtherAuthor extends POCDMT000040Author {

    public CdachOtherAuthor() {
        super.getTemplateId().add(createHl7TemplateIdFixedValue("2.16.756.5.30.1.1.10.9.23"));
    }

    /**
     * Creates fixed contents for CDA Element hl7FunctionCode
     */
    private static CE createHl7FunctionCodeFixedValue() {
        ObjectFactory factory = new ObjectFactory();
        CE retVal = factory.createCE();
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
     * Gets the hl7AssignedAuthor
     */
    public POCDMT000040AssignedAuthor getHl7AssignedAuthor() {
        return assignedAuthor;
    }

    /**
     * Gets the hl7FunctionCode
     */
    public CE getHl7FunctionCode() {
        return functionCode;
    }

    /**
     * Gets the hl7TemplateId
     */
    public List<II> getHl7TemplateId() {
        return templateId;
    }

    /**
     * Gets the hl7Time
     * Timestamp of the authorship.
     */
    public TS getHl7Time() {
        return time;
    }

    /**
     * Adds a predefined org.ehealth_connector.emed.cda.generated.hl7cdar2.CE, filled by:
     * @return the predefined element.
     */
    public static CE getPredefinedFunctionCode() {
        return createHl7FunctionCodeFixedValue();
    }

    /**
     * Sets the hl7AssignedAuthor
     */
    public void setHl7AssignedAuthor(POCDMT000040AssignedAuthor value) {
        this.assignedAuthor = value;
    }

    /**
     * Sets the hl7FunctionCode
     */
    public void setHl7FunctionCode(CE value) {
        this.functionCode = value;
    }

    /**
     * Sets the hl7TemplateId
     */
    public void setHl7TemplateId(II value) {
        getTemplateId().clear();
        getTemplateId().add(value);
    }

    /**
     * Sets the hl7Time
     * Timestamp of the authorship.
     */
    public void setHl7Time(TS value) {
        this.time = value;
    }
}
