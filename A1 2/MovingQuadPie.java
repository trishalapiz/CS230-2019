/*
 *  ===============================================================================
 *  MovingQuadPie.java : A shape that is a quad pie.
 *  A quad pie has 4 handles shown when it is selected (by clicking on it).
 *  UPI: tlap632
 *  Name: Trisha Lapiz
 *  ===============================================================================
 */
import java.awt.*;
import java.awt.geom.Arc2D;
public class MovingQuadPie extends MovingSquare { // can be any shape (oval/square)

	public MovingQuadPie() {
		this(0, 0, defaultWidth, defaultHeight, defaultMarginWidth, defaultMarginHeight, defaultBorderColor, defaultFillColor, defaultPath); // the default properties
	}

	public MovingQuadPie(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, int pathType) {
		super(x ,y ,w ,h ,mw ,mh ,bc ,fc , pathType);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; 
		
		int size = Math.min(width,  height);
		
		/**filled with orange color**/ // ORIGINAL CIRCLE
		g2d.setColor(borderColor); // fill with orange colour
		g2d.fillOval(topLeft.x, topLeft.y, size, size);
		g2d.drawOval(topLeft.x, topLeft.y, size, size);
		
		/**filled with white color**/ // SMALLER CIRCLE
		Graphics2D smallerCircle = (Graphics2D) g;
		smallerCircle.setColor(Color.WHITE);
		
		int newGap = size / 10; // i just picked height
		
		smallerCircle.fillOval(topLeft.x + newGap, topLeft.y + newGap, size - (2 * newGap), size - (2 * newGap));
		smallerCircle.drawOval(topLeft.x + newGap, topLeft.y + newGap, size - (2 * newGap), size - (2 * newGap));
		
		/**filled with blue color**/ // THE ARCS
		g2d.setColor(fillColor);
		g2d.drawArc(topLeft.x + newGap, topLeft.y + newGap, size - (2 * newGap), size - (2 * newGap), 0, 90);
		g2d.fillArc(topLeft.x + newGap, topLeft.y + newGap, size - (2 * newGap), size - (2 * newGap), 0, 90);
		g2d.drawArc(topLeft.x + newGap, topLeft.y + newGap, size - (2 * newGap), size - (2 * newGap), 180, 90);
		g2d.fillArc(topLeft.x + newGap, topLeft.y + newGap, size - (2 * newGap), size - (2 * newGap), 180, 90);
		drawHandles(g2d);
		
		
		
	}
	
	public boolean contains(Point p) {
		return super.contains(p);
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
	}
	
	public void setWidth(int w) {
		super.setWidth(w);
	}
	
	public void setPath(int pathID) {
		super.setPath(pathID);
	}
}
