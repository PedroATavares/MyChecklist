package pt.isel.ls.Json.Parcers;

import pt.isel.ls.Json.Source.JsonElement;

public interface JsonParcer <E>{

    public JsonElement supply(E source);

}
