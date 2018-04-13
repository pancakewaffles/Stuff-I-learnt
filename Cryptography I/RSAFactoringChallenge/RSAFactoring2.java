import java.math.BigInteger;


public class RSAFactoring2 {

	private static BigInteger arithmeticMean(BigInteger N){
		BigInteger am = sqrt(N).add(BigInteger.ONE);
		return am;
		
	}
	private static BigInteger findX(BigInteger am, BigInteger N){
		BigInteger x2 = (am.pow(2)).subtract(N);
		return sqrt(x2);
		
	}
	private static BigInteger calP(BigInteger x, BigInteger am){
		//p is the less of the two primes that make up N
		return am.subtract(x);
	}
	private static BigInteger calQ(BigInteger x, BigInteger am){
		//q is the greater of the two primes that make up N
		return am.add(x);
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
		BigInteger i;
		BigInteger c = new BigInteger("2").pow(20);
		for(i=BigInteger.ZERO;i.compareTo(c)<0;am=am.add(BigInteger.ONE)){
			BigInteger x = findX(am,N);
			BigInteger p = calP(x,am);
			BigInteger q = calQ(x,am);
			if((p.multiply(q)).compareTo(N)==0){ //pq = N
				System.out.println("p is: "+p);
				System.out.println("q is: "+q);
				break;
			}
		}
		
		

	}

}
