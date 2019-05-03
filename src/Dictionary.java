/*
Charles Morgan
CS110 Boggle

This class represents a dictionary of words

Dictionary

- dict : ArrayList<String>

+ Dictionary(String f)
+ isValidWord(ArrayList<Tile>) : boolean

*/

import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class Dictionary
{
   private ArrayList<String> dict;
   
   /** Alternate Constructor Dictionary takes a String containing a filename
       as input, it then attempts to open that file and read the contents into
       an ArrayList of strings (the dictionary)
   */
   public Dictionary(String filename)
   {
      try {
         Scanner infile = new Scanner(new File(filename));
         dict = new ArrayList<String>();
         
         while(infile.hasNext())
            dict.add(infile.nextLine());
         
         infile.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   /** Method isValidWord has parameter tiles which is an ArrayList of Tile
       objects. It iterates through the ArrayList and adds the letter showing
       of each Tile object to a string and then tests if the word is in
       the dictionary
       
       @return true if word is in dictionary, false otherwise
   */
   public boolean isValidWord(ArrayList<Tile> tiles)
   {
      String word = "";
      for (Tile tile : tiles)
         word += tile.getLetterShowing();
         
      return dict.contains(word.toLowerCase());
   }
}