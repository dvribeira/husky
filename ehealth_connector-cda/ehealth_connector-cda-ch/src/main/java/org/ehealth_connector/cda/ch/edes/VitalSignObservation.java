package org.ehealth_connector.cda.ch.edes;

import java.text.ParseException;
import java.util.Date;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ehealth_connector.cda.ch.edes.enums.ObservationInterpretationVitalSign;
import org.ehealth_connector.cda.enums.ActSite;
import org.ehealth_connector.cda.enums.LanguageCode;
import org.ehealth_connector.common.Code;
import org.ehealth_connector.common.Value;
import org.ehealth_connector.common.enums.CodeSystems;
import org.ehealth_connector.common.utils.DateUtil;
import org.openhealthtools.mdht.uml.cda.ihe.IHEFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.CD;
import org.openhealthtools.mdht.uml.hl7.datatypes.CE;
import org.openhealthtools.mdht.uml.hl7.datatypes.CS;

public class VitalSignObservation {

	public enum VitalSignCodes {
		//@formatter:off
		RESPIRATION_RATE("9279-1", "Atemfrequenz", null, null, "respiration rate"),
		HEART_BEAT("8867-4", "Herzfrequenz", null, null, "heart beat"),
		OXYGEN_SATURATION_PERCENT("2710-2", "Sauerstoffsättigung", null, null, "oxygen saturation"),
		INTRAVASCULAR_SYSTOLIC("8480-6", "Intravaskulärer systolischer Druck", null, null, "intravascular systolic"),
		INTRAVASCULAR_DIASTOLIC("8462-4", "Intrvaskulärer diastolischer Druck", null, null, "intravascular diastolic"),
		BODY_TEMPERATURE_CEL("8310-5", "Körpertemperatur", null, null, "body temperature"),
		BODY_HEIGHT("8302-2", "Körpergrösse (gemessen)", null, null, "body height (measured)"),
		BODY_HEIGHT_LYING("8306-3", "Körpergrösse im Liegen", null, null, "body height lying"),
		CIRCUMFRENCE_OCCIPITAL_FRONTAL("8287-5", "Kopfumfang okzipitofrontal", null, null, "circumfence occipital frontal"),
		BODY_WEIGHT("3141-9", "Körpergewicht (gewogen)", null, null, "body weight (measured)");
		//@formatter:on

		private String loinc;
		private String descriptionDe;
		private String descriptionFr;
		private String descriptionIt;
		private String descriptionEn;

		private VitalSignCodes(String loinc, String descriptionDe, String descriptionFr,
				String descriptionIt, String descriptionEn) {
			this.loinc = loinc;
			this.descriptionDe = descriptionDe;
			this.descriptionFr = descriptionFr;
			this.descriptionIt = descriptionIt;
			this.descriptionEn = descriptionEn;
		}

		public Code getCode() {
			Code ret = new Code(CodeSystems.LOINC, loinc);
			ret.setDisplayName(getDescription(null));
			return ret;
		}

		public Code getCode(CS languageCode) {
			Code ret = new Code(CodeSystems.LOINC, loinc);
			ret.setDisplayName(getDescription(languageCode));
			return ret;
		}

		public Object getLoinc() {
			return loinc;
		}

		public String getDescription(CS lc) {
			String lcStr = LanguageCode.ENGLISH.getCodeValue();
			if (lc != null) {
				lcStr = lc.getCode().toLowerCase();
			}
			if (lcStr.equals(LanguageCode.GERMAN.getCodeValue().toLowerCase()))
				return getDescriptionDe();
			if (lcStr.equals(LanguageCode.FRENCH.getCodeValue().toLowerCase()))
				return getDescriptionFr();
			if (lcStr.equals(LanguageCode.ITALIAN.getCodeValue().toLowerCase()))
				return getDescriptionIt();
			if ("de".equals(lcStr))
				return getDescriptionDe();
			if ("fr".equals(lcStr))
				return getDescriptionFr();
			if ("it".equals(lcStr))
				return getDescriptionIt();
			if ("en".equals(lcStr))
				return getDescriptionEn();
			return getDescriptionDe();
		}

		private String getDescriptionEn() {
			if (descriptionEn != null) {
				return descriptionEn;
			}
			return name();
		}

		private String getDescriptionFr() {
			if (descriptionFr != null) {
				return descriptionFr;
			}
			return getDescriptionEn();
		}

		private String getDescriptionIt() {
			if (descriptionIt != null) {
				return descriptionIt;
			}
			return getDescriptionEn();
		}

		private String getDescriptionDe() {
			if (descriptionDe != null) {
				return descriptionDe;
			}
			return getDescriptionEn();
		}

