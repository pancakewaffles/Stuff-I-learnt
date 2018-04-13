import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class PaddingOracle {
	
	private static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	private static String bytesToHex(byte[] b) {
		 char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
		                         '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		 StringBuffer buf = new StringBuffer();
		 for (int j=0; j<b.length; j++) {
		     buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
		     buf.append(hexDigit[b[j] & 0x0f]);
		   }
		 return buf.toString();
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
	
	private static int sendPost(String url,String urlParameters) throws Exception{ //attack function
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("POST");
		//Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		//Obtain response code
		int responseCode = con.getResponseCode();

		if(responseCode == 403){
			return 0;
		}
		else if(responseCode == 404){
			return 1;
		}
		else return -1;
		
	}
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
	
	private static byte[] xorBytes(byte a, byte b){
		byte[] a_array = new byte[1];
		a_array[0] = a;
		byte[] b_array = new byte[1];
		b_array[0] = b;
		String ahex = bytesToHex(a_array);
		String bhex = bytesToHex(b_array);
		return hexStringToByteArray(asciiToHex(xor(hextoASCII(ahex),hextoASCII(bhex))));
	}
	
	private static String modifyCipherText(byte[] cipherText,int index,int ctr,byte[] padnumber,byte[] guess,byte[] trueMsgBlock){ //index refers to the block which you want to modify (xor with guess and padding) 
													 //Since only 64 bytes, divide into IV, c[0], c[1], c[2] 0 = first block, 1 = second block, 2 = third block
		
		if(index == 0){
			byte[] block = obtainCTBlock(cipherText,16,31);
			byte GuessxorPadNumber = xorBytes(guess[0],padnumber[0])[0];
			byte[] b = new byte[1];
			b[0]=block[16-ctr];
			block[16-ctr]=xorBytes(GuessxorPadNumber,b[0])[0];
			
			for(int i=0;i<trueMsgBlock.length;i++){
				trueMsgBlock[i] = xorBytes(trueMsgBlock[i],padnumber[0])[0];
			}
			
			int i = 1;
			while(16-ctr+i < 16){ // pad it back
				block[16-ctr+i]=xorBytes(trueMsgBlock[16-ctr+i],block[16-ctr+i])[0];
				i++;
			}
			byte[] editedCipherText = putBackCTBlock(cipherText,block,16);
			
			return bytesToHex(editedCipherText);
		}else if(index == 1){
			byte[] block = obtainCTBlock(cipherText,32,47);
			byte GuessxorPadNumber = xorBytes(guess[0],padnumber[0])[0];
			byte[] b = new byte[1];
			b[0]=block[16-ctr];
			block[16-ctr]=xorBytes(GuessxorPadNumber,b[0])[0];
			
			for(int i=0;i<trueMsgBlock.length;i++){
				trueMsgBlock[i] = xorBytes(trueMsgBlock[i],padnumber[0])[0];
			}
			
			int i = 1;
			while(16-ctr+i < 16){ // pad it back
				block[16-ctr+i]=xorBytes(trueMsgBlock[16-ctr+i],block[16-ctr+i])[0];
				i++;
			}
			byte[] editedCipherText = putBackCTBlock(cipherText,block,32);
			
			return bytesToHex(editedCipherText);
			
		}else if(index == -1){
			return "";
		}else{
			return "";
		}
		
	}
	private static byte[] obtainCTBlock(byte[] cipherText, int indexFrom,int indexTo){ //obtain 16 byte block 0 16 32 48 64
		byte[] temp = new byte[16];
		for(int i = indexFrom, j=0; j<16;j++,i++){
			temp[j] = cipherText[i];
		}
		return temp;
		
	}
	private static byte[] putBackCTBlock(byte[] cipherText, byte[] block, int indexFrom){
		byte[] newCipherText = new byte[cipherText.length];
		for(int i=0;i<indexFrom;i++){
			newCipherText[i] = cipherText[i];
		}
		for(int i = indexFrom,j=0;j<block.length;i++,j++){
			newCipherText[i] = block[j];
		}
		for(int i = indexFrom +16;i<cipherText.length;i++){
			newCipherText[i] = cipherText[i];
		}
		return newCipherText;
	}
	private static byte[] shortenCT(byte[] cipherText, int index) {
		if(index == 1){
			return cipherText;
		}else if(index == 0){
			byte[] nct = new byte[48];
			for(int i = 0;i<nct.length;i++){
				nct[i] = cipherText[i];
			}
			return nct;
		}else if(index == -1){
			byte[] nct = new byte[32];
			for(int i = 0;i<nct.length;i++){
				nct[i] = cipherText[i];
			}
			return nct;
		}else{
			return null;
		}
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		byte[] trueMsgBlock = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,79,115};
		byte[] cipherText = hexStringToByteArray("f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa577c0bdf302936266926ff37dbf7035d5eeb4");
		
		byte[] padnumber = new byte[1];
		padnumber[0] = (byte) 2; //<--Change this number
		byte[] guess = new byte[1];
		guess[0] = (byte) 78;
		int ctr = 2;             //<--And this number
		int index = 0;            //<--And this too
		
		byte[] nct = shortenCT(cipherText,index);
				
		String modifiedCipherText = modifyCipherText(nct,index,ctr,padnumber,guess,trueMsgBlock);
		for(int i=76;i<256;i++){
			if(sendPost("http://crypto-class.appspot.com/po?er="+modifiedCipherText,"") == 1){
				System.out.println("The "+ctr+" last byte is "+guess[0]);
				break;
			}else if(sendPost("http://crypto-class.appspot.com/po?er="+modifiedCipherText,"") == -1){
				System.out.println("You got the right message!");
				break;
			}
			guess[0] = (byte) i;
			modifiedCipherText = modifyCipherText(nct,index,ctr,padnumber,guess,trueMsgBlock);
			
		}
		

		

		
	}


}
