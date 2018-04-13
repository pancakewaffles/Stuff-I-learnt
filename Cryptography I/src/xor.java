import java.util.Scanner;


public class xor {
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
		while(true){
			Scanner scan = new Scanner(System.in);
			String temp=scan.nextLine();
			if(temp.equals("generate")){
				String num = null;
				for(int i=0;i<16;i++){
					if(num==null){
						num="1";
					}else{
					num += "1";
					}
				}
				for(int i=0;i<16;i++){
					if(num==null){
						num="1";
					}else{
					num += "0";
					}
				}
				System.out.println(num);
				System.out.println(num.length());
				continue;
				
			}
			String c0 = temp;
			temp=scan.nextLine();
			String c1 = temp;
			System.out.println(asciiToHex(xor(hextoASCII(c0),hextoASCII(c1))));
			System.out.println("The length of your output is: "+asciiToHex(xor(hextoASCII(c0),hextoASCII(c1))).length());
		}

	}

}
