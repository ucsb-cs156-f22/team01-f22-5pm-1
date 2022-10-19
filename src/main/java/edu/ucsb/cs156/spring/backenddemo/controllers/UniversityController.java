package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.UniversityQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description="University info from universities.hipolabs.com")
@Slf4j
@RestController
@RequestMapping("/api/university")
public class UniversityController {
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UniversityQueryService universityQueryService;

    @ApiOperation(value="Get list of universities that match a given name", notes="Uses API documented here: http://universities.hipolabs.com/search")
    @GetMapping("/get")
    public ResponseEntity<String> getUniversity(
        @ApiParam("name to search, e.g. 'Harvard' or 'Stanford'") @RequestParam String name
    ) throws JsonProcessingException {
        log.info("getUniversity: name={}", name);
        String result = universityQueryService.getJSON(name);
        return ResponseEntity.ok().body(result);
    }
}