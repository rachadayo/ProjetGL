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
	
	@FXML private Button btn00, btn01, btn02,
    btn10, btn11, btn12,
    btn20, btn21, btn22;
	
	private int emptyRow= 2;
	private int emptyCol=0;
	
	@FXML
	private void handleHomeButton(ActionEvent event) throws IOException {
	    System.out.println("Home button clicked!"); 
	    Parent startRoot = FXMLLoader.load(getClass().getResource("/application/start.fxml"));
	    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	    
	    stage.setScene(new Scene(startRoot, 600, 600));
	    stage.show();
	}
	
	
    @FXML
    public void initialize() {
        // Verify all buttons are injected
        if (btn00 == null || btn01 == null || btn02 == null || 
            btn10 == null || btn11 == null || btn12 == null ||
            btn20 == null || btn21 == null || btn22 == null) {
            System.err.println("ERROR: Some buttons weren't injected properly!");
            return;
        }

        // Set up button actions
        btn00.setOnAction(e -> handleTileClick(0, 0));
        btn01.setOnAction(e -> handleTileClick(0, 1));
        btn02.setOnAction(e -> handleTileClick(0, 2));
        btn10.setOnAction(e -> handleTileClick(1, 0));
        btn11.setOnAction(e -> handleTileClick(1, 1));
        btn12.setOnAction(e -> handleTileClick(1, 2));
        btn20.setOnAction(e -> handleTileClick(2, 0));
        btn21.setOnAction(e -> handleTileClick(2, 1));
        btn22.setOnAction(e -> handleTileClick(2, 2));
        
        // Hide empty tile
        btn20.setVisible(false);
    }

    private void handleTileClick(int row, int col) {
        System.out.println("Clicked: " + row + "," + col);
        if (isAdjacent(row, col)) {
            swapTiles(row, col);
        }
    }

    private boolean isAdjacent(int row, int col) {
        return (Math.abs(row - emptyRow) + Math.abs(col - emptyCol)) == 1;
    }

    private void swapTiles(int row, int col) {
        Button clicked = getButtonAt(row, col);
        Button empty = getButtonAt(emptyRow, emptyCol);
        
        // Swap text
        String temp = clicked.getText();
        clicked.setText(empty.getText());
        empty.setText(temp);
        
        // Swap visibility
        clicked.setVisible(false);
        empty.setVisible(true);
        
        // Update empty position
        emptyRow = row;
        emptyCol = col;
    }

    private Button getButtonAt(int row, int col) {
        switch(row) {
            case 0: 
                switch(col) {
                    case 0: return btn00;
                    case 1: return btn01;
                    case 2: return btn02;
                }
            case 1:
                switch(col) {
                    case 0: return btn10;
                    case 1: return btn11;
                    case 2: return btn12;
                }
            case 2:
                switch(col) {
                    case 0: return btn20;
                    case 1: return btn21;
                    case 2: return btn22;
                }
        }
        return null;
    }
}
