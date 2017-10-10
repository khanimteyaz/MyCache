package com.org.mycache.datanode.controller;

import com.org.mycache.datanode.core.Health;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by imteyaz.khan on 27/09/17.
 */
@RestController()
@Slf4j
@RequestMapping("/cache/node")
@CrossOrigin(origins = "*")
public class HealthIndicator {
    private static String greenHealthJson = "{\"status\":\"Ready to serve\"}";
    private static String readHealthJson = "{\"status\":\"Not ready\"}";

    @RequestMapping(path = "/up", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE,
    })
    public
    @ResponseBody
    ResponseEntity<Boolean> available() {
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @RequestMapping(path = "/health", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE,
    })
    public
    @ResponseBody
    ResponseEntity<String> health() {
        if (Health.instance().ready()) {
            return new ResponseEntity(greenHealthJson, HttpStatus.OK);
        }
        return new ResponseEntity(readHealthJson, HttpStatus.FAILED_DEPENDENCY);
    }
}
