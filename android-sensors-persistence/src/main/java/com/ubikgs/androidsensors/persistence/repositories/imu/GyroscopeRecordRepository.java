package com.ubikgs.androidsensors.persistence.repositories.imu;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.persistence.daos.imu.GyroscopeRecordEntityDao;
import com.ubikgs.androidsensors.persistence.entities.imu.GyroscopeRecordEntity;
import com.ubikgs.androidsensors.persistence.repositories.AbstractSensorRecordRepository;
import com.ubikgs.androidsensors.records.imu.GyroscopeRecord;

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

public class GyroscopeRecordRepository extends AbstractSensorRecordRepository<GyroscopeRecord, GyroscopeRecordEntity> {

    @Inject
    public GyroscopeRecordRepository(GyroscopeRecordEntityDao sensorRecordEntityDao) {
        super(sensorRecordEntityDao);
    }

    @Override
    protected GyroscopeRecordEntity createFrom(GyroscopeRecord sensorRecord) {
        return new GyroscopeRecordEntity(sensorRecord);
    }

    @Override
    protected GyroscopeRecord transformIn(GyroscopeRecordEntity entity) {
        return entity.toSensorRecord();
    }

    @Override
    public SensorType getSensorType() {
        return SensorType.GYROSCOPE;
    }
}
