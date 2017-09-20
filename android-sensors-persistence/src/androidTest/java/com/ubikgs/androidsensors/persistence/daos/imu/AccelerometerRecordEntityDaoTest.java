package com.ubikgs.androidsensors.persistence.daos.imu;

import com.ubikgs.androidsensors.persistence.DaggerTestBedComponent;
import com.ubikgs.androidsensors.persistence.entities.imu.AccelerometerRecordEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

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
public class AccelerometerRecordEntityDaoTest {

    @Inject
    AccelerometerRecordEntityDao accelerometerRecordEntityDao;

    private AccelerometerRecordEntity entity1;
    private AccelerometerRecordEntity entity2;

    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);

        entity1 = new AccelerometerRecordEntity();
        entity1.setAccuracy(1.0f);
        entity1.setSensorTimestamp(new Date().getTime());
        entity1.setSystemTimestamp(new Date().getTime());
        entity1.setX(1.0f);
        entity1.setY(1.0f);
        entity1.setZ(1.0f);
        entity1.setForeignKey(0L);

        entity2 = new AccelerometerRecordEntity();
        entity2.setAccuracy(1.0f);
        entity2.setSensorTimestamp(new Date().getTime());
        entity2.setSystemTimestamp(new Date().getTime());
        entity2.setX(1.0f);
        entity2.setY(1.0f);
        entity2.setZ(1.0f);
        entity2.setForeignKey(1L);
    }

    @Test
    public void createAll() throws Exception {
        List<Long> uids = createEntities();

        assertThat(uids.size(), equalTo(2));
    }

    @Test
    public void findAll() throws Exception {
        createEntities();

        int size = getStoredRecordAmount();

        assertThat(size, equalTo(2));
    }

    @Test
    public void findByUid() throws Exception {
        List<Long> uids = createEntities();

        for (Long uid : uids) {
            accelerometerRecordEntityDao.findByUid(uid)
                    .blockingGet();
        }
    }

    @Test
    public void findAllByForeignKey() throws Exception {
        createEntities();

        int sizeFK0 = getRecordAmountByForeignKey(0L);
        int sizeFK1 = getRecordAmountByForeignKey(1L);

        assertThat(sizeFK0, equalTo(1));
        assertThat(sizeFK1, equalTo(1));
    }

    private int getRecordAmountByForeignKey(long foreignKey) {
        return accelerometerRecordEntityDao.findAllByForeignKey(foreignKey)
                    .blockingGet()
                    .size();
    }

    @Test
    public void removeAllPassedBy() throws Exception {
        createEntities();

        List<AccelerometerRecordEntity> entities = accelerometerRecordEntityDao.findAll()
                .blockingGet();

        accelerometerRecordEntityDao.removeAll(entities);

        int size = getStoredRecordAmount();

        assertThat(size, equalTo(0));
    }

    @Test
    public void removeAll() throws Exception {
        createEntities();

        accelerometerRecordEntityDao.removeAll();

        int size = getStoredRecordAmount();

        assertThat(size, equalTo(0));
    }

    private List<Long> createEntities() {
        return accelerometerRecordEntityDao.createAll(Arrays.asList(entity1, entity2));
    }

    private int getStoredRecordAmount() {
        return accelerometerRecordEntityDao.findAll()
                .blockingGet().size();
    }

    @After
    public void tearDown() throws Exception {
        accelerometerRecordEntityDao.removeAll();
    }
}