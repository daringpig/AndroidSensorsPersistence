package com.ubikgs.androidsensors.persistence.daos.imu;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.ubikgs.androidsensors.persistence.daos.SensorRecordEntityDao;
import com.ubikgs.androidsensors.persistence.entities.imu.MagneticFieldRecordEntity;

import java.util.List;

import io.reactivex.Single;

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
@Dao
public interface MagneticFieldRecordEntityDao extends SensorRecordEntityDao<MagneticFieldRecordEntity> {
    @Query("SELECT * FROM MagneticFieldRecordEntity LIMIT :limit OFFSET :offset")
    Single<List<MagneticFieldRecordEntity>> findAll(long offset, long limit);

    @Query("SELECT COUNT(*) FROM MagneticFieldRecordEntity")
    Single<Long> count();

    @Query("SELECT * FROM MagneticFieldRecordEntity WHERE foreignKey = :foreignKey LIMIT :limit OFFSET :offset")
    Single<List<MagneticFieldRecordEntity>> findAllBy(long foreignKey, long offset, long limit);

    @Query("SELECT COUNT(*) FROM MagneticFieldRecordEntity WHERE foreignKey = :foreignKey")
    Single<Long> countBy(long foreignKey);

    @Query("DELETE FROM MagneticFieldRecordEntity")
    void removeAll();

    @Query("DELETE FROM MagneticFieldRecordEntity WHERE foreignKey = :foreignKey")
    void removeAllBy(long foreignKey);
}
