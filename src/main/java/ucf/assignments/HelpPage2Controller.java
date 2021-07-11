package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

/*
This class is to Control HelpPage2Controller.fxml

OnClickBack function (created for the button lol, makes it look nice. Same thing as HelpController.java){
     Create a Parent which loads Help
     Create a Scene which connects to the Parent
     Create a Stage
     set Scene
     Show
}
OnClickBackToMain function (exactly the same thing as the function above){
     Create a Parent which loads ToDoList.fxml
     Create a Scene which connects to the Parent
     Create a Stage
     set Scene
     Show
}
 */
public class HelpPage2Controller {

    @FXML
    private TableView<?> tableView;

    @FXML
    private Button BackToMain;

    @FXML
    private Button Back;

    @FXML
    void OnClickBack(ActionEvent event) throws IOException {
        Parent AddNewTask = FXMLLoader.load(getClass().getResource("Help.fxml"));
        Scene AddNewScene = new Scene(AddNewTask);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(AddNewScene);
        window.show();
    }

    @FXML
    void OnClickBackToMain(ActionEvent event) throws IOException {
        Parent AddNewTask = FXMLLoader.load(getClass().getResource("ToDoList.fxml"));
        Scene AddNewScene = new Scene(AddNewTask);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(AddNewScene);
        window.show();
    }

}
