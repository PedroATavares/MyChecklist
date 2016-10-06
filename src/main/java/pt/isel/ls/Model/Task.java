package pt.isel.ls.Model;

/**
 * Created by HP on 04/10/2016.
 */
public class Task {
    public final int checklistId;
    public final int id;
    public final String name;
    public final String description;
    public final String dueDate;
    public final boolean isClosed;


    public Task(int checklistId, int taskId, String name, String description, String dueDate, boolean isClosed) {
        this.checklistId = checklistId;
        this.id = taskId;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.isClosed = isClosed;
    }
}
