package com.org.mycache.masternode.event.eventbus;

import com.org.mycache.masternode.event.Event;
import com.org.mycache.masternode.event.EventHandler;

/**
 * Created by imteyaz.khan on 02/10/17.
 */
public interface MyCacheEventBus {
    public void register(EventHandler eventHandler);
    public void post(Event event);
}
