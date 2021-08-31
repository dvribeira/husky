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
package org.ehealth_connector.cda.ch.emed.v097.enums;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.processing.Generated;

import org.ehealth_connector.common.enums.CodeSystems;
import org.ehealth_connector.common.enums.LanguageCode;
import org.ehealth_connector.common.mdht.enums.ValueSetEnumInterface;

/**
 * <!-- @formatter:off -->
 * Enumeration of MaritalStatus values
 *
 * <div class="en"><p> <b>History description 2014-03-26: </b>Lock all vaue sets untouched since 2014-03-26 to trackingId 2014T1_2014_03_26</p><b>description: </b><p>The domestic partnership status of a person.</p><p> <i>Example:</i> Married, separated, divorced, widowed, common-law marriage.</p></div>
 * <div class="de">no designation found for language GERMAN</div>
 * <div class="fr">no designation found for language FRENCH</div>
 * <div class="it">no designation found for language ITALIAN</div>
 *
 * Identifier: 2.16.840.1.113883.1.11.12212
 * Effective date: 2014-03-26 00:00
 * Version: DEFN=UV=VO=1360-20160323
 * Status: FINAL
 * <!-- @formatter:on -->
 */
@Generated(value = "org.ehealth_connector.codegenerator.ch.valuesets.UpdateValueSets", date = "2021-03-05")
public enum MaritalStatus implements ValueSetEnumInterface {

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Annulled</div>
	 * <!-- @formatter:on -->
	 */
	ANNULLED("A", "2.16.840.1.113883.5.2", "Annulled", "Annulled", "TOTRANSLATE", "TOTRANSLATE",
			"TOTRANSLATE"),
	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Divorced</div>
	 * <!-- @formatter:on -->
	 */
	DIVORCED("D", "2.16.840.1.113883.5.2", "Divorced", "Divorced", "TOTRANSLATE", "TOTRANSLATE",
			"TOTRANSLATE"),
	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Domestic partner</div>
	 * <!-- @formatter:on -->
	 */
	DOMESTIC_PARTNER("T", "2.16.840.1.113883.5.2", "Domestic partner", "Domestic partner",
			"TOTRANSLATE", "TOTRANSLATE", "TOTRANSLATE"),
	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Interlocutory</div>
	 * <!-- @formatter:on -->
	 */
	INTERLOCUTORY("I", "2.16.840.1.113883.5.2", "Interlocutory", "Interlocutory", "TOTRANSLATE",
			"TOTRANSLATE", "TOTRANSLATE"),
	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Legally Separated</div>
	 * <!-- @formatter:on -->
	 */
	LEGALLY_SEPARATED("L", "2.16.840.1.113883.5.2", "Legally Separated", "Legally Separated",
			"TOTRANSLATE", "TOTRANSLATE", "TOTRANSLATE"),
	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Married</div>
	 * <!-- @formatter:on -->
	 */
	MARRIED("M", "2.16.840.1.113883.5.2", "Married", "Married", "TOTRANSLATE", "TOTRANSLATE",
			"TOTRANSLATE"),
	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Never Married</div>
	 * <!-- @formatter:on -->
	 */
	NEVER_MARRIED("S", "2.16.840.1.113883.5.2", "Never Married", "Never Married", "TOTRANSLATE",
			"TOTRANSLATE", "TOTRANSLATE"),
	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Polygamous</div>
	 * <!-- @formatter:on -->
	 */
	POLYGAMOUS("P", "2.16.840.1.113883.5.2", "Polygamous", "Polygamous", "TOTRANSLATE",
			"TOTRANSLATE", "TOTRANSLATE"),
	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Widowed</div>
	 * <!-- @formatter:on -->
	 */
	WIDOWED("W", "2.16.840.1.113883.5.2", "Widowed", "Widowed", "TOTRANSLATE", "TOTRANSLATE",
			"TOTRANSLATE");

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Code for Annulled</div>
	 * <!-- @formatter:on -->
	 */
	public static final String ANNULLED_CODE = "A";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Code for Divorced</div>
	 * <!-- @formatter:on -->
	 */
	public static final String DIVORCED_CODE = "D";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Code for Domestic partner</div>
	 * <!-- @formatter:on -->
	 */
	public static final String DOMESTIC_PARTNER_CODE = "T";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Code for Interlocutory</div>
	 * <!-- @formatter:on -->
	 */
	public static final String INTERLOCUTORY_CODE = "I";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Code for Legally Separated</div>
	 * <!-- @formatter:on -->
	 */
	public static final String LEGALLY_SEPARATED_CODE = "L";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Code for Married</div>
	 * <!-- @formatter:on -->
	 */
	public static final String MARRIED_CODE = "M";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Code for Never Married</div>
	 * <!-- @formatter:on -->
	 */
	public static final String NEVER_MARRIED_CODE = "S";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Code for Polygamous</div>
	 * <!-- @formatter:on -->
	 */
	public static final String POLYGAMOUS_CODE = "P";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Code for Widowed</div>
	 * <!-- @formatter:on -->
	 */
	public static final String WIDOWED_CODE = "W";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Identifier of the value set</div>
	 * <div class="de">Identifikator für das Value Set</div>
	 * <!-- @formatter:on -->
	 */
	public static final String VALUE_SET_ID = "2.16.840.1.113883.1.11.12212";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Name of the value set</div>
	 * <div class="de">Name des Value Sets</div>
	 * <!-- @formatter:on -->
	 */
	public static final String VALUE_SET_NAME = "MaritalStatus";

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Gets the Enum with a given code</div>
	 * <div class="de">Liefert den Enum anhand eines gegebenen codes</div>
	 * <!-- @formatter:on -->
	 *
	 * @param code
	 *            <div class="de"> code</div>
	 * @return <div class="en">the enum</div>
	 */
	public static MaritalStatus getEnum(final String code) {
		for (final MaritalStatus x : values()) {
			if (x.getCodeValue().equals(code)) {
				return x;
			}
		}
		return null;
	}

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Checks if a given enum is part of this value set.</div>
	 * <div class="de">Prüft, ob der angegebene enum Teil dieses Value Sets ist.</div>
	 * <!-- @formatter:on -->
	 *
	 * @param enumName
	 *            <div class="de"> enumName</div>
	 * @return true, if enum is in this value set
	 */
	public static boolean isEnumOfValueSet(final String enumName) {
		if (enumName == null) {
			return false;
		}
		try {
			Enum.valueOf(MaritalStatus.class, enumName);
			return true;
		} catch (final IllegalArgumentException ex) {
			return false;
		}
	}

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Checks if a given code value is in this value set.</div>
	 * <div class="de">Prüft, ob der angegebene code in diesem Value Set vorhanden ist.</div>
	 * <!-- @formatter:on -->
	 *
	 * @param codeValue
	 *            <div class="de"> code</div>
	 * @return true, if is in value set
	 */
	public static boolean isInValueSet(final String codeValue) {
		for (final MaritalStatus x : values()) {
			if (x.getCodeValue().equals(codeValue)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Machine interpretable and (inside this class) unique code</div>
	 * <div class="de">Maschinen interpretierbarer und (innerhalb dieser Klasse) eindeutiger Code</div>
	 * <!-- @formatter:on -->
	 */
	private String code;

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Identifier of the referencing code system.</div>
	 * <div class="de">Identifikator des referenzierende Codesystems.</div>
	 * <!-- @formatter:on -->
	 */
	private String codeSystem;

	/**
	 * The display names per language
	 */
	private Map<LanguageCode, String> displayNames;

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Instantiates this Enum Object with a given Code and Display Name</div>
	 * <div class="de">Instanziiert dieses Enum Object mittels eines Codes und einem Display Name</div>.
	 * <!-- @formatter:on -->
	 *
	 * @param code
	 *            code
	 * @param codeSystem
	 *            codeSystem
	 * @param displayName
	 *            the default display name
	 * @param displayNameEn
	 *            the display name en
	 * @param displayNameDe
	 *            the display name de
	 * @param displayNameFr
	 *            the display name fr
	 * @param displayNameIt
	 *            the display name it
	 */
	MaritalStatus(final String code, final String codeSystem, final String displayName,
			final String displayNameEn, final String displayNameDe, final String displayNameFr,
			final String displayNameIt) {
		this.code = code;
		this.codeSystem = codeSystem;
		this.displayNames = new HashMap<>();
		this.displayNames.put(null, displayName);
		this.displayNames.put(LanguageCode.ENGLISH, displayNameEn);
		this.displayNames.put(LanguageCode.GERMAN, displayNameDe);
		this.displayNames.put(LanguageCode.FRENCH, displayNameFr);
		this.displayNames.put(LanguageCode.ITALIAN, displayNameIt);
	}

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Gets the code system identifier.</div>
	 * <div class="de">Liefert den Code System Identifikator.</div>
	 * <!-- @formatter:on -->
	 *
	 * @return <div class="en">the code system identifier</div>
	 */
	@Override
	public String getCodeSystemId() {
		return this.codeSystem;
	}

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Gets the code system name.</div>
	 * <div class="de">Liefert den Code System Namen.</div>
	 * <!-- @formatter:on -->
	 *
	 * @return <div class="en">the code system identifier</div>
	 */
	@Override
	public String getCodeSystemName() {
		String retVal = "";
		CodeSystems cs = CodeSystems.getEnum(this.codeSystem);
		if (cs != null)
			retVal = cs.getCodeSystemName();
		return retVal;
	}

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Gets the actual Code as string</div>
	 * <div class="de">Liefert den eigentlichen Code als String</div>
	 * <!-- @formatter:on -->
	 *
	 * @return <div class="en">the code</div>
	 */
	@Override
	public String getCodeValue() {
		return this.code;
	}

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Gets the display name defined by the language param. If
	 * there is no english translation, the default display name is returned.</div>
	 * <div class="de">Liefert display name gemäss Parameter, falls es keine
	 * Englische Übersetzung gibt, wird der default-Name zurückgegeben.</div>
	 * <!-- @formatter:on -->
	 *
	 * @param languageCode
	 *            the language code to get the display name for
	 * @return returns the display name in the desired language. if language not
	 *         found, display name in german will returned
	 */
	@Override
	public String getDisplayName(LanguageCode languageCode) {
		String displayName = this.displayNames.get(languageCode);
		if (displayName == null && languageCode == LanguageCode.ENGLISH) {
			return this.displayNames.get(null);
		}
		return displayName;
	}

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Gets the value set identifier.</div>
	 * <div class="de">Liefert den Value Set Identifikator.</div>
	 * <!-- @formatter:on -->
	 *
	 * @return <div class="en">the value set identifier</div>
	 */
	@Override
	public String getValueSetId() {
		return VALUE_SET_ID;
	}

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Gets the value set name.</div>
	 * <div class="de">Liefert den Value Set Namen.</div>
	 * <!-- @formatter:on -->
	 *
	 * @return <div class="en">the value set name</div>
	 */
	@Override
	public String getValueSetName() {
		return VALUE_SET_NAME;
	}
}