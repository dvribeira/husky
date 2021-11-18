package org.husky.emed.cda.generated.artdecor;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

import org.husky.common.hl7cdar2.CD;
import org.husky.common.hl7cdar2.II;
import org.husky.common.hl7cdar2.ObjectFactory;
import org.husky.common.hl7cdar2.POCDMT000040Act;

/**
 * IHEInternalReferenceEntry
 * <p>
 * Template description: <div>CDA and HL7 Version 3 Entries may reference (point to) information contained in other entries <span style="line-height: 1.22;">within the same document </span></div><br>
 * <p>
 * Identifier: 1.3.6.1.4.1.19376.1.5.3.1.4.4.1<br>
 * Effective date: 2013-12-20 00:00:00<br>
 * Version: 2014<br>
 * Status: active
 */
@Generated(value = "org.ehealth_connector.codegenerator.cda.ArtDecor2JavaGenerator", date = "2021-09-08")
public class IheinternalReferenceEntry extends POCDMT000040Act {

    public IheinternalReferenceEntry() {
        super.getTemplateId().add(createHl7TemplateIdFixedValue("1.3.6.1.4.1.19376.1.5.3.1.4.4.1"));
        super.setCode(createHl7CodeFixedValue("NA"));
    }

    /**
     * Creates fixed contents for CDA Element hl7Code
     *
     * @param nullFlavor the desired fixed value for this argument.
     */
    private static CD createHl7CodeFixedValue(String nullFlavor) {
        ObjectFactory factory = new ObjectFactory();
        CD retVal = factory.createCD();
        retVal.nullFlavor = new ArrayList<String>();
        retVal.nullFlavor.add(nullFlavor);
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
     * Gets the hl7Id
     */
    public List<II> getHl7Id() {
        return id;
    }

    /**
     * Gets the hl7TemplateId
     */
    public List<II> getHl7TemplateId() {
        return templateId;
    }

    /**
     * Sets the hl7Code
     */
    public void setHl7Code(CD value) {
        this.code = value;
    }

    /**
     * Sets the hl7Id
     */
    public void setHl7Id(II value) {
        getId().clear();
        getId().add(value);
    }

    /**
     * Sets the hl7TemplateId
     */
    public void setHl7TemplateId(II value) {
        getTemplateId().clear();
        getTemplateId().add(value);
    }
}