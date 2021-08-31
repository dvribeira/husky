/*
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://gitlab.com/ehealth-connector/api/wikis/Team/
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

package org.ehealth_connector.common.mdht;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ehealth_connector.common.enums.CodeSystems;
import org.ehealth_connector.common.mdht.enums.Isco08;
import org.ehealth_connector.common.mdht.enums.NullFlavor;
import org.ehealth_connector.common.utils.DateUtilMdht;
import org.ehealth_connector.common.utils.Util;
//import org.openhealthtools.ihe.xds.metadata.AuthorType;
import org.openhealthtools.mdht.uml.cda.CDAFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.AD;
import org.openhealthtools.mdht.uml.hl7.datatypes.CE;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.datatypes.IVL_TS;
import org.openhealthtools.mdht.uml.hl7.datatypes.PN;
import org.openhealthtools.mdht.uml.hl7.datatypes.TEL;
import org.openhealthtools.mdht.uml.hl7.datatypes.TS;

/**
 * Author
 */
public class Author {

	final public static Code FUNCTION_CODE_AUTHOR_PATIENT = new Code(NullFlavor.NOT_APPLICABLE);
	final public static Code FUNCTION_CODE_AUTHORDEVICE = new Code("2.16.756.5.30.2.1.1.1",
			"TASST");

	private org.openhealthtools.mdht.uml.cda.Author mAuthor;

	/**
	 * Instantiates a new author.
	 */
	public Author() {
		mAuthor = CDAFactory.eINSTANCE.createAuthor();
		org.openhealthtools.mdht.uml.cda.AssignedAuthor asAuthor = CDAFactory.eINSTANCE
				.createAssignedAuthor();
		org.openhealthtools.mdht.uml.cda.Person person = CDAFactory.eINSTANCE.createPerson();

		asAuthor.setAssignedPerson(person);
		mAuthor.setAssignedAuthor(asAuthor);

		// add functionCode and time
		mAuthor.setFunctionCode(Isco08.MEDICAL_DOCTORS.getCE());
		mAuthor.setTime(DateUtilMdht.nowAsTS());

		setTime(null);
	}

	/**
	 * Instantiates a new software author (e.g. a laboratory information system)
	 *
	 * @param device
	 *            the device
	 */
	public Author(AuthoringDevice device) {
		this();
		setAssignedAuthoringDevice(device);
	}

	/**
	 * Creates a null flavored author according to ELGA spec (necessary for
	 * PHARM DIS)
	 *
	 * @param nullFlavored
	 *            True
	 */
	public Author(boolean nullFlavored) {

		/*
		 * ELGA spec:
		 *
		 * <author nullFlavor="NA"> <time nullFlavor="NA"/> <assignedAuthor
		 * nullFlavor="NA"> <id nullFlavor="NA"/> </assignedAuthor> </author>
		 */

		mAuthor = CDAFactory.eINSTANCE.createAuthor();
		org.openhealthtools.mdht.uml.cda.AssignedAuthor asAuthor = CDAFactory.eINSTANCE
				.createAssignedAuthor();
		org.openhealthtools.mdht.uml.cda.Person person = null;

		asAuthor.setAssignedPerson(person);
		mAuthor.setAssignedAuthor(asAuthor);

		mAuthor.setNullFlavor(org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor.NA);
		mAuthor.getAssignedAuthor()
				.setNullFlavor(org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor.NA);
		final II ii = DatatypesFactory.eINSTANCE.createII();
		ii.setNullFlavor(org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor.NA);
		mAuthor.getAssignedAuthor().getIds().add(ii);
		final TS tsTime = DatatypesFactory.eINSTANCE.createTS();
		tsTime.setNullFlavor(org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor.NA);
		mAuthor.setTime(tsTime);
	}

