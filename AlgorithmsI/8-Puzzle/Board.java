import java.util.Arrays;


public class Board {
	private final short[][] blocks;
	private final int dim;
    
	// construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
	public Board(int[][] blocks) {
		this.blocks=copySquareArray(blocks);
		dim=blocks.length;
	
	}
	private Board(short[][] blocks) {
		this.blocks=copySquareArray(blocks);
		dim=blocks.length;
	
	}
	private short[][] copySquareArray(int[][] blocks) {
		int N=blocks.length;
		short[][] temp=new short[N][N];
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				temp[i][j]=(short) blocks[i][j];
			}
		}
		return temp;
	}
	private short[][] copySquareArray(short[][] blocks) {
		int N=blocks.length;
		short[][] temp=new short[N][N];
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				temp[i][j]=(short) blocks[i][j];
			}
		}
		return temp;
	}
	// board dimension N
	public int dimension(){
		return dim;
	}
	// number of blocks out of place
	public int hamming(){
		int count=0;
		for(int row=0;row<dim;row++){
			for(int col=0;col<dim;col++){
				int val=(col+1)+(dim)*(row);
				if(blocks[row][col]!=val){
					count++;
				}
			}
		}
		return count-1;
	}
	// sum of Manhattan distances between blocks and goal
	public int manhattan(){
		int count=0;
		int goalRow,goalCol;
		for(int row=0;row<dim;row++){
			for(int col=0;col<dim;col++){
				int val=blocks[row][col];
				if(val==0){
					continue;
				}
				goalRow=(val-1)/dim;
				goalCol=(val-1)%dim;
				count+= Math.abs(row-goalRow) + Math.abs(col-goalCol);
			}
		}
		return count;
	}
	// is this board the goal board?
	public boolean isGoal(){
		return (hamming()==0);
		
	}
	// a board obtained by exchanging two adjacent blocks in the same row
	public Board twin(){
		short[][] copy = copySquareArray(blocks);
		if(dim<=1){
			return new Board(copy);
		}
		int row=0;
		int col=0;
		short value=0;
		short lastValue=blocks[0][0];
		zerosearch:
			for(row=0;row<dim;row++){
				for(col=0;col<dim;col++){
					value=blocks[row][col];
					if(value!=0 && lastValue!=0 && col>0){ //smart to use col>0 to ensure swap must occur on same row;just think through it
						break zerosearch;
					}
					lastValue=value;
				}
			}
		copy[row][col]=lastValue;
		copy[row][col-1]=value;
		return new Board(copy);
		
		
	}
	// does this board equal y?
	public boolean equals(Object y){
		if(y == this)return true;
		if(y == null)return false;
		if(y.getClass() !=this.getClass())return false;
		Board that=(Board) y;
		if(this.blocks.length != that.blocks.length)return false;
		for(int row=0;row<dim;row++){
			if(!Arrays.equals(this.blocks[row],that.blocks[row])){
				return false;
			}
		}
		return true;
		
	}
	// all neighboring boards
	public Iterable<Board> neighbors(){
		Queue<Board> q=new Queue<Board>();
		//Find zero
		int row=0;
		int col=0;
		zerosearch:
			for(row=0;row<dim;row++){
				for(col=0;col<dim;col++){
					if(blocks[row][col]==0){
						break zerosearch;
					}
				}
			}
		if(row>0){
			q.enqueue(new Board(swap(blocks,row,col,row-1,col))); //move 0 up--> must make sure row>0
		}
		if(row<dim-1){
			q.enqueue(new Board(swap(blocks,row,col,row+1,col))); //move 0 down--> must make sure row<dim-1
		}
		if(col<dim-1){
			q.enqueue(new Board(swap(blocks,row,col,row,col+1))); //move 0 to right-->must make sure col<dim-1
		}
		if(col>0){
			q.enqueue(new Board(swap(blocks,row,col,row,col-1))); //move 0 to left-->must make sure col>0
		}
		return q;
		
	}
	private short[][] swap(short[][] array, int fromRow, int fromCol, int toRow, int toCol) {
		short[][] copy= copySquareArray(array);
		short temp=array[fromRow][fromCol];
		copy[fromRow][fromCol]=array[toRow][toCol];
		copy[toRow][toCol]=temp;
		return copy;
		
	}
	public String toString(){
		StringBuilder s = new StringBuilder();
	    s.append(dim + "\n");
	    for (int i = 0; i < dim; i++) {
	        for (int j = 0; j < dim; j++) {
	            s.append(String.format("%2d ", blocks[i][j]));
	        }
	        s.append("\n");
	    }
	    return s.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		  // create initial board from file


	}

}
