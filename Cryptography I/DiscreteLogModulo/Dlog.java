import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;


public class Dlog {
	//Meet in the Middle Attack
	
	private static HashMap LHSgen(BigInteger h,BigInteger g,BigInteger p,BigInteger B){
		HashMap al = new HashMap<BigInteger,BigInteger>();
		BigInteger zero = BigInteger.ZERO;
		for(zero=BigInteger.ZERO;B.compareTo(zero)>0;zero=zero.add(new BigInteger("1"))){
			BigInteger result =h.multiply((g.modPow(zero, p)).modInverse(p));
			BigInteger resultmod = result.mod(p);
			al.put(resultmod,zero);
			
		}
		return al;
		
	}
	private static String RHSfind(HashMap al,BigInteger B,BigInteger g,BigInteger p){
		BigInteger x1 = BigInteger.ZERO;
		BigInteger x0 = BigInteger.ZERO;
		BigInteger realx1 = null,realx0 = null;
		outerloop:
		for(x0=BigInteger.ZERO;B.compareTo(x0)>0;x0=x0.add(new BigInteger("1"))){
			BigInteger result = (g.modPow(B, p)).modPow(x0,p);
			BigInteger resultmod=result.mod(p);
			if(al.containsKey(resultmod)){
				realx0=x0;
				realx1=(BigInteger)al.get(resultmod);
			}
	
		}
		
		
		return ("(x0,x1) = ("+realx0+","+realx1+")");
		
	}
	private static BigInteger calculateX(BigInteger x0,BigInteger x1,BigInteger B){
		BigInteger x =(x0.multiply(B)).add(x1);
		return x;
	}


	public static void main(String[] args) {
		// args[0] = p, args[1] = g, args[2] = h
		BigInteger p,g,h,B;
		p=new BigInteger(args[0]);
		g=new BigInteger(args[1]);
		h=new BigInteger(args[2]);
		B=new BigDecimal(Math.pow(2, 20)).toBigInteger();
		//HashMap al =LHSgen(h,g,p,B);
		//String x0x1 = RHSfind(al,B,g,p);
		//System.out.println(x0x1);
		//x=x0 * B + x1;
		System.out.println(calculateX(new BigInteger("357984"),new BigInteger("787046"),B));
		
		
		
		
		
		
		
	

	}

}
