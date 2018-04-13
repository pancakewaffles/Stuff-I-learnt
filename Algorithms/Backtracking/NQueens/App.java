/*
 * The classic NQueens problem demonstrates the logic behind Backtracking. Backtracking has no set algorithm, but only a fixed line of reasoning. 
 * The whole idea is to try every possible solution until we find the one that fits. It sounds like a naive brute-force method but it improves upon it because it is so much easier to code.
 * 
 * Here's the general idea:
 * The idea is to place queens one by one in different columns, starting from the leftmost column. 
 * When we place a queen in a column, we check for clashes with already placed queens. 
 * 			In the current column, if we find a row for which there is no clash, we mark this row and column as part of the solution. 
 * 			If we do not find such a row due to clashes then we backtrack and return false.
 * 
 * 
 * 1) Start in the leftmost column
   2) If all queens are placed
        return true
   3) Try all rows in the current column.  Do following for every tried row.
    a) If the queen can be placed safely in this row then mark this [row, 
        column] as part of the solution and recursively check if placing  
        queen here leads to a solution.
    b) If placing queen in [row, column] leads to a solution then return 
        true.
    c) If placing queen doesn't lead to a solution then umark this [row, 
        column] (Backtrack) and go to step (a) to try other rows.
   4) If all rows have been tried and nothing worked, return false to trigger 
    backtracking.
 * 
 */
public class App {

	public static void main(String[] args) {
		NQueenProblem Queen = new NQueenProblem();
		Queen.solveNQ();

	}

}

class NQueenProblem{
	final int N = 4;
	
	// A utility function to print solution
	void printSolution(int board[][]) {
		
		for(int i = 0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(" "+board[i][j] + " ");
				
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/* A utility function to check if a queen can be placed on board[row][col]. 
	 * Side we are placing queens from left to right, 
	 * we need to check only left side for attacking queens. <--- IMPORTANT TO UNDERSTAND!!!
	 */
	boolean isSafe(int board[][] , int row, int col) {
		int i,j;
		
		// Check this row, on left side
		for(i = col;i>=0;i--) {
			if (board[row][i] == 1) {
				return false;
			}
			
		}
		// Check upper diagonal, on left side
		for(i = row, j = col; i>=0 && j>=0; i-- , j--) {
			if(board[i][j] == 1) {
				return false;
			}
		}
		// Check lower diagonal, on left side
		for(i = row, j = col; j>=0 && i < N; i++ , j--) {
			if(board[i][j] == 1) {
				return false;
			}
		}
		return true;
	}

	// A recursive utility function to solve N Queen problem
	boolean solveNQUtil(int board[][] , int col) {
		
		// Base case: If all queens are placed then return true
		if(col >= N) {
			
			printSolution(board);
			return true;
		}
		
		// Consider this column and try placing this queen in all rows one by one
		for(int i = 0;i<N;i++) {
			
			// Check if queen can be placed on board[i][col]
			if(isSafe(board,i,col)) {
				
				// Place this queen in board[i][col]
				board[i][col] = 1;
				
				
				//To get all solutions: (basically forcing it to backtrack anyway)
				//solveNQUtil(board,col+1);
				
				// Here's the RECURSION
				if(solveNQUtil(board,col + 1) == true) {
					return true;
				}
				
				
				/*
				 * It's easier to explain this with an example.
				 * Let's say we try to place a Queen in row 1, col 1. ( i = 0).
				 * Then, we advance to the next column because we can't place another Queen in the same column.
				 * If, we can place a Queen in the next column, we advance to the next next column.
				 * If, however, we can't place a Queen in the next column ( col 2), we have to go back, and try a different row (different i).
				 * That's why we do the following:
				 * 
				 */
				// BACKTRACK
				board[i][col] = 0;
				// And if we reach this part, we try the next row. (i = 1). 
				// We are basically saying, "Oops, I can't place the Queen in row 1, col 1 because it will clash with another Queen in the next column. So I have to try a different row."
				
				
			}
		}
		
		// If queen cannot be placed in any row in this column.
		return false;
	}
	
	void solveNQ() {
		int board[][] = { {0,0,0,0},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0}
		};
		if(solveNQUtil(board,0) == false) {
			System.out.println("Solution does not exist.");
			
		}
		
		
		
	}

}

