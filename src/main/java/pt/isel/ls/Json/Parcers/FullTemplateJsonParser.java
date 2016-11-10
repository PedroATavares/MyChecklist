package pt.isel.ls.Json.Parcers;

import pt.isel.ls.Json.Source.JsonElement;
import pt.isel.ls.Json.Source.JsonNestedElement;
import pt.isel.ls.Model.CheckList;
import pt.isel.ls.Model.FullTemplate;
import pt.isel.ls.Model.TemplateTask;
import static pt.isel.ls.Json.Source.JsonSupplier.*;

public class FullTemplateJsonParser implements JsonParcer<FullTemplate> {
    @Override
    public JsonElement supply(FullTemplate source) {
        JsonNestedElement root = TemplateJsonParser.makeEntitie(source.temp);
        JsonNestedElement entities=jsonArray("entities");
        root.whith(entities);

        for (CheckList t : source.listCkL) {
            entities.whith(ChecklistJsonParser.makeEntitie(t));
        }
        for (TemplateTask t : source.listTsk) {
            entities.whith(TemplateTaskJsonParser.makeEntitie(t));
        }
        return root;
    }

}