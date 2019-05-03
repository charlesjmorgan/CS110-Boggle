/*
Charles Morgan
CS110 Boggle

This class represents the board for the game boggle

Board

- static rand : Random
- board : ArrayList<ArrayList<Tile>>
- DIE1-10 : ArrayList<String>
*/

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Board
{
   private static Random rand = new Random();
   public static final int DICE_LENGTH = 16,
                            MAX_ROWS = 4,
                            MAX_COLS = 4,
                            SIDES = 6;

   private ArrayList<ArrayList<Tile>> board;
   
   private static final ArrayList<String>
      DIE0 = new ArrayList<>(Arrays.asList("R","I","F","O","B","X")),
      DIE1 = new ArrayList<>(Arrays.asList("I","F","E","H","E","Y")),
      DIE2 = new ArrayList<>(Arrays.asList("D","E","N","O","W","S")),
      DIE3 = new ArrayList<>(Arrays.asList("U","T","O","K","N","D")),
      DIE4 = new ArrayList<>(Arrays.asList("H","M","S","R","A","O")),
      DIE5 = new ArrayList<>(Arrays.asList("L","U","P","E","T","S")),
      DIE6 = new ArrayList<>(Arrays.asList("A","C","I","T","O","A")),
      DIE7 = new ArrayList<>(Arrays.asList("Y","L","G","K","U","E")),
      DIE8 = new ArrayList<>(Arrays.asList("Qu","B","M","J","O","A")),
      DIE9 = new ArrayList<>(Arrays.asList("E","H","I","S","P","N")),
      DIE10 = new ArrayList<>(Arrays.asList("V","E","T","I","G","N")),
      DIE11 = new ArrayList<>(Arrays.asList("B","A","L","I","Y","T")),
      DIE12 = new ArrayList<>(Arrays.asList("E","Z","A","V","N","D")),
      DIE13 = new ArrayList<>(Arrays.asList("R","A","L","E","S","C")),
      DIE14 = new ArrayList<>(Arrays.asList("U","W","I","L","R","G")),
      DIE15 = new ArrayList<>(Arrays.asList("P","A","C","E","M","D"));
                             
   private static final ArrayList<ArrayList<String>> DICE = new ArrayList<>
      (Arrays.asList(DIE0,DIE1,DIE2,DIE3,
                     DIE4,DIE5,DIE6,DIE7,
                     DIE8,DIE9,DIE10,DIE11,
                     DIE12,DIE13,DIE14,DIE15));
   
   /** Default Constructor Board takes no parameters and creates
       a 4x4 board using nested ArrayLists. The board consists
       of tile objects with random lettershowing chosen from
       the constant DICE array.
   */
   public Board()
   {
      board = new ArrayList<>(MAX_ROWS);
      
      // temporary deep copy of DICE ArrayList
      ArrayList<ArrayList<String>> temp = new ArrayList<>(DICE_LENGTH);
      
      // create deep copy
      for(ArrayList<String> die : DICE)
      {
         ArrayList<String> tempdie = new ArrayList<>(SIDES);
         for(int f = 0; f < SIDES; f++)
            tempdie.add(die.get(f));
         temp.add(tempdie);
      }
      
      // create 16 Tile objects with random letters showing from the temp ArrayList
      int ct = DICE_LENGTH;
      for (int i = 0; i < MAX_ROWS; i++)
      {
         ArrayList<Tile> row = new ArrayList<>(4);
         
         for (int j = 0; j < MAX_COLS; j++)
         {
            ArrayList<String> die = temp.get(rand.nextInt(ct));
            String l = die.get(rand.nextInt(SIDES));
            row.add(new Tile(l,i,j));  // letter, row, column
            temp.remove(die);  // remove die from temp so that it doesn't get repeated
            ct--;
         }
         board.add(row);
      }         
   }
   
   /** Method getTile has the row and column of a specific tile as params
       and returns a copy of that tile
       
       @param r is the row of the tile
       @param c is the column of the tile
       
       @return the specified Tile
   */
   public Tile getTile(int r, int c)
   {
      return new Tile(board.get(r).get(c));
   }
   
   /** Method getBoard returns an ArrayList containing the Tiles
       of the board 
       
       @return ArrayList of Tile objects that make up the board
   */
   public ArrayList<ArrayList<Tile>> getBoard()
   {
      ArrayList<ArrayList<Tile>> temp = new ArrayList<>();
      ArrayList<Tile> row;
      
      for (int i = 0; i < MAX_ROWS; i++)
      {
         row = new ArrayList<>();
         for (int j = 0; j < MAX_COLS; j++)
            row.add(new Tile(board.get(i).get(j)));
         temp.add(row);
      }
      
      return temp;
   }
   
   /** Method toString returns a string containing the letters showing
       on the tiles in the 4x4 board
       
       @return string containing letters showing on board
   */
   @Override
   public String toString()
   {
      StringBuilder sb = new StringBuilder();
      
      for (int i = 0; i < MAX_ROWS; i++)
      {
         for (int j = 0; j < MAX_COLS; j++)
            sb.append(board.get(i).get(j).getLetterShowing() + "\t");
         sb.append("\n");
      }
      return sb.toString();
   }
}
