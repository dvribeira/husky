package org.ehealth_connector.cda.ch.edes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.ehealth_connector.cda.MdhtFacade;
import org.ehealth_connector.cda.ch.edes.enums.ObservationInterpretationVitalSign;
import org.ehealth_connector.cda.ch.edes.enums.SectionsEDES;
import org.ehealth_connector.cda.enums.LanguageCode;
import org.ehealth_connector.common.Code;
import org.ehealth_connector.common.utils.DateUtil;
import org.ehealth_connector.common.utils.Util;
import org.openhealthtools.mdht.uml.cda.Observation;
import org.openhealthtools.mdht.uml.cda.Organizer;
import org.openhealthtools.mdht.uml.cda.ccd.CCDFactory;
import org.openhealthtools.mdht.uml.cda.ccd.VitalSignsOrganizer;
import org.openhealthtools.mdht.uml.cda.ihe.IHEFactory;
import org.openhealthtools.mdht.uml.cda.ihe.VitalSignsSection;

public class CodedVitalSigns extends MdhtFacade<VitalSignsSection> {

	// default language is German
	private LanguageCode languageCode = LanguageCode.GERMAN;

	/**
	 * Instantiates a new vital signs section.
	 * 
	 * @param languageCode
	 *            the language code
	 */
	public CodedVitalSigns(LanguageCode languageCode) {
		super(IHEFactory.eINSTANCE.createVitalSignsSection().init(), null, null);
		this.languageCode = languageCode;
		this.getMdht().setTitle(
				Util.st(SectionsEDES.CODED_VITAL_SIGNS
						.getSectionTitle((languageCode != null ? languageCode.getCS() : null))));
	}

	/**
	 * Instantiates a new vital signs section.
	 * 
	 * @param section
	 *            the vital signs section
	 */
	protected CodedVitalSigns(VitalSignsSection section) {
		super(section, null, null);
	}

	public void add(VitalSignObservation sign) {
		VitalSignsOrganizer organizer = getOrganizerForDate(sign.getEffectiveTime());
		organizer.addObservation(sign.getMdhtCopy());

		getMdht().createStrucDocText(getTable());
	}

	private VitalSignsOrganizer getOrganizerForDate(Date effectiveTime) {
		VitalSignsSection section = getMdht();
		EList<VitalSignsOrganizer> organizers = section.getVitalSignsOrganizers();
		for (VitalSignsOrganizer organizer : organizers) {
			Date organizerDate = DateUtil.parseIVL_TSVDateTimeValue(organizer.getEffectiveTime());
			if (organizerDate.equals(effectiveTime)) {
				return organizer;
			}
		}
		VitalSignsOrganizer organizer = CCDFactory.eINSTANCE.createVitalSignsOrganizer();
		try {
			organizer.setEffectiveTime(DateUtil.createIVL_TSFromEuroDateTime(effectiveTime));
		} catch (final ParseException e) {
			e.printStackTrace();
		}
		section.addOrganizer(organizer);
		return organizer;
	}

	public List<VitalSignObservation> getCodedVitalSignObservations() {
		List<VitalSignObservation> ret = new ArrayList<VitalSignObservation>();
		EList<Organizer> organizers = getMdht().getOrganizers();
		for (Organizer organizer : organizers) {
			EList<Observation> observations = organizer.getObservations();
			for (Observation observation : observations) {
				if (observation instanceof org.openhealthtools.mdht.uml.cda.ihe.VitalSignObservation) {
					ret.add(new VitalSignObservation(
							(org.openhealthtools.mdht.uml.cda.ihe.VitalSignObservation) observation));
				}
			}
		}
		ret.sort(new Comparator<VitalSignObservation>() {
			@Override
			public int compare(VitalSignObservation left, VitalSignObservation right) {
				return right.getEffectiveTime().compareTo(left.getEffectiveTime());
			}
		});
		return ret;
	}

	private String getTable() {
		StringBuilder sb = new StringBuilder();
		List<VitalSignObservation> observations = getCodedVitalSignObservations();

		if (!observations.isEmpty()) {
			sb.append("<table><tbody>");
			if (languageCode == LanguageCode.GERMAN) {
				sb.append("<tr><th>Datum / Uhrzeit</th><th>Beschreibung</th><th>Resultat</th></tr>");
			} else {
				sb.append("<tr><th>Date / Time</th><th>Description</th><th>Result</th></tr>");
			}
			for (VitalSignObservation vitalSignObservation : observations) {
				String signDateTime = DateUtil.formatDateTimeCh(vitalSignObservation
						.getEffectiveTime());
				String signDescription = vitalSignObservation.getCode().getDisplayName();
				String signResult = vitalSignObservation.getValue().getPhysicalQuantityValue()
						+ " " + vitalSignObservation.getValue().getPhysicalQuantityUnit();
				Code code = vitalSignObservation.getInterpretationCode();
				if (code != null
						&& !ObservationInterpretationVitalSign.NORMAL.getCodeValue().equals(
								code.getCode())) {
					String signInterpretation = "["
							+ vitalSignObservation.getInterpretationCode().getDisplayName() + "]";
					signResult += " " + signInterpretation;
				}
				Code target = vitalSignObservation.getTargetSiteCode();
				if (target != null) {
					String signTarget = "["
							+ vitalSignObservation.getTargetSiteCode().getDisplayName() + "]";
					signDescription += " " + signTarget;
				}
				sb.append("<tr><td>" + signDateTime + "</td><td>" + signDescription + "</td><td>"
						+ signResult + "</td></tr>");
			}
			sb.append("</tbody></table>");
		}

		return sb.toString();
	}
}