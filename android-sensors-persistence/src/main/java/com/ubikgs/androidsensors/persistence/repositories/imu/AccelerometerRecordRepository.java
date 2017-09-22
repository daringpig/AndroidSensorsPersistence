package com.ubikgs.androidsensors.persistence.repositories.imu;

import android.support.annotation.NonNull;

import com.ubikgs.androidsensors.persistence.daos.imu.AccelerometerRecordEntityDao;
import com.ubikgs.androidsensors.persistence.entities.imu.AccelerometerRecordEntity;
import com.ubikgs.androidsensors.persistence.repositories.SensorRecordRepository;
import com.ubikgs.androidsensors.records.imu.AccelerometerRecord;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
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

public class AccelerometerRecordRepository implements SensorRecordRepository<AccelerometerRecord> {

    private static final int RESULT_COUNT_LIMIT = 1000;

    private final AccelerometerRecordEntityDao accelerometerRecordEntityDao;

    @Inject
    public AccelerometerRecordRepository(AccelerometerRecordEntityDao accelerometerRecordEntityDao) {
        this.accelerometerRecordEntityDao = accelerometerRecordEntityDao;
    }

    public Single<List<Long>> createAll(Collection<AccelerometerRecord> accelerometerRecords) {
        return Observable.fromIterable(accelerometerRecords)
                .map(AccelerometerRecordEntity::new)
                .toList()
                .map(accelerometerRecordEntityDao::createAll);
    }

    public Flowable<AccelerometerRecord> findAll() {
        return findAllWith(this::count, findBucket ->
                accelerometerRecordEntityDao.findAll(findBucket.getOffset(), findBucket.getLimit()));
    }

    public Single<Long> count() {
        return accelerometerRecordEntityDao.count();
    }

    public void removeAll() {
        accelerometerRecordEntityDao.removeAll();
    }

    public Flowable<AccelerometerRecord> findAllBy(long foreignKey) {
        return findAllWith(
                    () -> countBy(foreignKey),
                    findBucket -> accelerometerRecordEntityDao.findAllBy(
                            foreignKey,
                            findBucket.getOffset(),
                            findBucket.getLimit()
                    )
                );
    }

    public Single<Long> countBy(long foreignKey) {
        return accelerometerRecordEntityDao.countBy(foreignKey);
    }

    public void removeAllBy(long foreignKey) {
        accelerometerRecordEntityDao.removeAllBy(foreignKey);
    }

    private Flowable<AccelerometerRecord> findAllWith(Counter counter, Finder finder) {
        return counter.count()
                .map(this::createFindBucketsFrom)
                .flatMapObservable(Observable::fromIterable)
                .flatMapSingle(finder::find)
                .map(Observable::fromIterable)
                .flatMap(accelerometerRecordEntityObservable ->
                        accelerometerRecordEntityObservable
                                .map(AccelerometerRecordEntity::toSensorRecord))
                .toFlowable(BackpressureStrategy.BUFFER);
    }

    private interface Counter {
        Single<Long> count();
    }

    private interface Finder {
        Single<List<AccelerometerRecordEntity>> find(FindBucket findBucket);
    }

    protected class FindBucket implements Comparable<FindBucket> {
        private final Long offset;
        private final Long limit;

        private FindBucket(long offset, long limit) {
            this.offset = offset;
            this.limit = limit;
        }

        public long getOffset() {
            return offset;
        }

        public long getLimit() {
            return limit;
        }

        @Override
        public int compareTo(@NonNull FindBucket findBucket) {
            return offset.compareTo(findBucket.offset);
        }
    }

    protected List<FindBucket> createFindBucketsFrom(long count) {
        return Observable.fromIterable(createFindBucketsFrom(count, count))
                .sorted()
                .toList()
                .blockingGet();
    }

    private List<FindBucket> createFindBucketsFrom(long count, long remaining) {
        if (remaining < RESULT_COUNT_LIMIT) {
            LinkedList<FindBucket> findBuckets = new LinkedList<>();
            findBuckets.add(new FindBucket(count - remaining, remaining));
            return findBuckets;
        }

        List<FindBucket> findBuckets = createFindBucketsFrom(count, remaining - RESULT_COUNT_LIMIT);
        findBuckets.add(new FindBucket(count - remaining, RESULT_COUNT_LIMIT));
        return findBuckets;
    }
}
