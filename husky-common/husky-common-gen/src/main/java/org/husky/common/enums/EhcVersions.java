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

package org.husky.common.enums;

/**
 * Enum that contains the different Versions of the eHealtConnector
 *
 */
public enum EhcVersions {
	/**
	 * OID eHealthConnector development edition (trunk)
	 */
	EHealthConnectorDev("2.16.756.5.30.1.139.1.1.3", "eHealthConnector trunk", "xxxxxxxx"),

	/**
	 * OID eHealthConnector Proof of Concept
	 */
	EHealthConnectorR201402("2.16.756.5.30.1.139.1.1.1", "eHealthConnector Proof of Concept",
			"20140211"),

	/**
	 * OID eHealthConnector R201503
	 */
	EHealthConnectorR201503("2.16.756.5.30.1.139.1.1.2", "eHealthConnector R201503", "2015-04-01"),

	/**
	 * OID eHealthConnector R201510
	 */
	EHealthConnectorR201510("2.16.756.5.30.1.139.1.1.4", "eHealthConnector R201510", "2015-10-31"),

	/**
	 * OID eHealthConnector R201604
	 */
	EHealthConnectorR201604("2.16.756.5.30.1.139.1.1.5", "eHealthConnector R201604", "2016-04-30"),

	/**
	 * OID eHealthConnector R201704
	 */
	EHealthConnectorR201704("2.16.756.5.30.1.139.1.1.6", "eHealthConnector R201704", "2017-04-30"),

	/**
	 * OID eHealthConnector R201711
	 */
	EHealthConnectorR201711("2.16.756.5.30.1.139.1.1.7", "eHealthConnector R201711", "2017-11-30"),

	/**
	 * OID eHealthConnector R201807
	 */
	EHealthConnectorR201807("2.16.756.5.30.1.139.1.1.8", "eHealthConnector R201807", "2018-07-24"),

	/**
	 * OID eHealthConnector R201909
	 */
	EHealthConnectorR201909("2.16.756.5.30.1.139.1.1.9", "eHealthConnector R201909", "2019-09-17"),

	/**
	 * OID eHealthConnector R202007
	 */
	EHealthConnectorR202007("2.16.756.5.30.1.139.1.1.10", "eHealthConnector R202007", "2020-07-15"),

	/**
	 * OID eHealthConnector Rxxxxxx
	 */
	// This is preparation for next release, only
	EHealthConnectorRxxxxxx("2.16.756.5.30.1.139.1.1.11", "eHealthConnector Rxxxxxx",
			"todo release date");

	public static EhcVersions getCurrentVersion() {
		return EhcVersions.EHealthConnectorRxxxxxx;
	}

	private String oid;
	private String releaseDate;

	private String systemVersionName;

	private EhcVersions(String oid, String systemVersionName, String releaseDate) {
		this.oid = oid;
		this.systemVersionName = systemVersionName;
		this.releaseDate = releaseDate;
	}

	public String getOid() {
		return oid;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public String getSystemVersionName() {
		return systemVersionName;
	}

}
