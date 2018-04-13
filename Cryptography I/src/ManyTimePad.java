import java.util.Scanner;


public class ManyTimePad {

	private static String xor(String a,String b){
		if(!less(a,b)){
			String temp=a;
			a=b;
			b=temp;
		}
		char[] chars = new char[a.length()];
		for(int i=0;i<chars.length;i++){
			chars[i]=(char) (a.charAt(i) ^ b.charAt(i));
		}
		String bnew=b.substring(a.length(), b.length());
		return (new String(chars))+bnew;
	}

	private static boolean less(String a, String b) { //Is a less than b?
		if(a.length()<b.length()){
			return true;
		}else{ return false; }
	}

	private static String hextoASCII(String hex) {
		StringBuilder output=new StringBuilder();
		for(int i=0;i<hex.length();i+=2){
			String str = hex.substring(i,i+2);
			output.append((char)Integer.parseInt(str, 16));
		}
		return output.toString();
	}
	private static String asciiToHex(String ascii){
        StringBuilder hex = new StringBuilder();
        
        for (int i=0; i < ascii.length(); i++) {
        	if((Integer.toHexString(ascii.charAt(i))).length()==1){
        		hex.append("0"+Integer.toHexString(ascii.charAt(i)));
        	}
        	else{
            hex.append(Integer.toHexString(ascii.charAt(i)));
        	}
        }       
        return hex.toString();
    }

	public static void main(String[] args) {
		String c0 = args[0];
		String c1 = args[1];
		String M01XOR=xor(hextoASCII(c0),hextoASCII(c1));
		M01XOR=asciiToHex(M01XOR);
		System.out.println(M01XOR);
		String prev=null;

		while(true){
		Scanner scan = new Scanner(System.in);
		String temp=scan.nextLine();
		if(temp.equals("")){
			String letter=" "+prev+"";
			prev=letter;
			String result2=xor(hextoASCII(M01XOR),letter);
			System.out.println(letter);
			System.out.println(result2);
		}else{
		prev=temp;
		String letter=temp;
		String result2=xor(hextoASCII(M01XOR),letter);
		System.out.println(result2);
		} 
		}
		
		
		

	}




}
