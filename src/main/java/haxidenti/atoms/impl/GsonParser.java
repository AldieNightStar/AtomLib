package haxidenti.atoms.impl;

import com.google.gson.Gson;
import haxidenti.atoms.interfaces.JsonParser;

public class GsonParser implements JsonParser {

    public static final GsonParser instance = new GsonParser();

    private Gson gson = new Gson();

    private GsonParser() {}

    @Override
    public String toJson(Object o) {
        return gson.toJson(o);
    }

    @Override
    public <T> T fromJson(String json, Class<T> c) {
        return gson.fromJson(json, c);
    }
}
