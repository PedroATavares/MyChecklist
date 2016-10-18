package pt.isel.ls.Model;

import java.util.List;

public class FullTemplate {

    public final Template temp;
    public final List<CheckList> listCkL;
    public final List<TemplateTask> listTsk;

    public FullTemplate(Template temp, List<CheckList> listCkL, List<TemplateTask> listTsk )
    {
        this.temp = temp;
        this.listCkL = listCkL;
        this.listTsk = listTsk;
    }

    @Override
    public String toString() {
        return temp+  "\n Checklists: \n" + listCkL  + "\n Tasks in template: \n" +listTsk + "\n";
    }
}
