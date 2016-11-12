package pt.isel.ls.Json.Parcers;

import pt.isel.ls.Json.Source.JsonElement;
import pt.isel.ls.Json.Source.JsonNestedElement;
import pt.isel.ls.Model.TemplateTask;
import java.util.List;
import static pt.isel.ls.Json.Parcers.TemplateTaskJsonFormat.makeEntitie;
import static pt.isel.ls.Json.Source.JsonSupplier.*;
import static pt.isel.ls.Json.Source.JsonSupplier.jsonArray;

public class TemplateTaskListJsonFormat implements JsonFormat<List<TemplateTask>> {
    @Override
    public JsonElement supply(List<TemplateTask> source) {

        JsonNestedElement root = jsonObject().whith(
                jsonArray("class").whith(
                        jsonString("templateTask"),
                        jsonString("collection")
                ),
                jsonObject("properties").whith(
                        jsonInteger("count", source.size())
                )
        );
        JsonNestedElement entities=jsonArray("entities");
        root.whith(entities);

        for ( TemplateTask c : source) {
            entities.whith(makeEntitie(c));
        }

        return root;
    }
}
