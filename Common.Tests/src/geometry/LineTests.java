package geometry;

import static org.junit.Assert.*;

import org.junit.Test;

public class LineTests {

	private static final double DELTA = 1e-15;
	
	@Test
	public void crossedLines() {
		Point a = new Point(1, 3);
		Point b = new Point(3, 3);
		Point c = new Point(3, 1);
		Point d = new Point(1, 1);
		
		Line l1 = new Line(a, c);
		Line l2 = new Line(b, d);
		Point i = l1.getIntersection(l2);
		assertEquals(2.0, i.x, DELTA);
		assertEquals(2.0, i.y, DELTA);
	}
	
	@Test
	public void crossedLinesOppositeDirection() {
		Point a = new Point(1, 3);
		Point b = new Point(3, 3);
		Point c = new Point(3, 1);
		Point d = new Point(1, 1);
		
		Line l1 = new Line(c, a);
		Line l2 = new Line(d, b);
		Point i = l1.getIntersection(l2);
		assertEquals(2.0, i.x, DELTA);
		assertEquals(2.0, i.y, DELTA);
	}
	
	@Test
	public void parallelLines() {
		Point a = new Point(1, 3);
		Point b = new Point(3, 3);
		Point c = new Point(3, 1);
		Point d = new Point(1, 1);
		
		Line l1 = new Line(a, b);
		Line l2 = new Line(d, c);
		Point i = l1.getIntersection(l2);		
		assertFalse(Double.isFinite(i.x));
		assertFalse(Double.isFinite(i.y));
	}
	
	@Test
	public void parallelLinesReverted() {
		Point a = new Point(1, 3);
		Point b = new Point(3, 3);
		Point c = new Point(3, 1);
		Point d = new Point(1, 1);
		
		Line l1 = new Line(a, b);
		Line l2 = new Line(c, d);
		Point i = l1.getIntersection(l2);		
		assertFalse(Double.isFinite(i.x));
		assertFalse(Double.isFinite(i.y));
	}
	
	@Test
	public void sameLine() {
		Point a = new Point(1, 3);
		Point b = new Point(3, 3);
		Point c = new Point(3, 1);
		Point d = new Point(1, 1);
		
		Line l1 = new Line(a, b);
		Line l2 = new Line(a, b);
		Point i = l1.getIntersection(l2);		
		assertFalse(Double.isFinite(i.x));
		assertFalse(Double.isFinite(i.y));
	}
	
	@Test
	public void lineAsPointIntersected() {
		Point a = new Point(1, 3);
		Point c = new Point(3, 1);
		Point x = new Point(2, 2);
		
		Line l1 = new Line(a, c);
		Line l2 = new Line(x, x);
		Point i = l1.getIntersection(l2);		
		assertEquals(x.x, i.x, DELTA);
		assertEquals(x.y, i.y, DELTA);
	}
	
	@Test
	public void lineAsPointNotIntersected() {
		Point a = new Point(1, 3);
		Point c = new Point(3, 1);
		Point x = new Point(1, 1);
		
		Line l1 = new Line(a, c);
		Line l2 = new Line(x, x);
		Point i = l1.getIntersection(l2);		
		assertFalse(Double.isFinite(i.x));
		assertFalse(Double.isFinite(i.y));
	}
	
	@Test
	public void potentiallyCrossedLines() {
		Point a = new Point(1, 3);
		Point b = new Point(3, 3);
		Point c = new Point(3, 1);
		Point d = new Point(4, 4);
		
		Line l1 = new Line(a, c);
		Line l2 = new Line(b, d);
		Point i = l1.getIntersection(l2);
		assertFalse(Double.isFinite(i.x));
		assertFalse(Double.isFinite(i.y));
	}
	
	@Test
	public void differentLineSegmentsSameLine() {
		Point a = new Point(2, 2);
		Point b = new Point(1, 1);
		Point c = new Point(3, 3);
		Point d = new Point(4, 4);
		
		Line l1 = new Line(a, b);
		Line l2 = new Line(c, d);
		Point i = l1.getIntersection(l2);
		assertEquals(l1.getSlope(), l2.getSlope(), DELTA);
		assertEquals(l1.getIntercept(), l2.getIntercept(), DELTA);
	}

}
