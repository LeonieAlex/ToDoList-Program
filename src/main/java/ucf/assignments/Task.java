package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
This class for the Task (which basically consists of the Name, Description, Due Date and the progress (incomplete or not))

Create a simple string for the Task Name, Task Description and Progress
Create a string for the Date

    Task Function which takes the Task Name, Task Description, Date and Progress
        take all the Task Name, Task Desc, Date and Progress
        Set the Date to a YYYY-MM-DD format

    getTaskName function
        return the TaskName

    setTaskName that takes the TaskName
        make it a simpleStringProperty

    getTaskDesc function
        return the TaskDesc

    setTaskDesc that takes the TaskDesc
        make it a simpleStringProperty

    getDate function
        return the TaskName

    setDate that takes the Date
        set it

    getProgress function
        return the Progress

    setProgress that takes the Progress
        make it a simpleStringProperty
 */
public class Task {
    public SimpleStringProperty TaskName, TaskDesc, Progress;
    public String date;

    public Task(String TaskName, String TaskDesc, LocalDate date, String Progress) {
        this.TaskName = new SimpleStringProperty(TaskName);
        this.TaskDesc = new SimpleStringProperty(TaskDesc);
        String DateFormat = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.date = DateFormat;
        this.Progress = new SimpleStringProperty(Progress);
    }

    public String getTaskName() {
        return TaskName.get();
    }

    public void setTaskName(String TaskName) {
        this.TaskName = new SimpleStringProperty(TaskName);
    }

    public String getTaskDesc() {
        return TaskDesc.get();
    }

    public void setTaskDesc(String TaskDesc) {
        this.TaskDesc = new SimpleStringProperty(TaskDesc);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String DateFormat) {
        this.date = DateFormat;
    }

    public String getProgress() {
        return Progress.get();
    }

    public void setProgress(String Progress) {
        this.Progress = new SimpleStringProperty(Progress);
    }

    @Override
    public String toString() {
        return "Task [Task_name="
                + TaskName
                + ", description="
                + TaskDesc
                + ", Date="
                + date + ", Progress=" + Progress + "]";
    }
}
