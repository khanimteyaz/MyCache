package com.org.mycache.masternode.controller;

/**
 * Created by imteyaz.khan on 01/10/17.
 */

import com.org.mycache.masternode.model.Bucket;
import com.org.mycache.masternode.model.ClusterInfo;
import com.org.mycache.masternode.service.MasterClusterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/cache/master")
@CrossOrigin(origins = "*")
public class MasterController {

    private MasterClusterService masterClusterService;
    public MasterController(MasterClusterService masterClusterService) {
        this.masterClusterService=masterClusterService;
    }

    @RequestMapping(path = "/cluster",method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE,
    })
    public ResponseEntity<ClusterInfo> list() {
        return new ResponseEntity(masterClusterService.getBucketClusterInfo(),HttpStatus.OK);
    }

    @RequestMapping(path = "/bucket",method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE,
    })
    public ResponseEntity<ClusterInfo> register(@RequestBody Bucket bucket) {
        masterClusterService.register(bucket);
        return new ResponseEntity(masterClusterService.getBucketClusterInfo(),HttpStatus.OK);
    }

 }

