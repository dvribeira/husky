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
package org.husky.communication.ch.ppq.epr.policyadmin.impl;

import java.util.LinkedList;
import java.util.List;

import org.husky.communication.ch.ppq.epr.policyadmin.api.OpenSamlAddPolicyRequest;
import org.opensaml.core.xml.XMLObject;

/**
 * <!-- @formatter:off -->
 * <div class="en">Implementing class of OpenSamlAddPolicyRequest interface.</div>
 * <div class="de">Implementierende Klasse des OpenSamlAddPolicyRequest Interfaces.</div>
 * <div class="fr"></div>
 * <div class="it"></div>
 * <!-- @formatter:on -->
 */
public class AddPolicyRequestImpl extends AbstractAssertionBasedRequestImpl
		implements OpenSamlAddPolicyRequest {

	protected AddPolicyRequestImpl(String namespaceURI, String elementLocalName,
			String namespacePrefix) {
		super(namespaceURI, elementLocalName, namespacePrefix);
	}

	@Override
	public List<XMLObject> getOrderedChildren() {
		return new LinkedList<>();
	}

}
