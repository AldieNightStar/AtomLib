# Atom Lib
Atomic variables, getters/setters, Even http and File->Json :)<br>
`Atom` is an `interface` with `static` methods, which creates Atoms, so don't be scared :)

## Operate operation
```java
public class X {
    public static void main(String ... args) {
        // Simple Operate
        Atom<Integer> a = Atom.of(32);
        for (int i = 0; i < 10; i++) {
            a.operate(n -> n * 2);
        }
        // Get and set
        a.set(32);
        int number = a.get();
        a.getThenSet(33);
        
        // HttpBased Atom
        Atom<String> a = Atom.ofHttp("", HTTP_CLIENT, JSON_PARSER, String.class);
        a.set("Hello!");
        a.get();
        a.getThenSet("I am programmer!");
        
        // FileBased Atom
        Atom<Point> a = Atom.ofJsonFile(new File("~/my.json"), JSON_PARSER, Point.class);
        a.set(new Point(32, 12));
        a.get();
        a.operate(p -> p.add(new Point(1, 0)));
    }
}
```

## It's our universal Getter & Setter !! ^^