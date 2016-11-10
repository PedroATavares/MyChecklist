package pt.isel.ls.Json.Parcers;

import pt.isel.ls.Json.Source.JsonElement;
import pt.isel.ls.Json.Source.JsonNestedElement;
import pt.isel.ls.Model.TemplateTask;
import static pt.isel.ls.Json.Source.JsonSupplier.*;
import static pt.isel.ls.Json.Source.JsonSupplier.jsonString;

public class TemplateTaskJsonParser implements JsonParcer<TemplateTask>{
    @Override
    public JsonElement supply(TemplateTask source) {

        return makeEntitie(source);
    }

    public static JsonNestedElement makeEntitie(TemplateTask source) {

        return jsonObject().whith(
                jsonArray("class").whith(
                        jsonString("templateTask") ),
                jsonObject("properties").whith(
                        jsonInteger("id", source.id),
                        jsonString("name",source.name),
                        jsonString("description",source.description),
                        jsonInteger("templateId", source.templateId)
                )
        );
    }
}
