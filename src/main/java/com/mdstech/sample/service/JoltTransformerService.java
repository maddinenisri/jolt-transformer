package com.mdstech.sample.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.mdstech.sample.bo.JoltPayloadBO;

public interface JoltTransformerService {
    JsonNode transformJson(JoltPayloadBO joltPayloadBO);
}
