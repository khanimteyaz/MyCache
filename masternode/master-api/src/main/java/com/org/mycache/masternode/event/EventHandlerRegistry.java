package com.org.mycache.masternode.event;

import com.org.mycache.masternode.command.OwnershipChangeCommand;
import com.org.mycache.masternode.event.handler.NodeAddedEventHandler;
import com.org.mycache.masternode.event.handler.NodeRemovedEventHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by imteyaz.khan on 02/10/17.
 */
public class EventHandlerRegistry {
    private static EventHandlerRegistry instance_=new EventHandlerRegistry();
    private Map<ClusterEventType,EventHandler> registryMap;

    private EventHandlerRegistry() {
        registryMap =new HashMap<>();
        registerHandler();
    }

    public static EventHandlerRegistry instance() {
        return instance_;
    }
    private void registerHandler() {
        registryMap.put(ClusterEventType.ADDED, new NodeAddedEventHandler(new OwnershipChangeCommand()));
        registryMap.put(ClusterEventType.REMOVED, new NodeRemovedEventHandler(new OwnershipChangeCommand()));
    }

    public EventHandler getHandler(ClusterEventType eventType) {
        return registryMap.get(eventType);
    }
}
