/*
 * This code is made available under the terms of the Eclipse Public License v1.0 
 * in the github project https://github.com/project-husky/husky there you also 
 * find a list of the contributors and the license information.
 * 
 * This project has been developed further and modified by the joined working group Husky 
 * on the basis of the eHealth Connector opensource project from June 28, 2021, 
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 *
 */
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren.
// Generiert: 2020.07.09 um 01:07:39 PM CEST
//

package org.husky.common.hl7cdar2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java-Klasse für COCT_MT230100UV.Approval complex type.
 *
 * <p>
 * Das folgende Schemafragment gibt den erwarteten Content an, der in dieser
 * Klasse enthalten ist.
 *
 * <pre>
 * &lt;complexType name="COCT_MT230100UV.Approval">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:hl7-org:v3}InfrastructureRootElements"/>
 *         &lt;element name="id" type="{urn:hl7-org:v3}II"/>
 *         &lt;element name="code" type="{urn:hl7-org:v3}CD" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{urn:hl7-org:v3}CS" minOccurs="0"/>
 *         &lt;element name="holder" type="{urn:ihe:pharm}COCT_MT230100UV.Holder" minOccurs="0"/>
 *         &lt;element name="author" type="{urn:ihe:pharm}COCT_MT230100UV.Author"/>
 *       &lt;/sequence>
 *       &lt;attribute name="nullFlavor" type="{urn:hl7-org:v3}NullFlavor" />
 *       &lt;attribute name="classCode" use="required" type="{urn:hl7-org:v3}ActClassContract" />
 *       &lt;attribute name="moodCode" use="required" type="{urn:hl7-org:v3}ActMoodEventOccurrence" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "COCT_MT230100UV.Approval", namespace = "urn:ihe:pharm", propOrder = { "realmCode",
        "typeId", "templateId", "id", "code", "statusCode", "holder", "author" })
public class COCTMT230100UVApproval {

    @XmlElement(namespace = "urn:hl7-org:v3")
    protected List<CS> realmCode;
    @XmlElement(namespace = "urn:hl7-org:v3")
    protected AllInfrastructureRootTypeId typeId;
    @XmlElement(namespace = "urn:hl7-org:v3")
    protected List<AllInfrastructureRootTemplateId> templateId;
    @XmlElement(required = true)
    protected II id;
    protected CD code;
    protected CS statusCode;
    @XmlElementRef(name = "holder", namespace = "urn:ihe:pharm", type = JAXBElement.class, required = false)
    protected JAXBElement<COCTMT230100UVHolder> holder;
    @XmlElement(required = true)
    protected COCTMT230100UVAuthor author;
    @XmlAttribute(name = "nullFlavor")
    protected List<String> nullFlavor;
    @XmlAttribute(name = "classCode", required = true)
    protected String classCode;
    @XmlAttribute(name = "moodCode", required = true)
    protected ActMoodEventOccurrence moodCode;

    /**
     * Ruft den Wert der author-Eigenschaft ab.
     *
     * @return possible object is {@link COCTMT230100UVAuthor }
     *
     */
    public COCTMT230100UVAuthor getAuthor() {
        return author;
    }

    /**
     * Ruft den Wert der classCode-Eigenschaft ab.
     *
     * @return possible object is {@link String }
     *
     */
    public String getClassCode() {
        return classCode;
    }

    /**
     * Ruft den Wert der code-Eigenschaft ab.
     *
     * @return possible object is {@link CD }
     *
     */
    public CD getCode() {
        return code;
    }

    /**
     * Ruft den Wert der holder-Eigenschaft ab.
     *
     * @return possible object is {@link JAXBElement
     *         }{@code <}{@link COCTMT230100UVHolder }{@code >}
     *
     */
    public JAXBElement<COCTMT230100UVHolder> getHolder() {
        return holder;
    }

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     *
     * @return possible object is {@link II }
     *
     */
    public II getId() {
        return id;
    }

    /**
     * Ruft den Wert der moodCode-Eigenschaft ab.
     *
     * @return possible object is {@link ActMoodEventOccurrence }
     *
     */
    public ActMoodEventOccurrence getMoodCode() {
        return moodCode;
    }

    /**
     * Gets the value of the nullFlavor property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the nullFlavor property.
     *
     * <p>
     * For example, to add a new item, do as follows: <pre>
     *    getNullFlavor().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
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
     * Gets the value of the realmCode property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the realmCode property.
     *
     * <p>
     * For example, to add a new item, do as follows: <pre>
     *    getRealmCode().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link CS }
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
     * Ruft den Wert der statusCode-Eigenschaft ab.
     *
     * @return possible object is {@link CS }
     *
     */
    public CS getStatusCode() {
        return statusCode;
    }

    /**
     * Gets the value of the templateId property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the templateId property.
     *
     * <p>
     * For example, to add a new item, do as follows: <pre>
     *    getTemplateId().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AllInfrastructureRootTemplateId }
     *
     *
     */
    public List<AllInfrastructureRootTemplateId> getTemplateId() {
        if (templateId == null) {
            templateId = new ArrayList<AllInfrastructureRootTemplateId>();
        }
        return this.templateId;
    }

    /**
     * Ruft den Wert der typeId-Eigenschaft ab.
     *
     * @return possible object is {@link AllInfrastructureRootTypeId }
     *
     */
    public AllInfrastructureRootTypeId getTypeId() {
        return typeId;
    }

    /**
     * Legt den Wert der author-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link COCTMT230100UVAuthor }
     *
     */
    public void setAuthor(COCTMT230100UVAuthor value) {
        this.author = value;
    }

    /**
     * Legt den Wert der classCode-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setClassCode(String value) {
        this.classCode = value;
    }

    /**
     * Legt den Wert der code-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link CD }
     *
     */
    public void setCode(CD value) {
        this.code = value;
    }

    /**
     * Legt den Wert der holder-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link JAXBElement
     *            }{@code <}{@link COCTMT230100UVHolder }{@code >}
     *
     */
    public void setHolder(JAXBElement<COCTMT230100UVHolder> value) {
        this.holder = value;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link II }
     *
     */
    public void setId(II value) {
        this.id = value;
    }

    /**
     * Legt den Wert der moodCode-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link ActMoodEventOccurrence }
     *
     */
    public void setMoodCode(ActMoodEventOccurrence value) {
        this.moodCode = value;
    }

    /**
     * Legt den Wert der statusCode-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link CS }
     *
     */
    public void setStatusCode(CS value) {
        this.statusCode = value;
    }

    /**
     * Legt den Wert der typeId-Eigenschaft fest.
     *
     * @param value
     *            allowed object is {@link AllInfrastructureRootTypeId }
     *
     */
    public void setTypeId(AllInfrastructureRootTypeId value) {
        this.typeId = value;
    }

}