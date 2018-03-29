/*
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland. All rights reserved.
 * https://medshare.net Source code, documentation and other resources have been contributed by various people. Project Team:
 * https://sourceforge.net/p/ehealthconnector/wiki/Team/ For exact developer information, please refer to the commit history of the forge.
 * This code is made available under the terms of the Eclipse Public License v1.0. Accompanying materials are made available under the terms
 * of the Creative Commons Attribution-ShareAlike 4.0 License. This line is intended for UTF-8 encoding checks, do not modify/delete: äöüéè
 */
package org.ehealth_connector.security.saml2.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.ehealth_connector.security.saml2.Assertion;
import org.ehealth_connector.security.saml2.Response;
import org.joda.time.DateTime;

/**
 * @since Feb 21, 2018 3:27:22 PM
 *
 */
public class ResponseImpl implements Response<org.opensaml.saml2.core.Response, org.opensaml.saml2.core.Assertion> {

	private org.opensaml.saml2.core.Response response;

	/**
	 * Default constructor to instanciate the object
	 * 
	 * @param response
	 */
	public ResponseImpl(org.opensaml.saml2.core.Response response) {
		super();
		this.response = response;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.security.core.SecurityObject#getWrappedObject()
	 */
	@Override
	public org.opensaml.saml2.core.Response getWrappedObject() {
		return response;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.security.saml2.Response#getID()
	 */
	@Override
	public String getID() {
		return response.getID();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.security.saml2.Response#getInResponseTo()
	 */
	@Override
	public String getInResponseTo() {
		return response.getInResponseTo();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.security.saml2.Response#getIssueInstant()
	 */
	@Override
	public Calendar getIssueInstant() {
		final DateTime instant = response.getIssueInstant();
		final Calendar retVal = Calendar.getInstance();
		retVal.setTimeInMillis(instant.getMillis());
		return retVal;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.security.saml2.Response#getDestination()
	 */
	@Override
	public String getDestination() {
		return response.getDestination();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.security.saml2.Response#getConsent()
	 */
	@Override
	public String getConsent() {
		return response.getConsent();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.security.saml2.Response#getIssuer()
	 */
	@Override
	public String getIssuer() {
		if (response.getIssuer() != null) {
			return response.getIssuer().getValue();
		}
		return "";
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.security.saml2.Response#getAssertions()
	 */
	@Override
	public List<Assertion<org.opensaml.saml2.core.Assertion>> getAssertions() {
		final List<org.opensaml.saml2.core.Assertion> internal = response.getAssertions();
		final List<Assertion<org.opensaml.saml2.core.Assertion>> retVal = new ArrayList<>();
		internal.forEach(c -> {
			retVal.add(new AssertionBuilderImpl().createAssertion(c));
		});
		return retVal;
	}

}