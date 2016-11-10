package pt.isel.ls;

import org.junit.Before;
import org.junit.Test;
import pt.isel.ls.Html.Parcers.*;
import pt.isel.ls.Model.*;

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
        HtmlParcer<CheckList> clParcer = new CheckListParcer();
        assertEquals("<html><h2>Checklist</h2><p>Id: 10</p><p>Name: Checklist 1</p><p>Description: Descricao 1</p><p>Due date: 2015-10-10</p><p>Template Id: 2</p><p>Is Closed: true</p><h3>Tasks</h3><p>No Tasks To Show</p><h3>Tags</h3><p>No Tags To Show.</p></html>",clParcer.supply(cl));
    }


    @Test
    public void test_checklist_List_parce(){
        CheckList cl= new CheckList(10,"Checklist 1","Descricao 1","2015-10-10",true,2,tasks);
        cl.setTagOnList(tags.get(0));
        HtmlParcer<CheckList> ex1 = new CheckListParcer();
        assertEquals("<html><h2>Checklist</h2><p>Id: 10</p><p>Name: Checklist 1</p><p>Description: Descricao 1</p><p>Due date: 2015-10-10</p><p>" +
                "Template Id: 2</p><p>Is Closed: true</p><h3>Tasks</h3><table><tr><td>Id</td><td>CheckList Id</td><td>Name</td><td>Descricao</td>" +
                "<td>Due Date</td><td>Is Closed</td></tr><tr><td>1</td><td>10</td><td>Task 1</td><td>Descricao 1</td><td>2015-10-10</td><td>false</td>" +
                "</tr><tr><td>2</td><td>10</td><td>Task 2</td><td>Descricao 2</td><td>2015-5-12</td><td>true</td></tr><tr><td>3</td><td>10</td><td>Task " +
                "3</td><td>Descricao 3</td><td>2016-12-11</td><td>false</td></tr></table><h3>Tags</h3><table><tr><td>Id</td><td>Name</td><td>Color</td>" +
                "</tr><tr><td>1</td><td>Tag 1</td><td>A</td></tr></table></html>",ex1.supply(cl).toString());
    }

    @Test
    public void test_tag_parce(){
        Tag t= new Tag(1,"Frutas","Black",cll);
        HtmlParcer<Tag> tagParcer = new TagParcer();
        assertEquals("<html><body><h3>TAG</h3><p>Id: 1</p><p>Name: Frutas</p><p>Color: Black</p><h3>Checklists: </h3><table><tr><td>Id</td><td>Template Id</td><td>Name" +
                "</td><td>Descricao</td><td>Due Date</td><td>Is Closed</td></tr><tr><td>10</td><td>null</td><td>Checklist 1</td><td>Descricao 1</td><td>2015-10-10</td><td>" +
                "true</td></tr><tr><td>10</td><td>null</td><td>Checklist 1</td><td>Descricao 1</td><td>2015-10-10</td><td>true</td></tr></table></body></html>", tagParcer.supply(t).toString());
    }


    @Test
    public void test_tag_ListEmpty_parce(){
        Tag t= new Tag(1,"Frutas","Black",new ArrayList<>());
        HtmlParcer<Tag> ex1 = new TagParcer();
        assertEquals("<html><body><h3>TAG</h3><p>Id: 1</p><p>Name: Frutas</p><p>Color: Black</p><h3>Checklists: </h3><p>No CheckLists To Show.</p></body></html>",ex1.supply(t));

    }

    @Test
    public void test_task_parce(){
        Task t = tasks.get(0);
        HtmlParcer<Task> tParcer = new TaskParcer();
        assertEquals("<html><body><h3>TASK</h3><p>Id: 1</p><p>CheckListID: 10</p><p>Description: Descricao 1</p><p>DueDate: 2015-10-10</p></body></html>",tParcer.supply(t));
    }

    @Test
    public void test_templateTask_parce(){
        TemplateTask TT = new TemplateTask(1, 1, "TemplateTask 1", "TemplateTask 1 faz cenas");
        HtmlParcer<TemplateTask> ttParcer = new TemplateTaskParcer();
        assertEquals("<html><h2>TEMPLATE TASK</h2><p>Id: 1</p><p>Name: TemplateTask 1</p><p>Description:" +
                " TemplateTask 1 faz cenas</p><p>Template Id: 1</p></html>",ttParcer.supply(TT));
    }

    @Test
    public void test_template_parce(){
        Template t = new Template(1,"Template 1","Este e o template 1.");
        HtmlParcer<Template> tParcer = new TemplateParcer();
        assertEquals("<html><body><h3>TEMPLATE</h3><p>Id: 1</p><p>Name: Template 1</p><p>Description: Este e o template 1.</p></body></html>",tParcer.supply(t));
    }

}