	/**
	 * Instantiates a new author.
	 */
	public Author(Code functionCode) {
		mAuthor = CDAFactory.eINSTANCE.createAuthor();
		org.openhealthtools.mdht.uml.cda.AssignedAuthor asAuthor = CDAFactory.eINSTANCE
				.createAssignedAuthor();
		org.openhealthtools.mdht.uml.cda.Person person = CDAFactory.eINSTANCE.createPerson();

		asAuthor.setAssignedPerson(person);
		mAuthor.setAssignedAuthor(asAuthor);

		// add functionCode and time
		if (functionCode != null) {
			mAuthor.setFunctionCode(functionCode.getCE());
		}
		mAuthor.setTime(DateUtilMdht.nowAsTS());

		setTime(null);
	}

	/**
	 * Instantiates a new human author.
	 *
	 * @param name
	 *            the name
	 */
	public Author(Name name) {
		this();
		addName(name);
	}

	/**
	 * Instantiates a new author.
	 *
	 * @param name
	 *            Name
	 * @param gln
	 *            Global Location Number (GLN)
	 */
	public Author(Name name, String gln) {
		this(name);

		// Create and fill Person Name and GLN
		final II id = DatatypesFactory.eINSTANCE.createII();
		id.setRoot(CodeSystems.GLN.getCodeSystemId());
		id.setExtension(gln);

		mAuthor.getAssignedAuthor().getIds().add(id);
	}

	/**
	 * Erstellt ein eHealthconnector-Author Objekt mittels eines MDHT-Author
	 * Objekts.
	 *
	 * @param authorMdht
	 *            the MDHT Author Object
	 */
	public Author(org.openhealthtools.mdht.uml.cda.Author authorMdht) {
		mAuthor = authorMdht;
	}

	/**
	 * <div class="en"> Creates a new Author based on the given Organization
	 * object</div> <div class="de"> Erstellt einen neuen Autor der der
	 * übergebenen Organisation entspricht.</div>
	 *
	 * @param organizationAsAuthor
	 *            <div class="en"> this Organization is used as Author</div>
	 *            <div class="de"> diese Organisation wird als Autor
	 *            verwendet</div>
	 */
	public Author(Organization organizationAsAuthor) {
		mAuthor = CDAFactory.eINSTANCE.createAuthor();
		if (organizationAsAuthor.getMdhtOrganization() != null) {
			org.openhealthtools.mdht.uml.cda.AssignedAuthor asAuthor = Util
					.createAssignedAuthorFromOrganization(organizationAsAuthor);
			mAuthor.setAssignedAuthor(asAuthor);
		}
		setTime(null);
	}

	/**
	 * <div class="en"> Creates a new Author based on the given Patient
	 * object</div> <div class="de"> Erstellt einen neuen Autor der dem
	 * übergebenden Patienten entspricht.</div>
	 *
	 * @param patientAsAuthor
	 *            <div class="en"> this Patient is used as Author</div>
	 *            <div class="de"> dieser Patient wird als Autor verwendet</div>
	 *            <div class="fr"></div>
	 */
	public Author(Patient patientAsAuthor) {
		this();
		if (patientAsAuthor.getMdhtPerson() != null) {
			org.openhealthtools.mdht.uml.cda.Person person = patientAsAuthor.copyMdhtPerson();
			mAuthor.getAssignedAuthor().setAssignedPerson(person);
		}

		if (patientAsAuthor.getMdhtPatient().getNames() != null) {
			for (final Name name : patientAsAuthor.getNames()) {
				mAuthor.getAssignedAuthor().getAssignedPerson().getNames().add(name.copyMdhtPn());
			}
		}

		if ((patientAsAuthor.getIds() != null) && (patientAsAuthor.getIds().size() > 0)) {
			mAuthor.getAssignedAuthor().getIds()
					.addAll(patientAsAuthor.copyMdhtPatientRole().getIds());
		}
		this.setFunctionCodePatient();
	}

	/**
	 * Instantiates a new human author.
	 *
	 * @param person
	 *            the person
	 */
	public Author(Person person) {
		this();
		addName(person.getName());
	}

