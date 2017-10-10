package com.org.mycache.masternode.command;

import com.google.common.eventbus.EventBus;
import com.org.mycache.masternode.event.EventHandler;
import com.org.mycache.masternode.event.eventbus.MyCacheEventBus;
import com.org.mycache.masternode.event.handler.NodeRemovedEventHandler;
import com.org.mycache.masternode.model.RegisteredBucket;
import com.org.mycache.masternode.service.MasterClusterService;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by imteyaz.khan on 01/10/17.
 */
@Slf4j
public class ClusterHealthCheckCommand implements Command<RegisteredBucket[]> {

    private int lastConfiguredMinute =10*60*1000;
    private final MasterClusterService masterClusterService;
    public ClusterHealthCheckCommand(MasterClusterService masterClusterService) {
        this.masterClusterService=masterClusterService;
    }
    @Override
    public void execute(RegisteredBucket[] registeredBuckets) {
        log.debug("Performing cluster health check");
        for(RegisteredBucket registeredBucket:registeredBuckets) {
            if((System.currentTimeMillis()-registeredBucket.getLastAccessedTime())> lastConfiguredMinute) {
                log.debug("Bucket {} not available.Launching bucket removal command",registeredBucket.getBucket());
                masterClusterService.remove(registeredBucket.getBucket());
            }
        }
    }
}
