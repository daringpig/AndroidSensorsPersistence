package com.ubikgs.androidsensors.persistence.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.ubikgs.androidsensors.persistence.daos.gps.LocationEntityDao;
import com.ubikgs.androidsensors.persistence.daos.gps.RawGPSMeasurementsEntityDao;
import com.ubikgs.androidsensors.persistence.daos.gps.RawGPSNavigationEntityDao;
import com.ubikgs.androidsensors.persistence.daos.gps.RawGPSStatusEntityDao;
import com.ubikgs.androidsensors.persistence.daos.imu.AccelerometerEntityDao;
import com.ubikgs.androidsensors.persistence.daos.imu.GravityEntityDao;
import com.ubikgs.androidsensors.persistence.daos.imu.GyroscopeEntityDao;
import com.ubikgs.androidsensors.persistence.daos.imu.LinearAccelerationEntityDao;
import com.ubikgs.androidsensors.persistence.daos.imu.MagneticFieldEntityDao;
import com.ubikgs.androidsensors.persistence.daos.imu.RotationVectorEntityDao;
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
public class AndroidSensorsPersistenceDaoModule {

    @Provides
    @Singleton
    AndroidSensorsDatabase provideAndroidSensorsDatabase(Context context,
                                                         @Named("sensorsDBName") String databaseName) {
        return Room.databaseBuilder(context, AndroidSensorsDatabase.class, databaseName).build();
    }

    @Provides
    @Singleton
    AccelerometerEntityDao provideAccelerometerRecordEntityDao(AndroidSensorsDatabase database) {
        return database.accelerometerEntityDao();
    }

    @Provides
    @Singleton
    GravityEntityDao provideGravityRecordEntityDao(AndroidSensorsDatabase database) {
        return database.gravityEntityDao();
    }

    @Provides
    @Singleton
    GyroscopeEntityDao provideGyroscopeRecordEntityDao(AndroidSensorsDatabase database) {
        return database.gyroscopeEntityDao();
    }

    @Provides
    @Singleton
    LinearAccelerationEntityDao provideLinearAccelerationRecordEntityDao(AndroidSensorsDatabase database) {
        return database.linearAccelerationEntityDao();
    }

    @Provides
    @Singleton
    MagneticFieldEntityDao provideMagneticFieldRecordEntityDao(AndroidSensorsDatabase database) {
        return database.magneticFieldEntityDao();
    }

    @Provides
    @Singleton
    RotationVectorEntityDao provideRotationVectorRecordEntityDao(AndroidSensorsDatabase database) {
        return database.rotationVectorEntityDao();
    }

    @Provides
    @Singleton
    LocationEntityDao provideLocationRecordEntityDao(AndroidSensorsDatabase database) {
        return database.locationEntityDao();
    }

    @Provides
    @Singleton
    RawGPSMeasurementsEntityDao provideRawGPSMeasurementsRecordEntityDao(AndroidSensorsDatabase database) {
        return database.rawGPSMeasurementsEntityDao();
    }

    @Provides
    @Singleton
    RawGPSNavigationEntityDao provideRawGPSNavigationRecordEntityDao(AndroidSensorsDatabase database) {
        return database.rawGPSNavigationEntityDao();
    }

    @Provides
    @Singleton
    RawGPSStatusEntityDao provideRawGPSStatusRecordEntityDao(AndroidSensorsDatabase database) {
        return database.rawGPSStatusEntityDao();
    }
}
