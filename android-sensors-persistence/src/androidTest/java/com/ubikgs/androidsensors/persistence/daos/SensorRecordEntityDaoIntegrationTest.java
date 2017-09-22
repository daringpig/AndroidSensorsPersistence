package com.ubikgs.androidsensors.persistence.daos;

import android.support.test.runner.AndroidJUnit4;

import com.ubikgs.androidsensors.persistence.entities.SensorRecordEntity;
import com.ubikgs.androidsensors.records.SensorRecord;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
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
@RunWith(AndroidJUnit4.class)
public abstract class SensorRecordEntityDaoIntegrationTest<T extends SensorRecordEntity> {

    protected SensorRecordEntityDao<T> sensorRecordEntityDao;

    private ForeignKeyStore foreignKeyStore = new ForeignKeyStore();
    private SensorRecordEntityStore entityStore = new SensorRecordEntityStore();

    @Before
    public void setUp() throws Exception {
        if (sensorRecordEntityDao == null) {
            throw new Error("Inject a SensorRecordEntityDao instance into sensorRecordEntityDao attribute in setUp() method");
        }
    }

    @Test
    public void createAll() throws Exception {
        List<Long> uids = createEntities();

        assertThat(uids.size(), equalTo(entityStore.entities.size()));
    }

    @Test
    public void findByUid() throws Exception {
        List<Long> uids = createEntities();

        for (int i = 0; i < uids.size(); i++) {
            SensorRecord sensorRecord = sensorRecordEntityDao.findByUid(uids.get(i))
                    .blockingGet().toSensorRecord();

            assertThat(sensorRecord, equalTo(entityStore.entities.get(i).toSensorRecord()));
        }
    }

    @Test
    public void findAll() throws Exception {
        createEntities();

        int size = getStoredRecordAmount();

        assertThat(size, equalTo(entityStore.entities.size()));
    }

    @Test
    public void count() throws Exception {
        createEntities();

        Long count = sensorRecordEntityDao.count().blockingGet();

        assertThat(count.intValue(), equalTo(entityStore.entities.size()));
    }

    @Test
    public void findAll_withOffsetAndLimit() throws Exception {
        int expectedSize = entityStore.entities.size() -1 < 0 ? 0 : 1;

        createEntities();

        int size = getStoredRecordAmountWith(1, 1);

        assertThat(size, equalTo(expectedSize));
    }

    @Test
    public void findAllByForeignKey() throws Exception {
        createEntities();

        for (Long foreignKey : foreignKeyStore.foreignKeys.keySet()) {
            int count = foreignKeyStore.foreignKeys.get(foreignKey);

            int size = getRecordAmountByForeignKey(foreignKey);
            assertThat(size, equalTo(count));
        }
    }

    @Test
    public void removeAllPassedBy() throws Exception {
        createEntities();

        List<T> entities = sensorRecordEntityDao.findAll()
                .blockingGet();

        sensorRecordEntityDao.removeAll(entities);

        int size = getStoredRecordAmount();

        assertThat(size, equalTo(0));
    }

    @Test
    public void removeAll() throws Exception {
        createEntities();

        sensorRecordEntityDao.removeAll();

        int size = getStoredRecordAmount();

        assertThat(size, equalTo(0));
    }

    protected List<Long> createEntities() {
        return sensorRecordEntityDao.createAll(entityStore.entities);
    }

    private int getStoredRecordAmount() {
        return sensorRecordEntityDao.findAll()
                .blockingGet().size();
    }

    private int getStoredRecordAmountWith(long offset, long limit) {
        return sensorRecordEntityDao.findAll(offset, limit)
                .blockingGet().size();
    }

    private int getRecordAmountByForeignKey(long foreignKey) {
        return sensorRecordEntityDao.findAllByForeignKey(foreignKey)
                .blockingGet()
                .size();

    }

    @After
    public void tearDown() throws Exception {
        sensorRecordEntityDao.removeAll();
    }

    private class ForeignKeyStore {
        Map<Long, Integer> foreignKeys = new HashMap<>();

        public long registerUsage(long foreignKey) {
            Integer integer = foreignKeys.putIfAbsent(foreignKey, 1);
            if (integer != null) {
                foreignKeys.put(foreignKey, integer + 1);
            }
            return foreignKey;
        }
    }

    protected long registerForeignKeyUsage(long foreignKey) {
        return foreignKeyStore.registerUsage(foreignKey);
    }

    private class SensorRecordEntityStore {
        List<T> entities = new ArrayList<>();

        public void registerEntity(T entity) {
            entities.add(entity);
        }
    }

    protected void registerTestEntity(T entity) {
        entityStore.registerEntity(entity);
    }
}
