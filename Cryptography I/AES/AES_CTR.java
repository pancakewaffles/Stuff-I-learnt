import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES_CTR {
	
	public static int blockSize = 16;
	public static byte[] key;
	public static byte[] ciphertext;
	public static byte[] IV;
	public static byte[] plaintext;

	public AES_CTR() {
		// TODO Auto-generated constructor stub
	}
	private static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}

	private static byte[] encrypt(byte[] key, byte[] plaintext, byte[] IV) throws Exception{
		AES_CTR.key = key;
		AES_CTR.plaintext = plaintext;
		AES_CTR.IV = IV;
		Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding","SunJCE");
		SecretKeySpec encryptionKey = new SecretKeySpec(key,"AES");
		cipher.init(Cipher.ENCRYPT_MODE, encryptionKey,new IvParameterSpec(IV));
		return cipher.doFinal(AES_CTR.plaintext);
	}
	
	private static byte[] decrypt(byte[] key, byte[] ciphertext) throws Exception{
		AES_CTR.key = key;
		AES_CTR.IV = obtainIV(ciphertext);
		byte[] newCipherText = new byte[ciphertext.length-16];
		for(int i = 16,j=0;j<newCipherText.length;i++,j++){
			newCipherText[j] = ciphertext[i];
		}
		AES_CTR.ciphertext = newCipherText;
		Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding","SunJCE");
		SecretKeySpec encryptionKey = new SecretKeySpec(key,"AES");
		cipher.init(Cipher.DECRYPT_MODE,encryptionKey,new IvParameterSpec(IV));
		return cipher.doFinal(AES_CTR.ciphertext);
	}
	private static byte[] obtainIV(byte[] ciphertext){
		byte[] IV = new byte[16];
		for(int i = 0; i<16;i++){
			IV[i]=ciphertext[i];
		}
		return IV;
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


	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		AES_CTR aes = new AES_CTR();
		byte[] message =aes.decrypt(hexStringToByteArray(args[0]),hexStringToByteArray(args[1]));
		String stringMessage = bytesToHex(message);
		System.out.println(hextoASCII(stringMessage));

	}

}
