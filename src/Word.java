/*
Charles Morgan
CS110 Boggle

This class represents a word entered by the user

Word

- word : String
- points : int
- tiles : ArrayList<Tile>

+ Word(ArrayList t)
+ setWord() : void
+ setTiles(ArrayList t) : void
+ getWord() : String
+ getPoints() : int
- calcPoints() : void
+ toString() : String
+ equals(Word other) : boolean
*/

import java.util.ArrayList;

public class Word
{
   private String word;
   private int points;
   private ArrayList<Tile> tiles;  // tiles containing word
   
   /** Copy Constructor takes a Word object as a param and
       creates a new Word object with the data from the other
       
       @param other is the Word object to be copied
   */
   public Word(Word other)
   {
      this(other.tiles);
   }
   
   /** Alternate Constructor takes an ArrayList of Tiles as
       a param and then sets the tiles, word and points
       
       @param t is an ArrayList of tiles containing the word
   */
   public Word(ArrayList<Tile> t)
   {
      this.setTiles(t);
      this.setWord();
   }
   
   /** Method setWord uses the ArrayList of Tiles to set the word
       it then calls the calcPoints method to calculate the points
       of the given word
   */
   private void setWord()
   {
      word = "";
      for (Tile tile : tiles)
         word += tile.getLetterShowing();
      
      this.calcPoints();
   }
   
   /** Method calcPoints calculates the points of the stored word
   */
   private void calcPoints()
   {
      int len = word.length();
      if (len >= 3)
         switch (len)
         {
            case 3:
               points = 1;
               break;
            case 4:
               points = 1;
               break;
            case 5:
               points = 2;
               break;
            case 6:
               points = 3;
               break;
            case 7:
               points = 5;
               break;
            default:
               points = 11;
         }
   }
   
   /** Method setTiles takes an ArrayList of Tiles as a param
       and then copies every tile and adds it to the ArrayList
       tiles
       
       @param t is an ArrayList of Tiles
   */
   public void setTiles(ArrayList<Tile> t)
   {
      tiles = new ArrayList<Tile>();
      
      for (Tile tile : t)
         tiles.add(new Tile(tile));
   }
   
   /** Method getWord returns the stored value for word
   
       @return the stored word
   */
   public String getWord()
   {
      return word;
   }
   
   /** Method getPoints returns the points of the word
   
       @return points value associated with word
   */
   public int getPoints()
   {
      return points;
   }
   
   /** Method toString returns a string containing the word
       
       @return String containing the word
   */
   @Override
   public String toString()
   {
      return this.getWord();
   }
   
   /** Method equals compares two instances of Word
   
       @return true if the word is equal, false otherwise
   */
   @Override
   public boolean equals(Object other)
   {
      if (other == null)
         return false;
      if (this == other)
         return true;
      if (this.getClass() != other.getClass())
         return false;
         
      Word otherWord = (Word)other;
      
      return this.word.equals(otherWord.word);
   }
}