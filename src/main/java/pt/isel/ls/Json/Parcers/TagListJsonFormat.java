package pt.isel.ls.Json.Parcers;

import pt.isel.ls.Json.Source.JsonElement;
import pt.isel.ls.Json.Source.JsonNestedElement;
import pt.isel.ls.Model.Tag;
import java.util.List;
import static pt.isel.ls.Json.Parcers.TagJsonFormat.makeEntitie;
import static pt.isel.ls.Json.Source.JsonSupplier.*;
import static pt.isel.ls.Json.Source.JsonSupplier.jsonArray;

public class TagListJsonFormat implements JsonFormat<List<Tag>> {
    @Override
    public JsonElement supply(List<Tag> source) {

        JsonNestedElement root = jsonObject().whith(
                jsonArray("class").whith(
                        jsonString("tag"),
                        jsonString("collection")
                ),
                jsonObject("properties").whith(
                        jsonInteger("count", source.size())
                )
        );

        JsonNestedElement entities=jsonArray("entities");
        root.whith(entities);

        for ( Tag c : source) {
            entities.whith(makeEntitie(c));
        }
        return root;
    }
}
