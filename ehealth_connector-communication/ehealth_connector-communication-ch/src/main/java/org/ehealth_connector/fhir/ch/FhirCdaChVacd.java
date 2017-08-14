/*
 *
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
 * For exact developer information, please refer to the commit history of the forge.
 *
 * This code is made available under the terms of the Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 4.0 License.
 *
 * This line is intended for UTF-8 encoding checks, do not modify/delete: äöüéè
 *
 */

package org.ehealth_connector.fhir.ch;

import java.util.ArrayList;
import java.util.List;

import org.ehealth_connector.cda.Consumable;
import org.ehealth_connector.cda.SectionAnnotationCommentEntry;
import org.ehealth_connector.cda.ch.ActiveProblemConcern;
import org.ehealth_connector.cda.ch.AllergyConcern;
import org.ehealth_connector.cda.ch.CodedResults;
import org.ehealth_connector.cda.ch.PastProblemConcern;
import org.ehealth_connector.cda.ch.PregnancyHistory;
import org.ehealth_connector.cda.ch.vacd.CdaChVacd;
import org.ehealth_connector.cda.ch.vacd.CriterionEntry;
import org.ehealth_connector.cda.ch.vacd.Immunization;
import org.ehealth_connector.cda.ch.vacd.ImmunizationRecommendation;
import org.ehealth_connector.cda.ch.vacd.LaboratoryObservation;
import org.ehealth_connector.cda.ch.vacd.MedicationTargetEntry;
import org.ehealth_connector.cda.enums.MedicationsSpecialConditions;
import org.ehealth_connector.common.Author;
import org.ehealth_connector.common.Code;
import org.ehealth_connector.common.Identificator;
import org.ehealth_connector.common.Value;
import org.ehealth_connector.common.enums.CodeSystems;
import org.ehealth_connector.common.enums.ObservationInterpretation;
import org.ehealth_connector.common.enums.Ucum;
import org.ehealth_connector.common.utils.DateUtil;
import org.ehealth_connector.common.utils.Util;
import org.ehealth_connector.fhir.FhirCommon;
import org.hl7.fhir.dstu3.model.Basic;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Condition;
import org.hl7.fhir.dstu3.model.DocumentManifest;
import org.hl7.fhir.dstu3.model.DocumentManifest.DocumentManifestContentComponent;
import org.hl7.fhir.dstu3.model.Dosage;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.ListResource;
import org.hl7.fhir.dstu3.model.ListResource.ListEntryComponent;
import org.hl7.fhir.dstu3.model.Medication;
import org.hl7.fhir.dstu3.model.MedicationStatement;
import org.hl7.fhir.dstu3.model.MedicationStatement.MedicationStatementTaken;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Observation.ObservationRelatedComponent;
import org.hl7.fhir.dstu3.model.Organization;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Person;
import org.hl7.fhir.dstu3.model.Quantity;
import org.hl7.fhir.dstu3.model.Ratio;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.dstu3.model.TimeType;
import org.hl7.fhir.exceptions.FHIRException;
import org.openhealthtools.mdht.uml.hl7.datatypes.BL;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.ED;
import org.openhealthtools.mdht.uml.hl7.datatypes.INT;
import org.openhealthtools.mdht.uml.hl7.datatypes.ST;
import org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.FhirVersionEnum;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Extension;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.util.ElementUtil;

/**
 * FhirCdaChVacd supports the creation of a CDA-CH-CH document from HL7 FHIR
 * resources. The content of these resources is not currently documented.
 * These resources may be created by the class
 * org.ehealth_connector.demo.cda.VACDResources. This is currently the one any
 * only way to create valid FHIR resources for CDA-CH-VACD. You may edit
 * these FHIR resources in a text editor in order to change the payload of the
 * resulting CdaChVacd object on your own risk.
 *
 * @see "https://www.hl7.org/fhir/"
 */
/**
 * <div class="en"></div><div class="de"></div><div class="fr"></div>
 */
public class FhirCdaChVacd extends AbstractFhirCdaCh {

	/**
	 * <div class="en">Type of the FHIR resource to define whether the resulting
	 * CDA document contains full or masked patient demographics</div>
	 * <div class="de"></div><div class="fr"></div>
	 */
	public static enum DocTypeCode {
		/**
		 * <div class="en">the resulting CDA document contains full patient
		 * demographics</div><div class="de"></div><div class="fr"></div>
		 */
		PATIENT,
		/**
		 * <div class="en">the resulting CDA document contains masked patient
		 * demographics</div><div class="de"></div> <div class="fr"></div>
		 */
		PSEUDONYMIZED
	}

	/**
	 * <div class="en">MyMedicationStatement extends from the FHIR HAPI
	 * MedicationStatement Resource and provides extension attributes in order
	 * to fulfill the needs for HL7 CDA-CH-VACD creation
	 *
	 * @formatter:off
	 * @see "http://jamesagnew.github.io/hapi-fhir/index.html"
	 * @formatter:on</div><div class="de"></div><div class="fr"></div>
	 */
	/**
	 * <div class="en"></div><div class="de"></div><div class="fr"></div>
	 */
	@ResourceDef(name = "MedicationStatement")
	public static class MyMedicationStatement extends MedicationStatement {

		private static final long serialVersionUID = -2062272744006946538L;

		/**
		 * <div class="en">Person who documented the medication statement</div>
		 * <div class="de"></div><div class="fr"></div>
		 */
		@Child(name = "author")
		@Extension(url = FhirCommon.urnUseAsAuthor, definedLocally = false, isModifier = false)
		@Description(shortDefinition = "Person who documented the medication statement")
		private Reference author;

		/**
		 * <div class="en">Code of the medication administration</div>
		 * <div class="de"></div><div class="fr"></div>
		 */
		@Child(name = "code")
		@Extension(url = FhirCommon.urnUseAsCode, definedLocally = false, isModifier = false)
		@Description(shortDefinition = "Code of the medication administration")
		private Coding code;

		/**
		 * <div class="en">Remark for this medication statement</div>
		 * <div class="de"></div><div class="fr"></div>
		 */
		@Child(name = "comment")
		@Extension(url = FhirCommon.urnUseAsComment, definedLocally = false, isModifier = false)
		@Description(shortDefinition = "Remark for this medication statement")
		private StringType comment;

		/**
		 * <div class="en">List of reasons for this medication statement</div>
		 * <div class="de"></div><div class="fr"></div>
		 */
		@Child(name = "externalDocument")
		@Extension(url = FhirCommon.urnUseAsExternalDocument, definedLocally = false, isModifier = false)
		@Description(shortDefinition = "reference to the guidline for theimmunization recommendation")
		private Reference externalDocument;

		/**
		 * <div class="en">LotNumber for this medication statement</div>
		 * <div class="de"></div><div class="fr"></div>
		 */
		@Child(name = "lot")
		@Extension(url = FhirCommon.urnUseAsLotNumbertext, definedLocally = false, isModifier = false)
		@Description(shortDefinition = "LotNumber for this medication statement")
		private StringType lot;

		/**
		 * <div class="en">Person who performed the medication
		 * administration</div> <div class="de"></div><div class="fr"></div>
		 */
		@Child(name = "performer")
		@Extension(url = FhirCommon.urnUseAsPerformer, definedLocally = false, isModifier = false)
		@Description(shortDefinition = "Person who performed the medicationadministration")
		private Reference performer;

