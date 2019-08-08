/*
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
package org.ehealth_connector.cda.ch.lrep;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.ehealth_connector.common.CdaNamespacePrefixMapper;

/**
 * Original ART-DECOR template id: 2.16.756.5.30.1.1.10.3.5
 * Template description: A Laboratory Specialty Section SHALL contain EITHER a list of Laboratory Report Item Section(s) OR a single text and entry element to represent the Report Items.Choice 1: Use of sub-sections (Laboratory Report Item Sections):With this option, the results of the examination MUST be documented in the subsections, and the main section (Laboratory Specialty Section) MUST NOT contain any text OR entry elements.
 */
public class ChpalmSectionLaboratoryReportItemCoded extends org.ehealth_connector.common.hl7cdar2.POCDMT000040Section {

	/**
	 * Gets the hl7Code
	 * This declares the laboratory discipline of the section.
	 */
	public org.ehealth_connector.common.hl7cdar2.CE getHl7Code() {
		return super.code;
	}

	/**
	 * Gets the hl7Entry
	 * The narrative text in the text element of the section MUST be generated automatically from the information in this entry.
	 */
	public org.ehealth_connector.common.hl7cdar2.POCDMT000040Entry getHl7Entry() {
		org.ehealth_connector.common.hl7cdar2.POCDMT000040Entry retVal = null;
		if (super.getEntry() != null)
			if (super.getEntry().size() > 0)
				retVal = super.getEntry().get(0);
		return retVal;
	}

	/**
	 * Gets the hl7Id
	 * An ID for this section MAY be filled for traceability.
	 */
	public org.ehealth_connector.common.hl7cdar2.II getHl7Id() {
		return super.id;
	}

	/**
	 * Gets the hl7TemplateId
	 */
	public org.ehealth_connector.common.hl7cdar2.II getHl7TemplateId() {
		org.ehealth_connector.common.hl7cdar2.II retVal = null;
		if (super.getTemplateId() != null)
			if (super.getTemplateId().size() > 0)
				retVal = super.getTemplateId().get(0);
		return retVal;
	}

	/**
	 * Gets the hl7Text
	 * The laboratory results MUST be automatically generated readable for humans in the text element of the section from the information in the Laboratory Data Processing Entry.
	 */
	public org.ehealth_connector.common.hl7cdar2.StrucDocText getHl7Text() {
		return super.text;
	}

	/**
	 * Gets the hl7Title
	 * The title of the chapter MUST be specified and correspond to the following text format, where &lt;human readable code&gt; contains the corresponding translation of the document LOINC code:
	 * - [ge]: 'Laborbefund - &lt;human readable code&gt;'
	 * - [fr]: 'Rapport de laboratoire - &lt;human readable code&gt;'
	 * - [it]: 'Referto di laboratorio - &lt;human readable code&gt;'
	 * - [en]: 'Laboratory Specialty - &lt;human readable code&gt;'The following translations of the LOINC codes given in XD-LAB MUST be used:<table><tbody><tr><th>LOINC Code</th><th>Text</th></tr><tr><td>18717-9</td><td>[ge]: Immunhämatologie[fr]: Immunohématologie[it]: Immunoematologia</td></tr><tr><td>18718-7</td><td>[ge]: Molekularbiologie[fr]: Biologie moléculaire[it]: Biologia molecolare</td></tr><tr><td>18719-5</td><td>[ge]: Chemie[fr]: Chimie[it]: Chimica</td></tr><tr><td>18720-3</td><td>[ge]: Gerinnung[fr]: Coagulation[it]: Coagulazione</td></tr><tr><td>18721-1</td><td>[ge]: Medikamente[fr]: Médicaments[it]: Medicamenti</td></tr><tr><td>18722-9</td><td>[ge]: Fertilität[fr]: Fertilité[it]: Fertilità</td></tr><tr><td>18723-7</td><td>[ge]: Hämatologie[fr]: Hématologie[it]: Ematologia</td></tr><tr><td>18724-5</td><td>[ge]: HLA[fr]: HLA[it]: HLA</td></tr><tr><td>18725-2</td><td>[ge]: Mikrobiologie[fr]: Microbiologie[it]: Microbiologia</td></tr><tr><td>18727-8</td><td>[ge]: Serologie[fr]: Sérologie[it]: Sierologia</td></tr><tr><td>18728-6</td><td>[ge]: Toxikologie[fr]: Toxicologie[it]: Tessicologia</td></tr><tr><td>18729-4</td><td>[ge]: Urin Untersuchungen[fr]: Analyses dans l'urine[it]: Analisi nelle urine</td></tr><tr><td>18767-4</td><td>[ge]: Blutgase[fr]: Gazométrie[it]: Gasometria</td></tr><tr><td>18768-2</td><td>[ge]: Differenzialblutbild[fr]: Répartition leucocytaire[it]: Differenziazione leucocitaria</td></tr><tr><td>18769-0</td><td>[ge]: Antibiogramme[fr]: Antibiogramme[it]: Antibiogramma</td></tr><tr><td>26435-8</td><td>[ge]: Molekulare Pathologie[fr]: Pathologie moleculaire[it]: Patologia moleculare</td></tr><tr><td>26436-6</td><td>[ge]: Laboruntersuchungen[fr]: Études de laboratoire[it]: Esami di laboratorio</td></tr><tr><td>26437-4</td><td>[ge]: Stimulationen[fr]: Stimulation[it]: Stimolazione</td></tr><tr><td>26438-2</td><td>[ge]: Zytologie[fr]: Cytologie[it]: Citologia</td></tr></tbody></table>
	 */
	public org.ehealth_connector.common.hl7cdar2.ST getHl7Title() {
		return super.title;
	}

