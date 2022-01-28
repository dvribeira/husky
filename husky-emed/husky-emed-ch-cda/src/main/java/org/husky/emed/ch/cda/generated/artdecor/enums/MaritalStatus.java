/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.emed.ch.cda.generated.artdecor.enums;

import java.util.Objects;
import javax.annotation.processing.Generated;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.common.enums.CodeSystems;
import org.husky.common.enums.LanguageCode;
import org.husky.common.enums.ValueSetEnumInterface;

/**
 * Enumeration of MaritalStatus values
 * <p>
 * EN: <p> <b>History description 2014-03-26: </b>Lock all vaue sets untouched since 2014-03-26 to trackingId 2014T1_2014_03_26</p><b>description: </b><p>The domestic partnership status of a person.</p><p> <i>Example:</i> Married, separated, divorced, widowed, common-law marriage.</p>.<br>
 * DE: No designation found.<br>
 * FR: No designation found.<br>
 * IT: No designation found.<br>
 * <p>
 * Identifier: 2.16.840.1.113883.1.11.12212<br>
 * Effective date: 2014-03-26 00:00<br>
 * Version: DEFN=UV=VO=1360-20160323<br>
 * Status: FINAL
 */
@Generated(value = "org.husky.codegenerator.ch.valuesets.UpdateValueSets", date = "2021-12-09")
public enum MaritalStatus implements ValueSetEnumInterface {

    /**
     * EN: Annulled.<br>
     */
    ANNULLED("A",
             "2.16.840.1.113883.5.2",
             "Annulled",
             "Annulled",
             "TOTRANSLATE",
             "TOTRANSLATE",
             "TOTRANSLATE"),
    /**
     * EN: Divorced.<br>
     */
    DIVORCED("D",
             "2.16.840.1.113883.5.2",
             "Divorced",
             "Divorced",
             "TOTRANSLATE",
             "TOTRANSLATE",
             "TOTRANSLATE"),
    /**
     * EN: Domestic partner.<br>
     */
    DOMESTIC_PARTNER("T",
                     "2.16.840.1.113883.5.2",
                     "Domestic partner",
                     "Domestic partner",
                     "TOTRANSLATE",
                     "TOTRANSLATE",
                     "TOTRANSLATE"),
    /**
     * EN: Interlocutory.<br>
     */
    INTERLOCUTORY("I",
                  "2.16.840.1.113883.5.2",
                  "Interlocutory",
                  "Interlocutory",
                  "TOTRANSLATE",
                  "TOTRANSLATE",
                  "TOTRANSLATE"),
    /**
     * EN: Legally Separated.<br>
     */
    LEGALLY_SEPARATED("L",
                      "2.16.840.1.113883.5.2",
                      "Legally Separated",
                      "Legally Separated",
                      "TOTRANSLATE",
                      "TOTRANSLATE",
                      "TOTRANSLATE"),
    /**
     * EN: Married.<br>
     */
    MARRIED("M",
            "2.16.840.1.113883.5.2",
            "Married",
            "Married",
            "TOTRANSLATE",
            "TOTRANSLATE",
            "TOTRANSLATE"),
    /**
     * EN: Never Married.<br>
     */
    NEVER_MARRIED("S",
                  "2.16.840.1.113883.5.2",
                  "Never Married",
                  "Never Married",
                  "TOTRANSLATE",
                  "TOTRANSLATE",
                  "TOTRANSLATE"),
    /**
     * EN: Polygamous.<br>
     */
    POLYGAMOUS("P",
               "2.16.840.1.113883.5.2",
               "Polygamous",
               "Polygamous",
               "TOTRANSLATE",
               "TOTRANSLATE",
               "TOTRANSLATE"),
    /**
     * EN: Widowed.<br>
     */
    WIDOWED("W",
            "2.16.840.1.113883.5.2",
            "Widowed",
            "Widowed",
            "TOTRANSLATE",
            "TOTRANSLATE",
            "TOTRANSLATE");

    /**
     * EN: Code for Annulled.<br>
     */
    public static final String ANNULLED_CODE = "A";

    /**
     * EN: Code for Divorced.<br>
     */
    public static final String DIVORCED_CODE = "D";

    /**
     * EN: Code for Domestic partner.<br>
     */
    public static final String DOMESTIC_PARTNER_CODE = "T";

    /**
     * EN: Code for Interlocutory.<br>
     */
    public static final String INTERLOCUTORY_CODE = "I";

