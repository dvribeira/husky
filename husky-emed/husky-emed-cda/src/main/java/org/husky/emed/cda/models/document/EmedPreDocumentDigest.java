package org.husky.emed.cda.models.document;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.husky.emed.cda.enums.EmedDocumentType;
import org.husky.emed.cda.generated.artdecor.enums.DocumentEntryConfidentialityCode;
import org.husky.common.hl7cdar2.StrucDocText;
import org.husky.emed.cda.models.common.AuthorDigest;
import org.husky.emed.cda.models.common.OrganizationDigest;
import org.husky.emed.cda.models.common.PatientDigest;
import org.husky.emed.cda.models.common.RecipientDigest;
import org.husky.emed.cda.models.entry.EmedPreEntryDigest;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the digest of a PRE Emed document.
 *
 * @author Quentin Ligier
 */
public class EmedPreDocumentDigest extends EmedDocumentDigest {

    /**
     * The prescription validity start time (inclusive).
     */
    final Instant prescriptionValidityStart;

    /**
     * The prescription validity stop time (inclusive).
     */
    final Instant prescriptionValidityStop;

    /**
     * The PRE entries contained in the document.
     */
    private final List<@NonNull EmedPreEntryDigest> preEntryDigests = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param id                        The document ID.
     * @param setId                     The document set ID.
     * @param version                   The document version.
     * @param effectiveTime             The document effective time.
     * @param confidentialityCode       The confidentiality code.
     * @param languageCode              The document main language.
     * @param patient                   The targeted patient.
     * @param authors                   The author(s).
     * @param custodian                 The custodian.
     * @param recipients                The intended recipient(s).
     * @param narrativeText             The narrative text.
     * @param preEntryDigests           The PRE entry digests.
     * @param prescriptionValidityStart The prescription validity start time (inclusive).
     * @param prescriptionValidityStop  The prescription validity stop time (inclusive).
     */
    public EmedPreDocumentDigest(final String id,
                                 final String setId,
                                 final int version,
                                 final OffsetDateTime effectiveTime,
                                 final DocumentEntryConfidentialityCode confidentialityCode,
                                 final String languageCode,
                                 final PatientDigest patient,
                                 final List<@NonNull AuthorDigest> authors,
                                 final OrganizationDigest custodian,
                                 final List<@NonNull RecipientDigest> recipients,
                                 final StrucDocText narrativeText,
                                 final List<@NonNull EmedPreEntryDigest> preEntryDigests,
                                 final Instant prescriptionValidityStart,
                                 final Instant prescriptionValidityStop) {
        super(id, setId, version, effectiveTime, confidentialityCode, languageCode, patient, authors, custodian,
                recipients, narrativeText);
        this.preEntryDigests.addAll(Objects.requireNonNull(preEntryDigests));
        this.prescriptionValidityStart = Objects.requireNonNull(prescriptionValidityStart);
        this.prescriptionValidityStop = Objects.requireNonNull(prescriptionValidityStop);
    }

    /**
     * Returns the PRE entries contained in the document.
     */
    public List<@NonNull EmedPreEntryDigest> getPreEntryDigests() {
        return this.preEntryDigests;
    }

    /**
     * Returns the prescription validity start time (inclusive).
     */
    public Instant getPrescriptionValidityStart() {
        return this.prescriptionValidityStart;
    }

    /**
     * Returns the prescription validity stop time (inclusive).
     */
    public Instant getPrescriptionValidityStop() {
        return this.prescriptionValidityStop;
    }

    /**
     * Returns the type of the Emed document.
     */
    public EmedDocumentType getEmedDocumentType() {
        return EmedDocumentType.PRE;
    }

    public String toString() {
        return "EmedPreDocumentDigest(super=" + super.toString() + ", preEntryDigests=" + this.getPreEntryDigests() +
                ", prescriptionValidityStart=" + this.getPrescriptionValidityStart() + ", prescriptionValidityStop=" +
                this.getPrescriptionValidityStop() + ")";
    }
}