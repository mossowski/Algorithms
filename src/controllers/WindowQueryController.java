package controllers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javafx.scene.shape.Line;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import managers.ConsoleManager;
import utilities.FileReader;

public class WindowQueryController extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int itsXShift = 0;
	private int itsYShift = 0;

	public WindowQueryController() {
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
		paintQueryBox(g2d, ConsoleManager.theLeft, ConsoleManager.theRight);
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
			double x1 = aLines.get(i).getStartX() * 10 + itsXShift;
			double y1 = -aLines.get(i).getStartY() * 10 + itsYShift;
			double x2 = aLines.get(i).getEndX() * 10 + itsXShift;
			double y2 = -aLines.get(i).getEndY() * 10  + itsYShift;
			g2d.setStroke(new BasicStroke(2));
			g2d.draw(new Line2D.Double(x1, y1, x2, y2));
		}
	}
	
	// --------------------------------------------------------------------------
	
	/**
	 * Paints query box
	 * 
	 * @param g2d
	 *            , aLines
	 */
	public void paintQueryBox(Graphics2D g2d, Line aLeft, Line aRight) {
		
		double x1 = aLeft.getStartX() * 10 + itsXShift;
		double y1 = aLeft.getStartY() * -10 + itsYShift;
		
		System.out.println(aLeft.getEndX() + "  " + aLeft.getEndY() + "\n");
		double width = Math.abs(aLeft.getStartX()) + Math.abs(aRight.getStartX()) * 10;
		double height = Math.abs(aLeft.getStartY()) * 10 + Math.abs(aLeft.getEndY()) * 10;
		
		g2d.setColor(Color.red);
		g2d.setStroke(new BasicStroke(2));
		g2d.draw(new Rectangle2D.Double(x1, y1, width, height));
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