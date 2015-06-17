package sudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * Panel for 3 by 3
 * @author Alison Wong
 *
 */
public class Sudoku3By3 extends JPanel {
	
	//For border.
	public static Border myBorder = BorderFactory.createLineBorder (Color.white,3);
	
	private static final Font FONT = new Font("Baskerville", Font.PLAIN, 12);
	
	JTextField[][] SudokuArray = new JTextField[3][3];
	
	/*
	 * The constructor of this class sets the textboxes to be equal to whatever is in the current Sudoku Array at the moment.
	 * It is dependent on CurrentSudokuArray for whatever the text boxes say, and uses a value setting function to set the values.  Basically , it gets constructed with
	 * the appropriate values, usng set value to do all the setting.  
	 * 
	 * 
	 * Note that not defining the array simply makes the textboxes say "".  YAAY!
	 * 
	 * So, set value changes the array, and makes new sudoku 3 by 3s.  to make new sudoku 3 by 3s call setup, don't make the values 0, get rid of all thingsl ike that. 
	 * 
	 * OKAY! WE CAN DO THIS. 
	 */
	
	/**SET VALUE
	 * setValue sets a cell in in SudokuArray to some number between 0 - 9.
	 * 	"0" is the number which signifies an empty cell.
	 * 
	 * @param row - must be a row # in the range 0 - 2
	 * @param column - must be a # in the range 0 - 2
	 * @param number - must be a # from 0 - 9.
	 */
	 void setValue(int row, int column, int number)
	{
		if (number == 0)
		{this.SudokuArray[row][column].setText("");}
		else{
	 	this.SudokuArray[row][column].setText("" + number);

		}
	}
	
	 int getValue(int row, int column)
		{
		
		 return Integer.parseInt(this.SudokuArray[row][column].getText());

		}
	 
	/** CREATES CELLS
	 *  
	 *  @param string - should be a number from 0 to 9 in string form.
	 *  returns a newly constructed JTextField.
	 *  
	 *  basically makes settings for the textbox
	 */
	 JTextField newCell (String string)
	{
			//make new text field.
		JTextField cell = new JTextField(string, 1); 
		CellSettings(cell);
		
		return cell;
		
	}
		
	/** CELL SETTINGS
	 * 
	 * sets font & text alignment of each textbox
	 * 
	 * @param JTextField - must be a JTextfield.
	 */
	static void CellSettings(JTextField cell)
	{
				//text is @ center of the text boxes.
				cell.setHorizontalAlignment ( JTextField.CENTER );
				
				cell.setFont(FONT); //set font here.
				
	}
	
	
	/** SETS THE TEXTBOX ARRAY TO CURRENT SUDOKU ARRAY (INTEGER ARRAY OF CURRENT VALUES) VALUES
	 * 
	 * (0 and any values but #s are empty).
	 * no parameters, no return values.
	 */
	
	/* ADDS TEXTBOX ARRAY TO this
	 */
	void addSudokuArrayToJPanel(int x, int y)
	{
		
		for (int i = 0; i < x; i++)
		
			for (int u = 0; u < y ; u++)
			{
				SudokuArray[i][u] = newCell("");
				this.add(SudokuArray[i][u]);	
			}
	}
	
	
	
	/*SET UP PANEL
	 * 
	 */
	
	void setUpPanel()
	{
	setLayout (new GridLayout (3, 3));
	
	//add Sudoku Array using above funcitons.
	addSudokuArrayToJPanel(3,3);

	setBackground(Color.black);
	setBorder(myBorder);
	
	}
	
		/*CONSTRUCTOR
		 * 
		 */
	
	public Sudoku3By3()
	{
		this.setUpPanel();
		
		this.setVisible(true);
	}
	
	//close Class Envelope
	}
