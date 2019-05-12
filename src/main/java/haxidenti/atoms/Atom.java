package haxidenti.atoms;

import haxidenti.atoms.impl.JsonFileBasedAtom;
import haxidenti.atoms.impl.HttpBasedAtom;
import haxidenti.atoms.interfaces.Getter;
import haxidenti.atoms.interfaces.HttpClient;
import haxidenti.atoms.interfaces.JsonParser;
import haxidenti.atoms.interfaces.Setter;
import haxidenti.atoms.impl.FieldBasedAtom;
import haxidenti.atoms.impl.FunctionBasedAtom;

import java.io.File;
import java.util.function.Function;

public interface Atom<T> {
    void set(T o);
    T get();
    T getThenSet(T o);
    void operate(Function<T, T> func);

    static <T> Atom<T> of(T o) {
        return new FieldBasedAtom<>(o);
    }
    static <T> Atom<T> of(Getter<T> getter, Setter<T> setter) {
        return new FunctionBasedAtom<>(getter, setter);
    }
    static <T> Atom<T> ofHttp(String url, HttpClient client, JsonParser parser, Class<T> type) {
        return new HttpBasedAtom<>(url, parser, client, type);
    }
    static <T> Atom<T> ofJsonFile(File file, JsonParser parser, Class<T> type) {
        return new JsonFileBasedAtom<>(file, parser, type);
    }
}
