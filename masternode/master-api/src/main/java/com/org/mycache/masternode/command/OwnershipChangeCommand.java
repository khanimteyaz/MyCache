package com.org.mycache.masternode.command;

import com.org.mycache.core.model.node.Bucket;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by imteyaz.khan on 02/10/17.
 */
@Slf4j
public class OwnershipChangeCommand implements Command<Context<Bucket>> {

    @Override
    public void execute(Context<Bucket> context) {
        log.debug("Going to execute command for new bucket {} addition",context.getElement());
    }
}
