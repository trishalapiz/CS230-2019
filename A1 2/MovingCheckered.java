/*
 *  ===============================================================================
 *  MovingCheckered.java : A shape that has an alternating square pattern with a circle and diamond in the middle.
 *  The checkered shape has 4 handles shown when it is selected (by clicking on it).
 *  UPI: tlap632
 *  Name: Trisha Lapiz
 *  ===============================================================================
 */
import java.awt.*;

public class MovingCheckered extends MovingSquare{

	public MovingCheckered() {
		super();
	}
	
	public MovingCheckered(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, int pathType) {
		super(x, y, w, h, mw, mh, bc, fc, pathType);
	}
	
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		Graphics2D smallCircle = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.drawRect(topLeft.x, topLeft.y, width, height);
		
		int distance = width/2;
		
		for (int i = 0; i < 2; i++) {
			if (i % 2 == 0) {
				g2d.setColor(borderColor); // yellow
				g2d.fillRect(topLeft.x, topLeft.y + (i * distance), distance, distance);
			} else {
				g2d.setColor(fillColor); //blue
				g2d.fillRect(topLeft.x, topLeft.y + (i * distance), distance, distance);
			}
		}

		for (int j = 0; j < 2; j++) {
			if (j % 2 == 0) {
				g2d.setColor(fillColor); // blue
				g2d.fillRect(topLeft.x + ((j+1) * distance), topLeft.y + (j * distance), distance, distance);
			} else {
				g2d.setColor(borderColor); // yellow
				g2d.fillRect(topLeft.x + (j * distance), topLeft.y + (j * distance), distance, distance);
			}
		}
			
		smallCircle.setColor(Color.white);
		smallCircle.fillOval(topLeft.x + (distance/2), topLeft.y + (distance/2), distance, distance);
		
		// diamond
		smallCircle.setColor(Color.black);
		smallCircle.drawLine(topLeft.x + distance, topLeft.y + (distance/2), topLeft.x + distance + (distance/2), topLeft.y + distance);
		smallCircle.drawLine(topLeft.x + distance + (distance/2), topLeft.y + distance, topLeft.x + distance, topLeft.y + distance + (distance/2));
		smallCircle.drawLine(topLeft.x + distance, topLeft.y + distance + (distance/2), topLeft.x + (distance/2), topLeft.y + distance);
		smallCircle.drawLine(topLeft.x + (distance/2), topLeft.y + distance, topLeft.x + distance, topLeft.y + (distance/2));
		
		// vertical line
		smallCircle.drawLine(topLeft.x + distance, topLeft.y + (distance/2), topLeft.x + distance, topLeft.y + distance + (distance/2));
		// horizontal line
		smallCircle.drawLine(topLeft.x + (distance/2), topLeft.y + distance, topLeft.x + distance + (distance/2), topLeft.y + distance);
		
		drawHandles(g2d);
		
	}
	
	public boolean contains(Point p) {
		// must change both width and height to get the shape to change sizes = FIX IMMEDIATELY
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
