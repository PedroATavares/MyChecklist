package pt.isel.ls.Json.Parcers;

import pt.isel.ls.Json.Source.JsonElement;
import pt.isel.ls.Json.Source.JsonNestedElement;
import pt.isel.ls.Model.Template;
import java.util.List;
import static pt.isel.ls.Json.Parcers.TemplateJsonFormat.makeEntitie;
import static pt.isel.ls.Json.Source.JsonSupplier.*;

public class TemplateListJsonFormat implements JsonFormat<List<Template>> {
    @Override
    public JsonElement supply(List<Template> source) {

        JsonNestedElement root = jsonObject().whith(
                jsonArray("class").whith(
                        jsonString("template"),
                        jsonString("collection")
                ),
                jsonObject("properties").whith(
                        jsonInteger("count", source.size())
                )
        );
        JsonNestedElement entities=jsonArray("entities");
        root.whith(entities);

        for (Template c : source) {
            entities.whith(makeEntitie(c));
        }
        return root;
    }
}
