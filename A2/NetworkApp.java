 import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.math.plot.*;

/**
 * @author Trisha Lapiz tlap632
 */

public class NetworkApp extends JFrame {

	private JPanel radioButtonPanel, graphPanel, comboBoxPanel;
	private JRadioButton source, destination;
	private JMenuBar menubar;
	private JMenu chooseFile;
	private JMenuItem traceFile, quit;
	private Font font;
	private ArrayList<Packet> p = new ArrayList<Packet>();
	private JComboBox<Object> box = new JComboBox<Object>();
	private DefaultComboBoxModel<Object> srcModel, destModel;

	/**
	 * This constructs a JFrame which displays a group of radiobuttons, a menu bar,
	 * combobox to select source / destination hosts and a graph to display network
	 * traffic for a particular host address.
	 */
	public NetworkApp() {
		super("Network Packet Transmission Visualiser");
		setLayout(null);
		add(menuBar());
		add(radioButtonPanel());
		add(combobox());
		add(graphPanel());
		font = new Font("Helvetica", Font.PLAIN, 15);
		setFont(font);
	}

	/**
	* Returns a graph panel displaying the initial graph.
	*
	* @return graphPanel
	*/
	public JPanel graphPanel() {
		graphPanel = new JPanel();
		graphPanel.setBackground(Color.WHITE);
		graphPanel.setBounds(0, 122, 1000, 325);

		return graphPanel;
	}

	/**
	 * Plots the x and y coordinates when a host address is selected.
	 * @param ipFilter
	 * @param isHost
	 * @param endTime
	 * @param interval
	 */
	public void getGraphData(Object ipFilter, boolean isHost, double endTime, int interval) {
	    int slots = (int) Math.ceil((endTime / interval));
	    double[] x = new double[slots];
	    double[] y = new double[slots];

	    double bytes = 0;
	    int index = 0;

	    // y coordinates
	    if (source.isSelected() == true) {
	    	if (isHost == true) { // if it is a source host
		        for (int i=0; i<p.size(); i++) {
		            if (p.get(i).getSourceHost().equals(ipFilter)) { // if we get the address we want
		                index = (int) (p.get(i).getTimeStamp() / interval); // RETURNS FLOAT timestamp is in that interval
		                bytes += p.get(i).getIpPacketSize();
		            } else {
		                continue;
		            }
		            y[index] += bytes;
		        }
		    }
	    } else {
	    	if (isHost == true) { // if it is a source host
		        for (int i=0; i<p.size(); i++) {
		        	if (p.get(i).getDestinationHost().equals(ipFilter)) { // if we get the address we want
		                index = (int) (p.get(i).getTimeStamp() / interval); // RETURNS FLOAT timestamp is in that interval
		                bytes += p.get(i).getIpPacketSize();
		            } else {
		                continue;
		            }
		            y[index] += bytes;
		        }
		    }
	    }

	    // x coordinates
	    for (int j=0; j<x.length; j++){
	        x[j] = interval * j;
	    }

	    Plot2DPanel plots = new Plot2DPanel();
	    plots.removeAllPlots();
	    plots.setPreferredSize(new Dimension(1000, 325));
		plots.addScatterPlot("my plot", Color.RED, x, y);
		graphPanel.add(plots);
	}

