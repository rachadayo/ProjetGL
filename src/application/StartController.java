package application;

import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {

	@FXML
	private Button startButton;

	@FXML
	private void handleStartButton(ActionEvent event) throws IOException {
		Parent gameRoot = FXMLLoader.load(getClass().getResource("game.fxml"));
		Stage stage = (Stage) startButton.getScene().getWindow();
		stage.setScene(new Scene(gameRoot));
		Scene scene = new Scene(gameRoot, 600, 600);
		stage.setResizable(false);
		stage.show();
	}
}
