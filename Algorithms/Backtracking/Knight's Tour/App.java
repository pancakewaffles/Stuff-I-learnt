/*
 * Knight's Tour problem
 * The knight is placed on the first block of an empty board and, moving according to the rules of chess, must visit each square exactly once.
 * 
 * The naive method is to generate all tours one by one and check if the generated tour satisfies the constraints. 
 * This is not efficient because you have to generate all the tours.
 * 
 * Backtracking, however, works in an incremental way and thus is a better method. 
 * Typically, we start from an empty solution vector and one by one add items 
 * (Meaning of item varies from problem to problem. In context of Knight’s tour problem, an item is a Knight’s move).
 *  When we add an item, we check if adding the current item violates the problem constraint, if it does then we remove the item and try other alternatives.
 *   
 *  If none of the alternatives work out then we go to previous stage and remove the item added in the previous stage. 
 *  
 *  If we reach the initial stage back then we say that no solution exists. 
 *  If adding an item doesn’t violate constraints then we recursively add items one by one.
 *  
 *  
 *  Here's the general idea:
 *  If all squares are visited 
    print the solution
Else
   a) Add one of the next moves to solution vector and recursively 
   check if this move leads to a solution. (A Knight can make maximum 
   eight moves. We choose one of the 8 moves in this step).
   b) If the move chosen in the above step doesn't lead to a solution
   then remove this move from the solution vector and try other 
   alternative moves.
   c) If none of the alternatives work then return false (Returning false 
   will remove the previously added item in recursion and if false is 
   returned by the initial call of recursion then "no solution exists" )
 */
public class App {

	public static void main(String[] args) {
		KnightTour kt = new KnightTour();
		kt.solveKT();
	}


}

class KnightTour{
	final int N = 8;
	
	// A utility function to check if i,j are valid indexes for N*N chessboard
	boolean isSafe(int x, int y, int[][] sol) {
		return (x>=0 && x<N && 
				y>=0 && y<N &&
				sol[x][y] == -1);
	}
	


	// A utility function to print solution matrix sol[N][N]
	 void printSolution(int[][] sol) {
		for(int x = 0; x < N; x++) {
			for(int y = 0;y < N; y++) {
				System.out.print(sol[x][y] + " ");
			}
			System.out.println();
		}
	}
	  void solveKT() {
		 // Initialisation
			int[][] sol = new int[8][8];
			for(int x = 0; x < N; x++) {
				for(int y = 0;y < N;y++) {
					sol[x][y] = -1;
				}
			}
			
			/* xMove[] and yMove[] define next move of Knight.
			 * xMove[] is for next value of x coord.
			 * yMove[] is for next value of y coord.
			 */
			int[] xMove = {2,1,-1,-2,-2,-1,1,2};
			int[] yMove = {1,2,2,1,-1,-2,-2,-1};
			
			sol[0][0] = 0; //Knight is initially at the first block
			
			// Start from 0,0 and explore all tours using solveKTUtil()
			if(!solveKTUtil(0,0,1,sol,xMove,yMove)) {
				System.out.println("Solution does not exist.");
				
			}else {
				printSolution(sol);
			}
			
		}


	  // Recursive utility to solve Knight Tour problem
	 boolean solveKTUtil(int x, int y, int movei, int[][] sol, int[] xMove, int[] yMove) {
		int k, next_x, next_y;
		if(movei == N*N) {
			return true;
		}
		
		// Try all next moves from the current coordinate x,y
		for(k=0;k<8;k++) {
			next_x = x + xMove[k];
			next_y = y + yMove[k];
			if(isSafe(next_x,next_y,sol)) {
				sol[next_x][next_y] = movei;
				if(solveKTUtil(next_x,next_y,movei+1,sol,xMove,yMove)) {
					return true;
				}else {
					sol[next_x][next_y] = -1; //Backtracking
				}
			}
		}
		
		return false;
	}
	 
	 
}
