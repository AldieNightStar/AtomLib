package haxidenti.atoms.interfaces;

import haxidenti.atoms.impl.GsonParser;

public interface JsonParser {
    String toJson(Object o);
    <T> T fromJson(String json, Class<T> c);

    // Standard Parsers
    JsonParser GSON_PARSER = GsonParser.instance;
}
