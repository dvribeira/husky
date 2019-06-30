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
package org.ehealth_connector.valueset.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.ehealth_connector.valueset.config.CustomizedYaml;
import org.ehealth_connector.valueset.config.ValueSetConfig;
import org.ehealth_connector.valueset.config.ValueSetPackageConfig;
import org.ehealth_connector.valueset.enums.ValueSetPackageStatus;
import org.ehealth_connector.valueset.exceptions.ConfigurationException;
import org.ehealth_connector.valueset.exceptions.InitializationException;
import org.ehealth_connector.valueset.model.ValueSet;
import org.ehealth_connector.valueset.model.ValueSetPackage;
import org.xml.sax.SAXException;

/**
 * The Class ValueSetPackageManager.
 */
public class ValueSetPackageManager {

	/**
	 * <div class="en">The default encoding used to encode URL parameter.</div>
	 */
	private static final String UTF8_ENCODING = "UTF-8";

	/** The value set package config list. */
	private ArrayList<ValueSetPackageConfig> valueSetPackageConfigList;

	/**
	 * Adds a value set package config.
	 *
	 * @param value
	 *            the value
	 */
	public void addValueSetPackageConfig(ValueSetPackageConfig value) {
		if (this.valueSetPackageConfigList == null) {
			this.valueSetPackageConfigList = new ArrayList<ValueSetPackageConfig>();
		}
		this.valueSetPackageConfigList.add(value);

	}

	/**
	 * Clear value set package config list.
	 */
	public void clearValueSetPackageConfigList() {
		this.valueSetPackageConfigList = new ArrayList<ValueSetPackageConfig>();
	}

	public ValueSetPackage downloadValueSetPackage(ValueSetPackageConfig valueSetPackageConfig,
			String pathName) throws MalformedURLException, IOException,
			ParserConfigurationException, SAXException, InitializationException {
		ValueSetPackage retVal = ValueSetPackage.builder()
				.withDescription(valueSetPackageConfig.getDescription())
				.withIdentificator(valueSetPackageConfig.getIdentificator())
				.withSourceUrl(valueSetPackageConfig.getSourceUrl())
				.withStatus(valueSetPackageConfig.getStatus())
				.withVersion(valueSetPackageConfig.getVersion()).build();

		ValueSetManager valueSetManager = new ValueSetManager();

		for (ValueSetConfig valueSetConfig : valueSetPackageConfig.listValueSetConfigs()) {
			ValueSet valueSet = valueSetManager.downloadValueSet(valueSetConfig);
			retVal.addValueSet(valueSet);
		}
		return retVal;
	}

	/**
	 * Download value set package config.
	 *
	 * @param sourceUrl
	 *            the source url
	 * @return the value set package config
	 * @throws MalformedURLException
	 *             the malformed URL exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ConfigurationException
	 *             the configuration exception
	 */
	public ValueSetPackageConfig downloadValueSetPackageConfig(String sourceUrl)
			throws MalformedURLException, IOException, ConfigurationException {
		return downloadValueSetPackageConfig(new URL(sourceUrl));
	}

	/**
	 * Download value set package config.
	 *
	 * @param sourceUrl
	 *            the source url
	 * @return the value set package config
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ConfigurationException
	 *             the configuration exception
	 */
	public ValueSetPackageConfig downloadValueSetPackageConfig(URL sourceUrl)
			throws IOException, ConfigurationException {
		ValueSetPackageConfig retVal = null;
		// download a package config
		String downloadedString = IOUtils.toString(sourceUrl, UTF8_ENCODING);
		retVal = loadValueSetPackageConfig(IOUtils.toInputStream(downloadedString));
		return retVal;
	}

	/**
	 * Gets the latest value set package config. The timestamps validFrom and
	 * validTo are taken in charge for the selection. If multiple entries have
	 * the same conditions, one of them will be returned without further checks.
	 *
	 * @return the latest value set package config
	 */
	public ValueSetPackageConfig getLatestValueSetPackageConfig() {
		return getLatestValueSetPackageConfigByStatus(null);
	}

