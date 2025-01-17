/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 *
 */
package org.projecthusky.fhir.emed.ch.epr.narrative.html;

import ca.uhn.fhir.context.FhirContext;
import org.projecthusky.fhir.emed.ch.epr.narrative.enums.NarrativeLanguage;
import org.projecthusky.fhir.emed.ch.epr.resource.pmlc.ChEmedEprDocumentPmlc;
import org.projecthusky.fhir.emed.ch.epr.resource.pmlc.ChEmedEprMedicationStatementPmlc;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A generator of narrative as HTML content.
 * <p>
 * The template engine is <a href="https://www.thymeleaf.org">Thymeleaf</a>. Templates are stored in
 * '/resources/narrative/templates'.
 *
 * @author Quentin Ligier
 **/
public class HtmlNarrativeGenerator extends AbstractNarrativeGenerator {

    /**
     * The FHIR context.
     */
    protected final FhirContext fhirContext;

    /**
     * The Thymeleaf template engine.
     */
    protected final TemplateEngine templateEngine;

    public HtmlNarrativeGenerator() throws IOException, ParserConfigurationException {
        super();
        this.fhirContext = FhirContext.forR4Cached();

        final var templateResolver = new ClassLoaderTemplateResolver(this.getClass().getClassLoader());
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(true);
        templateResolver.setCacheTTLMs(300000L);
        templateResolver.setPrefix("/narrative/templates/");
        templateResolver.setSuffix(".html");

        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
    }

    public String generate(final ChEmedEprDocumentPmlc document,
                           final NarrativeLanguage lang) {
        final var activeTreatments = new ArrayList<ChEmedEprMedicationStatementPmlc>(5);
        final var asneededTreatments = new ArrayList<ChEmedEprMedicationStatementPmlc>(5);
        for (final var treatment : document.resolveComposition().resolveMedicationStatements()) {
            if (treatment.resolveEffectiveDosageInstructions().isRegular()) {
                activeTreatments.add(treatment);
            } else {
                asneededTreatments.add(treatment);
            }
        }

        final var context = new Context();
        context.setVariable("resource", document);
        context.setVariable("lang", lang);
        context.setVariable("fopase", this.valueSetEnumNarrativeForPatientService);
        context.setVariable("activeTreatments", activeTreatments);
        context.setVariable("asneededTreatments", asneededTreatments);
        context.setLocale(lang.getLocale());
        return this.templateEngine.process("medication_card", context);
    }
}
