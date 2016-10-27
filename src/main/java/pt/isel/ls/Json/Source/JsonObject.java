package pt.isel.ls.Json.Source;

import java.util.ArrayList;
import java.util.List;

public class JsonObject implements JsonNestedElement{
    private final String name;
    List<JsonElement> fields;


    public JsonObject(String name) {
        this.name=name;
        fields=new ArrayList<>();
    }

    @Override
    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ \n");
        for (JsonElement e :fields)
            sb.append(e.toJson());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public JsonNestedElement whith(JsonElement... elements) {
        for(JsonElement e: elements){
            fields.add(e);
        }
        return this;
    }
}
