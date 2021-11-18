/*
 * This code is made available under the terms of the Eclipse Public License v1.0 
 * in the github project https://github.com/project-husky/husky there you also 
 * find a list of the contributors and the license information.
 * 
 * This project has been developed further and modified by the joined working group Husky 
 * on the basis of the eHealth Connector opensource project from June 28, 2021, 
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 *
 */

package org.husky.common.model;

import java.util.ArrayList;
import java.util.List;

import org.husky.common.hl7cdar2.POCDMT000040Person;

/**
 * Eine Person (z.B. Arzt, Datenerfasser, Angehörige, ...)
 */
public class Person {

	/**
	 * The CDA person.
	 */
	private POCDMT000040Person mPerson;

	/**
	 * <div class="en">Instantiates a new person.</div>
	 * <div class="de">Instantiiert ein neues Personen Objekt </div>
	 * <div class="fr"></div> <div class="it"></div>
	 */
	public Person() {
		mPerson = new POCDMT000040Person();
	}

	/**
	 * Erstellt eine neue Person (Dieser Konstruktor wird oft gebraucht für
	 * Behandelnde).
	 *
	 * @param name
	 *            <br>
	 *            <div class="de"> name</div> <div class="fr"></div>
	 *            <div class="it"></div>
	 */
	public Person(Name name) {
		mPerson = new POCDMT000040Person();
		// Create and fill Person Name
		mPerson.getName().add(name.getHl7CdaR2Pn());
	}

	/**
	 * <div class="en">Instantiates a new person.</div>
	 * <div class="de">Instantiiert eine neues Personen Objekt </div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @param person
	 *            <br>
	 *            <div class="de"> person</div> <div class="fr"></div>
	 *            <div class="it"></div>
	 */
	public Person(POCDMT000040Person person) {
		mPerson = person;
	}

	/**
	 * <div class="en">Adds the name.</div> <div class="de"></div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @param name
	 *            name
	 */
	public void addName(Name name) {
		mPerson.getName().add(name.getHl7CdaR2Pn());
	}

	/**
	 * <div class="en">Gets the complete name.</div> <div class="de">Liefert den
	 * ganzen Namen</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the complete name</div>
	 */
	public String getCompleteName() {
		var retVal = "";
		if (mPerson.getName() != null && !mPerson.getName().isEmpty()) {
			final var name = new Name(mPerson.getName().get(0));
			retVal = name.getFullName();
		}
		return retVal;
	}

	/**
	 * <div class="en">Gets the mdht person.</div> <div class="de">Liefert mdht
	 * person.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return org.openhealthtools.mdht.uml.cda.Person <div class="en">the mdht
	 *         person</div>
	 */
	public POCDMT000040Person getHl7CdaPerson() {
		return mPerson;
	}

	/**
	 * <div class="en">Gets the (first) name.</div> <div class="de">Liefert den
	 * (ersten) Namen.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the name</div>
	 */
	public Name getName() {
		return new Name(mPerson.getName().get(0));
	}

	/**
	 * <div class="en">Gets the names.</div> <div class="de">Liefert alle
	 * Namen.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the names</div>
	 */
	public List<Name> getNames() {
		final List<Name> nl = new ArrayList<>();
		for (org.husky.common.hl7cdar2.PN mName : mPerson.getName()) {
			nl.add(new Name(mName));
		}
		return nl;
	}

	public org.openehealth.ipf.commons.ihe.xds.core.metadata.Person getIpfPerson() {
		var person = new org.openehealth.ipf.commons.ihe.xds.core.metadata.Person();

		if(mPerson.getName() != null && !mPerson.getName().isEmpty()) {
			person.setName(Name.getIpfXpnName(mPerson.getName().get(0)));
		}
		
		return person;
	}

}