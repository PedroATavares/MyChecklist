package pt.isel.ls;

import org.junit.Test;
import pt.isel.ls.Html.Parcers.ChecklistParser;
import pt.isel.ls.Html.Parcers.HtmlParser;
import pt.isel.ls.Html.Parcers.TaskParser;
import pt.isel.ls.Model.CheckList;
import pt.isel.ls.Model.Task;

import java.util.ArrayList;

public class HtmlParcerTests {

    @Test
    public void test_checklist_parce(){
        CheckList cl= new CheckList(10,"Checklist 1","Descricao 1","2015-10-10",true,2,null);
        HtmlParser<CheckList> clParcer = new ChecklistParser();
        System.out.println(clParcer.supply(cl));
    }


    @Test
    public void test_checklist_List_parce(){
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task(10, 1,"Task 1","Descricao 1","2015-10-10",false));
        tasks.add(new Task(10, 2,"Task 2","Descricao 2","2015-5-12",true));
        tasks.add(new Task(10, 3,"Task 3","Descricao 3","2016-12-11",false));

        HtmlParser<Task> ex1 = new TaskParser();

        CheckList cl= new CheckList(10,"Checklist 1","Descricao 1","2015-10-10",true,null, tasks);
        HtmlParser<CheckList> clParcer = new ChecklistParser();
        System.out.println(ex1.supply(new Task(10, 1,"Task 1","Descricao 1","2015-10-10",false)));
    }
}
