/*
 *    ===============================================================================
 *    MovingShape.java : The superclass of all shapes.
 *    A shape has a point (top-left corner).
 *    A shape defines various properties, including selected, colour, width and height.
 *    
 *    UPI: tlap632
 *    Name: Trisha Lapiz
 *    ===============================================================================
 */

import java.awt.*;
public abstract class MovingShape {

    public int marginWidth, marginHeight; // the margin of the animation panel area
    protected Point topLeft;             // the top left corner of shapes
    protected int width, height;            // the width and height of shapes
    protected MovingPath path;            // the moving path of shapes
    protected Color borderColor, fillColor;        // the border colour of shapes
    protected boolean selected = false;    // draw handles if selected
    protected static final int defaultWidth=50, defaultHeight=50, defaultMarginWidth=800, defaultMarginHeight=500;
    protected static final Color defaultFillColor=Color.blue, defaultBorderColor=Color.orange;
    protected static final int defaultPath = 0;

    /** constructor to create a shape with default values
     */
    public MovingShape() {
        this(0, 0, defaultWidth, defaultHeight, defaultMarginWidth, defaultMarginHeight, defaultBorderColor,defaultFillColor, defaultPath); // the default properties
    }

    /** constructor to create a shape
     * @param x         the x-coordinate of the new shape
     * @param y        the y-coordinate of the new shape
     * @param w         the width of the new shape
     * @param h         the height of the new shape
     * @param mw         the margin width of the animation panel
     * @param mh        the margin height of the animation panel
     * @param c        the colour of the new shape
     * @param typeOfPath         the path of the new shape
     */
    public MovingShape(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, int pathType) {
        topLeft = new Point(x,y);
        width = w;
        height = h;
        marginWidth = mw;
        marginHeight = mh;
        borderColor = bc;
        fillColor = fc;
        setPath (pathType);
    }

    /** Return the x-coordinate of the shape.
     * @return the x coordinate
     */
    public int getX() { return topLeft.x; }
    /** Return the y-coordinate of the shape.
     * @return the y coordinate
     */
    public int getY() { return topLeft.y;}
    /** Set the width of the shape.
     * @param w     the width value
     */
    public void setX(int x) { this.topLeft.x = x; }
    public void setY(int y) { this.topLeft.y = y; }
    /** Set the width of the shape.
     * @param h     the width value
     */
    public void setWidth(int w) { width = w; }

    /** Set the height of the shape.
     * @param h     the height value
     */
    public void setHeight(int h) { height = h; }

    /** Return the selected property of the shape.
     * @return the selected property
     */
    public boolean isSelected() { return selected; }

    /** Set the selected property of the shape.
     *    When the shape is selected, its handles are shown.
     * @param s     the selected value
     */
    public void setSelected(boolean s) { selected = s; }

    /** Set the border colour of the shape.
     * @param c     the border colour
     */
    public void setBorderColor(Color c) { borderColor = c; }

    /** Set the border colour of the shape.
     * @param c     the border colour
     */
    public void setFillColor(Color fc) { fillColor = fc; }
    /**
     * Return a string representation of the shape, containing
     * the String representation of each element.
     */

    public String toString() {
        return "[" + this.getClass().getName() + "," + topLeft.x + "," + topLeft.y + "]";
    }

    /** Reset the margin for the shape
     * @param w     the margin width
     * @param h     the margin height
     */
    public void setMarginSize(int w, int h) {
        marginWidth = w;
        marginHeight = h;
    }

    public abstract boolean contains(Point p);

    /** Draw the handles of the shape
     * @param g     the Graphics control
     */
    public void drawHandles(Graphics g) {
        // if the shape is selected, then draw the handles
    	Graphics2D g2d = (Graphics2D) g;
        if (isSelected()) {
            g2d.setColor(Color.black);
            g2d.fillRect(topLeft.x -2, topLeft.y-2, 4, 4);
            g2d.fillRect(topLeft.x + width -2, topLeft.y + height -2, 4, 4);
            g2d.fillRect(topLeft.x -2, topLeft.y + height -2, 4, 4);
            g2d.fillRect(topLeft.x + width -2, topLeft.y-2, 4, 4);
        }
    }
    /** abstract draw method
     * draw the shape
     * @param g     the Graphics control
     */
    public abstract void draw(Graphics g);

