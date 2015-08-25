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
package org.ehealth_connector.communication;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ehealth_connector.cda.ch.enums.LanguageCode;
import org.ehealth_connector.cda.enums.Confidentiality;
import org.ehealth_connector.common.Code;
import org.ehealth_connector.common.Identificator;
import org.ehealth_connector.communication.ch.enums.AvailabilityStatus;
import org.ehealth_connector.communication.ch.storedquery.*;
import org.ehealth_connector.communication.storedquery.FindDocumentsQuery;
import org.ehealth_connector.communication.storedquery.GetDocumentsQuery;
import org.openhealthtools.ihe.xds.consumer.storedquery.MalformedStoredQueryException;
import org.openhealthtools.ihe.xds.document.DocumentDescriptor;
import org.openhealthtools.ihe.xds.document.XDSDocument;
import org.openhealthtools.ihe.xds.metadata.DocumentEntryType;
import org.openhealthtools.ihe.xds.response.XDSQueryResponseType;
import org.openhealthtools.ihe.xds.response.XDSResponseType;
import org.openhealthtools.ihe.xds.response.XDSRetrieveResponseType;

public class ConvenienceCommunicationTest {

	// NIST Repository
	public static final String NIST = "http://ihexds.nist.gov/tf6/services/xdsrepositoryb";

	public static final String NIST_CONSUMER = "http://localhost:8888/xdstools2/sim/b5e2987f-ceaf-44dd-91a3-903c202f9812/rep/rb";

	// NIST SECURED Repository (query interface)
	public static final String NIST_SECURED = "https://ihexds.nist.gov:12091/tf6/services/xdsrepositoryb";
	// Keystore and Truststore for secured communication (in this example, we
	// use one keystore file for those two)
	public static final String KEY_STORE = "../demo/java/ehealthconnectorDemo/rsc/demoDocSource/security/keystore";
	public static final String KEY_STORE_PASS = "nistbill";
	public static final String TRUST_STORE = "../demo/java/ehealthconnectorDemo/rsc/demoDocSource/security/keystore";
	public static final String TRUST_STORE_PASS = "nistbill";

	// The ID of your Organization
	public static final String ORGANIZATIONAL_ID = "1.3.6.1.4.1.21367.2010.1.2.666";

	// One PDF and one CDA Document that will be transfered
	public static final String pdfFileName = "patientconsent.pdf";
	public static final String cdaFilePath = "../demo/java/ehealthconnectorDemo/rsc/demoDocSource/CDA-CH-VACD_Impfausweis.xml";
	private final Log log = LogFactory.getLog(ConvenienceCommunicationTest.class);
	
	
	java.net.URI repUri;
	Destination dest;
	ConvenienceCommunication c;
	XDSQueryResponseType qr;

	@Before
	public void init() {
		try {
			repUri = new java.net.URI(NIST_SECURED);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		dest = new Destination(ORGANIZATIONAL_ID, repUri, KEY_STORE, KEY_STORE_PASS, TRUST_STORE,
				TRUST_STORE_PASS);

		try {
			c = new ConvenienceCommunication(dest, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddDocument() {
		try {
			DocumentMetadata d = c.addDocument(DocumentDescriptor.CDA_R2, cdaFilePath);
			assertNotNull(d.getMdhtDocumentEntryType().getEntryUUID());
			assertNotNull(c.getTxnData());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testConstructors() {
		// Set System Properties to null
		System.setProperty("javax.net.ssl.keyStore", "Null");
		System.setProperty("javax.net.ssl.keyStorePassword", "Null");
		System.setProperty("javax.net.ssl.trustStore", "Null");
		System.setProperty("javax.net.ssl.trustStorePassword", "Null");

		try {
			c = new ConvenienceCommunication(dest, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Check if the System Properties have been set
		assertEquals(KEY_STORE, System.getProperty("javax.net.ssl.keyStore"));
		assertEquals(KEY_STORE_PASS, System.getProperty("javax.net.ssl.keyStorePassword"));
		assertEquals(TRUST_STORE, System.getProperty("javax.net.ssl.trustStore"));
		assertEquals(TRUST_STORE_PASS, System.getProperty("javax.net.ssl.trustStorePassword"));
	}

	//@Test
	public void testXdsConsumerQueries() {
		//Create query
		try {
			String host = "ahdis-ihetest.cloudapp.net:8100/";
			String registryUrl = "http://" + host
					+ "/xdstools2/sim/305cd4eb-1724-45ae-b489-d10678342c97/reg/sq";
			String repositoryUrl = "http://" + host
					+ "/xdstools2/sim/305cd4eb-1724-45ae-b489-d10678342c97/rep/prb";
			dest.setRepositoryUri(URI.create(registryUrl));

			FindDocumentsQuery q = new FindDocumentsQuery(new Identificator("1.3.6.1.4.1.21367.13.20.2005.1000", "IHERED-1644"), null, null, null, null, null, null, null, AvailabilityStatus.APPROVED);
			XDSQueryResponseType qr = c.invokeStoredQuery(q, false);

			if (qr.getErrorList() != null) {
				log.info("ERRORS: "+qr.getErrorList().toString());
			}
			else {
				log.info("No Errors.");
			}
			log.info("Found Documents: "+qr.getDocumentEntryResponses().size());
			assertEquals(qr.getErrorList(),null);
		} catch (MalformedStoredQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
