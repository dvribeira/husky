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

/* Generated by camel build tools - do NOT edit this file! */
package org.husky.communication.ch.camel.chpharm1.transform;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;
import org.apache.camel.DeferredContextBinding;
import org.apache.camel.TypeConverterLoaderException;
import org.apache.camel.spi.TypeConverterLoader;
import org.apache.camel.spi.TypeConverterRegistry;
import org.apache.camel.support.SimpleTypeConverter;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Generated by camel build tools - do NOT edit this file!
 */
@SuppressWarnings("unchecked")
@DeferredContextBinding
public final class ChPharm1ConvertersLoader implements TypeConverterLoader, CamelContextAware {

    @MonotonicNonNull
    private CamelContext camelContext;

    public ChPharm1ConvertersLoader() {
    }

    @Override
    public void setCamelContext(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    @Override
    public CamelContext getCamelContext() {
        return camelContext;
    }

    @Override
    public void load(TypeConverterRegistry registry) throws TypeConverterLoaderException {
        registerConverters(registry);
    }

    private void registerConverters(TypeConverterRegistry registry) {
        addTypeConverter(registry, org.husky.communication.ch.camel.chpharm1.requests.ChFindDispensesQuery.class, org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAdhocQueryRequest30.class, false,
            (type, exchange, value) -> getChPharm1Converters().convertChFindDispensesQuery((org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAdhocQueryRequest30) value));
        addTypeConverter(registry, org.husky.communication.ch.camel.chpharm1.requests.ChFindMedicationAdministrationsQuery.class, org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAdhocQueryRequest30.class, false,
            (type, exchange, value) -> getChPharm1Converters().convertChFindMedicationAdministrationsQuery((org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAdhocQueryRequest30) value));
        addTypeConverter(registry, org.husky.communication.ch.camel.chpharm1.requests.ChFindMedicationCardQuery.class, org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAdhocQueryRequest30.class, false,
            (type, exchange, value) -> getChPharm1Converters().convertChFindMedicationCardQuery((org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAdhocQueryRequest30) value));
        addTypeConverter(registry, org.husky.communication.ch.camel.chpharm1.requests.ChFindMedicationListQuery.class, org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAdhocQueryRequest30.class, false,
            (type, exchange, value) -> getChPharm1Converters().convertChFindMedicationListQuery((org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAdhocQueryRequest30) value));
        addTypeConverter(registry, org.husky.communication.ch.camel.chpharm1.requests.ChFindMedicationTreatmentPlansQuery.class, org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAdhocQueryRequest30.class, false,
            (type, exchange, value) -> getChPharm1Converters().convertFindMedicationTreatmentPlansQuery((org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAdhocQueryRequest30) value));
        addTypeConverter(registry, org.husky.communication.ch.camel.chpharm1.requests.ChFindPrescriptionsForDispenseQuery.class, org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAdhocQueryRequest30.class, false,
            (type, exchange, value) -> getChPharm1Converters().convertChFindPrescriptionsForDispenseQuery((org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAdhocQueryRequest30) value));
        addTypeConverter(registry, org.husky.communication.ch.camel.chpharm1.requests.ChFindPrescriptionsForValidationQuery.class, org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAdhocQueryRequest30.class, false,
            (type, exchange, value) -> getChPharm1Converters().convertChFindPrescriptionsForValidationQuery((org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAdhocQueryRequest30) value));
        addTypeConverter(registry, org.husky.communication.ch.camel.chpharm1.requests.ChFindPrescriptionsQuery.class, org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAdhocQueryRequest30.class, false,
            (type, exchange, value) -> getChPharm1Converters().convertFindPrescriptionsQuery((org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAdhocQueryRequest30) value));
        addTypeConverter(registry, org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLAdhocQueryRequest.class, org.husky.communication.ch.camel.chpharm1.requests.ChFindDispensesQuery.class, false,
            (type, exchange, value) -> getChPharm1Converters().convertChFindDispensesQuery((org.husky.communication.ch.camel.chpharm1.requests.ChFindDispensesQuery) value));
        addTypeConverter(registry, org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLAdhocQueryRequest.class, org.husky.communication.ch.camel.chpharm1.requests.ChFindMedicationAdministrationsQuery.class, false,
            (type, exchange, value) -> getChPharm1Converters().convertChFindMedicationAdministrationsQuery((org.husky.communication.ch.camel.chpharm1.requests.ChFindMedicationAdministrationsQuery) value));
        addTypeConverter(registry, org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLAdhocQueryRequest.class, org.husky.communication.ch.camel.chpharm1.requests.ChFindMedicationCardQuery.class, false,
            (type, exchange, value) -> getChPharm1Converters().convertChFindMedicationCardQuery((org.husky.communication.ch.camel.chpharm1.requests.ChFindMedicationCardQuery) value));
        addTypeConverter(registry, org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLAdhocQueryRequest.class, org.husky.communication.ch.camel.chpharm1.requests.ChFindMedicationListQuery.class, false,
            (type, exchange, value) -> getChPharm1Converters().convertChFindMedicationListQuery((org.husky.communication.ch.camel.chpharm1.requests.ChFindMedicationListQuery) value));
        addTypeConverter(registry, org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLAdhocQueryRequest.class, org.husky.communication.ch.camel.chpharm1.requests.ChFindMedicationTreatmentPlansQuery.class, false,
            (type, exchange, value) -> getChPharm1Converters().convertFindMedicationTreatmentPlansQuery((org.husky.communication.ch.camel.chpharm1.requests.ChFindMedicationTreatmentPlansQuery) value));
        addTypeConverter(registry, org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLAdhocQueryRequest.class, org.husky.communication.ch.camel.chpharm1.requests.ChFindPrescriptionsForDispenseQuery.class, false,
            (type, exchange, value) -> getChPharm1Converters().convertChFindPrescriptionsForDispenseQuery((org.husky.communication.ch.camel.chpharm1.requests.ChFindPrescriptionsForDispenseQuery) value));
        addTypeConverter(registry, org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLAdhocQueryRequest.class, org.husky.communication.ch.camel.chpharm1.requests.ChFindPrescriptionsForValidationQuery.class, false,
            (type, exchange, value) -> getChPharm1Converters().convertChFindPrescriptionsForValidationQuery((org.husky.communication.ch.camel.chpharm1.requests.ChFindPrescriptionsForValidationQuery) value));
        addTypeConverter(registry, org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLAdhocQueryRequest.class, org.husky.communication.ch.camel.chpharm1.requests.ChFindPrescriptionsQuery.class, false,
            (type, exchange, value) -> getChPharm1Converters().convertFindPrescriptionsQuery((org.husky.communication.ch.camel.chpharm1.requests.ChFindPrescriptionsQuery) value));
    }

    private static void addTypeConverter(TypeConverterRegistry registry, Class<?> toType, Class<?> fromType, boolean allowNull, SimpleTypeConverter.ConversionMethod method) { 
        registry.addTypeConverter(toType, fromType, new SimpleTypeConverter(allowNull, method));
    }

    private volatile org.husky.communication.ch.camel.chpharm1.transform.@Nullable ChPharm1Converters chPharm1Converters;
    private org.husky.communication.ch.camel.chpharm1.transform.@Nullable ChPharm1Converters getChPharm1Converters() {
        if (chPharm1Converters == null) {
            chPharm1Converters = new org.husky.communication.ch.camel.chpharm1.transform.ChPharm1Converters();
            CamelContextAware.trySetCamelContext(chPharm1Converters, camelContext);
        }
        return chPharm1Converters;
    }
}
