package pt.isel.ls.Model;

import java.util.ArrayList;

public class CheckList {
    public final ArrayList<Task> tasks;
    public final int id;
    public final String name;
    public final String description;
    public String dueDate;
    public int templateId;


    public CheckList( int checklistId, String name, String description, String dueDate, int templateId, ArrayList<Task> tasks) {
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

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }
}
