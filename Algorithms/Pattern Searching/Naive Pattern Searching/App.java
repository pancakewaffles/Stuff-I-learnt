/*
 * Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function search(char pat[], char txt[]) 
 * that prints all occurrences of pat[] in txt[]. You may assume that n > m.
 */
public class App {

	public static void main(String[] args) {
		char[] txt = "AABAACAADAABAAABAA".toCharArray();
		char[] pat = "AABA".toCharArray();
		search(pat,txt);
		

	}
	public static void search(char[] pat, char[] txt){
		int M = pat.length;
		int N = txt.length;
		
		// Go through entire txt[]
		for(int i = 0;i<=N-M;i++){
			int j;
			
			// For current index i, check for pattern match
			
			for(j=0;j<M;j++){
				if(txt[i+j] != pat[j]){
					break; // Move on to txt[i+1] because pattern does not exist at txt[i]
				}
			}
			if(j==M){ // If you got here, it means you found a pattern!
				System.out.println("Pattern found at index: "+i);
				
			}
		}
	}

}