	/**
	 * Instantiates a new author.
	 *
	 * @param person
	 *            the person
	 * @param gln
	 *            Global Location Number (GLN)
	 */
	public Author(Person person, String gln) {
		this(person);

		// Create and fill Person Name and GLN
		final II id = DatatypesFactory.eINSTANCE.createII();
		id.setRoot(CodeSystems.GLN.getCodeSystemId());
		id.setExtension(gln);

		mAuthor.getAssignedAuthor().getIds().add(id);
	}

	/**
	 * Weist dem Autor eine Postadresse zu.
	 *
	 * @param address
	 *            Die Postadresse des Autors
	 */
	public void addAddress(Address address) {
		mAuthor.getAssignedAuthor().getAddrs().add(address.copyMdhtAdress());
	}

	/**
	 * Weist dem Autoren eine ID zu.
	 *
	 * @param identificator
	 *            Kombination von eigentlicher ID und der OID der verwaltenden
	 *            Domäne
	 */
	public void addId(Identificator identificator) {
		mAuthor.getAssignedAuthor().getIds().add(identificator.getIi());
	}

	/**
	 * <div class="en">Adds the name.</div> <div class="de"></div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @param name
	 *            name
	 */
	public void addName(Name name) {
		mAuthor.getAssignedAuthor().getAssignedPerson().getNames().add(name.getMdhtPn());
	}

	/**
	 * <div class="en">Copy mdht author.</div> <div class="de"></div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @return org.openhealthtools.mdht.uml.cda.author
	 */
	public org.openhealthtools.mdht.uml.cda.Author copyMdhtAuthor() {
		return EcoreUtil.copy(mAuthor);
	}

	/**
	 * <div class="en">Gets the address.</div> <div class="de">Liefert die
	 * (erste) Adresse.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the address</div>
	 */
	public Address getAddress() {
		final Address address = new Address(mAuthor.getAssignedAuthor().getAddrs().get(0));
		return address;
	}

	/**
	 * <div class="en">Gets the addresses.</div> <div class="de">Liefert alle
	 * Adressen.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the addresses</div>
	 */
	public List<Address> getAddresses() {
		final List<Address> al = new ArrayList<Address>();
		for (final AD mAddress : mAuthor.getAssignedAuthor().getAddrs()) {
			final Address address = new Address(mAddress);
			al.add(address);
		}
		return al;
	}

	/**
	 * Method to get
	 *
	 * @return the asAuthor
	 */
	public org.openhealthtools.mdht.uml.cda.AssignedAuthor getAsAuthor() {
		return mAuthor.getAssignedAuthor();
	}

	/**
	 * <div class="en">Gets the author mdht.</div> <div class="de">Liefert
	 * author mdht.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return org.openhealthtools.mdht.uml.cda.Author <div class="en">the
	 *         author mdht</div>
	 */
	public org.openhealthtools.mdht.uml.cda.Author getAuthorMdht() {
		return mAuthor;
	}

	/**
	 * <div class="en">Gets the complete name.</div> <div class="de">Liefert den
	 * ganzen Namen (z.B. "Dr. Allzeit Bereit der Dritte")</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the complete name</div>
	 */
	public String getCompleteName() {
		String retVal = "";
		// Search for the author name. If it isn´t there, try to use the
		// organisation name.
		if (mAuthor.getAssignedAuthor() != null) {
			if (mAuthor.getAssignedAuthor().getAssignedPerson() != null) {
				if (!mAuthor.getAssignedAuthor().getAssignedPerson().getNames().isEmpty()) {
					final Name name = new Name(
							mAuthor.getAssignedAuthor().getAssignedPerson().getNames().get(0));
					retVal = name.getCompleteName();
				}
			}
		}
		// If the Author has no name, try the represented organization
		if (retVal.equals("")) {
			if (mAuthor.getAssignedAuthor() != null) {
				if (mAuthor.getAssignedAuthor().getRepresentedOrganization() != null) {
					if (!mAuthor.getAssignedAuthor().getRepresentedOrganization().getNames()
							.isEmpty()) {
						final Name name = new Name(mAuthor.getAssignedAuthor()
								.getRepresentedOrganization().getNames().get(0));
						retVal = name.getCompleteName();
					}
				}
			}
		}
		// If the Represented Organization has no Name try the Authoring Device
		if (retVal.equals("")) {
			if ((mAuthor.getAssignedAuthor() != null)
					&& (mAuthor.getAssignedAuthor().getAssignedAuthoringDevice() != null)
					&& (mAuthor.getAssignedAuthor().getAssignedAuthoringDevice()
							.getSoftwareName() != null)) {
				return mAuthor.getAssignedAuthor().getAssignedAuthoringDevice().getSoftwareName()
						.getText();
			}
		}
		return retVal;

	}

