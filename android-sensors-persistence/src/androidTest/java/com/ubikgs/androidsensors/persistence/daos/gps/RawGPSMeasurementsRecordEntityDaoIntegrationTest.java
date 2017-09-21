package com.ubikgs.androidsensors.persistence.daos.gps;

import com.ubikgs.androidsensors.persistence.DaggerTestBedComponent;
import com.ubikgs.androidsensors.persistence.daos.SensorRecordEntityDaoIntegrationTest;
import com.ubikgs.androidsensors.persistence.entities.gps.RawGPSMeasurementsRecordEntity;

import org.junit.Before;

import java.util.Date;

import javax.inject.Inject;

/**
 * Copyright 2017 Alberto González Pérez
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * <p>http://www.apache.org/licenses/LICENSE-2.0</p>
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class RawGPSMeasurementsRecordEntityDaoIntegrationTest extends SensorRecordEntityDaoIntegrationTest<RawGPSMeasurementsRecordEntity> {

    @Inject RawGPSMeasurementsRecordEntityDao rawGPSMeasurementsRecordEntityDao;

    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);
        sensorRecordEntityDao = rawGPSMeasurementsRecordEntityDao;

        RawGPSMeasurementsRecordEntity entity1 = new RawGPSMeasurementsRecordEntity();
        entity1.setAccuracy(1.0f);
        entity1.setSensorTimestamp(new Date().getTime());
        entity1.setSystemTimestamp(new Date().getTime());
        entity1.setSatelliteCount(1);
        entity1.setSvids(new int[]{1});
        entity1.setConstellations(new int[]{1});
        entity1.setTimeOffsets(new double[]{1.0f});
        entity1.setStateCodes(new int[]{1});
        entity1.setSvTimes(new long[]{1L});
        entity1.setSvTimeUncerts(new long[]{1L});
        entity1.setCn0DbHzs(new double[]{1.0f});
        entity1.setPseudoranges(new double[]{1.0f});
        entity1.setPseudorangeUncerts(new double[]{1.0f});
        entity1.setDeltaStates(new int[]{1});
        entity1.setDeltas(new double[]{1.0f});
        entity1.setDeltaUncerts(new double[]{1.0f});
        entity1.setMultipaths(new int[]{1});
        entity1.setForeignKey(registerForeignKeyUsage(0));

        RawGPSMeasurementsRecordEntity entity2 = new RawGPSMeasurementsRecordEntity();
        entity2.setAccuracy(1.0f);
        entity2.setSensorTimestamp(new Date().getTime());
        entity2.setSystemTimestamp(new Date().getTime());
        entity2.setSatelliteCount(1);
        entity2.setSvids(new int[]{1});
        entity2.setConstellations(new int[]{1});
        entity2.setTimeOffsets(new double[]{1.0f});
        entity2.setStateCodes(new int[]{1});
        entity2.setSvTimes(new long[]{1L});
        entity2.setSvTimeUncerts(new long[]{1L});
        entity2.setCn0DbHzs(new double[]{1.0f});
        entity2.setPseudoranges(new double[]{1.0f});
        entity2.setPseudorangeUncerts(new double[]{1.0f});
        entity2.setDeltaStates(new int[]{1});
        entity2.setDeltas(new double[]{1.0f});
        entity2.setDeltaUncerts(new double[]{1.0f});
        entity2.setMultipaths(new int[]{1});
        entity2.setForeignKey(registerForeignKeyUsage(1));

        RawGPSMeasurementsRecordEntity entity3 = new RawGPSMeasurementsRecordEntity();
        entity3.setAccuracy(1.0f);
        entity3.setSensorTimestamp(new Date().getTime());
        entity3.setSystemTimestamp(new Date().getTime());
        entity3.setSatelliteCount(1);
        entity3.setSvids(new int[]{});
        entity3.setConstellations(new int[]{1,2});
        entity3.setTimeOffsets(new double[]{});
        entity3.setStateCodes(new int[]{1});
        entity3.setSvTimes(new long[]{});
        entity3.setSvTimeUncerts(new long[]{1L, 2L});
        entity3.setCn0DbHzs(new double[]{1.0f});
        entity3.setPseudoranges(new double[]{1.0f, 2.0f});
        entity3.setPseudorangeUncerts(new double[]{1.0f});
        entity3.setDeltaStates(new int[]{1});
        entity3.setDeltas(new double[]{1.0f});
        entity3.setDeltaUncerts(new double[]{1.0f});
        entity3.setMultipaths(new int[]{1});
        entity3.setForeignKey(registerForeignKeyUsage(1));

        registerTestEntity(entity1);
        registerTestEntity(entity2);
        registerTestEntity(entity3);
    }

}