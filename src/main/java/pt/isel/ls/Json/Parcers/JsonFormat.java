package pt.isel.ls.Json.Parcers;

import pt.isel.ls.Json.Source.JsonElement;

public interface JsonFormat<E>{

    public JsonElement supply(E source);

}