    /** Set the path of the shape.
     * @param pathID     the integer value of the path
     *    MovingPath.FALLING is the falling path
     */
    public void setPath(int pathID) {
        switch (pathID) {
            case 0 : {
                path = new FallingPath();
                break;
            }
             case 1 : {
                path = new BouncingPath(5, 10);
                break;
            } 
             case 2: {
            	path = new LeftToRightPath();
                break;
            	}
            }
    }

    /** move the shape by the path
     */
    public void move() {
        path.move();
    }

    // Inner class ===================================================================== Inner class
    /*
     *    ===============================================================================
     *    MovingPath : The superclass of all paths. It is an inner class.
     *    A path can change the current position of the shape.
     *    ===============================================================================
     */

    public abstract class MovingPath { // MEMBER CLASS
        protected int deltaX, deltaY; // moving distance

        /** constructor
         */
        public MovingPath() { }

        /** abstract move method
        * move the shape according to the path
        */
        public abstract void move();
    }

    /*
     *  ===============================================================================
     *  FallingPath : A falling path.
     *  ===============================================================================
     */
    public class FallingPath extends MovingPath {
        private double am = 0, stx =0, sinDeltax = 0;

        /** constructor to initialise values for a falling path
        */
        public FallingPath() {
            am = Math.random() * 20; //set amplitude variables [moving side to side]
            stx = 0.5; //set step variables
            deltaY = 5;
            sinDeltax = 0;
       }

       /** move the shape
       */
       public void move() {
           sinDeltax = sinDeltax + stx; // sinDeltax = 0.5
           topLeft.x = (int) Math.round(topLeft.x + am * Math.sin(sinDeltax)); // math.sin(0.5) = 
           topLeft.y = topLeft.y + deltaY;
           if (topLeft.y > marginHeight) // if it reaches the bottom of the frame, start again from the top
               topLeft.y = 0;
       }
    }
    /*
     *  ===============================================================================
     *  BouncingPath : A Bouncing path.
     *  ===============================================================================
     */
    public class BouncingPath extends MovingPath {

         /** constructor to initialise values for a bouncing path
         */
        public BouncingPath(int dx, int dy) { 
            deltaX = dx; // 5
            deltaY = dy; // 10
         }

        /** move the shape
         */
        public void move() {
             topLeft.x = topLeft.x + deltaX;
             topLeft.y = topLeft.y + deltaY;

             if ((topLeft.x < 0) && (deltaX < 0)) { // if both numbers are negative
                 deltaX = -deltaX;
                 topLeft.x = 0;
             }
             else if ((topLeft.x + width > marginWidth) && (deltaX > 0)) {
                 deltaX = -deltaX;
                 topLeft.x = marginWidth - width;
             }
             if ((topLeft.y< 0) && (deltaY < 0)) {
                 deltaY = -deltaY;
                 topLeft.y = 0;
             }
             else if((topLeft.y + height > marginHeight) && (deltaY > 0)) {
                 deltaY = -deltaY;
                 topLeft.y = marginHeight - height;
             }
        }
    }
    /*
     *  ===============================================================================
     *  FLoating Path : A path going left to right
     *  ===============================================================================
     */
    public class LeftToRightPath extends MovingPath {

        /** constructor to initialise values for a falling path
        */
        public LeftToRightPath() {
            deltaX = 5;
       }

       /** move the shape
       */
        public void move() { 
        	topLeft.x = topLeft.x + deltaX;
        	if (topLeft.x > marginWidth) // if it reaches the right of the frame, start again from the left
        		topLeft.x = 0;
       }
    }
}