import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * We need:
 * A map to store our city names and indexes (starting from 1)
 * a cost graph as our graph (in 2d array format)
 * 
 */
public class App {
	// Fast IO
	static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader()
        {
            br = new BufferedReader(new
                     InputStreamReader(System.in));
        }
 
        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt()
        {
            return Integer.parseInt(next());
        }
 
        long nextLong()
        {
            return Long.parseLong(next());
        }
 
        double nextDouble()
        {
            return Double.parseDouble(next());
        }
 
        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }
	// Dijkstra's Algorithm
	public static int[] dijkstra(int[][] g, int src, int V){
		// Okay, we need an output array
		int[] dist = new int[V]; // dist[i] will hold the shortest distance from src to city i
		
		// We maintain two sets, one set contains vertices included in shortest path tree,
		// the other set includes vertices not yet included in shortest path tree.
		Boolean[] sptSet = new Boolean[V];
		
		// Initialize all distances as infinite and sptSet[] as false
		for(int i =0;i<V;i++){
			dist[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		}
		// Distance of src vertex from itself is always 0
		dist[src] = 0;
		
		// Find shortest path for all vertices
		for(int i=0;i<V-1;i++){
			// Pick the minimum distance vertex from the set of vertices not yet processed. u is always equal to src in the first iteration.
			
			int u = minDistance(dist,sptSet,V);
			// Now that we got the vertex that has the minimum distance in the set of vertices not yet processed, we can process it
			sptSet[u] = true;
			// Update dist value of the adjacent vertices of the picked vertex
			for(int v =0;v<V;v++){
				if(!sptSet[v] &&  g[u][v] != 0 && dist[u]!=Integer.MAX_VALUE && dist[u] + g[u][v] < dist[v]){
					dist[v] = dist[u] + g[u][v];
				}
			}
		}
		return dist;
	}

	private static int minDistance(int[] dist, Boolean[] sptSet, int V) { // returns the min distance vertex from the set of vertices not yet included in sptSet
		int min = Integer.MAX_VALUE, min_index = -1;
		for(int i =0;i<V;i++){
			if(sptSet[i] == false && dist[i] <= min){
				min = dist[i];
				min_index = i;
			}
			
		}
		return min_index;
	}

	public static void main(String[] args) {
			FastReader s = new FastReader();
			int T = s.nextInt();
			while(T>0){
				int n = s.nextInt(); // Number of cities
				int[][] costs = new int[n][n]; // Cost graph
				HashMap<String,Integer> map = new HashMap<String,Integer>(); // map to convert city name to index	
				for(int i = 0;i<n ; i++){
					
					map.put(s.nextLine(), i);
					
					int p = s.nextInt(); // Number of neighbours to this city
					for(int j = 0;j<p;j++){
						String[] a = s.nextLine().split("\\s+");
						costs[i][Integer.parseInt(a[0])-1] = Integer.parseInt(a[1]);
					}
				}
				
				int r = s.nextInt(); // Number of paths to find 
				for(int i = 0;i<r;i++){
					String[] b = s.nextLine().split("\\s+");
					int src = map.get(b[0]);
					int dest = map.get(b[1]);
					
					int[] dist = dijkstra(costs,src,n);
					System.out.println(dist[dest]);
				}
				
				
				
				
				
				T--;
				System.out.println();
				s.nextLine();
			}
			
		

	}
	
	


}


