package pt.isel.ls.Model;

import java.util.ArrayList;

public class CheckList {
    public final ArrayList<Task> tasks;
    public final int id;
    public final String name;
    public final String description;
    public String dueDate;//
    public Integer templateId;//


    public CheckList( int checklistId, String name, String description, String dueDate, Integer templateId, ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.id = checklistId;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.templateId=templateId;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
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
        sb.append("Due Date: ");
        if(dueDate!=null && !dueDate.equals(""))sb.append(dueDate);
        else sb.append("Not established.");
        sb.append('\n');
        sb.append("Template Id: ");
        if(templateId != null)sb.append(templateId);
        else sb.append("Nonexistent.");
        sb.append('\n');
        sb.append('\n');
        for (int i = 0; i <tasks.size(); i++) {
            sb.append(tasks.get(i));
            sb.append('\n');
        }
        return sb.toString();
    }
}
