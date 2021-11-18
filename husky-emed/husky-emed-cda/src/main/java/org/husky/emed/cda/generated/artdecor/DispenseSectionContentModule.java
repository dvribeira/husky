package org.husky.emed.cda.generated.artdecor;

import java.util.List;
import javax.annotation.processing.Generated;

import org.husky.common.hl7cdar2.*;
import org.husky.common.hl7cdar2.POCDMT000040Section;

/**
 * DispenseSectionContentModule
 * <p>
 * Template description: The Dispense Section contains a description of a medication dispensed for the patient. It includes exactly one Dispense Item entry as described in the Dispense Item Entry Content Module. See also<a href="http://www.ihe.net/uploadedFiles/Documents/Pharmacy/IHE_Pharmacy_Suppl_DIS.pdf">IHE Pharmacy DIS Suppl</a><br>
 * <p>
 * Identifier: 2.16.756.5.30.1.1.10.3.11<br>
 * Effective date: 2016-06-06 00:00:00<br>
 * Version: 2017<br>
 * Status: draft
 */
@Generated(value = "org.ehealth_connector.codegenerator.cda.ArtDecor2JavaGenerator", date = "2021-09-08")
public class DispenseSectionContentModule extends POCDMT000040Section {

    public DispenseSectionContentModule() {
        super.getTemplateId().add(createHl7TemplateIdFixedValue("1.3.6.1.4.1.19376.1.9.1.2.3"));
        super.getTemplateId().add(createHl7TemplateIdFixedValue("2.16.756.5.30.1.1.10.3.11"));
        super.setCode(createHl7CodeFixedValue("60590-7",
                                              "2.16.840.1.113883.6.1",
                                              "LOINC",
                                              "Medication dispensed.brief"));
    }

    /**
     * Adds a hl7Author
     * Information about the author of a CDA document, section or entry. An author MAY be a person or a device.
     */
    public void addHl7Author(POCDMT000040Author value) {
        getAuthor().add(value);
    }

    /**
     * Adds a hl7Author
     * Information about the author of a CDA document, section or entry. An author MAY be a person or a device.
     */
    public void clearHl7Author() {
        getAuthor().clear();
    }

    /**
     * Creates fixed contents for CDA Element hl7Code
     *
     * @param code the desired fixed value for this argument.
     */
    private static CE createHl7CodeFixedValue(String code, String codeSystem, String codeSystemName, String displayName) {
        ObjectFactory factory = new ObjectFactory();
        CE retVal = factory.createCE();
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
    public CE getHl7Code() {
        return code;
    }

    /**
     * Gets the hl7Entry
     */
    public List<POCDMT000040Entry> getHl7Entry() {
        return entry;
    }

    /**
     * Gets the hl7Id
     */
    public II getHl7Id() {
        return id;
    }

    /**
     * Gets the hl7TemplateId
     */
    public List<II> getHl7TemplateId() {
        return templateId;
    }

    /**
     * Gets the hl7Text
     */
    public StrucDocText getHl7Text() {
        return text;
    }

    /**
     * Gets the hl7Title
     * <div>The German title shall be "Abgabe eines Medikaments".</div><div>The French title shall be "Dispensation d'un médicament".</div><div>The Italian title shall be "Dispensazione di un medicamento".</div><div>The English title shall be "Medication dispensed".</div><div>Titles in other languages are allowed and unrestricted.</div>
     */
    public ST getHl7Title() {
        return title;
    }

    /**
     * Sets the hl7Code
     */
    public void setHl7Code(CE value) {
        this.code = value;
    }

    /**
     * Sets the hl7Entry
     */
    public void setHl7Entry(POCDMT000040Entry value) {
        getEntry().clear();
        getEntry().add(value);
    }

    /**
     * Sets the hl7Id
     */
    public void setHl7Id(II value) {
        this.id = value;
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
     */
    public void setHl7Text(StrucDocText value) {
        this.text = value;
    }

    /**
     * Sets the hl7Title
     * <div>The German title shall be "Abgabe eines Medikaments".</div><div>The French title shall be "Dispensation d'un médicament".</div><div>The Italian title shall be "Dispensazione di un medicamento".</div><div>The English title shall be "Medication dispensed".</div><div>Titles in other languages are allowed and unrestricted.</div>
     */
    public void setHl7Title(ST value) {
        this.title = value;
    }
}