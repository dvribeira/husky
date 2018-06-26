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
package org.ehealth_connector.security.saml2.impl;

import org.ehealth_connector.security.core.SecurityObject;
import org.ehealth_connector.security.saml2.Condition;

/**
 * <!-- @formatter:off -->
 * <div class="en">Implementation class of Condition</div>
 * <div class="de">Implementations Klasse von Condition</div>
 * <div class="fr"></div>
 * <div class="it"></div>
 *
 * <!-- @formatter:on -->
 */
public class ConditionImpl
		implements Condition, SecurityObject<org.opensaml.saml.saml2.core.Condition> {

	/** The wrapped object. */
	private org.opensaml.saml.saml2.core.Condition wrappedObject;

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Default constructor to instanciate the object.</div>
	 * <div class="de">Default Konstruktor für die Instanziierung des Objekts.</div>
	 * <div class="fr"></div>
	 * <div class="it"></div>
	 *
	 * <!-- @formatter:on -->
	 *
	 * @param aInternalObject the Condition
	 */
	protected ConditionImpl(org.opensaml.saml.saml2.core.Condition aInternalObject) {
		wrappedObject = aInternalObject;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.security.core.SecurityObject#getWrappedObject()
	 */
	@Override
	public org.opensaml.saml.saml2.core.Condition getWrappedObject() {
		return wrappedObject;
	}

}
