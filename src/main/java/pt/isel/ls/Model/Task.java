package pt.isel.ls.Model;

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
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Task id: ");
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
        sb.append("Closed: ");
        sb.append(isClosed);
        sb.append('\n');
        return sb.toString();
    }

}
