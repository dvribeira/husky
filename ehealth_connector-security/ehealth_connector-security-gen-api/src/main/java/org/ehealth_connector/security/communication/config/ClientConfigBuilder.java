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
package org.ehealth_connector.security.communication.config;

/**
 * <!-- @formatter:off -->
 * <div class="en">Interface describing the ClientConfigBuilder methods.</div>
 * <div class="de">Interface welches die Methoden des ClientConfigBuilders beschreibt.</div>
 * <div class="fr"></div>
 * <div class="it"></div>
 * <!-- @formatter:on -->
 */
public interface ClientConfigBuilder {

	/**
	 *
	 * <!-- @formatter:off -->
	 * <div class="en">Method to set the enpoint URI.</div>
	 * <div class="de">Methode um die Enpunkt URI zu bilden.</div>
	 * <div class="fr"></div>
	 * <div class="it"></div>
	 *
	 * @param aEndpointUrl
	 * <div class="en">the enpoint URI to be set.</div>
	 * <div class="de">Die Enpunkt URI die zu setzen ist.</div>
	 * <div class="fr"></div>
	 * <div class="it"></div>
	 * @return
	 * <div class="en">the actual instance of this builder</div>
	 * <div class="de">die aktuelle Instanz des Builders</div>
	 * <div class="fr"></div>
	 * <div class="it"></div>
	 * <!-- @formatter:on -->
	 */
	ClientConfigBuilder url(String aEndpointUrl);

}
