package com.org.mycache.masternode.event;


import com.org.mycache.masternode.event.Event;

/**
 * Created by imteyaz.khan on 02/10/17.
 */
public interface EventHandler {
    public void handle(Event event);
}
