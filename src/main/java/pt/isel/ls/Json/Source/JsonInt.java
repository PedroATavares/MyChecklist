package pt.isel.ls.Json.Source;

public class JsonInt implements JsonElement {
    private String name;
    private final int value;

    public JsonInt(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public JsonInt(int value) {
        this.value = value;
    }

    @Override
    public String toStr() {
        if(name ==null) return value + "\n";
        return '\"' + name + "\": " + value + '\n';
    }
}
