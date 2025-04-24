package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
			Scene scene = new Scene(root, 600, 600);
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
			stage.setScene(scene);
			stage.setTitle("Slinding Puzzle");
			stage.setResizable(false);
			stage.centerOnScreen();
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
