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
 * This line is intended for UTF-8 encoding checks, do not modify/delete: �����
 *
 */
package org.ehealth_connector.cda.ch.lrep.v133;

import java.util.List;
import org.ehealth_connector.common.hl7cdar2.ObjectFactory;

/**
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.2.28 Template
 * description: Laboratory Performer template in the CDA header
 * (ClinicalDocument/documentationOf/serviceEvent)ClinicalDocument/documentationOf(s)
 * MAY be present. The documentationOf/serviceEvent represents the main Act
 * being documented, that is an act of reporting Result Event(s) produced by a
 * laboratory.Use of sub element documentationOf/serviceEvent/effectiveTime to
 * document the time boundaries of events in the document is appropriate.This
 * laboratory report content module adds the optional sub element
 * documentationOf/serviceEvent/statusCode to enable the sharing of non-final
 * reports. A report is considered as non-final (e.g., a preliminary report) if
 * and only if it documents an Act, which is still in the status "active" (i.e.,
 * serviceEvent/statusCode@code="active").The statusCode sub element is an
 * extension to the CDA R2 schema. This sub-element is optional. When it is not
 * there, the documented Act is assumed to be completed and the report is
 * assumed to be a final report.
 */
public class ChpalmHeaderDocumentationOfLaboratoryPerformer
		extends org.ehealth_connector.common.hl7cdar2.POCDMT000040DocumentationOf {

	public ChpalmHeaderDocumentationOfLaboratoryPerformer() {
		super.getTemplateId().add(createHl7TemplateIdFixedValue("2.16.756.5.30.1.1.10.2.28"));
	}

	/**
	 * Creates fixed contents for CDA Element hl7TemplateId
	 *
	 * @param root
	 *            the desired fixed value for this argument.
	 */
	private static org.ehealth_connector.common.hl7cdar2.II createHl7TemplateIdFixedValue(
			String root) {
		ObjectFactory factory = new ObjectFactory();
		org.ehealth_connector.common.hl7cdar2.II retVal = factory.createII();
		retVal.setRoot(root);
		return retVal;
	}

	/**
	 * Gets the hl7ServiceEvent
	 */
	public org.ehealth_connector.common.hl7cdar2.POCDMT000040ServiceEvent getHl7ServiceEvent() {
		return serviceEvent;
	}

	/**
	 * Gets the hl7TemplateId
	 */
	public java.util.List<org.ehealth_connector.common.hl7cdar2.II> getHl7TemplateId() {
		return templateId;
	}

	/**
	 * Sets the hl7ServiceEvent
	 */
	public void setHl7ServiceEvent(
			org.ehealth_connector.common.hl7cdar2.POCDMT000040ServiceEvent value) {
		this.serviceEvent = value;
	}

	/**
	 * Sets the hl7TemplateId
	 */
	public void setHl7TemplateId(org.ehealth_connector.common.hl7cdar2.II value) {
		getTemplateId().clear();
		getTemplateId().add(value);
	}
}