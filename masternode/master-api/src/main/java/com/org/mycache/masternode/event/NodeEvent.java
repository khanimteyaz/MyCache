package com.org.mycache.masternode.event;

import com.org.mycache.core.model.node.Bucket;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by imteyaz.khan on 02/10/17.
 */
@Getter
@AllArgsConstructor
public class NodeEvent implements Event {
    private Bucket bucket;
}
