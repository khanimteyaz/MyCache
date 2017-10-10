package com.org.mycache.masternode.daemon;

import com.org.mycache.masternode.command.ClusterHealthCheckCommand;
import com.org.mycache.masternode.command.Command;
import com.org.mycache.masternode.model.BucketRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by imteyaz.khan on 01/10/17.
 */
@Component
@Slf4j
public class NodeStatusJob {

    private ClusterHealthCheckCommand command;
    public NodeStatusJob(ClusterHealthCheckCommand command) {
        this.command=command;
    }

    @Scheduled(fixedDelay = 1000*60)
    public void run() {
        log.debug("Performing node status check");
        command.execute(BucketRegistry.instance().getRegisterCopy());
    }
}
