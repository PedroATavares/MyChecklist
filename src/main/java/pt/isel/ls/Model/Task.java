package pt.isel.ls.Model;

public class Task {
    public final int checklistId;
    public final int id;
    public final String name;
    public final String description;
    public final String dueDate;
    public boolean isClosed;


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
        sb.append("\n\tName: ");
        sb.append(name);
        sb.append("\n\tDescription: ");
        sb.append(description);
        sb.append("\n\tDue Date: ");
        if(dueDate!=null && !dueDate.equals(""))sb.append(dueDate);
        else sb.append("Not established.");
        sb.append("\n\tClosed: ");
        sb.append(isClosed);
        sb.append('\n');
        return sb.toString();
    }

}
