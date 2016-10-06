package pt.isel.ls.Model;

import java.util.ArrayList;

/**
 * Created by HP on 04/10/2016.
 */
public class CheckList {
    public final ArrayList<Task> tasks;
    public final int id;
    public final String name;
    public final String description;
    public String dueDate;
    public int templateId;


    public CheckList(ArrayList<Task> tasks, int checklistId, String name, String description, String dueDate, int templateId) {
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
