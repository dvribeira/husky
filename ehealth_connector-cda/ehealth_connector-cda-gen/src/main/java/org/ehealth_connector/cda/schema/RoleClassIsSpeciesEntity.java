//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.07.08 um 04:56:31 PM CEST 
//


package org.ehealth_connector.cda.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für RoleClassIsSpeciesEntity.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="RoleClassIsSpeciesEntity">
 *   &lt;restriction base="{urn:hl7-org:v3}cs">
 *     &lt;enumeration value="GEN"/>
 *     &lt;enumeration value="GRIC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RoleClassIsSpeciesEntity")
@XmlEnum
public enum RoleClassIsSpeciesEntity {

    GEN,
    GRIC;

    public String value() {
        return name();
    }

    public static RoleClassIsSpeciesEntity fromValue(String v) {
        return valueOf(v);
    }

}
