package pt.isel.ls;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.json.*;
import pt.isel.ls.Json.Parcers.*;
import pt.isel.ls.Json.Source.JsonElement;
import pt.isel.ls.Model.*;
import java.util.ArrayList;

public class JsonFormatTests {

    private static Template temp;
    private static ArrayList<TemplateTask> tempTkList;
    private static TemplateTask tempTk1;
    private static TemplateTask tempTk2;
    private static ArrayList<Task> tkList;
    private static Task tk1;
    private static Task tk2;
    private static Tag tg;
    private static ArrayList<CheckList> ckList;
    private static CheckList ckl1;
    private static  CheckList ckl2;


    @BeforeClass
    public static  void initialize() {

        temp = new Template(10, "tempTest", "ver se o Json bomba");

        tempTk1 = new TemplateTask(1, 1, "templateTaskTest1", "ver se o Json bomba");
        tempTk2 = new TemplateTask(2, 2, "templateTaskTest2", "ver se o Json bomba");
        tempTkList = new ArrayList<>();
        tempTkList.add(tempTk1);
        tempTkList.add(tempTk2);


        tk1 = new Task(1, 1, "taskTest1", "ver se o Json bomba", "data doida", false);
        tk2 = new Task(2, 2, "taskTest2", "ver se o Json bomba", "data doida", false);
        tkList = new ArrayList<>();
        tkList.add(tk1);
        tkList.add(tk2);

        ckl1 = new CheckList(1, "checkListTest1", "ver se o Json bomba", "data doida", false, 1, tkList);
        ckl2 = new CheckList(2, "checkListTest2", "ver se o Json bomba", "data doida", false, 1, tkList);
        ckList = new ArrayList<>();
        ckList.add(ckl1);
        ckList.add(ckl2);

        tg = new Tag(1, "tagTest", "vermelhao", ckList);
    }

    @Test
    public void test_TemplateJsonFormat() throws JSONException {

        JsonElement tempInJson =  new TemplateJsonFormat().supply(temp);
        JSONObject obj = new JSONObject( tempInJson.toStr() );

        String type = obj.getJSONArray("class").getString(0);
        Assert.assertEquals(type, "template");
        int id = obj.getJSONObject("properties").getInt("id");
        Assert.assertEquals(id, 10);
        String name = obj.getJSONObject("properties").getString("name");
        Assert.assertEquals(name, "tempTest");
        String descript = obj.getJSONObject("properties").getString("description");
        Assert.assertEquals(descript, "ver se o Json bomba" );

    }

    @Test
    public void test_TemplateTask() throws JSONException{

        JsonElement temptkInJson = new TemplateTaskJsonFormat().supply(tempTk1);
        JSONObject obj = new JSONObject(temptkInJson.toStr());

        String type = obj.getJSONArray("class").getString(0);
        Assert.assertEquals(type, "templateTask");
        int id = obj.getJSONObject("properties").getInt("id");
        Assert.assertEquals(id, 1);
        String name = obj.getJSONObject("properties").getString("name");
        Assert.assertEquals(name, "templateTaskTest1");
        String descript = obj.getJSONObject("properties").getString("description");
        Assert.assertEquals(descript, "ver se o Json bomba" );
        int tempId = obj.getJSONObject("properties").getInt("templateId");
        Assert.assertEquals(tempId, 1);
    }

    @Test
    public void test_TaskJsonFormat() throws JSONException{

        JsonElement tkInJson = new TaskJsonFormat().supply(tk1);
        JSONObject obj = new JSONObject(tkInJson.toStr());

        String type = obj.getJSONArray("class").getString(0);
        Assert.assertEquals(type, "task");
        int id = obj.getJSONObject("properties").getInt("id");
        Assert.assertEquals(id, 1);
        int idCkl = obj.getJSONObject("properties").getInt("checklistId");
        Assert.assertEquals(idCkl, 1);
        String name = obj.getJSONObject("properties").getString("name");
        Assert.assertEquals(name, "taskTest1");
        String descript = obj.getJSONObject("properties").getString("description");
        Assert.assertEquals(descript, "ver se o Json bomba" );
        String dueDate = obj.getJSONObject("properties").getString("dueDate");
        Assert.assertEquals(dueDate, "data doida");
        Boolean isClosed = obj.getJSONObject("properties").getBoolean("isClosed");
        Assert.assertFalse(isClosed);
    }

    @Test
    public void test_TagJsonFormat() throws JSONException{

        JsonElement tgInJson = new TagJsonFormat().supply(tg);
        JSONObject obj = new JSONObject(tgInJson.toStr());

        String type = obj.getJSONArray("class").getString(0);
        Assert.assertEquals(type, "tag");
        int id = obj.getJSONObject("properties").getInt("id");
        Assert.assertEquals(id, 1);
        String name = obj.getJSONObject("properties").getString("name");
        Assert.assertEquals(name, "tagTest");
        String color = obj.getJSONObject("properties").getString("color");
        Assert.assertEquals(color, "vermelhao");
    }

    @Test
    public void test_CheckListJsonFormat() throws JSONException{

        JsonElement checkListInJson = new CheckListJsonFormat().supply(ckl1);
        JSONObject obj = new JSONObject(checkListInJson.toStr());

        String type = obj.getJSONArray("class").getString(0);
        Assert.assertEquals(type, "checklist");
        int id = obj.getJSONObject("properties").getInt("id");
        Assert.assertEquals(id, 1);
        String name = obj.getJSONObject("properties").getString("name");
        Assert.assertEquals(name, "checkListTest1");
        String descript = obj.getJSONObject("properties").getString("description");
        Assert.assertEquals(descript, "ver se o Json bomba" );
        Boolean isClosed = obj.getJSONObject("properties").getBoolean("isClosed");
        Assert.assertFalse(isClosed);
        String dueDate = obj.getJSONObject("properties").getString("dueDate");
        Assert.assertEquals(dueDate, "data doida");
    }
}
