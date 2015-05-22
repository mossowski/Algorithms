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
	public static ArrayList<Integer> itsDataNumbers;
	public static ArrayList<MaximaPoint> itsDataMaximaPoints;
	public static ArrayList<Line> itsDataLines;
	public static int itsCentrumK;
	public static int itsCentrumN;
	public static double itsSumT;
	public static double itsSumEpsilon;

	// --------------------------------------------------------------------------

	public FileReader(String aFileName) throws FileNotFoundException {
		itsInFileData = new Scanner(new File(aFileName));
		itsDataPoints = new ArrayList<Point>();
		itsDataNumbers = new ArrayList<Integer>();
		itsDataMaximaPoints = new ArrayList<MaximaPoint>();
		itsDataLines = new ArrayList<Line>();
		itsCentrumK = 0;
		itsCentrumN = 0;
		itsSumT = 0;
		itsSumEpsilon = 0;
	}

	// --------------------------------------------------------------------------

	/**
	 * Loads data
	 * 
	 */
	public void loadData(int aWhatType) {

		boolean isFirstLine = true;

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

		case 4:
			int letter = 65;
			while (itsInFileData.hasNextLine()) {
				String theLine = itsInFileData.nextLine();
				Scanner s = new Scanner(theLine);
				s.useDelimiter(" ");
				int x = s.nextInt();
				int y = s.nextInt();
				String orientation = "" + (char) letter;
				letter++;
				MaximaPoint p = new MaximaPoint(x, y, orientation);
				itsDataMaximaPoints.add(p);
				s.close();
			}
			itsInFileData.close();
			break;

		case 5:
			while (itsInFileData.hasNextLine()) {
				String theLine = itsInFileData.nextLine();
				Scanner s = new Scanner(theLine);

				s.useDelimiter(" ");

				if (!isFirstLine) {
					int x = s.nextInt();
					int y = s.nextInt();
					Point p = new Point(x, y);
					itsDataPoints.add(p);
				} else {
					itsCentrumK = s.nextInt();
					itsCentrumN = s.nextInt();
					isFirstLine = false;
				}
				s.close();
			}
			itsInFileData.close();
			break;

		case 6:
			while (itsInFileData.hasNextLine()) {
				String theLine = itsInFileData.nextLine();
				Scanner s = new Scanner(theLine);
				s.useDelimiter(" ");
				if (isFirstLine) {
					while (s.hasNextInt()) {
						itsDataNumbers.add(s.nextInt());
					}
					isFirstLine = false;
				} else {
					if (itsSumT == 0){
						itsSumT = s.nextDouble();
					}
					else{
						itsSumEpsilon = s.nextDouble();
					}
				}
				s.close();
			}
			itsInFileData.close();
			break;

		default:
			break;
		}
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