	/**
	 * Gets the functionCode of the author
	 *
	 * @return the functionCode. Null, if not present.
	 */
	public Code getFunctionCode() {
		if (mAuthor.getFunctionCode() != null) {
			return new Code(mAuthor.getFunctionCode());
		}
		return null;
	}

	/**
	 * <div class="en">Gets the gln (identification of the author)</div>
	 * <div class="de">Liefert die GLN (ID des Autors)</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the gln</div>
	 */
	public String getGln() {
		if (!mAuthor.getAssignedAuthor().getIds().isEmpty()) {
			final Identificator gln = Identificator.getIdentificator(
					mAuthor.getAssignedAuthor().getIds(), CodeSystems.GLN.getCodeSystemId());
			return gln.getExtension();
		}
		return null;
	}

	/**
	 * <div class="en">Gets the gln as identificator.</div>
	 * <div class="de">Liefert gln (ID des Autoren) als Identificator
	 * Objekt.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the gln as identificator</div>
	 */
	public Identificator getGlnAsIdentificator() {
		if (!mAuthor.getAssignedAuthor().getIds().isEmpty()) {
			final II ii = mAuthor.getAssignedAuthor().getIds().get(0);
			final Identificator id = new Identificator(ii);
			return id;
		}
		return null;
	}

	/**
	 * <div class="en">Gets all ids of the author</div> <div class="de">Liefert
	 * alle IDs des Autoren</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the ids</div>
	 */
	public List<Identificator> getIds() {
		return Util.convertIds(mAuthor.getAssignedAuthor().getIds());
	}

	public EList<TEL> getMdhtTelecoms() {
		return mAuthor.getAssignedAuthor().getTelecoms();
	}

	/**
	 * <div class="en">Gets the (first) family name.</div>
	 * <div class="de">Liefert den (ersten) Nachnamen</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the name</div>
	 */
	public Name getName() {
		final Name name = new Name(
				mAuthor.getAssignedAuthor().getAssignedPerson().getNames().get(0));
		return name;
	}

	/**
	 * <div class="en">Gets the names.</div> <div class="de">Liefert alle
	 * Namen</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the names</div>
	 */
	public List<Name> getNames() {
		final List<Name> nl = new ArrayList<Name>();
		for (final PN mName : mAuthor.getAssignedAuthor().getAssignedPerson().getNames()) {
			final Name name = new Name(mName);
			nl.add(name);
		}
		return nl;
	}

	/**
	 * <div class="en">Gets the organization (RepresentedOrganization). This
	 * element can also be used as author represented organization, when a
	 * document is beeing sent (via XDS, XDM, XDR).</div>
	 * <div class="de">Liefert die Organisation (RepresentedOrganization), der
	 * der Autor angehört (z.B. ein Krankenhaus). Dieses Element kann auch als
	 * Author Represented Organization Attribut verwendet werden, wenn das
	 * Dokument per XDS, XDM oder XDR verschickt wird.</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 *
	 * @return organization <div class="en">the organization</div>
	 *         <div class="de">die Organisation</div> <div class="fr"></div>
	 *         <div class="it"></div>
	 */
	public Organization getOrganization() {
		final Organization o = new Organization(
				mAuthor.getAssignedAuthor().getRepresentedOrganization());
		return o;
	}

