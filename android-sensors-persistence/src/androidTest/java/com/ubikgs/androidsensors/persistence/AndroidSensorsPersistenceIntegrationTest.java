package com.ubikgs.androidsensors.persistence;

import android.content.Context;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.persistence.repositories.SensorRecordRepository;
import com.ubikgs.androidsensors.persistence.repositories.imu.AccelerometerRecordRepository;

import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

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
public class AndroidSensorsPersistenceIntegrationTest {

    @Inject Context context;

    private AndroidSensorsPersistence defaultAndroidSensorsPersistence;

    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);

        defaultAndroidSensorsPersistence = AndroidSensorsPersistence
                .builder()
                .build(context);
    }

    @Test
    public void recordRepositories_withDefaultConfigIsEmpty_isFalse() throws Exception {
        boolean empty = defaultAndroidSensorsPersistence.allSensorRecordRepositories().isEmpty();
        assertThat(empty, is(false));
    }

    @Test
    public void recordRepository_withDefaultConfig_returnsAccelerometerRepository() throws Exception {
        AccelerometerRecordRepository accelerometerRecordRepository =
                defaultAndroidSensorsPersistence.sensorRecordRepository(AccelerometerRecordRepository.class);

        assertThat(accelerometerRecordRepository, not(equalTo(null)));
    }

    @Test
    public void recordRepositoryBy_withDefaultConfig_returnsAccelerometerRepository() throws Exception {
        SensorRecordRepository sensorRecordRepository =
                defaultAndroidSensorsPersistence.sensorRecordRepositoryBy(SensorType.ACCELEROMETER);

        assertThat(sensorRecordRepository, not(equalTo(null)));
    }
}