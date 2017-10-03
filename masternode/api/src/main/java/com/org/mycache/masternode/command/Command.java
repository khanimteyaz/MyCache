package com.org.mycache.masternode.command;

/**
 * Created by imteyaz.khan on 01/10/17.
 */
public interface Command<T> {
    public void execute(T context);
}
