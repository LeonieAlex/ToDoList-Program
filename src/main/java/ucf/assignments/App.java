package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
This function is used to run the app
start function
    Create a Parent which loads an fxml file
    set scene
    show
main
    launch
 */
public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ToDoList.fxml"));
        stage.setScene(new Scene(root,800, 500));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

