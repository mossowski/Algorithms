package controllers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javafx.scene.shape.Line;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import utilities.FileReader;

public class WindowQueryController extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int itsXShift = 0;
	private int itsYShift = 0;

	public WindowQueryController() {
		setPreferredSize(new Dimension(800, 600));
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.black));
		computeMiddleOfScreen(800, 600);
	}

	// --------------------------------------------------------------------------

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		paintLines(g2d, FileReader.itsDataLines);
		paintAxis(g2d);

	}

	// --------------------------------------------------------------------------

	/**
	 * Paints lines
	 * 
	 * @param g2d
	 *            , aLines
	 */
	public void paintLines(Graphics2D g2d, ArrayList<Line> aLines) {
		g2d.setColor(Color.blue);
		for (int i = 0; i < aLines.size(); i++) {
			double x1 = aLines.get(i).getStartX() + itsXShift;
			double y1 = aLines.get(i).getStartY() + itsYShift;
			double x2 = aLines.get(i).getEndX() + itsXShift;
			double y2 = aLines.get(i).getEndY() + itsYShift;
			g2d.setStroke(new BasicStroke(2));
			g2d.draw(new Line2D.Double(x1, y1, x2, y2));
		}
	}

	// --------------------------------------------------------------------------

	/**
	 * Paints x and y axis
	 * 
	 * @param g2d
	 */
	public void paintAxis(Graphics2D g2d) {
		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(1));
		g2d.draw(new Line2D.Double(itsXShift, 999, itsXShift, 0));
		g2d.draw(new Line2D.Double(0, itsYShift, 999, itsYShift));
	}

	// --------------------------------------------------------------------------

	/**
	 * Computes middle of screen to draw 0,0 point
	 * 
	 * @param width
	 *            , height of the screen
	 */
	public void computeMiddleOfScreen(int width, int height) {
		itsXShift = width / 2;
		itsYShift = height / 2;
	}
}