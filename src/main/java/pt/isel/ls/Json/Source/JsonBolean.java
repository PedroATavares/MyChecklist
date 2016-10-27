package pt.isel.ls.Json.Source;

public class JsonBolean implements JsonElement{
    private final String name;
    private final boolean value;

    public JsonBolean(String name, boolean value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toJson() {
        return '\"' + name + "\": " + value + '\n';
    }
}
