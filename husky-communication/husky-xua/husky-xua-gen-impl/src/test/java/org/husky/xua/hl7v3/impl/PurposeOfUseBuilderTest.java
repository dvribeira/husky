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
package org.husky.xua.hl7v3.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.husky.xua.hl7v3.OpenSamlPurposeOfUse;
import org.husky.xua.hl7v3.impl.PurposeOfUseBuilder;
import org.husky.xua.utilities.impl.InitializerTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PurposeOfUseBuilderTest extends InitializerTestHelper {

	@BeforeEach
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link org.husky.xua.hl7v3.impl.ch.ppq.hl7v3.PurposeOfUseBuilder#buildObject()}.
	 */
	@Test
	public void testBuildObject() {
		final OpenSamlPurposeOfUse ref = new PurposeOfUseBuilder().buildObject();
		assertNotNull(ref);
	}

}