		/**
		 * <div class="en">List of reasons for this medication statement</div>
		 * <div class="de"></div><div class="fr"></div>
		 */
		@Child(name = "reasons")
		@Extension(url = FhirCommon.urnUseAsReason, definedLocally = false, isModifier = false)
		@Description(shortDefinition = "List of reasons for this medicationstatement")
		private Reference reasons;

		/**
		 * <div class="en">Empty constructor (default)</div>
		 * <div class="de"></div> <div class="fr"></div>
		 */
		public MyMedicationStatement() {
			super();
			setTaken(MedicationStatementTaken.UNK);
		}

		/**
		 * @return <div class="en">author of this MedicationStatement</div>
		 *         <div class="de">Autor dieses MedicationStatements</div>
		 *         <div class="fr">auteur de ce MedicationStatement</div>
		 */
		public Reference getAuthor() {
			if (this.author == null) {
				this.author = new Reference();
			}
			return author;
		}

		/**
		 * @return <div class="en">code of this MedicationStatement</div>
		 *         <div class="de">Code für dieses MedicationStatement</div>
		 *         <div class="fr">code de ce MedicationStatement</div>
		 */
		public Coding getCode() {
			return code;
		}

		/**
		 * @return <div class="en">comment for this medication</div>
		 *         <div class="de">Kommentar zu diesem Medikament</div>
		 *         <div class="fr">commentaire tu médicament</div>
		 */
		public StringType getComment() {
			if (this.comment == null) {
				this.comment = new StringType();
			}
			return comment;
		}

		/**
		 * Gets the external document.
		 *
		 * @return the external document
		 */
		public Reference getExternalDocument() {
			if (this.externalDocument == null) {
				this.externalDocument = new Reference();
			}
			return externalDocument;
		}

		/**
		 * @return <div class="en">lot number of this medication</div>
		 *         <div class="de">Lotnummer des Medikaments</div>
		 *         <div class="fr">numero lot du médicament</div>
		 */
		public StringType getLot() {
			if (this.lot == null) {
				this.lot = new StringType();
			}
			return lot;
		}

		/**
		 * @return <div class="en">performer of the medication
		 *         administration</div> <div class="de">Person, welche das
		 *         Medikament verabreicht hat</div><div class="fr">personne qui
		 *         a effectué l'administration du médicament</div>
		 */
		public Reference getPerformer() {
			if (this.performer == null) {
				this.performer = new Reference();
			}
			return performer;
		}

		/**
		 * @return <div class="en">reason for this medication</div>
		 *         <div class="de">Begründung für dieses Medikament</div>
		 *         <div class="fr">Justification de ce médicament</div>
		 */
		public Reference getReasons() {
			if (this.reasons == null) {
				this.reasons = new Reference();
			}
			return reasons;
		}

		/**
		 * It is important to override the isEmpty() method, adding a check for
		 * any newly added fields.
		 *
		 * @see "ca.uhn.fhir.model.dstu2.resource.MedicationStatement#isEmpty()"
		 */
		@Override
		public boolean isEmpty() {
			return super.isEmpty() && ElementUtil.isEmpty(code, performer, author, comment);
		}

		/**
		 * @param author
		 *            <div class="en">author of this MedicationStatement</div>
		 *            <div class="de">Autor dieses MedicationStatements</div>
		 *            <div class="fr">auteur de ce MedicationStatement</div>
		 */
		public void setAuthor(Reference author) {
			this.author = author;
		}

		/**
		 * @param code
		 *            <div class="en">code of this MedicationStatement</div>
		 *            <div class="de">Code für dieses MedicationStatement</div>
		 *            <div class="fr">code de ce MedicationStatement</div>
		 */
		public void setCode(Coding code) {
			this.code = code;
		}

		/**
		 * @param comment
		 *            <div class="en">comment for this medication</div>
		 *            <div class="de">Kommentar zu diesem Medikament</div>
		 *            <div class="fr">commentaire tu médicament</div>
		 */
		public void setComment(StringType comment) {
			this.comment = comment;
		}

		/**
		 * Sets the external document.
		 *
		 * @param externalDocument
		 *            the new external document
		 */
		public void setExternalDocument(Reference externalDocument) {
			this.externalDocument = externalDocument;
		}

		/**
		 * @param lot
		 *            <div class="en">lot number of this medication</div>
		 *            <div class="de">Lotnummer des Medikaments</div>
		 *            <div class="fr">numero lot du médicament</div>
		 */
		public void setLot(StringType lot) {
			this.lot = lot;
		}

		/**
		 * @param performer
		 *            <div class="en">performer of the medication
		 *            administration</div> <div class="de">Person, welche das
		 *            Medikament verabreicht hat</div><div class="fr">personne
		 *            qui a effectué l'administration du médicament</div>
		 */
		public void setPerformer(Reference performer) {
			this.performer = performer;
		}

		/**
		 * @param reasons
		 *            <div class="en">reason for this medication</div>
		 *            <div class="de">Begründung für dieses Medikament</div>
		 *            <div class="fr">Justification de ce médicament</div>
		 */
		public void setReasons(Reference reasons) {
			this.reasons = reasons;
		}

	}

	/**
	 * The class VacdDocument is a derived FHIR resource containing all
	 * information of an immunization document corresponding to the CDA-CH-VACD
	 * specification
	 */
	@ResourceDef(name = "DocumentManifest")
	public static class VacdDocument extends DocumentManifest {

		private static final long serialVersionUID = -1316775746486313864L;

		/** The active problem concern entries. */
		@Child(name = "activeProblemConcernEntries", max = Child.MAX_UNLIMITED)
		@Extension(url = FhirCommon.urnUseAsActiveProblemConcernEntries, definedLocally = false, isModifier = true)
		@Description(shortDefinition = "activeProblemConcernEntries")
		private List<Reference> activeProblemConcernEntries; // Condition

		/** The allergy problem concern entries. */
		@Child(name = "allergyProblemConcernEntries", max = Child.MAX_UNLIMITED)
		@Extension(url = FhirCommon.urnUseAsAllergyProblemConcernEntries, definedLocally = false, isModifier = true)
		@Description(shortDefinition = "allergyProblemConcernEntries")
		private List<Reference> allergyProblemConcernEntries; // Condition

		/** The coded result observations. */
		@Child(name = "codedResultObservations", max = Child.MAX_UNLIMITED)
		@Extension(url = FhirCommon.urnUseAsCodedResultObservations, definedLocally = false, isModifier = true)
		@Description(shortDefinition = "codedResultObservations")
		private List<Reference> codedResultObservations; // codedResults

		/** The comment. */
		@Child(name = "comment")
		@Extension(url = FhirCommon.urnUseAsComment, definedLocally = false, isModifier = false)
		@Description(shortDefinition = "comment")
		private Reference comment;

		/** The confidentiality. */
		@Child(name = "confidentiality")
		@Extension(url = FhirCommon.urnUseAsConfidentiality, definedLocally = false, isModifier = false)
		@Description(shortDefinition = "confidentiality")
		private Reference confidentiality;

		/** The custodian. */
		@Child(name = "custodian")
		@Extension(url = FhirCommon.urnUseAsCustodian, definedLocally = false, isModifier = false)
		@Description(shortDefinition = "custodian")
		private Reference custodian;

		/** The doc author. */
		@Child(name = "docAuthor")
		@Extension(url = FhirCommon.urnUseAsAuthor, definedLocally = false, isModifier = false)
		@Description(shortDefinition = "author")
		private Reference docAuthor;

