package com.ubikgs.androidsensors.persistence.daos.imu;

import com.ubikgs.androidsensors.persistence.DaggerTestBedComponent;
import com.ubikgs.androidsensors.persistence.daos.SensorRecordEntityDaoIntegrationTest;
import com.ubikgs.androidsensors.persistence.entities.imu.GravityRecordEntity;

import org.junit.Before;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
public class GravityRecordEntityDaoIntegrationTest
        extends SensorRecordEntityDaoIntegrationTest<GravityRecordEntity> {

    @Inject GravityRecordEntityDao gravityRecordEntityDao;

    private GravityRecordEntity entity1;
    private GravityRecordEntity entity2;

    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);

        sensorRecordEntityDao = gravityRecordEntityDao;

        entity1 = new GravityRecordEntity();
        entity1.setAccuracy(1.0f);
        entity1.setSensorTimestamp(new Date().getTime());
        entity1.setSystemTimestamp(new Date().getTime());
        entity1.setX(1.0f);
        entity1.setY(1.0f);
        entity1.setZ(1.0f);
        entity1.setForeignKey(foreignKeyStore.registerUsage(0));

        entity2 = new GravityRecordEntity();
        entity2.setAccuracy(1.0f);
        entity2.setSensorTimestamp(new Date().getTime());
        entity2.setSystemTimestamp(new Date().getTime());
        entity2.setX(1.0f);
        entity2.setY(1.0f);
        entity2.setZ(1.0f);
        entity2.setForeignKey(foreignKeyStore.registerUsage(1));
    }

    protected List<Long> createEntities() {
        return gravityRecordEntityDao.createAll(Arrays.asList(entity1, entity2));
    }
}