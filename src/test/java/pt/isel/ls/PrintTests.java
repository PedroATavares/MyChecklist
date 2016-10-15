package pt.isel.ls;
import org.junit.Test;
import pt.isel.ls.Model.CheckList;
import pt.isel.ls.Model.Task;
import pt.isel.ls.Model.Template;
import pt.isel.ls.Model.TemplateTask;

import java.util.ArrayList;


public class PrintTests {
    @Test
    public void test_CheckListtoString(){
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(5, 1, "Leite", "3", "20-5-2017", false));
        tasks.add(new Task(5, 2, "Pao", "1", "19-5-2017", false));
        tasks.add(new Task(5, 3, "Batata", "16", "20-5-2017", false));
        tasks.add(new Task(5, 4, "Pudim", "64", "21-5-2017", true));
        tasks.add(new Task(5, 5, "Pedra", "32", "20-5-2017", false));
        CheckList cl = new CheckList(5, "Lista de Compras", "Lista de compras para o frigorifico da Dona Fatima", "22-5-2017", false,4,tasks);
        System.out.println(cl);
    }

    @Test
    public void test_CheckListtoString_WithEmptyFields(){
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(5, 1, "Leite", "3", null, false));
        tasks.add(new Task(5, 2, "Pao", "1", null, false));
        tasks.add(new Task(5, 3, "Batata", "16", null, false));
        tasks.add(new Task(5, 4, "Pudim", "64", null, true));
        tasks.add(new Task(5, 5, "Pedra", "32", null, false));
        CheckList cl = new CheckList(5, "Lista de Compras", "Lista de compras para o frigorifico da Dona Fatima", "22-5-2017", false, null,tasks);
        System.out.println(cl);
    }
    @Test
    public void test_TasktoString(){
        Task t = new Task(5, 3, "Batata", "2", "10-8-2017", false);
        System.out.println(t);
    }
    @Test
     public void test_TemplatetoString(){
        Template t = new Template(2, "Lista de Compras", "Template Lista de Compras");
        System.out.println(t);
    }

    @Test
    public void test_TemplateTasktoString(){
        TemplateTask t = new TemplateTask(1,2, "Lista de Frutas", "Template Lista de Frutas");
        System.out.println(t);
    }
}
