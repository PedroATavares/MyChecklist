package pt.isel.ls.Model;

public class TemplateTask {

    public final int id;
    public final  int templateId;
    public final String name;
    public final String description;

    public TemplateTask(int id, int templateId, String name, String description) {
        this.id = id;
        this.templateId = templateId;
        this.name = name;
        this.description = description;
    }
}
