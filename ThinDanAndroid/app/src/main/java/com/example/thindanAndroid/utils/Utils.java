package com.example.thindanAndroid.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Utils {

    //coverts a map of values into input JSON String for POST Method
    public static String convertToJsonString(Map<String, String> valueMap) throws JSONException {
        JSONObject jsonInput = new JSONObject();
        for (String key : valueMap.keySet()) {
            jsonInput.put(key, valueMap.get(key));
        }
        return jsonInput.toString();
    }

}
