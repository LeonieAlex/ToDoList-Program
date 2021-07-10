package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private SimpleStringProperty TaskName, TaskDesc, Progress;
    private String date;

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
}
