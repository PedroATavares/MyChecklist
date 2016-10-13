package pt.isel.ls.Model;

import java.util.ArrayList;

public class CheckList {
    public final ArrayList<Task> tasks;
    public final int id;
    public final String name;
    public final String description;
    public String dueDate;
    public boolean isClosed;
    public int templateId;



    public CheckList( int checklistId, String name, String description, String dueDate, boolean isClosed, int templateId, ArrayList<Task> tasks) {
        this.tasks = tasks;
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

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }
}
