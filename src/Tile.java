/*
Charles Morgan
CS110 Boggle

Tile class represents one tile on the board of a game of boggle

                 Tile
---------------------------------------
   - letterShowing : String
   - row : int
   - col : int
   - selected : boolean
---------------------------------------
   + Tile(String l,int r, int c)
   + Tile(Tile other)
   + setLetterShowing(String l) : void
   - setRow(int r) : void
   - setColumn(int c) : void
   + setSelected(boolean s) : void
   + getLetterShowing() : String
   + getRow() : int
   + getColumn() : int
   + getSelected() : boolean
   + toString() : String

*/

public class Tile
{
   private String letterShowing;  // letter showing on tile
   private int row, col;  // location of the tile
   private boolean selected = false;  // state of the tile
   
   /** Default Constructor creates a tile with letter equal to "_" and
       row and col equal to -1
   */
   public Tile()
   {
      this("_",-1,-1);
   }
   
   /** Copy Constructor takes Tile other and returns a new instance of
       Tile with all the data in the given Tile object
       
       @param other is the Tile object to be copied
   */
   public Tile(Tile other)
   {
      this(other.getLetterShowing(),other.getRow(),other.getCol());
   }
   
   /** Alternate Constructor takes ints r (row) and c (column)
       and char l (letter showing) it then sets row, col, and
       letterShowing to these values
       
       @param c is the letter showing on the tile
       @param r is the row of the tile
       @param c is the column of the tile
   */
   public Tile(char l,int r,int c)
   {
      this(Character.toString(l),r,c);
   }
   
   /** Alternate Constructor takes ints r (row) and c (column)
       and String l (letter showing) it then sets row, col, and
       letterShowing to these values
       
       @param l is the letter showing on the tile
       @param r is the row of the tile
       @param c is the column of the tile
   */
   public Tile(String l,int r,int c)
   {
      this.setLetterShowing(l);
      this.setRow(r);
      this.setCol(c);
   }
   
   /** Method setLetterShowing sets the letter showing on the tile
       to the given String
       
       @param l the letter showing on the tile
   */
   public void setLetterShowing(String l)
   {
      letterShowing = l;
   }
   
   /** Method setRow sets the row of the tile
   
       @param r the row of the tile
   */
   private void setRow(int r)
   {
      row = r;
   }
   
   /** Method setCol sets the column of the tile
   
       @param c the column of the tile
   */
   private void setCol(int c)
   {
      col = c;
   }
   
   /** Method setSelected sets the selection state
       of the tile
       
       @param s the selection state of the tile
   */
   public void setSelected(boolean s)
   {
      selected = s;
   }
   
   /** Method getSelected a boolean true if the tile
       has been selected and false otherwise
       
       @return true if selected false otherwise
   */
   public boolean getSelected()
   {
      return selected;
   }
   
   /** Method getRow returns the row of the tile
       
       @return the row of the tile
   */
   public int getRow()
   {
      return row;
   }
   
   /** Method getCol returns the column of the tile
   
       @return the column of the tile
   */
   public int getCol()
   {
      return col;
   }
   
   /** Method getLetterShowing returns the letter showing
       on the tile
       
       @return the letter showing on the tile
   */
   public String getLetterShowing()
   {
      return letterShowing;
   }
   
   /** Method equals tests the equivalence of two Tile
       objects. A Tile is equal if the row, col, and 
       letter are all equal
       
       @param other the other Tile to be compared to
       @return true if the tiles are equal, false otherwise
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
         
      Tile otherT = (Tile)other;
      
      return this.row == otherT.getRow() &&
               this.col == otherT.getCol() &&
               this.letterShowing.equals(otherT.getLetterShowing());
   }
   
   /** Method toString returns a string containing the
       letter showing on the tile
       
       @return the letter showing on the tile
   */
   @Override
   public String toString()
   {
      return letterShowing;
   }
   
   /** Testing
   */
   public static void main(String [] args)
   {
      Tile t1 = new Tile("a",0,0);
      
      System.out.println(t1);
      System.out.println(t1.getSelected());
      System.out.printf("(%d,%d)\n",t1.getCol(),t1.getRow());
   }
}