		/** The doc language. */
		@Child(name = "docLanguage")
		@Extension(url = FhirCommon.urnUseAsLanguage, definedLocally = false, isModifier = false)
		@Description(shortDefinition = "docLanguage")
		private Reference docLanguage;

		/** The doc type. */
		@Child(name = "docType")
		@Extension(url = FhirCommon.urnUseAsDocType, definedLocally = false, isModifier = false)
		@Description(shortDefinition = "docType")
		private Reference docType;

		/** The immunization recommendations. */
		@Child(name = "immunizationRecommendations", max = Child.MAX_UNLIMITED)
		@Extension(url = FhirCommon.urnUseAsImmunizationRecommendation, definedLocally = false, isModifier = true)
		@Description(shortDefinition = "immunizationRecommendations")
		private List<Reference> immunizationRecommendations;

		/** The immunizations. */
		@Child(name = "immunizations", max = Child.MAX_UNLIMITED)
		@Extension(url = FhirCommon.urnUseAsImmunization, definedLocally = false, isModifier = true)
		@Description(shortDefinition = "immunizations")
		private List<Reference> immunizations;

		/** The laboratory observations. */
		@Child(name = "laboratoryObservations", max = Child.MAX_UNLIMITED)
		@Extension(url = FhirCommon.urnUseAsLaboratoryObservations, definedLocally = false, isModifier = true)
		@Description(shortDefinition = "laboratoryObservations")
		private List<Reference> laboratoryObservations; // Observations

		/** The legal authenticator. */
		@Child(name = "legalAuthenticator")
		@Extension(url = FhirCommon.urnUseAsLegalAuthenticator, definedLocally = false, isModifier = false)
		@Description(shortDefinition = "legalAuthenticator")
		private Reference legalAuthenticator;

		/** The past problem concern entries. */
		@Child(name = "pastProblemConcernEntries", max = Child.MAX_UNLIMITED)
		@Extension(url = FhirCommon.urnUseAsPastProblemConcernEntries, definedLocally = false, isModifier = true)
		@Description(shortDefinition = "pastProblemConcernEntries")
		private List<Reference> pastProblemConcernEntries; // Condition

		/** The patient. */
		@Child(name = "patient")
		@Extension(url = FhirCommon.urnUseAsPatient, definedLocally = false, isModifier = false)
		@Description(shortDefinition = "patient")
		private Reference patient;

		/** The pregnancy observation. */
		@Child(name = "pregnancyObservation,", max = Child.MAX_UNLIMITED)
		@Extension(url = FhirCommon.urnUseAsPregnancyObservation, definedLocally = false, isModifier = true)
		@Description(shortDefinition = "pregnancyObservation")
		private Reference pregnancyObservation; // Observation

		/**
		 * Adds the active problem concern entry.
		 *
		 * @param activeProbemlConcernEntry
		 *            the active probeml concern entry
		 */
		public void addActiveProblemConcernEntry(Condition activeProbemlConcernEntry) {
			if (activeProblemConcernEntries == null) {
				activeProblemConcernEntries = new ArrayList<Reference>();
			}
			final Reference resourceRef = new Reference();
			resourceRef.setResource(activeProbemlConcernEntry);
			this.activeProblemConcernEntries.add(resourceRef);
		}

		/**
		 * Adds the allergy problem concern entry.
		 *
		 * @param allergyProblemConcernEntry
		 *            the allergy problem concern entry
		 */
		public void addAllergyProblemConcernEntry(Condition allergyProblemConcernEntry) {
			if (allergyProblemConcernEntries == null) {
				allergyProblemConcernEntries = new ArrayList<Reference>();
			}
			final Reference resourceRef = new Reference();
			resourceRef.setResource(allergyProblemConcernEntry);
			this.allergyProblemConcernEntries.add(resourceRef);
		}

		/**
		 * Adds the coded result observation.
		 *
		 * @param laboratoryObservation
		 *            the laboratory observation
		 */
		public void addCodedResultObservation(Observation laboratoryObservation) {
			if (codedResultObservations == null) {
				codedResultObservations = new ArrayList<Reference>();
			}
			final Reference resourceRef = new Reference();
			resourceRef.setResource(laboratoryObservation);
			this.codedResultObservations.add(resourceRef);
		}

		/**
		 * Adds the immunization.
		 *
		 * @param immunization
		 *            the immunization
		 */
		public void addImmunization(MyMedicationStatement immunization) {
			if (immunizations == null) {
				immunizations = new ArrayList<Reference>();
			}
			final Reference resourceRef = new Reference();
			resourceRef.setResource(immunization);
			this.immunizations.add(resourceRef);
		}

		/**
		 * Adds the immunization recommendation.
		 *
		 * @param immunizationRecommendation
		 *            the immunization recommendation
		 */
		public void addImmunizationRecommendation(MedicationStatement immunizationRecommendation) {
			if (immunizationRecommendations == null) {
				immunizationRecommendations = new ArrayList<Reference>();
			}
			final Reference resourceRef = new Reference();
			resourceRef.setResource(immunizationRecommendation);
			this.immunizationRecommendations.add(resourceRef);
		}

		/**
		 * Adds the laboratory observation.
		 *
		 * @param laboratoryObservation
		 *            the laboratory observation
		 */
		public void addLaboratoryObservation(Observation laboratoryObservation) {
			if (laboratoryObservations == null) {
				laboratoryObservations = new ArrayList<Reference>();
			}
			final Reference resourceRef = new Reference();
			resourceRef.setResource(laboratoryObservation);
			this.laboratoryObservations.add(resourceRef);
		}

		/**
		 * Adds the past problem concern entry.
		 *
		 * @param pastProblemConcernEntry
		 *            the past problem concern entry
		 */
		public void addPastProblemConcernEntry(Condition pastProblemConcernEntry) {
			if (pastProblemConcernEntries == null) {
				pastProblemConcernEntries = new ArrayList<Reference>();
			}
			final Reference resourceRef = new Reference();
			resourceRef.setResource(pastProblemConcernEntry);
			this.pastProblemConcernEntries.add(resourceRef);
		}

		/**
		 * Gets the active problem concern entries.
		 *
		 * @return the active problem concern entries
		 */
		public List<Condition> getActiveProblemConcernEntries() {
			final List<Condition> list = new ArrayList<Condition>();
			if (this.activeProblemConcernEntries != null) {
				for (final Reference resource : this.activeProblemConcernEntries) {
					list.add((Condition) resource.getResource());
				}
			}
			return list;
		}

		/**
		 * Gets the allergy problem concern entries.
		 *
		 * @return the allergy problem concern entries
		 */
		public List<Condition> getAllergyProblemConcernEntries() {
			final List<Condition> list = new ArrayList<Condition>();
			if (this.allergyProblemConcernEntries != null) {
				for (final Reference resource : allergyProblemConcernEntries) {
					list.add((Condition) resource.getResource());
				}
			}
			return list;
		}

		/**
		 * Gets the coded result observations.
		 *
		 * @return the coded result observations
		 */
		public List<Observation> getCodedResultObservations() {
			final List<Observation> list = new ArrayList<Observation>();
			if (this.codedResultObservations != null) {
				for (final Reference resource : codedResultObservations) {
					list.add((Observation) resource.getResource());
				}
			}
			return list;
		}

		/**
		 * Gets the comment.
		 *
		 * @return the comment
		 */
		public Observation getComment() {
			if (this.comment != null) {
				return (Observation) this.comment.getResource();
			}
			return null;
		}

