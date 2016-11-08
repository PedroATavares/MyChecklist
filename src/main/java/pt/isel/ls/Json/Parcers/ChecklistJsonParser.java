package pt.isel.ls.Json.Parcers;

import pt.isel.ls.Json.Source.JsonElement;
import pt.isel.ls.Json.Source.JsonNestedElement;
import pt.isel.ls.Model.CheckList;
import pt.isel.ls.Model.Task;

import static pt.isel.ls.Json.Parcers.ChecklistListJsonParser.makeEntitie;
import static pt.isel.ls.Json.Parcers.TaskListJonParcer.makeEntitie;
import static pt.isel.ls.Json.Source.JsonSupplier.*;

public class ChecklistJsonParser implements JsonParcer<CheckList> {
    @Override
    public JsonElement supply(CheckList source) {
        JsonNestedElement root = ChecklistListJsonParser.makeEntitie(source);
        JsonNestedElement entities=jsonArray("entities");
        root.whith(entities);

        for (Task t : source.tasks) {
            entities.whith(TaskListJonParcer.makeEntitie(t));
        }
        return root;
    }
}
