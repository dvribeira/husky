package org.ehealth_connector.cda.ch.edes;

import java.util.Collections;
import java.util.List;

import org.ehealth_connector.cda.ch.AbstractCdaCh;
import org.ehealth_connector.cda.ch.edes.enums.SectionsEDES;
import org.ehealth_connector.cda.enums.LanguageCode;
import org.openhealthtools.mdht.uml.cda.ch.CHFactory;
import org.openhealthtools.mdht.uml.cda.ihe.CodedVitalSignsSection;
import org.openhealthtools.mdht.uml.cda.ihe.IHEFactory;

public class CdaChEdesCtnn extends AbstractCdaCh<org.openhealthtools.mdht.uml.cda.ch.CdaChEdesCtnn> {

	private CdaChEdesCommon common;

	private CodedVitalSigns mCodedVitalSigns;

	public CdaChEdesCtnn() {
		super(CHFactory.eINSTANCE.createCdaChEdesCtnn().init());
		common = new CdaChEdesCommon(getDoc());
	}

	public CdaChEdesCtnn(org.openhealthtools.mdht.uml.cda.ch.CdaChEdesCtnn doc) {
		super(doc);
		common = new CdaChEdesCommon(getDoc());
	}

	/**
	 * <div class="en">Creates a new EDES CTNN CDA document</div> <div
	 * class="de">Erstellt ein neues EDED CTNN CDA Dokument.</div>
	 * 
	 * @param language
	 * <br>
	 *            <div class="en">document language</div> <div
	 *            class="de">Sprache des Dokments</div> <div class="fr"></div>
	 *            <div class="it"></div>
	 * @param stylesheet
	 *            <div class="en">stylesheet, which should be referenced to
	 *            render a human readable representation of the document</div>
	 *            <div class="de">Stylesheet, welches im CDA mittels
	 *            ?xml-stylesheet für die menschlich lesbare Darstellung
	 *            referenziert werden soll (z.B.
	 *            '../../../../stylesheets/HL7.ch/CDA-CH/v1.2/cda-ch.xsl').</div
	 *            >
	 * @param cascadingStylesheet
	 *            <div class="en">Cascading stylesheet, which should be
	 *            referenced to render a human readable representation of the
	 *            document</div> <div class="de">Cascasing Stylesheet, welches
	 *            Designinformationen für die menschlich lesbare Darstellung
	 *            referenziert (z.B.
	 *            '../../../../stylesheets/HL7.ch/CDA-CH/v1.2/cda-ch.xsl').</div
	 *            >
	 */
	public CdaChEdesCtnn(LanguageCode language, String stylesheet, String cascadingStylesheet) {
		super(CHFactory.eINSTANCE.createCdaChEdesCtnn().init(), stylesheet, cascadingStylesheet);
		setLanguageCode(language);
		common = new CdaChEdesCommon(getDoc());
		setTitle(common.getDocumentTitle());
	}

