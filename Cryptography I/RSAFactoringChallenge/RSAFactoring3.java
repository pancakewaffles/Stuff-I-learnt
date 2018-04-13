import java.math.BigInteger;


public class RSAFactoring3 {

	private static BigInteger arithmeticMean(BigInteger N){
		BigInteger am = sqrt(N).add(BigInteger.ONE);
		return am;
		
	}
	private static BigInteger findE(BigInteger am, BigInteger N){
		BigInteger x2 = (am.pow(2)).subtract(N);
		return sqrt(x2);
		
	}
	private static BigInteger calP(BigInteger x, BigInteger am){
		//p is the less of the two primes that make up N
		return (am.subtract(x));
	}
	private static BigInteger calQ(BigInteger x, BigInteger am){
		//q is the greater of the two primes that make up N
		return (am.add(x));
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
		N = N.multiply(new BigInteger("24")); //N=24N
		BigInteger am = arithmeticMean(N);// am = (3p + 2q) 
		BigInteger E = findE(am,N);
		//E can be 3p - 2q or 2q - 3p
		//so, let's assume E = 3p - 2q
		BigInteger S = E.add(am);
		BigInteger p = S.divide(new BigInteger("6"));
		BigInteger q = (am.subtract(p.multiply(new BigInteger("3")))).divide(new BigInteger("2"));
		//check 
		if((p.multiply(q)).compareTo(new BigInteger(args[0]))==0){
			//p and q are correct
			System.out.println("p is: "+p);
			System.out.println("q is: "+q);
			System.out.println(((p.multiply(q)).compareTo(new BigInteger(args[0]))==0));
			
		}else{
			//E = 2q - 3p
			q = S.divide(new BigInteger("4"));
			p = (am.subtract(q.multiply(new BigInteger("2"))).divide(new BigInteger("3")));
			System.out.println("p is: "+p);
			System.out.println("q is: "+q);
			System.out.println(((p.multiply(q)).compareTo(new BigInteger(args[0]))==0));
			
		}
		
		
		

	}

}
