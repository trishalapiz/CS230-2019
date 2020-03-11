import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.math.plot.*;

public class Graph extends JPanel {    
	private Graphics g;
	
	public Graph(ArrayList<Packet> p, String add, boolean srcHost, double endTime, int interval) {
		int slots = (int) (endTime / interval);
		double[] x = new double[slots];
	    double[] y = new double[slots];
	    
	    if (srcHost == true) { // if it is a source host
	        for (int i=0; i<p.size(); i++) { 
	            if (p.get(i).getSourceHost().equals(add)) { // if we get the address we want
	                int index = (int) p.get(i).getTimeStamp() / interval; // RETURNS FLOAT timestamp is in that interval
	                y[index] = y[index] + p.get(i).getIpPacketSize();
	                // timestamp / interval
	                // y[index] plot at that position
	            } else {
	                continue;
	            }
	        }
	    }
	    
	    // x coordinates
	    for (int j=0; j<x.length; j++){
	        x[j] = interval * j;
	    }
	

//	public void draw(Graphics g) {
//		g.drawLine(50, 400, 950, 400);
//		g.drawLine(50, 130, 50, 400);
//	}
	    
	    Plot2DPanel plots = new Plot2DPanel();
		plots.addBarPlot("my plot", Color.RED, x, y);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		drawGraph(g);
	}
	
	public void drawGraph(Graphics g) {
		g.drawLine(50, 400, 950, 400);
		g.drawLine(50, 130, 50, 400);
	}
	
	

}