	/**
	 * Method to get
	 *
	 * @return the person
	 */
	public org.openhealthtools.mdht.uml.cda.Person getPerson() {
		return mAuthor.getAssignedAuthor().getAssignedPerson();
	}

	/**
	 * Gets the author function code. This element specifies in which role or
	 * function the author is related to this document (e.g. Code "40001" for
	 * "Apotheker"). The code can also be used as author role, when a document
	 * is beeing sent (via XDS, XDM, XDR).
	 *
	 * @return code the function code
	 */
	public Code getRoleFunction() {
		return new Code(mAuthor.getFunctionCode());
	}

	/**
	 * Sets the author speciality code. This element specifies the speciality
	 * ("Fachrichtung", e.g. "Fachärztin/Facharzt für Allgemeine Innere Medizin"
	 * ) of the author. The code can also be used as author speciality, when a
	 * document is beeing sent (via XDS, XDM, XDR).
	 *
	 * @return code the speciality code
	 */
	public Code getSpeciality() {
		return new Code(mAuthor.getAssignedAuthor().getCode());
	}

	/**
	 * <div class="en">Gets the telecoms.</div> <div class="de">Liefert
	 * telecoms.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return Telecoms <div class="en">the telecoms</div>
	 */
	public Telecoms getTelecoms() {
		return new Telecoms(mAuthor.getAssignedAuthor().getTelecoms());
	}

	/**
	 * <div class="en">Gets the author time. The author/time element represents
	 * the start time of the author’s participation in the creation of the
	 * clinical document </div> <div class="de">Liefert die Zeit für den Autor.
	 * Diese gibt den Startzeitpunkt an, an dem der Autor bei der Erstellung des
	 * Dokument mitgewirkt hat.</div> <div class="fr"></div>
	 * <div class="it"></div>
	 *
	 *
	 * @return date <div class="en">the start time of the participation as
	 *         string</div> <div class="de">den Startzeitpunkt der Partizipation
	 *         als String</div> <div class="fr"></div> <div class="it"></div>
	 */
	public Date getTimeAsDate() {
		if (mAuthor.getTime() != null) {
			return DateUtilMdht.parseDates(mAuthor.getTime().getValue());
		}
		return null;
	}

	/**
	 * <div class="en">Gets the author time. The author/time element represents
	 * the start time of the author’s participation in the creation of the
	 * clinical document </div> <div class="de">Liefert die Zeit für den Autor.
	 * Diese gibt den Startzeitpunkt an, an dem der Autor bei der Erstellung des
	 * Dokument mitgewirkt hat.</div> <div class="fr"></div>
	 * <div class="it"></div>
	 *
	 *
	 * @return date <div class="en">the start time of the participation as
	 *         string</div> <div class="de">den Startzeitpunkt der Partizipation
	 *         als String</div> <div class="fr"></div> <div class="it"></div>
	 */
	public IVL_TS getTimeAsIVL_TS() {
		IVL_TS retVal = null;
		if (mAuthor.getTime() != null) {
			try {
				retVal = DateUtilMdht.createIVL_TSFromHL7Date(mAuthor.getTime().getValue());
			} catch (final ParseException e) {
			}
		}
		return retVal;
	}

