package com.org.mycache.masternode.command;

import com.org.mycache.masternode.model.Bucket;

/**
 * Created by imteyaz.khan on 02/10/17.
 */
public class DatashiftContext extends Context<Bucket> {
    private Bucket destination;
    public DatashiftContext(Bucket source,Bucket destination) {
        super(source);
        this.destination=destination;
    }

    public Bucket getSourceBucket() {
        return getElement();
    }

    public Bucket getDestination() {
        return this.destination;
    }

}