		/**
		 * Gets the confidentiality code.
		 *
		 * @return the confidentiality code
		 */
		public Basic getConfidentiality() {
			if (this.confidentiality != null) {
				return (Basic) this.confidentiality.getResource();
			}
			return null;
		}

		/**
		 * Gets the custodian.
		 *
		 * @return the custodian
		 */
		public Organization getCustodian() {
			if (this.custodian != null) {
				return (Organization) this.custodian.getResource();
			}
			return null;
		}

		/**
		 * Gets the doc author.
		 *
		 * @return the doc author
		 */
		public Person getDocAuthor() {
			if (this.docAuthor != null) {
				return (Person) this.docAuthor.getResource();
			}
			return null;
		}

		/**
		 * Gets the doc language.
		 *
		 * @return the doc language
		 */
		public Basic getDocLanguage() {
			if (this.docLanguage != null) {
				return (Basic) this.docLanguage.getResource();
			}
			return null;
		}

		/**
		 * Gets the doc type.
		 *
		 * @return the doc type
		 */
		public Basic getDocType() {
			if (this.docType != null) {
				return (Basic) this.docType.getResource();
			}
			return null;
		}

		/**
		 * Gets the immunization recommendations.
		 *
		 * @return the immunization recommendations
		 */
		public List<MedicationStatement> getImmunizationRecommendations() {
			final List<MedicationStatement> list = new ArrayList<MedicationStatement>();
			if (this.immunizationRecommendations != null) {
				for (final Reference resource : immunizationRecommendations) {
					list.add((MedicationStatement) resource.getResource());
				}
			}
			return list;
		}

		/**
		 * Gets the immunizations.
		 *
		 * @return the immunizations
		 */
		public List<MedicationStatement> getImmunizations() {
			final List<MedicationStatement> list = new ArrayList<MedicationStatement>();
			if (this.immunizations != null) {
				for (final Reference resource : immunizations) {
					list.add((MedicationStatement) resource.getResource());
				}
			}
			return list;
		}

		/**
		 * Gets the laboratory observations.
		 *
		 * @return the laboratory observations
		 */
		public List<Observation> getLaboratoryObservations() {
			final List<Observation> list = new ArrayList<Observation>();
			if (this.laboratoryObservations != null) {
				for (final Reference resource : laboratoryObservations) {
					list.add((Observation) resource.getResource());
				}
			}
			return list;
		}

		/**
		 * Gets the legal authenticator.
		 *
		 * @return the legal authenticator
		 */
		public Person getLegalAuthenticator() {
			if (this.legalAuthenticator != null) {
				return (Person) this.legalAuthenticator.getResource();
			}
			return null;
		}

		/**
		 * Gets the past problem concern entries.
		 *
		 * @return the past problem concern entries
		 */
		public List<Condition> getPastProblemConcernEntries() {
			final List<Condition> list = new ArrayList<Condition>();
			if (this.pastProblemConcernEntries != null) {
				for (final Reference resource : pastProblemConcernEntries) {
					list.add((Condition) resource.getResource());
				}
			}
			return list;
		}

		/**
		 * Gets the patient.
		 *
		 * @return the patient
		 */
		public Patient getPatient() {
			if (this.patient != null) {
				return (Patient) this.patient.getResource();
			}
			return null;
		}

		/**
		 * Gets the pregnancy obeservation.
		 *
		 * @return the pregnancy obeservation
		 */
		public Observation getPregnancyObeservation() {
			if (this.pregnancyObservation != null) {
				return (Observation) this.pregnancyObservation.getResource();
			}
			return null;
		}

		/**
		 * Sets the comment.
		 *
		 * @param comment
		 *            the new comment
		 */
		public void setComment(Observation comment) {
			final Reference resourceRef = new Reference();
			resourceRef.setResource(comment);
			this.comment = resourceRef;
		}

		/**
		 * Sets the confidentiality code.
		 *
		 * @param confidentiality
		 *            the new confidentiality code
		 */
		public void setConfidentiality(Basic confidentiality) {
			final Reference resourceRef = new Reference();
			resourceRef.setResource(confidentiality);
			this.confidentiality = resourceRef;
		}

		/**
		 * Sets the custodian.
		 *
		 * @param custodian
		 *            the new custodian
		 */
		public void setCustodian(Organization custodian) {
			final Reference resourceRef = new Reference();
			resourceRef.setResource(custodian);
			this.custodian = resourceRef;
		}

		/**
		 * Sets the doc author.
		 *
		 * @param author
		 *            the new doc author
		 */
		public void setDocAuthor(Person author) {
			final Reference resourceRef = new Reference();
			resourceRef.setResource(author);
			this.docAuthor = resourceRef;
		}

		/**
		 * Sets the doc language.
		 *
		 * @param language
		 *            the new doc language
		 */
		public void setDocLanguage(Basic language) {
			final Reference resourceRef = new Reference();
			resourceRef.setResource(language);
			this.docLanguage = resourceRef;
		}

		/**
		 * Sets the doc type.
		 *
		 * @param typePseudonymized
		 *            the new doc type
		 */
		public void setDocType(Basic typePseudonymized) {
			final Reference resourceRef = new Reference();
			resourceRef.setResource(typePseudonymized);
			this.docType = resourceRef;
		}

		/**
		 * Sets the legal authenticator.
		 *
		 * @param legalAuthenticator
		 *            the new legal authenticator
		 */
		public void setLegalAuthenticator(Person legalAuthenticator) {
			final Reference resourceRef = new Reference();
			resourceRef.setResource(legalAuthenticator);
			this.legalAuthenticator = resourceRef;
		}

		/**
		 * Sets the patient.
		 *
		 * @param patient
		 *            the new patient
		 */
		public void setPatient(Patient patient) {
			final Reference resourceRef = new Reference();
			resourceRef.setResource(patient);
			this.patient = resourceRef;
		}

		/**
		 * Sets the pregnancy observation.
		 *
		 * @param pregnancyObservation
		 *            the new pregnancy observation
		 */
		public void setPregnancyObservation(Observation pregnancyObservation) {
			final Reference resourceRef = new Reference();
			resourceRef.setResource(pregnancyObservation);
			this.pregnancyObservation = resourceRef;
		}

	};

	/**
	 * <div class="en">uniform resource name (urn) of this OID</div>
	 * <div class="de"></div><div class="fr"></div>
	 */
	public static final String OID_CONFIDENTIALITY_CODE = FhirCommon
			.addUrnOid(CodeSystems.ConfidentialityCode.getCodeSystemId());

	/**
	 * <div class="en">uniform resource name (urn) of this OID</div>
	 * <div class="de"></div><div class="fr"></div>
	 */
	public static final String OID_VACD = FhirCommon.addUrnOid(CdaChVacd.OID_MAIN);

	private final FhirContext fhirCtx = new FhirContext(FhirVersionEnum.DSTU3);

	/**
	 * <div class="en">Empty constructor (default)</div><div class="de"></div>
	 * <div class="fr"></div>
	 */
	public FhirCdaChVacd() {
	}

