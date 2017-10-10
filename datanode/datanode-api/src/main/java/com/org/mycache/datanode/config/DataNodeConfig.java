package com.org.mycache.datanode.config;

import com.org.mycache.datanode.bootstrap.NodeRegistration;
import com.org.mycache.datanode.controller.HealthIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by imteyaz.khan on 10/10/17.
 */
@Configuration
public class DataNodeConfig {

    @Value("${datanode.id}")
    private int id;

    @Value("${datanode.masterUrl}")
    private String masterUrl;

    @Value("${server.port}")
    private int port;


    private String host() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }


    @Bean
    public NodeRegistration nodeRegistration() throws UnknownHostException {
        return new NodeRegistration(id,host(),port,masterUrl);
    }

}
