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
package org.ehealth_connector.xua.saml2.impl;

import static org.junit.Assert.assertEquals;

import org.ehealth_connector.xua.hl7v3.Role;
import org.ehealth_connector.xua.hl7v3.impl.RoleBuilder;
import org.ehealth_connector.xua.saml2.Attribute;
import org.ehealth_connector.xua.saml2.AttributeBuilder;
import org.junit.Before;
import org.junit.Test;

public class AttributeBuilderImplTest {

	private String testAttributeName;
	private String testAttributeValue;
	public Role testAttributeValueRole;

	public AttributeBuilder testBuilder;

	@Before
	public void setUp() throws Exception {
		testBuilder = new AttributeBuilderImpl();
		testAttributeName = "My Attribute Name";
		testAttributeValue = "My Attribute Value";

		testAttributeValueRole = new RoleBuilder().buildObject();
		testAttributeValueRole.setCode("My Code");
		testAttributeValueRole.setCode("My Code System");
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.xua.saml2.impl.AttributeBuilderImpl#name(java.lang.String)}.
	 */
	@Test
	public void testName() {
		final Attribute ref = testBuilder.name(testAttributeName).create();
		assertEquals(testAttributeName, ref.getName());
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.xua.saml2.impl.AttributeBuilderImpl#value(java.lang.String)}.
	 */
	@Test
	public void testValue() {
		final Attribute ref = testBuilder.value(testAttributeValue).create();
		assertEquals(testAttributeValue, ref.getValueAsString());
	}

	public void testValueRole() {
		final Attribute ref = testBuilder.value(testAttributeValueRole).create();
		assertEquals(testAttributeValueRole, ref.getValueAsRole());
	}

}