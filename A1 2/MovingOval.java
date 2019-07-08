/*
 *  ===============================================================================
 *  MovingCircle.java : A shape that is an oval.
 *  An oval/circle has 4 handles shown when it is selected (by clicking on it).
 *  UPI: tlap632
 *  Name: Trisha Lapiz
 *  ===============================================================================
 */
import java.awt.*;
public class MovingOval extends MovingShape {

	/** constructor to create an oval with default values
	 */
	public MovingOval() {
		this(0, 0, defaultWidth, defaultHeight, defaultMarginWidth, defaultMarginHeight, defaultBorderColor, defaultFillColor, defaultPath); // the default properties
	}

	/** constructor to create an oval shape
	 */
	public MovingOval(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, int pathType) {
		super(x ,y ,w ,h ,mw ,mh ,bc ,fc , pathType);
	}

	/** draw the oval with the fill colour
	 *  If it is selected, draw the handles
	 *  @param g	the Graphics control
	 */
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(fillColor);
		g2d.fillOval(topLeft.x, topLeft.y, width, height);
		g2d.setColor(borderColor);
		g2d.drawOval(topLeft.x, topLeft.y, width, height);
		drawHandles(g2d);
	}

	/** Returns whether the point is in the oval or not
	 * @return true if and only if the point is in the oval, false otherwise.
	 */
	public boolean contains(Point mousePt) {
		double dx, dy;
		Point EndPt = new Point(topLeft.x + width, topLeft.y + height);
		dx = (2 * mousePt.x - topLeft.x - EndPt.x) / (double) width;
		dy = (2 * mousePt.y - topLeft.y - EndPt.y) / (double) height;
		return dx * dx + dy * dy < 1.0;
	}
}