
public class ConversionTools {

	public static String hextoASCII(String hex) {
		StringBuilder output=new StringBuilder();
		for(int i=0;i<hex.length();i+=2){
			String str = hex.substring(i,i+2);
			output.append((char)Integer.parseInt(str, 16));
		}
		return output.toString();
	}
	public static String asciiToHex(String ascii){
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
	public static String bytesToHex(byte[] b) {
		 char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
		                         '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		 StringBuffer buf = new StringBuffer();
		 for (int j=0; j<b.length; j++) {
		     buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
		     buf.append(hexDigit[b[j] & 0x0f]);
		   }
		 return buf.toString();
		   }
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}

}
