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
import java.io.IOException;
import java.io.Writer;
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
    private Menu MenuSaveAs;

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

    final ObservableList<Task> task = FXCollections.observableArrayList();

    @FXML
    void MenuSave(ActionEvent event) throws IOException {
        JsonFile.ToJson();
    }

    @FXML
    void MenuSaveAs(ActionEvent event) {
        System.out.println("You clicked SaveAs");
    }

    @FXML
    void MenuOpen(ActionEvent event) {
        System.out.println("You clicked MenuOpen");
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

        if(name.length()<=0 || name.length()>=256 || desc.length()<=0 || desc.length()>=256){
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
        if(Date Validation for the New String is true)
            set the Cell to the New input
        else
            An alert box will alert the user about the input having the wrong format or invalid date (Gregorian date or not)
            set the Cell to the Old input
        refresh table
     */
    @FXML
    void changeTableDate(TableColumn.CellEditEvent editedCell) {
        Task taskSelected =  tableView.getSelectionModel().getSelectedItem();
        String New = editedCell.getNewValue().toString();
        String Old = editedCell.getOldValue().toString();

        if(DateValidation.dateValidation(New))
            taskSelected.setProgress(New);
        else{
            AlertBox.display("Error", "Not the right format or invalid date");
            taskSelected.setProgress(Old);
        }
        tableView.refresh();
    }

    /*
    This allows user to be able to edit the Description

        Create a new Task which is the selected table Row
        Create a New String which is the Task's Cell's new Value
        Create a Old String which is the Task's Cell's old Value

        Create an if statement to check if the input is valid
        if(the length is lesser than 0 or larger than 256)
            alert user that it is invalid
            set to Old Input
        else
            set to new input
        refresh table
     */
    @FXML
    void changeTableDescCellEvent(TableColumn.CellEditEvent editedCell) {
        Task taskSelected =  tableView.getSelectionModel().getSelectedItem();
        String New = editedCell.getNewValue().toString();
        String Old = editedCell.getOldValue().toString();

        if(New.length()<=0 || New.length()>=256){
            AlertBox.display("Error", "There must be more than 0 and less than 256 characters");
            taskSelected.setTaskDesc(Old);
        } else {
            taskSelected.setTaskDesc(New);
        }
        tableView.refresh();
    }

    /*
      This allows input added to the Editable Cell to have a format, therefore if it is not the right format, it wont work

          Create a new Task which is the selected table Row
          Create a New String which is the Task's Cell's new Value
          Create a Old String which is the Task's Cell's old Value

          Create an if statement to call the Date Validation method
          if(the New input is equal to "Completed" or "Incomplete")
              set the Cell to the New input
          else
              An alert box will alert the user about writing the wrong input
              set the Cell to the Old input
          refresh table
       */
    @FXML
    void changeTableProgress(TableColumn.CellEditEvent editedCell) {
        Task taskSelected =  tableView.getSelectionModel().getSelectedItem();
        String New = editedCell.getNewValue().toString();
        String Old = editedCell.getOldValue().toString();
        if(New.equals("Completed") || New.equals("Incomplete")){
            taskSelected.setProgress(New);
        } else {
            AlertBox.display("Error", "Task must either be Completed or Incomplete");
            taskSelected.setProgress(Old);
        }
        tableView.refresh();
    }

    @FXML
    void openFile(ActionEvent event) {

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
}