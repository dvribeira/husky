package org.projecthusky.fhir.emed.ch.epr.resource.pml;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Extension;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.MedicationRequest;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.projecthusky.fhir.emed.ch.common.annotation.ExpectsValidResource;
import org.projecthusky.fhir.emed.ch.common.error.InvalidEmedContentException;
import org.projecthusky.fhir.emed.ch.common.resource.ChCorePatientEpr;
import org.projecthusky.fhir.emed.ch.epr.resource.ChEmedEprMedicationRequest;
import org.projecthusky.fhir.emed.ch.epr.resource.ChEmedEprPractitionerRole;
import org.projecthusky.fhir.emed.ch.epr.resource.extension.ChEmedExtPrescription;
import org.projecthusky.fhir.emed.ch.epr.util.References;

import java.util.UUID;

/**
 * The HAPI custom structure for CH-EMED-EPR MedicationRequest (PML).
 *
 * @author Ronaldo Loureiro
 **/
@ResourceDef(profile = "https://fhir.cara.ch/StructureDefinition/ch-emed-epr-medicationrequest-list")
public class ChEmedEprMedicationRequestPml extends ChEmedEprMedicationRequest {

    /**
     * Author of the original document if different from the author of the medical decision.
     */
    @Nullable
    @Child(name = "authorDocument")
    @Extension(url = "http://fhir.ch/ig/ch-core/StructureDefinition/ch-ext-author", definedLocally = false)
    protected Reference authorDocument;

    /**
     * Reference to the original document.
     */
    @Nullable
    @Child(name = "parentDocument")
    @Extension(url = "http://fhir.ch/ig/ch-emed/StructureDefinition/ch-emed-ext-prescription")
    protected ChEmedExtPrescription parentDocument;

    /**
     * Empty constructor for the parser.
     */
    public ChEmedEprMedicationRequestPml() {
        super();
    }

    /**
     * Constructor that pre-populates fields.
     *
     * @param entryUuid the medication request ID.
     */
    public ChEmedEprMedicationRequestPml(final UUID entryUuid) {
        super(entryUuid);
    }

    /**
     * Resolves the author and her/his organization of the medical decision.
     *
     * @return the author and her/his organization of the medical decision.
     * @throws InvalidEmedContentException if author and her/his organization of the medical decision are missing or
     *                                     isn't of the right type.
     */
    @ExpectsValidResource
    public ChEmedEprPractitionerRole resolvePerformer() throws InvalidEmedContentException {
        if (!this.hasPerformer()) throw new InvalidEmedContentException(
                "The author and her/his organization of the medical decision are missing.");
        final var resource = this.getPerformer().getResource();
        if (resource instanceof ChEmedEprPractitionerRole chEmedEprPractitionerRole) {
            return chEmedEprPractitionerRole;
        }
        throw new InvalidEmedContentException(
                "The reference to the author and her/his organization of the medical decision isn't of the right type.");
    }

    /**
     * Gets the author reference of the original document if different from the author of the medical decision. If it
     * doesn't exist, it's created.
     *
     * @return the author reference of the original document
     */
    public Reference getAuthorDocumentElement() {
        if (this.authorDocument == null) {
            this.authorDocument = new Reference();
        }
        return this.authorDocument;
    }

    /**
     * Gets the last author document resource in the medication statement if available.
     *
     * @return the author document resource or {@code null}.
     * @throws InvalidEmedContentException if the author document resource is invalid.
     */
    @Nullable
    @ExpectsValidResource
    public DomainResource getAuthorDocument() throws InvalidEmedContentException {
        final var resource = getAuthorDocumentElement().getResource();
        if (resource == null) return null;

        if (resource instanceof ChCorePatientEpr || resource instanceof ChEmedEprPractitionerRole) {
            return (DomainResource) resource;
        }
        throw new InvalidEmedContentException("The last author of the original document is invalid");
    }

    /**
     * Sets the author of the original document.
     *
     * @param author the author.
     * @return this.
     */
    public ChEmedEprMedicationRequestPml setAuthorDocument(final IBaseResource author) {
        this.authorDocument = References.createReference((Resource) author);
        return this;
    }

    /**
     * Sets the author and her/his organization of the medical decision.
     *
     * @param practitionerRole the author and her/his organization of the medical decision.
     * @return this.
     */
    public ChEmedEprMedicationRequestPml setPerformer(final ChEmedEprPractitionerRole practitionerRole) {
        this.setPerformer(References.createReference(practitionerRole));
        return this;
    }

    /**
     * Return whether author of the original document.
     *
     * @return {@code true} if the author of the original document exists, {@code false} otherwise.
     */
    public boolean hasAuthorDocument() {
        return this.authorDocument != null && !this.authorDocument.isEmpty();
    }

    /**
     * Gets the parent document element. If it doesn't exist, it is created.
     *
     * @return the parent document element.
     */
    public ChEmedExtPrescription getParentDocumentElement() {
        if (this.parentDocument == null) {
            this.parentDocument = new ChEmedExtPrescription();
        }
        return this.parentDocument;
    }

    /**
     * Sets the parent document reference.
     *
     * @param parentDocument the parent document reference.
     * @return this.
     */
    public ChEmedEprMedicationRequestPml setParentDocumentElement(final ChEmedExtPrescription parentDocument) {
        this.parentDocument = parentDocument;
        return this;
    }

    /**
     * Returns whether the parent document reference exists.
     *
     * @return {@code true} if the parent document reference exists, {@code false} otherwise.
     */
    public boolean hasParentDocument() {
        return this.parentDocument != null && !this.parentDocument.isEmpty();
    }

    @Override
    public ChEmedEprMedicationRequestPml copy() {
        final var copy = new ChEmedEprMedicationRequestPml();
        this.copyValues(copy);
        return copy;
    }

    @Override
    public void copyValues(final MedicationRequest dst) {
        super.copyValues(dst);
        if (dst instanceof final ChEmedEprMedicationRequestPml als) {
            als.authorDocument = authorDocument == null ? null : authorDocument.copy();
            als.parentDocument = parentDocument == null ? null : parentDocument.copy();
        }
    }
}