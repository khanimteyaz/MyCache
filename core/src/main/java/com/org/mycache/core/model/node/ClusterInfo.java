package com.org.mycache.core.model.node;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by imteyaz.khan on 01/10/17.
 */
@Getter
@AllArgsConstructor
public class ClusterInfo {
    private int version;
    private Bucket[] buckets;
}
