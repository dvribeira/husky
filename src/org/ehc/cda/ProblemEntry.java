/*******************************************************************************
 *
 * The authorship of this code and the accompanying materials is held by
 * medshare GmbH, Switzerland. All rights reserved.
 * http://medshare.net
 *
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
 *
 * This code is are made available under the terms of the
 * Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the
 * Creative Commons Attribution-ShareAlike 3.0 Switzerland License.
 *
 * Year of publication: 2014
 *
 *******************************************************************************/

package org.ehc.cda;

import java.text.ParseException;
import java.util.Date;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ehc.common.Code;
import org.ehc.common.DateUtil;
import org.ehc.common.Util;
import org.ehc.common.Value;
import org.openhealthtools.mdht.uml.cda.Observation;
import org.openhealthtools.mdht.uml.cda.ihe.IHEFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.CD;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.datatypes.IVL_TS;
import org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor;

/**
 * Ein medizinisches Problem
 */
public class ProblemEntry {

	public org.openhealthtools.mdht.uml.cda.ihe.ProblemEntry mProblemEntry;

	/**
	 * @param problemEntry
	 */
	public ProblemEntry(org.openhealthtools.mdht.uml.cda.ihe.ProblemEntry problemEntry) {
		mProblemEntry = problemEntry;
	}
	
	/**
	 * @param observation
	 */
	public ProblemEntry(Observation observation) {
		mProblemEntry = (org.openhealthtools.mdht.uml.cda.ihe.ProblemEntry) observation;
	}

	public ProblemEntry() {
		mProblemEntry = IHEFactory.eINSTANCE.createProblemEntry().init();
		setNotOccured(false);
	}
	
	/**
	 * Erzeugt ein Objekt welches ein Problem repräsentiert. 
	 * Dieser Konstruktor wird verwendet, wenn der Zeitraum in dem das Problem bestand unbekannt ist, das Problem als Code angegeben werden soll.
	 * Dieses Objekt kann einem ProblemConcernEntry hinzugefügt werden.
	 * 
	 * @param problemNotOccured
	 *            Normalerweise false. Ist ein Problem nicht aufgetreten: true.
	 * @param startOfProblem
	 *            Beginn des Problems
	 * @param endOfProblem
	 *            Ende des Problems
	 * @param problem
	 *            Freitextbeschreibung zu dem Problem oder Code zu
	 *            Komplikationsrisiken oder Expositionsrisiken.
	 * @param internalProblemId
	 *            Interne ID des Problems innerhalb der Akte. Steht eine solche nicht zur Verfügung dann kann ein anderer Konstruktor verwendet werden und es wird stattdesssen eine GUID durch die Convenience API generiert.
	 */
	public ProblemEntry(org.ehc.common.Code problem) {
		mProblemEntry = IHEFactory.eINSTANCE.createProblemEntry().init();
		mProblemEntry.setEffectiveTime(DateUtil.createUnknownLowHighTimeNullFlavor());
		setNotOccured(false);
		setCode(problem);
		setId(null);
		CD cd = DatatypesFactory.eINSTANCE.createCD();
		cd.setNullFlavor(org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor.UNK);
		mProblemEntry.getValues().add(cd);
	}

	public ProblemEntry(Value value, Date date) {
		this(value, date, null);
	}
	
	public ProblemEntry(Code value, Date date) {
		this(value, date, null);
	}

	/**
	 * Erzeugt ein Objekt welches ein Problem repräsentiert. 
	 * Dieser Konstruktor wird verwendet, wenn der Zeitraum in dem das Problem bestand bekannt ist und das Problem als Code angegeben werden soll.
	 * Dieses Objekt kann einem ProblemConcernEntry hinzugefügt werden.
	 * @param problem
	 *            Freitextbeschreibung zu dem Problem oder Code zu
	 *            Komplikationsrisiken oder Expositionsrisiken.
	 * @param start
	 *            Beginn des Problems
	 * @param end
	 *            Ende des Problems
	 */
	public ProblemEntry(org.ehc.common.Code problem,
			Date start, Date end) {
		this(new Value(problem), start, end, null);
	}
	
	/**
	 * Erzeugt ein Objekt welches ein Problem repräsentiert. 
	 * Dieser Konstruktor wird verwendet, wenn der Zeitraum in dem das Problem bestand bekannt ist und das Problem als Code angegeben werden soll.
	 * Dieses Objekt kann einem ProblemConcernEntry hinzugefügt werden.
	 * @param problem
	 *            Freitextbeschreibung zu dem Problem oder Code zu
	 *            Komplikationsrisiken oder Expositionsrisiken.
	 * @param start
	 *            Beginn des Problems
	 * @param end
	 *            Ende des Problems
	 */
	public ProblemEntry(org.ehc.common.Value problem,
			Date start, Date end) {
		this(problem, start, end, null);
	}

