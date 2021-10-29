package org.ehealth_connector.emed.cda.models.entry;

import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.ehealth_connector.emed.cda.enums.EmedEntryType;
import org.ehealth_connector.emed.cda.generated.artdecor.enums.ActSubstanceAdminSubstitutionCode;
import org.ehealth_connector.emed.cda.generated.artdecor.enums.DispenseSupplyType;
import org.ehealth_connector.emed.cda.models.common.AuthorDigest;
import org.ehealth_connector.emed.cda.models.common.EmedReference;
import org.ehealth_connector.emed.cda.models.common.QuantityWithUnit;
import org.ehealth_connector.emed.cda.models.treatment.MedicationProduct;

import java.time.Instant;
import java.util.Objects;

/**
 * Represents the digest of an eMed DIS document item entry.
 *
 * @author Quentin Ligier
 */
@Getter
@Setter
public class EmedDisEntryDigest extends EmedEntryDigest {

    /**
     * The dispense type (first fill or refill, complete or partial).
     */
    private DispenseSupplyType dispenseType;

    /**
     * Whether the dispense is an over-the-counter (OTC) dispense (targeting the MTP) or a regular one (targeting a
     * PRE).
     */
    private boolean otc;

    /**
     * The targeted MTP entry reference or {@code null}.
     */
    @Nullable
    private EmedReference mtpEntryRef;

    /**
     * The targeted PRE entry reference or {@code null} if it's an over-the-counter dispense.
     */
    @Nullable
    private EmedReference preEntryRef;

    /**
     * The dispensed medication product.
     */
    private MedicationProduct product;

    /**
     * The dispensed medication quantity.
     */
    private QuantityWithUnit quantity;

    /**
     * The substitution act or {@code null} if it's not given.
     */
    @Nullable
    private ActSubstanceAdminSubstitutionCode substitutionAct;

    /**
     * The patient medication instructions or {@code null} if it isn't provided.
     */
    @Nullable
    private String patientMedicationInstructions;

    /**
     * The fulfilment notes or {@code null} if it isn't provided.
     */
    @Nullable
    private String fulfilmentNotes;

    /**
     * Constructor.
     *
     * @param creationTime                  The instant at which the item entry was created.
     * @param documentId                    The parent document unique ID.
     * @param documentAuthor                The author of the original parent document.
     * @param sectionAuthor                 The author of the original parent section.
     * @param entryId                       The item entry ID.
     * @param medicationTreatmentId         The ID of the medication treatment this item entry belongs to.
     * @param patientId                     The patient ID.
     * @param sequence                      The sequence of addition.
     * @param annotationComment             The annotation comment or {@code null} if it isn't provided.
     * @param dispenseType                  The dispense type (first fill or refill, complete or partial).
     * @param otc                           Whether the dispense is an over-the-counter (OTC) dispense (targeting the
     *                                      MTP) or a regular one (targeting a PRE).
     * @param mtpEntryRef                   The targeted MTP entry reference or {@code null}.
     * @param preEntryRef                   The targeted PRE entry reference or {@code null} if it's an over-the-counter
     *                                      dispense.
     * @param product                       The dispensed medication product.
     * @param quantity                      The dispensed medication quantity.
     * @param substitutionAct               The substitution act or {@code null} if it's not given.
     * @param patientMedicationInstructions The patient medication instructions or {@code null} if it isn't provided.
     * @param fulfilmentNotes               The fulfilment notes or {@code null} if it isn't provided.
     */
    public EmedDisEntryDigest(final Instant creationTime,
                              final String documentId,
                              final AuthorDigest documentAuthor,
                              final AuthorDigest sectionAuthor,
                              final String entryId,
                              final String medicationTreatmentId,
                              final String patientId,
                              final int sequence,
                              @Nullable final String annotationComment,
                              final DispenseSupplyType dispenseType,
                              final boolean otc,
                              @Nullable final EmedReference mtpEntryRef,
                              @Nullable final EmedReference preEntryRef,
                              final MedicationProduct product,
                              final QuantityWithUnit quantity,
                              @Nullable final ActSubstanceAdminSubstitutionCode substitutionAct,
                              @Nullable final String patientMedicationInstructions,
                              @Nullable final String fulfilmentNotes) {
        super(creationTime, documentId, documentAuthor, sectionAuthor, entryId, medicationTreatmentId, patientId,
                sequence, annotationComment);
        this.dispenseType = Objects.requireNonNull(dispenseType);
        this.otc = otc;
        this.mtpEntryRef = mtpEntryRef;
        this.preEntryRef = preEntryRef;
        this.product = Objects.requireNonNull(product);
        this.quantity = Objects.requireNonNull(quantity);
        this.substitutionAct = substitutionAct;
        this.patientMedicationInstructions = patientMedicationInstructions;
        this.fulfilmentNotes = fulfilmentNotes;
    }

    /**
     * Returns the non-null type of the item entry digest.
     */
    public EmedEntryType getEmedEntryType() {
        return EmedEntryType.DIS;
    }
}
