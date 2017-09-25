package com.ubikgs.androidsensors.persistence.daos.imu;

import com.ubikgs.androidsensors.persistence.DaggerTestBedComponent;
import com.ubikgs.androidsensors.persistence.daos.SensorRecordEntityDaoIntegrationTest;
import com.ubikgs.androidsensors.persistence.entities.imu.MagneticFieldRecordEntity;

import org.junit.Before;

import javax.inject.Inject;

import static com.ubikgs.androidsensors.persistence.testutils.TestEntityProvider.createMagneticFieldRecordEntity;

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
public class MagneticFieldRecordEntityDaoIntegrationTest extends SensorRecordEntityDaoIntegrationTest<MagneticFieldRecordEntity> {

    @Inject MagneticFieldRecordEntityDao magneticFieldRecordEntityDao;

    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);
        sensorRecordEntityDao = magneticFieldRecordEntityDao;

        MagneticFieldRecordEntity entity1 = createMagneticFieldRecordEntity();
        entity1.setForeignKey(registerForeignKeyUsage(0));
        registerTestEntity(entity1);

        MagneticFieldRecordEntity entity2 = createMagneticFieldRecordEntity();
        entity2.setForeignKey(registerForeignKeyUsage(1));
        registerTestEntity(entity2);

    }
}