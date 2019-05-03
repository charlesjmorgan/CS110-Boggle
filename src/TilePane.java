import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.geometry.Pos;

public class TilePane extends HBox
{
   private String letter;
   private int row, col;
   
   public TilePane(Tile t)
   {
      letter = t.getLetterShowing();
      row = t.getRow();
      col = t.getCol();
      
      this.setAlignment(Pos.CENTER);
      this.setPrefSize(100,100);
      this.setStyle("-fx-border-width: 3;" +
                  "-fx-border-color: black;");
      Text l = new Text(50,50,letter);
      l.setFont(Font.font("Courier", FontWeight.BOLD,
                              FontPosture.REGULAR, 24));
      this.getChildren().add(l);
   }
   
   public String getLetter()
   {
      return letter;
   }
   
   public void setSelected()
   {
      this.setStyle("-fx-background-color: lightblue;" + 
                    "-fx-border-width: 3;" +
                    "-fx-border-color: black;");
   }
   
   public void setUnselected()
   {
      this.setStyle("-fx-background-color: white;" + 
                    "-fx-border-width: 3;" +
                    "-fx-border-color: black;");
   }
   
   public int getRow()
   {
      return row;
   }
   
   public int getCol()
   {
      return col;
   }
}