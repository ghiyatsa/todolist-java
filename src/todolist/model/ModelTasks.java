package todolist.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author ghiyatsa_
 */
public class ModelTasks {

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    
    public Date getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }    
    
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ModelStatus getStatus() {
        return status;
    }

    public void setStatus(ModelStatus status) {
        this.status = status;
    }

    public ModelTasks(int taskId, Date dueDate, String task, String description, ModelStatus status) {
        this.taskId = taskId;
        this.dueDate = dueDate;
        this.task = task;
        this.description = description;
        this.status = status;
    }

    public ModelTasks() {
    }

    private int taskId;
    private Date dueDate;
    private String task;
    private String description;
    private ModelStatus status;

    public Object[] toTableRow(int rowNum) {
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        return new Object[]{false, rowNum, dueDate == null ? "" : df.format(dueDate), this, description, status };
    }

    @Override
    public String toString() {
        return task;
    }
}
