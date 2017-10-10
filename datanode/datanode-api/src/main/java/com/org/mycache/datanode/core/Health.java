package com.org.mycache.datanode.core;

/**
 * Created by imteyaz.khan on 28/09/17.
 */
public class Health {

    private static Health instance=new Health();
    private boolean ready;

    public static Health instance() {
        return instance;
    }

    public synchronized void changeStatus(boolean ready) {
        this.ready=ready;
    }

    public boolean ready() {
        return this.ready;
    }

}
