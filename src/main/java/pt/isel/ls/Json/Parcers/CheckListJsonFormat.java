package pt.isel.ls.Json.Parcers;

import pt.isel.ls.Json.Source.JsonElement;
import pt.isel.ls.Json.Source.JsonNestedElement;
import pt.isel.ls.Model.CheckList;
import pt.isel.ls.Model.Task;
import static pt.isel.ls.Json.Source.JsonSupplier.*;

public class CheckListJsonFormat implements JsonFormat<CheckList> {
        @Override
        public JsonElement supply(CheckList source) {
            JsonNestedElement root = makeEntitie(source);
            JsonNestedElement entities=jsonArray("entities");
            root.whith(entities);

            for (Task t : source.tasks) {
                entities.whith(TaskJsonFormat.makeEntitie(t));
            }
            return root;
        }
    public static JsonNestedElement makeEntitie(CheckList source) {
        return jsonObject().whith(
                jsonArray("class").whith(
                        jsonString("checklist")
                ),
                jsonObject("properties").whith(
                        jsonInteger("id", source.id),
                        jsonString("name",source.name),
                        jsonString("description",source.description),
                        jsonBool("isClosed", source.isClosed),
                        jsonString("dueDate", source.dueDate)
                )
        );
    }
}