	/**
	 * <div class="en">Gets the MDHT-CdaChEdesCtnn Object</div> <div
	 * class="de">Liefert das MDHT-CdaChEdesCtnn-Objekt zurück.</div>
	 * 
	 * @return the doc
	 */
	@Override
	public org.openhealthtools.mdht.uml.cda.ch.CdaChEdesCtnn getDoc() {
		return (org.openhealthtools.mdht.uml.cda.ch.CdaChEdesCtnn) super.getDoc();
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionAbilityToWork() {
		return common.getNarrativeText(SectionsEDES.ABILITY_TO_WORK.findSection(getDoc()));
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionAbilityToWork(String text) {
		common.setNarrativeTextSection(SectionsEDES.ABILITY_TO_WORK,
				SectionsEDES.ABILITY_TO_WORK.findSection(getDoc()), text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionAcuityAssessment() {
		return common.getNarrativeText(getDoc().getAcuityAssessmentSection());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionAcuityAssessment(String text) {
		common.setNarrativeTextSection(SectionsEDES.ACUITY_ASSESSMENT, getDoc()
				.getAcuityAssessmentSection(), text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionAllergiesReactions() {
		return common.getNarrativeText(getDoc().getAllergiesReactionsSection());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionAllergiesReactions(String text) {
		common.setNarrativeTextSection(SectionsEDES.ALLERGIES_REACTIONS, getDoc()
				.getAllergiesReactionsSection(), text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionAssessments() {
		return common.getNarrativeText(getDoc().getAssessmentsSection());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionAssessments(String text) {
		common.setNarrativeTextSection(SectionsEDES.ASSESSMENTS, getDoc().getAssessmentsSection(),
				text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionChiefComplaint() {
		return common.getNarrativeText(getDoc().getChiefComplaintSection());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionChiefComplaint(String text) {
		common.setNarrativeTextSection(SectionsEDES.CHIEF_COMPLAINT, getDoc()
				.getChiefComplaintSection(), text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionCodedVitalSigns() {
		return common.getNarrativeText(getDoc().getCodedVitalSignsSection());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionCodedVitalSigns(String text) {
		common.setNarrativeTextSection(SectionsEDES.CODED_VITAL_SIGNS, getDoc()
				.getCodedVitalSignsSection(), text);
	}

	/**
	 * <div class="en">Add the coded vital sign observation to the
	 * document</div> <div class="de">Fügt das codierte Vitalzeichen in das
	 * Dokument ein</div>
	 * 
	 * @param sign
	 *            The vital sign observation <div class="en">The coded vital
	 *            sign observation to add</div> <div class="de">Das
	 *            hinzuzufügende codierte Vitalzeichen</div>
	 */
	public void addCodedVitalSign(VitalSignObservation sign) {
		if (mCodedVitalSigns == null) {
			CodedVitalSignsSection section = getDoc().getCodedVitalSignsSection();
			if (section == null) {
				section = IHEFactory.eINSTANCE.createCodedVitalSignsSection().init();
				common.addSection(section);
			}
			mCodedVitalSigns = new CodedVitalSigns(section);
		}
		mCodedVitalSigns.add(sign);
	}

	/**
	 * <div class="en">Gets the coded vital sign observations</div> <div
	 * class="de">Liefert alle codierten Vitalzeichen zurück</div>
	 * 
	 * @return List with coded vital sign observations
	 */
	public List<VitalSignObservation> getCodedVitalSignObservations() {
		if (mCodedVitalSigns == null) {
			final CodedVitalSignsSection section = getDoc().getCodedVitalSignsSection();
			if (section == null) {
				return Collections.emptyList();
			}
			mCodedVitalSigns = new CodedVitalSigns(section);
		}
		return mCodedVitalSigns.getCodedVitalSignObservations();
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionEDDisposition() {
		return common.getNarrativeText(getDoc().getEDDispositionSection());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionEDDisposition(String text) {
		common.setNarrativeTextSection(SectionsEDES.ED_DISPOSITION, getDoc()
				.getEDDispositionSection(), text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionFamilyMedicalHistory() {
		return common.getNarrativeText(getDoc().getFamilyMedicalHistorySection());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionFamilyMedicalHistory(String text) {
		common.setNarrativeTextSection(SectionsEDES.FAMILY_MEDICAL_HISTORY, getDoc()
				.getFamilyMedicalHistorySection(), text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionHistoryOfPastIllness() {
		return common.getNarrativeText(getDoc().getHistoryOfPastIllnessSection());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionHistoryOfPastIllness(String text) {
		common.setNarrativeTextSection(SectionsEDES.HISTORY_OF_PAST_ILLNESS, getDoc()
				.getHistoryOfPastIllnessSection(), text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionHistoryOfPresentIllness() {
		return common.getNarrativeText(getDoc().getHistoryOfPresentIllness());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionHistoryOfPresentIllness(String text) {
		common.setNarrativeTextSection(SectionsEDES.HISTORY_OF_PRESENT_ILLNESS, getDoc()
				.getHistoryOfPresentIllness(), text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionImmunizations() {
		return common.getNarrativeText(getDoc().getImmunizationsSection());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionImmunizations(String text) {
		common.setNarrativeTextSection(SectionsEDES.HISTORY_OF_IMMUNIZATION, getDoc()
				.getImmunizationsSection(), text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionIntravenousFluidsAdministered() {
		return common.getNarrativeText(getDoc().getIntravenousFluidsAdministeredSection());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionIntravenousFluidsAdministered(String text) {
		common.setNarrativeTextSection(SectionsEDES.INTRAVENOUS_FLUIDS_ADMINISTERED, getDoc()
				.getIntravenousFluidsAdministeredSection(), text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionMedicationsAdministered() {
		return common.getNarrativeText(getDoc().getMedicationsAdministeredSection());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionMedicationsAdministered(String text) {
		common.setNarrativeTextSection(SectionsEDES.MEDICATIONS_ADMINISTERED, getDoc()
				.getMedicationsAdministeredSection(), text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionMedications() {
		return common.getNarrativeText(getDoc().getMedicationsSection());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionMedications(String text) {
		common.setNarrativeTextSection(SectionsEDES.CURRENT_MEDICATIONS, getDoc()
				.getMedicationsSection(), text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionModeOfArrival() {
		return common.getNarrativeText(getDoc().getModeOfArrivalSection());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionModeOfArrival(String text) {
		common.setNarrativeTextSection(SectionsEDES.MODE_OF_ARRIVAL, getDoc()
				.getModeOfArrivalSection(), text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionPregnancyHistory() {
		return common.getNarrativeText(getDoc().getPregnancyHistorySection());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionPregnancyHistory(String text) {
		common.setNarrativeTextSection(SectionsEDES.HISTORY_OF_PREGNANCIES, getDoc()
				.getPregnancyHistorySection(), text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionProceduresAndInterventions() {
		return common.getNarrativeText(getDoc().getProceduresAndInterventionsSection());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionProceduresAndInterventions(String text) {
		common.setNarrativeTextSection(SectionsEDES.PROCEDURES, getDoc()
				.getProceduresAndInterventionsSection(), text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionReasonForVisit() {
		return common.getNarrativeText(getDoc().getReasonForVisitSection());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionReasonForVisit(String text) {
		common.setNarrativeTextSection(SectionsEDES.REASON_FOR_VISIT, getDoc()
				.getReasonForVisitSection(), text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionRemarks() {
		return common.getNarrativeText(SectionsEDES.REMARKS.findSection(getDoc()));
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionRemarks(String text) {
		common.setNarrativeTextSection(SectionsEDES.REMARKS,
				SectionsEDES.REMARKS.findSection(getDoc()), text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionSocialHistory() {
		return common.getNarrativeText(getDoc().getSocialHistorySection());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionSocialHistory(String text) {
		common.setNarrativeTextSection(SectionsEDES.SOCIAL_HISTORY, getDoc()
				.getSocialHistorySection(), text);
	}

	/**
	 * <div class="en">Gets the human readable CDA section text for the
	 * according section</div> <div class="de">Liefert den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @return <div class="en"> the text of the human readable part of the cda
	 *         document</div> <div class="de"> der text des menschlenlesbaren
	 *         Teils des CDA-Dokuments</div>
	 */
	public String getNarrativeTextSectionSurgeriesSection() {
		return common.getNarrativeText(getDoc().getSurgeriesSection());
	}

	/**
	 * <div class="en">Sets the human readable CDA section text for the
	 * according section</div> <div class="de">Setzt den menschenlesbaren CDA
	 * Section Text für die entsprechende Section</div>
	 * 
	 * @param text
	 *            <div class="en"> the new text for the human readable part of
	 *            the cda document</div> <div class="de"> der neue text für den
	 *            menschlenlesbaren Teil des CDA-Dokuments</div>
	 */
	public void setNarrativeTextSectionSurgeriesSection(String text) {
		common.setNarrativeTextSection(SectionsEDES.LIST_OF_SURGERIES, getDoc()
				.getSurgeriesSection(), text);
	}
}