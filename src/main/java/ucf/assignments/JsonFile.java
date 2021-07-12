package ucf.assignments;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

public class JsonFile {
    /*
    ToJson(Observable List that wants to be in Json){
        try{
            Create a writer which connects to a Buffered Writer
            write the task

            flush writer
        }
    }
     */
    public static void ToJson(ObservableList<Task> task){
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("./json/App.json"));
            writer.write(String.valueOf(task));

            writer.flush();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
    readJson(){
        Create reader which connects to a buffered reader which reads a Json
        Create a Gson builder
        Create an array of what is read
        Call TransferContents
    }
     */
    public static void readJson() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("./json/App.json"));
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Task[] enums = gson.fromJson(reader, Task[].class);
        transferContents(enums);
    }

    /*
    pseudo
     */
    public static ObservableList<Task> transferContents(Task[] enums){
        ObservableList<Task> task = FXCollections.observableArrayList();
        for(Task test : enums) {
            task.add(test);
        }
        System.out.println(task);
        return task;
    }

}
