package com.mdstech.sample.service;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mdstech.sample.bo.JoltPayloadBO;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@Service
public class JoltTransformerServiceImpl implements JoltTransformerService {

    @Override
    public JsonNode transformJson(JoltPayloadBO joltPayloadBO) {
//        List<?> chainrConfig = JsonUtils.jsonToList(joltPayloadBO.getJsonSpec().toString(), Charset.defaultCharset().name());
        List jsonSpec = convertSpecJsonNode(joltPayloadBO.getJsonSpec());
        Chainr chainr = Chainr.fromSpec(jsonSpec);
        Object response = chainr.transform(convertJsonObject(joltPayloadBO.getInputJson()));

//        Object response = chainr.transform(JsonUtils.jsonToList(joltPayloadBO.getInputJson().toString(), Charset.defaultCharset().name()));
        return new ObjectMapper().valueToTree(response);
    }

    private List convertSpecJsonNode(JsonNode specJsonNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(specJsonNode, List.class);
    }

    private Object convertJsonObject(JsonNode inputNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        if(inputNode instanceof ArrayNode) {
            return objectMapper.convertValue(inputNode, List.class);
        }
        return objectMapper.convertValue(inputNode, Map.class);
    }
}
