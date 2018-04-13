import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES_CBC {
	
	public static int blockSize = 16;
	public static byte[] key;
	public static byte[] ciphertext;
	public static byte[] IV;
	public static byte[] plaintext;

	public AES_CBC() {
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

	private static byte[] encrypt(byte[] key, byte[] pt, byte[] IV) throws Exception{
		AES_CBC.key = key;
		AES_CBC.plaintext = pt;
		AES_CBC.IV = IV;
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding","SunJCE");
		SecretKeySpec encryptionKey = new SecretKeySpec(key,"AES");
		cipher.init(Cipher.ENCRYPT_MODE, encryptionKey,new IvParameterSpec(IV));
		return cipher.doFinal(plaintext);
	}
	private static byte[] decrypt(byte[] key, byte[] ct) throws Exception{
		AES_CBC.key = key;
		AES_CBC.ciphertext = ct;
		AES_CBC.IV = obtainIV(ciphertext);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding","SunJCE");
		SecretKeySpec encryptionKey = new SecretKeySpec(key,"AES");
		cipher.init(Cipher.DECRYPT_MODE,encryptionKey,new IvParameterSpec(IV));
		return cipher.doFinal(ciphertext);
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

	public static void main(String[] args) throws Exception {
		//args[0] = key , args[1] = ciphertext
		AES_CBC aes = new AES_CBC();
		byte[] message =aes.decrypt(hexStringToByteArray(args[0]),hexStringToByteArray(args[1]));
		String stringMessage = bytesToHex(message);
		System.out.println(hextoASCII(stringMessage));

	}

}
