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
package org.projecthusky.fhir.emed.ch.epr.narrative.enums;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.projecthusky.common.enums.LanguageCode;

import java.util.Locale;
import java.util.Objects;

/**
 * Supported languages for the narrative generators.
 *
 * @author Quentin Ligier
 */
public enum NarrativeLanguage {

    ENGLISH("en", "English"),
    FRENCH("fr", "French"),
    GERMAN("de", "German"),
    ITALIAN("it", "Italian");

    /**
     * The language ISO code.
     */
    private final String isoCode;

    /**
     * The language display name in English.
     */
    private final String displayName;

    /**
     * Constructor.
     *
     * @param isoCode     The language ISO code.
     * @param displayName The language display name in English.
     */
    NarrativeLanguage(final String isoCode,
                      final String displayName) {
        this.isoCode = Objects.requireNonNull(isoCode);
        this.displayName = Objects.requireNonNull(displayName);
    }

    public String getIsoCode() {
        return isoCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the related {@link Locale}.
     */
    public Locale getLocale() {
        return new Locale(this.isoCode, "ch");
    }

    /**
     * Returns the related {@link LanguageCode}.
     */
    public LanguageCode getLanguageCode() {
        return switch (this) {
            case ENGLISH -> LanguageCode.ENGLISH;
            case FRENCH -> LanguageCode.FRENCH;
            case GERMAN -> LanguageCode.GERMAN;
            case ITALIAN -> LanguageCode.ITALIAN;
        };
    }

    /**
     * Gets the enum with a given ISO code.
     *
     * @param isoCode The ISO code value.
     * @return The enum value found or {@code null}.
     */
    @Nullable
    public static NarrativeLanguage getEnum(@Nullable final String isoCode) {
        for (final NarrativeLanguage x : values()) {
            if (x.getIsoCode().equals(isoCode)) {
                return x;
            }
        }
        return null;
    }
}

