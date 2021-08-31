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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ehealth_connector.common.mdht.enums.ValueSetEnumInterface;
import org.ehealth_connector.common.utils.Util;
import org.openhealthtools.mdht.uml.hl7.datatypes.CD;
import org.openhealthtools.mdht.uml.hl7.datatypes.CE;
import org.openhealthtools.mdht.uml.hl7.datatypes.CS;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.ED;
import org.openhealthtools.mdht.uml.hl7.datatypes.TEL;
import org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor;

/**
 * <div class="en">Class Code. A Code consists of the code itself and an
 * according code system.</div> <div class="de">Ein Code bestehend aus
 * eigentlichem Code und dem zugehörigen Codesystem</div> (OID).
 */
public class Code {

	private CD mCD;

	/**
	 * <div class="en">Instantiates a new code.</div>
	 * <div class="de">Instantiiert ein neues Code Objekt</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 */
	public Code() {
		mCD = DatatypesFactory.eINSTANCE.createCD();
	}

	/**
	 * <div class="en">Instantiates a new code.</div>
	 * <div class="de">Instantiiert ein neues Code Objekt</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @param code
	 *            <br>
	 *            <div class="de"> code</div> <div class="fr"></div>
	 *            <div class="it"></div>
	 */
	public Code(CD code) {
		mCD = code;
	}

	/**
	 * <div class="en">Instantiates a new code.</div>
	 * <div class="de">Instantiiert ein neues Code Objekt</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @param code
	 *            <br>
	 *            <div class="de"> code</div> <div class="fr"></div>
	 *            <div class="it"></div>
	 */
	public Code(Code code) {
		this(code.getCodeSystem(), code.getCode(), code.getDisplayName());
	}

	/**
	 * <div class="en">Instantiates a new code.</div>
	 * <div class="de">Instantiiert ein neues Code Objekt</div>
	 *
	 * @param codeSystem
	 *            <div class="en">OID of the according code system</div>
	 *            <div class="de">Die OID des zugehörigen Codesystems</div>
	 *            <div class="it"></div>
	 *
	 * @param code
	 *            the code
	 */
	public Code(IdentityDomain codeSystem, String code) {
		mCD = DatatypesFactory.eINSTANCE.createCD();
		mCD.setCodeSystem(codeSystem.getCodeSystemId());
		mCD.setCodeSystemName(codeSystem.getCodeSystemName());
		mCD.setCode(code);
	}

	/**
	 * <div class="en">Instantiates a new code.</div>
	 * <div class="de">Instantiiert ein neues Code Objekt</div>
	 *
	 * @param nullFlavor
	 *            can hold a reason why the code is null
	 */
	public Code(org.ehealth_connector.common.mdht.enums.NullFlavor nullFlavor) {
		mCD = DatatypesFactory.eINSTANCE.createCD();
		mCD.setNullFlavor(NullFlavor.getByName(nullFlavor.getCodeValue()));
	}

	/**
	 * <div class="en">Instantiates a new code.</div>
	 * <div class="de">Instantiiert ein neues Code Objekt</div>
	 *
	 * @param oid
	 *            <div class="en">OID of the according code system</div>
	 *            <div class="de">Die OID des zugehörigen Codesystems</div>
	 *            <div class="it"></div>
	 * @param code
	 *            the code
	 */
	public Code(String oid, String code) {
		mCD = DatatypesFactory.eINSTANCE.createCD();
		mCD.setCodeSystem(oid);
		mCD.setCode(code);
	}

	/**
	 * <div class="en">Instantiates a new code.</div>
	 * <div class="de">Instantiiert ein neues Code Objekt</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @param codeSystem
	 *            <br>
	 *            <div class="en">code system</div><div class="de"> code
	 *            system</div> <div class="fr"></div> <div class="it"></div>
	 * @param code
	 *            <br>
	 *            <div class="en">the code</div><div class="de"> code</div>
	 *            <div class="fr"></div> <div class="it"></div>
	 * @param displayName
	 *            <br>
	 *            <div class="en">the display name</div><div class="de"> display
	 *            name</div> <div class="fr"></div> <div class="it"></div>
	 */
	public Code(String codeSystem, String code, String displayName) {
		this(codeSystem, code);
		mCD.setDisplayName(displayName);
	}