    /**
     * EN: Code for Legally Separated.<br>
     */
    public static final String LEGALLY_SEPARATED_CODE = "L";

    /**
     * EN: Code for Married.<br>
     */
    public static final String MARRIED_CODE = "M";

    /**
     * EN: Code for Never Married.<br>
     */
    public static final String NEVER_MARRIED_CODE = "S";

    /**
     * EN: Code for Polygamous.<br>
     */
    public static final String POLYGAMOUS_CODE = "P";

    /**
     * EN: Code for Widowed.<br>
     */
    public static final String WIDOWED_CODE = "W";

    /**
     * Identifier of the value set.
     */
    public static final String VALUE_SET_ID = "2.16.840.1.113883.1.11.12212";

    /**
     * Name of the value set.
     */
    public static final String VALUE_SET_NAME = "MaritalStatus";

    /**
     * Identifier of the code system (all values share the same).
     */
    public static final String CODE_SYSTEM_ID = "2.16.840.1.113883.5.2";

    /**
     * Gets the Enum with a given code.
     *
     * @param code The code value.
     * @return the enum value found or {@code null}.
     */
    @Nullable
    public static MaritalStatus getEnum(@Nullable final String code) {
        for (final MaritalStatus x : values()) {
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
    public static boolean isEnumOfValueSet(@Nullable final String enumName) {
        if (enumName == null) {
            return false;
        }
        try {
            Enum.valueOf(MaritalStatus.class,
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
    public static boolean isInValueSet(@Nullable final String codeValue) {
        for (final MaritalStatus x : values()) {
            if (x.getCodeValue().equals(codeValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Machine interpretable and (inside this class) unique code.
     */
    @NonNull
    private final String code;

    /**
     * Identifier of the referencing code system.
     */
    @NonNull
    private final String codeSystem;

    /**
     * The display names per language. It's always stored in the given order: default display name (0), in English (1),
     * in German (2), in French (3) and in Italian (4).
     */
    @NonNull
    private final String[] displayNames;

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
    MaritalStatus(@NonNull final String code, @NonNull final String codeSystem, @NonNull final String displayName, @NonNull final String displayNameEn, @NonNull final String displayNameDe, @NonNull final String displayNameFr, @NonNull final String displayNameIt) {
        this.code = Objects.requireNonNull(code);
        this.codeSystem = Objects.requireNonNull(codeSystem);
        this.displayNames = new String[5];
        this.displayNames[0] = Objects.requireNonNull(displayName);
        this.displayNames[1] = Objects.requireNonNull(displayNameEn);
        this.displayNames[2] = Objects.requireNonNull(displayNameDe);
        this.displayNames[3] = Objects.requireNonNull(displayNameFr);
        this.displayNames[4] = Objects.requireNonNull(displayNameIt);
    }

    /**
     * Gets the code system identifier.
     *
     * @return the code system identifier.
     */
    @Override
    @NonNull
    public String getCodeSystemId() {
        return this.codeSystem;
    }

    /**
     * Gets the code system name.
     *
     * @return the code system name.
     */
    @Override
    @NonNull
    public String getCodeSystemName() {
        final var codeSystem = CodeSystems.getEnum(this.codeSystem);
        if (codeSystem != null) {
            return codeSystem.getCodeSystemName();
        }
        return "";
    }

    /**
     * Gets the code value as a string.
     *
     * @return the code value.
     */
    @Override
    @NonNull
    public String getCodeValue() {
        return this.code;
    }

    /**
     * Gets the display name defined by the language param.
     *
     * @param languageCode The language code to get the display name for, {@code null} to get the default display name.
     * @return the display name in the desired language.
     */
    @Override
    @NonNull
    public String getDisplayName(@Nullable final LanguageCode languageCode) {
        if (languageCode == null) {
            return this.displayNames[0];
        }
        return switch(languageCode) {
            case ENGLISH ->
                this.displayNames[1];
            case GERMAN ->
                this.displayNames[2];
            case FRENCH ->
                this.displayNames[3];
            case ITALIAN ->
                this.displayNames[4];
            default ->
                "TOTRANSLATE";
        };
    }

    /**
     * Gets the value set identifier.
     *
     * @return the value set identifier.
     */
    @Override
    @NonNull
    public String getValueSetId() {
        return VALUE_SET_ID;
    }

    /**
     * Gets the value set name.
     *
     * @return the value set name.
     */
    @Override
    @NonNull
    public String getValueSetName() {
        return VALUE_SET_NAME;
    }
}