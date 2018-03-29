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
package org.ehealth_connector.security.saml2.validation.impl;

import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

import org.ehealth_connector.security.exceptions.ValidationException;
import org.opensaml.core.criterion.EntityIdCriterion;
import org.opensaml.saml.security.impl.SAMLSignatureProfileValidator;
import org.opensaml.security.credential.Credential;
import org.opensaml.security.credential.impl.KeyStoreCredentialResolver;
import org.opensaml.xmlsec.signature.Signature;
import org.opensaml.xmlsec.signature.support.SignatureValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.shibboleth.utilities.java.support.resolver.CriteriaSet;
import net.shibboleth.utilities.java.support.resolver.Criterion;

/**
 * <!-- @formatter:off -->
 * <div class="en">Abstract class implementing the generic functions for all validators.</div>
 * <div class="de">Abstrakte Klasse implemtiert alle generischen funktionen für die validatoren.</div>
 * <div class="fr">VOICIFRANCAIS</div>
 * <div class="it">ITALIANO</div>
 * <!-- @formatter:on -->
 */
public abstract class AbstractValidator {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private KeyStore trustStore;
	private String password;

	public KeyStore getTrustStore() {
		return trustStore;
	}

	public void setTrustStore(KeyStore trustStore) {
		this.trustStore = trustStore;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Logger getLog() {
		return log;
	}

	public void validate(Signature aSignature, String aAlias) throws ValidationException {
		try {
			final SAMLSignatureProfileValidator profileValidator = new SAMLSignatureProfileValidator();
			profileValidator.validate(aSignature);
		} catch (final Exception e) {
			log.error("Error", e);
			// throw new ValidationException(e);
		}
		try {
			final Map<String, String> passwordMap = new HashMap<>();
			final KeyStoreCredentialResolver resolver = new KeyStoreCredentialResolver(trustStore, passwordMap);

			final Criterion criterion = new EntityIdCriterion(aAlias);
			final CriteriaSet criteriaSet = new CriteriaSet(criterion);
			final Credential credential = resolver.resolveSingle(criteriaSet);

			SignatureValidator.validate(aSignature, credential);

		} catch (final Exception e) {
			log.error("Error", e);
			throw new ValidationException(e);
		}
	}

}
