package a_Introductory;

public class Line {
	private Point p1, p2;
	
	Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Vector2D getVector() {
		return new Vector2D(p1, p2);
	}
	
	public Double getLength() {
		return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
	}
	
	public Boolean isSameLengthAs(Line l) {
		return (Math.abs(getLength() - l.getLength()) < 0.00001);
	}
}