	/**
	 * Returns a combobox containing a list of IPv4 host addresses.
	 *
	 * @return comboBoxPanel
	 */
	public JPanel combobox() {
		comboBoxPanel = new JPanel();
		comboBoxPanel.setBounds(200, 22, 250, 100);
		comboBoxPanel.add(box);
		comboBoxPanel.setBackground(Color.YELLOW);
		box.setPreferredSize(new Dimension(225,100));
		box.setFont(font);
		box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// getGraphData(Object ipFilter, boolean isHost, double endTime, int interval)
				getGraphData(box.getSelectedItem(), true, p.get(p.size()-1).getTimeStamp(), (int) (p.get(p.size()-1).getTimeStamp()) / 2);
			}
		});
		box.setLocation(100, 22);
		box.setVisible(false);

		return comboBoxPanel;
	}

	/**
	 * This method returns a panel which creates radiobuttons,
	 * and puts them in a buttongroup.
	 *
	 * @return radioButtonPanel;
	 */
	public JPanel radioButtonPanel() {
		radioButtonPanel = new JPanel();
		radioButtonPanel.setOpaque(true);
		radioButtonPanel.setBounds(0, 22, 200, 100);
		radioButtonPanel.setLayout(new BoxLayout(radioButtonPanel, BoxLayout.Y_AXIS));
		radioButtonPanel.setBorder(new EmptyBorder(10,6,0,0));

		font = new Font("Helvetica", Font.PLAIN, 15);

		//new radiobuttons
		source = new JRadioButton("Source Hosts", true); // 'source' is selected by default
		destination = new JRadioButton("Destination Hosts");

		// set button fonts
		source.setFont(font);
		destination.setFont(font);

		// if buttons are clicked
		source.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					// make combobox appear
				Object[] sourceArray = getUniqueSortedSourceHosts(p); // returns Object[] array
				srcModel = new DefaultComboBoxModel<Object>(sourceArray);
					box.setModel(srcModel);
					box.setLocation(100, 22);
					box.setVisible(true);
				}
			});

		destination.addActionListener(new ActionListener() { // WHEN THEY SELECT DESTINATION HOSTS
			public void actionPerformed(ActionEvent e) {
					// make combobox appear
					Object[] destArray = getUniqueSortedDestHosts(p); // returns Object[] array
					destModel = new DefaultComboBoxModel<Object>(destArray);
					box.setModel(destModel);
					box.setLocation(100, 22);
					box.setVisible(true);
				}
			});

		//BUTTONGROUP
		ButtonGroup radiobuttons = new ButtonGroup();
		radiobuttons.add(source);
		radiobuttons.add(destination);

		// add radiobuttons and radiobutton panel into frame
		radioButtonPanel.add(source);
		radioButtonPanel.add(destination);

		return radioButtonPanel;
	}

	/**
	 * This method returns a menu bar, which displays two menu items.
	 * One menu item is for choosing a file from the current directory
	 * and the other menu item closes the frame.
	 *
	 * @return menubar
	 */
	public JMenuBar menuBar() {
		menubar = new JMenuBar();
		setJMenuBar(menubar);

		chooseFile = new JMenu("File");
		chooseFile.setMnemonic('F');
		traceFile = new JMenuItem("Open trace file");
		traceFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser("."); // inserting a dot means it opens at the folder this Java program is stored in
					int val = chooser.showOpenDialog(NetworkApp.this);
					if (val == JFileChooser.APPROVE_OPTION) {
						File f = chooser.getSelectedFile();
						p.clear(); // every time a file is selected, clear existing hosts in combobox
						try {
							p = getPacket(f); // returns an arraylist of packets
						} catch (IOException e1) {
							System.out.println("File not found");
						}

					}

				}
			}); // anonymous inner class
		quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		menubar.add(chooseFile);
		chooseFile.add(traceFile);
		chooseFile.add(quit);
		return menubar;
	}


	/**
	 * This method takes a trace file, reads it, and adds a line from the file into an arraylist.
	 *
	 * If a line from the trace file contains a valid IPv4 source / destination host address, the line from the file
	 * is added into the array list.
	 *
	 * @return packet
	 * @throws IOException
	 */
	public ArrayList<Packet> getPacket(File f) throws IOException { /**JUST GETTING THE HOST**/

	    Scanner input = null;

	    try {
	        input = new Scanner(f); // gets all the lines from the trace file
	    } catch (IOException e) { // xxx.txt (The system cannot find the file specified)
	        e.printStackTrace();
	    }

	    while (input.hasNext()) {
	        String somePacket = input.nextLine(); // read 1 line of text file at a time

	        Packet packet = new Packet(somePacket); // argument is String type, somePacket is String
		    String source = packet.getSourceHost(); // get the source host CHECK IF IT'S NULL
		    String destination = packet.getDestinationHost();
		    if (source == null | destination == null) {
		    	continue;
		    } else {
		    	p.add(packet);
		    }
	    }
	    input.close();
	    return p;
	}

	/**
	 * This method creates an array with source hosts.
	 *
	 * @param packets
	 * @return an object type array of source hosts
	 */
	public static Object[] getUniqueSortedSourceHosts(ArrayList<Packet> packets) {
	    HashSet<String> uniqueStrings = new HashSet<String>();
	    ArrayList<sourceHost> sourceHosts = new ArrayList<sourceHost>();

	    for (int i=0; i<packets.size(); i++) {
	        uniqueStrings.add(packets.get(i).getSourceHost());
	    }

	    Iterator<String> i = uniqueStrings.iterator();
	    while (i.hasNext()) {
	    	sourceHosts.add(new sourceHost(i.next()));
	    }

	    Collections.sort(sourceHosts);
	    Object[] anArray = sourceHosts.toArray();
	    return anArray;
	}

	/**
	 * This method creates an array with destination hosts.
	 *
	 * @param packets
	 * @return an object type array of destination hosts
	 */
	public Object[] getUniqueSortedDestHosts(ArrayList<Packet> packets) {
		HashSet<String> uniqueStrings = new HashSet<String>();
	    ArrayList<destHost> destHosts = new ArrayList<destHost>();

	    for (int i=0; i<packets.size(); i++) {
	        uniqueStrings.add(packets.get(i).getDestinationHost());
	    }

	    Iterator<String> i = uniqueStrings.iterator();
	    while (i.hasNext()) {
	        destHosts.add(new destHost(i.next()));
	    }

	    Collections.sort(destHosts);
	    Object[] anArray = destHosts.toArray();
	    return anArray;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				NetworkApp n = new NetworkApp();
				n.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				n.setSize(1000, 500);
				n.setVisible(true);
			}
		});
	}
}
