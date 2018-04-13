import java.util.Arrays;

/*
 *  	An essential part of circuit design and general system optimization is critical path analysis. 
 *  On a chip, the critical path represents the longest path any signal would have to travel during execution. 
 *  In this problem we will be analyzing chip designs to determine their critical path length. 
 *  The chips in this problem will not contain any cycles, i.e. there exists no path from one component of a chip back to itself.



Given a String[] connects representing the wiring scheme, and a String[] costs representing the cost of each connection, 
your method will return the size of the most costly path between any 2 components on the chip. 
In other words, you are to find the longest path in a directed, acyclic graph. 
Element j of connects will list the components of the chip that can be reached directly from the jth component (0-based). 
Element j of costs will list the costs of each connection mentioned in the jth element of connects. As mentioned above, the chip will not contain any cyclic paths. For example:
connects = {"1 2",
            "2",
            ""}
costs    = {"5 3",
            "7",
            ""}
In this example, component 0 connects to components 1 and 2 with costs 5 and 3 respectively. 
Component 1 connects to component 2 with a cost of 7. 
All connections mentioned are directed. 
This means a connection from component i to component j does not imply a connection from component j to component i. 
Since we are looking for the longest path between any 2 components, your method would return 12.

 */

// oh mate, this can't get any more obvious that it is a graph problem
public class App {
	
	public static int howLong(String[] connects, String[] costs){
		//look, let's have the graph as a 2d array. If i does not connect to j, then g[i][j] = -1, else, g[i][j] = cost
		
		//Setting up g
		int n = connects.length;
		int[][] g = new int[n][n];
		for(int i =0;i<n;i++){
			Arrays.fill(g[i], -1);
		}
		for(int i = 0;i<n;i++){
			if(connects[i] == ""){
				break;
			}
			String[] a = connects[i].split("\\s+");
			String[] b = costs[i].split("\\s+");
			for(int j = 0;j<a.length;j++){
				g[i][Integer.parseInt(a[j])] = Integer.parseInt(b[j]);
			}
		}
		
		
		//Run dfs
			
		return dfs(0,0,g,n);
	}
	
	public static int dfs(int v, int father, int[][] g, int n){
		int maxi = 0;
		// we go through each vertex, and see where it ends up, adding the costs on the way, and then comparing it with the current max
		for(int i =0;i<n;i++){
			if(g[v][i] != -1 && i != father){
				maxi = Math.max(maxi, dfs(i,v,g,n)+g[v][i]);
			}
		}
		return maxi;
	}
	
	public static void main(String[] args) {
		String[] connects = 	{"1 2 3 4 5","2 3 4 5","3 4 5","4 5","5",""};
		String[] costs =	{"2 2 2 2 2","2 2 2 2","2 2 2","2 2","2",""};
		
		System.out.println(howLong(connects,costs));

	}

}
