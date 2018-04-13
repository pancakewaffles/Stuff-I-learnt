/*
 * Given a text txt[0..n-1] and a pattern pat[0..m-1], 
 * write a function search(char pat[], char txt[]) that prints all occurrences of pat[] in txt[]. 
 * You may assume that n > m.
 * 
 * Okay, so obviously the naive method may not work efficiently in certain cases.
 * The worst case complexity of Naive algorithm is O(m(n-m+1)).
 * aka these cases:
 * txt[] = "AAAAAAAAAAAAAAAAAB"
   pat[] = "AAAAB"

   txt[] = "ABABABCABABABCABABABC"
   pat[] =  "ABABAC" (not a worst case, but a bad case for Naive)
   
   The KMP matching algorithm uses degenerating property (pattern having same sub-patterns appearing more than once in the pattern)
   of the pattern (Do we really have to type out that many "patterns"?) and improves the worst case complexity to O(n).
   
   ***Okay, the main idea is this: Whenever we detect a mismatch after some matches, it implies that some of the characters are in the text.
   So we don't match those characters again because we know they will match.***
   
   KMP algorithm does some preprocessing over the pattern pat[] and constructs an auxiliary array lps[] of size m (same as size of pattern). 
   Here name lps indicates longest proper prefix which is also suffix.. For each sub-pattern pat[0…i] where i = 0 to m-1, 
   lps[i] stores length of the maximum matching proper prefix which is also a suffix of the sub-pattern pat[0..i].
   
     lps[i] = the longest proper prefix of pat[0..i] 
              which is also a suffix of pat[0..i]. 
     
     What is that talking about?
     Okay, for the pattern "AAABAAA", lps[i] means to only consider the pattern up to index i.
     So, lps[3] means we only look at "AAAB"
     The proper prefixes of "AAAB" are : A, AA, AAA
     The proper suffixes of "AAAB" are : B, AB, AAB 
     Obviously, there are no matches.
     But for lps[2] i.e. look at "AAA" :
     Prefixes : A , AA
     Suffixes: A, AA
     Longest proper prefix which is also a suffix = AA = length 2.
              
     Examples:
For the pattern “AABAACAABAA”, lps[] is [0, 1, 0, 1, 2, 0, 1, 2, 3, 4, 5]
For the pattern “ABCDE”, lps[] is [0, 0, 0, 0, 0]
For the pattern “AAAAA”, lps[] is [0, 1, 2, 3, 4]
For the pattern “AAABAAA”, lps[] is [0, 1, 2, 0, 1, 2, 3]
For the pattern “AAACAAAAAC”, lps[] is [0, 1, 2, 0, 1, 2, 3, 3, 3, 4]         
   
 */
public class App {
	public static void KMPSearch(String pat, String txt){
		int M = pat.length();
		int N = txt.length();
		
		//create lps[] that will hold the longest prefix suffix values for pattern
		int[] lps = new int[M]; // Same size as pattern
		int j = 0; // index for pat[]
		
		//Preprocess the pattern (calculate lps[] array)
		computeLPSArray(pat,M,lps);
		
		int i = 0; //index for txt[]
		while(i<N){
			if(pat.charAt(j) == txt.charAt(i)){
				j++;
				i++;
			}
			if(j == M){
				System.out.println("Found pattern "+ "at index "+(i-j));
			}
	           // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i))
            {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j-1];
                else
                    i = i+1;
            }
		}
		
	}

	private static void computeLPSArray(String pat, int M, int[] lps) {
		int len = 0;
		int i = 1;
		lps[0] = 0; // lps[0] is always 0
		
		// the loop calculates lps[i] for i=1 to M-1
		
		while(i<M){
			if(pat.charAt(i) == pat.charAt(len)){
				len++;
				lps[i] = len;
				i++;
				
			}else{ // pat[i] != pat[len]
				if( len != 0){
					
					len = lps[len-1];
					
				}else{ // pat[i] != pat[len] and len = 0
					
					lps[i] = len;
					i++;
				}
			}
		}
		
	}

	public static void main(String[] args) {
		String pat = "pattern";
		String txt = "hellopatternohyea";
		KMPSearch(pat,txt);
	}

}
