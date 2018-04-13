
public class PercolationStats {
	private double[] openSites;
	public PercolationStats(int N, int T) {
		
		if(N<=0 || T<=0){
			throw new java.lang.IllegalArgumentException("N<=0 or T<=0");
		}
		openSites=new double[T];
		
		for(int t=0;t<T;t++){
			Percolation perc=new Percolation(N);
			int count=0;
			while(perc.percolates()==false){
				
				int i=StdRandom.uniform(1, N+1);
				int j=StdRandom.uniform(1, N+1);
				if(perc.isOpen(i,j)==false){
					perc.open(i,j);
					count++;
				} 
			/*int count=0;
			for(int j=1;j<N+1;j++){
				for(int k=1;k<N+1;k++){
					if(perc.isOpen(j, k)==true){
						count++;
					}
				}
			} */
			}
			
			openSites[t]=((double)(count))/(N*N);
		}
	}
	public double mean(){
		return StdStats.mean(openSites);
		
	}
	public double stddev(){
		return StdStats.stddev(openSites);
		
	}
	public double confidenceLo(){
		double mean=StdStats.mean(openSites);
		double stddev=StdStats.stddev(openSites);
		return mean-(1.96*stddev)/Math.sqrt(openSites.length);
		
	}
	public double confidenceHi(){
		double mean=StdStats.mean(openSites);
		double stddev=StdStats.stddev(openSites);
		return mean+(1.96*stddev)/Math.sqrt(openSites.length);
		
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int N, T;
		N = Integer.parseInt(args[0]);
		T = Integer.parseInt(args[1]);
		PercolationStats ps= new PercolationStats(N,T);
		double mean = ps.mean();
		double stddev = ps.stddev();
		double confidenceLo = ps.confidenceLo();
		double confidenceHi = ps.confidenceHi();
		System.out.println("mean = "+ mean);
		System.out.println("stddev = "+ stddev);
		System.out.println("95% confidence interval = "+ confidenceLo+", "+confidenceHi);

	} 

}
