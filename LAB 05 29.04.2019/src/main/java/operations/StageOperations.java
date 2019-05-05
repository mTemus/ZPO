package operations;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageOperations {

    public void changeSceneToReflections(ActionEvent event) throws IOException {
        Parent EventViewParent = FXMLLoader.load(getClass().getResource("/reflectionsView.fxml"));
        Scene eventScene = new Scene(EventViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(eventScene);
        window.setTitle("Reflections panel");
        window.show();
    }

    public void changeSceneToLoadClass(ActionEvent event) throws IOException {
        Parent EventViewParent = FXMLLoader.load(getClass().getResource("/loadClassView.fxml"));
        Scene eventScene = new Scene(EventViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(eventScene);
        window.setTitle("Choose a class");
        window.show();
    }


}
