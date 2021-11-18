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
package org.husky.emed.cda.generated.artdecor.enums;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.processing.Generated;

import org.husky.common.enums.CodeSystems;
import org.husky.common.enums.LanguageCode;
import org.husky.common.enums.ValueSetEnumInterface;

/**
 * Enumeration of ProblemStatusObservation_value values
 * <p>
 * EN: No designation found.<br>
 * DE: No designation found.<br>
 * FR: No designation found.<br>
 * IT: No designation found.<br>
 * <p>
 * Identifier: 1.3.6.1.4.1.19376.1.5.3.1.11.2<br>
 * Effective date: 2016-09-26 13:58<br>
 * Version: 2014<br>
 * Status: FINAL
 */
@Generated(value = "org.ehealth_connector.codegenerator.ch.valuesets.UpdateValueSets", date = "2021-09-08")
public enum ProblemStatusObservationValue implements ValueSetEnumInterface {

    /**
     * EN: Active<br>
     */
    ACTIVE("55561003",
           "2.16.840.1.113883.6.96",
           "Active",
           "Active",
           "TOTRANSLATE",
           "TOTRANSLATE",
           "TOTRANSLATE"),
    /**
     * EN: Chronic<br>
     */
    CHRONIC("90734009",
            "2.16.840.1.113883.6.96",
            "Chronic",
            "Chronic",
            "TOTRANSLATE",
            "TOTRANSLATE",
            "TOTRANSLATE"),
    /**
     * EN: Inactive<br>
     */
    INACTIVE("73425007",
             "2.16.840.1.113883.6.96",
             "Inactive",
             "Inactive",
             "TOTRANSLATE",
             "TOTRANSLATE",
             "TOTRANSLATE"),
    /**
     * EN: Intermittent<br>
     */
    INTERMITTENT("7087005",
                 "2.16.840.1.113883.6.96",
                 "Intermittent",
                 "Intermittent",
                 "TOTRANSLATE",
                 "TOTRANSLATE",
                 "TOTRANSLATE"),
    /**
     * EN: Recurrent<br>
     */
    RECURRENT("255227004",
              "2.16.840.1.113883.6.96",
              "Recurrent",
              "Recurrent",
              "TOTRANSLATE",
              "TOTRANSLATE",
              "TOTRANSLATE"),
    /**
     * EN: Resolved<br>
     */
    RESOLVED("413322009",
             "2.16.840.1.113883.6.96",
             "Resolved",
             "Resolved",
             "TOTRANSLATE",
             "TOTRANSLATE",
             "TOTRANSLATE"),
    /**
     * EN: Ruled out<br>
     */
    RULED_OUT("410516002",
              "2.16.840.1.113883.6.96",
              "Ruled out",
              "Ruled out",
              "TOTRANSLATE",
              "TOTRANSLATE",
              "TOTRANSLATE"),
    /**
     * EN: Rule out<br>
     */
    RULE_OUT("415684004",
             "2.16.840.1.113883.6.96",
             "Rule out",
             "Rule out",
             "TOTRANSLATE",
             "TOTRANSLATE",
             "TOTRANSLATE");

    /**
     * EN: Code for Active<br>
     */
    public static final String ACTIVE_CODE = "55561003";

    /**
     * EN: Code for Chronic<br>
     */
    public static final String CHRONIC_CODE = "90734009";

    /**
     * EN: Code for Inactive<br>
     */
    public static final String INACTIVE_CODE = "73425007";

    /**
     * EN: Code for Intermittent<br>
     */
    public static final String INTERMITTENT_CODE = "7087005";

    /**
     * EN: Code for Recurrent<br>
     */
    public static final String RECURRENT_CODE = "255227004";

    /**
     * EN: Code for Resolved<br>
     */
    public static final String RESOLVED_CODE = "413322009";

    /**
     * EN: Code for Ruled out<br>
     */
    public static final String RULED_OUT_CODE = "410516002";

    /**
     * EN: Code for Rule out<br>
     */
    public static final String RULE_OUT_CODE = "415684004";

