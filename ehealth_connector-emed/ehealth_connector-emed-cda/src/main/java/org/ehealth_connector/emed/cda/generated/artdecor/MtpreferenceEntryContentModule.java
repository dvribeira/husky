package org.ehealth_connector.emed.cda.generated.artdecor;

import java.util.List;
import javax.annotation.processing.Generated;
import org.ehealth_connector.emed.cda.generated.hl7cdar2.ObjectFactory;
import org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040Consumable;
import org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040ManufacturedProduct;
import org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040Material;
import org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040SubstanceAdministration;

/**
 * MTPReferenceEntryContentModule
 * <p>
 * Template description: Reference to a Medication Treatment Plan Entry<br>
 * Element description: Reference to Medication Treatment Plan Item General Specification<br>
 * <p>
 * Identifier: 2.16.756.5.30.1.1.10.4.45<br>
 * Effective date: 2017-01-10 15:34:25<br>
 * Version: 2017<br>
 * Status: pending
 */
@Generated(value = "org.ehealth_connector.codegenerator.cda.ArtDecor2JavaGenerator", date = "2021-09-08")
public class MtpreferenceEntryContentModule extends POCDMT000040SubstanceAdministration {

    public MtpreferenceEntryContentModule() {
        super.getClassCode().add("SBADM");
        super.setMoodCode(org.ehealth_connector.emed.cda.generated.hl7cdar2.XDocumentSubstanceMood.INT);
        super.getTemplateId().add(createHl7TemplateIdFixedValue("1.3.6.1.4.1.19376.1.9.1.3.10"));
        super.getTemplateId().add(createHl7TemplateIdFixedValue("2.16.756.5.30.1.1.10.4.45"));
        super.setCode(createHl7CodeFixedValue("MTPItem",
                                              "1.3.6.1.4.1.19376.1.9.2.2",
                                              null,
                                              null));
        super.setConsumable(createHl7ConsumableNa());
    }

    /**
     * Creates fixed contents for CDA Element hl7Code
     *
     * @param code the desired fixed value for this argument.
     */
    private static org.ehealth_connector.emed.cda.generated.hl7cdar2.CD createHl7CodeFixedValue(String code, String codeSystem, String codeSystemName, String displayName) {
        ObjectFactory factory = new ObjectFactory();
        org.ehealth_connector.emed.cda.generated.hl7cdar2.CD retVal = factory.createCD();
        retVal.setCode(code);
        retVal.setCodeSystem(codeSystem);
        retVal.setCodeSystemName(codeSystemName);
        retVal.setDisplayName(displayName);
        return retVal;
    }

    /**
     * Creates fixed contents for CDA Element hl7Consumable, containing an hl7ManufacturedMaterial with a null flavor NA.
     */
    private static POCDMT000040Consumable createHl7ConsumableNa() {
        final POCDMT000040Material material = new POCDMT000040Material();
        material.getNullFlavor().add("NA");
        final POCDMT000040ManufacturedProduct product = new POCDMT000040ManufacturedProduct();
        product.setManufacturedMaterial(material);
        final POCDMT000040Consumable consumable = new POCDMT000040Consumable();
        consumable.setManufacturedProduct(product);
        return consumable;
    }

    /**
     * Creates fixed contents for CDA Element hl7Reference
     *
     * @param typeCode the desired fixed value for this argument.
     */
    private static org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040Reference createHl7ReferenceFixedValue(String typeCode) {
        ObjectFactory factory = new ObjectFactory();
        org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040Reference retVal = factory.createPOCDMT000040Reference();
        retVal.setTypeCode(org.ehealth_connector.emed.cda.generated.hl7cdar2.XActRelationshipExternalReference.fromValue(typeCode));
        return retVal;
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
     * Gets the hl7Author
     * Author of the referenced item
     */
    public List<org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040Author> getHl7Author() {
        return author;
    }

    /**
     * Gets the hl7Code
     * Reference to Medication Treatment Plan Item code
     */
    public org.ehealth_connector.emed.cda.generated.hl7cdar2.CD getHl7Code() {
        return code;
    }

    /**
     * Gets the hl7Consumable
     */
    public org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040Consumable getHl7Consumable() {
        return consumable;
    }

    /**
     * Gets the hl7Id
     * Reference to Medication Treatment Plan Item ID
     */
    public List<org.ehealth_connector.emed.cda.generated.hl7cdar2.II> getHl7Id() {
        return id;
    }

    /**
     * Gets the hl7Reference
     * ID of parent container of referenced item
     */
    public List<org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040Reference> getHl7Reference() {
        return reference;
    }

    /**
     * Gets the hl7TemplateId
     * Reference to Medication Treatment Plan Item General Specification Template ID
     */
    public List<org.ehealth_connector.emed.cda.generated.hl7cdar2.II> getHl7TemplateId() {
        return templateId;
    }

    /**
     * Adds a predefined org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040Reference, filled by: "XCRPT"
     * @return the predefined element.
     */
    public static org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040Reference getPredefinedReferenceXcrpt() {
        return createHl7ReferenceFixedValue("XCRPT");
    }

    /**
     * Sets the hl7Author
     * Author of the referenced item
     */
    public void setHl7Author(org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040Author value) {
        getAuthor().clear();
        getAuthor().add(value);
    }

    /**
     * Sets the hl7Code
     * Reference to Medication Treatment Plan Item code
     */
    public void setHl7Code(org.ehealth_connector.emed.cda.generated.hl7cdar2.CD value) {
        this.code = value;
    }

    /**
     * Sets the hl7Consumable
     */
    public void setHl7Consumable(org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040Consumable value) {
        this.consumable = value;
    }

    /**
     * Sets the hl7Id
     * Reference to Medication Treatment Plan Item ID
     */
    public void setHl7Id(org.ehealth_connector.emed.cda.generated.hl7cdar2.II value) {
        getId().clear();
        getId().add(value);
    }

    /**
     * Sets the hl7Reference
     * ID of parent container of referenced item
     */
    public void setHl7Reference(org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040Reference value) {
        getReference().clear();
        getReference().add(value);
    }

    /**
     * Sets the hl7TemplateId
     * Reference to Medication Treatment Plan Item General Specification Template ID
     */
    public void setHl7TemplateId(org.ehealth_connector.emed.cda.generated.hl7cdar2.II value) {
        getTemplateId().clear();
        getTemplateId().add(value);
    }
}
