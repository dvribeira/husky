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
 * <p>Java-Klasse für ParticipationInformationGenerator.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="ParticipationInformationGenerator">
 *   &lt;restriction base="{urn:hl7-org:v3}cs">
 *     &lt;enumeration value="AUT"/>
 *     &lt;enumeration value="ENT"/>
 *     &lt;enumeration value="INF"/>
 *     &lt;enumeration value="WIT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ParticipationInformationGenerator")
@XmlEnum
public enum ParticipationInformationGenerator {

    AUT,
    ENT,
    INF,
    WIT;

    public String value() {
        return name();
    }

    public static ParticipationInformationGenerator fromValue(String v) {
        return valueOf(v);
    }

}