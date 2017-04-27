package geometry;

public class Line {
	private static final double DELTA = 1e-15;
	public Line (Point start, Point end) {
		if (start == end){
			throw new IllegalArgumentException("Line has to have different points.");
		}
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
		
		Point potentialIntersection = new Point(x, y);
		if (this.mayBelongToLineSegment(potentialIntersection) && line.mayBelongToLineSegment(potentialIntersection)){
			return potentialIntersection;
		}
		return new Point(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
	}
	
	private boolean mayBelongToLineSegment(Point point)
	{
		return point.y <= Math.max(start.y, end.y)
				&& point.x <= Math.max(start.x, end.x)
				&& point.y >= Math.min(start.y, end.y)
				&& point.x >= Math.min(start.x, end.x);
	}
	
	public double getSlope() {
		return (end.y - start.y) / (end.x  - start.x);
	}
	
	public double getIntercept() {
		return -this.getSlope()*start.x + start.y;
	}
}
