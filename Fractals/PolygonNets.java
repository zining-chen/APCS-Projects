/*  Polygon Nets
	- Program that takes input on how many sides of a polygon there are
	- Draws the lines to create the net of a flower in the center
*/

// Imports for swing, event, etc.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
// Import MATH
import java.lang.Math;;

// Class extends JFRAME
public class PolygonNets extends JFrame {

	DrawNet drawShape;

	// Constructor to declare size, location, etc/
	public PolygonNets () {
		// Set super and class title
		super("PolygonNets");
		// Declare the DrawNet for setContentPane
		drawShape = new DrawNet();
		// Set location, size, content pane
		setLocation(0, 0);
		setSize(1280, 800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setContentPane(drawShape);
		setVisible(true);
	}

	// Main calls constructor
	public static void main (String[] args) {
		PolygonNets runApp = new PolygonNets();
	}

}

// DrawNet extends JPANEL implements ACTIONLISTENER
class DrawNet extends JPanel {

	// Constant Values for center of screen
	private final int CENTER_X = 640;
	private final int CENTER_Y = 350;
	private final int AXIS_LENGTH = 300;

	// Create int axes, int frequency
	public int axes = 5;
	public int frequency = 20;

	// Class declaration
	FractalValues paintValues;

	// Constructor to initialize all values
	public DrawNet () {
		// Get the values from other class

		// Initialize paintValues
		paintValues = new FractalValues();

		// Border Layout, PaintValues in south
		setLayout(new BorderLayout());
		add(paintValues, BorderLayout.SOUTH);
	}

	public void paintComponent (Graphics g) {

		super.paintComponent(g);
		setBackground(Color.WHITE);
		
		// Call drawAxes(axes, Graphics)
		drawAxes(axes, frequency, g);
		System.out.println("Drawing Axes: " + "Axes: " + axes + " Frequency: " + frequency);

		axes = FractalValues.axisUser;
		frequency = FractalValues.frequencyUser;
		repaint();
	}

	public void drawAxes (int numAxes, int frequency, Graphics g) {

		// Axis angle for trig
		int AXIS_ANGLE = 360/numAxes;

		// Draw as many lines as axes
		for (int lineDrawn = 0; lineDrawn < numAxes; lineDrawn++) {
			// Declare x
			// Declare y
			int x;
			int y;

			// Use trig to determine current x and y
			x = CENTER_X + (int)(AXIS_LENGTH * Math.cos(Math.toRadians(lineDrawn * AXIS_ANGLE)));
			y = CENTER_Y + (int)(AXIS_LENGTH * Math.sin(Math.toRadians(lineDrawn * AXIS_ANGLE)));

			// DrawLine from center to (x, y)
			g.drawLine(CENTER_X, CENTER_Y, x, y);

			int DOT_SPACING = AXIS_LENGTH/frequency;
			// Loop to go across the line
			for (int dotNumber = 0; dotNumber < frequency; dotNumber++) {
				// Variables to control center of dot
				int middle_x;
				int middle_y;
				int old_middle_x;
				int old_middle_y;

				// Set value with adjusted radius length
				middle_x = CENTER_X + (int)(DOT_SPACING * dotNumber * Math.cos(Math.toRadians(lineDrawn * AXIS_ANGLE)));
				middle_y = CENTER_Y + (int)(DOT_SPACING * dotNumber * Math.sin(Math.toRadians(lineDrawn * AXIS_ANGLE)));

				// Store values in previous for the first time
				old_middle_x = CENTER_X + (int)(DOT_SPACING * (frequency - dotNumber) * Math.cos(Math.toRadians((lineDrawn - 1) * AXIS_ANGLE)));
				old_middle_y = CENTER_Y + (int)(DOT_SPACING * (frequency - dotNumber) * Math.sin(Math.toRadians((lineDrawn - 1) * AXIS_ANGLE)));
				// Draw the connecting line
				g.drawLine(middle_x, middle_y, old_middle_x, old_middle_y);
			}
		}
	}

	public void drawCircle (int x_center_h, int y_center_k, int radius, Graphics g) {
		g.drawOval(x_center_h - radius, y_center_k - radius, 2 * radius, 2 * radius);
	}

	public void fillCircle (int x_center_h, int y_center_k, int radius, Graphics g) {
		g.fillOval(x_center_h - radius, y_center_k - radius, 2 * radius, 2 * radius);
	}

}

class FractalValues extends JPanel implements ActionListener {

	public static int axisUser = 1;
	public static int frequencyUser = 1;

	private JTextField axisInput;
	private JTextField frequencyInput;
	private JButton submitInput;

	public FractalValues () {
		// Initialize inputFields
		axisInput = new JTextField("Enter the number of axes");
		frequencyInput = new JTextField("Enter the frequency of lines drawn");
		submitInput = new JButton("SUBMIT");
		submitInput.addActionListener(this);

		setLayout(new GridLayout(3, 1));
		add(axisInput);
		add(frequencyInput);
		add(submitInput);
	}

	public void actionPerformed (ActionEvent event) {
		axisUser = Integer.parseInt(axisInput.getText());
		frequencyUser = Integer.parseInt(frequencyInput.getText());
	}

}