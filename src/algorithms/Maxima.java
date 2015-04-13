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
	public ArrayList<ArrayList<MaximaPoint>> detectMaxima(ArrayList<MaximaPoint> aLeftData) {

		ArrayList<ArrayList<MaximaPoint>> theMyList = new ArrayList<ArrayList<MaximaPoint>>();
		ArrayList<MaximaPoint> theRightData = new ArrayList<MaximaPoint>();
		double yMax, yMin;

		copy(theRightData, aLeftData);
		aLeftData = sortByX(aLeftData);
		theRightData = sortByX(theRightData);

		aLeftData.get(0).setOrientation("MIN | MAX");
		itsResultLeft.add(aLeftData.get(0));
		yMin = aLeftData.get(0).getY();
		yMax = yMin;

		for (int i = 1; i < aLeftData.size(); i++) {

			double y = aLeftData.get(i).getY();

			if (y > yMax) {
				aLeftData.get(i).setOrientation("MAX");
				itsResultLeft.add(aLeftData.get(i));
				yMax = y;
			}
			/*if (y < yMin) {
				aLeftData.get(i).setOrientation("MIN");
				itsResultLeft.add(aLeftData.get(i));
				yMin = y;
			}*/
		}

		int last = theRightData.size() - 1;
		theRightData.get(last).setOrientation("MIN | MAX");
		itsResultRight.add(theRightData.get(last));
		yMin = theRightData.get(last).getY();
		yMax = yMin;

		for (int i = theRightData.size() - 1; i > 2; i--) {

			double y = theRightData.get(i).getY();

			if (y > yMax) {
				theRightData.get(i).setOrientation("MAX");
				itsResultRight.add(theRightData.get(i));
				yMax = y;
			}
			/*if (y < yMin) {
				theRightData.get(i).setOrientation("MIN");
				itsResultRight.add(theRightData.get(i));
				yMin = y;
			}*/
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

	// --------------------------------------------------------------------------

	public void copy(ArrayList<MaximaPoint> copy, ArrayList<MaximaPoint> data) {
		for (int i = 0; i < data.size(); i++) {

			MaximaPoint p = new MaximaPoint(data.get(i).getX(), data.get(i).getY(), data.get(i).getOrientation());
			copy.add(p);
		}
	}
}