package com.org.mycache.core.model.node;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by imteyaz.khan on 01/10/17.
 */
@Getter
 @AllArgsConstructor
@ToString
public class Bucket implements Comparable<Bucket>{
    private int id;
    private String host;
    private int port;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bucket bucket = (Bucket) o;

        if (id != bucket.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public int compareTo(Bucket o) {
        return Integer.compare(getId(),o.getId());
    }
}
