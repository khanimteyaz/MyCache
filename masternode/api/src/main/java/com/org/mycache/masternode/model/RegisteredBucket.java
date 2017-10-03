package com.org.mycache.masternode.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by imteyaz.khan on 01/10/17.
 */
@Getter
@Builder
public class RegisteredBucket implements Comparable<RegisteredBucket>{
    private Bucket bucket;
    private long lastAccessedTime;

    public void touch() {
        lastAccessedTime=System.currentTimeMillis();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegisteredBucket that = (RegisteredBucket) o;

        if (bucket != null ? !bucket.equals(that.bucket) : that.bucket != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return bucket != null ? bucket.hashCode() : 0;
    }

    @Override
    public int compareTo(RegisteredBucket o) {
        return bucket.compareTo(o.bucket);
    }

}
