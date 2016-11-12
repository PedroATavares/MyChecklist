package pt.isel.ls.Json.Parcers;

import pt.isel.ls.Json.Source.JsonElement;
import pt.isel.ls.Json.Source.JsonNestedElement;
import pt.isel.ls.Model.Task;
import static pt.isel.ls.Json.Source.JsonSupplier.*;
import static pt.isel.ls.Json.Source.JsonSupplier.jsonBool;
import static pt.isel.ls.Json.Source.JsonSupplier.jsonString;

public class TaskJsonFormat implements JsonFormat<Task> {
    @Override
    public JsonElement supply(Task source) {
        return makeEntitie(source);
    }

    public static JsonNestedElement makeEntitie(Task source) {

        return jsonObject().whith(
                jsonArray("class").whith(
                        jsonString("task")
                ),
                jsonObject("properties").whith(
                        jsonInteger("id",source.id),
                        jsonInteger("checklistId",source.checklistId),
                        jsonString("name", source.name),
                        jsonString("description", source.description),
                        jsonBool("isClosed", source.isClosed),
                        jsonString("dueDate", source.dueDate)
                )
        );
    }
}
