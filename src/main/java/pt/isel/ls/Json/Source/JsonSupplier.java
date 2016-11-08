package pt.isel.ls.Json.Source;

public class JsonSupplier {

    public static JsonNestedElement jsonObject(String name) { return new JsonObject(name); }
    public static JsonNestedElement jsonObject() { return new JsonObject(); }
    public static JsonNestedElement jsonArray(String name) { return new JsonArray(name); }
    public static JsonNestedElement jsonArray() { return new JsonArray(); }
    public static JsonElement jsonInteger(String name,int value) { return new JsonInt(name, value); }
    public static JsonElement jsonInteger(int value) { return new JsonInt(value); }
    public static JsonElement jsonString(String name, String value) { return new JsonString(name, value); }
    public static JsonElement jsonString(String value) { return new JsonString(value); }
    public static JsonElement jsonBool(String name, boolean value) { return new JsonBolean(name, value); }
    public static JsonElement jsonBool( boolean value) { return new JsonBolean( value); }
}
