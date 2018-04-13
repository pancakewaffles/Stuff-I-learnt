/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER= new BySlope();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate
    
    private class BySlope implements Comparator<Point>{
    	public int compare(Point a, Point b){
    		double slopeA=Point.this.slopeTo(a);
    		double slopeB=Point.this.slopeTo(b);
    		if(slopeA<slopeB){
    			return -1;
    		}else if(slopeA==slopeB){
    			return 0;
    		}else{
    			return +1;
    		}
    	}
    }

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }


    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        double slope;

        if(this.y==that.y && this.x==that.x){
        	slope = Double.NEGATIVE_INFINITY;
        }else if(this.y==that.y){
        	slope=0.0;
        }else if(this.x==that.x){
        	slope=Double.POSITIVE_INFINITY;
        }else{
        slope = ((double)(that.y-this.y))/((double)(that.x-this.x));
        }
        return slope;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
       if(this.y < that.y){
    	   return -1; //this.y is less than that.y
       }else if(this.y==that.y){
    	   if(this.x<that.x){
    		   return -1; //(x0,y0) is less than (x1,y0)
    	   }else if(this.x==that.x){
        	   return 0;
           }else{ return +1;}
       }else{
    	   return +1;
       }
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {

	
    }
}
