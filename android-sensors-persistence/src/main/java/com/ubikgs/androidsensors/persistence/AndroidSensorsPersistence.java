package com.ubikgs.androidsensors.persistence;

import android.content.Context;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.modules.AndroidSystemModule;
import com.ubikgs.androidsensors.persistence.modules.AndroidSensorsPersistenceConfigModule;
import com.ubikgs.androidsensors.persistence.repositories.SensorRecordRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

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

public class AndroidSensorsPersistence {


    @Inject Set<SensorRecordRepository> sensorRecordRepositories;

    private final Map<Class<? extends SensorRecordRepository>, SensorRecordRepository> mappedRepositoriesByClass;
    private final Map<SensorType, SensorRecordRepository> mappedRepositoriesByType;

    private AndroidSensorsPersistence(Context context, String sensorsDBName) {

        this.mappedRepositoriesByClass = new HashMap<>();
        this.mappedRepositoriesByType = new HashMap<>();

        DaggerAndroidSensorsPersistenceComponent.builder()
                .androidSystemModule(new AndroidSystemModule(context))
                .androidSensorsPersistenceConfigModule(
                        new AndroidSensorsPersistenceConfigModule(sensorsDBName))
                .build()
                .inject(this);

        createRepositoriesMap();
    }

    private void createRepositoriesMap() {
        for (SensorRecordRepository repository : sensorRecordRepositories) {
            mappedRepositoriesByClass.put(repository.getClass(), repository);
            mappedRepositoriesByType.put(repository.getSensorType(), repository);
        }
    }

    public Set<SensorRecordRepository> allSensorRecordRepositories() {
        return new HashSet<>(sensorRecordRepositories);
    }

    public <T extends SensorRecordRepository> T sensorRecordRepository(Class<T> type) {
        if (!mappedRepositoriesByClass.containsKey(type))
            throw new SensorRecordRepositoryNotAvailableException(type);

        return (T) mappedRepositoriesByClass.get(type);
    }

    public SensorRecordRepository sensorRecordRepositoryBy(SensorType sensorType) {
        if (!mappedRepositoriesByType.containsKey(sensorType))
            throw new SensorRecordRepositoryNotAvailableException(sensorType);

        return mappedRepositoriesByType.get(sensorType);
    }

    /*
    * Builder
    * */

    public interface Builder {
        Builder customSensorsDBName(String sensorsDBName);
        AndroidSensorsPersistence build(Context applicationContext);
    }

    public static Builder builder() {

        return new Builder() {

            private String sensorsDBName;

            @Override
            public Builder customSensorsDBName(String sensorsDBName) {
                this.sensorsDBName = sensorsDBName;
                return this;
            }

            @Override
            public AndroidSensorsPersistence build(Context applicationContext) {
                return new AndroidSensorsPersistence(applicationContext, sensorsDBName);
            }
        };
    }
}