	/**
	 * <div class="en">Instantiates a new code.</div>
	 * <div class="de">Instantiiert ein neues Code Objekt</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @param codeSystem
	 *            <br>
	 *            <div class="en">code system</div><div class="de"> code
	 *            system</div> <div class="fr"></div> <div class="it"></div>
	 * @param code
	 *            <br>
	 *            <div class="en"> code</div> <div class="fr"></div>
	 *            <div class="it"></div>
	 * @param codeSystemName
	 *            <br>
	 *            <div class="en"> code system name</div> <div class="fr"></div>
	 *            <div class="it"></div>
	 * @param displayName
	 *            <br>
	 *            <div class="en"> display name</div> <div class="fr"></div>
	 *            <div class="it"></div>
	 */
	public Code(String codeSystem, String code, String codeSystemName, String displayName) {
		this(codeSystem, code, displayName);
		mCD.setCodeSystemName(codeSystemName);
	}

	/**
	 * Instantiates a new code based on an enum value.
	 *
	 * @param enumValue
	 *            the enum value
	 */
	public Code(ValueSetEnumInterface enumValue) {
		this(enumValue.getCodeSystemId(), enumValue.getCodeValue(), enumValue.getDisplayName());
		if (enumValue.getCodeSystemName() != null)
			if (!"".equals(enumValue.getCodeSystemName()))
				mCD.setCodeSystemName(enumValue.getCodeSystemName());
	}

	/**
	 * Adds a translation to the code object
	 *
	 * @param code
	 *            the code
	 */
	public void addTranslation(Code code) {
		if (code != null) {
			mCD.getTranslations().add(code.getCD());
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Code)) {
			return false; // different class
		}
		final Code other = (Code) obj;
		if (this.mCD == other.mCD)
			return true;

		if (this.mCD.getCode() == null) {
			if (other.mCD.getCode() != null)
				return false;
		} else if (!this.mCD.getCode().equals(other.mCD.getCode()))
			return false;

		if (this.mCD.getCodeSystemVersion() == null) {
			if (other.mCD.getCodeSystemVersion() != null)
				return false;
		} else if (!this.mCD.getCodeSystemVersion().equals(other.mCD.getCodeSystemVersion()))
			return false;

		if (this.mCD.getNullFlavor().getName() == null) {
			if (other.mCD.getNullFlavor().getName() != null)
				return false;
		} else if (!this.mCD.getNullFlavor().getName().equals(other.mCD.getNullFlavor().getName()))
			return false;

		if (this.mCD.getNullFlavor().getValue() == 0) {
			if (other.mCD.getNullFlavor().getValue() != 0)
				return false;
		} else if (!(this.mCD.getNullFlavor().getValue() == other.mCD.getNullFlavor().getValue()))
			return false;

		if (this.mCD.getTranslations() == null) {
			if (other.mCD.getTranslations() != null)
				return false;
		} else if (!this.mCD.getTranslations().equals(other.mCD.getTranslations()))
			return false;