	/**
	 * <div class="en">Creates an eHC CdaChVacd instance from a valid FHIR
	 * resource</div> <div class="de"></div> <div class="fr"></div>
	 *
	 * @param docManifest
	 *            <div class="en">valid CdaChVacd FHIR resource</div>
	 *            <div class="de"></div> <div class="fr"></div>
	 * @param xsl
	 *            <div class="en">desired stylesheet for the CDA document</div>
	 *            <div class="de"></div> <div class="fr"></div>
	 * @param css
	 *            <div class="en">desired CSS for the CDA document</div>
	 *            <div class="de"></div> <div class="fr"></div>
	 * @return <div class="en">eHC CdaChVacd instance containing payload of the
	 *         given FHIR resource</div> <div class="de"></div>
	 *         <div class="fr"></div>
	 */
	public CdaChVacd createCdaChVacdFromFHIR(DocumentManifest docManifest, String xsl, String css) {
		// Header
		final CdaChVacd doc = new CdaChVacd(getDocLanguage(docManifest), xsl, css);
		final DocTypeCode docType = getVacdDocType(docManifest);
		doc.setId(getDocumentId(docManifest));
		doc.setSetId(getDocumentId(docManifest));
		doc.setTimestamp(getDocumentDate(docManifest));
		doc.setConfidentialityCode(getConfidentialityCode(docManifest));
		doc.setPatient(FhirCommon.getPatient(docManifest));
		if (docType == DocTypeCode.PSEUDONYMIZED)
			doc.pseudonymization();

		for (final Author author : getAuthors(docManifest)) {
			author.setTime(DateUtil.date("15.12.2014"));
			doc.addAuthor(author);
		}
		doc.setCustodian(getCustodian(docManifest));
		doc.setLegalAuthenticator(getLegalAuthenticator(docManifest));

		// Body
		// Immunizations / Impfungen
		for (final Immunization immunization : getVacdImmunizations(docManifest)) {
			doc.addImmunization(immunization);
		}

		// Active Problems / Problemliste
		for (final ActiveProblemConcern activeProblemConcernEntry : getActiveProblemConcernEntries(
				docManifest)) {
			doc.addActiveProblemConcern(activeProblemConcernEntry);
		}

		// History of Past Illness / Bisherige Krankheiten/Anamnese
		for (final PastProblemConcern pastProblemConcern : getPastProblemConcernEntries(
				docManifest)) {
			doc.addPastProblemConcern(pastProblemConcern);
		}

		// Allergies and Basic Adverse Reactions / Allergien und
		// Unverträglichkeiten
		for (final AllergyConcern allergyProblemConcern : getAllergyProblemConcernEntries(
				docManifest)) {
			doc.addAllergyConcern(allergyProblemConcern);
		}

		// Coded Results for gestational age / Codierte Resultate für
		// Gestationsalter
		final CodedResults cr = getGestationalAge(docManifest);
		if (cr != null) {
			doc.addCodedResults(cr);
		}

		// Laboratory Specialty Section / Relevante Laborbefunde
		for (final LaboratoryObservation laboratoryObservation : getVacdLaboratoryObservations(
				docManifest)) {
			doc.addLaboratoryObservation(laboratoryObservation);
		}

		// Schwangerschaftsanamnese
		// Coded Results for gestational age / Codierte Resultate für
		// Gestationsalter
		final PregnancyHistory ph = getPregnancyHistory(docManifest);
		if (ph != null) {
			doc.addPregnancyHistory(ph);
		}

		for (final ImmunizationRecommendation immunizationRecommendation : getImmunizationRecommendations(
				docManifest)) {
			doc.addImmunizationRecommendation(immunizationRecommendation);
		}

		doc.addComment(getComment(docManifest));

		return doc;
	}

	/**
	 * <div class="en">Gets an eHC Author object from the given FHIR
	 * MedicationStatement object
	 *
	 * @param fhirMedicationStatement
	 *            the FHIR MedicationStatement object
	 * @return eHC Author object </div> <div class="de"></div>
	 *         <div class="fr"></div>
	 */
	public org.ehealth_connector.common.Author getAuthor(
			MedicationStatement fhirMedicationStatement) {
		org.ehealth_connector.common.Author retVal = null;
		for (final Reference ref : fhirMedicationStatement.getDerivedFrom()) {
			List<org.hl7.fhir.dstu3.model.Extension> extensions = ref
					.getExtensionsByUrl(FhirCommon.urnUseAsAuthor);
			if (!extensions.isEmpty() && (ref.getResource() instanceof Person)) {
				retVal = FhirCommon.getAuthor((Person) ref.getResource());
				if (extensions.get(0).getValue() instanceof TimeType) {
					TimeType timeStamp = ((TimeType) extensions.get(0).getValue());
					if (timeStamp.getValue().length() > 8)
						retVal.setTime(DateUtil.parseDateyyyyMMddHHmmssZZZZ(timeStamp.getValue()));

					else
						retVal.setTime(DateUtil.parseDateyyyyMMdd(timeStamp.getValue()));
					break;
				}
			}
		}
		return retVal;
	}

	/**
	 * Gets the code of the given medication
	 *
	 * @param fhirMedicationStatement
	 *            the FHIR resource
	 * @return the code of the given medication
	 */
	private Code getCode(MedicationStatement fhirMedicationStatement) {
		Code retVal = null;
		if (!fhirMedicationStatement.getExtensionsByUrl(FhirCommon.urnUseAsCode).isEmpty()) {
			final Coding fhirCode = (Coding) fhirMedicationStatement
					.getExtensionsByUrl(FhirCommon.urnUseAsCode).get(0).getValue();
			retVal = new Code(FhirCommon.removeUrnOidPrefix(fhirCode.getSystem()),
					fhirCode.getCode(), fhirCode.getDisplay());
		}
		return retVal;
	}

	/**
	 * Gets the Consumable of the given medication
	 *
	 * @param fhirMedicationStatement
	 *            the FHIR resource
	 * @return the Consumable of the given medication
	 */
	private org.ehealth_connector.cda.Consumable getConsumable(
			MedicationStatement fhirMedicationStatement) {
		org.ehealth_connector.cda.Consumable retVal = null;

		if (fhirMedicationStatement.getMedication() instanceof Reference) {
			final Reference ref = (Reference) fhirMedicationStatement.getMedication();
			final Medication fhirMedi = (Medication) ref.getResource();
			// Create the consumable
			retVal = new org.ehealth_connector.cda.Consumable(fhirMedi.getCode().getText());

			// Set id
			if (!fhirMedi.getExtensionsByUrl(FhirCommon.urnUseAsIdentifier).isEmpty()) {
				final Identifier id = (Identifier) fhirMedi
						.getExtensionsByUrl(FhirCommon.urnUseAsIdentifier).get(0).getValue();
				retVal.setManufacturedProductId(new org.ehealth_connector.common.Identificator(
						FhirCommon.removeUrnOidPrefix(id.getSystem()), id.getValue()));
			}

			// Set code
			final Coding fhirCode = fhirMedi.getCode().getCodingFirstRep();
			final Code code = new Code(FhirCommon.removeUrnOidPrefix(fhirCode.getSystem()),
					fhirCode.getCode(), fhirCode.getDisplay());
			retVal.setWhoAtcCode(code);

			// Set manufacturer
			final Reference refManufacturer = fhirMedi.getManufacturer();
			retVal.setManufacturer(FhirCommon.getOrganization(refManufacturer.getResource()));

			// LotNumber
			retVal.setLotNr(getLot(fhirMedicationStatement));
		} else {
			// Create the consumable
			retVal = new org.ehealth_connector.cda.Consumable(false);
		}

		return retVal;
	}

