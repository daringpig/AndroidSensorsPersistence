package com.ubikgs.androidsensors.persistence.repositories.imu;

import com.ubikgs.androidsensors.persistence.daos.imu.AccelerometerRecordEntityDao;
import com.ubikgs.androidsensors.persistence.entities.imu.AccelerometerRecordEntity;
import com.ubikgs.androidsensors.records.imu.AccelerometerRecord;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
@RunWith(MockitoJUnitRunner.class)
public class AccelerometerRecordRepositoryTest {

    @Mock AccelerometerRecordEntityDao accelerometerRecordEntityDao;

    private AccelerometerRecordRepository accelerometerRecordRepository;

    @Before
    public void setUp() throws Exception {
        accelerometerRecordRepository = new AccelerometerRecordRepository(accelerometerRecordEntityDao);
    }

    @Test
    public void createAll() throws Exception {
        List<Long> pKeys = Arrays.asList(0L, 1L);
        when(accelerometerRecordEntityDao.createAll(anyCollection()))
                .thenReturn(pKeys);

        List<Long> keys = accelerometerRecordRepository.createAll(
                Arrays.asList(new AccelerometerRecord(), new AccelerometerRecord())).blockingGet();

        verify(accelerometerRecordEntityDao, times(1)).createAll(anyCollection());

        assertThat(keys, equalTo(pKeys));
    }

    @Test
    public void findAll() throws Exception {
        int count = 1001;

        List<AccelerometerRecord> accelerometerRecords =
                createAccelerometerRecords(count);

        List<AccelerometerRecordEntity> accelerometerRecordEntities =
                createAccelerometerRecordEntities(count);

        when(accelerometerRecordEntityDao.count()).thenReturn(Single.just((long) count));
        when(accelerometerRecordEntityDao.findAll(0, 1000))
                .thenReturn(Single.just(accelerometerRecordEntities.subList(0, 1000)));
        when(accelerometerRecordEntityDao.findAll(1000, 1))
                .thenReturn(Single.just(accelerometerRecordEntities.subList(1000, 1001)));

        List<AccelerometerRecord> fetchedRecords = accelerometerRecordRepository.findAll()
                .toList()
                .blockingGet();

        assertThat(fetchedRecords.size(), equalTo(accelerometerRecords.size()));
    }

    @Test
    public void count() throws Exception {
        accelerometerRecordRepository.count();

        verify(accelerometerRecordEntityDao, times(1)).count();
    }

    @Test
    public void removeAll() throws Exception {
        accelerometerRecordRepository.removeAll();

        verify(accelerometerRecordEntityDao, times(1)).removeAll();
    }

    @Test
    public void findAllByForeignKey() throws Exception {
        int count = 1001;

        List<AccelerometerRecord> accelerometerRecords =
                createAccelerometerRecords(count);

        List<AccelerometerRecordEntity> accelerometerRecordEntities =
                createAccelerometerRecordEntities(count);

        int foreignKey = 0;
        when(accelerometerRecordEntityDao.countBy(foreignKey)).thenReturn(Single.just((long) count));
        when(accelerometerRecordEntityDao.findAllBy(foreignKey, 0, 1000))
                .thenReturn(Single.just(accelerometerRecordEntities.subList(foreignKey, 1000)));
        when(accelerometerRecordEntityDao.findAllBy(foreignKey,1000, 1))
                .thenReturn(Single.just(accelerometerRecordEntities.subList(1000, 1001)));

        List<AccelerometerRecord> fetchedRecords = accelerometerRecordRepository.findAllBy(foreignKey)
                .toList()
                .blockingGet();

        assertThat(fetchedRecords.size(), equalTo(accelerometerRecords.size()));
    }

    @Test
    public void countByForeignKey() throws Exception {
        accelerometerRecordRepository.countBy(0L);

        verify(accelerometerRecordEntityDao, times(1)).countBy(0L);
    }

    @Test
    public void removeAllByForeignKey() throws Exception {
        accelerometerRecordRepository.removeAllBy(0L);

        verify(accelerometerRecordEntityDao, times(1)).removeAllBy(0L);
    }

    @Test
    public void createFindBucketsFrom_withRecordCountUnderLimit_returnsOneFindBucket() throws Exception {
        List<AccelerometerRecordRepository.FindBucket> findBuckets =
                accelerometerRecordRepository.createFindBucketsFrom(999);

        assertThat(findBuckets.size(), equalTo(1));
        assertThat(findBuckets.get(0).getOffset(), equalTo(0L));
        assertThat(findBuckets.get(0).getLimit(), equalTo(999L));
    }

    @Test
    public void createFindBucketsFrom_withRecordCountAboveLimit_returnsTwoFindBuckets() throws Exception {
        List<AccelerometerRecordRepository.FindBucket> findBuckets =
                accelerometerRecordRepository.createFindBucketsFrom(1001);

        assertThat(findBuckets.size(), equalTo(2));

        assertThat(findBuckets.get(0).getOffset(), equalTo(0L));
        assertThat(findBuckets.get(0).getLimit(), equalTo(1000L));

        assertThat(findBuckets.get(1).getOffset(), equalTo(1000L));
        assertThat(findBuckets.get(1).getLimit(), equalTo(1L));
    }

    @Test
    public void createFindBucketsFrom_withRecordCountVeryAboveLimit_returnsMultipleFindBuckets() throws Exception {
        List<AccelerometerRecordRepository.FindBucket> findBuckets =
                accelerometerRecordRepository.createFindBucketsFrom(12123);

        assertThat(findBuckets.size(), equalTo(13));

        assertThat(findBuckets.get(2).getOffset(), equalTo(2000L));
        assertThat(findBuckets.get(2).getLimit(), equalTo(1000L));

        assertThat(findBuckets.get(12).getOffset(), equalTo(12000L));
        assertThat(findBuckets.get(12).getLimit(), equalTo(123L));
    }

    private List<AccelerometerRecord> createAccelerometerRecords(int count) {
        List<AccelerometerRecord> accelerometerRecords = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            accelerometerRecords.add(new AccelerometerRecord());
        }
        return accelerometerRecords;
    }

    private List<AccelerometerRecordEntity> createAccelerometerRecordEntities(int count) {
        List<AccelerometerRecordEntity> accelerometerRecords = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            accelerometerRecords.add(new AccelerometerRecordEntity());
        }
        return accelerometerRecords;
    }
}