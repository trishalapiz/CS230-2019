/*
 *  ===============================================================================
 *  MovingRectangle.java : A shape that is an rectangle.
 *  A rectangle has 4 handles shown when it is selected (by clicking on it).
 *  UPI: tlap632
 *  Name: Trisha Lapiz
 *  ===============================================================================
 */
import java.awt.*; 
public class MovingRectangle extends MovingShape {
	
	//create rectangle with default values
	public MovingRectangle() { 
		super();
	}
	
	//create rectangle with specified values
	public MovingRectangle(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, int pathType) {
		super(x, y, w, h, mw, mh, bc, fc, pathType);
	}
	
	// draw rectangle
	// maybe change to drawRect(int x, int y, int width, int height) ?
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(fillColor);
		g2d.fillRect(topLeft.x, topLeft.y, width, height);
		g2d.setColor(borderColor);
		g2d.drawRect(topLeft.x, topLeft.y, width, height);
		drawHandles(g2d);
	}
	
	public boolean contains(Point p) {
		double x2 = this.topLeft.getX() + width;
		double y2 = this.topLeft.getY() + height;
		
		if ((x2 >= p.getX() && p.getX() >= this.topLeft.getX()) && (y2 >= p.getY() && p.getY() >= this.topLeft.getY())) {
            return true;
        } else {
            return false;
        }
	}
	
}
