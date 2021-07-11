package ucf.assignments;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

public class JsonFile {
    //error here
    public static void ToJson(ObservableList<Task> task){
        /*task.setTaskName(Name);
        task.setTaskDesc(Desc);
        task.setDate(Date);
        task.setProgress(2000);*/
        System.out.println(new Gson().toJson(task));
    }

    //Havent even tried this
    public void readJson(ObservableList<Task> task) throws IOException {
        Writer writer = Files.newBufferedWriter(Paths.get("App.json"));
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        gson.toJson(Task.class, writer);
        writer.flush();
    }
}
