package pt.isel.ls.Json.Source;

public class JsonString implements JsonElement{
    private final String name;
    private final String value;

    public JsonString(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toJson() {
        return '\"' + name + "\": " + '\"' + value + '\"'+ '\n';
    }
}
