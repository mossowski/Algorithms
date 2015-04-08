package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import utilities.MaximaPoint;

public class Maxima {

	public static ArrayList<MaximaPoint> itsResultLeft = null;
	public static ArrayList<MaximaPoint> itsResultRight = null;

	public Maxima() {
		itsResultLeft = new ArrayList<MaximaPoint>();
		itsResultRight = new ArrayList<MaximaPoint>();
	}

	// --------------------------------------------------------------------------

	/**
	 * Detects maxima
	 * 
	 * @param aData
	 * @return
	 */
	public ArrayList<ArrayList<MaximaPoint>> detectMaxima(ArrayList<MaximaPoint> aData) {

		ArrayList<ArrayList<MaximaPoint>> theMyList = new ArrayList<ArrayList<MaximaPoint>>();
		double yMax, yMin;
		aData = sortByX(aData);
		aData.get(0).setOrientation("MIN | MAX");
		itsResultLeft.add(aData.get(0));
		yMin = aData.get(0).getY();
		yMax = yMin;

		for (int i = 1; i < aData.size(); i++) {

			double y = aData.get(i).getY();

			if (y > yMax) {
				aData.get(i).setOrientation("MAX");
				itsResultLeft.add(aData.get(i));
				yMax = y;
			}
			if (y < yMin) {
				aData.get(i).setOrientation("MIN");
				itsResultLeft.add(aData.get(i));
				yMin = y;
			}
		}

		int last = aData.size() - 1;
		aData.get(last).setOrientation("MIN | MAX");
		itsResultRight.add(aData.get(last));
		yMin = aData.get(last).getY();
		yMax = yMin;

		for (int i = aData.size() - 1; i > 2; i--) {

			double y = aData.get(i).getY();

			if (y > yMax) {
				aData.get(i).setOrientation("MAX");
				itsResultRight.add(aData.get(i));
				yMax = y;
			}
			if (y < yMin) {
				aData.get(i).setOrientation("MIN");
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
	public ArrayList<MaximaPoint> sortByX(ArrayList<MaximaPoint> aData) {
		Comparator<MaximaPoint> theComp = new Comparator<MaximaPoint>() {
			public int compare(MaximaPoint o1, MaximaPoint o2) {
				return new Integer(o1.getX()).compareTo(o2.getX());
			}
		};
		Collections.sort(aData, theComp);
		return aData;
	}

	// --------------------------------------------------------------------------

	public void printResult(ArrayList<ArrayList<MaximaPoint>> aResult) {

		ArrayList<MaximaPoint> theLeft = aResult.get(0);
		ArrayList<MaximaPoint> theRight = aResult.get(1);

		if (theLeft != null) {
			System.out.println("-----------------------------------LEFT TO RIGHT-------------------------");
			for (int i = 0; i < theLeft.size(); i++) {
				System.out.println("X : " + theLeft.get(i).getX() + " Y: " + theLeft.get(i).getY() + " ORIENT: " + theLeft.get(i).getOrientation());
			}
			System.out.println("------------------------------------------------------------");
		}

		if (theRight != null) {
			System.out.println("-----------------------------------RIGHT TO LEFT-------------------------");
			for (int i = 0; i < theRight.size(); i++) {
				System.out.println("X : " + theRight.get(i).getX() + " Y: " + theRight.get(i).getY() + " ORIENT: " + theRight.get(i).getOrientation());
			}
			System.out.println("------------------------------------------------------------");
		}
	}
}