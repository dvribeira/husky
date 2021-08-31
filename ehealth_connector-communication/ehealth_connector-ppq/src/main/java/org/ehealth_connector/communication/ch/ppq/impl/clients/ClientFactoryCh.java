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
package org.ehealth_connector.communication.ch.ppq.impl.clients;

import javax.jms.IllegalStateException;

import org.ehealth_connector.communication.ch.ppq.api.clients.PpfClient;
import org.ehealth_connector.communication.ch.ppq.api.clients.PpqClient;
import org.ehealth_connector.communication.ch.ppq.api.config.PpClientConfig;
import org.ehealth_connector.xua.communication.clients.impl.ClientFactory;

/**
 * <!-- @formatter:off -->
 * <div class="en">Class implementing the factory to instantiate correct clients according to the configuration for CH implementations.</div>
 * <div class="de">Klasse implementiert die factory um client instanzen entsprechend der Konfiguration zu erstellen für CH spezifische clients.</div>
 * <div class="fr"></div>
 * <div class="it"></div>
 * <!-- @formatter:on -->
 */
public class ClientFactoryCh extends ClientFactory {
	
	private ClientFactoryCh() throws IllegalStateException {
		super();
	}

	public static PpfClient getPpfClient(PpClientConfig clientConfiguration) {
		return new SimplePpfClient(clientConfiguration);
	}

	public static PpqClient getPpqClient(PpClientConfig clientConfiguration) {
		return new SimplePpqClient(clientConfiguration);
	}
}