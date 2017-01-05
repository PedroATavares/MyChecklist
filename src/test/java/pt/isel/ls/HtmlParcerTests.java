package pt.isel.ls;

import org.junit.Before;
import org.junit.Test;

import pt.isel.ls.Html.Parcers.*;
import pt.isel.ls.Model.*;
import pt.isel.ls.Model.CheckList;
import pt.isel.ls.Model.Task;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HtmlParcerTests {
    ArrayList<Task> tasks;
    CheckList cl1;
    ArrayList<CheckList> cll;
    ArrayList<Tag> tags;
    @Before
    public void initialize(){
        tasks = new ArrayList<>();
        tasks.add(new Task(10, 1,"Task 1","Descricao 1","2015-10-10",false));
        tasks.add(new Task(10, 2,"Task 2","Descricao 2","2015-5-12",true));
        tasks.add(new Task(10, 3,"Task 3","Descricao 3","2016-12-11",false));
        cl1= new CheckList(10,"Checklist 1","Descricao 1","2015-10-10",true,null, tasks);
        cll=new ArrayList<>();
        cll.add(cl1);
        cll.add(cl1);
        tags = new ArrayList<>();
        tags.add(new Tag(1,"Tag 1","A",cll));
        tags.add(new Tag(2,"Tag 2","B",cll));
        tags.add(new Tag(3,"Tag 3","C",cll));
    }
    @Test
    public void test_checklist_parce(){
        CheckList cl= new CheckList(10,"Checklist 1","Descricao 1","2015-10-10",true,2,null);
        HtmlParser<CheckList> clParcer = new ChecklistParser();
                assertEquals("<html  ><h2  >Checklist</h2><p  >Id: 10" +
                        "</p><p  >Name: Checklist 1</p><p  >Description: Descricao 1</p><p  >" +
                        "Due date: 2015-10-10</p><p  ><a  href= \"/templates/2\", >Template Id:2</a>" +
                        "</p><p  >Is Closed: true</p><h3  >Tasks</h3><p  >No Tasks To Show</p><form  " +
                        "action= \"/checklists/10/tasks\", method= \"post\", ><br  >Name:</br><input  " +
                        "type= \"text\", name= \"name\", ></input><br  >Description:</br><input  " +
                        "type= \"text\", name= \"description\", ></input><br  >Due Date in format " +
                        "yyyy-mm-dd:</br><input  type= \"text\", name= \"dueDate\", ></input><input  " +
                        "type= \"hidden\", name= \"reload\", value= \"/checklists/10\", ></input><input  " +
                        "type= \"submit\", value= \"Submit\", ></input></form><h3  >Tags</h3><p  >No Tags To Show.</p>" +
                        "<form  action= \"/checklists/10/tags\", method= \"post\", ><br  >Gid:</br><input  " +
                        "type= \"text\", name= \"gid\", ></input><input  type= \"hidden\", name= \"reload\", " +
                        "value= \"/checklists/10\", ></input><input  type= \"submit\", value= \"Submit\", >" +
                        "</input></form><h3  ><a  href= \"./\", >Back</a></h3><h3  ><a  href= \"/\", >" +
                        "Home</a></h3></html>",clParcer.supply(cl));
    }


    @Test
    public void test_checklist_List_parce(){
        CheckList cl= new CheckList(10,"Checklist 1","Descricao 1","2015-10-10",true,2,tasks);
        cl.setTagOnList(tags.get(0));
        HtmlParser<CheckList> ex1 = new ChecklistParser();
        assertEquals("<html  ><h2  >Checklist</h2><p  >" +
                "Id: 10</p><p  >Name: Checklist 1</p><p  >" +
                "Description: Descricao 1</p><p  >Due date: 2015-10-10</p><p  >" +
                "<a  href= \"/templates/2\", >Template Id:2</a></p><p  >Is Closed: true</p><h3  >" +
                "Tasks</h3><table  style= \"width:50%\", border= \"1\", ><tr  ><td  >Id</td><td  >" +
                "CheckList Id</td><td  >Name</td><td  >Descricao</td><td  >Due Date</td><td  >" +
                "Is Closed</td><td  >Change</td></tr><tr  ><td  >1</td><td  >10</td><td  >" +
                "Task 1</td><td  >Descricao 1</td><td  >2015-10-10</td><td  >false</td><td  ><form  " +
                "action= \"/checklists/10/tasks/1\", method= \"post\", ><input  type= \"submit\", " +
                "value= \"Submit\", ></input><input  type= \"hidden\", name= \"isClosed\", value= \"true\", ></input>" +
                "<input  type= \"hidden\", name= \"reload\", value= \"/checklists/10\", >" +
                "</input></form></td></tr><tr  ><td  >2</td><td  >10</td><td  >Task 2</td><td  >" +
                "Descricao 2</td><td  >2015-5-12</td><td  >true</td><td  ><form  action= \"/checklists/10/tasks/2\", " +
                "method= \"post\", ><input  type= \"submit\", value= \"Submit\", ></input><input  type= \"hidden\", " +
                "name= \"isClosed\", value= \"false\", ></input><input  type= \"hidden\", name= \"reload\", value= \"" +
                "/checklists/10\", ></input></form></td></tr><tr  ><td  >3</td><td  >10</td><td  >Task 3</td><td  >" +
                "Descricao 3</td><td  >2016-12-11</td><td  >false</td><td  ><form  action= \"/checklists/10/tasks/3\"," +
                " method= \"post\", ><input  type= \"submit\", value= \"Submit\", ></input><input  type= \"hidden\", " +
                "name= \"isClosed\", value= \"true\", ></input><input  type= \"hidden\", name= \"reload\", value= \"/" +
                "checklists/10\", ></input></form></td></tr></table><form  action= \"/checklists/10/tasks\", method= \"post\", >" +
                "<br  >Name:</br><input  type= \"text\", name= \"name\", ></input><br  >Description:</br><input  type= \"text\"," +
                " name= \"description\", ></input><br  >Due Date in format yyyy-mm-dd:</br><input  type= \"text\", name= \"dueDate\", >" +
                "</input><input  type= \"hidden\", name= \"reload\", value= \"/checklists/10\", ></input><input  type= \"submit\"," +
                " value= \"Submit\", ></input></form><h3  >Tags</h3><table  style= \"width:50%\", border= \"1\", ><tr  ><th  >Id</th><th  >" +
                "Name</th><th  >Color</th><th  >Link</th><th  >Checklist of Tag</th></tr><tr  ><td  >1</td><td  >Tag 1</td><td  >A</td><td  >" +
                "<a  href= \"/tags/1\", >Link</a></td><td  ><a  href= \"/tags/1/checklists\", >Link</a></td></tr></table><form  action= \"" +
                "/checklists/10/tags\", method= \"post\", ><br  >Gid:</br><input  type= \"text\", name= \"gid\", ></input><input  type= \"hidden\", " +
                "name= \"reload\", value= \"/checklists/10\", ></input><input  type= \"submit\", value= \"Submit\", ></input></form><h3  >" +
                "<a  href= \"./\", >Back</a></h3><h3  ><a  href= \"/\", >Home</a></h3></html>",ex1.supply(cl).toString());
    }


    @Test
    public void test_tag_parce(){
        Tag t= new Tag(1,"Frutas","Black",cll);
        HtmlParser<Tag> tagParcer = new TagParser();
        assertEquals("<html  ><body  ><h3  >TAG</h3><p  >Id: 1</p><p  >Name: Frutas</p><p  >" +
                "Color: Black</p><h3  >Checklists: </h3><table  style= \"width:50%\", border= \"1\", >" +
                "<tr  ><th  >Id</th><th  >Template Id</th><th  >Name</th><th  >Description</th><th  >" +
                "Due Date</th><th  >Is Closed</th><th  >Links</th></tr><tr  ><td  >10</td><td  >None</td>" +
                "<td  >Checklist 1</td><td  >Descricao 1</td><td  >2015-10-10</td><td  >true</td><td  >" +
                "<a  href= \"/checklists/10\", >Link</a></td></tr><tr  ><td  >10</td><td  >None</td><td  >" +
                "Checklist 1</td><td  >Descricao 1</td><td  >2015-10-10</td><td  >true</td><td  ><a  href= \"/checklists/10\", >" +
                "Link</a></td></tr></table><h3  ><a  href= \"/tags/1/checklists\", >CheckLists of Tag</a></h3><h3  >" +
                "<a  href= \"/tags\", >Back</a></h3><h3  ><a  href= \"/\", >Home</a></h3></body></html>"
                , tagParcer.supply(t).toString());
    }



    @Test
    public void test_tag_ListEmpty_parce(){
        Tag t= new Tag(1,"Frutas","Black",new ArrayList<>());
        HtmlParser<Tag> ex1 = new TagParser();
        assertEquals("<html  ><body  ><h3  >TAG</h3><p  >Id: 1</p><p  >Name: Frutas</p><p  >Color: Black</p><h3  >" +
                "Checklists: </h3><p  >No CheckLists To Show.</p><h3  ><a  href= \"/tags/1/checklists\", >" +
                "CheckLists of Tag</a></h3><h3  ><a  href= \"/tags\", >Back</a></h3><h3  ><a  href= \"/\", >" +
                "Home</a></h3></body></html>",ex1.supply(t));
    }

    @Test
    public void test_task_parce(){
        Task t = tasks.get(0);
        HtmlParser<Task> tParcer = new TaskParser();
        assertEquals("<html  ><body  ><h3  >TASK</h3><p  >Id: 1</p><p  >CheckListID: 10</p><p  >" +
                "Description: Descricao 1</p><p  >DueDate: 2015-10-10</p></body></html>",tParcer.supply(t));
    }

    @Test
    public void test_templateTask_parce(){
        TemplateTask TT = new TemplateTask(1, 1, "TemplateTask 1", "TemplateTask 1 faz cenas");
        HtmlParser<TemplateTask> ttParcer = new TemplateTaskParser();
        assertEquals("<html  ><h2  >TEMPLATE TASK</h2><p  >Id: 1</p><p  >Name: TemplateTask 1</p>" +
                "<p  >Description: TemplateTask 1 faz cenas</p><p  >Template Id: 1</p></html>",ttParcer.supply(TT));
    }


    @Test
    public void test_template_parce(){
        Template t = new Template(1,"Template 1","Este e o template 1.");
        ArrayList<Template> tlist= new ArrayList<>();
        tlist.add(t);
        tlist.add(t);
        HtmlParser<List<Template>> tParcer = new TemplateParser();
        assertEquals("<html  ><body  ><table  style= \"width:50%\", border= \"1\", ><tr  ><h3  >" +
                        "TEMPLATE</h3><th  >Id</th><th  >Name</th><th  >Description</th><th  >" +
                        "Lisks</th></tr><tr  ><td  >1</td><td  >Template 1</td><td  >Este e o template 1.</td><td  >" +
                        "<a  href= \"/templates/1\", >Link</a></td></tr><tr  ><td  >1</td><td  >Template 1</td><td  >" +
                        "Este e o template 1.</td><td  ><a  href= \"/templates/1\", >Link</a></td></tr></table>" +
                        "<form  action= \"/templates\", method= \"post\", ><br  >Name:</br><input  type= \"text\", name= \"name\", >" +
                        "</input><br  >Description:</br><input  type= \"text\", name= \"description\", ></input>" +
                        "<input  type= \"hidden\", name= \"dest\", value= \"/templates/\", ></input><input  type= \"submit\", " +
                        "value= \"Submit\", ></input></form><h3  ><a  href= \"/checklists\", >CheckLists</a></h3><h3  ><a  href= \"/\", >" +
                        "Home</a></h3></body></html>",
                tParcer.supply(tlist));
    }

}
