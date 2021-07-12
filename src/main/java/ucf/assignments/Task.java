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
    public String TaskName, TaskDesc, Progress;
    public String date;

    public Task(String TaskName, String TaskDesc, LocalDate date, String Progress) {
        this.TaskName = TaskName;
        this.TaskDesc = TaskDesc;
        String DateFormat = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.date = DateFormat;
        this.Progress = Progress;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String TaskName) {
        this.TaskName = TaskName;
    }

    public String getTaskDesc() {
        return TaskDesc;
    }

    public void setTaskDesc(String TaskDesc) {
        this.TaskDesc = TaskDesc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String DateFormat) {
        this.date = DateFormat;
    }

    public String getProgress() {
        return Progress;
    }

    public void setProgress(String Progress) {
        this.Progress = Progress;
    }

    /*
    Override
    ToString()
        return the format of how a Json should look like
     */
   @Override
    public String toString() {
        return "{\"TaskName\":"
                + "\"" + TaskName + "\""
                + ", \"TaskDesc\":"
                + "\"" + TaskDesc + "\""
                + ", \"date\":"
                + "\"" + date + "\"" + ", \"Progress\":" + "\"" + Progress + "\"" + "}";
    }
}
