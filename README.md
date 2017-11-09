# AndroidSensorsPersistence [![Build Status](https://travis-ci.org/ubikgs/AndroidSensorsPersistence.svg?branch=master)](https://travis-ci.org/ubikgs/AndroidSensorsPersistence) [ ![Download](https://api.bintray.com/packages/ubikgs/AndroidSensors/android-sensors-persistence/images/download.svg) ](https://bintray.com/ubikgs/AndroidSensors/android-sensors-persistence/_latestVersion) [![Javadocs](https://www.javadoc.io/badge/com.ubikgs/android-sensors-persistence.svg)](https://www.javadoc.io/doc/com.ubikgs/android-sensors-persistence)


An [AndroidSensors](https://github.com/ubikgs/AndroidSensors) companion library.

Facilitates storing and recovering sensor and location records to and from the local SQLite database.

Uses [RxJava](https://github.com/ReactiveX/RxJava) to achieve an asynchronous IO and [Room Persistence Library](https://developer.android.com/topic/libraries/architecture/room.html) as an ORM.

## Usage

Add the dependency

```groovy
dependencies {
    implementation 'com.ubikgs:android-sensors:1.0.0-alpha7'
    implementation 'com.ubikgs:android-sensors-persistence:1.0.0-alpha7'

    // To conveniently work with AndroidSensors output Flowables
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
    implementation 'io.reactivex.rxjava2:rxjava:2.1.6'
}
```

To start using the library, you must initialize it first:

```java
Context applicationContext;

AndroidSensorsPersistence androidSensorsPersistence = AndroidSensorsPersistence
                         .builder()
                         .build(applicationContext);
```
Library should be initialized only once. To avoid database issues, keep the instance as a singleton.

The library uses by default "android-sensors-database" as database name. If for any reason this database name enters in conflict with another database and you can't change it, you can configure AndroidSensorsPersistence database name doing the following:

```java
Context applicationContext;

AndroidSensorsPersistence androidSensorsPersistence = AndroidSensorsPersistence
                         .builder()
                         .customSensorsDBName("new-sensors-db-name")
                         .build(applicationContext);
```

Then you can request some sensor record repositories in different ways:

```java
AccelerometerRepository accelerometerRepository =
                androidSensorsPersistence.recordRepository(AccelerometerRepository.class);

RecordRepository recordRepository =
                androidSensorsPersistence.recordRepositoryBy(SensorType.ACCELEROMETER);

Set<RecordRepository> recordRepositories =
                androidSensorsPersistence.allRecordRepositories()
```

To consume and store the records from a sensor:

```java
Disposable subscriber = sensorGatherer.recordStream() // From AndroidSensors library, check the README to know how to initialize this
                .subscribeOn(Schedulers.newThread()) // Gather records on a new thread
                .observeOn(Schedulers.io()) // Store them on the io thread
                .buffer(1000) // Buffer 1000 elements
                .map(new Function<List<SensorRecord>, Single<List>>() { // Bulk write 1000 elements
                    @Override
                    public Single apply(List<SensorRecord> sensorRecords) throws Exception {
                        return recordRepository.createAll(sensorRecords;
                    }
                })
                .subscribe(); // Keep the stream alive

subscriber.dispose() // Stop gathering and storing records
```

If you re using Java 8 as a compilation and source target you can also do the following which is more succinct:

```java
Disposable subscriber = sensorGatherer.recordStream() // From AndroidSensors library, check the README to know how to initialize this
                .subscribeOn(Schedulers.newThread()) // Gather records on a new thread
                .observeOn(Schedulers.io()) // Store them on the io thread
                .buffer(1000) // Buffer 1000 elements
                .map(recordRepository::createAll) // Bulk write 1000 elements
                .subscribe(); // Keep the stream alive

subscriber.dispose() // Stop gathering and storing records
```

If you prefer to work in this way you'll need [Retrolambda](https://github.com/orfjackal/retrolambda) or [Android Studio 3.0.0 Preview](https://developer.android.com/studio/preview/index.html).

When using Android Studio 3.0.0, you'll have to specify the following in your app `build.gradle`:

```groovy
android {

    compileOptions {
            sourceCompatibility JavaVersion.VERSION_1_8
            targetCompatibility JavaVersion.VERSION_1_8
    }

}
```

## License
    Copyright 2017 Ubik Geospatial Solutions
    Copyright 2017 Alberto González Pérez

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.