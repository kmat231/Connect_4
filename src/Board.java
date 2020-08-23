import java.util.Scanner; 

public class Board {
	private char [][] gameBoard= {
			{'|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' '},
			{'|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' '},
			{'|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' '},
			{'|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' '},
			{'|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' '},
			{'|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' '}
				}; 
	private boolean isWinning = false; 
	private char winningSymbol; //use in each check method for win
	
	
	
	public void startGame() {		//contains every method, displays user prompt, board, displays winner. 
		System.out.println("  Connect 4 !!");
		displayBoard();
		System.out.println("...............");
		userInput(); 
	}
	
	
	public void userInput() {				//collect user column value, edit char matrix to account for either "R" or "Y". 
		int i = 0;
		char symbol = ' ';					
		Scanner stdin = new Scanner(System.in);
		while (isWinning == false) {							
			int column; 
			if (i % 2 == 0) 
			{
				symbol = 'R';
				System.out.print("Drop a red disk at column (0-6): ");
			}
			else
			{	
				symbol = 'Y';
				System.out.print("Drop a yellow disk at column (0-6): ");
				
			}
			while(!stdin.hasNextInt()) {
				System.out.print("Invalid Input (Enter again): ");
				stdin.next();
			}
				column = stdin.nextInt(); 
			
			switch(column) {	//case --> change matrix --> display board
									//switch statement
			case 0 :
			case 1 : 
			case 2 : 
			case 3 : 
			case 4 : 
			case 5 : 
			case 6 : 		if(checkBottomRow(column) >= 0) {
							 	gameBoard[checkBottomRow(column)][column * 2 + 1] = symbol;
								displayBoard();  
								System.out.println("...............");
								break;
							}
							else {
								columnFull();
								i--; 
								break; 
							}
				
			 default : 		System.out.println("Invalid Column Entered"); //use do while loop
							i--; 
							break; 
							
						
			};
			isWinning();
			i++;
		}
												//check if someone won or draw isDraw(); 
		if (winningSymbol == 'R')				//display winner *congratulations* 
			System.out.print("The red player won.");
		else if (winningSymbol == 'Y')
			System.out.print("The yellow player won.");
		else if (winningSymbol == 'D')
			System.out.print("Game ended in a draw.");
			stdin.close(); 
	}
	
	public void displayBoard() {
		for (int i = 0; i < gameBoard.length; i++)
		{
			for (int j = 0; j < gameBoard[i].length; j++)
			{
				System.out.print(gameBoard[i][j]);
			}
			System.out.println(); 
		}
		
	}
	public int checkBottomRow(int column)	//returns bottom most row; 
	{
		column = column * 2 + 1;
		int val = -1; 
		for(int i = gameBoard.length - 1; i >= 0; i--)
		{
			if (gameBoard[i][column] == ' ')
			{
				val = i;
				break; 
			}
			else if (gameBoard[i][column] == 'R' || gameBoard[i][column] == 'Y') {
				val = -1; 
			}
		}
			return val;
	}

	public void columnFull() {
		System.out.print("Column is full");
		System.out.println();
	}
	
	public void isWinning() {
		if (verticalWin() || horizontalWin() || diagonalwin() || reverseDiagonalWin())
			isWinning = true;
		else if (isDraw()) {
			isWinning = true;
			winningSymbol = 'D';
		}
		else 
			isWinning = false; 
	}
	
	public boolean verticalWin() {
		for (int i = 0; i < gameBoard.length - 3; i++) {
			for (int j = 1; j < gameBoard[i].length; j+=2)
			{
				if (gameBoard[i][j] != ' ' && gameBoard[i][j] == gameBoard[i+1][j] && gameBoard[i+1][j] == gameBoard[i+2][j] && gameBoard[i+2][j] == gameBoard[i+3][j]) {
						winningSymbol = gameBoard[i][j];
						return true; 
				}
			}
		}
		return false; 
	}
	
	public boolean horizontalWin() {
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 1; j < gameBoard[i].length; j+=2)
			{
				if (gameBoard[i][j] != ' ' && gameBoard[i][j] == gameBoard[i][j+2] && gameBoard[i][j+2] == gameBoard[i][j+4] && gameBoard[i][j+4] == gameBoard[i][j+6]) {
						winningSymbol = gameBoard[i][j];
						return true; 
				}
			}
		}
		return false;  
	}
	
	public boolean diagonalwin() {
		for (int i = 0; i < gameBoard.length - 3; i++) {
			for (int j = 1; j < gameBoard[i].length; j+=2)
			{	if((j - 2) >= 0 || (j - 4) >= 0 || (j - 6) >= 0) {
						if (gameBoard[i][j] != ' ' && gameBoard[i][j] == gameBoard[i+1][j-2] && gameBoard[i+1][j-2] == gameBoard[i+2][j-4] && gameBoard[i+2][j-4] == gameBoard[i+3][j-6]) {
							winningSymbol = gameBoard[i][j];
							return true; 
						}
			}
			}
		}
		return false; 
	}
	
	public boolean reverseDiagonalWin() {
		for (int i = 0; i < gameBoard.length - 3; i++) {
			for (int j = 1; j < gameBoard[i].length; j+=2)
			{
				if (gameBoard[i][j] != ' ' && gameBoard[i][j] == gameBoard[i+1][j+2] && gameBoard[i+1][j+2] == gameBoard[i+2][j+4] && gameBoard[i+2][j+4] == gameBoard[i+3][j+6]) {
						winningSymbol = gameBoard[i][j];
						return true; 
				}
			}
		}
		return false; 
	}
	
	public boolean isDraw() {
		boolean val = false; 
		int counter = 0; 
		for (int i = 0; i < gameBoard.length; i++)
		{
			for (int j = 0; j < gameBoard[i].length; j++)
			{
				if (gameBoard[i][j] == ' ')
					counter++;
			}
		}
		if (counter <=6) {
			val = true;
		}
		else 
			val = false; 
			winningSymbol = 'D';
			return val;
	}
	
}

