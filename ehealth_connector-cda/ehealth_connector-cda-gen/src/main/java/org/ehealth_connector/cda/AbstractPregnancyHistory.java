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

package org.ehealth_connector.cda;

import java.util.Date;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ehealth_connector.cda.enums.Pregnancies;
import org.ehealth_connector.common.utils.DateUtilMdht;
import org.openhealthtools.mdht.uml.cda.ihe.IHEFactory;
import org.openhealthtools.mdht.uml.cda.ihe.PregnancyObservation;
import org.openhealthtools.mdht.uml.hl7.datatypes.TS;
import org.openhealthtools.mdht.uml.hl7.vocab.ActClassObservation;
import org.openhealthtools.mdht.uml.hl7.vocab.x_ActMoodDocumentObservation;

/**
 * <div class="en">This element CAN be used to give information about
 * pregnancies</div> <div class="de">Bei Frauen KANN mit diesem Element die
 * strukturierte Angabe der Anamnese zu einer Schwangerschaft erfolgen.</div>
 * <div class="fr">Class PregnancyHistory.</div> <div class="it">Class
 * PregnancyHistory.</div>
 */
public abstract class AbstractPregnancyHistory {

	/** The m pregnancy. */
	private org.openhealthtools.mdht.uml.cda.ihe.PregnancyObservation mPregnancy;

	/**
	 * Instantiates a new pregnancy.
	 */
	public AbstractPregnancyHistory() {
		mPregnancy = IHEFactory.eINSTANCE.createPregnancyObservation().init();
		mPregnancy.setClassCode(ActClassObservation.OBS);
		mPregnancy.setMoodCode(x_ActMoodDocumentObservation.EVN);
		mPregnancy.setCode(Pregnancies.DELIVERY_DATE_CLINICAL_ESTIMATE.getCD());
		mPregnancy.setEffectiveTime(DateUtilMdht.createUnknownTime(null));
		setInternalId(null);
	}

	/**
	 * Instantiates a new pregnancy.
	 *
	 * @param estimatedBirthDate
	 *            <div class="en">calculated due date</div>
	 *            <div class="de">Errechneter Geburtstermin</div>
	 *            <div class="fr"></div> <div class="it"></div>
	 */
	public AbstractPregnancyHistory(Date estimatedBirthDate) {
		this();
		setEstimatedBirthDate(estimatedBirthDate);
	}

	/**
	 * Instantiates a new pregnancy.
	 *
	 * @param pregnancy
	 *            <div class="en">pregnancy</div>
	 *            <div class="de">Schwangerschaft</div> <div class="fr">
	 *            pregnancy</div> <div class="it"> pregnancy</div>
	 */
	public AbstractPregnancyHistory(
			org.openhealthtools.mdht.uml.cda.ihe.PregnancyObservation pregnancy) {
		mPregnancy = pregnancy;
	}

	/**
	 * <div class="en">Copy mdht pregnancy.</div> <div class="de">kopiert das
	 * MDHT Schwangerschaftsobjekt</div> <div class="fr"></div>
	 * <div class="it"></div>
	 *
	 * @return the org.openhealthtools.mdht.uml.cda.ihe.PregnancyObservation
	 */
	public org.openhealthtools.mdht.uml.cda.ihe.PregnancyObservation copyMdhtPregnancy() {
		return EcoreUtil.copy(mPregnancy);
	}

	/**
	 * <div class="en">Gets the calculated due date</div><div class="de">Gibt
	 * den errechneten Geburtstermin zurück.</div>
	 *
	 * @return the estimated birthdate (due date)
	 */
	public String getEstimatedBirthdate() {

		if (mPregnancy.getValues().size() > 0) {
			final TS ts = (TS) copyMdhtPregnancy().getValues().get(0);
			return DateUtilMdht.parseDateToStr(ts);
		} else
			return null;
	}

	/**
	 * Gets the mdht pregnancy.
	 *
	 * @return the mdht pregnancy
	 */
	public PregnancyObservation getMdhtPregnancy() {
		return mPregnancy;
	}

	/**
	 * <div class="en">sets the calculated due date</div><div class="de">Setzt
	 * den errechneten Geburtstermin</div>
	 *
	 * @param estimatedBirdDate
	 *            the new estimated birth date
	 */
	public void setEstimatedBirthDate(Date estimatedBirdDate) {
		final TS ts = DateUtilMdht.ts(estimatedBirdDate);
		mPregnancy.getValues().add(ts);
	}

	protected abstract void setInternalId(String id);
}
