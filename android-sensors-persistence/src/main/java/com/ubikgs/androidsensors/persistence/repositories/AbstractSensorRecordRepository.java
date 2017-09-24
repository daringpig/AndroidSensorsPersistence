package com.ubikgs.androidsensors.persistence.repositories;

import com.ubikgs.androidsensors.persistence.daos.SensorRecordEntityDao;
import com.ubikgs.androidsensors.persistence.entities.SensorRecordEntity;
import com.ubikgs.androidsensors.persistence.repositories.common.FindBucket;
import com.ubikgs.androidsensors.records.SensorRecord;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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

public abstract class AbstractSensorRecordRepository<T extends SensorRecord, E extends SensorRecordEntity<T>>
        implements SensorRecordRepository<T> {

    private static final int RESULT_COUNT_LIMIT = 1000;

    private final SensorRecordEntityDao<E> sensorRecordEntityDao;

    public AbstractSensorRecordRepository(SensorRecordEntityDao<E> sensorRecordEntityDao) {
        this.sensorRecordEntityDao = sensorRecordEntityDao;
    }

    public Single<List<Long>> createAll(Collection<T> sensorRecords) {
        return Observable.fromIterable(sensorRecords)
                .map(this::createFrom)
                .toList()
                .map(sensorRecordEntityDao::createAll);
    }

    public Single<List<Long>> createAll(Collection<T> sensorRecords, Long foreignKey) {
        return Observable.fromIterable(sensorRecords)
                .map(this::createFrom)
                .map(e -> {
                    e.setForeignKey(foreignKey);
                    return e;
                })
                .toList()
                .map(sensorRecordEntityDao::createAll);
    }

    protected abstract E createFrom(T sensorRecord);

    public Flowable<T> findAll() {
        return findAllWith(this::count, findBucket ->
                sensorRecordEntityDao.findAll(findBucket.getOffset(), findBucket.getLimit()));
    }

    public Single<Long> count() {
        return sensorRecordEntityDao.count();
    }

    public void removeAll() {
        sensorRecordEntityDao.removeAll();
    }

    public Flowable<T> findAllBy(long foreignKey) {
        return findAllWith(
                () -> countBy(foreignKey),
                findBucket -> sensorRecordEntityDao.findAllBy(
                        foreignKey,
                        findBucket.getOffset(),
                        findBucket.getLimit()
                )
        );
    }

    public Single<Long> countBy(long foreignKey) {
        return sensorRecordEntityDao.countBy(foreignKey);
    }

    public void removeAllBy(long foreignKey) {
        sensorRecordEntityDao.removeAllBy(foreignKey);
    }

    private Flowable<T> findAllWith(Counter counter, Finder<E> finder) {
        return counter.count()
                .map(this::createFindBucketsFrom)
                .flatMapObservable(Observable::fromIterable)
                .flatMapSingle(finder::find)
                .map(Observable::fromIterable)
                .flatMap(accelerometerRecordEntityObservable ->
                        accelerometerRecordEntityObservable
                                .map(this::transformIn))
                .toFlowable(BackpressureStrategy.BUFFER);
    }

    protected abstract T transformIn(E entity);

    private interface Counter {
        Single<Long> count();
    }

    private interface Finder<E extends SensorRecordEntity> {
        Single<List<E>> find(FindBucket findBucket);
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
