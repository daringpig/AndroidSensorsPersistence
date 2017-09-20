package com.ubikgs.androidsensors.persistence.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ubikgs.androidsensors.persistence.daos.imu.AccelerometerRecordEntityDao;
import com.ubikgs.androidsensors.persistence.entities.imu.AccelerometerRecordEntity;

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
@Database(entities = {AccelerometerRecordEntity.class}, version = 1)
public abstract class AndroidSensorsDatabase extends RoomDatabase {
    /*
    * WARNING! If you modify this class or a linked entity remember to add and test a proper
    * database version migration.
    *
    * If you don't do so the sensors database will be wiped on every application using this library
    * */

    public abstract AccelerometerRecordEntityDao accelerometerRecordDao();
}