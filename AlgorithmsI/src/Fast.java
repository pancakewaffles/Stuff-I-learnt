import java.util.Arrays;
import java.util.ArrayList;


public class Fast {
	
	private static ArrayList<Double> prevSlopes=new ArrayList<Double>();
	private static ArrayList<Point> prevOrigins=new ArrayList<Point>();
	
	
	private static void setUpDrawing(){
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		
	}
	
	private static Point createPoint(int x,int y){
		Point pt=new Point(x,y);
		return pt;
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
	private static boolean checkSlope(double slope){
		if(prevSlopes.contains(slope)==true){
			return true;
		}else{
			
			return false;
		}
	}
	
	private static boolean checkOrigins(Point origin) {
		if(prevOrigins.contains(origin)==true){
			return true;
		}else{
			
			return false;
		}
	}
	private static void transferProcess(ArrayList<Point> collinearPoints) {
		Point[] result=new Point[collinearPoints.size()];
		for(int i=0;i<collinearPoints.size();i++){
			result[i]=collinearPoints.get(i);
		}
		
		Arrays.sort(result);
		double slope=result[0].slopeTo(result[1]);
		if(checkSlope(slope)==true && checkOrigins(result[0])==true){ 

		}else{
			draw(result);
			printLineSegment(result);
			prevSlopes.add(slope);
			prevOrigins.add(result[0]);
		}
		
	}




	public static void main(String[] args) {
		setUpDrawing();
		ArrayList<Point> collinearPoints=new ArrayList<Point>();

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
		Arrays.sort(arrayPoints);
		//Pick a point, then sort the array by slope with that point, next check the points
		//in the new sorted array, and select points which have similar slope with p,
		//if < 3, discard;if >3, include all but make sure no subsegments --> use ArrayList.removeAll(prevArray)
		for(int i=0;i<N;i++){
			Point origin=arrayPoints[i];
			Arrays.sort(arrayPoints, i+1,N,origin.SLOPE_ORDER);
			int step;
			for(int j=i+1;j<N;j+=step){
				/*for(Point point:collinearPoints){
					prevcollinearPoints.add(point);
				}*/
				collinearPoints.clear();
				collinearPoints.add(origin);
				collinearPoints.add(arrayPoints[j]);
				step=1;
				while(j+step<N && origin.SLOPE_ORDER.compare(arrayPoints[j], arrayPoints[j+step])==0){
					collinearPoints.add(arrayPoints[j+step]);
					step++;
				} //POWER TRICK
				//collinearPoints.removeAll(prevcollinearPoints);
				if(collinearPoints.size()>3){
					transferProcess(collinearPoints);
				}
		
			}
		}
		
		

	}



}
