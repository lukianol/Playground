package geometry;

public class Point {
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public double x;
	public double y;
	
	@Override
	  public boolean equals(Object ob) {
	    if (ob == null) return false;
	    if (ob.getClass() != getClass()) return false;
	    Point other = (Point)ob;
	    return other.x == this.x && other.y == this.y;
	  }
}
