package pt.isel.ls;

import org.junit.Test;
import pt.isel.ls.Html.Parcers.CheckListParcer;
import pt.isel.ls.Html.Parcers.HtmlParcer;
import pt.isel.ls.Model.CheckList;
import pt.isel.ls.Model.Task;

import java.util.ArrayList;

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
