package pt.isel.ls.Model;

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
        sb.append("\n\tName: ");
        sb.append(name);
        sb.append("\n\tDescription: ");
        sb.append(description);
        sb.append("\n\tTemplate Id: ");
        sb.append(templateId);
        sb.append('\n');
        return sb.toString();
    }
}
