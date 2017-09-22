package com.ubikgs.androidsensors.persistence.daos.imu;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.ubikgs.androidsensors.persistence.daos.SensorRecordEntityDao;
import com.ubikgs.androidsensors.persistence.entities.imu.RotationVectorRecordEntity;

import java.util.List;

import io.reactivex.Maybe;
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
public interface RotationVectorRecordEntityDao extends SensorRecordEntityDao<RotationVectorRecordEntity> {
    @Query("SELECT * FROM RotationVectorRecordEntity WHERE uid = :uid")
    Maybe<RotationVectorRecordEntity> findByUid(long uid);

    @Query("SELECT * FROM RotationVectorRecordEntity")
    Single<List<RotationVectorRecordEntity>> findAll();

    @Query("SELECT COUNT(*) FROM RotationVectorRecordEntity")
    Single<Long> count();

    @Query("SELECT * FROM RotationVectorRecordEntity LIMIT :limit OFFSET :offset")
    Single<List<RotationVectorRecordEntity>> findAll(long offset, long limit);

    @Query("SELECT * FROM RotationVectorRecordEntity WHERE foreignKey = :foreignKey")
    Single<List<RotationVectorRecordEntity>> findAllByForeignKey(long foreignKey);

    @Query("SELECT COUNT(*) FROM RotationVectorRecordEntity WHERE foreignKey = :foreignKey")
    Single<Long> countByForeignKey(long foreignKey);

    @Query("DELETE FROM RotationVectorRecordEntity")
    void removeAll();
}
