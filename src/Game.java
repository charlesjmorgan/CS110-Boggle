/*
Charles Morgan
CS110
Contains the main game code for Boggle

constructor must create board dictionary and word objects

Game

- board : Board
- word : Word
- selectedTiles : ArrayList<Tile>

+ Game()
+ isValidSelection
+ toString() : String
*/

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import java.util.ArrayList;

/** The Game class contains the core functionality for
    a game of boggle
*/
public class Game
{
   private ArrayList<Tile> selectedTiles;
   private int score = 0;
   private Board board;
   private ArrayList<Word> words;
   private Dictionary dict;
   private final int MIN_WORD_LEN = 3;
   private Tile lastSelected;
   
   /** Default Constructor creates a board and a dictionary object
       and the words and selectedTiles ArrayList
   */
   public Game()
   {
      board = new Board();
      dict = new Dictionary("dictionary.txt");
      words = new ArrayList<>();
      selectedTiles = new ArrayList<>();
   }
   
   /** Method isValidSelection tests whether the users selection
       is a valid move. A selection is valid if the difference
       between the row/col of the last selection and the new selection
       are both less than or equal to one.
       
       @param r the row of the users selection
       @param c the column of the users selection
       
       @return true if it is a valid selection, false otherwise
   */
   public boolean isValidSelection(int r, int c)
   {
      if (r < Board.MAX_ROWS && r >= 0 &&
            c < Board.MAX_COLS && c >= 0)
      {
         Tile selection = board.getTile(r,c);
         
         if (selectedTiles.isEmpty())
            return true;
         else
         {
            Tile last = selectedTiles.get(selectedTiles.size() -1);
            // valid tile if diff of both row and col is <=1
            if (Math.abs(last.getRow() - selection.getRow()) <= 1 
                  && Math.abs(last.getCol() - selection.getCol()) <= 1)
               return true;
         }
      }
      return false;
   }
   
   /** Method getSelectedTiles returns a copy of the
       selectedTiles ArrayList
       
       @return ArrayList of the user selected tiles
   */
   public ArrayList<Tile> getSelectedTiles()
   {
      return selectedTiles;
   }
   
   /** Method addToSelected adds the specified tile to the
       selectedTiles ArrayList
       
       @param r the row of the specified tile
       @param c the column of the specified tile
   */
   public void addToSelected(int r, int c)
   {
      lastSelected = board.getTile(r,c);
      selectedTiles.add(lastSelected);
   }
   
   /** Method removeFromSelected removes the specified tile
       from the selectedTiles ArrayList
       
       @param r the row of the specified tile
       @param c the column of the specified tile
   */
   public void removeFromSelected(int r, int c)
   {
      selectedTiles.remove(board.getTile(r,c));
      if (selectedTiles.size() > 0)
         lastSelected = selectedTiles.get(selectedTiles.size()-1);
      else
         lastSelected = null;
   }
   
   /** Method clearSelected clears the ArrayList of selected tiles
   
       @param gp is the GridPane containing the TilePanes
   */
   public void clearSelected(GridPane gp)
   {
      selectedTiles = new ArrayList<>();
      
      for (Node node : gp.getChildren())
         ((TilePane)node).setUnselected();
   }
   
   public Tile getLastSelected()
   {
      return lastSelected;
   }
   
   /** Method testSelected tests if the selected tiles
       create a valid word. A word is valid if it is in
       the dictionary. Must be 3 or more letters long.
       
       @param gp is the GridPane containing the TilePanes
       @return String containing message about selection
   */
   public String testSelected(GridPane gp, VBox wordsFound)
   {
      Word word = new Word(selectedTiles);
      
      // check if selectedTiles is empty
      if (!selectedTiles.isEmpty())
         if (selectedTiles.size() < MIN_WORD_LEN)
            return String.format("%s is too short", word.toString());
            
         // check if it is a valid word
         else if (dict.isValidWord(selectedTiles))
         {
            if (!words.contains(word))
            {
               words.add(word);
               score += word.getPoints();
               clearSelected(gp);
               Label wordStr = new Label(word.toString());
               wordsFound.getChildren().add(wordStr);
               return String.format("%s is a valid word!", word.toString());
            }
            else
               return String.format("%s has already been found!", word.toString());
         }
         else
            return String.format("%s is not a valid word", word.toString());
            
      return "You don't have any tiles selected!";
   }
   
   /** Method get tile returns the tile located at the given coordinates
       row, column
   
       @return the specified tile
   */
   public Tile getTile(int r, int c)
   {
      return board.getTile(r,c);
   }
   
   /** Method getScore returns the users score
   
       @return the users score
   */
   public String getScore()
   {
      return "" + score;
   }
   
   /** Method toString returns a string containing the board,
       selected tiles, past words, and the user's score
       
       @return string containing all relevant information
   */
   @Override
   public String toString()
   {
      return "Selected: " + selectedTiles;
   }
}