		public static VitalSignCodes getVitalSignCode(String loincCode) {
			VitalSignCodes[] values = values();
			for (VitalSignCodes vitalSignCodes : values) {
				if (vitalSignCodes.getLoinc().equals(loincCode)) {
					return vitalSignCodes;
				}
			}
			return null;
		}
	}

	/** The m vital sign observation. */
	private final org.openhealthtools.mdht.uml.cda.ihe.VitalSignObservation mVitalSignObservation;

	/**
	 * Instantiates a new vital sign observation.
	 */
	public VitalSignObservation() {
		mVitalSignObservation = IHEFactory.eINSTANCE.createVitalSignObservation().init();
	}

	/**
	 * Instantiates a new vital sign observation.
	 * 
	 * @param observation
	 *            <div class="en">Existing vital sign observation</div> <div
	 *            class="de">Existierende vital sign observation</div> <div
	 *            class="fr"></div> <div class="it"></div>
	 */
	public VitalSignObservation(
			org.openhealthtools.mdht.uml.cda.ihe.VitalSignObservation observation) {
		mVitalSignObservation = observation;
	}

	/**
	 * Instantiates a new vital sign observation.
	 * 
	 * @param code
	 *            <div class="en">Code for a vital sign observation</div> <div
	 *            class="de">Code für ein Vitalzeichen</div> <div
	 *            class="fr"></div> <div class="it"></div>
	 * @param effectiveTime
	 *            <div class="en">clinically or operationally relevant
	 *            time</div> <div class="de">klinisch relevantes Datum und
	 *            Uhrzeit</div> <div class="fr"></div> <div class="it"></div>
	 * @param value
	 *            <div class="de">Wert des Resultats (als Value-Objekt)</div>
	 *            <div class="fr"></div> <div class="it"></div>
	 */
	public VitalSignObservation(VitalSignCodes code, Date effectiveTime, Value value) {
		this(code.getCode(), effectiveTime, value);
	}

	/**
	 * Instantiates a new vital sign observation.
	 * 
	 * @param code
	 *            <div class="en">Code for a vital sign observation</div> <div
	 *            class="de">Code für ein Vitalzeichen</div> <div
	 *            class="fr"></div> <div class="it"></div>
	 * @param effectiveTime
	 *            <div class="en">clinically or operationally relevant
	 *            time</div> <div class="de">klinisch relevantes Datum und
	 *            Uhrzeit</div> <div class="fr"></div> <div class="it"></div>
	 * @param interpretation
	 *            <div class="de">Beurteilung des Resultats</div> <div
	 *            class="fr"></div> <div class="it"></div>
	 * @param targetSite
	 *            <div class="de">Anatomische Lage des Resultats</div> <div
	 *            class="fr"></div> <div class="it"></div>
	 * @param value
	 *            <div class="de">Wert des Resultats (als Value-Objekt)</div>
	 *            <div class="fr"></div> <div class="it"></div>
	 */
	public VitalSignObservation(VitalSignCodes code, Date effectiveTime,
			ObservationInterpretationVitalSign interpretation, ActSite targetSite, Value value) {
		this(code.getCode(), effectiveTime, interpretation, targetSite, value);
	}

	/**
	 * Instantiates a new vital sign observation.
	 * 
	 * @param code
	 *            <div class="en">Code for a vital sign observation</div> <div
	 *            class="de">Code für ein Vitalzeichen</div> <div
	 *            class="fr"></div> <div class="it"></div>
	 * @param effectiveTime
	 *            <div class="en">clinically or operationally relevant
	 *            time</div> <div class="de">klinisch relevantes Datum und
	 *            Uhrzeit</div> <div class="fr"></div> <div class="it"></div>
	 * @param value
	 *            <div class="de">Wert des Resultats (als Value-Objekt)</div>
	 *            <div class="fr"></div> <div class="it"></div>
	 */
	public VitalSignObservation(Code code, Date effectiveTime, Value value) {
		this(code, effectiveTime, null, null, value);
	}

	/**
	 * Instantiates a new vital sign observation.
	 * 
	 * @param code
	 *            <div class="en">Code for a vital sign observation</div> <div
	 *            class="de">Code für ein Vitalzeichen</div> <div
	 *            class="fr"></div> <div class="it"></div>
	 * @param effectiveTime
	 *            <div class="en">clinically or operationally relevant
	 *            time</div> <div class="de">klinisch relevantes Datum und
	 *            Uhrzeit</div> <div class="fr"></div> <div class="it"></div>
	 * @param interpretation
	 *            <div class="de">Beurteilung des Resultats</div> <div
	 *            class="fr"></div> <div class="it"></div>
	 * @param targetSite
	 *            <div class="de">Anatomische Lage des Resultats</div> <div
	 *            class="fr"></div> <div class="it"></div>
	 * @param value
	 *            <div class="de">Wert des Resultats (als Value-Objekt)</div>
	 *            <div class="fr"></div> <div class="it"></div>
	 */
	public VitalSignObservation(Code code, Date effectiveTime,
			ObservationInterpretationVitalSign interpretation, ActSite targetSite, Value value) {
		mVitalSignObservation = IHEFactory.eINSTANCE.createVitalSignObservation().init();

		setCode(code);
		setEffectiveTime(effectiveTime);
		setInterpretationCode(interpretation);
		setTargetSite(targetSite);
		addValue(value);
	}

