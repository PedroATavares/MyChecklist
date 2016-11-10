package pt.isel.ls.Json.Parcers;

import pt.isel.ls.Json.Source.JsonElement;
import pt.isel.ls.Json.Source.JsonNestedElement;
import pt.isel.ls.Model.Task;

import java.util.List;

import static pt.isel.ls.Json.Parcers.TaskJsonParser.makeEntitie;
import static pt.isel.ls.Json.Source.JsonSupplier.*;
import static pt.isel.ls.Json.Source.JsonSupplier.jsonArray;

public class TaskListJonParcer implements JsonParcer<List<Task>> {

    @Override
    public JsonElement supply(List<Task> source) {
        JsonNestedElement root = jsonObject().whith(
                jsonArray("class").whith(
                        jsonString("task"),
                        jsonString("collection")
                ),
                jsonObject("properties").whith(
                        jsonInteger("count", source.size())
                )
        );

        JsonNestedElement entities=jsonArray("entities");
        root.whith(entities);

        for (Task t : source) {
            entities.whith(makeEntitie(t));
        }
        return root;
    }

}
