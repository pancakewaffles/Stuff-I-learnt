import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/*
 * You work for a very large company that markets many different products. In some cases, one product you market competes with another. 
 * To help deal with this situation you have split the intended consumers into two groups, namely Adults and Teenagers. 
 * If your company markets 2 products that compete with each other, selling one to Adults and the other to Teenagers will help maximize profits. 
 * Given a list of the products that compete with each other, 
 * you are going to determine whether all can be marketed such that no pair of competing products are both sold to Teenagers or both sold to Adults. 
 * If such an arrangement is not feasible your method will return -1. Otherwise, it should return the number of possible ways of marketing all of the products.

The products will be given in a String[] compete whose kth element describes product k. The kth element will be a single-space delimited list of integers. These integers will refer to the products that the kth product competes with. For example:
compete = {"1 4",
            "2",
            "3",
            "0",
	    ""}
The example above shows product 0 competes with 1 and 4, product 1 competes with 2, product 2 competes with 3, and product 3 competes with 0. Note, competition is symmetric so product 1 competing with product 2 means product 2 competes with product 1 as well.

Ways to market:

1) 0 to Teenagers, 1 to Adults, 2 to Teenagers, 3 to Adults, and 4 to Adults

2) 0 to Adults, 1 to Teenagers, 2 to Adults, 3 to Teenagers, and 4 to Teenagers

Your method would return 2.
 */
public class Marketing {
	
	public static void main(String[] args) {
		String[] compete = {"","","","","","","","","","",
				 "","","","","","","","","","",
				 "","","","","","","","","",""};
		System.out.println(howMany(compete));
		
			
			
			
			
			
	
	}
	public static long howMany(String[] compete){
		int n = compete.length; // how many products
		
		// Create md array to simulate graph
		long[][] g = new long[n][n];
		for(int i = 0;i<n;i++){
			if(compete[i] == ""){
				break;
			}
			String[] a = compete[i].split("\\s+");
			for(int j = 0;j<a.length;j++){
				g[i][Integer.parseInt(a[j])] =  1;
				g[Integer.parseInt(a[j])][i] = 1;  // If there is an edge a<->b, then g[a][b] = 1 and g[b][a] = 1
				
			}		
		}
		
		
		// If a product a is visited
		int[] visited = new int[n];
		Arrays.fill(visited, -1);
		// -1 -> haven't visited, 1 and 0 = visited and coloured
		
		int count = 0;
		for(int i = 0;i<n;i++){
			if(visited[i] == -1){
				if(dfs(i,0,visited,g) == false){
					return -1; // impossible to color graph (no solution)
				}
				count++; // If we land here, it means it is possible to colour graph, from vertex i. 
			}
		}
		/* count at this stage would represent the number of vertices that make it possible to colour graph fully. However, each vertex can be coloured in two ways.
		   Imagine 4 products, two compete with each other, and the other two do not compete with any product. (A B C D), A competes with B
		   One solution will be A - Black, B - White , C - Black, D - Black. 
		   However, while A and B are interdependent on each other's colour, C and D are not.
		   So, the total number of solutions is : 2 ( = solutions of A = solutions of B) * 2 ( = solutions of C) * 2 ( = solutions of D)
		   count represents the number of "independent" vertices.										
		   So solution is 2^count */
		
		return (1L<<count); //2^count because 1 * 2^count. e.g. if we want to represent 2^5,
							//we would do 1<<5 10000 = 1*2^5 + 0*2^4 + 0*2^3 + 0*2^2 + 0*2^1 + 0*2^0
		
	}
	
	public static boolean dfs(int v, int c, int[] visited,long[][] g){
		visited[v] = c;
		for(int i = 0;i<visited.length;i++){
			if(g[v][i]==1){ // if there is an edge between vertices v and i
				// if product i is already visited and colored in wrong color it is bad and thus impossible to color whole graph
				if(visited[i] != -1 && visited[i] == c){
					return false;
				}
				// if product i is not visited we call dfs from it and if returns false we return false too
				if(visited[i] == -1 && dfs(i,1-c,visited,g) == false){
					// 1-c is the colour. If c =1 we want to make sure that the neighbours are coloured 0. If c=0, neighbours must be coloured 1.
					return false;
				}
				
			}
		}
		return true;
	}

}


