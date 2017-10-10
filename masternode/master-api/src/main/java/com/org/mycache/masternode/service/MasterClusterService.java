package com.org.mycache.masternode.service;

import com.org.mycache.core.model.node.Bucket;
import com.org.mycache.masternode.model.ClusterInfo;

/**
 * Created by imteyaz.khan on 01/10/17.
 */

public interface MasterClusterService {
    public void register(Bucket bucket);
    public void remove(Bucket bucket);
    public ClusterInfo getBucketClusterInfo();
}
