/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.emed.models.entry;

import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.emed.enums.EmedEntryType;
import org.husky.emed.models.common.AuthorDigest;

import java.time.Instant;
import java.util.Objects;

/**
 * Represents the digest of an EMED document item entry. This class is declined in subclasses {@link
 * EmedMtpEntryDigest}, {@link EmedPreEntryDigest}, {@link EmedDisEntryDigest} and {@link EmedPadvEntryDigest}.
 *
 * @author Quentin Ligier
 */
@Getter
@Setter
public abstract class EmedEntryDigest {

    /**
     * The instant at which the item entry was created.
     */
    protected Instant creationTime;

    /**
     * The parent document unique ID.
     */
    protected String documentId;

    /**
     * The author of the original parent section.
     */
    protected AuthorDigest sectionAuthor;

    /**
     * The author of the original parent document.
     */
    protected AuthorDigest documentAuthor;

    /**
     * The item entry ID.
     */
    protected String entryId;

    /**
     * The ID of the medication treatment this item entry belongs to.
     */
    protected String medicationTreatmentId;

    /**
     * The sequence of creation, if multiple documents in the same medication treatment share the same creation time (it
     * usually happens when the creation date is only precise to the day).
     */
    protected int sequence;

    /**
     * The patient ID.
     */
    protected String patientId;

    /**
     * The annotation comment or {@code null} if it isn't provided.
     */
    @Nullable protected String annotationComment;

    /**
     * Constructor.
     *
     * @param creationTime          The instant at which the item entry was created.
     * @param documentId            The parent document unique ID.
     * @param documentAuthor        The author of the original parent document.
     * @param sectionAuthor         The author of the original parent section
     * @param entryId               The item entry ID.
     * @param medicationTreatmentId The ID of the medication treatment this item entry belongs to.
     * @param patientId             The patient ID.
     * @param sequence              The sequence of addition.
     * @param annotationComment     The annotation comment or {@code null} if it isn't provided.
     */
    protected EmedEntryDigest(final Instant creationTime,
                              final String documentId,
                              final AuthorDigest documentAuthor,
                              final AuthorDigest sectionAuthor,
                              final String entryId,
                              final String medicationTreatmentId,
                              final String patientId,
                              final int sequence,
                              @Nullable final String annotationComment) {
        this.creationTime = Objects.requireNonNull(creationTime);
        this.documentId = Objects.requireNonNull(documentId);
        this.documentAuthor = Objects.requireNonNull(documentAuthor);
        this.sectionAuthor = Objects.requireNonNull(sectionAuthor);
        this.entryId = Objects.requireNonNull(entryId);
        this.medicationTreatmentId = Objects.requireNonNull(medicationTreatmentId);
        this.patientId = Objects.requireNonNull(patientId);
        this.sequence = sequence;
        this.annotationComment = annotationComment;
    }

    /**
     * Returns the non-null type of the item entry digest.
     */
    public abstract EmedEntryType getEmedEntryType();

    @Override
    public String toString() {
        return "EmedEntryDigest{" +
                "creationTime=" + creationTime +
                ", documentId='" + documentId + '\'' +
                ", sectionAuthor=" + sectionAuthor +
                ", documentAuthor=" + documentAuthor +
                ", entryId='" + entryId + '\'' +
                ", medicationTreatmentId='" + medicationTreatmentId + '\'' +
                ", sequence=" + sequence +
                ", patientId='" + patientId + '\'' +
                ", annotationComment='" + annotationComment + '\'' +
                '}';
    }
}
