package pt.isel.ls.Json.Source;

public class JsonSupplier {

    public static JsonNestedElement jsonObject(String name) { return new JsonObject(name); }
    public static JsonNestedElement jsonArray(String name) { return new JsonArray(name); }
    public static JsonElement jsonInteger(String name,int value) { return new JsonInt(name, value); }
    public static JsonElement jsonString(String name, String value) { return new JsonString(name, value); }
    public static JsonElement jsonBool(String name, boolean value) { return new JsonBolean(name, value); }
}
