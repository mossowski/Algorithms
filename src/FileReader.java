import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.shape.Line;

public class FileReader {

	private Scanner itsInFileData;
	public ArrayList<Point> itsDataPoints;
	public ArrayList<Line> itsDataLines;

	FileReader(String afileName) throws FileNotFoundException {
		itsInFileData = new Scanner(new File(afileName));
		itsDataPoints = new ArrayList<Point>();

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
	}

	// --------------------------------------------------------------------------

	FileReader(String afileName, boolean aIsLines) throws FileNotFoundException {
		itsInFileData = new Scanner(new File(afileName));
		itsDataLines = new ArrayList<Line>();

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
	}

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