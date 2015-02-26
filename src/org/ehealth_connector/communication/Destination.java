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
 * Attribution-ShareAlike 3.0 Switzerland License.
 * 
 * Year of publication: 2014
 * 
 *******************************************************************************/

package org.ehealth_connector.communication;

import java.net.URI;
import java.security.PrivateKey;

public class Destination {

	/**
	 * URI des Repository. Z.B. "urn:ihe:iti:2007:ProvideAndRegisterDocumentSet-b"
	 */
	URI repositoryUri;

	/**
	 * Privater X.509 Zertifikats-Schlüssel
	 */
	PrivateKey privateX509Key;

	//
	/**
	 * ID des Empfängers der Nachricht. Z.B. 1.2.3.4.1789.10 bei
	 * "Princeton Hospital^^^^^^^^^1.2.3.4.1789.10"
	 */
	String intendedRecipientId;

	/**
	 * Name des Empfängers der Nachricht. Z.B. Princeton Hospital bei
	 * "Princeton Hospital^^^^^^^^^1.2.3.4.1789.10"
	 */
	String intendedRecipientName;

	/**
	 * Kommunikations-Endpunkt
	 * 
	 * @param repositoryUri URI des Kommunikations-Endpunkt
	 */
	public Destination(URI repositoryUri) {
		this(repositoryUri, null, null);
	}

	/**
	 * Kommunikations-Endpunkt (TLS)
	 * 
	 * @param repositoryUri URI des Kommunikations-Endpunkt
	 * @param privateX509Key Privater X.509 Zertifikats-Schlüssel
	 */
	public Destination(URI repositoryUri, PrivateKey privateX509Key) {
		this(repositoryUri, null, null, privateX509Key);
	}

	/**
	 * Kommunikations-Endpunkt
	 * 
	 * @param repositoryUri URI des Kommunikations-Endpunkt
	 * @param intendedRecipientId Id des Empfängers
	 * @param intendedRecipientName Name des Empfängers
	 */
	public Destination(URI repositoryUri, String intendedRecipientId, String intendedRecipientName) {

	}

	/**
	 * Kommunikations-Endpunkt (TLS)
	 * 
	 * @param repositoryUri URI des Kommunikations-Endpunkt
	 * @param intendedRecipientId Id des Empfängers
	 * @param intendedRecipientName Name des Empfängers
	 * @param privateX509Key Privater X.509 Zertifikats-Schlüssel
	 */
	public Destination(URI repositoryUri, String intendedRecipientId, String intendedRecipientName,
			PrivateKey privateX509Key) {

	}

	/**
	 * Liefert den Empfänger der Nachricht
	 * 
	 * @return das intendedRecipient Objekt
	 */
	public String getIntendedRecipientId() {
		return intendedRecipientId;
	}

	/**
	 * Liefert den privaten X.509 Zertifikats-Schlüssel
	 * 
	 * @return das privateX509Key Objekt
	 */
	public PrivateKey getPrivateX509Key() {
		return privateX509Key;
	}

	/**
	 * Liefert die URI des Kommunikations-Endpunkts
	 * 
	 * @return das repositoryUri Objekt
	 */
	public URI getResositoryUri() {
		return repositoryUri;
	}

	/**
	 * Setzt den Empfänger der Nachricht
	 * 
	 * @param intendedRecipientId das intendedRecipientId Objekt welches gesetzt wird
	 */
	public void setIntendedRecipientId(String intendedRecipientId) {
		this.intendedRecipientId = intendedRecipientId;
	}

	/**
	 * Setzt den privaten X.509 Zertifikats-Schlüssel
	 * 
	 * @param privateX509Key das privateX509Key Objekt welches gesetzt wird
	 */
	public void setPrivateX509Key(PrivateKey privateX509Key) {
		this.privateX509Key = privateX509Key;
	}

	/**
	 * Setzt die URI des Kommunikations-Endpunkts
	 * 
	 * @param repositoryUri das repositoryUri Objekt welches gesetzt wird
	 */
	public void setResositoryUri(URI repositoryUri) {
		this.repositoryUri = repositoryUri;
	}

}