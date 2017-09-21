package com.ubikgs.androidsensors.persistence.daos.gps;

import com.ubikgs.androidsensors.persistence.DaggerTestBedComponent;
import com.ubikgs.androidsensors.persistence.daos.SensorRecordEntityDaoIntegrationTest;
import com.ubikgs.androidsensors.persistence.entities.gps.RawGPSNavigationRecordEntity;

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
public class RawGPSNavigationRecordEntityDaoIntegrationTest extends SensorRecordEntityDaoIntegrationTest<RawGPSNavigationRecordEntity> {

    @Inject RawGPSNavigationRecordEntityDao rawGPSNavigationRecordEntityDao;

    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);
        sensorRecordEntityDao = rawGPSNavigationRecordEntityDao;

        RawGPSNavigationRecordEntity entity1 = new RawGPSNavigationRecordEntity();
        entity1.setAccuracy(1.0f);
        entity1.setSensorTimestamp(new Date().getTime());
        entity1.setSystemTimestamp(new Date().getTime());
        entity1.setMessage("Type = Glonass L1 C/A, Svid = 106, Status = ParityPassed, " +
                "MessageId = -1, SubmessageId = 10, " +
                "Data = {85, 108, 0, -33, 75, -112, -63, -8, 25, 25, 56}");
        entity1.setForeignKey(registerForeignKeyUsage(0));

        RawGPSNavigationRecordEntity entity2 = new RawGPSNavigationRecordEntity();
        entity2.setAccuracy(1.0f);
        entity2.setSensorTimestamp(new Date().getTime());
        entity2.setSystemTimestamp(new Date().getTime());
        entity2.setMessage("Type = Glonass L1 C/A, Svid = 96, Status = ParityPassed, " +
                "MessageId = -1, SubmessageId = 12, " +
                "Data = {101, 116, -16, -64, -124, 32, -27, 20, 53, 88, -8}");
        entity2.setForeignKey(registerForeignKeyUsage(0));

        registerTestEntity(entity1);
        registerTestEntity(entity2);
    }

}