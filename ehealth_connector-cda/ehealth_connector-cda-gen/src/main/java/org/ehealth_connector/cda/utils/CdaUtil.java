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
package org.ehealth_connector.cda.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.bind.Binder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.ehealth_connector.common.Name;
import org.ehealth_connector.common.basetypes.NameBaseType;
import org.ehealth_connector.common.enums.LanguageCode;
import org.ehealth_connector.common.hl7cdar2.CS;
import org.ehealth_connector.common.hl7cdar2.ED;
import org.ehealth_connector.common.hl7cdar2.EN;
import org.ehealth_connector.common.hl7cdar2.INT;
import org.ehealth_connector.common.hl7cdar2.ObjectFactory;
import org.ehealth_connector.common.hl7cdar2.POCDMT000040StructuredBody;
import org.ehealth_connector.common.hl7cdar2.SC;
import org.ehealth_connector.common.hl7cdar2.ST;
import org.ehealth_connector.common.hl7cdar2.StrucDocText;
import org.ehealth_connector.common.mdht.enums.EhcVersions;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class CdaUtil {

	public static CS createCs(String value) {
		ObjectFactory factory = new ObjectFactory();
		CS retVal = factory.createCS();
		retVal.setCode(value);
		return retVal;
	}

	public static ED createEd(String value) {
		ObjectFactory factory = new ObjectFactory();
		ED retVal = factory.createED();
		retVal.xmlContent = value;
		return retVal;
	}

	public static ST createSt(String value) {
		ObjectFactory factory = new ObjectFactory();
		ST retVal = factory.createST();
		retVal.xmlContent = value;
		return retVal;
	}

	public static StrucDocText createStrucDocText(String value) {
		return createStrucDocText(null, null, value);
	}

	public static StrucDocText createStrucDocText(String id, LanguageCode languageCode,
			String value) {
		ObjectFactory factory = new ObjectFactory();
		StrucDocText retVal = factory.createStrucDocText();
		if (id != null)
			retVal.setID(id);
		if (languageCode != null)
			retVal.setLanguage(languageCode.getCodeValue());
		if (value != null)
			retVal.xmlContent = value;
		return retVal;
	}

	/**
	 * Generate comment.
	 *
	 * @return the string
	 */
	public static String generateXmlHeaderComment() {
		return "Document based on CDA R2 generated by "
				+ EhcVersions.getCurrentVersion().getSystemVersionName() + ", Release Date "
				+ EhcVersions.getCurrentVersion().getReleaseDate();
	}

	public static String getFullName(EN en) {
		NameBaseType nameBt = Name.createNameBaseType(en);
		return nameBt.getFullName();
	}

	public static String getFullName(SC value) {
		return value.xmlContent;
	}

	public static Integer getInt(INT value) {
		return value.getValue().intValue();
	}

	public static int getSectionCount(POCDMT000040StructuredBody structuredBody) {
		int retVal = structuredBody.getComponent().size();
		return retVal;
	}

	/**
	 * <div class="en">prints the XML representation of the document to the
	 * console</div> <div class="de">Gibt die XML-Repräsentation des Dokuments
	 * auf der Konsole aus</div>.
	 *
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 * @throws JAXBException
	 */
	public static void printCdaToConsole(
			org.ehealth_connector.common.hl7cdar2.POCDMT000040ClinicalDocument cdaDoc)
			throws JAXBException, ParserConfigurationException, TransformerException {
		org.ehealth_connector.cda.utils.CdaUtil.saveJaxbObjectToStream(cdaDoc, System.out, null,
				null);
	}

	public static void saveJaxbObjectToFile(Object jaxbObject, File outputFile, String xsl,
			String css) throws JAXBException, ParserConfigurationException, TransformerException,
			FileNotFoundException {
		saveJaxbObjectToStream(jaxbObject, new FileOutputStream(outputFile), xsl, css);

	}

	public static void saveJaxbObjectToFile(Object jaxbObject, String outputFileName, String xsl,
			String css) throws JAXBException, ParserConfigurationException, TransformerException,
			FileNotFoundException {
		saveJaxbObjectToFile(jaxbObject, new File(outputFileName), xsl, css);
	}

	public static void saveJaxbObjectToStream(Object jaxbObject, OutputStream outputStream,
			String xsl, String css)
			throws JAXBException, ParserConfigurationException, TransformerException {
		final JAXBContext context = JAXBContext.newInstance(jaxbObject.getClass());
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder builder = factory.newDocumentBuilder();
		final Document doc = builder.getDOMImplementation().createDocument(null, null, null);

		final Binder<Node> binder = context.createBinder();
		final Comment comment = doc.createComment(CdaUtil.generateXmlHeaderComment());
		doc.appendChild(comment);
		doc.appendChild(doc.createProcessingInstruction("xml-stylesheet",
				"type=\"text/css\" href=\"" + css + "\""));
		doc.appendChild(doc.createProcessingInstruction("xml-stylesheet",
				"type=\"text/xsl\" href=\"" + xsl + "\""));

		binder.marshal(jaxbObject, doc);

		final DOMSource domSource = new DOMSource(doc);
		final StreamResult streamResult = new StreamResult(outputStream);
		final TransformerFactory tf = TransformerFactory.newInstance();
		final Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		transformer.transform(domSource, streamResult);

	}

}
