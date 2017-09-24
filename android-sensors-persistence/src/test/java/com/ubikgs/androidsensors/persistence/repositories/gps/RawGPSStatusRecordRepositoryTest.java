package com.ubikgs.androidsensors.persistence.repositories.gps;

import com.ubikgs.androidsensors.persistence.daos.gps.RawGPSStatusRecordEntityDao;
import com.ubikgs.androidsensors.persistence.entities.gps.RawGPSStatusRecordEntity;
import com.ubikgs.androidsensors.persistence.repositories.SensorRecordRepositoryTest;
import com.ubikgs.androidsensors.records.gps.RawGPSStatusRecord;

import org.junit.Before;
import org.mockito.Mock;

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
public class RawGPSStatusRecordRepositoryTest extends SensorRecordRepositoryTest<RawGPSStatusRecord, RawGPSStatusRecordEntity> {

    @Mock RawGPSStatusRecordEntityDao rawGPSStatusRecordEntityDao;

    @Before
    public void setUp() throws Exception {
        sensorRecordEntityDao = rawGPSStatusRecordEntityDao;
        sensorRecordRepository = new RawGPSStatusRecordRepository(rawGPSStatusRecordEntityDao);
    }

    @Override
    protected RawGPSStatusRecord createSensorRecord() {
        return new RawGPSStatusRecord();
    }

    @Override
    protected RawGPSStatusRecordEntity createSensorRecordEntity() {
        return new RawGPSStatusRecordEntity();
    }

}