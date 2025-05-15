package application;

import javafx.event.ActionEvent;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.*;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;





public class GameController {

    @FXML private Button btn00, btn01, btn02,
                         btn10, btn11, btn12,
                         btn20, btn21, btn22;

    @FXML private Label scoreLabel;
    @FXML private Label bestScoreLabel;
    @FXML private Label movesLeftLabel;
    @FXML private Label timerLabel;
    
    private int emptyRow = 2;
    private int emptyCol = 0;
    private int movesCount = 0;
    private int score = 0;
    private int bestScore ;
    private final int maxMoves = 90; 
    private int movesLeft; 
    
   
    private int seconds=0;
    private int minutes=0;
    private int hours=0;
    private Timeline timeline;
    
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    private static final List<String> PUZZLE_CONFIGS = Arrays.asList(
            "073214568", "124857063 ", "204153876", 
             "280163547", "781635240", "120453786"
        );
    @FXML
    private void handleHomeButton(ActionEvent event) throws IOException {
        Parent startRoot = FXMLLoader.load(getClass().getResource("/application/start.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(startRoot, 600, 600));
        stage.show();
    }

    // Initialization Of the game
    @FXML
    public void initialize() {
        if (btn00 == null || btn01 == null || btn02 == null || 
            btn10 == null || btn11 == null || btn12 == null ||
            btn20 == null || btn21 == null || btn22 == null) {
            System.err.println("ERROR: Some buttons weren't injected properly!");
            getButtonAt(emptyRow, emptyCol).setVisible(false);
            return;
        }
        
      

        btn00.setOnAction(e -> handleTileClick(0, 0));
        btn01.setOnAction(e -> handleTileClick(0, 1));
        btn02.setOnAction(e -> handleTileClick(0, 2));
        btn10.setOnAction(e -> handleTileClick(1, 0));
        btn11.setOnAction(e -> handleTileClick(1, 1));
        btn12.setOnAction(e -> handleTileClick(1, 2));
        btn20.setOnAction(e -> handleTileClick(2, 0));
        btn21.setOnAction(e -> handleTileClick(2, 1));
        btn22.setOnAction(e -> handleTileClick(2, 2));

        movesCount = 0;
        score = 0;
        movesLeft = maxMoves;
      
        updateScore();
        updateMovesLeft();
        
        btn20.setVisible(false);
        
        //TIMER 
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            seconds++;
            if (seconds == 60) {
                seconds = 0;
                minutes++;
            }
            if (minutes == 60) {
                minutes = 0;
                hours++;
            }

            String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            timerLabel.setText(timeString);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    // Moving the tile
    private void handleTileClick(int row, int col) {
        System.out.println("Clicked: " + row + "," + col);
        if (isAdjacent(row, col)) {
            swapTiles(row, col);
            movesCount++;
            movesLeft--;
            score += 10;
        
            updateScore();
            updateMovesLeft();
            
            if (isSolved()) {
                showWinAlert();
        }}
    }

    private boolean isAdjacent(int row, int col) {
        return (Math.abs(row - emptyRow) + Math.abs(col - emptyCol)) == 1;
    }

    private void swapTiles(int row, int col) {
        Button clicked = getButtonAt(row, col);
        Button empty = getButtonAt(emptyRow, emptyCol);
        
        String temp = clicked.getText();
        clicked.setText(empty.getText());
        empty.setText(temp);
        
        clicked.setVisible(false);
        empty.setVisible(true);
        
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

  
    private void updateScore() {
    	scoreLabel.setText("Score : " + score);
    	if(isSolved()) {
    		if (score > bestScore) {
                bestScore = score;
                bestScoreLabel.setText("Best score : " + bestScore);
            }
    	} 
    }
  
   
    private void updateMovesLeft() {
        movesLeftLabel.setText("Moves left : " + movesLeft);
        
        if (movesLeft <= 5) {
            movesLeftLabel.setStyle("-fx-text-fill: #FF0000; -fx-font-weight: bold;");
        } else if (movesLeft <= 10) {
            movesLeftLabel.setStyle("-fx-text-fill: #FF4500; -fx-font-weight: bold;");
        } else {
            movesLeftLabel.setStyle("-fx-text-fill: #880E4F;");
        }
        
        if (movesLeft <= 0) {
            showGameOverAlert();
        }
    }

    private boolean isSolved() {
        return btn00.getText().equals("1") &&
               btn01.getText().equals("2") &&
               btn02.getText().equals("3") &&
               btn10.getText().equals("4") &&
               btn11.getText().equals("5") &&
               btn12.getText().equals("6") &&
               btn20.getText().equals("7") &&
               btn21.getText().equals("8") &&
               btn22.getText().isEmpty(); 
        }
    private void showWinAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Félicitations !");
        alert.setHeaderText("Puzzle résolu !");
        alert.setContentText("Score: " + score + "\nMouvements: " + movesCount);
        alert.showAndWait();
       
    }

    private void showGameOverAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Game Over");
        alert.setHeaderText("Vous avez dépassé le nombre de mouvements autorisés !");
        alert.setContentText("Final score: " + score);
        alert.showAndWait();
    }

    // Configirations to play with
    @FXML
    private void shufflePuzzle() {
    	System.out.println("shuflle..");
        Collections.shuffle(PUZZLE_CONFIGS);
        loadPuzzleConfig(PUZZLE_CONFIGS.get(0));
        movesCount = 0;
        score = 0;
        movesLeft = maxMoves;
        updateScore();
        updateMovesLeft();
        
    }

    
    // rearrange the puzzle
   
    private void loadPuzzleConfig(String config) {
    	int index = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button btn = getButtonAt(row, col);
                char c = config.charAt(index++);
                if (c == '0') {
                    btn.setText("");
                    btn.setVisible(false);
                    emptyRow = row;
                    emptyCol = col;
                } else {
                    btn.setText(String.valueOf(c));
                    btn.setVisible(true);
                }
            }
        }
    }
    
    // load the config
    private void rearrangingConfig(String config) {
      switch (config) {
      
      case "124857063":
      case "073214568":
      case "204153876":
      case "280163547":
      case "781635240":
      case "120453786":
        	   
    	  System.out.println("rearranging the puzzle... ");
           loadPuzzleConfig("124857063");
    	  break; 
          }
     }
     
    // set the action
   @FXML
   private void rearrange(ActionEvent event) {
   	String config = "124857063";
   	rearrangingConfig(config);
   }
   
    
 

    
    
}