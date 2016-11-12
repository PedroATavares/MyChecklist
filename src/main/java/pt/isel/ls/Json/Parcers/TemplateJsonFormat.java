package pt.isel.ls.Json.Parcers;

import pt.isel.ls.Json.Source.JsonElement;
import pt.isel.ls.Json.Source.JsonNestedElement;
import pt.isel.ls.Model.Template;
import static pt.isel.ls.Json.Source.JsonSupplier.*;
import static pt.isel.ls.Json.Source.JsonSupplier.jsonString;

public class TemplateJsonFormat implements JsonFormat<Template> {

    @Override
    public JsonElement supply(Template source) {

        return makeEntitie(source);
    }

    public static JsonNestedElement makeEntitie(Template source) {
        return jsonObject().whith(
                jsonArray("class").whith(
                        jsonString("template") ),
                jsonObject("properties").whith(
                        jsonInteger("id", source.id),
                        jsonString("name",source.name),
                        jsonString("description",source.description)
                )
        );
    }
}