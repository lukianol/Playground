package geometry;

public class Line {
	public Line (Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	public Point start;
	public Point end;
	
	public Point getIntersection(Line line) {		
		if (line == null) {
			throw new IllegalArgumentException();
		}
				
		double b1 = this.getIntercept();
		double b2 = line.getIntercept();
		double k1 = this.getSlope();
		double k2 = line.getSlope();
		
		double x = (b2 - b1) / (k1 - k2);
		double y = (k2 * b1 - k1* b2) / (k2 - k1);
		
		return new Point(x, y);
	}
	
	public double getSlope() {
		return (end.y - start.y) / (end.x  - start.x);
	}
	
	public double getIntercept() {
		return -this.getSlope()*start.x + start.y;
	}
}
