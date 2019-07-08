/*
 *  ===============================================================================
 *  MovingSquarePattern.java : A shape that has a square pattern.
 *  A square pattern has 4 handles shown when it is selected (by clicking on it).
 *  UPI: tlap632
 *  Name: Trisha Lapiz
 *  ===============================================================================
 */
import java.awt.*;
public class MovingSquarePattern extends MovingSquare {

	public MovingSquarePattern() {
		super(); // the default properties
	}
	
	public MovingSquarePattern(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, int pathType) {
		super(x, y, w, h, mw, mh, bc, fc, pathType);
	}

	public void draw(Graphics g) {
		int size = Math.min(width,  height);
		int distance = size / 10;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(fillColor);
		// bottom left corner
		for (int i = 0; i < 11; i++) { 
			g2d.drawLine(topLeft.x, topLeft.y + (i * distance), topLeft.x + (i * distance), topLeft.y + size);
		}
		
		// top right corner
		for (int j = 0; j < 11; j++) { 
			g2d.drawLine(topLeft.x + (j * distance), topLeft.y, topLeft.x + size, topLeft.y + (j * distance));
		}
		drawHandles(g2d);
	}
	
	public boolean contains(Point p) {
		return super.contains(p);
	}
	
	public void setFillColor(Color fc) {
		super.setFillColor(fc);
	}
	
	public void setHeight(int h) {
		super.setHeight(h);
	}
	
	public void setWidth(int w) {
		super.setWidth(w);
	}
	
	public void setPath(int pathID) {
		super.setPath(pathID);
	}
	
}
