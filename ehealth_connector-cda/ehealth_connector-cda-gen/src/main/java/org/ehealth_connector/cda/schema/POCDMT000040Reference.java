//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.07.08 um 04:56:31 PM CEST 
//


package org.ehealth_connector.cda.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für POCD_MT000040.Reference complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="POCD_MT000040.Reference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="realmCode" type="{urn:hl7-org:v3}CS" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="typeId" type="{urn:hl7-org:v3}POCD_MT000040.InfrastructureRoot.typeId" minOccurs="0"/>
 *         &lt;element name="templateId" type="{urn:hl7-org:v3}II" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="seperatableInd" type="{urn:hl7-org:v3}BL" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="externalAct" type="{urn:hl7-org:v3}POCD_MT000040.ExternalAct"/>
 *           &lt;element name="externalObservation" type="{urn:hl7-org:v3}POCD_MT000040.ExternalObservation"/>
 *           &lt;element name="externalProcedure" type="{urn:hl7-org:v3}POCD_MT000040.ExternalProcedure"/>
 *           &lt;element name="externalDocument" type="{urn:hl7-org:v3}POCD_MT000040.ExternalDocument"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="nullFlavor" type="{urn:hl7-org:v3}NullFlavor" />
 *       &lt;attribute name="typeCode" use="required" type="{urn:hl7-org:v3}x_ActRelationshipExternalReference" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "POCD_MT000040.Reference", propOrder = {
    "realmCode",
    "typeId",
    "templateId",
    "seperatableInd",
    "externalAct",
    "externalObservation",
    "externalProcedure",
    "externalDocument"
})
public class POCDMT000040Reference {

    protected List<CS> realmCode;
    protected POCDMT000040InfrastructureRootTypeId typeId;
    protected List<II> templateId;
    protected BL seperatableInd;
    protected POCDMT000040ExternalAct externalAct;
    protected POCDMT000040ExternalObservation externalObservation;
    protected POCDMT000040ExternalProcedure externalProcedure;
    protected POCDMT000040ExternalDocument externalDocument;
    @XmlAttribute(name = "nullFlavor")
    protected List<String> nullFlavor;
    @XmlAttribute(name = "typeCode", required = true)
    protected XActRelationshipExternalReference typeCode;

    /**
     * Gets the value of the realmCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the realmCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRealmCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CS }
     * 
     * 
     */
    public List<CS> getRealmCode() {
        if (realmCode == null) {
            realmCode = new ArrayList<CS>();
        }
        return this.realmCode;
    }

    /**
     * Ruft den Wert der typeId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040InfrastructureRootTypeId }
     *     
     */
    public POCDMT000040InfrastructureRootTypeId getTypeId() {
        return typeId;
    }

    /**
     * Legt den Wert der typeId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040InfrastructureRootTypeId }
     *     
     */
    public void setTypeId(POCDMT000040InfrastructureRootTypeId value) {
        this.typeId = value;
    }

    /**
     * Gets the value of the templateId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the templateId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTemplateId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link II }
     * 
     * 
     */
    public List<II> getTemplateId() {
        if (templateId == null) {
            templateId = new ArrayList<II>();
        }
        return this.templateId;
    }

    /**
     * Ruft den Wert der seperatableInd-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BL }
     *     
     */
    public BL getSeperatableInd() {
        return seperatableInd;
    }

    /**
     * Legt den Wert der seperatableInd-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BL }
     *     
     */
    public void setSeperatableInd(BL value) {
        this.seperatableInd = value;
    }

    /**
     * Ruft den Wert der externalAct-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040ExternalAct }
     *     
     */
    public POCDMT000040ExternalAct getExternalAct() {
        return externalAct;
    }

    /**
     * Legt den Wert der externalAct-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040ExternalAct }
     *     
     */
    public void setExternalAct(POCDMT000040ExternalAct value) {
        this.externalAct = value;
    }

    /**
     * Ruft den Wert der externalObservation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040ExternalObservation }
     *     
     */
    public POCDMT000040ExternalObservation getExternalObservation() {
        return externalObservation;
    }

    /**
     * Legt den Wert der externalObservation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040ExternalObservation }
     *     
     */
    public void setExternalObservation(POCDMT000040ExternalObservation value) {
        this.externalObservation = value;
    }

    /**
     * Ruft den Wert der externalProcedure-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040ExternalProcedure }
     *     
     */
    public POCDMT000040ExternalProcedure getExternalProcedure() {
        return externalProcedure;
    }

    /**
     * Legt den Wert der externalProcedure-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040ExternalProcedure }
     *     
     */
    public void setExternalProcedure(POCDMT000040ExternalProcedure value) {
        this.externalProcedure = value;
    }

    /**
     * Ruft den Wert der externalDocument-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040ExternalDocument }
     *     
     */
    public POCDMT000040ExternalDocument getExternalDocument() {
        return externalDocument;
    }

    /**
     * Legt den Wert der externalDocument-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040ExternalDocument }
     *     
     */
    public void setExternalDocument(POCDMT000040ExternalDocument value) {
        this.externalDocument = value;
    }

    /**
     * Gets the value of the nullFlavor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nullFlavor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNullFlavor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNullFlavor() {
        if (nullFlavor == null) {
            nullFlavor = new ArrayList<String>();
        }
        return this.nullFlavor;
    }

    /**
     * Ruft den Wert der typeCode-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XActRelationshipExternalReference }
     *     
     */
    public XActRelationshipExternalReference getTypeCode() {
        return typeCode;
    }

    /**
     * Legt den Wert der typeCode-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XActRelationshipExternalReference }
     *     
     */
    public void setTypeCode(XActRelationshipExternalReference value) {
        this.typeCode = value;
    }

}