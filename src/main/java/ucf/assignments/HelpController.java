package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/*
This class is to Control Help.fxml

NextButton function (created for the button lol, makes it look nice){
     Create a Parent which loads HelpPage2
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
public class HelpController {

    @FXML
    private Button NextButton;

    @FXML
    private Button Back;

    @FXML
    void NextButton(ActionEvent event) throws IOException {
        Parent AddNewTask = FXMLLoader.load(getClass().getResource("HelpPage2.fxml"));
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
