package haxidenti.atoms.impl;

import haxidenti.atoms.Atom;

import java.util.function.Function;

public class FieldBasedAtom<T> implements Atom<T> {
    private volatile T field;

    public FieldBasedAtom(T o) {
        field = o;
    }

    @Override
    public void set(T o) {
        field = o;
    }

    @Override
    public T get() {
        return field;
    }

    @Override
    public T getThenSet(T o) {
        T previous = field;
        field = o;
        return previous;
    }

    @Override
    public void operate(Function<T, T> func) {
        T oldField = field;
        field = func.apply(oldField);
    }
}
