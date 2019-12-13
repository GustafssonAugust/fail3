package sudokupak;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sudokupak.MySudokuModel;

@RestController
public class SudokuController {

@Autowired
private SudokuModel sudoku;


@GetMapping("/new")
public void newBoard() {
	sudoku.getBoard();
	
}


@GetMapping("/reset")
public void clear() {
	sudoku.clear();
	
}	

@GetMapping("/solve")
public boolean solve() {
	return sudoku.solve();
}
@RequestMapping(value = "/input", method = RequestMethod.POST)
public void input(@RequestParam String input,
		@RequestParam("row") int row,
		@RequestParam("col") int col,
		@RequestParam("val") int val){
	
	
	
		
	}
}


