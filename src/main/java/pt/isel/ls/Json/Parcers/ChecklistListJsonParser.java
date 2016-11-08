package pt.isel.ls.Json.Parcers;

import pt.isel.ls.Json.Source.JsonElement;
import pt.isel.ls.Json.Source.JsonNestedElement;
import pt.isel.ls.Model.CheckList;

import java.util.List;

import static pt.isel.ls.Json.Source.JsonSupplier.*;


public class ChecklistListJsonParser implements JsonParcer<List<CheckList>> {
    @Override
    public JsonElement supply(List<CheckList> source) {
        JsonNestedElement root = jsonObject().whith(
                jsonArray("class").whith(
                        jsonString("checklist"),
                        jsonString("collection")
                ),
                jsonObject("properties").whith(
                        jsonInteger("count", source.size())
                )
        );

        JsonNestedElement entities=jsonArray("entities");
        root.whith(entities);

        for (CheckList c : source) {
            entities.whith(makeEntitie(c));
        }
        return root;
    }

    public static JsonNestedElement makeEntitie(CheckList source) {
        return jsonObject().whith(
                jsonArray("class").whith(
                        jsonString("checklist")
                ),
                jsonObject("properties").whith(
                        jsonString("name",source.name),
                        jsonString("description",source.description),
                        jsonBool("isClosed", source.isClosed),
                        jsonString("dueDate", source.dueDate)
                )
        );
    }
}
