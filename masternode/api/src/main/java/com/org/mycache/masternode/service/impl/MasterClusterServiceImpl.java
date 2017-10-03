package com.org.mycache.masternode.service.impl;

import com.org.mycache.masternode.event.ClusterEvent;
import com.org.mycache.masternode.event.ClusterEventType;
import com.org.mycache.masternode.event.NodeEvent;
import com.org.mycache.masternode.event.eventbus.GuavaEventBusImpl;
import com.org.mycache.masternode.event.eventbus.MyCacheEventBus;
import com.org.mycache.masternode.model.Bucket;
import com.org.mycache.masternode.model.BucketRegistry;
import com.org.mycache.masternode.model.ClusterInfo;
import com.org.mycache.masternode.model.RegisteredBucket;
import com.org.mycache.masternode.service.MasterClusterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by imteyaz.khan on 01/10/17.
 */
@Component
@Slf4j
public class MasterClusterServiceImpl implements MasterClusterService {

    private MyCacheEventBus eventBus;
    public MasterClusterServiceImpl(MyCacheEventBus eventBus) {
        this.eventBus=eventBus;
    }
    @Override
    public void register(Bucket bucket) {
        log.debug("Going to add new bucket into cluster {}",bucket);
        if(BucketRegistry.instance().isPresent(bucket)) {
            log.debug("Bucket {} already present, touching timestamp",bucket);
            BucketRegistry.instance().touch(bucket);
            return;
        }
        BucketRegistry.instance().addBucket(bucket);
        eventBus.post(new ClusterEvent(ClusterEventType.ADDED, new NodeEvent(bucket)));
        log.debug("Bucket added into cluster {}",bucket);
    }

    @Override
    public void remove(Bucket bucket) {
        log.debug("Going to remove bucket from cluster {}",bucket);
        if(!BucketRegistry.instance().isPresent(bucket)) {
           log.debug("Bucket {} not present in cluster..skipping",bucket);
        }
        BucketRegistry.instance().removeBucket(bucket);
        eventBus.post(new ClusterEvent(ClusterEventType.REMOVED,new NodeEvent(bucket)));
        log.debug("Bucket removed from cluster {}",bucket);

    }

    @Override
    public ClusterInfo getBucketClusterInfo() {
        RegisteredBucket[] registeredBuckets=BucketRegistry.instance().getRegisterCopy();
        Bucket[] bucket=new Bucket[registeredBuckets.length];
        int index=0;
        for(RegisteredBucket registeredBucket:registeredBuckets){
            bucket[index++]=registeredBucket.getBucket();
        }
        return new ClusterInfo(BucketRegistry.instance().version(),bucket);
    }
}
