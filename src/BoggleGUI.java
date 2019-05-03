import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.text.Font;

public class BoggleGUI extends Application
{
   public static final int WIDTH = 580, HEIGHT = 500;
   private Game boggle;
   private Scene mainScene, endScene;
   
   /** Method mainScreen creates the main screen for the game
       and sets it as the current scene
       
       @param stage is the stage for the game
   */
   public void mainScreen(Stage stage)
   {
      boggle = new Game();
      BorderPane bp = new BorderPane();
      GridPane gp = new GridPane();
      HBox infoPane = new HBox(20);
      VBox dynamicPane = new VBox(5);
      VBox wordsFound = new VBox(1);
      wordsFound.getChildren().clear();
      Label msg = new Label("Welcome to my game of boggle!");
      Label score = new Label(boggle.getScore());
      Label selected = new Label(boggle.toString());
      
      TilePane temp;
      for (int i = 0; i < Board.MAX_ROWS; i++)
         for (int j = 0; j < Board.MAX_COLS; j++)
         {
            temp = new TilePane(boggle.getTile(i,j));
            gp.add(temp,i,j);
            temp.setOnMouseClicked(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent me)
               {
                  TilePane tp = (TilePane)(me.getSource());
                  int row, col;
                  row = tp.getRow();
                  col = tp.getCol();
                  
                  if (!boggle.getTile(row, col).getSelected())
                     if (boggle.isValidSelection(row, col))
                     {
                        if (boggle.getSelectedTiles().contains(boggle.getTile(row, col)))
                        {
                           if (boggle.getTile(row, col).equals(boggle.getLastSelected()))
                           {
                              boggle.removeFromSelected(row, col);
                              tp.setUnselected();
                              msg.setText(String.format("%s has been unselected", tp.getLetter()));
                              selected.setText(boggle.toString());
                           }
                           else
                              msg.setText("You must deselect tiles starting at the most recently selected one");
                        }
                        else
                        {
                           boggle.addToSelected(row, col);
                           tp.setSelected();
                           msg.setText(String.format("%s has been selected", tp.getLetter()));
                           selected.setText(boggle.toString());
                        }
                     }
                     else
                     {
                        if (boggle.getSelectedTiles().contains(boggle.getTile(row, col)))
                           msg.setText("You must deselect tiles starting at the most recently selected one");
                        else
                           msg.setText("Invalid selection!\nPlease select a letter adjacent to the previously selected letter.");
                     }
                  }
                      
               });
         }
         
      Button test = new Button("Test Selected");
      test.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent e)
         {
            msg.setText(boggle.testSelected(gp, wordsFound));
            score.setText(boggle.getScore());
            selected.setText(boggle.toString());
         }
      });
      
      Button clear = new Button("Clear Selected");
      clear.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent e)
         {
            msg.setText("Selection Cleared");
            boggle.clearSelected(gp);
            selected.setText(boggle.toString());
         }
      });
      
      Button exit = new Button("Exit");
      exit.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent e)
         {
            endScreen(stage);
         }
      });
      
      dynamicPane.getChildren().add(selected);
      dynamicPane.getChildren().add(msg);
      dynamicPane.getChildren().add(test);
      dynamicPane.getChildren().add(clear);
      dynamicPane.getChildren().add(exit);
      dynamicPane.setAlignment(Pos.CENTER);
      dynamicPane.setPadding(new Insets(0,0,10,0));
       
      Label words = new Label("Words Found");
      wordsFound.getChildren().add(words);
      wordsFound.setPadding(new Insets(10,10,30,10));
      
      Label scoreLab = new Label("Score:");
      infoPane.getChildren().add(scoreLab);
      infoPane.getChildren().add(score);
      infoPane.setPadding(new Insets(10,10,30,10));
      
      bp.setRight(infoPane);
      bp.setLeft(wordsFound);
      bp.setCenter(gp);
      bp.setBottom(dynamicPane);
      
      bp.setAlignment(gp, Pos.CENTER);
      bp.setAlignment(infoPane, Pos.TOP_CENTER);
      bp.setAlignment(dynamicPane, Pos.BOTTOM_CENTER);
      
      mainScene = new Scene(bp, WIDTH, HEIGHT);
      
      stage.setScene(mainScene);
   }
   
   /** Method endScreen creates the end screen for the game
       and sets it as the current scene
       
       @param stage is the stage for the game
   */
   public void endScreen(Stage stage)
   {
      Button playAgain = new Button("Play Again?");
      playAgain.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent e)
         {
            mainScreen(stage);
         }
      });
      
      Label endText = new Label(String.format("Your final score was: %s\nThank you for playing!", boggle.getScore()));
      endText.setFont(new Font(30));
      
      VBox endPane = new VBox();
      endPane.setAlignment(Pos.CENTER);
      
      endPane.getChildren().add(playAgain);
      endPane.getChildren().add(endText);
      
      endScene = new Scene(endPane, WIDTH, HEIGHT);
      
      stage.setScene(endScene);
   }
   
   @Override
   public void start(Stage stage)
   {
      mainScreen(stage);
      stage.setTitle("Charles' Game of Boggle");
      stage.show();
   }
   
   public static void main(String [] args)
   {
      launch(args);
   }
}