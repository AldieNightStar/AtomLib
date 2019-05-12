package haxidenti.atoms.impl;

import haxidenti.atoms.Atom;
import haxidenti.atoms.interfaces.HttpClient;
import haxidenti.atoms.interfaces.JsonParser;

import java.util.function.Function;

public class HttpBasedAtom<T> implements Atom<T> {

    private HttpClient client;
    private JsonParser parser;
    private String url;
    private Class<T> type;

    public HttpBasedAtom(String url, JsonParser parser, HttpClient client, Class<T> type) {
        this.url = url;
        this.parser = parser;
        this.client = client;
        this.type = type;
    }

    @Override
    public void set(T o) {
        postToClient(o);
    }

    @Override
    public T get() {
        return getFromClient();
    }

    @Override
    public T getThenSet(T o) {
        T previous = getFromClient();
        postToClient(o);
        return previous;
    }

    @Override
    public void operate(Function<T, T> func) {
        postToClient(func.apply(getFromClient()));
    }

    private T getFromClient() {
        if (type == String.class) return (T) client.get(url);
        return parser.fromJson(client.get(url), type);
    }

    private void postToClient(T o) {
        client.post(url, parser.toJson(o));
    }
}
