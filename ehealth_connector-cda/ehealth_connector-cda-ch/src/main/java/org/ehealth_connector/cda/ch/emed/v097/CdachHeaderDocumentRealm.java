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
package org.ehealth_connector.cda.ch.emed.v097;

import javax.annotation.processing.Generated;

/**
 * cdach_header_DocumentRealm
 *
 * Template description: Swiss Realm (CHE) of HL7 CDA. All CDA-CH V2 derivatives, i.e. Swiss exchange formats MUST reference this template.
 *
 * Element description: Swiss Realm (CHE) of HL7 CDA.
 *
 * <!-- @formatter:off -->
 * Identifier: 2.16.756.5.30.1.1.10.2.25
 * Effective date: 2018-04-18 00:00:00
 * Version: 2017
 * Status: active
 * <!-- @formatter:on -->
 */
@Generated(value = "org.ehealth_connector.codegenerator.cda.ArtDecor2JavaGenerator", date = "2021-03-05")
public class CdachHeaderDocumentRealm extends org.ehealth_connector.common.hl7cdar2.CS {

	public CdachHeaderDocumentRealm() {
		super.setCode("CHE");
	}
}