		return true;
	}

	/**
	 * <div class="en">Gets the CD MDHT Object</div> <div class="de">Liefert das
	 * CD MDHT Objekt</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return <div class="en">the cd</div>
	 */
	public CD getCD() {
		final CD cd = DatatypesFactory.eINSTANCE.createCD();
		final CD mCD2 = EcoreUtil.copy(mCD);
		if (mCD2.getCodeSystem() != null) {
			cd.setCodeSystem(mCD2.getCodeSystem());
		}
		if (mCD2.getCode() != null) {
			cd.setCode(mCD2.getCode());
		}
		if (mCD2.getCodeSystemName() != null) {
			cd.setCodeSystemName(mCD2.getCodeSystemName());
		}
		if (mCD2.getDisplayName() != null) {
			cd.setDisplayName(mCD2.getDisplayName());
		}
		if (mCD2.getCodeSystemVersion() != null) {
			cd.setCodeSystemVersion(mCD2.getCodeSystemVersion());
		}
		if (mCD2.isNullFlavorDefined()) {
			cd.setNullFlavor(mCD2.getNullFlavor());
		}
		if (mCD2.getOriginalText() != null) {
			cd.setOriginalText(mCD2.getOriginalText());
		}
		for (final CD translation : mCD2.getTranslations()) {
			cd.getTranslations().add(EcoreUtil.copy(translation));
		}
		return cd;
	}

	/**
	 * <div class="en">Gets the code as MDHT CE Object</div>
	 * <div class="de">Liefert das MDHT CD Objekt</div> <div class="fr"></div>
	 * <div class="it"></div>
	 *
	 * @return the code as CE object
	 */
	public CE getCE() {
		final CE ce = DatatypesFactory.eINSTANCE.createCE();
		final CD mCD2 = EcoreUtil.copy(mCD);
		if (mCD2.getCodeSystem() != null) {
			ce.setCodeSystem(mCD2.getCodeSystem());
		}
		if (mCD2.getCode() != null) {
			ce.setCode(mCD2.getCode());
		}
		if (mCD2.getCodeSystemName() != null) {
			ce.setCodeSystemName(mCD2.getCodeSystemName());
		}
		if (mCD2.getDisplayName() != null) {
			ce.setDisplayName(mCD2.getDisplayName());
		}
		if (mCD2.getCodeSystemVersion() != null) {
			ce.setCodeSystemVersion(mCD2.getCodeSystemVersion());
		}
		if (mCD2.isNullFlavorDefined()) {
			ce.setNullFlavor(mCD2.getNullFlavor());
		}
		if (mCD2.getOriginalText() != null) {
			ce.setOriginalText(mCD2.getOriginalText());
		}
		for (final CD translation : mCD2.getTranslations()) {
			ce.getTranslations().clear();
			ce.getTranslations().add(EcoreUtil.copy(translation));
		}
		return ce;
	}

	/**
	 * <div class="en">Gets the code.</div> <div class="de">Liefert code.</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 * @return the code object
	 */
	public String getCode() {
		String codeStr = "";
		if (mCD != null) {
			if (mCD.getCode() == null) {
				if (mCD.getNullFlavor() != null) {
					mCD.getNullFlavor().getLiteral();
				}
			} else {
				codeStr = mCD.getCode();
			}
		}
		return codeStr;
	}

	/**
	 * <div class="en">Gets the code system.</div> <div class="de">Liefert code
	 * system.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return das oid Objekt
	 */
	public String getCodeSystem() {
		String codeStr = "";
		if (mCD.getCodeSystem() == null) {
			if (mCD.getNullFlavor() != null) {
				mCD.getNullFlavor().getLiteral();
			}
		} else {
			codeStr = mCD.getCodeSystem();
		}
		return codeStr;
	}

	/**
	 * Gets the name of the code system
	 *
	 * @return the name of the code system. null, if it wasn´t set.
	 */
	public String getCodeSystemName() {
		if (mCD.getCodeSystemName() != null) {
			return mCD.getCodeSystemName();
		} else {
			return null;
		}
	}

	/**
	 * <div class="en">Gets the code as MDHT CE Object</div>
	 * <div class="de">Liefert das MDHT CD Objekt</div> <div class="fr"></div>
	 * <div class="it"></div>
	 *
	 * @return the code as CE object
	 */
	public CS getCS() {
		final CS ce = DatatypesFactory.eINSTANCE.createCS();
		final CD mCD2 = EcoreUtil.copy(mCD);
		if (mCD2.getCode() != null) {
			ce.setCode(mCD2.getCode());
		}
		if (mCD2.isNullFlavorDefined()) {
			ce.setNullFlavor(mCD2.getNullFlavor());
		}
		return ce;
	}

	/**
	 * <div class="en">Gets the display name.</div> <div class="de">Liefert
	 * display name.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 * @return String <div class="en">the display name</div>
	 */
	public String getDisplayName() {
		return mCD.getDisplayName();
	}

	/**
	 * <div class="en"> Gets the null flavor
	 *
	 * @return the null flavor </div> <div class="de"></div>
	 *         <div class="fr"></div>
	 */
	public NullFlavor getNullFlavor() {
		return mCD.getNullFlavor();
	}

	/**
	 * Returns the text content of the originalText element
	 *
	 * @return originalText
	 */
	public String getOriginalText() {
		String retVal = "";
		if (mCD.getOriginalText() != null)
			retVal = mCD.getOriginalText().getText();
		return retVal;
	}

	/**
	 * Returns a reference to human readable SectionText of the OriginalText
	 * element
	 *
	 * @return reference
	 */
	public String getOriginalTextReference() {
		final TEL tel = mCD.getOriginalText().getReference();
		return tel.getValue();
	}

	/**
	 * Gets a list of translations for the code object.
	 *
	 * @return an List, which contains all translation codes
	 */
	public List<Code> getTranslations() {
		final List<Code> cl = new ArrayList<>();
		for (final CD cd : mCD.getTranslations()) {
			cl.add(new Code(cd));
		}
		return cl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;

		if (mCD == null)
			return prime;
		int result = 1;
		result = (prime * result) + (mCD.getCode() != null ? mCD.getCode().hashCode() : 0);
		result = (prime * result)
				+ (mCD.getCodeSystem() != null ? mCD.getCodeSystem().hashCode() : 0);
		result = (prime * result)
				+ (mCD.getCodeSystemVersion() != null ? mCD.getCodeSystemVersion().hashCode() : 0);
		result = (prime * result)
				+ (mCD.getCodeSystemName() != null ? mCD.getCodeSystemName().hashCode() : 0);
		result = (prime * result)
				+ (mCD.getNullFlavor() != null ? mCD.getNullFlavor().getName().hashCode() : 0);
		result = (prime * result)
				+ (mCD.getNullFlavor() != null ? mCD.getNullFlavor().getValue() : 0);
		result = (prime * result)
				+ (mCD.getTranslations() != null ? mCD.getTranslations().hashCode() : 0);
		return result;
	}

	/**
	 * <div class="en">Returns true if the code has a null flavor
	 *
	 * @return true if the code has a null flavor; false otherwise </div>
	 *         <div class="de"></div> <div class="fr"></div>
	 */
	public boolean isNullFlavor() {
		return mCD.isNullFlavorDefined();
	}

	/**
	 * <div class="en">Sets the MDHT CD Object</div> <div class="de">Setzt das
	 * MDHT CD Objekt</div> <div class="fr"></div> <div class="it"></div>
	 *
	 *
	 * @param cd
	 *            <div class="en">the new cd</div> <div class="de">das neue
	 *            cd.</div> <div class="fr"></div> <div class="it"></div>
	 */
	public void setCD(CD cd) {
		mCD = cd;
	}

	/**
	 * <div class="en">Sets the code.</div> <div class="de">Setzt code.</div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 *
	 * @param code
	 *            the code object which will be set
	 */
	public void setCode(String code) {
		mCD.unsetNullFlavor();
		mCD.setCode(code);
	}

	/**
	 * <div class="en">Sets the code system.</div> <div class="de">Setzt code
	 * system.</div> <div class="fr"></div> <div class="it"></div>
	 *
	 *
	 * @param oid
	 *            the oid object which will be set
	 */
	public void setCodeSystem(String oid) {
		mCD.unsetNullFlavor();
		mCD.setCodeSystem(oid);
	}

	/**
	 * Sets the name of the code system
	 *
	 * @param codeSystemName
	 *            name of the code system.
	 */
	public void setCodeSystemName(String codeSystemName) {
		mCD.unsetNullFlavor();
		mCD.setCodeSystemName(codeSystemName);
	}

	/**
	 * <div class="en">Sets the display name.</div> <div class="de"></div>
	 * <div class="fr"></div> <div class="it"></div>
	 *
	 *
	 * @param displayName
	 *            the displayName which will be set
	 */
	public void setDisplayName(String displayName) {
		mCD.setDisplayName(displayName);
	}

	/**
	 * Sets the null flavor.
	 *
	 * @param nf
	 *            the new null flavor
	 */
	public void setNullFlavor(org.ehealth_connector.common.mdht.enums.NullFlavor nf) {
		NullFlavor mdhtNf = NullFlavor.UNK;
		switch (nf) {
		case ASKED_BUT_UNKNOWN:
			mdhtNf = NullFlavor.ASKU;
			break;
		case MASKED:
			mdhtNf = NullFlavor.MSK;
			break;
		case OTHER:
			mdhtNf = NullFlavor.OTH;
			break;
		case TEMPORARILY_UNAVAILABLE:
			mdhtNf = NullFlavor.NAV;
			break;
		default:
			mdhtNf = NullFlavor.UNK;
			break;
		}
		mCD.setNullFlavor(mdhtNf);
	}

	/**
	 * Sets the OriginalText element
	 */
	public void setOriginalText(String originalText) {
		setOriginalText(originalText, null);
	}

	/**
	 * Sets the OriginalText element including the reference to the narrative
	 * part
	 */
	public void setOriginalText(String originalText, String textReference) {
		ED ed = null;
		if (textReference != null) {
			if (!textReference.startsWith("#"))
				textReference = "#" + textReference;
			ed = Util.createReference(textReference, originalText);
		}
		if (ed == null) {
			ed = DatatypesFactory.eINSTANCE.createED();
			ed.addText(originalText);
		}
		mCD.setOriginalText(ed);
	}

	/**
	 * Sets a reference to human readable SectionText in the OriginalText
	 * element
	 *
	 * @param reference
	 *            the reference
	 */
	public void setOriginalTextReference(String reference) {
		mCD.setOriginalText(Util.createReference(reference));
	}
}