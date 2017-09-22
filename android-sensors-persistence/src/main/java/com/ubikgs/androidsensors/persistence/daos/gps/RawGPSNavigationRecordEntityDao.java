package com.ubikgs.androidsensors.persistence.daos.gps;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.ubikgs.androidsensors.persistence.daos.SensorRecordEntityDao;
import com.ubikgs.androidsensors.persistence.entities.gps.RawGPSNavigationRecordEntity;

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
public interface RawGPSNavigationRecordEntityDao extends SensorRecordEntityDao<RawGPSNavigationRecordEntity> {
    @Query("SELECT * FROM RawGPSNavigationRecordEntity WHERE uid = :uid")
    Maybe<RawGPSNavigationRecordEntity> findByUid(long uid);

    @Query("SELECT COUNT(*) FROM RawGPSNavigationRecordEntity")
    Single<Long> count();

    @Query("SELECT * FROM RawGPSNavigationRecordEntity")
    Single<List<RawGPSNavigationRecordEntity>> findAll();

    @Query("SELECT * FROM RawGPSNavigationRecordEntity LIMIT :limit OFFSET :offset")
    Single<List<RawGPSNavigationRecordEntity>> findAll(long offset, long limit);

    @Query("SELECT COUNT(*) FROM RawGPSNavigationRecordEntity WHERE foreignKey = :foreignKey")
    Single<Long> countByForeignKey(long foreignKey);

    @Query("SELECT * FROM RawGPSNavigationRecordEntity WHERE foreignKey = :foreignKey")
    Single<List<RawGPSNavigationRecordEntity>> findAllByForeignKey(long foreignKey);

    @Query("SELECT * FROM RawGPSNavigationRecordEntity WHERE foreignKey = :foreignKey LIMIT :limit OFFSET :offset")
    Single<List<RawGPSNavigationRecordEntity>> findAllByForeignKey(long foreignKey, long offset, long limit);

    @Query("DELETE FROM RawGPSNavigationRecordEntity")
    void removeAll();
}
