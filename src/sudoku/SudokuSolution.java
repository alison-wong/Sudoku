package sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SudokuSolution {

	int[][] AnswerArray = new int[9][9]; //holds the answers to Sudoku, as loaded from the loaded file.
	int answersX; //the columns of the sudoku answers
	int answersY; //the rows of the sudoku answers
	Scanner inFile; //create scanner
	Sudoku Sudoku; //holds the Sudoku instance created 

	



	/*  CONSTRUCTOR
	 * 
	 * Takes in a file name and makes sudoku with it
	 */
	public SudokuSolution(String string, Sudoku s)
	{
		Sudoku = s;
		File filename = new File(string);
		inFile = createFileReadingThingies(filename);
		this.readRows(AnswerArray);
	}
	
	public SudokuSolution(Sudoku s)
	{
		Sudoku = s;
	}

	
	
    
//----STUFF HEREBY RELATED TO READING IN THE ANSWER FILE INTO THE "ANSWER ARRAY"----
	

	Scanner createFileReadingThingies (File filename)
	{

		try{
			Scanner inFile = 

					new Scanner(
							new BufferedReader(new FileReader (filename)));
			return inFile;

		}
		
		//In case there is no file loaded.
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(Sudoku.sudokupanel, "Please Choose A File!");
			Sudoku.CheckButton.setEnabled(false);
			Sudoku.NextGameButton.setEnabled(false);
			Sudoku.RevealSolutionButton.setEnabled(false);
			return null;
		}	 

	}

	/**READ ROWS
	 * 
	 * Scanner reads rows & does appropriate actions.
	 * 
	 */
	void readRows(int[][] AnswerArray)
	{

		/*Read First 9 Rows (up to @)
		 *
		 */
		if (inFile.hasNextInt() == true)

			for (int x = 0;x < AnswerArray.length;x++)

				for (int y = 0; y < AnswerArray.length;y++)
				{
					AnswerArray[x][y] = inFile.nextInt();
				}	


		empty(); //Empty JTextFields
		
		readRestOfRows(); //Read from @ on.

	}
	
	/**Scanner reads file from @ on.
	 * 	1. sets the JTextFields in which the file specifies in format row column
	 *  after the @ as the corresponding value in Answer Array.
	 * 
	 * @param takes in nothing
	 * @return returns nothing
	 */

	void readRestOfRows()
	{
		int A = 0;
		int B = 0;
		
		//Skip @ here.
		inFile.next();	
		
		
		try{
			
		
		if (inFile.hasNextInt() == true)

			for (int x = 0;x < AnswerArray.length;x++)

				for (int y = 0; y < AnswerArray.length;y++)
				{
					 A = inFile.nextInt();
					
					if (inFile.hasNextInt() == true)
	
					{ B = inFile.nextInt();}
					
					System.out.println(A + " " + B + "   "+ AnswerArray[A][B]);
					
					Sudoku.setValue(A,B, AnswerArray[A][B]);					
				}	
			}
		
		//Catch loop takes care of the end of the doc.
		catch (NoSuchElementException e)
		{
			System.out.println("crap");
		}

	}

//-----------------------------------------------------------------------------------------

/**EMPTY EMPTIES TEXTBOXES
 *
 * walks over each JTextField, emptying it.
 * 
 * @param takes in no parameters.
 * @return returns nothing.
 */

	void empty()
	{
		for (int x = 0; x < 9; x++)

			for (int y = 0; y < 9; y++)

				Sudoku.setValue(x, y, 0);
	}



    
    
	/**Get Correct Value gets the correct answer from Answer Array from a specified row and column
	 *
	 * @param row must be an int from 0 to 8
	 * @param column must be an int from 0 to 8
	 *
	 * @return returns the corresponding integer (the correct answer of that position) from the Answer Array.
	 */
	public int getCorrectValue (int row, int col)
	{
		return AnswerArray[row][col];
	}


	/** REVEAL SOLUTION, CALLED FROM REVEAL SOLUTION IN SUDOKU
	 * 		reveals the current Sudoku Solution.
	 * 
	 * 1. Walks through AnswerArray and uses setValue from the Sudoku Class
	 *    to set each textbox the corresponding number in Answer Array.
	 * 2. Enables NextGameButton for the Next Game.
	 * 
	 * @param takes in no parameters
	 * @return return nosthing
	 */
	void revealSolution()
	{
		for (int x = 0; x < AnswerArray.length; x++)

			for (int y = 0; y < AnswerArray.length; y++)
			{
				Sudoku.setValue(x,y,AnswerArray[x][y]);
				

			}
		
		//Enables Next Game Butotn.
		Sudoku.NextGameButton.setEnabled(true);
	}


	//close class envelope
}