	/**
	 * Gets the latest value set package config by status. The timestamps
	 * validFrom and validTo are taken in charge for the selection and elements
	 * not having the given status will be ignored. If multiple entries have the
	 * same conditions, one of them will be returned without further checks.
	 *
	 *
	 * @param status
	 *            the status to be verified
	 * @return the latest value set package config by status
	 */
	public ValueSetPackageConfig getLatestValueSetPackageConfigByStatus(
			ValueSetPackageStatus status) {
		return getValueSetPackageConfigByStatusAndDate(status, null);
	}

	/**
	 * Gets the value set package config by status and date. Elements not having
	 * the given status and elements that are not valid at the given time will
	 * be ignored. From the remaining elements, the latest one will be returned
	 * (If multiple entries have the same conditions, one of them will be
	 * returned without further checks.)
	 *
	 * @param status
	 *            the status
	 * @param date
	 *            the date
	 * @return the value set package config by status and date
	 */
	public ValueSetPackageConfig getValueSetPackageConfigByStatusAndDate(
			ValueSetPackageStatus status, Date date) {
		ValueSetPackageConfig retVal = null;
		boolean isCandidate = false;
		Date latestFrom = null;
		Date latestTo = null;
		if (valueSetPackageConfigList != null) {
			for (ValueSetPackageConfig valueSetPackageConfig : valueSetPackageConfigList) {
				isCandidate = false;
				boolean ignoreStatus = (status == null);
				boolean ignoreDate = (date == null);

				if ((ignoreStatus) || (status == valueSetPackageConfig.getStatus())) {
					Date from = valueSetPackageConfig.getVersion().getValidFrom();
					Date to = valueSetPackageConfig.getVersion().getValidTo();

					boolean dateFits = (date == null);
					if (!dateFits) {
						if (from != null)
							dateFits = ((date.equals(from)) || (date.after(from)));
						if (dateFits) {
							if (to != null)
								dateFits = ((date.equals(to)) || (date.before(to)));
						}
					}

					if (ignoreDate || dateFits) {

						if (retVal == null)
							retVal = valueSetPackageConfig;

						if (from != null) {
							if (latestFrom == null)
								latestFrom = from;
						} else
							isCandidate = true;

						if (to != null) {
							if (latestTo == null)
								latestTo = to;
						} else {
							// from null and to null => this always valid
							// -> the first entry makes it
							if (isCandidate)
								retVal = valueSetPackageConfig;
							isCandidate = true;
						}

						if (from != null) {
							if (from.after(latestFrom)) {
								latestFrom = from;
								// in this case, a from candidate with a later
								// from
								// date
								// will
								// get the new choice
								if (isCandidate)
									retVal = valueSetPackageConfig;
								isCandidate = true;
							} else
								isCandidate = false;
						}

						if (to != null) {
							if (to.after(latestTo)) {
								latestTo = to;
								// in this case, a from candidate with a later
								// to
								// date
								// will
								// get the new choice
								if (isCandidate)
									retVal = valueSetPackageConfig;
								isCandidate = true;
							}
						} else if (isCandidate)
							// in this case, a from candidate with a null to
							// date
							// will
							// get the new choice
							retVal = valueSetPackageConfig;
					}
				}
			}
		}

		return retVal;

	}

	/**
	 * List value set package config.
	 *
	 * @return the list
	 */
	public List<ValueSetPackageConfig> listValueSetPackageConfigs() {
		if (this.valueSetPackageConfigList == null) {
			this.valueSetPackageConfigList = new ArrayList<ValueSetPackageConfig>();
		}
		return this.valueSetPackageConfigList;

	}

	/**
	 * Load value set package.
	 *
	 * @param valueSetPackage
	 *            the value set package
	 * @return the value set package
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public ValueSetPackage loadValueSetPackage(File valueSetPackage) throws FileNotFoundException {
		return loadValueSetPackage(new FileInputStream(valueSetPackage));
	}

	/**
	 * Load value set package.
	 *
	 * @param valueSetPackage
	 *            the value set package
	 * @return the value set package
	 */
	public ValueSetPackage loadValueSetPackage(InputStream valueSetPackage) {
		return loadValueSetPackage(new InputStreamReader(valueSetPackage, Charsets.UTF_8));
	}

