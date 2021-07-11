package ucf.assignments;

import com.google.gson.Gson;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

public class JsonFile {
    public static void ToJson() throws IOException {
        Writer writer = Files.newBufferedWriter(Paths.get("App.json"));
        Gson gson = new Gson();
        gson.toJson(ToDoListController.class, writer);
        writer.flush();
    }

    public void readJson(ObservableList<Task> task){
        for (Task temp: task) {
            String tempName = temp.getTaskName();
            String tempDesc = temp.getTaskDesc();
            String tempDate = temp.getDate();
            LocalDate localDate = LocalDate.parse(tempDate);
            String tempProgress = temp.getProgress();
            task.add(new Task(tempName, tempDesc, localDate, tempProgress));
        }
    }
}
