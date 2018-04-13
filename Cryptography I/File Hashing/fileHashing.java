import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.google.common.io.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class fileHashing {
	
	private static void SHA256(File file) throws IOException, NoSuchAlgorithmException{
		byte[] byteArray = Files.toByteArray(file);
		//while byteArray size not equal to zero, read last 1024 bytes and hash them
		int n=byteArray.length;
		int i = n - 1;
		//initialization
		byte[] block, hash;
		if(n % 1024 != 0){
			int remainder = n % 1024;
			int rindex = remainder - 1;
			byte[] rblock = readremainderBlock(byteArray,rindex);
			n = n - remainder;
			hash = hash(rblock);
			i = n - 1;
			
			
			
			
		}else{
		 block = readlast1024Block(byteArray,i);
		 hash = hash(block);
		 n = n - 1024;
		i = n - 1;
		
		}
		byte[] finalHash;
		while(n != 0){
			block = readlast1024Block(byteArray,i);
			byte[] tempblock = new byte[1024+32];
			System.arraycopy(block,0,tempblock,0,1024);
			System.arraycopy(hash,0,tempblock,1024,32);
			hash = hash(tempblock);
			n = n - 1024;
			i = n - 1;
			
		}
		finalHash = hash;
		System.out.println(bytesToHex(finalHash));
		
		
		
	}
	private static byte[] readlast1024Block(byte[] byteArray,int index){ //read last 1024 bytes and form new byte[] from that
		byte[] block = new byte[1024];
		for(int i=index,j=1023;j>=0;i--,j--){
			block[j]=byteArray[i];
			
		}
		return block;
	}
	private static byte[] readremainderBlock(byte[] byteArray,int rindex){ //read last 1024 bytes and form new byte[] from that
		byte[] block = new byte[rindex+1];
		for(int i=byteArray.length-1,j=rindex;j>=0;i--,j--){
			block[j]=byteArray[i];
		}
		return block;
	}
	private static byte[] hash(byte[] byteArray) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		return md.digest(byteArray);
	
		
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


	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		String file = args[0];
		File f = new File(file);
		SHA256(f);

	}

}
