package controllers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import algorithms.Centrum;
import utilities.FileReader;

public class CentrumController extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int itsXShift = 0;
	private int itsYShift = 0;

	public CentrumController() {
		setPreferredSize(new Dimension(800, 500));
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.black));
		computeMiddleOfScreen(800, 500);
	}

	// --------------------------------------------------------------------------

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		paintPoints(g2d, FileReader.itsDataPoints, Color.black);
		paintPoints(g2d, Centrum.itsCentrumPoints, Color.blue);
		paintAxis(g2d);
	}

	// --------------------------------------------------------------------------

	/**
	 * Paints lines
	 * 
	 * @param g2d, aPoints
	 *
	 */
	public void paintPoints(Graphics2D g2d, ArrayList<Point> aPoints, Color aColor) {
		g2d.setColor(aColor);
		for (int i = 0; i < aPoints.size(); i++) {
			double x = aPoints.get(i).getX() * 10 + itsXShift;
			double y = -aPoints.get(i).getY() * 10 + itsYShift;
			g2d.setStroke(new BasicStroke(8));
			g2d.draw(new Line2D.Double(x, y, x, y));
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
