
public class PointSET {
	private final SET<Point2D> rbBST;
	
	

	public PointSET() { // construct an empty set of points
		rbBST=new SET<Point2D>();
		
	}
	public boolean isEmpty(){
		// is the set empty?
		return rbBST.isEmpty();
	}
	public int size(){
		// number of points in the set
		return rbBST.size();
	}
	public void insert(Point2D p){
		// add the point p to the set (if it is not already in the set)
		rbBST.add(p);
	}
	public boolean contains(Point2D p){
		// does the set contain the point p?
		return rbBST.contains(p);
	}
	public void draw(){
		// draw all of the points to standard draw
		for(Point2D p : rbBST){
			StdDraw.setPenRadius(.01);
			StdDraw.setPenColor(StdDraw.BLACK);
			p.draw();
		}
	}
	public Iterable<Point2D> range(RectHV rect){
		Stack<Point2D> stack = new Stack<Point2D>();
		for(Point2D p : rbBST){
			if(rect.contains(p)){
				stack.push(p);
			}
		}
		return stack;
		// all points in the set that are inside the rectangle
	}
	public Point2D nearest(Point2D p){
		// a nearest neighbor in the set to p; null if set is empty
		if(rbBST.size()==0){
			return null;
		}
		Point2D neighbor = null;
		for(Point2D point : rbBST){
			if(neighbor == null){
				neighbor = point;
			}
			if(point.distanceSquaredTo(p)<neighbor.distanceSquaredTo(p)){
				neighbor = point;
			}
			
			
		}
		return neighbor;
		
	}



}
