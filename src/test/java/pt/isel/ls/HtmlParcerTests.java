package pt.isel.ls;

import org.junit.Test;
import pt.isel.ls.Html.Parcers.CheckListParcer;
import pt.isel.ls.Html.Parcers.HtmlParcer;
import pt.isel.ls.Model.CheckList;
import pt.isel.ls.Model.Task;

import java.util.ArrayList;
import java.util.List;

public class HtmlParcerTests {

    @Test
    public void test_checklist_parce(){
        CheckList cl= new CheckList(10,"Checklist 1","Descricao 1","2015-10-10",true,2,null);
        HtmlParcer<CheckList> clParcer = new CheckListParcer();
        System.out.println(clParcer.supply(cl));
    }


    @Test
    public void test_checklist_List_parce(){
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task(10, 1,"Task 1","Descricao 1","2015-10-10",false));
        tasks.add(new Task(10, 2,"Task 2","Descricao 2","2015-5-12",true));
        tasks.add(new Task(10, 3,"Task 3","Descricao 3","2016-12-11",false));

        CheckList cl= new CheckList(10,"Checklist 1","Descricao 1","2015-10-10",true,null, tasks);
        HtmlParcer<CheckList> clParcer = new CheckListParcer();
        System.out.println(clParcer.supply(cl));
    }
}
