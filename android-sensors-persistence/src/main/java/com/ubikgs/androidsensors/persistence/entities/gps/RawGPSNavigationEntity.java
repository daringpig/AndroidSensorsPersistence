package com.ubikgs.androidsensors.persistence.entities.gps;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.ubikgs.androidsensors.persistence.entities.RecordEntity;
import com.ubikgs.androidsensors.records.gps.RawGPSNavigationRecord;

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
@Entity(indices = {@Index("foreignKey")})
public class RawGPSNavigationEntity extends RawGPSNavigationRecord implements RecordEntity<RawGPSNavigationRecord> {

    @PrimaryKey(autoGenerate = true)
    private long uid;

    private long foreignKey;

    public RawGPSNavigationEntity() {
        super();
    }

    public RawGPSNavigationEntity(RawGPSNavigationRecord rawGPSNavigationRecord) {
        super(rawGPSNavigationRecord);
    }

    @Override
    public long getUid() {
        return uid;
    }

    @Override
    public void setUid(long uid) {
        this.uid = uid;
    }

    @Override
    public long getForeignKey() {
        return foreignKey;
    }

    @Override
    public void setForeignKey(long foreignKey) {
        this.foreignKey = foreignKey;
    }

    @Override
    public RawGPSNavigationRecord toSensorRecord() {
        return new RawGPSNavigationRecord(this);
    }

    @Override
    public String toString() {
        return "RawGPSNavigationEntity{" +
                "uid=" + uid +
                ", foreignKey=" + foreignKey +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RawGPSNavigationEntity)) return false;
        if (!super.equals(o)) return false;

        RawGPSNavigationEntity that = (RawGPSNavigationEntity) o;

        if (getUid() != that.getUid()) return false;
        return getForeignKey() == that.getForeignKey();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (getUid() ^ (getUid() >>> 32));
        result = 31 * result + (int) (getForeignKey() ^ (getForeignKey() >>> 32));
        return result;
    }
}
