package com.mdstech.sample.bo;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JoltPayloadBO {
    private JsonNode inputJson;
    private JsonNode jsonSpec;
}
