package pt.isel.ls.Json.Source;

public class JsonBolean implements JsonElement{
    private String name;
    private final boolean value;

    public JsonBolean(String name, boolean value) {
        this.name = name;
        this.value = value;
    }

    public JsonBolean(boolean value) {
        this.value = value;
    }

    @Override
    public String toJson() {
        if(name== null) return  value + "\n";
        return '\"' + name + "\": " + value + '\n';
    }
}
