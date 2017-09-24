package com.ubikgs.androidsensors.persistence.repositories.imu;

import com.ubikgs.androidsensors.persistence.daos.imu.RotationVectorRecordEntityDao;
import com.ubikgs.androidsensors.persistence.entities.imu.RotationVectorRecordEntity;
import com.ubikgs.androidsensors.persistence.repositories.AbstractSensorRecordRepository;
import com.ubikgs.androidsensors.records.imu.RotationVectorRecord;

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

public class RotationVectorRecordRepository extends AbstractSensorRecordRepository<RotationVectorRecord, RotationVectorRecordEntity> {

    @Inject
    public RotationVectorRecordRepository(RotationVectorRecordEntityDao sensorRecordEntityDao) {
        super(sensorRecordEntityDao);
    }

    @Override
    protected RotationVectorRecordEntity createFrom(RotationVectorRecord sensorRecord) {
        return new RotationVectorRecordEntity(sensorRecord);
    }

    @Override
    protected RotationVectorRecord transformIn(RotationVectorRecordEntity entity) {
        return entity.toSensorRecord();
    }
}
