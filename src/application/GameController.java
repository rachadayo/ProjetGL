package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.io.IOException;

import javafx.animation.*;

public class GameController {

	 @FXML
	    private Button Home; 
@FXML
	    private void handleHomeButton(ActionEvent event) throws IOException {
	       
	        Parent root = FXMLLoader.load(getClass().getResource("/application/start.fxml"));
	        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	        stage.setScene(new Scene(root));
	}

}
