/*******************************************************************************
 *
 * The authorship of this code and the accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. http://medshare.net
 *
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
 *
 * This code is are made available under the terms of the Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 4.0 License.
 *
 * Year of publication: 2015
 *
 *******************************************************************************/

package org.ehealth_connector.cda.tests;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.ehealth_connector.cda.ProblemEntry;
import org.ehealth_connector.cda.ch.enums.AllergiesSpecialConditions;
import org.ehealth_connector.cda.ch.enums.CdaChVacdComplRisks;
import org.ehealth_connector.cda.ch.enums.CdaChVacdExpRisks;
import org.ehealth_connector.cda.ch.enums.ProblemType;
import org.ehealth_connector.cda.ch.enums.ProblemsSpecialConditions;
import org.ehealth_connector.common.DateUtil;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * The Class ProblemEntryTests.
 */
public class ProblemEntryTests {

	private XPathFactory xpathFactory = XPathFactory.newInstance();
	private XPath xpath = xpathFactory.newXPath();

	@Test
	public void testSerializeEmpty() throws Exception {
		ProblemEntry entry = new ProblemEntry();

		Document document = entry.getDocument();

		XPathExpression expr = xpath
				.compile("observation/templateId[@root='1.3.6.1.4.1.19376.1.5.3.1.4.5']");
		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		expr = xpath.compile("observation/templateId[@root='2.16.840.1.113883.10.20.1.28']");
		nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		expr = xpath.compile("observation/statusCode[@code='completed']");
		nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		expr = xpath.compile("observation[@negationInd='false']");
		nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		expr = xpath.compile("observation/id");
		nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

	}

	@Test
	public void testProblemOccured() throws Exception {
		ProblemEntry entry = new ProblemEntry();

		assertEquals(false, entry.getProblemNotOccured());
		entry.setNotOccured(true);
		assertEquals(true, entry.getProblemNotOccured());

		Document document = entry.getDocument();

		XPathExpression expr = xpath.compile("observation[@negationInd='true']");
		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

	}

	@Test
	public void testStartDate() throws Exception {
		ProblemEntry entry = new ProblemEntry();

		assertEquals(null, entry.getStartDate());
		Date date = DateUtil.parseDateyyyyMMdd("20151019");
		entry.setStartDate(date);
		assertEquals(date, entry.getStartDate());

		Document document = entry.getDocument();

		XPathExpression expr = xpath.compile("observation/effectiveTime/low[@value='20151019']");
		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());
	}

	@Test
	public void testStartDateNotSet() throws Exception {
		ProblemEntry entry = new ProblemEntry();

		entry.setStartDate(null);
		Document document = entry.getDocument();

		XPathExpression expr = xpath.compile("observation/effectiveTime/low[@nullFlavor='UNK']");
		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());
	}

	@Test
	public void testEndDate() throws Exception {
		ProblemEntry entry = new ProblemEntry();

		assertEquals(null, entry.getEndDate());
		Date date = DateUtil.parseDateyyyyMMdd("20161019");
		entry.setEndDate(date);
		assertEquals(date, entry.getEndDate());

		Document document = entry.getDocument();

		XPathExpression expr = xpath.compile("observation/effectiveTime/high[@value='20161019']");
		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());
	}

	@Test
	public void testEndDateNotSet() throws Exception {
		ProblemEntry entry = new ProblemEntry();

		entry.setEndDate(null);
		Document document = entry.getDocument();

		XPathExpression expr = xpath.compile("observation/effectiveTime/high[@nullFlavor='UNK']");
		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());
	}

	/*
	 * PROBLEM("55607006", "Problem"),
	 */
	@Test
	public void testProblemTypEnum() throws XPathExpressionException {
		ProblemEntry entry = new ProblemEntry();

		entry.setProblemType(ProblemType.PROBLEM);

		Document document = entry.getDocument();

		XPathExpression expr = xpath.compile(
				"observation/code[@code='55607006' and @codeSystem='2.16.840.1.113883.6.96']");

		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		assertEquals(ProblemType.PROBLEM, entry.getProblemType());
	}

	@Test
	public void testTextReference() throws XPathExpressionException {
		ProblemEntry entry = new ProblemEntry();

		entry.setTextReference("#reference1");

		Document document = entry.getDocument();

		XPathExpression expr = xpath.compile("observation/text/reference[@value='#reference1']");

		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		assertEquals("#reference1", entry.getTextReference());
	}

	@Test
	public void testComplicationRisks() throws XPathExpressionException {
		ProblemEntry entry = new ProblemEntry();

		entry.setComplicationRisk(CdaChVacdComplRisks.CR114006, null);

		Document document = entry.getDocument();

		XPathExpression expr = xpath.compile(
				"observation/code[@code='55607006' and @codeSystem='2.16.840.1.113883.6.96']");

		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		// note, does not check xsi:prefix in attribute value, output
		// xsi:type="CD"
		expr = xpath.compile(
				"observation/value[@type='CD' and @code='114006' and @codeSystem='2.16.756.5.30.1.127.3.3.1']");
		nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		assertEquals(CdaChVacdComplRisks.CR114006, entry.getComplicationRisk());
	}

	@Test
	public void testExposureRisks() throws XPathExpressionException {
		ProblemEntry entry = new ProblemEntry();

		entry.setExposureRisk(CdaChVacdExpRisks.ER213012, null);

		Document document = entry.getDocument();

		XPathExpression expr = xpath.compile(
				"observation/code[@code='55607006' and @codeSystem='2.16.840.1.113883.6.96']");

		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		// note, does not check xsi:prefix in attribute value, output
		// xsi:type="CD"
		expr = xpath.compile(
				"observation/value[@type='CD' and @code='213012' and @codeSystem='2.16.756.5.30.1.127.3.3.2']");
		nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		assertEquals(CdaChVacdExpRisks.ER213012, entry.getExposureRisk());
	}

	@Test
	public void testAllergiesSpecialConditions() throws XPathExpressionException {
		ProblemEntry entry = new ProblemEntry();

		entry.setAllergySpecialCondition(AllergiesSpecialConditions.NO_KNOWN_ALLERGIES);

		Document document = entry.getDocument();

		XPathExpression expr = xpath.compile(
				"observation/value[@type='CD' and @code='160244002' and @codeSystem='2.16.840.1.113883.6.96']");

		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		assertEquals(AllergiesSpecialConditions.NO_KNOWN_ALLERGIES,
				entry.getAllergySpecialCondition());
	}

	@Test
	public void testProblemSpecialConditions() throws XPathExpressionException {
		ProblemEntry entry = new ProblemEntry();

		entry.setProblemSpecialCondition(
				ProblemsSpecialConditions.NO_CURRENT_PROBLEMS_OR_DISABILITY);

		Document document = entry.getDocument();

		XPathExpression expr = xpath.compile(
				"observation/value[@type='CD' and @code='160245001' and @codeSystem='2.16.840.1.113883.6.96']");

		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		assertEquals(ProblemsSpecialConditions.NO_CURRENT_PROBLEMS_OR_DISABILITY,
				entry.getProblemSpecialCondition());
	}

}