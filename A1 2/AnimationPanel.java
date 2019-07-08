/*
 *    ==========================================================================================
 *    AnimationPanel.java : Moves shapes around on the screen according to different paths.
 *    It is the main drawing area where shapes are added and manipulated.
 *    It also contains a popup menu to clear all shapes.
 *    UPI: tlap632
 *    Name: Trisha Lapiz
 *    ==========================================================================================
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class AnimationPanel extends JComponent implements Runnable {
    private Thread animationThread = null;    // the thread for animation
    private ArrayList<MovingShape> shapes; // arraylist that stores list of shapes
    private int currentWidth=50, currentHeight=100, currentShapeType, currentPath; // the current shape type, // the current path type
    private Color currentBorderColor = Color.orange;  // the current border colour of a shape
    private Color currentFillColor = Color.blue;  // the current fill colour of a shape
    private int delay = 30;         // the current animation speed
    JPopupMenu popup;                // popup menu

     /** Constructor of the AnimationPanel
        */
    public AnimationPanel() {
    	shapes = new ArrayList<MovingShape>();
		Insets insets = getInsets();
        int marginWidth = getWidth() - insets.left - insets.right; //check with original
        int marginHeight = getHeight() - insets.top - insets.bottom;//check with original
        popup = new JPopupMenu(); //create the popup menu
        makePopupMenu();
        // add the mouse event to handle popup menu

		addMouseListener( new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				maybeShowPopup(e);
			}
			public void mouseReleased(MouseEvent e) {
				maybeShowPopup(e);
			}
			private void maybeShowPopup(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
            public void mouseClicked( MouseEvent e ) {
                if (animationThread != null) {  // if the animation has started, then
                	boolean found = false;
                    for (int i=0;i<shapes.size();i++)
                    	if ( shapes.get(i).contains(e.getPoint()) ) { // if the mousepoint is within a shape, then set the shape to be selected/deselected
                    		shapes.get(i).setSelected(!shapes.get(i).isSelected());
                            found = true;
						}
					if (!found) createNewShape(e.getX(), e.getY());
            	}
			}
	    });
    }

    /** create a new shape
     * @param x     the x-coordinate of the mouse position
     * @param y    the y-coordinate of the mouse position
     */
    protected void createNewShape(int x, int y) {
        // get the margin of the frame
        Insets insets = getInsets();
        int marginWidth = getWidth() - insets.left - insets.right;
        int marginHeight = getHeight() - insets.top - insets.bottom;
        // create a new shape dependent on all current properties and the mouse position
        switch (currentShapeType) {
            case 0: {
            	shapes.add(new MovingOval(x, y, currentWidth, currentHeight, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
                break;
            }
            case 1: {
            	shapes.add(new MovingRectangle(x, y, currentWidth, currentHeight, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
            	break;
            }
            case 2: {
            	shapes.add(new MovingSquare(x, y, currentWidth, currentHeight, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
            	break;
            }
            case 3: {
            	shapes.add(new MovingSquarePattern(x, y, currentWidth,	currentHeight, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
            	break;
            }
            case 4: {
            	shapes.add(new MovingQuadPie(x, y, currentWidth, currentHeight, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
            	break;
            } case 5: { // bonus shape
            	shapes.add(new MovingCheckered(x, y, currentWidth, currentHeight, marginWidth, marginHeight, currentBorderColor, currentFillColor, currentPath));
            	break;
            }
       }
    }

    /** set the current shape type
     * @param s    the new shape type
     */
    public void setCurrentShapeType(int s) {
        currentShapeType = s;
    }

    /** set the current path type and the path type for all currently selected shapes
     * @param t    the new path type
     */
    public void setCurrentPathType(int index) {
        currentPath = index;
        for (MovingShape s : shapes)
			if ( s.isSelected()) // shapes.get(i).isSelected()
				s.setPath(index); // shapes.get(i).setPath(index)
 			
    }

	/** get the current width
	 * @return currentWidth - the width value
	 */
	public int getCurrentWidth() {
		return currentWidth;
	}
	/** set the current width and the width for all currently selected shapes
	 * @param w	the new width value
	 */
	public void setCurrentWidth(int w) {
		currentWidth = w;
		for (MovingShape s : shapes)
			if ( s.isSelected()) // shapes.get(i).isSelected()
				s.setWidth(currentWidth); // shapes.get(i).setWidth(currentWidth)
	}

	/** get the current height
	 * @return currentHeight - the height value
	 */
	public int getCurrentHeight() {
		return currentHeight;
	}
	/** set the current height and the height for all currently selected shapes
	 * @param h	the new height value
	 */
	public void setCurrentHeight(int h) {
		currentHeight = h;
		for (MovingShape s : shapes) // for (int i=0;i<shapes.size();i++) 
			if ( s.isSelected()) // shapes.get(i).isSelected()
				s.setHeight(currentHeight); // shapes.get(i).setHeight(currentHeight)
	}

	/** get the current border colour
	 * @return currentBorderColor - the border colour value
	 */
	public Color getCurrentBorderColor() {
		return currentBorderColor;
	}
 	/** set the current border colour and the border colour for all currently selected shapes
	 * @param bc	the new border colour value
	 */
	public void setCurrentBorderColor(Color bc) {
		currentBorderColor = bc;
		for (MovingShape s: shapes) 
			if ( s.isSelected()) // shapes.get(i).isSelected()
				s.setBorderColor(currentBorderColor); // shapes.get(i).setBorderColor(currentBorderColor)
	}

	/** get the current fill colour
	 * @return currentFillColor - the fill colour value
	 */
	public Color getCurrentFillColor() {
		return currentFillColor;
	}
	/** set the current fill colour and the border colour for all currently selected shapes
     * @param bc    the new fill colour value
     */
    public void setCurrentFillColor(Color fc) {
        currentFillColor = fc;
        //complete this
        for (MovingShape s: shapes) 
			if ( s.isSelected())
				s.setFillColor(currentFillColor);
    }

   /** reset the margin size of all shapes
     */
    public void resetMarginSize() {
        Insets insets = getInsets();
        int marginWidth = getWidth() - insets.left - insets.right;
        int marginHeight = getHeight() - insets.top - insets.bottom ;
        for (MovingShape s: shapes)
			s.setMarginSize(marginWidth,marginHeight);
    }

	/** remove all shapes
     */
    public void clearAllShapes() {
        shapes.clear();
    	
    }

    /**    update the painting area
     * @param g    the graphics control
     */
    public void update(Graphics g){
        paint(g);
    }

    /**    move and paint all shapes within the animation area
     * @param g    the Graphics control
     */
    public void paintComponent(Graphics g) {
        for (MovingShape s: shapes) {
        	s.move();
        	s.draw(g);
        }
    }
    // you don't need to make any changes after this line ______________
    /** create the popup menu for our animation program
     */
    protected void makePopupMenu() {
        JMenuItem menuItem;
     // clear all
        menuItem = new JMenuItem("Clear All");
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearAllShapes();
            }
        });
        popup.add(menuItem);
     }
    /** change the speed of the animation
     * @param newValue     the speed of the animation in ms
     */
    public void adjustSpeed(int newValue) {
        if (animationThread != null) {
            stop();
            delay = newValue;
            start();
        }
    }
    /**    When the "start" button is pressed, start the thread
     */
    public void start() {
        animationThread = new Thread(this);
        animationThread.start();
    }
    /**    When the "stop" button is pressed, stop the thread
     */
    public void stop() {
        if (animationThread != null) {
            animationThread = null;
        }
    }
    /** run the animation
     */
    public void run() {
        Thread myThread = Thread.currentThread();
        while(animationThread==myThread) {
            repaint();
            pause(delay);
        }
    }
    /** Sleep for the specified amount of time
     */
    private void pause(int milliseconds) {
        try {
            Thread.sleep((long)milliseconds);
        } catch(InterruptedException ie) {}
    }
}
