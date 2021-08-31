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
package org.ehealth_connector.communication.ch.ppq.impl.config;

import org.ehealth_connector.communication.ch.ppq.api.config.PpClientConfig;
import org.ehealth_connector.xua.communication.config.impl.BaseSoapClientConfigImpl;

/**
 * <!-- @formatter:off -->
 * <div class="en">Implementation class of Interface PpClientConfig</div>
 * <div class="de">Implementations Klasse von Interface PpClientConfig</div>
 * <div class="fr"></div>
 * <div class="it"></div>
 * <!-- @formatter:on -->
 */
public class PpClientConfigImpl extends BaseSoapClientConfigImpl implements PpClientConfig {

	// There are no special methods defined, but the interface has to be
	// declared for selection reasons.
	protected PpClientConfigImpl() {
		setSoapVersion(SoapVersion.SOAP_12);
	}

}