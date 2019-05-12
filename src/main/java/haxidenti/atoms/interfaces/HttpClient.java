package haxidenti.atoms.interfaces;

public interface HttpClient {
    String get(String url);
    String post(String url, String body);

    // Standard HttpClients
}
