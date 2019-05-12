package haxidenti.atoms.impl;

import haxidenti.atoms.Atom;
import haxidenti.atoms.interfaces.JsonParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.function.Function;

public class JsonFileBasedAtom<T> implements Atom<T> {

    private File file;
    private JsonParser parser;
    private Class<T> type;

    public JsonFileBasedAtom(File file, JsonParser parser, Class<T> type) {
        this.file = file;
        this.parser = parser;
        this.type = type;
    }

    @Override
    public void set(T o) {
        writeFile(o);
    }

    @Override
    public T get() {
        return readFile();
    }

    @Override
    public T getThenSet(T o) {
        T previous = readFile();
        writeFile(o);
        return previous;
    }

    @Override
    public void operate(Function<T, T> func) {
        writeFile(func.apply(readFile()));
    }

    private T readFile() {
        try {
            byte[] data = Files.readAllBytes(file.toPath());
            String json = new String(data, StandardCharsets.UTF_8);
            return parser.fromJson(json, type);
        } catch (IOException e) {
            return null;
        }
    }

    private void writeFile(T o) {
        try {
            String json = parser.toJson(o);
            Files.write(file.toPath(), json.getBytes());
        } catch (Exception e) {}
    }
}
