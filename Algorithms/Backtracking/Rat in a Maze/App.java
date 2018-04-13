/*
 * Rat in a Maze
 * A Maze is given as N*N binary matrix of blocks where source block is the upper left most block i.e., maze[0][0] and destination block is lower rightmost block i.e., maze[N-1][N-1].
 *  A rat starts from source and has to reach destination. The rat can move only in two directions: forward and down.
In the maze matrix, 0 means the block is dead end and 1 means the block can be used in the path from source to destination. 
Note that this is a simple version of the typical Maze problem. 
For example, a more complex version can be that the rat can move in 4 directions and a more complex version can be with limited number of moves.


It's another example where Backtracking is more efficient and more effective than the naive method.
The naive method generates all paths from source to destination and checks if the generated path satisfies the constraints, one by one.

Backtracking, builds and improves the path.

Here's the general idea:
If destination is reached
    print the solution matrix
Else
   a) Mark current cell in solution matrix as 1. 
   b) Move forward in horizontal direction and recursively check if this 
       move leads to a solution. 
   c) If the move chosen in the above step doesn't lead to a solution
       then move down and check if  this move leads to a solution. 
   d) If none of the above solutions work then unmark this cell as 0 
       (BACKTRACK) and return false.
 */
public class App {

	public static void main(String[] args) {
		RatMaze rat = new RatMaze();
		int maze[][] = { {1,0,0,0},
				{1,1,0,1},
				{0,1,0,0},
				{1,1,1,1}
		};
		rat.solveMaze(maze);
		}

	}



class RatMaze{
	final int N = 4;
	
	// Utility function to print solution matrix sol[N][N]
	void printSolution(int[][] sol) {
		for(int i = 0;i<N;i++) {
			for(int j = 0;j<N;j++) {
				System.out.print(" "+sol[i][j] + " ");
				
			}
			System.out.println();
		}
	}

	// Utility function to check if x,y is valid index for N*N maze
	boolean isSafe(int[][] maze, int x, int y) {
		
		// if (x,y) is within maze, return true
		return (x>=0 && x<N &&
				y>=0 && y<N &&
				maze[x][y] == 1);
				
	}
	
	boolean solveMaze(int[][] maze) {
		int[][] sol = {{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0}
				};
		
		if(solveMazeUtil(maze,0,0,sol) == false) {
			System.out.println("Solution doesn't exist.");
			return false;
				
		}
		printSolution(sol);
		return true;
	}
	
	boolean solveMazeUtil(int[][] maze, int x,int y, int[][] sol) {
		
		// if (x,y) is goal, return true
		if(x == N -1 && y == N-1) {
			sol[x][y] = 1;
			return true;
		}
		
		// Check if maze[x][y] is valid
		if(isSafe(maze,x,y)) {
			sol[x][y] = 1;
			
			// Move forward in x direction
			if(solveMazeUtil(maze,x+1,y,sol)) {
				return true;
			}
			
			// Well, if we can't move in x direction, we'd have to move in y-direction
			if(solveMazeUtil(maze,x,y+1,sol)) {
				return true;
			}
			
			// If none of the above work, backtrack
			sol[x][y] = 0;
			return false;
		}
		
		return false;
	}

}
