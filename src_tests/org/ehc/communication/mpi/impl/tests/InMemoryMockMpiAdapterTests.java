/*******************************************************************************
 * 
 * The authorship of this code and the accompanying materials is held by ahdis gmbh, Switzerland.
 * All rights reserved. http://ahdis.ch
 * 
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
 * 
 * This code is are made available under the terms of the Eclipse Public License v1.0.
 * 
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 3.0 Switzerland License.
 * 
 * Year of publication: 2015
 *******************************************************************************/
package org.ehc.communication.mpi.impl.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ehc.communication.mpi.FhirPatient;
import org.ehc.communication.mpi.impl.InMemoryMockMpiAdapter;
import org.junit.Before;
import org.junit.Test;

/**
 * Junit Tests for the ImMemoryMpiAdapter
 * 
 * @see org.ehc.communication.mpi.impl.InMemoryMockMpiAdapter
 * @author oliveregger
 * 
 */
public class InMemoryMockMpiAdapterTests {

  private Log log = LogFactory.getLog(InMemoryMockMpiAdapterTests.class);

  private InMemoryMockMpiAdapter mpiAdapter;

  @Before
  public void initMpi() {
    log.debug("init mpi");
    mpiAdapter = new InMemoryMockMpiAdapter();
    mpiAdapter.clear();
  }

  @Test
  public void useCase1RegisterNewPatientWithLocalId() {
    FhirPatient patient = TestPatient.getFhirPatientMueller();
    assertTrue(mpiAdapter.addPatient(patient));
    String patientId = mpiAdapter.queryPatientId(patient);
    assertTrue(patientId != null);
  }

  @Test
  public void useCase2UpdateNewPatientWithLocalId() {
    FhirPatient patient = TestPatient.getFhirPatientMueller();
    assertTrue(mpiAdapter.addPatient(patient));
    String patientId = mpiAdapter.queryPatientId(patient);
    patient.getName().get(0).getFamily().get(0).setValue("Müster");
    assertTrue(mpiAdapter.updatePatient(patient));
    String patientIdAfterUpdate = mpiAdapter.queryPatientId(patient);
    assertEquals(patientId, patientIdAfterUpdate);
  }

  @Test
  public void useCase3MergeNewPatientWithObsoleteId() {
    FhirPatient patient = TestPatient.getFhirPatientMueller();
    assertTrue(mpiAdapter.addPatient(patient));
    String mpiPatientId = mpiAdapter.queryPatientId(patient);
    FhirPatient patientObsolete = TestPatient.getFhirPatientMuellerObsoleteId();
    assertTrue(mpiAdapter.addPatient(patientObsolete));
    String mpiPatientIdObsolete = mpiAdapter.queryPatientId(patientObsolete);
    assertTrue(!mpiPatientId.equals(mpiPatientIdObsolete));
    assertTrue(mpiAdapter
        .mergePatient(patient, TestPatient.getTestPatientMuellerObsolete().localId));
    assertEquals(mpiPatientId, mpiAdapter.queryPatientId(patient));
    assertEquals(mpiPatientId, mpiAdapter.queryPatientId(patientObsolete));
  }

  @Test
  public void useCase4RegisterNewPatientWithLocalId() {
    FhirPatient patient = TestPatient.getFhirPatientMueller();
    assertTrue(mpiAdapter.addPatient(patient));
    String patientId = mpiAdapter.queryPatientId(patient);
    assertTrue(patientId != null);
  }

}