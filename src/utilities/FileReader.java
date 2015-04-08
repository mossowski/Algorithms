package utilities;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import utilities.MaximaPoint;

import javafx.scene.shape.Line;

public class FileReader {

	private Scanner itsInFileData;
	public static ArrayList<Point> itsDataPoints;
	public static ArrayList<MaximaPoint> itsDataMaximaPoints;
	public static ArrayList<Line> itsDataLines;

	public FileReader(String afileName, int aWhatType) throws FileNotFoundException {
		itsInFileData = new Scanner(new File(afileName));
		itsDataPoints = new ArrayList<Point>();
		itsDataMaximaPoints = new ArrayList<MaximaPoint>();
		itsDataLines = new ArrayList<Line>();

		switch (aWhatType) {

		case 1:
			while (itsInFileData.hasNextLine()) {
				String theLine = itsInFileData.nextLine();
				Scanner s = new Scanner(theLine);
				s.useDelimiter(" ");
				int x = s.nextInt();
				int y = s.nextInt();
				Point p = new Point(x, y);
				itsDataPoints.add(p);
				s.close();
			}
			itsInFileData.close();
			break;

		case 2:
			while (itsInFileData.hasNextLine()) {
				String theLine = itsInFileData.nextLine();
				Scanner s = new Scanner(theLine);
				s.useDelimiter(" ");
				int x = s.nextInt();
				int y = s.nextInt();
				String orientation = "None";
				MaximaPoint p = new MaximaPoint(x, y, orientation);
				itsDataMaximaPoints.add(p);
				s.close();
			}
			itsInFileData.close();
			break;

		case 3:
			while (itsInFileData.hasNextLine()) {
				String theLine = itsInFileData.nextLine();
				Scanner s = new Scanner(theLine);
				s.useDelimiter(" ");
				int startX = s.nextInt();
				int startY = s.nextInt();
				int endX = s.nextInt();
				int endY = s.nextInt();
				Line l = new Line(startX, startY, endX, endY);
				itsDataLines.add(l);
				s.close();
			}
			itsInFileData.close();
			break;
		}
	}

	// --------------------------------------------------------------------------
	/*
	 * public FileReader(String afileName, String aIsMaxima) throws
	 * FileNotFoundException { itsInFileData = new Scanner(new File(afileName));
	 * itsDataMaximaPoints = new ArrayList<MaximaPoint>();
	 * 
	 * while (itsInFileData.hasNextLine()) { String theLine =
	 * itsInFileData.nextLine(); Scanner s = new Scanner(theLine);
	 * s.useDelimiter(" "); int x = s.nextInt(); int y = s.nextInt(); String
	 * orientation = "None"; MaximaPoint p = new MaximaPoint(x, y, orientation);
	 * itsDataMaximaPoints.add(p); s.close(); } itsInFileData.close(); }
	 */
	// --------------------------------------------------------------------------
	/*
	 * public FileReader(String afileName, boolean aIsLines) throws
	 * FileNotFoundException { itsInFileData = new Scanner(new File(afileName));
	 * itsDataLines = new ArrayList<Line>();
	 * 
	 * while (itsInFileData.hasNextLine()) { String theLine =
	 * itsInFileData.nextLine(); Scanner s = new Scanner(theLine);
	 * s.useDelimiter(" "); int startX = s.nextInt(); int startY = s.nextInt();
	 * int endX = s.nextInt(); int endY = s.nextInt(); Line l = new Line(startX,
	 * startY, endX, endY); itsDataLines.add(l); s.close(); }
	 * itsInFileData.close(); }
	 */
	// --------------------------------------------------------------------------

	/**
	 * Prints data points
	 * 
	 */
	public void printDataPoints() {
		System.out.println("----------FILE DATA----------");
		for (int i = 0; i < itsDataPoints.size(); i++) {
			System.out.println("X : " + itsDataPoints.get(i).getX() + " Y : " + itsDataPoints.get(i).getY());
		}
		System.out.println("----------FILE DATA----------\n");
	}

	// --------------------------------------------------------------------------

	/**
	 * Prints data lines
	 * 
	 */
	public void printDataLines() {
		System.out.println("----------FILE DATA----------");
		for (int i = 0; i < itsDataLines.size(); i++) {
			System.out.println("Start : " + itsDataLines.get(i).getStartX() + "," + itsDataLines.get(i).getStartY() + " End : " + itsDataLines.get(i).getEndX() + "," + itsDataLines.get(i).getEndY());
		}
		System.out.println("----------FILE DATA----------\n");
	}
}