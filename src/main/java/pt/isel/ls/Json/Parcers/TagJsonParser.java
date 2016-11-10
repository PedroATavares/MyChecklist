package pt.isel.ls.Json.Parcers;

import pt.isel.ls.Json.Source.JsonElement;
import pt.isel.ls.Json.Source.JsonNestedElement;
import pt.isel.ls.Model.CheckList;
import pt.isel.ls.Model.Tag;
import static pt.isel.ls.Json.Source.JsonSupplier.*;
import static pt.isel.ls.Json.Source.JsonSupplier.jsonString;

public class TagJsonParser implements JsonParcer<Tag> {
    @Override
    public JsonElement supply(Tag source) {

        JsonNestedElement root = makeEntitie(source);
        JsonNestedElement entities=jsonArray("entities");
        root.whith(entities);

        for (CheckList t : source.checkLists) {
            entities.whith(ChecklistJsonParser.makeEntitie(t));
        }
        return root;
    }

    public static JsonNestedElement makeEntitie(Tag source) {
        return jsonObject().whith(
                jsonArray("class").whith(
                        jsonString("tag")
                ),
                jsonObject("properties").whith(
                        jsonInteger("id", source.gid),
                        jsonString("name",source.name),
                        jsonString("color",source.color)
                )
        );
    }
}
