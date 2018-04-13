import java.util.Arrays;


public class Brute {
	private static void setUpDrawing(){
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		
	}
	
	private static Point createPoint(int x,int y){
		Point pt=new Point(x,y);
		return pt;
	}
	private static void transferProcess(Point point, Point point2,
			Point point3, Point point4) {
		Point[] result= {point,point2,point3,point4};
		Arrays.sort(result);
		printLineSegment(result);
		draw(result);
		
	}
	private static void printLineSegment(Point[] result){
		int end=result.length-1;
		if(end>0){
			for(int i=0;i<end;i++){
				System.out.print(result[i]+" -> ");
			}
			
		}
		System.out.println(result[end]);
	}
	private static void draw(Point[] result){
		result[0].drawTo(result[result.length-1]);
	}
	
	private static void findCollinear(int N, Point[] arrayPoints) {
		for(int i=0;i<N;i++){ //exhausting all possible distinct combinations using j=i+1,k=j+1,l=k+1
			for(int j=i+1;j<N;j++){
				for(int k=j+1;k<N;k++){
					for(int l=k+1;l<N;l++){
						if(i==j || i==k || i==l){
							
						}else if(j==k || j==l){
							
						}else if(k==l){
							
						}else{
							if(arrayPoints[i].slopeTo(arrayPoints[j])==arrayPoints[i].slopeTo(arrayPoints[k]) && arrayPoints[i].slopeTo(arrayPoints[k])==arrayPoints[i].slopeTo(arrayPoints[l])){
								//means they lie on a straight line :D aka all points have same gradient
								transferProcess(arrayPoints[i],arrayPoints[j],arrayPoints[k],arrayPoints[l]);
							/*	System.out.print("Point i "+ arrayPoints[i]+" Point j " + arrayPoints[j]+" Point k "+arrayPoints[k]+" Point l "+arrayPoints[l]);
								System.out.println("");
								System.out.println("Slope from i to j "+arrayPoints[i].slopeTo(arrayPoints[j]));
								System.out.println("Slope from i to k "+arrayPoints[i].slopeTo(arrayPoints[k]));
								System.out.println("Slope from i to l "+arrayPoints[i].slopeTo(arrayPoints[l])); */
							}
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		setUpDrawing();
		In in=new In(args[0]);
		int N=Integer.parseInt(in.readLine());
		Point[] arrayPoints= new Point[N];
		for(int i=0;i<N;i++){
			int x=in.readInt();
			int y=in.readInt();
			arrayPoints[i]=createPoint(x,y);
			arrayPoints[i].draw();
			//System.out.println("Point "+(i+1)+" "+arrayPoints[i].toString());	
		}
		if(N>=4){
		findCollinear(N, arrayPoints);
		}
		StdDraw.show();

		

	}





}
