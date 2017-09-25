package com.ubikgs.androidsensors.persistence.testutils;

import com.ubikgs.androidsensors.persistence.entities.gps.LocationRecordEntity;
import com.ubikgs.androidsensors.persistence.entities.gps.RawGPSMeasurementsRecordEntity;
import com.ubikgs.androidsensors.persistence.entities.gps.RawGPSNavigationRecordEntity;
import com.ubikgs.androidsensors.persistence.entities.gps.RawGPSStatusRecordEntity;
import com.ubikgs.androidsensors.persistence.entities.imu.AccelerometerRecordEntity;
import com.ubikgs.androidsensors.persistence.entities.imu.GravityRecordEntity;
import com.ubikgs.androidsensors.persistence.entities.imu.GyroscopeRecordEntity;
import com.ubikgs.androidsensors.persistence.entities.imu.LinearAccelerationRecordEntity;
import com.ubikgs.androidsensors.persistence.entities.imu.MagneticFieldRecordEntity;
import com.ubikgs.androidsensors.persistence.entities.imu.RotationVectorRecordEntity;
import com.ubikgs.androidsensors.records.SensorRecord;
import com.ubikgs.androidsensors.records.imu.TriAxisRecord;

import java.util.Date;
import java.util.Random;

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

public class TestEntityProvider {

    private static Random random = new Random();

    public static AccelerometerRecordEntity createAccelerometerRecordEntity() {
        AccelerometerRecordEntity entity = new AccelerometerRecordEntity();
        initializeSensorRecordFields(entity);
        initializeTriAxisRecordFields(entity);
        return entity;
    }

    public static GravityRecordEntity createGravityRecordEntity() {
        GravityRecordEntity entity = new GravityRecordEntity();
        initializeSensorRecordFields(entity);
        initializeTriAxisRecordFields(entity);
        return entity;
    }

    public static GyroscopeRecordEntity createGyroscopeRecordEntity() {
        GyroscopeRecordEntity entity = new GyroscopeRecordEntity();
        initializeSensorRecordFields(entity);
        initializeTriAxisRecordFields(entity);
        return entity;
    }

    public static LinearAccelerationRecordEntity createLinearAccelerationRecordEntity() {
        LinearAccelerationRecordEntity entity = new LinearAccelerationRecordEntity();
        initializeSensorRecordFields(entity);
        initializeTriAxisRecordFields(entity);
        return entity;
    }

    public static MagneticFieldRecordEntity createMagneticFieldRecordEntity() {
        MagneticFieldRecordEntity entity = new MagneticFieldRecordEntity();
        initializeSensorRecordFields(entity);
        initializeTriAxisRecordFields(entity);
        return entity;
    }

    public static RotationVectorRecordEntity createRotationVectorRecordEntity() {
        RotationVectorRecordEntity entity = new RotationVectorRecordEntity();
        initializeSensorRecordFields(entity);
        entity.setXSin(random.nextFloat());
        entity.setYSin(random.nextFloat());
        entity.setZSin(random.nextFloat());
        entity.setCos(random.nextFloat());
        return entity;
    }

    public static LocationRecordEntity createLocationRecordEntity() {
        LocationRecordEntity entity = new LocationRecordEntity();
        initializeSensorRecordFields(entity);
        entity.setLatitude(random.nextDouble());
        entity.setLongitude(random.nextDouble());
        entity.setAltitude(random.nextDouble());
        entity.setSpeed(random.nextFloat());
        entity.setBearing(random.nextFloat());
        return entity;
    }

    public static RawGPSMeasurementsRecordEntity createRawGPSMeasurementsRecordEntity() {
        RawGPSMeasurementsRecordEntity entity = new RawGPSMeasurementsRecordEntity();
        entity.setSatelliteCount(random.nextInt(3));
        entity.setSvids(generateRandomIntArray());
        entity.setConstellations(generateRandomIntArray());
        entity.setTimeOffsets(generateRandomDoubleArray());
        entity.setStateCodes(generateRandomIntArray());
        entity.setSvTimes(generateRandomLongArray());
        entity.setSvTimeUncerts(generateRandomLongArray());
        entity.setCn0DbHzs(generateRandomDoubleArray());
        entity.setPseudoranges(generateRandomDoubleArray());
        entity.setPseudorangeUncerts(generateRandomDoubleArray());
        entity.setDeltaStates(generateRandomIntArray());
        entity.setDeltas(generateRandomDoubleArray());
        entity.setDeltaUncerts(generateRandomDoubleArray());
        entity.setMultipaths(generateRandomIntArray());
        return entity;
    }

    public static RawGPSNavigationRecordEntity createRawGPSNavigationRecordEntity() {
        RawGPSNavigationRecordEntity entity = new RawGPSNavigationRecordEntity();
        entity.setAccuracy(random.nextFloat());
        entity.setSensorTimestamp(new Date().getTime());
        entity.setSystemTimestamp(new Date().getTime());
        entity.setMessage(new String(new char[255]));
        return entity;
    }

    public static RawGPSStatusRecordEntity createRawGPSStatusRecordEntity() {
        RawGPSStatusRecordEntity entity = new RawGPSStatusRecordEntity();
        entity.setAccuracy(random.nextFloat());
        entity.setSensorTimestamp(new Date().getTime());
        entity.setSystemTimestamp(new Date().getTime());
        entity.setSatelliteCount(random.nextInt(3));
        entity.setAzimuths(generateRandomFloatArray());
        entity.setCn0DHzs(generateRandomFloatArray());
        entity.setConstellationTypes(generateRandomIntArray());
        entity.setElevations(generateRandomFloatArray());
        entity.setSvids(generateRandomIntArray());
        return entity;
    }

    private static void initializeSensorRecordFields(SensorRecord sensorRecord) {
        sensorRecord.setAccuracy(random.nextFloat());
        sensorRecord.setSensorTimestamp(new Date().getTime());
        sensorRecord.setSystemTimestamp(new Date().getTime());
    }

    private static void initializeTriAxisRecordFields(TriAxisRecord triAxisRecord) {
        triAxisRecord.setX(random.nextFloat());
        triAxisRecord.setY(random.nextFloat());
        triAxisRecord.setZ(random.nextFloat());
    }

    private static double[] generateRandomDoubleArray() {
        double[] doubles = new double[generateRandomArrayLength()];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = random.nextDouble();
        }
        return doubles;
    }

    private static float[] generateRandomFloatArray() {
        float[] floats = new float[generateRandomArrayLength()];
        for (int i = 0; i < floats.length; i++) {
            floats[i] = random.nextFloat();
        }
        return floats;
    }

    private static int[] generateRandomIntArray() {
        int[] ints = new int[generateRandomArrayLength()];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt();
        }
        return ints;
    }

    private static long[] generateRandomLongArray() {
        long[] longs = new long[generateRandomArrayLength()];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = random.nextLong();
        }
        return longs;
    }

    private static int generateRandomArrayLength() {
        return random.nextInt(3);
    }
}
