package pt.isel.ls.Model;

import java.util.ArrayList;

public class CheckList {
    public  ArrayList<Task> tasks;
    public  ArrayList<Tag> tags;
    public final int id;
    public final String name;
    public final String description;
    public String dueDate;
    public boolean isClosed;
    public Integer templateId;


    public CheckList( int checklistId, String name,
                      String description,
                      String dueDate,
                      boolean isClosed,
                      Integer templateId,
                      ArrayList<Task> tasks)
    {

        this.tasks = tasks;
        this.id = checklistId;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.isClosed = isClosed;
        this.templateId=templateId;
        this.tags=new ArrayList<>();

    }
    public CheckList( int checklistId, String name,
                      String description,
                      String dueDate,
                      boolean isClosed,
                      Integer templateId)
    {
        this.id = checklistId;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.isClosed = isClosed;
        this.templateId=templateId;

    }


    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }
    public void setTagOnList(Tag tag) {
        tags.add(tag);
    }
    public void setTasks(ArrayList<Task> t){this.tasks = t;}
    public void setTag(ArrayList<Tag> tg){this.tags = tg;}

    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Checklist id: ");
        sb.append(id);
        sb.append("\n\tName: ");
        sb.append(name);
        sb.append("\n\tDescription: ");
        sb.append(description);
        sb.append("\n\tDue Date: ");
        if(dueDate!=null && !dueDate.equals(""))sb.append(dueDate);
        else sb.append("Not established.");
        sb.append("\n\tTemplate Id: ");
        if(templateId != null && templateId>0)sb.append(templateId);
        else sb.append("Nonexistent.");
        sb.append('\n');
        sb.append('\n');
        if(tasks!=null)
            for (int i = 0; i <tasks.size(); i++) {
                sb.append(tasks.get(i));
                sb.append('\n');
            }
        if(tags!=null)
            for (int i = 0; i <tags.size(); i++) {
                sb.append(tags.get(i));
                sb.append('\n');
            }
        return sb.toString();
    }
}
