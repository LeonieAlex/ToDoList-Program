package ucf.assignments;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToDoListController implements Initializable {

    @FXML
    private TableView<Task> tableView;

    @FXML
    private TableColumn<Task, String> table_task;

    @FXML
    private TableColumn<Task, String> table_description;

    @FXML
    private TableColumn<Task, String> table_date;

    @FXML
    private TableColumn<Task, String> table_progress;

    @FXML
    private Button ButtonDelete;

    @FXML
    private Button ButtonClear;

    @FXML
    private TextField FilterField;

    @FXML
    private Menu MenuOpen;

    @FXML
    private Menu MenuSave;

    @FXML
    private TextField Name;

    @FXML
    private TextField Description;

    @FXML
    private DatePicker datePickerOption;

    @FXML
    private CheckBox Progress;

    @FXML
    private Button ButtonAdd;

    @FXML
    private Button HelpButton;

    public final ObservableList<Task> task = FXCollections.observableArrayList();

    /*
    Call ToJson Method from JsonFile
     */
    @FXML
    void MenuSave(ActionEvent event) throws IOException {
        JsonFile.ToJson(task);
    }

    /*
    Call readJson method from JsonFile
    set items to TableView
     */
    @FXML
    void openFile(ActionEvent event) throws IOException {
        tableView.setItems(JsonFile.readJson());
    }

    /*
    When user clicks on the Help button, this function is called

        Create a Parent that calls the next fxml file (in this case Help.fxml)
        Create a Scene which calls the Parent
        Create a new Stage
        set Scene
        Show
     */
    @FXML
    void OnClickHelp(ActionEvent event) throws IOException {
        Parent AddNewTask = FXMLLoader.load(getClass().getResource("Help.fxml"));
        Scene AddNewScene = new Scene(AddNewTask);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(AddNewScene);
        window.show();
    }

    /*
    When user clicks on this button, the selected row of task is deleted

        Create an ObservableList variable of Task
        let that variable equal to the selected row
        create a loop
        for(task in table is equal to the task selected)
            remove the task
     */
    @FXML
    void OnButtonClickDelete(ActionEvent event) {
        ObservableList<Task> selectedRows;

        selectedRows = tableView.getSelectionModel().getSelectedItems();

        for (Task anotherTask: selectedRows){
            task.remove(anotherTask);
        }
    }

    /*
    When User clicks on this button, the whole table is cleared

        Call the ObservableList variable created above
        Clear it
     */
    @FXML
    void OnButtonClickClear(ActionEvent event) {
        task.clear();
    }

    /*
    When user clicks on this button, everything written into the TextField would be added to the Table

        Create a String which is empty called CheckBox
        Create a String called name which gets the Text written in to the Name TextField
        Create a String called desc which gets the Text written in the Description TextField
        Create a LocalDate called date which takes the value in the date Picker

        Create an if statement to check the description length
        if the length is lesser than 0 or larger than 256
            call the AlertBox class and show error
        else
            if the Progress check box is selected
                CheckBox String is equal to "Completed"
            else
                CheckBox String is equal to "Incomplete"

            Create a Task which calls Name, Description, Date and CheckBox String
            Add the Task

        Reset everything to null or empty
     */
    @FXML
    void OnButtonClickAdd(ActionEvent event) {
        String CheckBox = "";
        String name = Name.getText();
        String desc = Description.getText();
        LocalDate date = datePickerOption.getValue();

        if(!Check.CheckLength(name) && !Check.CheckLength(desc)){
            AlertBox.display("Error", "There must be more than 0 and less than 256 characters");
        } else {
            if(Progress.isSelected()){
                CheckBox = "Completed";
            } else {
                CheckBox = "Incomplete";
            }
            Task newTask = new Task(Name.getText(), Description.getText(), date, CheckBox);

            task.add(newTask);
        }
        Name.setText("");
        Description.setText("");
        Progress.setSelected(false);
        datePickerOption.setValue(null);
    }

    /*
    This allows input added to the Editable Cell to have a format, therefore if it is not the right format, it wont work

        Create a new Task which is the selected table Row
        Create a New String which is the Task's Cell's new Value
        Create a Old String which is the Task's Cell's old Value

        Create an if statement to call the Date Validation method
        Call Change Date to see if the cell should return the new or Old value
        if statement to check if it's the old value
            Alert Box
        refresh table
     */
    @FXML
    void changeTableDate(TableColumn.CellEditEvent editedCell) {
        Task taskSelected =  tableView.getSelectionModel().getSelectedItem();
        String New = editedCell.getNewValue().toString();
        String Old = editedCell.getOldValue().toString();

        taskSelected.setDate(Check.ChangeDate(New, Old));
        if(Check.ChangeDate(New, Old).equals(Old)){
            AlertBox.display("Error", "Not the right format or invalid date");
        }
        tableView.refresh();
    }

    /*
    This allows user to be able to edit the Description

        Create a new Task which is the selected table Row
        Create a New String which is the Task's Cell's new Value
        Create a Old String which is the Task's Cell's old Value

        Call the Check function to see if the new or old string will be returned
        Change the cell with the new or old string
        if statement to check if it's the old value
            Alert Box
        refresh table
     */
    @FXML
    void changeTableDescCellEvent(TableColumn.CellEditEvent editedCell) {
        Task taskSelected =  tableView.getSelectionModel().getSelectedItem();
        String New = editedCell.getNewValue().toString();
        String Old = editedCell.getOldValue().toString();

        taskSelected.setTaskDesc(Check.ChangeDescription(New, Old));
        if(Check.ChangeDescription(New, Old).equals(Old)){
            AlertBox.display("Error", "There must be more than 0 and less than 256 characters");
        }
        tableView.refresh();
    }

    /*
      This allows input added to the Editable Cell to have a format, therefore if it is not the right format, it wont work

        Create a new Task which is the selected table Row
        Create a New String which is the Task's Cell's new Value
        Create a Old String which is the Task's Cell's old Value

        Call Check class which returns whether it would return the New or Old string
        Change the cell to the New or Old String
        if statement to check if it's the old value
            Alert Box
        refresh table
       */
    @FXML
    void changeTableProgress(TableColumn.CellEditEvent editedCell) {
        Task taskSelected =  tableView.getSelectionModel().getSelectedItem();
        String New = editedCell.getNewValue().toString();
        String Old = editedCell.getOldValue().toString();
        taskSelected.setProgress(Check.ChangeProgress(New, Old));
        if(Check.ChangeProgress(New, Old).equals(Old)){
            AlertBox.display("Error", "Task must either be Completed or Incomplete");
        }
        tableView.refresh();
    }


    /*
    This method is to do a lot of things..

        setCellValueFactory for table_task
        setCellValueFactory for table_description
        setCellValueFactory for table_date
        setCellValueFactory for table_progress

        set items for the table view by calling the GetTask function

        set the tableview to editable
        set editable for table_description, table_date and table_progress

        Create a FilteredList variable called filteredData
        Create a Listener which takes the observable, oldValue and newValue which filters the task
            if(the newValue is null or empty)
                return true

            Create a new LowerCaseFilter which allows the newValue to be in lowerCase

            if(the Task's Progress's index is not -1) (Allows only the Task Progress to be sorted)
                return true
            return false

        Create a SortedList variable
        Bind the variable to the tableView
        setItems as what is in the SortedList
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table_task.setCellValueFactory(new PropertyValueFactory<Task, String>("taskName"));
        table_description.setCellValueFactory(new PropertyValueFactory<Task, String>("taskDesc"));
        table_date.setCellValueFactory(new PropertyValueFactory<Task, String>("Date"));
        table_progress.setCellValueFactory(new PropertyValueFactory<Task, String>("Progress"));

        tableView.setItems(getTask());

        tableView.setEditable(true);
        table_description.setCellFactory(TextFieldTableCell.forTableColumn());
        table_date.setCellFactory(TextFieldTableCell.forTableColumn());
        table_progress.setCellFactory(TextFieldTableCell.forTableColumn());

        FilteredList<Task> filteredData = new FilteredList<>(task, b->true);
        FilterField.textProperty().addListener((observable, oldValue, newValue)->{
            filteredData.setPredicate(Task -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if(Task.getProgress().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;
            });
        });

        SortedList<Task> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    /*
    Create a getTask function
        return task
     */
    public ObservableList<Task> getTask(){
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Completed"));
        task.add(new Task("Essay","For ENC1102", LocalDate.of(1952, Month.MAY, 21), "Completed"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Completed"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Incomplete"));

        return task;
    }

    //for testing purposes lol
    /*
    CountAdd
    Create a Observable List
    Add tasks
    Count the size of the task

    return count
     */
    static Integer CountAdd(){
        ObservableList<Task> task = FXCollections.observableArrayList();

        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Incomplete"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Incomplete"));
        Integer count = task.size();

        return count;
    }
    /*
       Count Remove
            Create a Observable List
            Add tasks
            remove index 1
            Count the size of the task

       return count
    */
    static Integer CountRemove(){
        ObservableList<Task> task = FXCollections.observableArrayList();

        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Incomplete"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Incomplete"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Incomplete"));

        task.remove(1);
        Integer CountRemove = task.size();

        return CountRemove;
    }

    /*
   CountClear
   Create a Observable List
   Add tasks
   Clear task
   Count the size of the task

   return count
    */
    static Integer CountClear(){
        ObservableList<Task> task = FXCollections.observableArrayList();

        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Incomplete"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Incomplete"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Incomplete"));

        task.clear();
        Integer CountClear = task.size();

        return CountClear;
    }

    /*
    Integer CountCompleted()
        create a new Observable List
        create a count which equals to 0

        add new tasks

        loop through task
            if the the test progress is "completed"
                count

        return count
     */
    static Integer CountCompleted(){
        ObservableList<Task> task = FXCollections.observableArrayList();
        Integer count=0;

        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Incomplete"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Completed"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Completed"));

        for(Task test : task) {
            String completion = test.getProgress();
            if(completion.equals("Completed"))
                count++;
        }
        return count;
    }

    /*
    Integer CountIncomplete()
        create a new Observable List
        create a count which equals to 0

        add new tasks

        loop through task
            if the the test progress is "Incomplete"
                count

        return count
     */
    static Integer CountIncomplete(){
        ObservableList<Task> task = FXCollections.observableArrayList();
        Integer count=0;

        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Incomplete"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Completed"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Completed"));

        for(Task test : task) {
            String completion = test.getProgress();
            if(completion.equals("Incomplete"))
                count++;
        }
        return count;
    }

    /*
    Integer CountAll()
        create a new Observable List
        create a count which equals to 0

        add new tasks

        loop through task
            count

        return count
     */
    static Integer CountAll(){
        ObservableList<Task> task = FXCollections.observableArrayList();
        Integer count=0;

        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Incomplete"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Completed"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Completed"));

        for(Task test : task) {
            count++;
        }
        return count;
    }

    /*
    Integer CheckJson()
        create a new Observable List
        add new tasks

        Put the observable list to a Json file
        Create a new observable list which is the taken from a JsonFile

        for(loop_
            if the string from the observable list are equal
                return 1
            else
                return 0

        return 0
     */
    static Integer CheckJson() throws IOException {
        ObservableList<Task> task = FXCollections.observableArrayList();
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Incomplete"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Completed"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Completed"));

        JsonFile.ToJson(task);
        ObservableList<Task> output =  JsonFile.readJson();

        for (int i = 0; i < task.size(); i++){
            if(task.get(i).toString().equals(output.get(i).toString())){
                return 1;
            }else{
                return 0;
            }
        }
        return 0;
    }

    /*
    Integer CheckSaveJson()
        create a new Observable List
        add new tasks

        Put the observable list to a Json file

        Read App.json file
        Scan
            if it has contents
                return 1
        return 0
     */
    static Integer CheckSaveJson() throws IOException {
        ObservableList<Task> task = FXCollections.observableArrayList();
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Incomplete"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Completed"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Completed"));

        JsonFile.ToJson(task);

        File myObj = new File("App.json");
        Scanner myReader = new Scanner(myObj);
        if(myReader.hasNext()){
            return 1;
        }
        return 0;
    }
}