	/**
	 * Load value set package.
	 *
	 * @param reader
	 *            the reader
	 * @return the value set package
	 */
	public ValueSetPackage loadValueSetPackage(InputStreamReader reader) {
		ValueSetPackage valueSetPackage = CustomizedYaml.getCustomizedYaml().loadAs(reader,
				ValueSetPackage.class);
		return valueSetPackage;

	}

	/**
	 * Load value set package.
	 *
	 * @param fileName
	 *            the file name
	 * @return the value set package
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws ConfigurationException
	 *             the configuration exception
	 */
	public ValueSetPackage loadValueSetPackage(String fileName)
			throws FileNotFoundException, ConfigurationException {
		return loadValueSetPackage(new File(fileName));

	}

	/**
	 * Load value set package config.
	 *
	 * @param config
	 *            the config
	 * @return the value set package config
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws ConfigurationException
	 *             the configuration exception
	 */
	public ValueSetPackageConfig loadValueSetPackageConfig(File config)
			throws FileNotFoundException, ConfigurationException {
		return loadValueSetPackageConfig(new FileInputStream(config));
	}

	/**
	 * Load value set package config.
	 *
	 * @param config
	 *            the config
	 * @return the value set package config
	 * @throws ConfigurationException
	 *             the configuration exception
	 */
	public ValueSetPackageConfig loadValueSetPackageConfig(InputStream config)
			throws ConfigurationException {
		return loadValueSetPackageConfig(new InputStreamReader(config, Charsets.UTF_8));
	}

	/**
	 * Load value set package config.
	 *
	 * @param reader
	 *            the reader
	 * @return the value set package config
	 * @throws ConfigurationException
	 *             the configuration exception
	 */
	public ValueSetPackageConfig loadValueSetPackageConfig(InputStreamReader reader)
			throws ConfigurationException {

		if (this.valueSetPackageConfigList == null) {
			this.valueSetPackageConfigList = new ArrayList<ValueSetPackageConfig>();
		}

		ValueSetPackageConfig valueSetPackageConfig = CustomizedYaml.getCustomizedYaml()
				.loadAs(reader, ValueSetPackageConfig.class);

		if (valueSetPackageConfig.getVersion() == null)
			throw new ConfigurationException(
					"ValueSetPackageConfig must contain a version element");
		else if (valueSetPackageConfig.getVersion().getValidFrom() == null)
			throw new ConfigurationException(
					"ValueSetPackageConfig must contain a version element, having a validFrom timestamp");

		valueSetPackageConfigList.add(valueSetPackageConfig);
		return valueSetPackageConfig;

	}

	/**
	 * Load value set package config.
	 *
	 * @param fileName
	 *            the file name
	 * @return the value set package config
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws ConfigurationException
	 *             the configuration exception
	 */
	public ValueSetPackageConfig loadValueSetPackageConfig(String fileName)
			throws FileNotFoundException, ConfigurationException {
		return loadValueSetPackageConfig(new File(fileName));

	}

	/**
	 * Save value set package.
	 *
	 * @param valueSetPackage
	 *            the value set package
	 * @param file
	 *            the file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void saveValueSetPackage(ValueSetPackage valueSetPackage, File file) throws IOException {
		FileUtils.writeByteArrayToFile(file, CustomizedYaml.getCustomizedYaml()
				.dumpAsMap(valueSetPackage).getBytes(Charsets.UTF_8));
	}

	/**
	 * Save value set package.
	 *
	 * @param valueSetPackage
	 *            the value set package
	 * @param fileName
	 *            the file name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void saveValueSetPackage(ValueSetPackage valueSetPackage, String fileName)
			throws IOException {
		saveValueSetPackage(valueSetPackage, new File(fileName));
	}

	/**
	 * Save value set package config.
	 *
	 * @param config
	 *            the config
	 * @param file
	 *            the file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void saveValueSetPackageConfig(ValueSetPackageConfig config, File file)
			throws IOException {
		FileUtils.writeByteArrayToFile(file,
				CustomizedYaml.getCustomizedYaml().dumpAsMap(config).getBytes(Charsets.UTF_8));
	}

	/**
	 * Save value set package config.
	 *
	 * @param config
	 *            the config
	 * @param fileName
	 *            the file name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void saveValueSetPackageConfig(ValueSetPackageConfig config, String fileName)
			throws IOException {
		saveValueSetPackageConfig(config, new File(fileName));
	}

}
