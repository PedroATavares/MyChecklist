package pt.isel.ls;
import org.junit.Assert;
import org.junit.Test;
import pt.isel.ls.Model.*;

import java.util.*;


public class PrintTests {
    @Test
    public void test_CheckListtoString(){
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(5, 1, "Leite", "3", "20-5-2017", false));
        tasks.add(new Task(5, 2, "Pao", "1", "19-5-2017", false));
        tasks.add(new Task(5, 3, "Batata", "16", "20-5-2017", false));
        tasks.add(new Task(5, 4, "Pudim", "64", "21-5-2017", true));
        tasks.add(new Task(5, 5, "Pedra", "32", "20-5-2017", false));

        CheckList cl = new CheckList(5, "Lista de Compras", "Lista de compras para o frigorifico da Dona Fatima", "22-5-2017",false,4,tasks);

        Assert.assertEquals(cl.toString(), "Checklist id: 5\n" +
                "\tName: Lista de Compras\n" +
                "\tDescription: Lista de compras para o frigorifico da Dona Fatima\n" +
                "\tDue Date: 22-5-2017\n" +
                "\tTemplate Id: 4\n" +
                "\n" +
                "Task id: 1\n" +
                "\tName: Leite\n" +
                "\tDescription: 3\n" +
                "\tDue Date: 20-5-2017\n" +
                "\tClosed: false\n" +
                "\n" +
                "Task id: 2\n" +
                "\tName: Pao\n" +
                "\tDescription: 1\n" +
                "\tDue Date: 19-5-2017\n" +
                "\tClosed: false\n" +
                "\n" +
                "Task id: 3\n" +
                "\tName: Batata\n" +
                "\tDescription: 16\n" +
                "\tDue Date: 20-5-2017\n" +
                "\tClosed: false\n" +
                "\n" +
                "Task id: 4\n" +
                "\tName: Pudim\n" +
                "\tDescription: 64\n" +
                "\tDue Date: 21-5-2017\n" +
                "\tClosed: true\n" +
                "\n" +
                "Task id: 5\n" +
                "\tName: Pedra\n" +
                "\tDescription: 32\n" +
                "\tDue Date: 20-5-2017\n" +
                "\tClosed: false\n\n");
    }

    @Test
    public void test_CheckListtoString_WithEmptyFields(){
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(5, 1, "Leite", "3", null, false));
        tasks.add(new Task(5, 2, "Pao", "1", null, false));
        tasks.add(new Task(5, 3, "Batata", "16", null, false));
        tasks.add(new Task(5, 4, "Pudim", "64", null, true));
        tasks.add(new Task(5, 5, "Pedra", "32", null, false));

        CheckList cl = new CheckList(5, "Lista de Compras", "Lista de compras para o frigorifico da Dona Fatima", "22-5-2017",false,null,tasks);

        Assert.assertEquals(cl.toString(), "Checklist id: 5\n" +
                "\tName: Lista de Compras\n" +
                "\tDescription: Lista de compras para o frigorifico da Dona Fatima\n" +
                "\tDue Date: 22-5-2017\n" +
                "\tTemplate Id: Nonexistent.\n" +
                "\n" +
                "Task id: 1\n" +
                "\tName: Leite\n" +
                "\tDescription: 3\n" +
                "\tDue Date: Not established.\n" +
                "\tClosed: false\n" +
                "\n" +
                "Task id: 2\n" +
                "\tName: Pao\n" +
                "\tDescription: 1\n" +
                "\tDue Date: Not established.\n" +
                "\tClosed: false\n" +
                "\n" +
                "Task id: 3\n" +
                "\tName: Batata\n" +
                "\tDescription: 16\n" +
                "\tDue Date: Not established.\n" +
                "\tClosed: false\n" +
                "\n" +
                "Task id: 4\n" +
                "\tName: Pudim\n" +
                "\tDescription: 64\n" +
                "\tDue Date: Not established.\n" +
                "\tClosed: true\n" +
                "\n" +
                "Task id: 5\n" +
                "\tName: Pedra\n" +
                "\tDescription: 32\n" +
                "\tDue Date: Not established.\n" +
                "\tClosed: false\n\n");
    }
    @Test
    public void test_TasktoString(){
        Task t = new Task(5, 3, "Batata", "2", "10-8-2017", false);
        System.out.println(t);
        Assert.assertEquals(t.toString(), "Task id: 3\n" +
                "\tName: Batata\n" +
                "\tDescription: 2\n" +
                "\tDue Date: 10-8-2017\n" +
                "\tClosed: false\n");

    }
    @Test
     public void test_TemplatetoString(){
        Template t = new Template(2, "Lista de Compras", "Template Lista de Compras");
        Assert.assertEquals(t.toString(), "Template id: 2\n" +
                "\tName: Lista de Compras\n" +
                "\tDescription: Template Lista de Compras\n");
    }

    @Test
    public void test_TemplateTasktoString(){
        TemplateTask tt = new TemplateTask(1,2, "Lista de Frutas", "Template Lista de Frutas");
        System.out.println(tt);
        Assert.assertEquals(tt.toString(), "id: 1\n" +
                "\tName: Lista de Frutas\n" +
                "\tDescription: Template Lista de Frutas\n" +
                "\tTemplate Id: 2\n");
    }

    @Test
    public void test_FullTemplatetoString(){
        Template t = new Template(2, "Lista de Compras", "Template Lista de Compras");
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(5, 1, "Leite", "3", "20-5-2017", false));
        tasks.add(new Task(5, 2, "Pao", "1", "19-5-2017", false));
        tasks.add(new Task(5, 3, "Batata", "16", "20-5-2017", false));
        tasks.add(new Task(5, 4, "Pudim", "64", "21-5-2017", true));
        tasks.add(new Task(5, 5, "Pedra", "32", "20-5-2017", false));

        CheckList cl = new CheckList(5, "Lista de Compras", "Lista de compras para o frigorifico da Dona Fatima", "22-5-2017",false,4,tasks);
        CheckList cl1 = new CheckList(5, "Lista de Compras", "Lista de compras para o frigorifico da Dona Fatima", "22-5-2017",false,4,tasks);

        TemplateTask tt = new TemplateTask(1,2, "Lista de Frutas", "Template Lista de Frutas");
        ArrayList<TemplateTask> listTT = new ArrayList<TemplateTask>();
        listTT.add(tt);

        ArrayList<CheckList> listCkL = new ArrayList<CheckList>();
        listCkL.add(cl);
        listCkL.add(cl1);

        FullTemplate ft = new FullTemplate(t, listCkL, listTT );


        System.out.println(ft);

        Assert.assertEquals(ft.toString(), "Template id: 2\n" +
                "\tName: Lista de Compras\n" +
                "\tDescription: Template Lista de Compras\n" +
                "\n" +
                " Checklists: \n" +
                "[Checklist id: 5\n" +
                "\tName: Lista de Compras\n" +
                "\tDescription: Lista de compras para o frigorifico da Dona Fatima\n" +
                "\tDue Date: 22-5-2017\n" +
                "\tTemplate Id: 4\n" +
                "\n" +
                "Task id: 1\n" +
                "\tName: Leite\n" +
                "\tDescription: 3\n" +
                "\tDue Date: 20-5-2017\n" +
                "\tClosed: false\n" +
                "\n" +
                "Task id: 2\n" +
                "\tName: Pao\n" +
                "\tDescription: 1\n" +
                "\tDue Date: 19-5-2017\n" +
                "\tClosed: false\n" +
                "\n" +
                "Task id: 3\n" +
                "\tName: Batata\n" +
                "\tDescription: 16\n" +
                "\tDue Date: 20-5-2017\n" +
                "\tClosed: false\n" +
                "\n" +
                "Task id: 4\n" +
                "\tName: Pudim\n" +
                "\tDescription: 64\n" +
                "\tDue Date: 21-5-2017\n" +
                "\tClosed: true\n" +
                "\n" +
                "Task id: 5\n" +
                "\tName: Pedra\n" +
                "\tDescription: 32\n" +
                "\tDue Date: 20-5-2017\n" +
                "\tClosed: false\n" +
                "\n" +
                ", Checklist id: 5\n" +
                "\tName: Lista de Compras\n" +
                "\tDescription: Lista de compras para o frigorifico da Dona Fatima\n" +
                "\tDue Date: 22-5-2017\n" +
                "\tTemplate Id: 4\n" +
                "\n" +
                "Task id: 1\n" +
                "\tName: Leite\n" +
                "\tDescription: 3\n" +
                "\tDue Date: 20-5-2017\n" +
                "\tClosed: false\n" +
                "\n" +
                "Task id: 2\n" +
                "\tName: Pao\n" +
                "\tDescription: 1\n" +
                "\tDue Date: 19-5-2017\n" +
                "\tClosed: false\n" +
                "\n" +
                "Task id: 3\n" +
                "\tName: Batata\n" +
                "\tDescription: 16\n" +
                "\tDue Date: 20-5-2017\n" +
                "\tClosed: false\n" +
                "\n" +
                "Task id: 4\n" +
                "\tName: Pudim\n" +
                "\tDescription: 64\n" +
                "\tDue Date: 21-5-2017\n" +
                "\tClosed: true\n" +
                "\n" +
                "Task id: 5\n" +
                "\tName: Pedra\n" +
                "\tDescription: 32\n" +
                "\tDue Date: 20-5-2017\n" +
                "\tClosed: false\n" +
                "\n" +
                "]\n" +
                " Tasks in template: \n" +
                "[id: 1\n" +
                "\tName: Lista de Frutas\n" +
                "\tDescription: Template Lista de Frutas\n" +
                "\tTemplate Id: 2\n" +
                "]\n");
    }
}
