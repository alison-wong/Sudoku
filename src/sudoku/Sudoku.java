package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
/**
 * Sudoku- run this class
 */
public class Sudoku extends JComponent implements ActionListener {
	
	
		
	/**Default Serial VersionUID
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/* Width of the frame */
	private static final int FRAME_WIDTH = 800;

	/* Height of the frame */
	private static final int FRAME_HEIGHT = 450;


	//JPanel with Buttons on it.
	static JPanel buttonpanel = new JPanel();
	
	//JPanel with OuterSudokuSquare on it.
	static JPanel sudokupanel = new Sudoku3By3();
	
	//Sudoku
	static Sudoku sudoku;
	
	//Array of Sudoku3By3s.
	static Sudoku3By3[][] OuterGrid= new Sudoku3By3[3][3];
	
	//Buttons
	static JButton CheckButton = new JButton("Check");
	static JButton NextGameButton = new JButton("Next Game");
	static JButton RevealSolutionButton = new JButton("Reveal Solution");
	
	
	//Current Solution
	SudokuSolution sudokusolution;
	
	//solution #
	int SolutionNumber = 0;
	
	//-------End variables-----
	

	/**
	 * CONSTRUCTOR
	 * 
	 * 1. Adds action listeners
	 * 2. disables NextGame Button
	 * 
	 * @param takes in nothing
	 * @constructs a Sudoku object
	 * 
	 */
	
	public Sudoku()
	{
		this.addActionListeners();		
		NextGameButton.setEnabled(false); //DISABLE NEXT GAME BUTTON
	}
	
	
	
	
	
	/** ADDS ACTION LISTENERS
	 * 
	 * @param take in no parameters
	 * @return return nothing
	 */
	void addActionListeners()
	{
		CheckButton.addActionListener(this);
		NextGameButton.addActionListener(this);
		RevealSolutionButton.addActionListener(this);
		
	}
	
	

	/**ACTION PERFORMED
	 * 
	 * RevealSolutionButton has the current sudokusolution reveals the solution to the puzzle.
	 * Check Button has the sudoku object check if the current answers typed in the textbox is 
	 * correct
	 * Next Game loads the next game
	 * 
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 
	 * @param takes in nothing
	 * @return returns nothing.
	 */
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
		if (event.getSource() == RevealSolutionButton)
		
			sudokusolution.revealSolution(); 
		
					
		if (event.getSource() == CheckButton)
		
			sudoku.check();
		
		
		if (event.getSource() == NextGameButton)
		
