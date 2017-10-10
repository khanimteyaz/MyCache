package com.org.mycache.masternode.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * Created by imteyaz.khan on 01/10/17.
 */
@Getter
@AllArgsConstructor
public class Context<T> {
    private T element;
}
