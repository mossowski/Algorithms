package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Maxima {

	public static ArrayList<Point> itsResult = null;

	public Maxima() {
		itsResult = new ArrayList<Point>();
	}

	// --------------------------------------------------------------------------

	/**
	 * Detects maxima
	 * 
	 * @param aData
	 * @return
	 */
	public ArrayList<Point> detectMaxima(ArrayList<Point> aData) {

		double yMax, yMin;
		aData = sortByX(aData);
		itsResult.add(aData.get(0));
		yMin = aData.get(0).getY();
		yMax = yMin;

		for (int i = 1; i < aData.size(); i++) {

			double y = aData.get(i).getY();

			if (y > yMax) {
				itsResult.add(aData.get(i));
				yMax = y;
			}
			if (y < yMin) {
				itsResult.add(aData.get(i));
				yMin = y;
			}
		}

		int last = aData.size() - 1;
		itsResult.add(aData.get(last));
		yMax = yMin;
		yMin = aData.get(last).getY();

		for (int i = aData.size() - 1; i > 2; i--) {

			double y = aData.get(i).getY();

			if (y > yMax) {
				itsResult.add(aData.get(i));
				yMax = y;
			}
			if (y < yMin) {
				itsResult.add(aData.get(i));
				yMin = y;
			}
		}
		return itsResult;
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

	public void printResult(ArrayList<Point> aResult) {

		if (aResult != null) {
			System.out.println("------------------------------------------------------------");
			for (int i = 0; i < aResult.size(); i++) {
				System.out.println("X : " + aResult.get(i).getX() + " Y: " + aResult.get(i).getY());
			}
			System.out.println("------------------------------------------------------------");
		}
	}
}