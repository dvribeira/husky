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
package org.husky.communication.ch.enums;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.processing.Generated;

import org.husky.common.enums.CodeSystems;
import org.husky.common.enums.LanguageCode;
import org.husky.common.enums.ValueSetEnumInterface;

/**
 * Enumeration of SubmissionSet.Author.AuthorRole values
 * <p>
 * EN: No designation found.<br>
 * DE: No designation found.<br>
 * FR: No designation found.<br>
 * IT: No designation found.<br>
 * <p>
 * Identifier: 2.16.756.5.30.1.127.3.10.1.41<br>
 * Effective date: 2021-04-01 17:12<br>
 * Version: 202104.0-stable<br>
 * Status: FINAL
 */
@Generated(value = "org.ehealth_connector.codegenerator.ch.valuesets.UpdateValueSets", date = "2021-08-26")
public enum SubmissionSetAuthorRole implements ValueSetEnumInterface {

    /**
     * EN: Assistant<br>
     * DE: Hilfsperson<br>
     * FR: Auxiliaire<br>
     * IT: Persona ausiliara<br>
     */
    ASSISTANT("ASS",
              "2.16.756.5.30.1.127.3.10.6",
              "Assistant",
              "Assistant",
              "Hilfsperson",
              "Auxiliaire",
              "Persona ausiliara"),
    /**
     * EN: Document administrator<br>
     * DE: Dokumenten-Administrator<br>
     * FR: Administrateur de documents<br>
     * IT: Amministratore dei documenti<br>
     */
    DOCUMENT_ADMINISTRATOR("DADM",
                           "2.16.756.5.30.1.127.3.10.6",
                           "Document administrator",
                           "Document administrator",
                           "Dokumenten-Administrator",
                           "Administrateur de documents",
                           "Amministratore dei documenti"),
    /**
     * EN: Healthcare professional<br>
     * DE: Gesundheitsfachperson<br>
     * FR: Professionnel de la santé<br>
     * IT: Professionista della salute<br>
     */
    HEALTHCARE_PROFESSIONAL("HCP",
                            "2.16.756.5.30.1.127.3.10.6",
                            "Healthcare professional",
                            "Healthcare professional",
                            "Gesundheitsfachperson",
                            "Professionnel de la santé",
                            "Professionista della salute"),
    /**
     * EN: Patient<br>
     * DE: Patient<br>
     * FR: Patient<br>
     * IT: Paziente<br>
     */
    PATIENT("PAT",
            "2.16.756.5.30.1.127.3.10.6",
            "Patient",
            "Patient",
            "Patient",
            "Patient",
            "Paziente"),
    /**
     * EN: Representative<br>
     * DE: Stellvertretung<br>
     * FR: Représentant<br>
     * IT: Rappresentante<br>
     */
    REPRESENTATIVE("REP",
                   "2.16.756.5.30.1.127.3.10.6",
                   "Representative",
                   "Representative",
                   "Stellvertretung",
                   "Représentant",
                   "Rappresentante"),
    /**
     * EN: Technical user<br>
     * DE: Technischer Benutzer<br>
     * FR: Utilisateur technique<br>
     * IT: Utente tecnico<br>
     */
    TECHNICAL_USER("TCU",
                   "2.16.756.5.30.1.127.3.10.6",
                   "Technical user",
                   "Technical user",
                   "Technischer Benutzer",
                   "Utilisateur technique",
                   "Utente tecnico");

    /**
     * EN: Code for Assistant<br>
     * DE: Code für Hilfsperson<br>
     * FR: Code de Auxiliaire<br>
     * IT: Code per Persona ausiliara<br>
     */
    public static final String ASSISTANT_CODE = "ASS";

    /**
     * EN: Code for Document administrator<br>
     * DE: Code für Dokumenten-Administrator<br>
     * FR: Code de Administrateur de documents<br>
     * IT: Code per Amministratore dei documenti<br>
     */
    public static final String DOCUMENT_ADMINISTRATOR_CODE = "DADM";

    /**
     * EN: Code for Healthcare professional<br>
     * DE: Code für Gesundheitsfachperson<br>
     * FR: Code de Professionnel de la santé<br>
     * IT: Code per Professionista della salute<br>
     */
    public static final String HEALTHCARE_PROFESSIONAL_CODE = "HCP";

    /**
     * EN: Code for Patient<br>
     * DE: Code für Patient<br>
     * FR: Code de Patient<br>
     * IT: Code per Paziente<br>
     */
    public static final String PATIENT_CODE = "PAT";

    /**
     * EN: Code for Representative<br>
     * DE: Code für Stellvertretung<br>
     * FR: Code de Représentant<br>
     * IT: Code per Rappresentante<br>
     */
    public static final String REPRESENTATIVE_CODE = "REP";

    /**
     * EN: Code for Technical user<br>
     * DE: Code für Technischer Benutzer<br>
     * FR: Code de Utilisateur technique<br>
     * IT: Code per Utente tecnico<br>
     */
    public static final String TECHNICAL_USER_CODE = "TCU";

    /**
     * Identifier of the value set.
     */
    public static final String VALUE_SET_ID = "2.16.756.5.30.1.127.3.10.1.41";

    /**
     * Name of the value set.
     */
    public static final String VALUE_SET_NAME = "SubmissionSet.Author.AuthorRole";

    /**
     * Gets the Enum with a given code.
     *
     * @param code The code value.
     * @return the enum value found or {@code null}.
     */
    public static SubmissionSetAuthorRole getEnum(final String code) {
        for (final SubmissionSetAuthorRole x : values()) {
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
            Enum.valueOf(SubmissionSetAuthorRole.class,
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
        for (final SubmissionSetAuthorRole x : values()) {
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
    SubmissionSetAuthorRole(final String code, final String codeSystem, final String displayName, final String displayNameEn, final String displayNameDe, final String displayNameFr, final String displayNameIt) {
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