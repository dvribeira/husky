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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java-Klasse für StrucDoc.Table complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="StrucDoc.Table">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="caption" type="{urn:hl7-org:v3}StrucDoc.Caption" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="col" type="{urn:hl7-org:v3}StrucDoc.Col" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="colgroup" type="{urn:hl7-org:v3}StrucDoc.Colgroup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="thead" type="{urn:hl7-org:v3}StrucDoc.Thead" minOccurs="0"/>
 *         &lt;element name="tfoot" type="{urn:hl7-org:v3}StrucDoc.Tfoot" minOccurs="0"/>
 *         &lt;element name="tbody" type="{urn:hl7-org:v3}StrucDoc.Tbody" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="language" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="styleCode" type="{http://www.w3.org/2001/XMLSchema}NMTOKENS" />
 *       &lt;attribute name="summary" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="width" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="border" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="frame">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *             &lt;enumeration value="void"/>
 *             &lt;enumeration value="above"/>
 *             &lt;enumeration value="below"/>
 *             &lt;enumeration value="hsides"/>
 *             &lt;enumeration value="lhs"/>
 *             &lt;enumeration value="rhs"/>
 *             &lt;enumeration value="vsides"/>
 *             &lt;enumeration value="box"/>
 *             &lt;enumeration value="border"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="rules">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *             &lt;enumeration value="none"/>
 *             &lt;enumeration value="groups"/>
 *             &lt;enumeration value="rows"/>
 *             &lt;enumeration value="cols"/>
 *             &lt;enumeration value="all"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="cellspacing" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="cellpadding" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StrucDoc.Table", propOrder = {
    "caption",
    "col",
    "colgroup",
    "thead",
    "tfoot",
    "tbody"
})
public class StrucDocTable {

    protected StrucDocCaption caption;
    protected List<StrucDocCol> col;
    protected List<StrucDocColgroup> colgroup;
    protected StrucDocThead thead;
    protected StrucDocTfoot tfoot;
    @XmlElement(required = true)
    protected List<StrucDocTbody> tbody;
    @XmlAttribute(name = "ID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "language")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String language;
    @XmlAttribute(name = "styleCode")
    @XmlSchemaType(name = "NMTOKENS")
    protected List<String> styleCode;
    @XmlAttribute(name = "summary")
    protected String summary;
    @XmlAttribute(name = "width")
    protected String width;
    @XmlAttribute(name = "border")
    protected String border;
    @XmlAttribute(name = "frame")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String frame;
    @XmlAttribute(name = "rules")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String rules;
    @XmlAttribute(name = "cellspacing")
    protected String cellspacing;
    @XmlAttribute(name = "cellpadding")
    protected String cellpadding;

    /**
     * Ruft den Wert der caption-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StrucDocCaption }
     *     
     */
    public StrucDocCaption getCaption() {
        return caption;
    }

    /**
     * Legt den Wert der caption-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StrucDocCaption }
     *     
     */
    public void setCaption(StrucDocCaption value) {
        this.caption = value;
    }

    /**
     * Gets the value of the col property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the col property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCol().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StrucDocCol }
     * 
     * 
     */
    public List<StrucDocCol> getCol() {
        if (col == null) {
            col = new ArrayList<StrucDocCol>();
        }
        return this.col;
    }

    /**
     * Gets the value of the colgroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the colgroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getColgroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StrucDocColgroup }
     * 
     * 
     */
    public List<StrucDocColgroup> getColgroup() {
        if (colgroup == null) {
            colgroup = new ArrayList<StrucDocColgroup>();
        }
        return this.colgroup;
    }

    /**
     * Ruft den Wert der thead-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StrucDocThead }
     *     
     */
    public StrucDocThead getThead() {
        return thead;
    }

    /**
     * Legt den Wert der thead-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StrucDocThead }
     *     
     */
    public void setThead(StrucDocThead value) {
        this.thead = value;
    }

    /**
     * Ruft den Wert der tfoot-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StrucDocTfoot }
     *     
     */
    public StrucDocTfoot getTfoot() {
        return tfoot;
    }

    /**
     * Legt den Wert der tfoot-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StrucDocTfoot }
     *     
     */
    public void setTfoot(StrucDocTfoot value) {
        this.tfoot = value;
    }

    /**
     * Gets the value of the tbody property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tbody property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTbody().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StrucDocTbody }
     * 
     * 
     */
    public List<StrucDocTbody> getTbody() {
        if (tbody == null) {
            tbody = new ArrayList<StrucDocTbody>();
        }
        return this.tbody;
    }

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der language-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Legt den Wert der language-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the styleCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the styleCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStyleCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getStyleCode() {
        if (styleCode == null) {
            styleCode = new ArrayList<String>();
        }
        return this.styleCode;
    }

    /**
     * Ruft den Wert der summary-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Legt den Wert der summary-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummary(String value) {
        this.summary = value;
    }

    /**
     * Ruft den Wert der width-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWidth() {
        return width;
    }

    /**
     * Legt den Wert der width-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWidth(String value) {
        this.width = value;
    }

    /**
     * Ruft den Wert der border-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorder() {
        return border;
    }

    /**
     * Legt den Wert der border-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorder(String value) {
        this.border = value;
    }

    /**
     * Ruft den Wert der frame-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrame() {
        return frame;
    }

    /**
     * Legt den Wert der frame-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrame(String value) {
        this.frame = value;
    }

    /**
     * Ruft den Wert der rules-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRules() {
        return rules;
    }

    /**
     * Legt den Wert der rules-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRules(String value) {
        this.rules = value;
    }

    /**
     * Ruft den Wert der cellspacing-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCellspacing() {
        return cellspacing;
    }

    /**
     * Legt den Wert der cellspacing-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCellspacing(String value) {
        this.cellspacing = value;
    }

    /**
     * Ruft den Wert der cellpadding-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCellpadding() {
        return cellpadding;
    }

    /**
     * Legt den Wert der cellpadding-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCellpadding(String value) {
        this.cellpadding = value;
    }

}