	/**
	 * <div class="en"> Gets a FHIR ListResource object containing the
	 * criterions in the given FHIR MedicationStatement object
	 *
	 * @param fhirMedicationStatement
	 *            the FHIR MedicationStatement object
	 * @return FHIR ListResource object containing the criterions </div>
	 *         <div class="de"></div> <div class="fr"></div>
	 */
	public ListResource getCriterions(MedicationStatement fhirMedicationStatement) {
		ListResource retVal = new ListResource();
		for (final Reference ref : fhirMedicationStatement.getDerivedFrom()) {
			if (!ref.getExtensionsByUrl(FhirCommon.urnUseAsCriterion).isEmpty()) {
				retVal = (ListResource) ref.getResource();
				break;
			}
		}
		return retVal;
	}

	/**
	 * Gets the Immunization Recommendation
	 *
	 * @param fhirMedicationStatement
	 *            the FHIR resource
	 * @return the Immunization Recommendation
	 */
	private org.ehealth_connector.cda.ch.vacd.ImmunizationRecommendation getImmunizationRecommendation(
			MedicationStatement fhirMedicationStatement) {
		org.ehealth_connector.cda.ch.vacd.ImmunizationRecommendation retVal = null;

		// Create the immunization
		retVal = new org.ehealth_connector.cda.ch.vacd.ImmunizationRecommendation();

		// Add Identifiers
		for (final Identifier id : fhirMedicationStatement.getIdentifier()) {
			final String codeSystem = FhirCommon.removeUrnOidPrefix(id.getSystem());
			retVal.addId(new Identificator(codeSystem, id.getValue()));
		}

		// Date
		retVal.setPossibleAppliance(fhirMedicationStatement.getDateAsserted(), null);

		// code
		final Code code = getCode(fhirMedicationStatement);
		final MedicationsSpecialConditions specialConditions = MedicationsSpecialConditions
				.getEnum(code.getCode());
		if (specialConditions != null) {
			retVal.setCode(specialConditions);
		} else {
			// code
			if (fhirMedicationStatement.getMedication() instanceof CodeableConcept) {
				final CodeableConcept cc = (CodeableConcept) fhirMedicationStatement
						.getMedication();

				retVal.setCode(
						MedicationsSpecialConditions.getEnum(cc.getCodingFirstRep().getCode()));
			} else {
				if (fhirMedicationStatement.getTaken() == MedicationStatementTaken.N)
					retVal.setShallNotBeAdministerd();

				// doseQuantity
				Double doseQuantity = null;
				for (final Dosage dosage : fhirMedicationStatement.getDosage()) {
					try {
						doseQuantity = ((Quantity) dosage.getDoseSimpleQuantity()).getValue()
								.doubleValue();
						retVal.setDosage(doseQuantity);
					} catch (FHIRException e) {
					}
					break; // currently only support for one doseQuantity
				}

				// consumable
				retVal.setConsumable(getConsumable(fhirMedicationStatement));
			}
		}
		// author
		retVal.setAuthor(getAuthor(fhirMedicationStatement));

		// medication targets
		final ListResource medicationTargets = getMedicationTargets(fhirMedicationStatement);
		for (ListEntryComponent listEntry : medicationTargets.getEntry()) {
			Reference ref = listEntry.getItem();
			final Observation fhirObs = (Observation) ref.getResource();
			final Coding fhirCode = fhirObs.getCode().getCodingFirstRep();
			final MedicationTargetEntry entry = new MedicationTargetEntry();
			entry.setId(new Identificator(
					fhirObs.getIdentifierFirstRep().getSystem().replace(FhirCommon.oidUrn, ""),
					fhirObs.getIdentifierFirstRep().getValue()));
			entry.setImmunizationTargetCode(
					new Code(FhirCommon.removeUrnOidPrefix(fhirCode.getSystem()),
							fhirCode.getCode(), fhirCode.getDisplay()));
			retVal.addMedicationTargetEntry(entry);
		}

		// criterion
		final ListResource criterions = getCriterions(fhirMedicationStatement);
		for (ListEntryComponent listEntry : criterions.getEntry()) {
			Reference ref = listEntry.getItem();
			final Observation fhirObs = (Observation) ref.getResource();
			final Coding fhirCode = fhirObs.getCode().getCodingFirstRep();
			final CriterionEntry entry = new CriterionEntry();
			entry.setRecCategoryCode(new Code(FhirCommon.removeUrnOidPrefix(fhirCode.getSystem()),
					fhirCode.getCode(), fhirCode.getDisplay()));
			retVal.setCriterionEntry(entry);
		}

		// comment
		retVal.setCommentText(getComment(fhirMedicationStatement));

		return retVal;

	}

	/**
	 * <div class="en">Gets a list of eHC ImmunizationRecommendations from the
	 * given FHIR resource
	 *
	 * @param docManifest
	 *            the FHIR resource
	 * @return list of eHC ImmunizationRecommendations </div>
	 *         <div class="de"></div> <div class="fr"></div>
	 */
	public List<org.ehealth_connector.cda.ch.vacd.ImmunizationRecommendation> getImmunizationRecommendations(
			DocumentManifest docManifest) {
		final List<org.ehealth_connector.cda.ch.vacd.ImmunizationRecommendation> retVal = new ArrayList<org.ehealth_connector.cda.ch.vacd.ImmunizationRecommendation>();
		for (final DocumentManifestContentComponent entry : docManifest.getContent()) {
			if (!entry.getExtensionsByUrl(FhirCommon.urnUseAsImmunizationRecommendation)
					.isEmpty()) {
				Reference ref = null;
				try {
					ref = entry.getPReference();
				} catch (FHIRException e) {
				}
				if (ref != null) {
					if (ref.getResource() instanceof MedicationStatement) {
						MedicationStatement fhirObject = (MedicationStatement) ref.getResource();
						ImmunizationRecommendation recom = getImmunizationRecommendation(
								fhirObject);
						retVal.add(recom);
					}
				}
			}
		}
		return retVal;
	}

	/**
	 * <div class="en"> Gets a FHIR ListResource object containing the
	 * medication targes in the given FHIR MedicationStatement object
	 *
	 * @param fhirMedicationStatement
	 *            the FHIR MedicationStatement object
	 * @return FHIR ListResource object containing the medication targets </div>
	 *         <div class="de"></div> <div class="fr"></div>
	 */
	public ListResource getMedicationTargets(MedicationStatement fhirMedicationStatement) {
		ListResource retVal = new ListResource();
		for (final Reference ref : fhirMedicationStatement.getDerivedFrom()) {
			if (!ref.getExtensionsByUrl(FhirCommon.urnUseAsMedicationTarget).isEmpty()) {
				retVal = (ListResource) ref.getResource();
			}
		}
		return retVal;
	}

	/**
	 * <div class="en"> Gets the eHC Vacd document type code (full or masked
	 * patient demographics) from the given FHIR resource
	 *
	 * @param docManifest
	 *            the FHIR resource
	 * @return eHC Vacd document type code (full or masked patient
	 *         demographics)</div> <div class="de"></div> <div class="fr"></div>
	 */
	public DocTypeCode getVacdDocType(DocumentManifest docManifest) {

		DocTypeCode retVal = DocTypeCode.PATIENT; // default
		for (final DocumentManifestContentComponent entry : docManifest.getContent()) {
			if (!entry.getExtensionsByUrl(FhirCommon.urnUseAsDocType).isEmpty()) {
				Reference ref = null;
				try {
					ref = entry.getPReference();
				} catch (FHIRException e) {
				}
				if (ref != null) {
					if (ref.getResource() instanceof Basic) {
						final Basic fhirBasic = (Basic) ref.getResource();
						final Coding langCode = fhirBasic.getCode().getCodingFirstRep();
						if (OID_VACD.equals(langCode.getSystem())) {
							if ("patient".equals(langCode.getCode().toLowerCase())) {
								retVal = DocTypeCode.PATIENT;
								break;
							} else if ("pseudonymized".equals(langCode.getCode().toLowerCase())) {
								retVal = DocTypeCode.PSEUDONYMIZED;
								break;
							}
						}
					}
				}
			}
		}
		return retVal;
	}

