package com.org.mycache.datanode.bootstrap;

import static com.org.mycache.core.http.HttpRemoteUtil.post;
import static com.org.mycache.core.http.HttpRemoteUtil.defaultHeaders;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.org.mycache.core.model.node.Bucket;
import com.org.mycache.core.model.node.ClusterInfo;
import lombok.extern.slf4j.Slf4j;


import javax.annotation.PostConstruct;

/**
 * Created by imteyaz.khan on 05/10/17.
 */
@Slf4j
public class NodeRegistration {
    public static final String MASTER_NODE_REGISTRATION_CONTEXT_PATH="/cache/master/bucket";
    private int id;
    private String masterUrl;
    private String selfIp;
    private int selfPort;

    public NodeRegistration(int id,String  selfIp,int selfPort,String masterUrl) {
        this.id=id;
        this.selfIp=selfIp;
        this.selfPort=selfPort;
        this.masterUrl=masterUrl;
    }

    @PostConstruct
    public void registerWithMaster() throws UnirestException {
        Bucket bucket=new Bucket(id,this.selfIp,this.selfPort);
        post(getBucketRegistrationUrl(), ClusterInfo.class, Bucket.class, bucket, null, defaultHeaders());
    }

    private String getBucketRegistrationUrl() {
        return new StringBuilder(this.masterUrl).append(MASTER_NODE_REGISTRATION_CONTEXT_PATH).toString();
    }
}
