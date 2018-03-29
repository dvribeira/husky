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
package org.ehealth_connector.security.saml2.validation;

import java.security.KeyStore;

import org.ehealth_connector.security.exceptions.ValidationException;

/**
 * 
 * <!-- @formatter:off -->
 * <div class="en">Interface describing the generic Methods for the validation of Assertions.</div>
 * <div class="de">Interface beschreibend die generic Methoden für die Validierung von Assertions.</div>
 * <div class="fr">VOICIFRANCAIS</div>
 * <div class="it">ITALIANO</div>
 * 
 * @param <T>
 * <!-- @formatter:on -->
 */
public interface GenericValidator<T> {
	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Method to set the KeyStore with the keys/certificates used for the Validation.</div>
	 * <div class="de">Methode um den KeyStore mit den Schlüsseln und Zertifikaten gebraucht bei der Validierung.</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 *
	 * @param trustStore
	 * <div class="en">the truststore</div>
	 * <div class="de">Der truststore</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 * <!-- @formatter:on -->
	 */
	public void setTruststore(KeyStore trustStore);

	/**
	 * 
	 * <!-- @formatter:off -->
	 * <div class="en">Method to validate the instance of type T</div>
	 * <div class="de">Methode um die Instanz vom Typ t zu validieren</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 *
	 * @param aType
	 * <div class="en">the instance of type T</div>
	 * <div class="de">die Instanz vom Typ t</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 * @throws ValidationException
	 * 	<div class="en">will be thrown when an error occures while validating the instance of type T</div>
	 *  <div class="de">wird geworfen wenn ein Fehler beim Validieren der Instance von T auftreten</div>
	 *  <div class="fr">VOICIFRANCAIS</div>
	 *  <div class="it">ITALIANO</div>
	 * <!-- @formatter:on -->
	 */
	public void validate(T aType) throws ValidationException;
}
