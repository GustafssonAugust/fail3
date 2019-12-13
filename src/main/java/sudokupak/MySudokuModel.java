package sudokupak;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import ch.qos.logback.core.boolex.Matcher;

class MySudokuModel implements SudokuModel{
	
	private int [][] board = new int [9][9];
	public static final int EMPTY = 0; // empty cell
	public static final int SIZE = 9; 
	 public MySudokuModel() {
	       
	    }
	 	@Override
	public void clear() {
		for(int col = 0; col < board.length; col++)
		{
			for(int row = 0; row < board[col].length; row++)
			{
			this.board[row][col] = 0;
		}
		}
	}
	@Override
	public int[][] getBoard() {
		return this.board;
	}
	
    @Override
    public void setBoard(String input) {
 
    String replaceInput = input.replaceAll("\\.", "0");
    String testInput = replaceInput.replaceAll("\n", "");
 
    int charcount = 0;
    //Här nedan räknar vi antal tecken
    for (int i = 0, len = replaceInput.length(); i < len; i++) {
        if (Character.isDigit(replaceInput.charAt(i))) {
            charcount++;
        }
    }
    if (charcount != 81) {
        throw new IllegalArgumentException("Din string innehåller felaktigt antal tecken");
    }
    //Här nedan räknar vi antal rader
    String[] lines = replaceInput.split("\n");
    int linesCount = lines.length;
    if (linesCount != 9) {
        throw new IllegalArgumentException("Din string innehåller felaktigt antal rader");
        
    }    
    //Här nedan räknar vi antal tecken per rad
    for (int i = 0; i < 9; i++) {
        Pattern patt = Pattern.compile("\\d{9}");
        java.util.regex.Matcher matcher = patt.matcher(lines[i]);
         if (matcher.matches()) {
         }
         else {
            throw new IllegalArgumentException("Rad " + (i+1) + " har felaktigt antal tecken??!");
         }
    }
   
    int z = 0;
    for (int x=0; x<lines.length; x++) {
    for (int y = 0; y<lines.length; y++){
        board[x][y] = Character.getNumericValue(testInput.charAt(z));
        System.out.print(board[x][y] + " ");
        z++;
    }
    
    System.out.println();
    }
     
     System.out.println();  
     System.out.println(Arrays.deepToString(board));
    }
     
 	private boolean isInRow(int row, int number) {
		for (int i = 0; i < SIZE; i++)
			if (board[row][i] == number)
				return true;
		
		return false;
	}
	
	// we check if a possible number is already in a column
	private boolean isInCol(int col, int number) {
		for (int i = 0; i < SIZE; i++)
			if (board[i][col] == number)
				return true;
		
		return false;
	}
	
	// we check if a possible number is in its 3x3 box
	private boolean isInBox(int row, int col, int number) {
		int r = row - row % 3;
		int c = col - col % 3;
		
		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3; j++)
				if (board[i][j] == number)
					return true;
		
		return false;
	}
	
	
	public boolean isLegal(int row, int col, int val) {
		return !isInRow(row, val)  &&  !isInCol(col, val)  &&  !isInBox(row, col, val);
	}
   
   
   
  
 
    
	@Override
	public int getSquare(int row, int col) {
		// TODO Auto-generated method stub
		return board[row][col];
	}


	@Override
	public boolean setSquare(int row, int col, int val) {
		if (isLegal(row,col,val)) {
			this.board[row][col] = val;
		}
		// Kontrollerar om insatt värde är ok. annars return false.
		return false;
	}

@Override
public boolean solve() {
	
	  for (int row = 0; row < board.length; row++) {
          for (int col = 0; col <board.length; col++) {
              if (board[row][col] == EMPTY) {
                  for (int val =1 ; val <=9; val++) {
                      if (isLegal(row,col,val)) {
                          board[row][col]   = val;
                          if(solve()) {
                              return true;
                          }
                          else {
                              board[row][col] = EMPTY;
                          }
                      }
                  }
                  return false;
                 
              }
          }
      }
      return true;
  }
	@Override
	public SudokuModel copy() {
		 try {
 MySudokuModel copy = (MySudokuModel) super.clone();
 return copy;
} catch (CloneNotSupportedException e) {
   return null;
  }
}
//public static void main(String[] args) {
//	
//	MySudokuModel sModel= new MySudokuModel();	
//	System.out.println("Sudoku grid to solve");
//	//sModel.getBoard();
//	
//	String input = "..19....3" + "\n" + "9..7..16."+ "\n" + ".3...5..7" + "\n" + ".5......9" + "\n" + "..43.26.." + "\n" + "2......7." + "\n" + "6..1...3." + "\n" +  ".42..7..6" + "\n" + "5....68..";
//	sModel.setBoard(input);
//
//	// testar lösning.
//	if (sModel.solve()) {
//		System.out.println("Sudoku Grid solved with simple BT");
//		sModel.getBoard();
//	} else {
//		System.out.println("Unsolvable");
//	}
//}
}
