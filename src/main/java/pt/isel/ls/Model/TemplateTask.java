package pt.isel.ls.Model;

/**
 * Created by HP on 04/10/2016.
 */
public class TemplateTask {

    public final int id;
    public final  Integer templateId;
    public final String name;
    public final String description;

    public TemplateTask(int id, Integer templateId, String name, String description) {
        this.id = id;
        this.templateId = templateId;
        this.name = name;
        this.description = description;
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("id: ");
        sb.append(id);
        sb.append('\n');
        sb.append("Name: ");
        sb.append(name);
        sb.append('\n');
        sb.append("Description: ");
        sb.append(description);
        sb.append('\n');
        sb.append("Template Id: ");
        sb.append(templateId);
        sb.append('\n');
        return sb.toString();
    }
}
