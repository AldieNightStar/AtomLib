package haxidenti.atoms.impl;

import haxidenti.atoms.Atom;
import haxidenti.atoms.interfaces.Getter;
import haxidenti.atoms.interfaces.Setter;

import java.util.function.Function;

public class FunctionBasedAtom<T> implements Atom<T> {
    private Setter<T> setterFunc;
    private Getter<T> getterFunc;

    public FunctionBasedAtom(Getter<T> getterFunc, Setter<T> setterFunc) {
        this.getterFunc = getterFunc;
        this.setterFunc = setterFunc;
    }

    @Override
    public void set(T o) {
        setterFunc.set(o);
    }

    @Override
    public T get() {
        return getterFunc.get();
    }

    @Override
    public T getThenSet(T o) {
        T previous = getterFunc.get();
        setterFunc.set(o);
        return previous;
    }

    @Override
    public void operate(Function<T, T> func) {
        setterFunc.set(func.apply(getterFunc.get()));
    }
}
