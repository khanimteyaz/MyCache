package com.org.mycache.masternode.event.handler;

import com.org.mycache.core.model.node.Bucket;
import com.org.mycache.masternode.command.Command;
import com.org.mycache.masternode.command.Context;
import com.org.mycache.masternode.event.Event;
import com.org.mycache.masternode.event.EventHandler;
import com.org.mycache.masternode.event.NodeEvent;

/**
 * Created by imteyaz.khan on 02/10/17.
 */
public class NodeRemovedEventHandler implements EventHandler {

    private Command command;

    public NodeRemovedEventHandler(Command command) {
        this.command = command;
    }

    @Override
    public void handle(Event event) {
        NodeEvent nodeEvent = (NodeEvent) event;
        Context<Bucket> bucketContext = new Context<>(nodeEvent.getBucket());
        command.execute(bucketContext);
    }
}
