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
    public static void ToJson(ObservableList<Task> task) throws IOException {
        File myObj = new File("App.json");
        try {
            FileWriter writer = new FileWriter(myObj);
            Writer bw = new BufferedWriter(writer);
            bw.write(String.valueOf(task));

            bw.flush();
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
        return TransferContents
    }
     */
    public static ObservableList<Task> readJson() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("App.json"));
        Gson gson = new Gson();
        Task[] enums = gson.fromJson(reader, Task[].class);

        return transferContents(enums);
    }

    /*
    transferContents
        Create Observable List
        loop through
            add
        return Observable List
     */
    public static ObservableList<Task> transferContents(Task[] enums){
        ObservableList<Task> task = FXCollections.observableArrayList();
        for(Task test : enums) {
            task.add(test);
        }
        return task;
    }

}
