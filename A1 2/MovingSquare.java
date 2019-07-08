/*
 *  ===============================================================================
 *  MovingSquare.java : A shape that is an square.
 *  A square has 4 handles shown when it is selected (by clicking on it).
 *  UPI: tlap632
 *  Name: Trisha Lapiz
 *  ===============================================================================
 */
import java.awt.*; 
public class MovingSquare extends MovingRectangle { // NOT MOVING SHAPE
	
	public MovingSquare() {
		super(); 
	}
	
	public MovingSquare(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, int pathType) {
		super(x, y, Math.min(w, h), Math.min(w, h), mw, mh, bc, fc, pathType);
	}
	
	public void draw(Graphics g) {
		super.draw(g); 
		
	}
	
	public boolean contains(Point p) { 
		int size = Math.min(width,  height);
		double x2 = this.topLeft.getX() + size;
		double y2 = this.topLeft.getY() + size;
		
		if ((x2 >= p.getX() && p.getX() >= this.topLeft.getX()) && (y2 >= p.getY() && p.getY() >= this.topLeft.getY())) {
            return true;
        } else {
            return false;
        }
	}
	
	/** change fill/border colour, size and bouncing path**/
	public void setFillColor(Color fc) {
		super.setFillColor(fc);
	}
	
	public void setBorderColor(Color c) {
		super.setBorderColor(c);
	}
	
	public void setHeight(int h) { 
		super.setHeight(h);
		super.setWidth(h);
	}
	
	public void setWidth(int w) { 
		super.setWidth(w);
		super.setHeight(w);
	}
	
	public void setPath(int pathID) {
		super.setPath(pathID);
	}
}
