package com.ubikgs.androidsensors.persistence.daos.gps;

import com.ubikgs.androidsensors.persistence.DaggerTestBedComponent;
import com.ubikgs.androidsensors.persistence.daos.SensorRecordEntityDaoIntegrationTest;
import com.ubikgs.androidsensors.persistence.entities.gps.LocationRecordEntity;

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
public class LocationRecordEntityDaoIntegrationTest extends SensorRecordEntityDaoIntegrationTest<LocationRecordEntity> {

    @Inject LocationRecordEntityDao locationRecordEntityDao;

    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);
        sensorRecordEntityDao = locationRecordEntityDao;

        LocationRecordEntity entity1 = new LocationRecordEntity();
        entity1.setAccuracy(1.0f);
        entity1.setSensorTimestamp(new Date().getTime());
        entity1.setSystemTimestamp(new Date().getTime());
        entity1.setLatitude(1.0f);
        entity1.setLongitude(1.0f);
        entity1.setAltitude(1.0f);
        entity1.setSpeed(1.0f);
        entity1.setBearing(1.0f);
        entity1.setForeignKey(registerForeignKeyUsage(0));

        LocationRecordEntity entity2 = new LocationRecordEntity();
        entity2.setAccuracy(1.0f);
        entity2.setSensorTimestamp(new Date().getTime());
        entity2.setSystemTimestamp(new Date().getTime());
        entity2.setLatitude(1.0f);
        entity2.setLongitude(1.0f);
        entity2.setAltitude(1.0f);
        entity2.setSpeed(1.0f);
        entity2.setBearing(1.0f);
        entity2.setForeignKey(registerForeignKeyUsage(1));

        registerTestEntity(entity1);
        registerTestEntity(entity2);
    }

}