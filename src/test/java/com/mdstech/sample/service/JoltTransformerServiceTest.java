package com.mdstech.sample.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdstech.sample.SpringUnitTestCaseHelper;
import com.mdstech.sample.bo.JoltPayloadBO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class JoltTransformerServiceTest extends SpringUnitTestCaseHelper {

    @Autowired
    private JoltTransformerService joltTransformerService;

    @Test
    public void testTransform() throws IOException {
        JsonNode jsonNode = joltTransformerService.transformJson(createSampleInput());
        ObjectMapper objectMapper = new ObjectMapper();
        assertThat("Expected not null value", jsonNode.toString(), is(notNullValue()));
    }

    private JoltPayloadBO createSampleInput() throws IOException {
        return JoltPayloadBO
                .builder()
                .inputJson(getInputJsonNode())
                .jsonSpec(getInputSpecJsonNode())
                .build();
    }

    private JsonNode getInputSpecJsonNode() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String inputSpec = "[{ \"operation\": \"shift\", \"spec\": { \"*\": { \"PersonalDetails\": \"@(1,ID).PersonalDetails\", \"OfficeDetails\": \"@(1,ID).OfficeDetails\" } } }, { \"operation\": \"shift\", \"spec\": { \"*\": { \"$\": \"[#2].ID\", \"@.OfficeDetails\": \"[#2].OfficeDetails\", \"@.PersonalDetails\": \"[#2].PersonalDetails\"}}}]";
        return objectMapper.readTree(inputSpec);
    }

    private JsonNode getInputJsonNode() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String inputPayload = "[\n" +
                "  { \"ID\": \"100\",\n" +
                "    \"PersonalDetails\": [\n" +
                "      { \"name\": \"leo\",\n" +
                "        \"age\": \"30\",\n" +
                "        \"address\": \"Us\" }\n" +
                "    ] },\n" +
                "  { \"ID\": \"100\",\n" +
                "    \"OfficeDetails\": [\n" +
                "      { \"Company\": \"lys\",\n" +
                "        \"salary\": \"2000\",\n" +
                "        \"designation\": \"manager\" }\n" +
                "    ] },\n" +
                "  { \"ID\": \"101\",\n" +
                "    \"PersonalDetails\": [\n" +
                "      { \"name\": \"karo\",\n" +
                "        \"age\": \"24\",\n" +
                "        \"address\": \"Newyork\" }\n" +
                "    ] },\n" +
                "  { \"ID\": \"101\",\n" +
                "    \"OfficeDetails\": [\n" +
                "      { \"Company\": \"lys\",\n" +
                "        \"salary\": \"1000\",\n" +
                "        \"designation\": \"Operator\" }\n" +
                "    ] }\n" +
                "]";

        return objectMapper.readTree(inputPayload);
    }

}
