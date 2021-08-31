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
package org.ehealth_connector.xua.communication.xua;

/**
 * <!-- @formatter:off -->
 * <div class="en">Class defining XUserAssertionConstants.</div>
 * <div class="de">Klasse welche XUserAssertionConstanten definiert.</div>
 * <div class="fr"></div>
 * <div class="it"></div>
 * <!-- @formatter:on -->
 */
public class XUserAssertionConstants {

	/** SAML V2.0 ws profile 1.1 */
	public static final String OASIS_WSS_SAML_PROFILE_11_SAMLV20 = "http://docs.oasis-open.org/wss/oasis-wss-saml-token-profile-1.1#SAMLV2.0";

	/** XSPA subject organization */
	public static final String OASIS_XACML_ORGANISATION = "urn:oasis:names:tc:xspa:1.0:subject:organization";

	/** XSPA subject organization-id */
	public static final String OASIS_XACML_ORGANISATIONID = "urn:oasis:names:tc:xspa:1.0:subject:organization-id";

	/** XSPA subject purposeofuse */
	public static final String OASIS_XACML_PURPOSEOFUSE = "urn:oasis:names:tc:xspa:1.0:subject:purposeofuse";

	/** XACML resource resource-id string */
	public static final String OASIS_XACML_RESOURCEID = "urn:oasis:names:tc:xacml:2.0:resource:resource-id";

	/** XACML subject role string */
	public static final String OASIS_XACML_ROLE = "urn:oasis:names:tc:xacml:2.0:subject:role";

	/** XACML subject subject-id string */
	public static final String OASIS_XACML_SUBJECTID = "urn:oasis:names:tc:xacml:1.0:subject:subject-id";
	
	private XUserAssertionConstants() {
		 throw new IllegalStateException("Constants class");
	}

}