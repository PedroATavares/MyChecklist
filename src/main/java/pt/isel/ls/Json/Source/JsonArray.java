package pt.isel.ls.Json.Source;

import java.util.ArrayList;
import java.util.List;

public class JsonArray implements JsonNestedElement {
    private String name;
    List<JsonElement> fields;

    public JsonArray(String name) {
        this.name = name;
        fields= new ArrayList<>();
    }

    public JsonArray() {
        fields= new ArrayList<>();
    }

    @Override
    public JsonNestedElement whith(JsonElement... elements) {
        for(JsonElement e: elements){
            fields.add(e);
        }
        return this;
    }

    @Override
    public String toJson() {
        StringBuilder sb = new StringBuilder();
        if(name!=null)
            sb.append('\"' + name + "\":\n");
        sb.append( "[ \n");
        for (JsonElement e :fields) {
            sb.append(e.toJson());
            sb.append(", \n");
        }
        if(sb.lastIndexOf(",")!=-1)
            sb.delete(sb.lastIndexOf(","),sb.lastIndexOf("\n"));
        sb.append("]\n");
        return sb.toString();
    }
}
