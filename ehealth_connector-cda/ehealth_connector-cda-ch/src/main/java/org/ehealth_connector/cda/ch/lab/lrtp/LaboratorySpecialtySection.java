package org.ehealth_connector.cda.ch.lab.lrtp;

import org.ehealth_connector.cda.ihe.lab.AbstractLaboratorySpecialtySection;

public class LaboratorySpecialtySection extends AbstractLaboratorySpecialtySection {

	public LaboratorySpecialtySection() {
		super();
	}

	public LaboratorySpecialtySection(
			org.openhealthtools.mdht.uml.cda.ihe.lab.LaboratorySpecialtySection mdht) {
		super(mdht);
	}

	public org.ehealth_connector.cda.ch.lab.lrtp.LaboratoryReportDataProcessingEntry getLaboratoryReportDataProcessingEntry() {
		if (getMdht().getEntries() != null && !getMdht().getEntries().isEmpty()
				&& getMdht().getEntries().get(
						0) instanceof org.openhealthtools.mdht.uml.cda.ihe.lab.LaboratoryReportDataProcessingEntry) {
			return new org.ehealth_connector.cda.ch.lab.lrtp.LaboratoryReportDataProcessingEntry(
					(org.openhealthtools.mdht.uml.cda.ihe.lab.LaboratoryReportDataProcessingEntry) getMdht()
							.getEntries().get(0));
		}
		return null;
	}

	public void setLaboratoryReportDataProcessingEntry(
			org.ehealth_connector.cda.ch.lab.lrtp.LaboratoryReportDataProcessingEntry entry) {
		getMdht().getEntries().clear();
		getMdht().getEntries().add(entry.getMdht());
	}
}
