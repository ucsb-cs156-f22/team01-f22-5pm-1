package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Api(description="Home Page with links to documentation")
@Slf4j
@RestController
public class HomeController {
    
    @ApiOperation(value = "Get general info about the server, including link to api documentation")
    @GetMapping("/")
    public ResponseEntity<String> getHome() throws JsonProcessingException {
        log.info("Home Page accessed");
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        // builder.scheme("http");
        URI uri = builder.build().toUri();

        String body = getHomePageObjectJSON(uri.toString());
        return ResponseEntity.ok().body(body);
    }
    
    public static String getHomePageObjectJSON(String baseUrl) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("greeting","Greetings from Spring Boot!");

        List<String> team = new ArrayList<String>();
        team.add("Liam P.");
        team.add("James Z.");
        team.add("Isaac H.");
        team.add("Gianmarco P.");
        team.add("Ethan L.");
        team.add("Christian S.");
        resultMap.put("team",team);
        resultMap.put("repo","https://github.com/ucsb-cs156-f22/team01-f22-5pm-1");
        resultMap.put("api-documentation", baseUrl + "swagger-ui/");
        resultMap.put("actuator", baseUrl + "actuator/");
        return mapper.writeValueAsString(resultMap);
    }
}
