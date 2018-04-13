import java.math.BigInteger;


public class RSADecrypt {

	private static BigInteger decrypt(BigInteger N, BigInteger y, BigInteger d){
		return (y.modPow(d,N));
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
	private static void reversePKCS(BigInteger x) {
		byte[] hexbytearray = x.toByteArray();
		String toHex = bytesToHex(hexbytearray);
		String[] parts = toHex.split("00");
		System.out.println(hextoASCII(parts[1]));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigInteger y = new BigInteger(args[0]);
		BigInteger N = new BigInteger("179769313486231590772930519078902473361797697894230657273430081157732675805505620686985379449212982959585501387537164015710139858647833778606925583497541085196591615128057575940752635007475935288710823649949940771895617054361149474865046711015101563940680527540071584560878577663743040086340742855278549092581");
		BigInteger p = new BigInteger("13407807929942597099574024998205846127479365820592393377723561443721764030073662768891111614362326998675040546094339320838419523375986027530441562135724301");
		BigInteger q = new BigInteger("13407807929942597099574024998205846127479365820592393377723561443721764030073778560980348930557750569660049234002192590823085163940025485114449475265364281");
		BigInteger e = new BigInteger("65537");
		BigInteger phiN = (p.subtract(BigInteger.ONE)).multiply((q.subtract(BigInteger.ONE)));
		BigInteger d = e.modInverse(phiN);
		//output pk and sk
		System.out.println("pk = ( "+N+" , "+ e+" ) ");
		System.out.println("sk = ( "+N+" , "+ d+" ) ");
		BigInteger x = decrypt(N,y,d);
		reversePKCS(x);
		
	}


}
