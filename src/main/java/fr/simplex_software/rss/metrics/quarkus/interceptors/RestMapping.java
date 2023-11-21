package fr.simplex_software.rss.metrics.quarkus.interceptors;

public record RestMapping (
        String type,
        String path
        ){
    @Override
    public String toString() {
        return String.format("%s %s", type, path);
    }
}
