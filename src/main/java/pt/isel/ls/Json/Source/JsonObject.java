package pt.isel.ls.Json.Source;

import java.util.ArrayList;
import java.util.List;

public class JsonObject implements JsonNestedElement{
    private String name;
    List<JsonElement> fields;


    public JsonObject(String name) {
        this.name=name;
        fields=new ArrayList<>();
    }

    public JsonObject() {
        fields=new ArrayList<>();
    }

    @Override
    public String toStr() {
        StringBuilder sb = new StringBuilder();
        if(name != null){
            sb.append('\"' + name + "\":");
        }
        sb.append("{ \n");
        for (JsonElement e :fields)
            sb.append(e.toStr() + ", \n");
        if(sb.lastIndexOf(",")!=-1)
            sb.delete(sb.lastIndexOf(","),sb.length()-1);

        sb.append("} \n");
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
