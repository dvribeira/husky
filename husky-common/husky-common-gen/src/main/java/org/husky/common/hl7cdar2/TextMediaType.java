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

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java-Klasse für TextMediaType.
 *
 * <p>
 * Das folgende Schemafragment gibt den erwarteten Content an, der in dieser
 * Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="TextMediaType">
 *   &lt;restriction base="{urn:hl7-org:v3}cs">
 *     &lt;enumeration value="text/html"/>
 *     &lt;enumeration value="text/plain"/>
 *     &lt;enumeration value="text/rtf"/>
 *     &lt;enumeration value="text/sgml"/>
 *     &lt;enumeration value="text/x-hl7-ft"/>
 *     &lt;enumeration value="text/xml"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "TextMediaType")
@XmlEnum
public enum TextMediaType {

    @XmlEnumValue("text/html")
    TEXT_HTML("text/html"), @XmlEnumValue("text/plain")
    TEXT_PLAIN("text/plain"), @XmlEnumValue("text/rtf")
    TEXT_RTF("text/rtf"), @XmlEnumValue("text/sgml")
    TEXT_SGML("text/sgml"), @XmlEnumValue("text/x-hl7-ft")
    TEXT_X_HL_7_FT("text/x-hl7-ft"), @XmlEnumValue("text/xml")
    TEXT_XML("text/xml");

    public static TextMediaType fromValue(String v) {
        for (TextMediaType c : TextMediaType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    private final String value;

    TextMediaType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

}