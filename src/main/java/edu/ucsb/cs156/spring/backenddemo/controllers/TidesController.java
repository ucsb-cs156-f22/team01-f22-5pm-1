package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.EarthquakeQueryService;
import edu.ucsb.cs156.spring.backenddemo.services.TidesQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Api(description="Info on tides")
@Slf4j
@RestController
@RequestMapping("/api/tides")
public class TidesController {
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    TidesQueryService tideQueryService;

    @ApiOperation(value = "Get water level for date range, in local time.", notes = "For station id, see: https://tidesandcurrents.noaa.gov/tide_predictions.html?gid=1393")
    @GetMapping("/get")
    public ResponseEntity<String> getTides(
        @ApiParam("beginDate in format yyyymmdd") @RequestParam String beginDate,
        @ApiParam("endDate in format yyyymmdd") @RequestParam String endDate,
        @ApiParam("station, e.g. 9411340 for Santa Barbara") @RequestParam String station
    ) throws JsonProcessingException {
        log.info("getTides: beginDate={} endDate={}, station={}", beginDate, endDate);
        String result = tideQueryService.getJSON(beginDate, endDate, station);
        return ResponseEntity.ok().body(result);
    }
}
