package com.org.mycache.masternode.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by imteyaz.khan on 02/10/17.
 */
@Getter
@AllArgsConstructor
public class ClusterEvent implements Event {
    private ClusterEventType eventType;
    private Event nodeEvent;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClusterEvent{");
        sb.append("eventType=").append(eventType);
        sb.append(", nodeEvent=").append(nodeEvent);
        sb.append('}');
        return sb.toString();
    }
}
