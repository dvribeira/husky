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
package org.ehealth_connector.communication.ch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;

import org.ehealth_connector.common.Identificator;
import org.ehealth_connector.common.ch.AuthorCh;
import org.ehealth_connector.common.ch.enums.AuthorRole;
import org.ehealth_connector.common.communication.AffinityDomain;
import org.ehealth_connector.common.communication.AtnaConfig.AtnaConfigMode;
import org.ehealth_connector.common.communication.DocumentMetadata.DocumentMetadataExtractionMode;
import org.ehealth_connector.common.communication.SubmissionSetMetadata;
import org.ehealth_connector.common.communication.SubmissionSetMetadata.SubmissionSetMetadataExtractionMode;
import org.ehealth_connector.common.enums.DocumentDescriptor;
import org.ehealth_connector.common.utils.Util;
import org.ehealth_connector.communication.ConvenienceCommunication;
import org.ehealth_connector.communication.ch.enums.AvailabilityStatus;
import org.ehealth_connector.communication.ch.xd.storedquery.FindDocumentsQuery;
import org.ehealth_connector.communication.exceptions.DocumentNotAccessibleException;
import org.ehealth_connector.xua.core.SecurityHeaderElement;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Document;
import org.openehealth.ipf.commons.ihe.xds.core.requests.query.QueryReturnType;
import org.openehealth.ipf.commons.ihe.xds.core.responses.QueryResponse;
import org.openehealth.ipf.commons.ihe.xds.core.responses.Response;

/**
 *
 * Implementation of ch specific convenience communication
 *
 */
public class ConvenienceCommunicationCh extends ConvenienceCommunication {

	/**
	 * Default constructor to instanciate the object
	 */
	public ConvenienceCommunicationCh() {
		super();
	}

	/**
	 * Default constructor to instanciate the object
	 *
	 * @param affinityDomain
	 *            the affinity Domain
	 */
	public ConvenienceCommunicationCh(AffinityDomain affinityDomain) {
		super(affinityDomain);
	}

	/**
	 * Instantiates a new convenience communication ch.
	 *
	 * @param affinityDomain
	 *            the affinity domain
	 * @param atnaConfigMode
	 *            the atna config mode
	 * @param documentMetadataExtractionMode
	 *            the document metadata extraction mode
	 * @param submissionSetMetadataExtractionMode
	 *            the submission set metadata extraction mode
	 */
	public ConvenienceCommunicationCh(AffinityDomain affinityDomain, AtnaConfigMode atnaConfigMode,
			DocumentMetadataExtractionMode documentMetadataExtractionMode,
			SubmissionSetMetadataExtractionMode submissionSetMetadataExtractionMode) {
		super(affinityDomain, atnaConfigMode, documentMetadataExtractionMode,
				submissionSetMetadataExtractionMode);
	}

	/**
	 * <div class="en">Adds a document to the XDS Submission set.
	 *
	 * @param desc
	 *            the document descriptor (which kind of document do you want to
	 *            transfer? e.g. PDF, CDA,...)
	 * @param inputStream
	 *            the input stream to the document
	 * @return the document metadata (which have to be completed)</div>
	 */
	public DocumentMetadataCh addChDocument(DocumentDescriptor desc, InputStream inputStream) {
		return addChDocument(desc, inputStream, null);
	}

	/**
	 * <div class="en">Adds a document to the XDS Submission set.
	 *
	 * @param desc
	 *            the document descriptor (which kind of document do you want to
	 *            transfer? e.g. PDF, CDA,...)
	 * @param inputStream
	 *            the input stream to the document
	 * @param inputStream4Metadata
	 *            the input stream 4 metadata
	 * @return the document metadata (which have to be completed)</div>
	 */
	public DocumentMetadataCh addChDocument(DocumentDescriptor desc, InputStream inputStream,
			InputStream inputStream4Metadata) {
		DocumentMetadataCh retVal = null;
		if (inputStream == null)
			try {
				throw new DocumentNotAccessibleException();
			} catch (final DocumentNotAccessibleException e) {
				e.printStackTrace();
			}
		var doc = new Document();
		try {
			var doc4Metadata = new Document();
			if (inputStream4Metadata != null) {
				InputStream unicodeStream = Util.convertNonAsciiText2Unicode(inputStream4Metadata);
				var dataSource = new ByteArrayDataSource(unicodeStream, desc.getMimeType());
				doc4Metadata.setDataHandler(new DataHandler(dataSource));
			}
			var dataSource = new ByteArrayDataSource(inputStream, desc.getMimeType());
			doc.setDataHandler(new DataHandler(dataSource));
			retVal = new DocumentMetadataCh(addXdsDocument(doc, desc, doc4Metadata));
		} catch (final IOException e) {
			e.printStackTrace();
		}
		if (retVal != null)
			retVal.setDocumentDescriptor(desc);
		return retVal;
	}

	/**
	 * <div class="en">Adds a document to the XDS Submission set.
	 *
	 * @param desc
	 *            the document descriptor (which kind of document do you want to
	 *            transfer? e.g. PDF, CDA,...)
	 * @param filePath
	 *            the file path
	 * @return the document metadata (which have to be completed)</div>
	 * @throws FileNotFoundException
	 *             exception
	 */
	public DocumentMetadataCh addChDocument(DocumentDescriptor desc, String filePath)
			throws FileNotFoundException {
		return addChDocument(desc, new FileInputStream(new File(filePath)));
	}

	/**
	 * <div class="en">Queries the registry of the affinity domain for all documents
	 * of one patient. This is useful if the number of results is limited in the
	 * registry and your query would exceed this limit. In this case, precise your
	 * query or do a query for references first, choose the possible matches (e.g.
	 * the last 10 results) and then query for metadata.
	 *
	 * @param patientId the ID of the patient
	 * @return the OHT XDSQueryResponseType containing references instead of the
	 *         complete document metadata</div>
	 * @throws Exception
	 */
	public QueryResponse queryDocumentReferencesOnly(Identificator patientId, SecurityHeaderElement security)
			throws Exception {
		return queryDocumentsReferencesOnly(new FindDocumentsQuery(patientId, AvailabilityStatus.APPROVED), security);
	}

	/**
	 * <div class="en">Queries the registry of the affinity domain for all documents
	 * of one patient.
	 *
	 * @param patientId the ID of the patient
	 * @return the OHT XDSQueryResponseType containing full document metadata</div>
	 * @throws Exception
	 */
	public QueryResponse queryDocuments(Identificator patientId, SecurityHeaderElement security, boolean secure)
			throws Exception {
		return queryDocumentQuery(new FindDocumentsQuery(patientId, AvailabilityStatus.APPROVED), security,
				QueryReturnType.LEAF_CLASS, secure);
	}

	/**
	 * <div class="en">Submission of the previously prepared document(s) to the
	 * repository<br>
	 * IHE [ITI-41] Provide and Register Document Set – b in the role of the IHE
	 * ITI Document Source actor
	 *
	 * @param authorRole
	 *            The AuthorRole is one of the minimal required information
	 *            according to IHE Suisse for classification of documents in
	 *            Switzerland.
	 * @return the OHT XDSResponseType</div>
	 * @throws Exception
	 *             if the transfer is not successful
	 */
	public Response submit(AuthorRole authorRole, SecurityHeaderElement security) throws Exception {
		final var subSet = new SubmissionSetMetadata(getTxnData().getSubmissionSet());
		final var author = new AuthorCh();
		author.setRoleFunction(authorRole);
		subSet.setAuthor(author);
		return submit(subSet, security);
	}

}