	/**
	 * Gets the VACD Immunization
	 *
	 * @param fhirMedicationStatement
	 *            the FHIR resource
	 * @return the VACD Immunization
	 */
	private org.ehealth_connector.cda.ch.vacd.Immunization getVacdImmunization(
			MedicationStatement fhirMedicationStatement) {

		final Code code = getCode(fhirMedicationStatement);

		// Create the immunization
		final org.ehealth_connector.cda.ch.vacd.Immunization retVal = new org.ehealth_connector.cda.ch.vacd.Immunization();

		// Add Identifiers
		retVal.getMdht().getIds().clear();
		for (final Identifier id : fhirMedicationStatement.getIdentifier()) {
			final String codeSystem = FhirCommon.removeUrnOidPrefix(id.getSystem());
			retVal.addId(new Identificator(codeSystem, id.getValue()));
		}

		// author
		retVal.setAuthor(getAuthor(fhirMedicationStatement));

		if (!"IMMUNIZ".equals(code.getCode())) {
			// code
			retVal.setCode(MedicationsSpecialConditions.getEnum(code.getCode()));
		} else {
			// code
			if (fhirMedicationStatement.getMedication() instanceof CodeableConcept) {
				final CodeableConcept cc = (CodeableConcept) fhirMedicationStatement
						.getMedication();

				retVal.setCode(
						MedicationsSpecialConditions.getEnum(cc.getCodingFirstRep().getCode()));
			}

			// effectiveTime
			retVal.setApplyDate(fhirMedicationStatement.getDateAsserted());

			// doseQuantity
			Double doseQuantity = null;
			for (final Dosage dosage : fhirMedicationStatement.getDosage()) {
				try {
					doseQuantity = ((Quantity) dosage.getDoseSimpleQuantity()).getValue()
							.doubleValue();
					retVal.setDosage(doseQuantity);
				} catch (FHIRException e) {
				}
				break; // currently only support for one doseQuantity
			}

			// consumable
			final Consumable consumable = getConsumable(fhirMedicationStatement);
			if (consumable != null)
				retVal.setConsumable(consumable);

			// perfomer
			final Author author = getPerformer(fhirMedicationStatement);
			if (author != null)
				retVal.setPerformer(author);

			// medication targets
			final ListResource medicationTargets = getMedicationTargets(fhirMedicationStatement);
			for (ListEntryComponent listEntry : medicationTargets.getEntry()) {
				Reference ref = listEntry.getItem();
				final Observation fhirObs = (Observation) ref.getResource();
				final Coding fhirCode = fhirObs.getCode().getCodingFirstRep();
				final MedicationTargetEntry entry = new MedicationTargetEntry();
				entry.setId(new Identificator(
						fhirObs.getIdentifierFirstRep().getSystem().replace(FhirCommon.oidUrn, ""),
						fhirObs.getIdentifierFirstRep().getValue()));
				entry.setImmunizationTargetCode(
						new Code(FhirCommon.removeUrnOidPrefix(fhirCode.getSystem()),
								fhirCode.getCode(), fhirCode.getDisplay()));
				retVal.addMedicationTargetEntry(entry);
			}

			// criterion
			final ListResource criterions = getCriterions(fhirMedicationStatement);
			for (ListEntryComponent listEntry : criterions.getEntry()) {
				Reference ref = listEntry.getItem();
				final Observation fhirObs = (Observation) ref.getResource();
				final Coding fhirCode = fhirObs.getCode().getCodingFirstRep();
				final CriterionEntry entry = new CriterionEntry();
				entry.setRecCategoryCode(
						new Code(FhirCommon.removeUrnOidPrefix(fhirCode.getSystem()),
								fhirCode.getCode(), fhirCode.getDisplay()));
				retVal.setCriterionEntry(entry);
			}

			// comment
			retVal.setCommentText(getComment(fhirMedicationStatement));

		}

		return retVal;

	}

	//
	/**
	 * <div class="en">Gets a list of eHC Immunizations from the given FHIR
	 * resource
	 *
	 * @param docManifest
	 *            FHIR resource
	 * @return list of eHC Immunizations </div> <div class="de"></div>
	 *         <div class="fr"></div>
	 */
	public List<org.ehealth_connector.cda.ch.vacd.Immunization> getVacdImmunizations(
			DocumentManifest docManifest) {
		final List<org.ehealth_connector.cda.ch.vacd.Immunization> retVal = new ArrayList<org.ehealth_connector.cda.ch.vacd.Immunization>();
		for (final DocumentManifestContentComponent entry : docManifest.getContent()) {
			if (!entry.getExtensionsByUrl(FhirCommon.urnUseAsImmunization).isEmpty()) {
				Reference ref = null;
				try {
					ref = entry.getPReference();
				} catch (FHIRException e) {
				}
				if (ref != null) {
					retVal.add(getVacdImmunization((MedicationStatement) ref.getResource()));
				}
			}
		}
		return retVal;
	}

