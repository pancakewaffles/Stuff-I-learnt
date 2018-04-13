
public class Percolation {
	
   private int total;
   private WeightedQuickUnionUF wquuF;
   private boolean[] gridOpen;
   private int openSites;
   
	public Percolation(int N) {
		if(N<=0){
			throw new IllegalArgumentException("N<=0");
		}
		//Initialize an all-blocked N*N grid
		total=N;
		int gridSize = N*N;
		openSites=0;
		
		wquuF= new WeightedQuickUnionUF(gridSize+2);
		gridOpen= new boolean[gridSize];
		/*while(percolates()==false){
			
			int i=StdRandom.uniform(1, N+1);
			int j=StdRandom.uniform(1, N+1);
			if(isOpen(i,j)==false){
				open(i,j);
				openSites++;
			} 
		} */
		
	}
	public void open(int i, int j){
		validIndices(i,j);
		int p=xyTo1D(i,j,total);
		gridOpen[p]=true;
		
		if((i-1) != 0 && isOpen(i-1,j)==true ){
			int q=xyTo1D(i-1,j,total);
			wquuF.union(p, q);
	
		}
		if((i+1) != total+1 && isOpen(i+1,j)==true  ){
			int q=xyTo1D(i+1,j,total);
			wquuF.union(p, q);
		
		}
		if((j-1) != 0 && isOpen(i,j-1)==true ){
			int q=xyTo1D(i,j-1,total);
			wquuF.union(p, q);
	
		}
		if((j+1) != total+1 && isOpen(i,j+1)==true  ){
			int q=xyTo1D(i,j+1,total);
			wquuF.union(p, q);
		
		}
		if(i==1){
			wquuF.union(p,total*total);
		}
		if(i==total){
			wquuF.union(total*total+1,p);
		}
		
	}
	public boolean isOpen(int i, int j){
		validIndices(i,j);
		int p= xyTo1D(i,j,total);
		if(gridOpen[p]==true){
			return true;
		}else{
		return false;
		}
	}
	public boolean isFull(int i, int j){
		validIndices(i,j);
		int p= xyTo1D(i,j,total);
		if(wquuF.connected(p,total*total)==true){
			return true;
		}else{
		return false;
		}
	}
	public boolean percolates(){
		if(wquuF.connected(total*total,total*total+1)==true){
			return true;
		}else{ return false; }
		
		
	}
	
	private void validIndices(int i, int j){
		if(i<1 || i>total){
			throw new IndexOutOfBoundsException("row index i out of bounds");
		}
		if(j<1 || j>total){
			throw new IndexOutOfBoundsException("row index j out of bounds");
		}
	}
	
	private static int xyTo1D(int i, int j,int N){
		return j-1+(i-1)*N;
	}
	public static void main(String[] arg){
		
	
	}



}
