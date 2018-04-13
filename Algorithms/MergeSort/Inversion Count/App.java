import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class App {
	
	private static long inversions = 0;
	// MergeSort
	
	// Merges two subarrays of arr[]
	// First subarray is arr[l...m]
	// Second subarray is arr[m+1...r]
	public static void merge(long arr[],int l,int m, int r){
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1; 
		int n2 = r - m;
		
		// Create temp arrays
		long L[] = new long[n1];
		long R[] = new long[n2];
		
		// Copy data to temp arrays
		for(int i = 0;i<n1;i++){
			L[i] = arr[l+i];
		}
		for(int j = 0;j<n2;j++){
			R[j] = arr[m+1+j];
		}
		
		// MERGE THEM!!!
		// Initial indexes of first and second subarrays
		int i = 0, j =0;
		
		// Initial index of merged subarray array
		int k = l;
		while(i<n1 && j<n2){
			if(L[i] <= R[j]){
				arr[k] = L[i];
				
				i++;
			}
			else{
				arr[k] = R[j];
				inversions += n1 - i ;
				j++;
			}
			k++;
		}
		
		// Copy remaining elements of L[] if any
		while(i<n1){
			arr[k] = L[i];
			i++;
			k++;
		}
		// Copy remaining elements of R[] if any
		while(j<n2){
			arr[k] = R[j];
			j++;
			k++;
		}
		
		
	}
	
	// Main function that sorts arr[l..r] using merge()
	
	public static void sort(long arr[], int l, int r){
		if(l<r){
			// Find the middle point
			int m = (l+r)/2;
			
			// Sort first and second halves
			sort(arr,l,m);
			sort(arr,m+1,r);
			
			// Merge the sorted halves
			merge(arr,l,m,r);
		}
	}

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			int T = Integer.parseInt(br.readLine());
			br.readLine();
			while(T>0){
				int N = Integer.parseInt(br.readLine());
				long[] arr = new long[N];
				for(int i =0;i<N;i++){
					arr[i] = Integer.parseInt(br.readLine());
							
							
					
				}
				br.readLine();
				sort(arr,0,arr.length-1);
				
				System.out.println(inversions);
				
				inversions = 0;
				T--;
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

	}
	
	

}


