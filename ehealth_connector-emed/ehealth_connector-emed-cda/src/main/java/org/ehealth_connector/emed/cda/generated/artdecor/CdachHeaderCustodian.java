package org.ehealth_connector.emed.cda.generated.artdecor;

import java.util.List;
import javax.annotation.processing.Generated;
import org.ehealth_connector.emed.cda.generated.hl7cdar2.ObjectFactory;
import org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040Custodian;

/**
 * cdach_header_Custodian
 * <p>
 * Template description: The organization in whose name this CDA document has been created (corresponds to the sender of a letter). All CDA-CH V2 derivatives, i.e. Swiss exchange formats MUST use this template by either reference or specialisation.<br>
 * Element description: The organization in whose name this CDA document has been created (corresponds to the sender of a letter).<br>
 * <p>
 * Identifier: 2.16.756.5.30.1.1.10.2.3<br>
 * Effective date: 2019-10-15 09:26:07<br>
 * Version: 2020<br>
 * Status: active
 */
@Generated(value = "org.ehealth_connector.codegenerator.cda.ArtDecor2JavaGenerator", date = "2021-09-08")
public class CdachHeaderCustodian extends POCDMT000040Custodian {

    public CdachHeaderCustodian() {
        super.getTemplateId().add(createHl7TemplateIdFixedValue("2.16.756.5.30.1.1.10.2.3"));
    }

    /**
     * Creates fixed contents for CDA Element hl7TemplateId
     *
     * @param root the desired fixed value for this argument.
     */
    private static org.ehealth_connector.emed.cda.generated.hl7cdar2.II createHl7TemplateIdFixedValue(String root) {
        ObjectFactory factory = new ObjectFactory();
        org.ehealth_connector.emed.cda.generated.hl7cdar2.II retVal = factory.createII();
        retVal.setRoot(root);
        return retVal;
    }

    /**
     * Gets the hl7AssignedCustodian
     */
    public org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040AssignedCustodian getHl7AssignedCustodian() {
        return assignedCustodian;
    }

    /**
     * Gets the hl7TemplateId
     */
    public List<org.ehealth_connector.emed.cda.generated.hl7cdar2.II> getHl7TemplateId() {
        return templateId;
    }

    /**
     * Sets the hl7AssignedCustodian
     */
    public void setHl7AssignedCustodian(org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040AssignedCustodian value) {
        this.assignedCustodian = value;
    }

    /**
     * Sets the hl7TemplateId
     */
    public void setHl7TemplateId(org.ehealth_connector.emed.cda.generated.hl7cdar2.II value) {
        getTemplateId().clear();
        getTemplateId().add(value);
    }
}
