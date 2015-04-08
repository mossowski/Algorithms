package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Maxima {

	public static ArrayList<Point> itsResultLeft = null;
	public static ArrayList<Point> itsResultRight = null;

	public Maxima() {
		itsResultLeft = new ArrayList<Point>();
		itsResultRight = new ArrayList<Point>();
	}

	// --------------------------------------------------------------------------

	/**
	 * Detects maxima
	 * 
	 * @param aData
	 * @return
	 */
	public ArrayList<ArrayList<Point>> detectMaxima(ArrayList<Point> aData) {

		ArrayList<ArrayList<Point>> theMyList = new ArrayList<ArrayList<Point>>();
		double yMax, yMin;
		aData = sortByX(aData);
		itsResultLeft.add(aData.get(0));
		yMin = aData.get(0).getY();
		yMax = yMin;

		for (int i = 1; i < aData.size(); i++) {

			double y = aData.get(i).getY();

			if (y > yMax) {
				itsResultLeft.add(aData.get(i));
				yMax = y;
			}
			if (y < yMin) {
				itsResultLeft.add(aData.get(i));
				yMin = y;
			}
		}

		int last = aData.size() - 1;
		itsResultRight.add(aData.get(last));
		yMax = yMin;
		yMin = aData.get(last).getY();

		for (int i = aData.size() - 1; i > 2; i--) {

			double y = aData.get(i).getY();

			if (y > yMax) {
				itsResultRight.add(aData.get(i));
				yMax = y;
			}
			if (y < yMin) {
				itsResultRight.add(aData.get(i));
				yMin = y;
			}
		}

		theMyList.add(itsResultLeft);
		theMyList.add(itsResultRight);

		return theMyList;
	}

	// --------------------------------------------------------------------------

	/**
	 * Sorts data by x
	 * 
	 * @param aData
	 * @return
	 */
	public ArrayList<Point> sortByX(ArrayList<Point> aData) {
		Comparator<Point> theComp = new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				return new Integer(o1.x).compareTo(o2.x);
			}
		};
		Collections.sort(aData, theComp);
		return aData;
	}

	// --------------------------------------------------------------------------

	public void printResult(ArrayList<ArrayList<Point>> aResult) {

		ArrayList<Point> theLeft = aResult.get(0);
		ArrayList<Point> theRight = aResult.get(1);

		if (theLeft != null) {
			System.out.println("-----------------------------------LEFT TO RIGHT-------------------------");
			for (int i = 0; i < theLeft.size(); i++) {
				System.out.println("X : " + theLeft.get(i).getX() + " Y: " + theLeft.get(i).getY());
			}
			System.out.println("------------------------------------------------------------");
		}

		if (theRight != null) {
			System.out.println("-----------------------------------RIGHT TO LEFT-------------------------");
			for (int i = 0; i < theRight.size(); i++) {
				System.out.println("X : " + theRight.get(i).getX() + " Y: " + theRight.get(i).getY());
			}
			System.out.println("------------------------------------------------------------");
		}
	}
}