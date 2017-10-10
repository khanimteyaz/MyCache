package com.org.mycache.masternode.config;

import com.google.common.eventbus.EventBus;
import com.org.mycache.masternode.command.ClusterHealthCheckCommand;
import com.org.mycache.masternode.controller.MasterController;
import com.org.mycache.masternode.daemon.NodeStatusJob;
import com.org.mycache.masternode.event.EventHandler;
import com.org.mycache.masternode.event.EventHandlerRegistry;
import com.org.mycache.masternode.event.eventbus.GuavaEventBusImpl;
import com.org.mycache.masternode.event.eventbus.MyCacheEventBus;
import com.org.mycache.masternode.event.handler.EventBusHandler;
import com.org.mycache.masternode.service.MasterClusterService;
import com.org.mycache.masternode.service.impl.MasterClusterServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import java.util.concurrent.Executor;

/**
 * Created by imteyaz.khan on 02/10/17.
 */
@Configuration
@EnableAsync
public class MasterConfig {

    @Bean
    public MyCacheEventBus myCacheEventBus() {
        return new GuavaEventBusImpl(new EventBus());
    }

    @Bean
    public EventHandlerRegistry eventHandlerRegistry() {
        return EventHandlerRegistry.instance();
    }
    @Bean
    public EventHandler defaultEventHandler() {
        return new EventBusHandler(eventHandlerRegistry(),myCacheEventBus());
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }

    @Bean
    public MasterClusterService masterClusterService() {
        return new MasterClusterServiceImpl(myCacheEventBus());
    }

    @Bean
    public NodeStatusJob nodeStatusJob() {
        return new NodeStatusJob(new ClusterHealthCheckCommand(masterClusterService()));
    }

    @Bean
    public MasterController masterController() {
        return new MasterController(masterClusterService());
    }
}
