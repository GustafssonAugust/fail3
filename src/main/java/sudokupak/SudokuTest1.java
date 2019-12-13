package sudokupak;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class SudokuTest1 {   
  public static void main(String[] arg) throws FileNotFoundException {
    JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
    while (true) {
      SudokuModel mod = new MySudokuModel();
      String filename = null;
      File f = null;
      if (dialog.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
         f = dialog.getSelectedFile();
      else
        System.exit(0);
      Scanner sc = new Scanner(f);
      String input = "";
      while (sc.hasNextLine())
        input += sc.nextLine() + '\n'; 
      System.out.println(f.getName() + ": " +  "\n" + input);   
      try {
        mod.setBoard(input);
        if (!mod.isUnique()) {
           System.out.println("Illegal Sudoku puzzle. Multiple solutions");
        }
        else {
          mod.solve();     
          System.out.println(f.getName() + " Solution: ");
          int[][] m = mod.getBoard(); 
          for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
               int v = mod.getSquare(i,j);
               if (v != m[i][j]) 
                  System.out.println("Error. Methods getBoard and getSquare return different values");
               if (v != 0)
                 System.out.print(v);
               else 
                 System.out.print('.');
            }
            System.out.println();
          }
        }
      }      
      catch (IllegalArgumentException e) {
          System.out.println("Illegal Sudoku puzzle. " + e.getMessage());
      }
      System.out.println("========================================================"); 
    }  
  }
}