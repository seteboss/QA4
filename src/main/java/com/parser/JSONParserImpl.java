package com.parser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class JSONParserImpl implements JSONParser {

    @Override
    public List<String> parsReport(String json) {
        JSONArray array = new JSONArray(json);

        List<String> repos = new ArrayList<>();

        IntStream.range(0, array.length())
                .forEach(i -> repos.add(
                        ((JSONObject) array.get(i)).getString("name")
                ));

        return repos;
    }
}
