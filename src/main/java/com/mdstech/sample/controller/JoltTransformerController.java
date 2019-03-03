package com.mdstech.sample.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.mdstech.sample.bo.JoltPayloadBO;
import com.mdstech.sample.service.JoltTransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoltTransformerController {

    @Autowired
    private JoltTransformerService joltTransformerService;

    @PostMapping(path = "/api/transform")
    @ResponseBody
    public ResponseEntity<JsonNode> transform(@RequestBody JoltPayloadBO joltPayloadBO) {
        JsonNode jsonNode = joltTransformerService.transformJson(joltPayloadBO);
        return ResponseEntity.ok(jsonNode);
    }
}
