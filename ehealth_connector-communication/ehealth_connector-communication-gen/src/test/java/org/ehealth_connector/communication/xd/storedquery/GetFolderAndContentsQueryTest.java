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
package org.ehealth_connector.communication.xd.storedquery;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.ehealth_connector.communication.testhelper.XdsTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.DocumentEntryType;
import org.openehealth.ipf.commons.ihe.xds.core.requests.query.QueryList;

/**
 * Test of class GetFolderAndContentsQuery
 */
class GetFolderAndContentsQueryTest extends XdsTestUtils {

	/**
	 * Method implementing
	 *
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.communication.xd.storedquery.GetFolderAndContentsQuery#GetFolderAndContentsQuery(java.lang.String, boolean, org.ehealth_connector.common.Code[], org.ehealth_connector.common.Code[])}
	 * .
	 */
	@Test
	void testGetFolderAndContentsQueryStringBooleanCodeArrayCodeArray() {
		final GetFolderAndContentsQuery q1 = new GetFolderAndContentsQuery("1234", true,
				formatCodes, confidentialityCodes);
		final Map<String, QueryList<String>> sqpl1 = q1.getIpfQuery().getExtraParameters();

		assertTrue(sqpl1.get("$XDSFolderEntryUUID").getOuterList().stream().anyMatch(t -> t.contains("1234")));
		assertTrue(sqpl1.get("$XDSDocumentEntryFormatCode").getOuterList().stream()
				.anyMatch(t -> t.contains(formatCodes[0].getCode())));
		assertTrue(sqpl1.get("$XDSDocumentEntryConfidentialityCode").getOuterList().stream()
				.anyMatch(t -> t.contains(confidentialityCodes[0].getCode())));
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.communication.xd.storedquery.GetFolderAndContentsQuery#GetFolderAndContentsQuery(java.lang.String, boolean, org.ehealth_connector.common.Code[], org.ehealth_connector.common.Code[], java.lang.String)}
	 * .
	 */
	@Test
	void testGetFolderAndContentsQueryStringBooleanCodeArrayCodeArrayString() {
		final GetFolderAndContentsQuery q2 = new GetFolderAndContentsQuery("1234", true,
				formatCodes, confidentialityCodes, "9876");
		assertTrue(q2.getIpfQuery().getHomeCommunityId().contains("9876"));
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.communication.xd.storedquery.GetFolderAndContentsQuery#GetFolderAndContentsQuery(java.lang.String, boolean, org.ehealth_connector.common.Code[], org.ehealth_connector.common.Code[], java.lang.String, org.openhealthtools.ihe.xds.consumer.storedquery.ObjectType)}
	 * .
	 */
	@Test
	void testGetFolderAndContentsQueryStringBooleanCodeArrayCodeArrayStringObjectType() {
		final GetFolderAndContentsQuery q3 = new GetFolderAndContentsQuery("1234", true,
				formatCodes, confidentialityCodes, "6565873dsdgsdg", DocumentEntryType.STABLE);
		assertTrue(q3.getIpfQuery().getExtraParameters().get("$XDSDocumentEntryType").getOuterList().stream()
				.anyMatch(t -> t.contains("urn:uuid:7edca82f-054d-47f2-a032-9b2a5b5186c1")));
	}

}
