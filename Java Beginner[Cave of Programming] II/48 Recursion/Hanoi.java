//T(h) = 2T(h-1) +1
public class Hanoi {

	public static void main(String[] args) {
		int h = 5;
		System.out.println(calculate(h));
	}
	public static int calculate(int h){
		System.out.println(h);
		if(h == 1){
			return 1;
		}
		return 2*calculate(h-1)+1;
		
	}

}
