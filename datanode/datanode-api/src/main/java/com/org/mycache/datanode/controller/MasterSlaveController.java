package com.org.mycache.datanode.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by imteyaz.khan on 05/10/17.
 */
@RestController
@Slf4j
@RequestMapping("/cache/node")abstract
@CrossOrigin(origins = "*")
public class MasterSlaveController {

}
