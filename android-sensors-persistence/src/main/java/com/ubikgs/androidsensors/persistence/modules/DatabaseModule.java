package com.ubikgs.androidsensors.persistence.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.ubikgs.androidsensors.persistence.daos.gps.LocationRecordEntityDao;
import com.ubikgs.androidsensors.persistence.daos.gps.RawGPSMeasurementsRecordEntityDao;
import com.ubikgs.androidsensors.persistence.daos.gps.RawGPSNavigationRecordEntityDao;
import com.ubikgs.androidsensors.persistence.daos.imu.AccelerometerRecordEntityDao;
import com.ubikgs.androidsensors.persistence.daos.imu.GravityRecordEntityDao;
import com.ubikgs.androidsensors.persistence.daos.imu.GyroscopeRecordEntityDao;
import com.ubikgs.androidsensors.persistence.daos.imu.LinearAccelerationRecordEntityDao;
import com.ubikgs.androidsensors.persistence.daos.imu.MagneticFieldRecordEntityDao;
import com.ubikgs.androidsensors.persistence.daos.imu.RotationVectorRecordEntityDao;
import com.ubikgs.androidsensors.persistence.database.AndroidSensorsDatabase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
public class DatabaseModule {

    @Provides
    @Singleton
    AndroidSensorsDatabase provideAndroidSensorsDatabase(Context context,
                                                         @Named("sensorsDBName") String databaseName) {
        return Room.databaseBuilder(context, AndroidSensorsDatabase.class, databaseName).build();
    }

    @Provides
    @Singleton
    AccelerometerRecordEntityDao provideAccelerometerRecordEntityDao(AndroidSensorsDatabase database) {
        return database.accelerometerRecordEntityDao();
    }

    @Provides
    @Singleton
    GravityRecordEntityDao provideGravityRecordEntityDao(AndroidSensorsDatabase database) {
        return database.gravityRecordEntityDao();
    }

    @Provides
    @Singleton
    GyroscopeRecordEntityDao provideGyroscopeRecordEntityDao(AndroidSensorsDatabase database) {
        return database.gyroscopeRecordEntityDao();
    }

    @Provides
    @Singleton
    LinearAccelerationRecordEntityDao provideLinearAccelerationRecordEntityDao(AndroidSensorsDatabase database) {
        return database.linearAccelerationRecordEntityDao();
    }

    @Provides
    @Singleton
    MagneticFieldRecordEntityDao provideMagneticFieldRecordEntityDao(AndroidSensorsDatabase database) {
        return database.magneticFieldRecordEntityDao();
    }

    @Provides
    @Singleton
    RotationVectorRecordEntityDao provideRotationVectorRecordEntityDao(AndroidSensorsDatabase database) {
        return database.rotationVectorRecordEntityDao();
    }

    @Provides
    @Singleton
    LocationRecordEntityDao provideLocationRecordEntityDao(AndroidSensorsDatabase database) {
        return database.locationRecordEntityDao();
    }

    @Provides
    @Singleton
    RawGPSMeasurementsRecordEntityDao provideRawGPSMeasurementsRecordEntityDao(AndroidSensorsDatabase database) {
        return database.rawGPSMeasurementsRecordEntityDao();
    }

    @Provides
    @Singleton
    RawGPSNavigationRecordEntityDao provideRawGPSNavigationRecordEntityDao(AndroidSensorsDatabase database) {
        return database.rawGPSNavigationRecordEntityDao();
    }
}