	/**
	 * Gets the VACD Laboratory Observation
	 *
	 * @param fhirObservation
	 *            the FHIR resource
	 * @return the VACD Laboratory Observation
	 */
	private org.ehealth_connector.cda.ch.vacd.LaboratoryObservation getVacdLaboratoryObservation(
			Observation fhirObservation) {
		final org.ehealth_connector.cda.ch.vacd.LaboratoryObservation retVal = new LaboratoryObservation();

		// TODO better inheritance: this code is copied from
		// AbstractFhirCdaCh

		fhirObservation.getCode().getCodingFirstRep();
		retVal.setCode(FhirCommon.fhirCodeToEhcCode(fhirObservation.getCode()));
		if (fhirObservation.getIssued() != null) {
			retVal.setEffectiveTime(fhirObservation.getIssued());
		}
		if (!fhirObservation.getPerformer().isEmpty()) {
			final Reference refPerf = fhirObservation.getPerformer().get(0);
			retVal.setLaboratory(FhirCommon.getOrganization((Organization) refPerf.getResource()),
					fhirObservation.getIssued());
		}
		Value v = null;
		// type PQ
		if (fhirObservation.getValue() instanceof Quantity) {
			final Quantity fhirQuantity = (Quantity) fhirObservation.getValue();
			v = new Value(fhirQuantity.getValue().toString(), Ucum.AHGEquivalentsPerMilliLiter);

			// fix for the bug(?), which ommits the unit when it´s set to
			// "1"
			// Seems to be a bug in the MDHT. Ucum Unit can´t be set to "1".
			// unit = fhirQuantity.getUnit().replace("#", "");
			String unit;
			if (fhirQuantity.getUnit().startsWith("#")) {
				unit = fhirQuantity.getUnit();
			} else {
				unit = fhirQuantity.getUnit();
			}
			v.setUcumUnit(unit);
		}
		// type String
		if (fhirObservation.getValue() instanceof StringType) {
			final StringType fhirString = (StringType) fhirObservation.getValue();
			// type BL
			if (fhirString.getValueAsString().equalsIgnoreCase("false")
					|| fhirString.getValueAsString().equalsIgnoreCase("true")
					|| fhirString.getValueAsString().equalsIgnoreCase("NA")) {
				if (!fhirObservation.getDataAbsentReason().isEmpty()) {
					BL bl = DatatypesFactory.eINSTANCE.createBL();
					bl.setNullFlavor(NullFlavor.NA);
					v = new Value(bl);
				} else {
					if (fhirString.getValueAsString().equalsIgnoreCase("true")) {
						v = new Value(true);
					}
					if (fhirString.getValueAsString().equalsIgnoreCase("false")) {
						v = new Value(false);
					}
					if (fhirString.getValueAsString().equalsIgnoreCase("NA")) {
						BL bl = DatatypesFactory.eINSTANCE.createBL();
						bl.setNullFlavor(NullFlavor.NA);
						v = new Value(bl);
					}
				}
			}
			if (fhirString.getValue().startsWith("INT:")) {
				if (fhirString.getValue().startsWith("INT:NA")) {
					INT intValue = DatatypesFactory.eINSTANCE.createINT();
					intValue.setNullFlavor(NullFlavor.NA);
					v = new Value(intValue);
				} else {
					v = new Value(Integer.parseInt(fhirString.getValue().replace("INT:", "")));
				}
			}
			if (fhirString.getValue().startsWith("ED:")) {
				if (fhirString.getValue().startsWith("ED:#")) {
					ED edValue = Util.createReference(fhirString.getValue().replace("ED:", ""));
					v = new Value(edValue);
				} else {
					v = new Value(Integer.parseInt(fhirString.getValue().replace("INT:", "")));
				}
			}
			if (fhirString.getValue().startsWith("ST:")) {
				if (fhirString.getValue().startsWith("ST:NA")) {
					ST stValue = DatatypesFactory.eINSTANCE.createST();
					stValue.setNullFlavor(NullFlavor.NA);
					v = new Value(stValue);
				} else {
					ST stValue = DatatypesFactory.eINSTANCE
							.createST(fhirString.getValue().replace("ST:", ""));
					v = new Value(stValue);
				}
			}
		}
		// type CD
		if (fhirObservation.getValue() instanceof CodeableConcept)

		{
			final Coding fhirValueCode = ((CodeableConcept) fhirObservation.getValue())
					.getCodingFirstRep();
			retVal.addValue(
					new Code(new Code(FhirCommon.removeUrnOidPrefix(fhirValueCode.getSystem()),
							fhirValueCode.getCode(), fhirValueCode.getDisplay())));
		}
		if (fhirObservation.getValue() instanceof Ratio) {
			// type RTO not yet implemented
		}
		if (v != null) {
			retVal.addValue(v);
		}

		// ReferenceRange
		if (!fhirObservation.getReferenceRange().isEmpty()) {
			org.ehealth_connector.common.ReferenceRange rr = new org.ehealth_connector.common.ReferenceRange();
			// Value
			if (fhirObservation.getReferenceRangeFirstRep().getLow().getUnit() != null
					&& fhirObservation.getReferenceRangeFirstRep().getHigh().getUnit() != null) {
				v = new Value(fhirObservation.getReferenceRangeFirstRep().getLow().getValue(),
						fhirObservation.getReferenceRangeFirstRep().getLow().getUnit(),
						fhirObservation.getReferenceRangeFirstRep().getHigh().getValue(),
						fhirObservation.getReferenceRangeFirstRep().getHigh().getUnit());
			} else {
				v = new Value(
						fhirObservation.getReferenceRangeFirstRep().getLow().getValue()
								.toBigInteger(),
						fhirObservation.getReferenceRangeFirstRep().getHigh().getValue()
								.toBigInteger());
			}

			rr.setValue(v);

			// Interpretation of the reference range
			ObservationInterpretation obsInt = null;
			if (fhirObservation.getReferenceRangeFirstRep().getAppliesTo() != null) {
				if (fhirObservation.getReferenceRangeFirstRep().getAppliesTo().size() > 0) {
					if (fhirObservation.getReferenceRangeFirstRep().getAppliesTo().get(0) != null) {
						if (fhirObservation.getReferenceRangeFirstRep().getAppliesTo().get(0)
								.getCodingFirstRep() != null) {
							String code = fhirObservation.getReferenceRangeFirstRep().getAppliesTo()
									.get(0).getCodingFirstRep().getCode();
							obsInt = ObservationInterpretation.getEnum(code);
						}
					}
				}
			}
			if (obsInt != null) {
				rr.setInterpretation(obsInt);
			}
			retVal.setReferenceRange(rr);
		}

		// Interpretation of the observation
		final Coding fhirInterpretationCode = fhirObservation.getInterpretation()
				.getCodingFirstRep();
		if (fhirInterpretationCode != null) {
			if (fhirInterpretationCode.getSystem() != null) {
				retVal.addInterpretationCode(new Code(
						FhirCommon.removeUrnOidPrefix(fhirInterpretationCode.getSystem()),
						fhirInterpretationCode.getCode(), fhirInterpretationCode.getDisplay()));
			} else
				retVal.addInterpretationCode(NullFlavor.NA);
		}
		// Text reference (inside the observation)
		if (fhirObservation.getComment() != null && !fhirObservation.getComment().isEmpty()) {
			retVal.setTextReference(fhirObservation.getComment());
		}

		// Comments
		for (ObservationRelatedComponent commentRef : fhirObservation.getRelated()) {
			if (commentRef.getTarget().getResource() instanceof Observation) {
				Observation comment = (Observation) commentRef.getTarget().getResource();
				if (comment.getComment() != null) {
					retVal.addCommentEntry(new SectionAnnotationCommentEntry(comment.getComment()));
				}
			}
		}

		return retVal;
	}

	/**
	 * <div class="en">Gets a list of eHC LaboratoryObservations from the given
	 * FHIR resource
	 *
	 * @param docManifest
	 *            FHIR resource
	 * @return list of eHC LaboratoryObservations </div> <div class="de"></div>
	 *         <div class="fr"></div>
	 */
	public List<org.ehealth_connector.cda.ch.vacd.LaboratoryObservation> getVacdLaboratoryObservations(
			DocumentManifest docManifest) {
		final List<org.ehealth_connector.cda.ch.vacd.LaboratoryObservation> retVal = new ArrayList<org.ehealth_connector.cda.ch.vacd.LaboratoryObservation>();

		// TODO better inheritance: this code is copied from
		// AbstractFhirCdaCh

		for (final DocumentManifestContentComponent entry : docManifest.getContent()) {
			if (!entry.getExtensionsByUrl(FhirCommon.urnUseAsLaboratoryObservation).isEmpty()) {
				Reference ref = null;
				try {
					ref = entry.getPReference();
				} catch (FHIRException e) {
				}
				if (ref != null) {
					if (ref.getResource() instanceof Observation) {
						retVal.add(getVacdLaboratoryObservation((Observation) ref.getResource()));
					}
				}
			}
		}
		return retVal;
	}

	/**
	 * Read the VacdDocument object from the FHIR resource file
	 *
	 * @param fileName
	 *            the file name
	 * @return the vacd document
	 */
	public VacdDocument readVacDocumentFromFile(String fileName) {
		final String resourceString = FhirCommon.getXmlResource(fileName);
		final IParser parser = fhirCtx.newXmlParser();
		return parser.parseResource(VacdDocument.class, resourceString);
	}

}
