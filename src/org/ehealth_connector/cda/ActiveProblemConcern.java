/*******************************************************************************
*
* The authorship of this code and the accompanying materials is held by medshare GmbH, Switzerland.
* All rights reserved. http://medshare.net
*
* Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
*
* This code is are made available under the terms of the Eclipse Public License v1.0.
*
* Accompanying materials are made available under the terms of the Creative Commons
* Attribution-ShareAlike 4.0 License.
*
* Year of publication: 2015
*
*******************************************************************************/
package org.ehealth_connector.cda;

import java.util.Date;

import org.ehealth_connector.cda.ch.enums.ProblemConcernStatusCode;

/**
 * <div class="de">Dieses Element bezeichnet das Leiden und dokumentiert die
 * Zeitspanne, in welcher das Leiden für den Patienten aktuell ist. Es enthält
 * zudem eine Sammlung von Verweisen auf Probleme, die zu diesem Leiden gehören.
 * Im Zusammenhang mit Impfungen MUSS für Komplikations- und Expositionsrisiken
 * je ein Problem Concern Entry angegeben werden.</div> <div class="fr">Une
 * souffrance de la santé</div>.
 */
public class ActiveProblemConcern extends ProblemConcern {

	/**
	 * Instantiates a new active problem concern entry.
	 */
	public ActiveProblemConcern() {
		super();
	}

	/**
	 * <div class="de">Erzeugt ein Objekt welches ein Leiden repräsentiert.
	 * Dieses Objekt kann einer ActiveProblemsSection hinzugefügt werden.</div>
	 * 
	 * <div class="fr">Crée un objet qui représente un problème. L'objet peut
	 * être ajouté dans ActiveProblemsSection.</div>
	 * 
	 * @param problemConcernEntry
	 *            <div class="de">Vorbestehendes Objekt, das geklont werden
	 *            soll</div>
	 * 
	 *            <div class="fr">Objet préexistante à cloner</div>
	 * 
	 */
	public ActiveProblemConcern(
			org.openhealthtools.mdht.uml.cda.ihe.ProblemConcernEntry problemConcernEntry) {
		super(problemConcernEntry);
	}

	/**
	 * <div class="de">Erzeugt ein Objekt welches ein Leiden repräsentiert.
	 * Dieses Objekt kann einer ActiveProblemsSection hinzugefügt werden.</div>
	 * 
	 * <div class="fr">Crée un objet qui représente un problème. L'objet peut
	 * être ajouté dans ActiveProblemsSection.</div>
	 *
	 * @param concern
	 *            <div class="de">Die Bezeichnung des Leidens (Freitext)</div>
	 *            <div class="fr">Le nom du problème (texte libre)</div>
	 * @param begin
	 *            <div class="de">Beginn des Leidens</div> <div class="fr">Le
	 *            début du problème</div>
	 * @param end
	 *            <div class="de">Ende des Leidens</div> <div class="fr">Le fin
	 *            du problème</div>
	 * @param problemEntry
	 * <br>
	 *            <div class="de">Das medizinische Problem</div> <div
	 *            class="fr"> problem entry</div> <div class="it"> problem
	 *            entry</div>
	 * @param problemConcernStatus
	 * <br>
	 *            <div class="de">Status des Leidens</div> <div class="fr">
	 *            problem concern status</div> <div class="it"> problem concern
	 *            status</div>
	 */
	public ActiveProblemConcern(String concern, Date begin, Date end,
			Problem problemEntry, ProblemConcernStatusCode problemConcernStatus) {
		super(concern, problemEntry, problemConcernStatus, begin, end);
	}

	/**
	 * Instantiates a new active problem concern entry.
	 *
	 * @param concern
	 *            <div class="de">Die Bezeichnung des Leidens (Freitext)</div>
	 *            <div class="fr">Le nom du problème (texte libre)</div> <div
	 *            class="it"> string</div>
	 * @param date
	 * <br>
	 *            <div class="de"> date</div> <div class="fr"> date</div> <div
	 *            class="it"> date</div>
	 * @param problemEntry
	 * <br>
	 *            <div class="de">Das medizinische Problem</div> <div
	 *            class="fr"> problem entry</div> <div class="it"> problem
	 *            entry</div>
	 * @param problemStatusCode
	 * <br>
	 *            <div class="de">Status des Leidens</div> <div class="fr">
	 *            problem status code</div> <div class="it"> problem status
	 *            code</div>
	 */
	public ActiveProblemConcern(String concern, Date date,
			Problem problemEntry, ProblemConcernStatusCode problemStatusCode) {
		this(concern, date, null, problemEntry, problemStatusCode);
	}

	/**
	 * <div class="de">Erzeugt ein Objekt welches ein Leiden repräsentiert.
	 * Dieses Objekt kann einer ActiveProblemsSection hinzugefügt werden.</div>
	 * 
	 * <div class="fr">Crée un objet qui représente un problème. L'objet peut
	 * être ajouté dans ActiveProblemsSection.</div>
	 *
	 * @param concern
	 *            <div class="de">Die Bezeichnung des Leidens (Freitext)</div>
	 *            <div class="fr">Le nom du problème (texte libre)</div>
	 * @param problemEntry
	 * <br>
	 *            <div class="de">Das medizinische Problem</div> <div
	 *            class="fr"> problem entry</div> <div class="it"> problem
	 *            entry</div>
	 * @param concernStatus
	 *            <div class="de">Der Status Code des Leidens
	 *            (active/suspended/aborted/completed)</div> <div class="fr">Le
	 *            statut du problème (active/suspended/aborted/completed)</div>
	 */
	public ActiveProblemConcern(String concern, Problem problemEntry,
			ProblemConcernStatusCode concernStatus) {
		super(concern, problemEntry, concernStatus);
	}
}