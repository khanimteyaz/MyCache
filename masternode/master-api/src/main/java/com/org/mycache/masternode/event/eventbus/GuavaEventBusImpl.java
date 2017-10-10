package com.org.mycache.masternode.event.eventbus;

import com.google.common.eventbus.*;
import com.google.common.eventbus.EventBus;
import com.org.mycache.masternode.event.Event;
import com.org.mycache.masternode.event.EventHandler;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by imteyaz.khan on 02/10/17.
 */
public class GuavaEventBusImpl implements MyCacheEventBus {
    private final EventBus eventBus;

    public GuavaEventBusImpl(EventBus eventBus)  {
        this.eventBus=eventBus;
    }

    @Override
    public void register(EventHandler eventHandler) {
        System.out.println("GuavaEventBusImpl.register::");
        eventBus.register(eventHandler);
    }

    @Override
    public void post(Event event) {
        System.out.println("GuavaEventBusImpl.post::::"+event.toString());
        eventBus.post(event);
    }

}
