import java.math.BigInteger;


public class RSAFactoring1 {

	private static BigInteger arithmeticMean(BigInteger N){
		BigInteger am = sqrt(N).add(BigInteger.ONE);
		return am;
		
	}
	private static BigInteger findX(BigInteger am, BigInteger N){
		BigInteger x2 = (am.pow(2)).subtract(N);
		return sqrt(x2);
		
	}
	
	private static BigInteger sqrt(BigInteger n) { //courtesy of 
		                                           //http://faruk.akgul.org/blog/javas-missing-algorithm-biginteger-sqrt/
		  BigInteger a = BigInteger.ONE;
		  BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
		  while(b.compareTo(a) >= 0) {
			BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
		    if(mid.multiply(mid).compareTo(n) > 0) b = mid.subtract(BigInteger.ONE);
		    else a = mid.add(BigInteger.ONE);
		  }
		  return a.subtract(BigInteger.ONE);
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//args[0] = N;
		BigInteger N = new BigInteger(args[0]);
		BigInteger am = arithmeticMean(N);
		BigInteger x = findX(am,N);
		System.out.println("p is: "+am.subtract(x));
		System.out.println("q is: "+am.add(x));
		

	}

}
