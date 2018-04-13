
public class KdTree {
	private Node root;
	private int size;
	
	private static class Node {
		   private Point2D p;      // the point
		   private RectHV rect;    // the axis-aligned rectangle corresponding to this node
		   private Node left;        // the left/bottom subtree
		   private Node right;        // the right/top subtree
		   private int level;
		   
		   private Node(Point2D p, RectHV rect, Node lb, Node rt,int level){
			   this.p=p;
			   this.rect=rect;
			   this.left=lb;
			   this.right=rt;
			   this.level=level;
		   }
		}

	public KdTree() {
		// TODO Auto-generated constructor stub
		Node root = null;
		size = 0;
	}
	public boolean isEmpty(){
		// is the set empty?
		return size == 0;
		
	}
	public int size(){
		// number of points in the set
		return size;
		
	}
	public void insert(Point2D p){
		// add the point p to the set (if it is not already in the set)
		if(!contains(p)){
			root = insertNode(root,p,0,new RectHV(0,0,1,1));
		}
	
	}

	private Node insertNode(Node node, Point2D p, int level, RectHV rect) {
		if(node == null){
			node = new Node(p,rect,null,null,level);
			size++;
		}
		else{
			if(node.level % 2 ==0){
				if(Point2D.X_ORDER.compare(p,node.p) < 0){
					node.left = insertNode(node.left,p,++level,new RectHV(rect.xmin(),rect.ymin(),node.p.x(),rect.ymax()));
				}else{
					node.right = insertNode(node.right,p,++level,new RectHV(node.p.x(),rect.ymin(),rect.xmax(),rect.ymax()));
				}
			}else{
				if(Point2D.Y_ORDER.compare(p, node.p) < 0){
					node.left = insertNode(node.left, p, ++level, new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.p.y()));
				}else{
					node.right = insertNode(node.right, p, ++level, new RectHV(rect.xmin(), node.p.y(), rect.xmax(), rect.ymax()));
				}
			}
		}
		return node;
		
	}
	public boolean contains(Point2D p){
		// does the set contain the point p?
		return containsNode(root,p);
	
	}
	
	private boolean containsNode(Node node, Point2D p) {
		if(node == null){
			return false;
		}
		if(node.p.equals(p)){
			return true;
		}
		else{
			if(node.level % 2==0){
				if(Point2D.X_ORDER.compare(p,node.p)<0){
					return containsNode(node.left,p);
				}
				else{
					return containsNode(node.right,p);
				}
			}
			else{
				if(Point2D.Y_ORDER.compare(p,node.p)<0){
					return containsNode(node.left,p);
				}
				else{
					return containsNode(node.right,p);
				}
			}
		}
	}
	public void draw(){
		// draw all of the points to standard draw
		drawNode(root);
	
	}
	
	private void drawNode(Node node) {
		if(node == null){
			return;
		}
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(.01);
		node.p.draw();
		
		StdDraw.setPenRadius(.005);
		if(node.level % 2 ==0){
			StdDraw.setPenColor(StdDraw.RED);
			node.p.drawTo(new Point2D(node.p.x(),node.rect.ymax()));
			node.p.drawTo(new Point2D(node.p.x(),node.rect.ymin()));
			
			drawNode(node.left);
			drawNode(node.right);
		}
		else{
			StdDraw.setPenColor(StdDraw.BLUE);
			node.p.drawTo(new Point2D(node.rect.xmax(),node.p.y()));
			node.p.drawTo(new Point2D(node.rect.xmin(),node.p.y()));
			
			drawNode(node.left);
			drawNode(node.right);
		}
		
	}
	public Iterable<Point2D> range(RectHV rect){
		// all points in the set that are inside the rectangle
		Stack<Point2D> stack = new Stack<Point2D>();
		rangeNode(root,stack,rect);
		return stack;
		
	
	}
	private void rangeNode(Node node, Stack<Point2D> stack, RectHV rect) {
		 // all points in the set that are inside the rectangle
		if(node==null){
			 return;
		}
		if(!node.rect.intersects(rect)){
			return;
		}
		if(rect.contains(node.p)){
			stack.push(node.p);
		}
		rangeNode(node.left,stack,rect);
		rangeNode(node.right,stack,rect);
		
	}
	public Point2D nearest(Point2D p){
		// a nearest neighbor in the set to p; null if set is empty
		Stack<Point2D> stack = new Stack<Point2D>();
		nearestNode(root,p,stack,2.0);
		return stack.pop();
		
	
	}
	private void nearestNode(Node node, Point2D queryP , Stack<Point2D> stack,
			double distance) {
		if(node == null){
			return;
		}
		if(distance < node.rect.distanceSquaredTo(queryP)){
			return;
		}
		if(queryP.distanceSquaredTo(node.p)<distance){
			stack.push(node.p);
			distance = queryP.distanceSquaredTo(node.p);
		}
		if(node.level % 2 == 0){
			if(queryP.x()<node.p.x()){
				distance = queryP.distanceSquaredTo(stack.peek());
				nearestNode(node.left, queryP, stack, distance);
				distance = queryP.distanceSquaredTo(stack.peek());
				nearestNode(node.right,queryP,stack,distance);
			}
			else{
				distance = queryP.distanceSquaredTo(stack.peek());
				nearestNode(node.right, queryP, stack, distance);
				distance = queryP.distanceSquaredTo(stack.peek());
				nearestNode(node.left,queryP,stack,distance);
			}
		}
		else{
			if(queryP.y() < node.p.y()){
				distance = queryP.distanceSquaredTo(stack.peek());
				nearestNode(node.left, queryP, stack, distance);
				distance = queryP.distanceSquaredTo(stack.peek());
				nearestNode(node.right,queryP,stack,distance);
			}
			else{
				distance = queryP.distanceSquaredTo(stack.peek());
				nearestNode(node.right, queryP, stack, distance);
				distance = queryP.distanceSquaredTo(stack.peek());
				nearestNode(node.left,queryP,stack,distance);
			}
		}
	}
		



}
