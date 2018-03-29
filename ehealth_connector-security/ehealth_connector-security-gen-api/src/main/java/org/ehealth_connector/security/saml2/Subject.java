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
package org.ehealth_connector.security.saml2;

import java.util.List;

/**
 * <!-- @formatter:off -->
 * <div class="en">Interface decribing the methods of Subject</div>
 * <div class="de">Interface beschreibt die Methoden von Subject</div>
 * <div class="fr">VOICIFRANCAIS</div>
 * <div class="it">ITALIANO</div>
 * <!-- @formatter:on -->
 *
 */
public interface Subject {

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Method to get the NameID.Value.</div>
	 * <div class="de">Methode um den NameID.Value zu erhalten.</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 *
	 * @return
	 * <div class="en">the NameID.Format as {@link java.lang.String}</div>
	 * <div class="de">den NameID.Format as {@link java.lang.String}</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 * <!-- @formatter:on -->
	 */
	String getNameIDFormat();

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Method to get the NameID.Value.</div>
	 * <div class="de">Methode um den NameID.Value zu erhalten.</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 *
	 * @return
	 * <div class="en">the NameID.Value as {@link java.lang.String}</div>
	 * <div class="de">den NameID.Value as {@link java.lang.String}</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 * <!-- @formatter:on -->
	 */
	String getNameIDValue();

	/**
	 * <!-- @formatter:off -->
	 * <div class="en">Method to get the SubjectConfirmations.</div>
	 * <div class="de">Methode um den SubjectConfirmationen zu erhalten.</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 *
	 * @return
	 * <div class="en">a {@link java.uitl.List} of {@link org.ehealth_connector.security.saml2.SubjectConfirmation}s</div>
	 * <div class="de">eine {@link java.uitl.List} von {@link org.ehealth_connector.security.saml2.SubjectConfirmation}en</div>
	 * <div class="fr">VOICIFRANCAIS</div>
	 * <div class="it">ITALIANO</div>
	 * <!-- @formatter:on -->
	 */
	List<SubjectConfirmation> getSubjectConfirmations();

}