	/**
	 * <div class="en">Gets the author time. The author/time element represents
	 * the start time of the author’s participation in the creation of the
	 * clinical document </div> <div class="de">Liefert die Zeit für den Autor.
	 * Diese gibt den Startzeitpunkt an, an dem der Autor bei der Erstellung des
	 * Dokument mitgewirkt hat.</div> <div class="fr"></div>
	 * <div class="it"></div>
	 *
	 *
	 * @return date <div class="en">the start time of the participation as
	 *         string</div> <div class="de">den Startzeitpunkt der Partizipation
	 *         als String</div> <div class="fr"></div> <div class="it"></div>
	 */
	public String getTimeAsString() {
		if (mAuthor.getTime() != null) {
			return DateUtilMdht.parseDateToStr(mAuthor.getTime());
		}
		return null;
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see java.lang.Object#hashCode()
	// */
	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = (prime * result) + ((asAuthor == null) ? 0 :
	// asAuthor.hashCode());
	// result = (prime * result) + ((mAuthor == null) ? 0 : mAuthor.hashCode());
	// result = (prime * result) + ((person == null) ? 0 : person.hashCode());
	// return result;
	// }
	//
	/**
	 * Checks if is author patient.
	 *
	 * @return true, if is author patient
	 */
	public boolean isAuthorPatient() {
		final CE functionCode = mAuthor.getFunctionCode();
		if (functionCode != null) {
			return FUNCTION_CODE_AUTHOR_PATIENT.equals(new Code(functionCode));
		}
		return false;
	}

	/**
	 * Method to set
	 *
	 * @param asAuthor
	 *            the asAuthor to set
	 */
	public void setAsAuthor(org.openhealthtools.mdht.uml.cda.AssignedAuthor asAuthor) {
		mAuthor.setAssignedAuthor(asAuthor);
	}

	public void setAssignedAuthoringDevice(AuthoringDevice device) {
		if (mAuthor.getAssignedAuthor() == null) {
			mAuthor.setAssignedAuthor(CDAFactory.eINSTANCE.createAssignedAuthor());
		}
		mAuthor.getAssignedAuthor().setAssignedAuthoringDevice(device.copy());
		if (mAuthor.getAssignedAuthor().getAssignedPerson() != null) {
			mAuthor.getAssignedAuthor()
					.eUnset(mAuthor.getAssignedAuthor().getAssignedPerson().eContainingFeature());
		}
	}

	/**
	 * Method to set
	 *
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(org.openhealthtools.mdht.uml.cda.Author author) {
		mAuthor = author;
	}

	/**
	 * Sets the functionCode of the author
	 *
	 * @param code
	 *            the functionCode
	 */
	public void setFunctionCode(Code code) {
		if (code != null) {
			mAuthor.setFunctionCode(code.getCE());
		}
	}

	/**
	 * Indicates that the author is the patient itself.
	 *
	 */
	public void setFunctionCodePatient() {
		mAuthor.setFunctionCode(FUNCTION_CODE_AUTHOR_PATIENT.getCE());
	}

	/**
	 * <div class="en">Sets the gln.</div> <div class="de">Setzt die GLN (ID des
	 * Autoren).</div> <div class="fr"></div> <div class="it"></div>
	 *
	 *
	 * @param gln
	 *            <div class="en">the new gln. If null an Id with nullFlavor =
	 *            NA will be added.</div> <div class="de">Die neue gln. Bei null
	 *            wird eine Id mit dem NullFlavor = NA hinzugefügt.</div>
	 *            <div class="fr"></div> <div class="it"></div>
	 */
	public void setGln(String gln) {
		if (gln != null) {
			addId(new Identificator(CodeSystems.GLN.getCodeSystemId(), gln));
		} else {
			final II ii = DatatypesFactory.eINSTANCE.createII();
			ii.setNullFlavor(org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor.NA);
			mAuthor.getAssignedAuthor().getIds().add(ii);
		}
	}

	/**
	 * <div class="en">Sets the organization (RepresentedOrganization). The code
	 * can also be used as author role, when a document is beeing sent (via XDS,
	 * XDM, XDR).</div> <div class="de">Setzt eine Organisation
	 * (RepresentedOrganization), der der Autor angehört (z.B. ein Krankenhaus).
	 * Dieses Element kann auch als Author Represented Organization Attribut
	 * verwendet werden, wenn das Dokument per XDS, XDM oder XDR verschickt
	 * wird.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 *
	 * @param organization
	 *            <div class="en">the new organization</div> <div class="de">die
	 *            neue Organisation</div> <div class="fr"></div>
	 *            <div class="it"></div>
	 */
	public void setOrganization(Organization organization) {
		if (organization != null)
			mAuthor.getAssignedAuthor()
					.setRepresentedOrganization(organization.copyMdhtOrganization());
	}

	/**
	 * Sets the functionCode of the author
	 *
	 * @param codeSystem
	 *            the code system
	 * @param codeSystemame
	 *            the name of the code system
	 * @param originalText
	 *            the function code (free text)
	 */
	/**
	 * Sets the other function code.
	 *
	 * @param codeSystem
	 *            the code system OID.
	 * @param codeSystemName
	 *            the name of the code system.
	 * @param originalText
	 *            the original text (narrative text of the code).
	 */
	public void setOtherFunctionCode(String codeSystem, String codeSystemName,
			String originalText) {
		if (originalText != null) {
			final CE otherCode = DatatypesFactory.eINSTANCE.createCE();
			otherCode.setNullFlavor(org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor.OTH);
			if (codeSystem != null)
				otherCode.setCode(codeSystem);
			if (codeSystemName != null)
				otherCode.setCodeSystemName(codeSystemName);
			otherCode.setOriginalText(DatatypesFactory.eINSTANCE.createED(originalText));
			mAuthor.setFunctionCode(otherCode);
		}
	}

	/**
	 * Method to set
	 *
	 * @param person
	 *            the person to set
	 */
	public void setPerson(org.openhealthtools.mdht.uml.cda.Person person) {
		mAuthor.getAssignedAuthor().setAssignedPerson(person);
	}

	/**
	 * Sets the author function code. This element specifies in which role or
	 * function the author is related to this document (e.g. Code "40001" for
	 * "Apotheker"). The code can also be used as author role, when a document
	 * is beeing sent (via XDS, XDM, XDR).
	 *
	 * @param code
	 *            the function code
	 */
	public void setRoleFunction(Code code) {
		mAuthor.setFunctionCode(code.getCE());
	}

	/**
	 * Sets the author speciality code. This element specifies the speciality
	 * ("Fachrichtung", e.g. Code "50001" for "Fachärztin/Facharzt für
	 * Allgemeine Innere Medizin") of the author. The code can also be used as
	 * author speciality, when a document is beeing sent (via XDS, XDM, XDR).
	 *
	 * @param code
	 *            the speciality code
	 */
	public void setSpeciality(Code code) {
		mAuthor.getAssignedAuthor().setCode(code.getCE());
	}

	/**
	 * <div class="en">Sets the telecoms.</div> <div class="de">Setzt
	 * telecoms.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @param telecoms
	 *            <div class="en">the new telecoms</div> <div class="de">das
	 *            neue telecoms.</div> <div class="fr"></div>
	 *            <div class="it"></div>
	 */
	public void setTelecoms(Telecoms telecoms) {
		mAuthor.getAssignedAuthor().getTelecoms()
				.addAll(EcoreUtil.copyAll(telecoms.getMdhtTelecoms()));
	}

	/**
	 * <div class="en">Sets the author time. The author/time element represents
	 * the start time of the author’s participation in the creation of the
	 * clinical document </div> <div class="de">Setzt die Zeit für den Autor.
	 * Diese gibt den Startzeitpunkt an, an dem der Autor bei der Erstellung des
	 * Dokument mitgewirkt hat.</div> <div class="fr"></div>
	 * <div class="it"></div>
	 *
	 *
	 * @param date
	 *            <div class="en">the start time of the participation as
	 *            date</div> <div class="de">den Startzeitpunkt der
	 *            Partizipation als Date</div> <div class="fr"></div>
	 *            <div class="it"></div>
	 */
	public void setTime(Date date) {
		if (date != null) {
			try {
				if ("000000".equals(DateUtilMdht.createTimeTSFromEuroDate(date).getValue()))
					mAuthor.setTime(DateUtilMdht.createDateTSFromEuroDate(date));
				else
					mAuthor.setTime(DateUtilMdht.createFullTSFromEuroDate(date));
			} catch (ParseException e) {
			}
		} else {
			mAuthor.setTime(DateUtilMdht.convertDateToTsyyyyMMddHHmmssZZZZ(new Date()));
		}
	}

}