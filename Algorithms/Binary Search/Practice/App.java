import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class App {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			int T = Integer.parseInt(br.readLine());
			while(T>0){
				int N = Integer.parseInt(br.readLine());
				String[] s = br.readLine().split("\\s+");
				int k = Integer.parseInt(br.readLine());
				int[] A = new int[N];
				for(int i = 0;i<N;i++){
					
					A[i] = Integer.parseInt(s[i]);
					
					
				}
				
				int ans = bin_search(A,0,N-1,k);
				
				System.out.println(ans);
				
				T--;
			}
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

	}
	private static int bin_search(int[] A, int left, int right,  int k)
	{
		if(right>=left){
			int mid = left + (right-left)/2;
			if(A[mid] == k){
				return mid;
			}
			if(A[mid] > k){ //in the left side
				return bin_search(A, left, mid-1,k);
			}
			if(A[mid] < k){ //in the right side
				return bin_search(A,mid+1,right,k);
			}
		}
		
		return -1;
	}

}
