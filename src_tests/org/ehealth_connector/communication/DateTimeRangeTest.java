package org.ehealth_connector.communication;

import static org.junit.Assert.*;

import java.util.Date;

import org.ehealth_connector.common.DateUtil;
import org.ehealth_connector.communication.ch.enums.DateTimeRangeAttributes;
import org.ehealth_connector.communication.storedquery.DateTimeRange;
import org.junit.Test;
import org.openhealthtools.ihe.xds.consumer.query.MalformedQueryException;

public class DateTimeRangeTest extends XdsTestUtils {
	
	Date d1;
	Date d2;
	
	public DateTimeRangeTest() {
		super();
		d1 = DateUtil.parseDateyyyyMMddHHmmss("19800521022211");
		d2 = DateUtil.parseDateyyyyMMddHHmmss("20150521133459");
	}
	
	@Test
	public void constructorTest() {
		try {
			DateTimeRange d = new DateTimeRange(DateTimeRangeAttributes.CREATION_TIME, d1, d2);
			assertTrue(d.getFrom().getTime() == d1.getTime());
			assertTrue(d.getTo().getTime() == d2.getTime());//201401012300

			assertTrue(DateUtil.format(d.getFrom()).equals("19800521022211"));
			assertTrue(DateUtil.format(d.getTo()).equals("20150521133459"));
			
			assertEquals("19800521022211", d.getOhtDateTimeRange().getFrom());
			assertEquals("20150521133459", d.getOhtDateTimeRange().getTo());
		} catch (MalformedQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}