	/**
	 * Loads the CDA document from file.
	 * @param inputFileName the full path and filename of the sourcefile.
	 * @return the CDA document\n@throws JAXBException\n@throws IOException Signals that an I/O exception has occurred.
	 */
	public static ChpalmSectionLaboratoryReportItemCoded loadFromFile(String inputFileName) throws JAXBException, IOException {
		return loadFromFile(new File(inputFileName));
	}

	/**
	 * Loads the CDA document from file.
	 * @param inputFile the source file.
	 * n@return the CDA document\n@throws JAXBException\n@throws IOException Signals that an I/O exception has occurred.
	 */
	public static ChpalmSectionLaboratoryReportItemCoded loadFromFile(File inputFile) throws JAXBException, IOException {
		ChpalmSectionLaboratoryReportItemCoded retVal;
		JAXBContext context = JAXBContext.newInstance(ChpalmSectionLaboratoryReportItemCoded.class);
		Unmarshaller mar = context.createUnmarshaller();
		StreamSource source = new StreamSource(inputFile);
		JAXBElement<ChpalmSectionLaboratoryReportItemCoded> root = mar.unmarshal(source, ChpalmSectionLaboratoryReportItemCoded.class);
		retVal = root.getValue();
		return retVal;
	}

	/**
	 * Saves the current CDA document to file.
	 * @param outputFileName the full path and filename of the destination file.
	 * @throws JAXBException
	 */
	public void saveToFile(String outputFileName) throws JAXBException {
		saveToFile(new File(outputFileName));
	}

