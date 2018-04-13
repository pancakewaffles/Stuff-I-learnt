import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class App {
	// F(x,pos[],C) is a function which returns 1 if it is possible to place C cows with a min distance of x
	private static int F(int x, int[] pos,int C){
		int cowsplaced = 1, lastpos = pos[0];
		for(int i = 0;i<pos.length;i++){
			if((pos[i] - lastpos) >= x){
				cowsplaced++;
				if(cowsplaced == C){
					return 1;
				}else{
					lastpos=pos[i];
				}
			}
		}
		return 0;
		
	}
	
	private static int bin_search(int[] pos,int left, int right, int C)
	{
		if(right-left>1){
			int mid = left + (right-left)/2;
			if( F(mid,pos,C) == 1 ){ // In the right side or at mid
				return bin_search(pos,mid,right,C);
			}
			if( F(mid,pos,C) == 0){ // In the left side 
				return bin_search(pos,left,mid-1,C);
			}
			
		}
		if(F(right,pos,C)==1){
			return right;
		}else{
			return left;
		}
		
		
	}

	public static void main(String[] args) {
		
		
		//Modified bin_search
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			int T = Integer.parseInt(br.readLine());
			while(T>0){
				String[] s = br.readLine().split("\\s+");
				int N = Integer.parseInt(s[0]); // Number of stalls
				int C = Integer.parseInt(s[1]); // Number of cows
				
				int[] posCowStalls = new int[N];
				for(int i = 0;i<N;i++){
					posCowStalls[i] = Integer.parseInt(br.readLine());
				}
				Arrays.sort(posCowStalls);
				int maxDiff = posCowStalls[N-1]-posCowStalls[0];
				
				int ans = bin_search(posCowStalls,0,maxDiff,C);
				
				System.out.println(ans);
				
				T--;
			}
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

	}

}
