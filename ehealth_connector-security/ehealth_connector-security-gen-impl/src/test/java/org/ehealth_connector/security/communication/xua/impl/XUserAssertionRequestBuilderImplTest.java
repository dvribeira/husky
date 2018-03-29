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
package org.ehealth_connector.security.communication.xua.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.UUID;

import org.ehealth_connector.security.communication.xua.AppliesTo;
import org.ehealth_connector.security.communication.xua.RequestType;
import org.ehealth_connector.security.communication.xua.TokenType;
import org.ehealth_connector.security.communication.xua.XUserAssertionRequest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.opensaml.soap.wstrust.RequestSecurityToken;
import org.opensaml.soap.wstrust.impl.RequestSecurityTokenBuilder;

public class XUserAssertionRequestBuilderImplTest {

	private XUserAssertionRequestBuilderImpl builder;
	private String testDialect;
	private String testContext;
	private String testSubjectId;
	private String testSubjectName;
	private String testOrganizationId;
	private String testOrganizationName;
	private String testResourceId;
	private RequestSecurityToken testInternalFromOutside;
	private String testRoleId;
	private AppliesTo testAppliesTo;
	private String testAddress;

	@Before
	public void setUp() throws Exception {
		builder = new XUserAssertionRequestBuilderImpl();
		testDialect = "http://bag.admin.ch/epr/2017/annex/5/addendum/2";
		testContext = "This is my Context";
		testSubjectId = UUID.randomUUID().toString();
		testSubjectName = "Harry Hirsch";
		testOrganizationId = UUID.randomUUID().toString();
		testOrganizationName = "My Best organisation";
		testResourceId = UUID.randomUUID().toString();
		testRoleId = "HCP";

		testInternalFromOutside = new RequestSecurityTokenBuilder().buildObject();
		testInternalFromOutside.setContext(testContext);

		testAddress = "https://guguseli.org/test/access/endpoint/address";
		testAppliesTo = new AppliesToBuilderImpl().address(testAddress).create();
	}

	@Test
	public void testDialect() {
		final XUserAssertionRequest ref = builder.dialect(testDialect).create();
		assertNotNull(ref);
		assertEquals(testDialect, ref.getDialect());
	}

	@Test
	public void testContext() {
		final XUserAssertionRequest ref = builder.context(testContext).create();
		assertNotNull(ref);
		assertEquals(testContext, ref.getContext());
	}

	@Test
	public void testSubjectId() {
		final XUserAssertionRequest ref = builder.subjectId(testSubjectId).create();
		assertNotNull(ref);
		assertEquals(testSubjectId, ref.getSubjectId());
	}

	@Test
	public void testSubjectName() {
		final XUserAssertionRequest ref = builder.subjectName(testSubjectName).create();
		assertNotNull(ref);
		assertEquals(testSubjectName, ref.getSubjectName());
	}

	@Test
	public void testSubjectRole() {
		final XUserAssertionRequest ref = builder.subjectRole(testRoleId).create();
		assertNotNull(ref);
		assertEquals(testRoleId, ref.getSubjectRole());
	}

	@Test
	public void testOrganizationId() {
		final XUserAssertionRequest ref = builder.organizationId(testOrganizationId).create();
		assertNotNull(ref);
		assertEquals(testOrganizationId, ref.getOrganizationId());
	}

	@Test
	public void testOrganizationName() {
		final XUserAssertionRequest ref = builder.organizationName(testOrganizationName).create();
		assertNotNull(ref);
		assertEquals(testOrganizationName, ref.getOrganizationName());
	}

	@Test
	public void testResourceId() {
		final XUserAssertionRequest ref = builder.resourceId(testResourceId).create();
		assertNotNull(ref);
		assertEquals(testResourceId, ref.getResourceId());
	}

	@Test
	@Ignore
	public void testPurposeOfUse() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequestType() {
		final XUserAssertionRequest ref = builder.requestType(RequestType.WST_ISSUE).create();
		assertNotNull(ref);
		assertEquals(RequestType.WST_ISSUE, ref.getRequestType());
	}

	@Test
	public void testTokenType() {
		final XUserAssertionRequest ref = builder.tokenType(TokenType.OASIS_WSS_SAML_PROFILE_11_SAMLV20).create();
		assertNotNull(ref);
		assertEquals(TokenType.OASIS_WSS_SAML_PROFILE_11_SAMLV20, ref.getTokenType());
	}

	@Test
	public void testAppliesTo() {
		final XUserAssertionRequest ref = builder.appliesTo(testAppliesTo).create();
		assertNotNull(ref);
		assertEquals(testAppliesTo.getAddress(), ref.getAppliesTo().getAddress());
	}

	@Test
	public void testCreateRequestSecurityToken() {
		final XUserAssertionRequest ref = builder.create(testInternalFromOutside);
		assertNotNull(ref);
		assertEquals(testInternalFromOutside, ((XUserAssertionRequestImpl) ref).getWrappedObject());
		assertEquals(testContext, ref.getContext());
	}

}