	/**
	 * Saves the current CDA document to file.
	 * @param outputFile the destination file.
	 * @throws JAXBException
	 */
	public void saveToFile(File outputFile) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(this.getClass());
		Marshaller mar = context.createMarshaller();
		mar.setProperty("com.sun.xml.bind.namespacePrefixMapper", new CdaNamespacePrefixMapper());
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		mar.marshal(this, outputFile);
	}

	/**
	 * Sets the hl7Code
	 * This declares the laboratory discipline of the section.
	 */
	public void setHl7Code(org.ehealth_connector.common.hl7cdar2.CE value) {
		super.code = value;
	}

	/**
	 * Sets the hl7Entry
	 * The narrative text in the text element of the section MUST be generated automatically from the information in this entry.
	 */
	public void setHl7Entry(org.ehealth_connector.common.hl7cdar2.POCDMT000040Entry value) {
		super.getEntry().clear();
		super.getEntry().add(value);
	}

	/**
	 * Sets the hl7Id
	 * An ID for this section MAY be filled for traceability.
	 */
	public void setHl7Id(org.ehealth_connector.common.hl7cdar2.II value) {
		super.id = value;
	}

	/**
	 * Sets the hl7TemplateId
	 */
	public void setHl7TemplateId(org.ehealth_connector.common.hl7cdar2.II value) {
		super.getTemplateId().clear();
		super.getTemplateId().add(value);
	}

	/**
	 * Sets the hl7Text
	 * The laboratory results MUST be automatically generated readable for humans in the text element of the section from the information in the Laboratory Data Processing Entry.
	 */
	public void setHl7Text(org.ehealth_connector.common.hl7cdar2.StrucDocText value) {
		super.text = value;
	}

	/**
	 * Sets the hl7Title
	 * The title of the chapter MUST be specified and correspond to the following text format, where &lt;human readable code&gt; contains the corresponding translation of the document LOINC code:
	 * - [ge]: 'Laborbefund - &lt;human readable code&gt;'
	 * - [fr]: 'Rapport de laboratoire - &lt;human readable code&gt;'
	 * - [it]: 'Referto di laboratorio - &lt;human readable code&gt;'
	 * - [en]: 'Laboratory Specialty - &lt;human readable code&gt;'The following translations of the LOINC codes given in XD-LAB MUST be used:<table><tbody><tr><th>LOINC Code</th><th>Text</th></tr><tr><td>18717-9</td><td>[ge]: Immunhämatologie[fr]: Immunohématologie[it]: Immunoematologia</td></tr><tr><td>18718-7</td><td>[ge]: Molekularbiologie[fr]: Biologie moléculaire[it]: Biologia molecolare</td></tr><tr><td>18719-5</td><td>[ge]: Chemie[fr]: Chimie[it]: Chimica</td></tr><tr><td>18720-3</td><td>[ge]: Gerinnung[fr]: Coagulation[it]: Coagulazione</td></tr><tr><td>18721-1</td><td>[ge]: Medikamente[fr]: Médicaments[it]: Medicamenti</td></tr><tr><td>18722-9</td><td>[ge]: Fertilität[fr]: Fertilité[it]: Fertilità</td></tr><tr><td>18723-7</td><td>[ge]: Hämatologie[fr]: Hématologie[it]: Ematologia</td></tr><tr><td>18724-5</td><td>[ge]: HLA[fr]: HLA[it]: HLA</td></tr><tr><td>18725-2</td><td>[ge]: Mikrobiologie[fr]: Microbiologie[it]: Microbiologia</td></tr><tr><td>18727-8</td><td>[ge]: Serologie[fr]: Sérologie[it]: Sierologia</td></tr><tr><td>18728-6</td><td>[ge]: Toxikologie[fr]: Toxicologie[it]: Tessicologia</td></tr><tr><td>18729-4</td><td>[ge]: Urin Untersuchungen[fr]: Analyses dans l'urine[it]: Analisi nelle urine</td></tr><tr><td>18767-4</td><td>[ge]: Blutgase[fr]: Gazométrie[it]: Gasometria</td></tr><tr><td>18768-2</td><td>[ge]: Differenzialblutbild[fr]: Répartition leucocytaire[it]: Differenziazione leucocitaria</td></tr><tr><td>18769-0</td><td>[ge]: Antibiogramme[fr]: Antibiogramme[it]: Antibiogramma</td></tr><tr><td>26435-8</td><td>[ge]: Molekulare Pathologie[fr]: Pathologie moleculaire[it]: Patologia moleculare</td></tr><tr><td>26436-6</td><td>[ge]: Laboruntersuchungen[fr]: Études de laboratoire[it]: Esami di laboratorio</td></tr><tr><td>26437-4</td><td>[ge]: Stimulationen[fr]: Stimulation[it]: Stimolazione</td></tr><tr><td>26438-2</td><td>[ge]: Zytologie[fr]: Cytologie[it]: Citologia</td></tr></tbody></table>
	 */
	public void setHl7Title(org.ehealth_connector.common.hl7cdar2.ST value) {
		super.title = value;
	}
}
