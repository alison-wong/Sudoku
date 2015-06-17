package sudoku;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


public class Sudoku3By3Test {
	
	@Before
	public void setUp() throws Exception 
	{
		
	}

	@Test
	public void testSetValue() {
		Sudoku3By3 sudoku3by3 =  new Sudoku3By3();
		//Invoke setValue, using the SudokuArray in Sudoku3By#.
		sudoku3by3.setValue(0, 0, 1);
		assertEquals("1",sudoku3by3.SudokuArray[0][0].getText());
	}

}


