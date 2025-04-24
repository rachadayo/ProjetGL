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
	private void handleHomeButton() throws IOException {
		Parent startRoot = FXMLLoader.load(getClass().getResource("start.fxml"));

		Stage stage = (Stage) Home.getScene().getWindow();

		stage.setScene(new Scene(startRoot, 600, 600));
	}

}
