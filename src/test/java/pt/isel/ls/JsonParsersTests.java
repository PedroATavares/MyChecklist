package pt.isel.ls;

import org.junit.Test;

import static pt.isel.ls.Json.Source.JsonSupplier.*;

public class JsonParsersTests {

    @Test
    public void test_object_parce(){
        System.out.println(jsonObject("").whith(
                jsonBool("isClosed",false),
                jsonInteger("id", 4),
                jsonArray("names").whith(
                        jsonString("str1","nome 1"),
                        jsonString("str2","nome 2"),
                        jsonString("str3","nome 3")
                )
        ).toJson());

    }
}
