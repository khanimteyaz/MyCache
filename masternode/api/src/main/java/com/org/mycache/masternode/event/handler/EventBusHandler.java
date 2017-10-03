package com.org.mycache.masternode.event.handler;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.org.mycache.masternode.event.*;
import com.org.mycache.masternode.event.EventHandler;
import com.org.mycache.masternode.event.eventbus.GuavaEventBusImpl;
import com.org.mycache.masternode.event.eventbus.MyCacheEventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * Created by imteyaz.khan on 02/10/17.
 */
@Slf4j
public class EventBusHandler implements EventHandler {

    private final EventHandlerRegistry registry;

    public EventBusHandler(EventHandlerRegistry registry,MyCacheEventBus myCacheEventBus) {
        this.registry = registry;
        register(myCacheEventBus);
    }

    @Subscribe
    @AllowConcurrentEvents
    @Override
    public void handle(final Event event) {
        log.debug("Got cluster event {}",event);
        ClusterEvent clusterEvent=(ClusterEvent)event;
        EventHandler handler=registry.getHandler(clusterEvent.getEventType());
        log.debug("Going to handover event {} to handler {}",event,handler.getClass().getName());
        handler.handle(clusterEvent.getNodeEvent());
    }

    private void register(final MyCacheEventBus myCacheEventBus) {
        log.debug("Event bus registration completed");
        myCacheEventBus.register(this);
    }
}