    /**
     * Identifier of the value set.
     */
    public static final String VALUE_SET_ID = "1.3.6.1.4.1.19376.1.5.3.1.11.2";

    /**
     * Name of the value set.
     */
    public static final String VALUE_SET_NAME = "ProblemStatusObservation_value";

    /**
     * Gets the Enum with a given code.
     *
     * @param code The code value.
     * @return the enum value found or {@code null}.
     */
    public static ProblemStatusObservationValue getEnum(final String code) {
        for (final ProblemStatusObservationValue x : values()) {
            if (x.getCodeValue().equals(code)) {
                return x;
            }
        }
        return null;
    }

    /**
     * Checks if a given enum is part of this value set.
     *
     * @param enumName The name of the enum.
     * @return {@code true} if the name is found in this value set, {@code false} otherwise.
     */
    public static boolean isEnumOfValueSet(final String enumName) {
        if (enumName == null) {
            return false;
        }
        try {
            Enum.valueOf(ProblemStatusObservationValue.class,
                         enumName);
            return true;
        } catch (final IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * Checks if a given code value is in this value set.
     *
     * @param codeValue The code value.
     * @return {@code true} if the value is found in this value set, {@code false} otherwise.
     */
    public static boolean isInValueSet(final String codeValue) {
        for (final ProblemStatusObservationValue x : values()) {
            if (x.getCodeValue().equals(codeValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Machine interpretable and (inside this class) unique code.
     */
    private String code;

    /**
     * Identifier of the referencing code system.
     */
    private String codeSystem;

    /**
     * The display names per language.
     */
    private Map<LanguageCode, String> displayNames;

    /**
     * Instantiates this enum with a given code and display names.
     *
     * @param code          The code value.
     * @param codeSystem    The code system (OID).
     * @param displayName   The default display name.
     * @param displayNameEn The display name in English.
     * @param displayNameDe The display name in German.
     * @param displayNameFr The display name in French.
     * @param displayNameIt The display name in Italian.
     */
    ProblemStatusObservationValue(final String code, final String codeSystem, final String displayName, final String displayNameEn, final String displayNameDe, final String displayNameFr, final String displayNameIt) {
        this.code = code;
        this.codeSystem = codeSystem;
        this.displayNames = new HashMap<>();
        this.displayNames.put(null,
                              displayName);
        this.displayNames.put(LanguageCode.ENGLISH,
                              displayNameEn);
        this.displayNames.put(LanguageCode.GERMAN,
                              displayNameDe);
        this.displayNames.put(LanguageCode.FRENCH,
                              displayNameFr);
        this.displayNames.put(LanguageCode.ITALIAN,
                              displayNameIt);
    }

    /**
     * Gets the code system identifier.
     *
     * @return the code system identifier.
     */
    @Override
    public String getCodeSystemId() {
        return this.codeSystem;
    }

    /**
     * Gets the code system name.
     *
     * @return the code system identifier.
     */
    @Override
    public String getCodeSystemName() {
        final CodeSystems cs = CodeSystems.getEnum(this.codeSystem);
        if (cs != null) {
            return cs.getCodeSystemName();
        }
        return "";
    }

    /**
     * Gets the code value as a string.
     *
     * @return the code value.
     */
    @Override
    public String getCodeValue() {
        return this.code;
    }

    /**
     * Gets the display name defined by the language param. If there is no english translation, the default display name
     *      is returned.
     *
     * @param languageCode The language code to get the display name for.
     * @return the display name in the desired language. if language not found, display name in german will be returned.
     */
    @Override
    public String getDisplayName(final LanguageCode languageCode) {
        final String displayName = this.displayNames.get(languageCode);
        if (displayName == null && languageCode == LanguageCode.ENGLISH) {
            return this.displayNames.get(null);
        }
        return displayName;
    }

    /**
     * Gets the value set identifier.
     *
     * @return the value set identifier.
     */
    @Override
    public String getValueSetId() {
        return VALUE_SET_ID;
    }

    /**
     * Gets the value set name.
     *
     * @return the value set name.
     */
    @Override
    public String getValueSetName() {
        return VALUE_SET_NAME;
    }
}