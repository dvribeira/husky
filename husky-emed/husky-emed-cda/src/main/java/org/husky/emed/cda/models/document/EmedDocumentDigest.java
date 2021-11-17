package org.husky.emed.cda.models.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.emed.cda.enums.EmedDocumentType;
import org.husky.emed.cda.generated.artdecor.enums.DocumentEntryConfidentialityCode;
import org.husky.common.hl7cdar2.StrucDocText;
import org.husky.emed.cda.models.common.AuthorDigest;
import org.husky.emed.cda.models.common.OrganizationDigest;
import org.husky.emed.cda.models.common.PatientDigest;
import org.husky.emed.cda.models.common.RecipientDigest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Represents the digest of an Emed document. This class is declined in subclasses {@link EmedMtpDocumentDigest}, {@link
 * EmedPreDocumentDigest}, {@link EmedDisDocumentDigest}, {@link EmedPadvDocumentDigest}, {@link EmedPmlDocumentDigest}
 * and {@link EmedPmlcDocumentDigest}.
 * <p>
 * Creating these digests is typically done from a CDA-CH-EMED document with the digesters ({@link }).
 *
 * @author Quentin Ligier
 */
@Data
@EqualsAndHashCode
public abstract class EmedDocumentDigest {

    /**
     * The author(s).
     */
    private final List<@org.checkerframework.checker.nullness.qual.NonNull AuthorDigest> authors = new ArrayList<>();
    /**
     * The intended recipient(s).
     */
    private final List<@org.checkerframework.checker.nullness.qual.NonNull RecipientDigest> recipients = new ArrayList<>();
    /**
     * The document ID.
     */
    @NonNull
    private String id;
    /**
     * The document set ID.
     */
    @NonNull
    private String setId;
    /**
     * The document version.
     */
    private int version;
    /**
     * The document effective time.
     */
    @NonNull
    private OffsetDateTime effectiveTime;
    /**
     * The confidentiality code.
     */
    @NonNull
    private DocumentEntryConfidentialityCode confidentialityCode;
    /**
     * The document main language (some parts may be in another language, e.g. PML documents may contain entries in
     * different languages).
     */
    @NonNull
    private String languageCode;

    /**
     * The data enterer or {@code null} if it's not provided.
     */

    /**
     * The CDA informant(s).
     */
    /**
     * The targeted patient.
     */
    @NonNull
    private PatientDigest patient;
    /**
     * The custodian.
     */
    @NonNull
    private OrganizationDigest custodian;

    /**
     * The legal authenticator or {@code null} if it's not provided.
     */

    /**
     * The authenticator(s).
     */

    /**
     * The employer(s).
     */

    /**
     * The insurance(s).
     */

    /**
     * The insurance card(s).
     */

    /**
     * The patient contact(s).
     */

    /**
     * The order reference(s).
     */

    /**
     * The health service(s).
     */

    /**
     * The document replacement(s).
     */

    /**
     * The CDA authorization(s).
     */

    /**
     * The component of or {@code null} if it's not provided.
     */

    /**
     * The narrative text.
     */
    private StrucDocText narrativeText;

    /**
     * The document remarks or {@code null} if it's not provided.
     */
    @Nullable
    private StrucDocText remarks = null;

    /**
     * The PDF representation of the human representation; it may be empty.
     */
    private byte[] pdfRepresentation = new byte[]{};

    /**
     * Constructor.
     *
     * @param id                  The document ID.
     * @param setId               The document set ID.
     * @param version             The document version.
     * @param effectiveTime       The document effective time.
     * @param confidentialityCode The confidentiality code.
     * @param languageCode        The document main language.
     * @param patient             The targeted patient.
     * @param authors             The author(s).
     * @param custodian           The custodian.
     * @param recipients          The intended recipient(s).
     * @param narrativeText       The narrative text.
     */
    public EmedDocumentDigest(final String id,
                              final String setId,
                              final int version,
                              final OffsetDateTime effectiveTime,
                              final DocumentEntryConfidentialityCode confidentialityCode,
                              final String languageCode,
                              final PatientDigest patient,
                              final List<@org.checkerframework.checker.nullness.qual.NonNull AuthorDigest> authors,
                              final OrganizationDigest custodian,
                              final List<@org.checkerframework.checker.nullness.qual.NonNull RecipientDigest> recipients,
                              final StrucDocText narrativeText) {
        this.id = Objects.requireNonNull(id);
        this.setId = Objects.requireNonNull(setId);
        this.version = version;
        this.effectiveTime = Objects.requireNonNull(effectiveTime);
        this.confidentialityCode = Objects.requireNonNull(confidentialityCode);
        this.languageCode = Objects.requireNonNull(languageCode);
        this.patient = Objects.requireNonNull(patient);
        this.authors.addAll(Objects.requireNonNull(authors));
        this.custodian = Objects.requireNonNull(custodian);
        this.recipients.addAll(Objects.requireNonNull(recipients));
        this.narrativeText = Objects.requireNonNull(narrativeText);
    }

    /**
     * Returns the type of the Emed document.
     */
    public abstract EmedDocumentType getEmedDocumentType();

    @Override
    public String toString() {
        return "EmedDocumentDigest{" +
                "authors=" + authors +
                ", recipients=" + recipients +
                ", id='" + id + '\'' +
                ", setId='" + setId + '\'' +
                ", version=" + version +
                ", effectiveTime=" + effectiveTime +
                ", confidentialityCode=" + confidentialityCode +
                ", languageCode='" + languageCode + '\'' +
                ", patient=" + patient +
                ", custodian=" + custodian +
                ", remarks=" + remarks +
                ", pdfRepresentation=" + Arrays.toString(pdfRepresentation) +
                '}';
    }
}
