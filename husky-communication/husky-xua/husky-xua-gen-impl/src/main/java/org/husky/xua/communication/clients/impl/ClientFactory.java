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
package org.husky.xua.communication.clients.impl;

import org.husky.xua.communication.clients.IdpClient;
import org.husky.xua.communication.clients.XuaClient;
import org.husky.xua.communication.config.IdpClientConfig;
import org.husky.xua.communication.config.XuaClientConfig;
import org.husky.xua.communication.config.impl.IdpClientBasicAuthConfigImpl;
import org.husky.xua.communication.config.impl.IdpClientByBrowserAndProtocolHandlerConfigImpl;
import org.husky.xua.communication.config.impl.IdpClientCertificateAuthConfigImpl;
import org.husky.xua.communication.config.impl.IdpClientViaHttpProxyConfigImpl;

/**
 * <!-- @formatter:off -->
 * <div class="en">Class implementing the factory to instantiate correct clients according to the configuration.</div>
 * <div class="de">Klasse welche die Factory implementiert um Client Instanzen entsprechend der Konfiguration zu erstellen.</div>
 * <div class="fr"></div>
 * <div class="it"></div>
 * <!-- @formatter:on -->
 */
public class ClientFactory {
	
	protected ClientFactory() {
	}
	
	public static IdpClient getIdpClient(IdpClientConfig clientConfiguration) {
		if (clientConfiguration instanceof IdpClientViaHttpProxyConfigImpl) {
			return new IdpClientByProxy((IdpClientViaHttpProxyConfigImpl) clientConfiguration);
		} else if (clientConfiguration instanceof IdpClientCertificateAuthConfigImpl) {
			return new IdpClientByCert((IdpClientCertificateAuthConfigImpl) clientConfiguration);
		} else if (clientConfiguration instanceof IdpClientBasicAuthConfigImpl) {
			return new IdpSoapBindingClientByBasicAuth(
					(IdpClientBasicAuthConfigImpl) clientConfiguration);
		} else if (clientConfiguration instanceof IdpClientByBrowserAndProtocolHandlerConfigImpl) {
			return new IdpClientByBrowserAndProtocolHandler(
					(IdpClientByBrowserAndProtocolHandlerConfigImpl) clientConfiguration);
		}
		return null;
	}

	public static XuaClient getXuaClient(XuaClientConfig clientConfiguration) {
		return new SimpleXuaClient(clientConfiguration);
	}
}