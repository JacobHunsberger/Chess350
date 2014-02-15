package model;

/**
 * A player of a chess game.
 * @author Jonathan Powers, Jacob Hunsberger and Jared Thomas
 */
 
/**
 * Enum Player, the color can be black or white.
 */
public enum Player {
   /**
    * Black Player.
    */
   BLACK, 
   /**
    * White Player.
    */
   WHITE,
   /**
    * Blank, (null) Player.
    */
   BLANK;

   /**
    * Return the Player whose turn is next.
    * @return the Player whose turn is next
    */
   public Player next() {
      if (this == BLACK) { 
    	  return WHITE; }
      else {
    	  return BLACK; } 
   }
}