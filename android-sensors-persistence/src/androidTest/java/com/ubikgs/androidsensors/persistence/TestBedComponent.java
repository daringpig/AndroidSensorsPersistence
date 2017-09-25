package com.ubikgs.androidsensors.persistence;

import com.ubikgs.androidsensors.persistence.daos.gps.LocationRecordEntityDaoIntegrationTest;
import com.ubikgs.androidsensors.persistence.daos.gps.RawGPSMeasurementsRecordEntityDaoIntegrationTest;
import com.ubikgs.androidsensors.persistence.daos.gps.RawGPSNavigationRecordEntityDaoIntegrationTest;
import com.ubikgs.androidsensors.persistence.daos.gps.RawGPSStatusRecordEntityDaoIntegrationTest;
import com.ubikgs.androidsensors.persistence.daos.imu.AccelerometerRecordEntityDaoIntegrationTest;
import com.ubikgs.androidsensors.persistence.daos.imu.GravityRecordEntityDaoIntegrationTest;
import com.ubikgs.androidsensors.persistence.daos.imu.GyroscopeRecordEntityDaoIntegrationTest;
import com.ubikgs.androidsensors.persistence.daos.imu.LinearAccelerationRecordEntityDaoTest;
import com.ubikgs.androidsensors.persistence.daos.imu.MagneticFieldRecordEntityDaoIntegrationTest;
import com.ubikgs.androidsensors.persistence.daos.imu.RotationVectorRecordEntityDaoIntegrationTest;
import com.ubikgs.androidsensors.persistence.modules.AndroidSensorsPersistenceDaoModule;
import com.ubikgs.androidsensors.persistence.modules.TestBedModule;
import com.ubikgs.androidsensors.persistence.modules.TestConfigModule;
import com.ubikgs.androidsensors.persistence.repositories.gps.LocationRecordRepositoryIntegrationTest;
import com.ubikgs.androidsensors.persistence.repositories.gps.RawGPSMeasurementsRecordRepositoryIntegrationTest;
import com.ubikgs.androidsensors.persistence.repositories.gps.RawGPSNavigationRecordRepositoryIntegrationTest;
import com.ubikgs.androidsensors.persistence.repositories.gps.RawGPSStatusRecordRepositoryIntegrationTest;
import com.ubikgs.androidsensors.persistence.repositories.imu.AccelerometerRecordRepositoryIntegrationTest;
import com.ubikgs.androidsensors.persistence.repositories.imu.GravityRecordRepositoryIntegrationTest;
import com.ubikgs.androidsensors.persistence.repositories.imu.GyroscopeRecordRepositoryIntegrationTest;
import com.ubikgs.androidsensors.persistence.repositories.imu.LinearAccelerationRecordRepositoryIntegrationTest;
import com.ubikgs.androidsensors.persistence.repositories.imu.MagneticFieldRecordRepositoryIntegrationTest;
import com.ubikgs.androidsensors.persistence.repositories.imu.RotationVectorRecordRepositoryIntegrationTest;

import javax.inject.Singleton;

import dagger.Component;

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
@Singleton
@Component(modules = {
        TestBedModule.class,
        TestConfigModule.class,
        AndroidSensorsPersistenceDaoModule.class
})
public interface TestBedComponent {
    void inject(AccelerometerRecordEntityDaoIntegrationTest test);

    void inject(GravityRecordEntityDaoIntegrationTest test);

    void inject(GyroscopeRecordEntityDaoIntegrationTest test);

    void inject(LinearAccelerationRecordEntityDaoTest test);

    void inject(MagneticFieldRecordEntityDaoIntegrationTest test);

    void inject(RotationVectorRecordEntityDaoIntegrationTest test);

    void inject(LocationRecordEntityDaoIntegrationTest test);

    void inject(RawGPSMeasurementsRecordEntityDaoIntegrationTest test);

    void inject(RawGPSNavigationRecordEntityDaoIntegrationTest test);

    void inject(RawGPSStatusRecordEntityDaoIntegrationTest test);

    void inject(AccelerometerRecordRepositoryIntegrationTest test);

    void inject(GravityRecordRepositoryIntegrationTest test);

    void inject(GyroscopeRecordRepositoryIntegrationTest test);

    void inject(LinearAccelerationRecordRepositoryIntegrationTest test);

    void inject(MagneticFieldRecordRepositoryIntegrationTest test);

    void inject(RotationVectorRecordRepositoryIntegrationTest test);

    void inject(LocationRecordRepositoryIntegrationTest test);

    void inject(RawGPSMeasurementsRecordRepositoryIntegrationTest test);

    void inject(RawGPSNavigationRecordRepositoryIntegrationTest test);

    void inject(RawGPSStatusRecordRepositoryIntegrationTest test);
}
