package pt.isel.ls.Json.Source;

public class JsonInt implements JsonElement {
    private final String name;
    private final int value;

    public JsonInt(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toJson() {
        return '\"' + name + "\": " + value + '\n';
    }
}