	/**
	 * <div class="de">Get a copy mdht vital sign observation.</div> <div
	 * class="de">Gibt eine Kopie der mdth vital sign observation zurück.</div>
	 * <div class="fr"></div> <div class="it"></div>
	 * 
	 * @return the org.openhealthtools.mdht.uml.cda.ch. vital sign observation
	 */
	public org.openhealthtools.mdht.uml.cda.ihe.VitalSignObservation getMdhtCopy() {
		return EcoreUtil.copy(mVitalSignObservation);
	}

	/**
	 * Sets the code.
	 * 
	 * @param code
	 *            the new code
	 */
	public void setCode(Code code) {
		mVitalSignObservation.setCode(code.getCD());
	}

	/**
	 * <div class="en">Gets the code of the observation</div> <div
	 * class="de">Gibt den Code der Beobachtung zurück.</div> <div
	 * class="fr"></div> <div class="it"></div>
	 * 
	 * @return the code
	 */
	public Code getCode() {
		final Code code = new Code(mVitalSignObservation.getCode());
		return code;
	}

	/**
	 * Sets the date time of result.
	 * 
	 * @param dateTimeOfResult
	 *            the new date time of result
	 */
	public void setEffectiveTime(Date dateTimeOfResult) {
		try {
			mVitalSignObservation.setEffectiveTime(DateUtil
					.createIVL_TSFromEuroDateTime(dateTimeOfResult));
		} catch (final ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the Effective Time
	 * 
	 * @return the effective time as date
	 */
	public Date getEffectiveTime() {
		return DateUtil.parseIVL_TSVDateTimeValue(mVitalSignObservation.getEffectiveTime());
	}

	/**
	 * Get the (first) problem value. The Value may be a coded or uncoded
	 * String.
	 * 
	 * @return the (first) problem value as string.
	 */
	public Value getValue() {
		if (!mVitalSignObservation.getValues().isEmpty()) {
			return new Value(mVitalSignObservation.getValues().get(0));
		}
		return null;
	}

	/**
	 * Adds the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public void addValue(Value value) {
		if (value.isPhysicalQuantity()) {
			mVitalSignObservation.getValues().add(value.copyMdhtPhysicalQuantity());
		}
		if (value.isCode()) {
			mVitalSignObservation.getValues().add(value.copyMdhtCode());
		}
		if (value.isRto()) {
			mVitalSignObservation.getValues().add(value.copyMdhtRto());
		}
	}

	/**
	 * Set a new interpretations of the vital sign observation.
	 * 
	 * @param code
	 *            <div class="de">Beurteilung des Resultats</div> <div
	 *            class="fr"></div> <div class="it"></div>
	 */
	public void setInterpretationCode(ObservationInterpretationVitalSign code) {
		mVitalSignObservation.getInterpretationCodes().clear();
		if (code != null) {
			mVitalSignObservation.getInterpretationCodes().add(code.getCE());
		}
	}

	public Code getInterpretationCode() {
		EList<CE> codes = mVitalSignObservation.getInterpretationCodes();
		if (!codes.isEmpty()) {
			return new Code(codes.get(0));
		}
		return null;
	}

	/**
	 * Set a new act site of the vital sign observation.
	 * 
	 * @param code
	 *            <div class="de">Anatomische Lage des Resultats</div> <div
	 *            class="fr"></div> <div class="it"></div>
	 */
	public void setTargetSite(ActSite code) {
		mVitalSignObservation.getTargetSiteCodes().clear();
		if (code != null) {
			mVitalSignObservation.getTargetSiteCodes().add(code.getCD());
		}
	}

	public Code getTargetSiteCode() {
		EList<CD> codes = mVitalSignObservation.getTargetSiteCodes();
		if (!codes.isEmpty()) {
			return new Code(codes.get(0));
		}
		return null;
	}

	public void setLanguageCode(CS languageCode) {
		mVitalSignObservation.setLanguageCode(languageCode);
		VitalSignCodes vsCode = VitalSignCodes.getVitalSignCode(mVitalSignObservation.getCode()
				.getCode());
		if (vsCode != null) {
			setCode(vsCode.getCode(languageCode));
		}
	}
}
