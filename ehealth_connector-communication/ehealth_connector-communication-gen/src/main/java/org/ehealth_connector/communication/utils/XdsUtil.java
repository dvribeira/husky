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
package org.ehealth_connector.communication.utils;

import org.ehealth_connector.common.enums.DocumentDescriptor;
import org.ehealth_connector.communication.DocDescriptor;
import org.ehealth_connector.communication.xd.storedquery.DateTimeRange;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Document;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.TimeRange;

/**
 *
 * xds util class
 *
 */
public class XdsUtil {

	/**
	 * Converts a list of eHC DateTimeRange to a list of OHT DateTimeRange.
	 *
	 * @param dtr
	 *            the OHT DateTimeRange
	 * @return the DateTimeRange Array
	 */
	public static TimeRange[] convertEhcDateTimeRange(
			DateTimeRange[] dtr) {
		if (dtr == null)
			return null;
		else {
			final var dtrArray = new TimeRange[dtr.length];

			var i = 0;
			for (final DateTimeRange dt : dtr) {
				dtrArray[i] = dt.getOhtDateTimeRange();
				i++;
			}

			return dtrArray;
		}
	}

	/**
	 * Creates a document name in conformance with the XDM document naming
	 * scheme
	 *
	 * @param xdsDoc
	 *            the OHT XDSDocument
	 * @param docNr
	 *            the number of the document
	 * @return the name of the document
	 */
	public static String createXdmDocName(Document xdsDoc, int docNr) {
		// compile the path and filename for the zip file
		var fileName = "DOC";

		// Fix DocumentDescriptor problem...
		var dd = DocumentDescriptor
				.valueOf(xdsDoc.getDocumentEntry().getMimeType());
		if (dd.toString().startsWith("UNKNOWN!")) {
			String mimeType = dd.toString().replace("UNKNOWN!", "");
			mimeType = mimeType.substring(mimeType.indexOf("!") + 1, mimeType.length());
			dd = DocumentDescriptor.getDocumentDescriptorForMimeType(mimeType);
		}
		// if ("UNKNOWN!CDA-R2!text/xml".equals(dd.toString()))
		// dd = DocumentDescriptor.CDA_R2;

		final String fileNameExtension = DocDescriptor.getFileExtension(dd);
		fileName = fileName.concat(String.format("%5s", docNr).replace(' ', '0'));
		fileName = fileName.concat("." + fileNameExtension.toUpperCase());
		return fileName;
	}

	/**
	 * Creates a document path and name in conformance with the XDM document
	 * naming scheme
	 *
	 * @param xdsDoc
	 *            the OHT XDSDocument
	 * @param docNr
	 *            the number of the document
	 * @return the path and name of the document
	 */
	public static String createXdmDocPathAndName(Document xdsDoc, int docNr) {
		return "IHE_XDM/SUBSET01/" + createXdmDocName(xdsDoc, docNr);
	}

	/**
	 * Default constructor to instanciate the object
	 */
	private XdsUtil() {

	}
}