	/**
	 * Erzeugt ein Objekt welches ein Problem repräsentiert. 
	 * Dieser Konstruktor wird verwendet, wenn der Zeitraum in dem das Problem bestand bekannt ist und das Problem als Code angegeben werden soll.
	 * Dieses Objekt kann einem ProblemConcernEntry hinzugefügt werden.
	 * @param problem
	 *            Freitextbeschreibung zu dem Problem oder Code zu
	 *            Komplikationsrisiken oder Expositionsrisiken.
	 * @param startOfProblem
	 *            Beginn des Problems
	 * @param endOfProblem
	 *            Ende des Problems
	 * @param internalProblemId
	 *            Interne ID des Problems innerhalb der Akte. Steht eine solche nicht zur Verfügung dann kann ein anderer Konstruktor verwendet werden und es wird stattdesssen eine GUID durch die Convenience API generiert.
	 */
	protected ProblemEntry(org.ehc.common.Value problem,
			Date startOfProblem, Date endOfProblem, String internalProblemId) {
		mProblemEntry = IHEFactory.eINSTANCE.createProblemEntry().init();
		setNotOccured(false);
		try {
			setStart(startOfProblem);
			setEnd(endOfProblem);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		setId(internalProblemId);
		setValue(problem);
	}

	public ProblemEntry(Value problemValue, Code problemCode,
			Date startOfProblem, Date endOfProblem) {
		this(problemCode, startOfProblem, endOfProblem);
		this.setValue(problemValue);
	}

	public org.openhealthtools.mdht.uml.cda.ihe.ProblemEntry copyMdhtProblemEntry() {
		return EcoreUtil.copy(mProblemEntry);
	}
	
	public org.openhealthtools.mdht.uml.cda.ihe.ProblemEntry getMdhtProblemEntry() {
		return mProblemEntry;
	}

	/**
	 * @return das codedProblem Objekt
	 */
	public org.ehc.common.Code getCode() {
		org.ehc.common.Code code = new org.ehc.common.Code(mProblemEntry.getCode());
		return code;
	}

	/**
	 * @return das endOfProblem Objekt
	 */
	public String getEnd() {
		return Util.createEurDateStrFromTS(mProblemEntry
				.getEffectiveTime().getHigh().getValue());
	}

	/**
	 * @return das startOfProblem Objekt
	 */
	public String getStart() {
		return Util.createEurDateStrFromTS(mProblemEntry
				.getEffectiveTime().getLow().getValue());
	}

	/**
	 * The Value may be a coded or uncoded String. 
	 * @return the problem value as string.
	 */
	public Value getValue() { 
		Value value = new Value(mProblemEntry.getValues().get(0));
		return value;
	}

	/**
	 * @return das problemNotOccured Objekt
	 */
	public boolean getProblemNotOccured() {
		return mProblemEntry.getNegationInd();
	}

	public void setId(String id) {
		II ii = Util.createUuidVacd(id);
		mProblemEntry.getIds().add(ii);
	}

	/**
	 * @param codedProblem
	 *            das codedProblem Objekt welches gesetzt wird
	 */
	public void setCode(org.ehc.common.Code codedProblem) {
		mProblemEntry.setCode(codedProblem.getCD());
	}

	/**
	 * @param endOfProblem
	 *            das endOfProblem Objekt welches gesetzt wird
	 * @throws ParseException
	 */
	public void setEnd(Date endOfProblem) throws ParseException {
		mProblemEntry.getEffectiveTime().setHigh(
				DateUtil.createIVXB_TSFromDate(endOfProblem));
	}

	/**
	 * @param problemNotOccured
	 *            das problemNotOccured Objekt welches gesetzt wird
	 */
	public void setNotOccured(boolean problemNotOccured) {
		mProblemEntry.setNegationInd(problemNotOccured);
	}

	/**
	 * @param startOfProblem
	 *            das startOfProblem Objekt welches gesetzt wird
	 * @throws ParseException
	 */
	public void setStart(Date startOfProblem) throws ParseException {
		if (mProblemEntry.getEffectiveTime() == null) {
			IVL_TS interval = DatatypesFactory.eINSTANCE.createIVL_TS();
			mProblemEntry.setEffectiveTime(interval);
		}
		mProblemEntry.getEffectiveTime().setLow(
				DateUtil.createIVXB_TSFromDate(startOfProblem));
	}

	public void setValue(Value problemValue) {
		if (problemValue == null) {
			CD cd = DatatypesFactory.eINSTANCE.createCD();
			cd.setNullFlavor(NullFlavor.UNK);
			mProblemEntry.getValues().add(cd);
		}
		else {
			mProblemEntry.getValues().add(problemValue.getValue());
		}
	}
}
