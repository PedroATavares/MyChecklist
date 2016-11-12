package pt.isel.ls.Json.Parcers;

import pt.isel.ls.Json.Source.JsonElement;
import pt.isel.ls.Json.Source.JsonNestedElement;
import pt.isel.ls.Model.CheckList;

import java.util.List;

import static pt.isel.ls.Json.Parcers.CheckListJsonFormat.makeEntitie;
import static pt.isel.ls.Json.Source.JsonSupplier.*;


public class ChecklistListJsonFormat implements JsonFormat<List<CheckList>> {
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


}
