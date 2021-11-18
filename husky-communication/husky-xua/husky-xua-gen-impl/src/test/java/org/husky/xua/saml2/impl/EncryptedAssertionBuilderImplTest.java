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
package org.husky.xua.saml2.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.husky.xua.saml2.EncryptedAssertion;
import org.husky.xua.saml2.impl.EncryptedAssertionBuilderImpl;
import org.husky.xua.saml2.impl.EncryptedAssertionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opensaml.xmlsec.encryption.EncryptedData;

public class EncryptedAssertionBuilderImplTest {

	private EncryptedAssertionBuilderImpl builder;
	private String testId;
	private org.opensaml.saml.saml2.core.EncryptedAssertion testInnerObject;

	@BeforeEach
	public void setUp() throws Exception {
		builder = new EncryptedAssertionBuilderImpl();
		testInnerObject = new org.opensaml.saml.saml2.core.impl.EncryptedAssertionBuilder()
				.buildObject();
		final EncryptedData newEncryptedData = new org.opensaml.xmlsec.encryption.impl.EncryptedDataBuilder()
				.buildObject();
		testId = UUID.randomUUID().toString();
		newEncryptedData.setID(testId);
		testInnerObject.setEncryptedData(newEncryptedData);
	}

	/**
	 * Test method for
	 * {@link org.husky.xua.saml2.impl.EncryptedAssertionBuilderImpl#create()}.
	 */
	@Test
	public void testCreate() {
		final EncryptedAssertion ref = builder.create();
		assertNotNull(ref);
	}

	/**
	 * Test method for
	 * {@link org.husky.xua.saml2.impl.EncryptedAssertionBuilderImpl#create(org.opensaml.saml.saml2.core.EncryptedAssertion)}.
	 */
	@Test
	public void testCreateEncryptedAssertion() {
		final EncryptedAssertion ref = builder.create(testInnerObject);
		assertEquals(testInnerObject, ((EncryptedAssertionImpl) ref).getWrappedObject());
	}

}
