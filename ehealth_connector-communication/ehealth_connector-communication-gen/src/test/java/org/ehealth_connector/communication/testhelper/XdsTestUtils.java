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
package org.ehealth_connector.communication.testhelper;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ehealth_connector.common.Author;
import org.ehealth_connector.common.Code;
import org.ehealth_connector.common.Identificator;
import org.ehealth_connector.common.Name;
import org.ehealth_connector.common.mdht.enums.DateTimeRangeAttributes;
import org.ehealth_connector.common.utils.DateUtil;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.AssociationType;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.AvailabilityStatus;
import org.openhealthtools.ihe.xds.consumer.query.DateTimeRange;
import org.openhealthtools.ihe.xds.consumer.query.MalformedQueryException;
import org.openhealthtools.ihe.xds.consumer.storedquery.StoredQueryParameter;
import org.openhealthtools.ihe.xds.consumer.storedquery.StoredQueryParameterList;
import org.openhealthtools.ihe.xds.metadata.constants.DocumentEntryConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XdsTestUtils {

	// Initialize PatientId
	public static Identificator patientId = new Identificator("1.3.6.1.4.1.21367.13.20.2005.1000",
			"IHERED-1644");

	// Initialize AuthorPerson
	public static Author authorPerson;

	public static Code[] formatCodes = new Code[] {
			new Code("urn:ihe:rad:1.2.840.10008.5.1.4.1.1.88.59", "KOS Dokument", null) };

	public static Code[] confidentialityCodes = new Code[] {
			new Code("30001", "administrative Daten", null) };

	public static List<String> docIds = List.of("123455.235234", "1.2.3.4.5.6.7.8");

	public static List<AssociationType> parentRelation = List.of(AssociationType.APPEND, AssociationType.REPLACE);

	// Date Time Ranges
	public static DateTimeRange dateTimeRange1;

	public static DateTimeRange dateTimeRange2;

	public static DateTimeRange[] dateTimeRanges;

	public static org.ehealth_connector.communication.xd.storedquery.DateTimeRange eDateTimeRange1;
	public static org.ehealth_connector.communication.xd.storedquery.DateTimeRange eDateTimeRange2;

	public static org.ehealth_connector.communication.xd.storedquery.DateTimeRange eDateTimeRanges[];
	public static Date d1;
	public static Date d2;
	/** The SLF4J logger instance. */
	protected final Logger log = LoggerFactory.getLogger(getClass());

	public AvailabilityStatus availabilityStatus;

	public XdsTestUtils() {
		// Initalize DateTimeRanges
		try {
			dateTimeRange1 = new DateTimeRange(DocumentEntryConstants.CREATION_TIME, "201401012300", "201412310400");
			dateTimeRange2 = new DateTimeRange(DocumentEntryConstants.CREATION_TIME, "201501012300", "201502010400");
			dateTimeRanges = new DateTimeRange[] { dateTimeRange1, dateTimeRange2 };
		} catch (MalformedQueryException e) {
			e.printStackTrace();
		}

			d1 = DateUtil.parseDateyyyyMMddHHmmss("19800521022211");
			d2 = DateUtil.parseDateyyyyMMddHHmmss("20150521133459");
			eDateTimeRange1 = new org.ehealth_connector.communication.xd.storedquery.DateTimeRange(
					DateTimeRangeAttributes.CREATION_TIME, d1, d2);
			eDateTimeRange2 = new org.ehealth_connector.communication.xd.storedquery.DateTimeRange(
					DateTimeRangeAttributes.SERVICE_START_TIME, d1, d2);
			eDateTimeRanges = new org.ehealth_connector.communication.xd.storedquery.DateTimeRange[] {
					eDateTimeRange1, eDateTimeRange2 };

		availabilityStatus = AvailabilityStatus.SUBMITTED;

		// Initialize AuthorPerson
		authorPerson = new Author();
		var name = new Name();
		name.setFamily("Bereit");
		name.setGiven("Allzeit");
		name.setPrefix("Dr.");
		authorPerson.addName(name);

		authorPerson.getIds().add(new Identificator(null, "123456789"));
	}

	public String extractByRegex(String aRegEx, List<List<String>> aInput) {
		final Pattern p = Pattern.compile(aRegEx);

		for (List<String> item : aInput) {
			for (String itemStr : item) {
				final Matcher m = p.matcher(itemStr);
				if (m.find()) {
					return m.group(1);
				}
			}
		}

		return "";
	}

	public void log(StoredQueryParameterList aSqpl) {
		aSqpl.forEach(new Consumer<StoredQueryParameter>() {
			@Override
			public void accept(StoredQueryParameter t) {
				log.debug(t.getName() + ": " + t.getValue());
			}
		});
	}
}
