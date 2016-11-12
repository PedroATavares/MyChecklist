package pt.isel.ls.Json.Source;

public class JsonString implements JsonElement{
    private String name;
    private final String value;

    public JsonString(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public JsonString(String value) {
        this.value = value;
    }

    @Override
    public String toStr() {
        if(name == null) return '\"'+ value + "\" \n";
        return '\"' + name + "\": " + '\"' + value + '\"'+ '\n';
    }
}
