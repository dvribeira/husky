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
package org.ehealth_connector.security.ch.ppq;

import org.ehealth_connector.security.hl7v3.InstanceIdentifier;
import org.ehealth_connector.security.saml2.BaseBuilder;

/**
 * <!-- @formatter:off -->
 * <div class="en">Describing the methods of the PatentPrivacyQueryBuilder Interface</div>
 * <div class="de">Beschreibung der Methoden des PatentPrivacyQueryBuilder Interfaces</div>
 * <div class="fr"></div>
 * <div class="it"></div>
 * <!-- @formatter:on -->
 */
public interface PrivacyPolicyQueryBuilder extends BaseBuilder<PrivacyPolicyQueryBuilder> {

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Method to set the consent of the PatientPrivacyQuery to be build.</div>
	 * <div class="de">Methode um ein consent dem PatientPrivacyQuery hinzuzufügen.</div>
	 * <div class="fr"></div>
	 * <div class="it"></div>
	 *
	 * @param newConsent
	 *  <div class="en">the consent to be set</div>
	 * 	<div class="de">der consent der gesetzt werden soll</div>
	 *  <div class="fr"></div>
	 *  <div class="it"></div>
	 * @return
	 *  <div class="en">the actual instance of this builder</div>
	 *  <div class="de">die aktuelle Instanz des Builders</div>
	 *  <div class="fr"></div>
	 *  <div class="it"></div>
	 * <!-- @formatter:on -->
	 */
	PrivacyPolicyQueryBuilder consent(String newConsent);

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Creates a concrete instance of PatientPrivacyQuery with setted params.</div>
	 * <div class="de">Erstellt die konkrete Instanz des PatientPrivacyQuery mit den gesetzten Parametern.</div>
	 * <div class="fr"></div>
	 * <div class="it"></div>
	 *
	 * @return
	 *  <div class="en">a new PatientPrivacyQuery instance with the params set</div>
	 *  <div class="de">eine neue PatientPrivacyQuery Instanz mit den gesetzten Parametern</div>
	 *  <div class="fr"></div>
	 *  <div class="it"></div>
	 *  <!-- @formatter:on -->
	 */
	PrivacyPolicyQuery create();

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Method to set the destination of the PatientPrivacyQuery to be build.</div>
	 * <div class="de">Methode um eine Destination dem PatientPrivacyQuery hinzuzufügen.</div>
	 * <div class="fr"></div>
	 * <div class="it"></div>
	 *
	 * @param newDestination
	 *  <div class="en">the consent to be set</div>
	 * 	<div class="de">der Consent der gesetzt werden soll</div>
	 *  <div class="fr"></div>
	 *  <div class="it"></div>
	 * @return
	 *  <div class="en">the actual instance of this builder</div>
	 *  <div class="de">die aktuelle Instanz des Builders</div>
	 *  <div class="fr"></div>
	 *  <div class="it"></div>
	 * <!-- @formatter:on -->
	 */
	PrivacyPolicyQueryBuilder destination(String newDestination);

	/**
	 *
	 * <!-- @formatter:off -->
	 * <div class="en">Method to set the instance identifier of the PatientPrivacyQuery to be build.</div>
	 * <div class="de">Methode um den instance identifier dem PatientPrivacyQuery hinzuzufügen.</div>
	 * <div class="fr"></div>
	 * <div class="it"></div>
	 *
	 * @param identifier
	 *  <div class="en">the identifier to be set</div>
	 * 	<div class="de">der Identifier der gesetzt werden soll</div>
	 *  <div class="fr"></div>
	 *  <div class="it"></div>
	 * @return
	 *  <div class="en">the actual instance of this builder</div>
	 *  <div class="de">die aktuelle Instanz des Builders</div>
	 *  <div class="fr"></div>
	 *  <div class="it"></div>
	 * <!-- @formatter:on -->
	 */
	PrivacyPolicyQueryBuilder instanceIdentifier(InstanceIdentifier identifier);

}