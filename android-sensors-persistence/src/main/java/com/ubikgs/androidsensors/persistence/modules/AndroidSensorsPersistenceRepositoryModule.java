package com.ubikgs.androidsensors.persistence.modules;

import com.ubikgs.androidsensors.persistence.repositories.SensorRecordRepository;
import com.ubikgs.androidsensors.persistence.repositories.gps.LocationRecordRepository;
import com.ubikgs.androidsensors.persistence.repositories.gps.RawGPSMeasurementsRecordRepository;
import com.ubikgs.androidsensors.persistence.repositories.gps.RawGPSNavigationRecordRepository;
import com.ubikgs.androidsensors.persistence.repositories.gps.RawGPSStatusRecordRepository;
import com.ubikgs.androidsensors.persistence.repositories.imu.AccelerometerRecordRepository;
import com.ubikgs.androidsensors.persistence.repositories.imu.GravityRecordRepository;
import com.ubikgs.androidsensors.persistence.repositories.imu.GyroscopeRecordRepository;
import com.ubikgs.androidsensors.persistence.repositories.imu.LinearAccelerationRecordRepository;
import com.ubikgs.androidsensors.persistence.repositories.imu.MagneticFieldRecordRepository;
import com.ubikgs.androidsensors.persistence.repositories.imu.RotationVectorRecordRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

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
@Module
public class AndroidSensorsPersistenceRepositoryModule {

    @Provides
    @ElementsIntoSet
    Set<SensorRecordRepository> provideAllSensorRecordRepositories(
            AccelerometerRecordRepository accelerometerRecordRepository,
            GravityRecordRepository gravityRecordRepository,
            GyroscopeRecordRepository gyroscopeRecordRepository,
            LinearAccelerationRecordRepository linearAccelerationRecordRepository,
            MagneticFieldRecordRepository magneticFieldRecordRepository,
            RotationVectorRecordRepository rotationVectorRecordRepository,
            LocationRecordRepository locationRecordRepository,
            RawGPSMeasurementsRecordRepository rawGPSMeasurementsRecordRepository,
            RawGPSNavigationRecordRepository rawGPSNavigationRecordRepository,
            RawGPSStatusRecordRepository rawGPSStatusRecordRepository) {

        return new HashSet<>(Arrays.asList(
                accelerometerRecordRepository,
                gravityRecordRepository,
                gyroscopeRecordRepository,
                linearAccelerationRecordRepository,
                magneticFieldRecordRepository,
                rotationVectorRecordRepository,
                locationRecordRepository,
                rawGPSMeasurementsRecordRepository,
                rawGPSNavigationRecordRepository,
                rawGPSStatusRecordRepository));
    }
}
