package com.johnsonfitness;

import java.util.Map;

import com.amazonaws.athena.connector.lambda.handlers.UserDefinedFunctionHandler;
import com.amazonaws.services.dynamodbv2.document.ItemUtils;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONObject;


/**
 * Hello world!
 *
 */
public class DdbUnmarshal extends UserDefinedFunctionHandler
{

    private static final String SOURCE_TYPE = "custom";

    public DdbUnmarshal()
    {
        super(SOURCE_TYPE);
    }

    public String unmarshal(String input)
    {
        JSONObject jsonObj = new JSONObject(input);
        @SuppressWarnings("unchecked")
        Map<String, Object> map = new Gson().fromJson(jsonObj.toString(), Map.class);
        ObjectMapper objectMapper = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        Map<String, AttributeValue> dynamoDbAttributes =
            objectMapper.convertValue(map, new TypeReference<Map<String, AttributeValue>>() {});
        return ItemUtils.toItem(dynamoDbAttributes).toJSON();
    }
}