			sudoku.nextGame();
	}
	
	


	
	/** GIVES  VALUE OF "OUTER ARRAY INDEX" EQUIVALENT TO THAT FROM A 9 BY 9 ARRAY
	 *			VALID FOR BOTH X & Y
	 * @param takes in an index from a 9 by 9 array
	 * @return it's equivalent within the Outer Square of Sudoku.
	 */
	static int outerIndex(int OriginalIndex)
	{
		return OriginalIndex/3;
	}
	
	
	/** GIVES VALUE OF "INNER ARRAY INDEX" ON SUDOKU EQUIVALENT TO THAT BY A 9 BY 9 ARRAY
	 *			VALID FOR BOTH X & Y
	 * @param takes in an index from a 9 by 9 array
	 * @return its equivalent within the inner square of Sudoku.
	 */
	 
	static int innerIndex(int OriginalIndex)
	{
		return OriginalIndex%3;
		
	}
	
	
	
	
	/**SET VALUE
	 * 
	 * @return returns nothing
	 * @param row - must be int 0 - 8
	 * @param column - must be int 0 - 8
	 * @param number - msut be 0 - 9, 0 signifies empty textbox
	 */
	 void setValue(int row, int column, int number)
	{
		OuterGrid[outerIndex(row)][outerIndex(column)].setValue(innerIndex(row),innerIndex(column),number);
	}
	
	/**GET VALUE
	 * 
	 *  * @return retursn nothing
	 * @param row - must be int 0 - 8
	 * @param column - must be int 0 - 8
	 * @param number - msut be 0 - 9, 0 signifies empty textbox
	 */
	
	 int getValue(int row, int column)
	{
		return OuterGrid[outerIndex(row)][outerIndex(column)].getValue(innerIndex(row),innerIndex(column));
	}
	

	 /**Check
	  * 
	  * Checks the Sudoku
	  * @param takes in nothing
	  * @return returns nothing
	  */
	 void check()
	 {
	 
	boolean isCorrectSolution = false;
	 int[][] CurrentAnswers = new int[9][9];
	 
	for (int x = 0; x < CurrentAnswers.length; x++)
		
		for (int y = 0; y < CurrentAnswers[0].length; y++)
		{
			try{
				
				CurrentAnswers[x][y] = this.getValue(x, y);
				
				if (this.getValue(x,y) != sudokusolution.AnswerArray[x][y])
					
				{
					this.setValue(x,y,0);
					isCorrectSolution = false;
				}	
				
				else
					isCorrectSolution = true;
			}
			catch (java.lang.NumberFormatException e)
			{
				this.setValue(x, y, 0);
				isCorrectSolution = false;
			}
		}
	
		if (isCorrectSolution == true)
			
			sudokusolution.revealSolution();
	
	 }
	 
	 /** NEXT GAME
	  * 
	  * 1. makes the new game
	  * 2. constructs a new sudoku solution based on going thru the three files
	  * 3. If the sudoku solution #  is greater than 
	  * 
	  * @param takes in nothing 
	  * @return returns nothing
	  */
	 void nextGame()
	 {
		SolutionNumber =  SolutionNumber + 1;
		if (SolutionNumber == 4)
			SolutionNumber = 1;
		
		sudokusolution = new SudokuSolution("solution" + SolutionNumber + ".txt", this);
	 }
	
	
	/** 	
	 * 
	 * 1. adds an array of Sudoku3By3s to a JPanel.  
	 *    -->Intended to be used as to add OuterSudokuSquare to SudokuPanel.
	 * 2. Constructs and adds a new Sudoku3By3 Square to SudokuSquareArray
	 *    
	 *    @param JPanel - should be SudokuPanel only.
	 *    @param OuterSudokuSquare - should be OuterSudokuSquare only.
	 *
	 */
	
	void initialize3By3(JPanel j)
	{
		for (int i = 0; i < OuterGrid.length; i++)
		
			for (int u = 0; u < OuterGrid.length; u++)
			{
				OuterGrid[i][u] = new Sudoku3By3();
				j.add(OuterGrid[i][u]);

				
			}
	
	}	
	
	
		
	 	/** SUDOKU PANEL SETS UP SUDOKU PANEL
	 	 * 
	 	 * 	1. Sets layout
	 	 *  2. Fills the Originaly empty 3 by 3 Array of Sudoku3By3s with new Sudoku3By3s via &CALLING add3By3SquareToArray
	 	 *  2. Adds to panel a 3 By 3 Array of Sudoku3By3s, called OuterSudokuSquare via &CALLING add3By3SquareToJPanel
	 	 *  3. Sets border
	 	 * 
	 	 */
		JPanel SudokuPanel ()
		{
			 //New Jpanel created.
			JPanel myPanel = new JPanel();
			
			//JPanel's layout is set to Grid Layout, with three columns and three rows.
			myPanel.setLayout (new GridLayout (3, 3));
			
			//Fills OuterSudokuSquare with Sudoku3by3s, via CALLING add3By3SquareToArray
			this.initialize3By3(myPanel);
			
			//Adds 3By3
			//Sets border using border from Sudoku3by3.
			//Combined with border setting in Sudoku3By3, creates effect of black cross on white.
			myPanel.setBorder(Sudoku3By3.myBorder);

			//Returns the finished panel.
			return myPanel;
		}
	

		/**CREATE PANEL  (called in Setup)
		 * adds components to the ButtonPanel
		 * 		1. JButton "Check" which as of now does nothing
		 * 		2. JButton "Next Game" which as of now does nothing
		 * 		3. JButton "Reveal Solution" which as of now does nothing
		 * 
		 * adds components to a Container object;; 
		 * 	(to be invoked when setting up interface & adding game to window)
		 * 		1. adds the ButtonPanel to the Container
		 * 		2. adds the SudokuPanel to the Container 
		 * 				(&Call SudokuPanel(), which sets up SudokuPanel)
		 * 
		 */
		public void createPanel ( Container contentPane)
		{
			//Add the Buttons.
			buttonpanel.add (CheckButton);
			buttonpanel.add(NextGameButton);
			buttonpanel.add(RevealSolutionButton);
			
			//Add ButtonPanel to container.
			contentPane.add(buttonpanel,BorderLayout.NORTH);
			
			//Add SudokuPanel (setup from function SudokuPanel() 
			contentPane.add(this.SudokuPanel(),BorderLayout.CENTER);
		}
		
		
		/**
		 * SETUP
		 * 
		 * 
		 * @param takes in nothing
		 * @return returns nothing
		 */
		public static void Setup()
		{
			/*Create Window*
			 * 
			 */
			JFrame f = new JFrame();
			
					//Customize Window
					//Set Title Sudoku
					f.setTitle("Sudoku");
					//Make the background black
					f.setBackground(Color.black);

			//Create new instance of Sudoku
			sudoku = new Sudoku();
		
			Container contentPane = f.getContentPane();
			
			// &Call CREATE PANEL
			sudoku.createPanel(contentPane);
					
			//setting visible & packing.
			sudoku.setVisible(true);
			f.setVisible(true);
			f.pack();
		}
	
		
	/**
	 * MAIN FUNCTION
	 * 
	 * calls Setup();
	 */
	
	public static void main(String Args[])
	{
		System.out.println("Yeah");
		Setup();
		sudoku.nextGame();
		
	}



}
