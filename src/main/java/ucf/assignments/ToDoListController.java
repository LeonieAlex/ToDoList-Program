package ucf.assignments;

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

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
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

    final ObservableList<Task> task = FXCollections.observableArrayList();

    @FXML
    void MenuSave(ActionEvent event) {

    }

    @FXML
    void MenuSaveAs(ActionEvent event) {

    }

    @FXML
    void OnButtonClickDelete(ActionEvent event) {
        ObservableList<Task> selectedRows, allTask;
        allTask = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();

        for (Task task: selectedRows){
            allTask.remove(task);
        }
    }

    @FXML
    void OnButtonClickClear(ActionEvent event) {
        tableView.getItems().clear();
    }

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

            tableView.getItems().add(newTask);
        }
        Name.setText("");
        Description.setText("");
        Progress.setSelected(false);
        datePickerOption.setValue(null);
    }

    //Change set Progress but how????
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
    }

    @FXML
    void changeTableDescCellEvent(TableColumn.CellEditEvent editedCell) {
        Task taskSelected =  tableView.getSelectionModel().getSelectedItem();
        taskSelected.setTaskDesc(editedCell.getNewValue().toString());
    }

    //Error, change to checkbox
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
    }

    @FXML
    void openFile(ActionEvent event) {

    }

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

        //Issue cause of this i think, it's causing the buttons to not work
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

    public ObservableList<Task> getTask(){
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Completed"));
        task.add(new Task("Essay","For ENC1102", LocalDate.of(1952, Month.MAY, 21), "Completed"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Completed"));
        task.add(new Task("Hw","MATH", LocalDate.of(2002, Month.JUNE, 20), "Incomplete"));

        return task;
    }
}