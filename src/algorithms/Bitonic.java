package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.scene.shape.Line;
import utilities.MaximaPoint;

public class Bitonic {

	public static ArrayList<Line> itsBitonicLines = null;

	public Bitonic() {
		itsBitonicLines = new ArrayList<Line>();
	}

	/**
	 * Makes Bitonic Route
	 * 
	 * @param itsData
	 * 
	 */

	public double makeBitonicRoute(ArrayList<MaximaPoint> aData) {

		ArrayList<MaximaPoint> itsBitonicPoints = sortByX(aData);

		double[] theB = new double[itsBitonicPoints.size()];
		Point theFirst = new Point(0, 0);
		Point theLast = new Point(0, 0);

		for(int i = 0; i < theB.length; i++)
			theB[i] = 0;
		
		theB[1] = distance(itsBitonicPoints.get(0), itsBitonicPoints.get(1));

		itsBitonicLines.add(new Line(itsBitonicPoints.get(0).getX(), itsBitonicPoints.get(0).getY(), itsBitonicPoints.get(1).getX(), itsBitonicPoints.get(1).getY()));

		
		for (int j = 2; j < itsBitonicPoints.size(); j++) {

			double theMin = 100000;
			double theSuma = 0;

			for (int i = j - 2; i >= 0; i--) {
				double d = theB[i + 1] + distance(itsBitonicPoints.get(i), itsBitonicPoints.get(j)) + theSuma;

				if (d < theMin) {
					theMin = d;
					theFirst.setLocation(itsBitonicPoints.get(i).getX(), itsBitonicPoints.get(i).getY());
					theLast.setLocation(itsBitonicPoints.get(j).getX(), itsBitonicPoints.get(j).getY());
				}

				theSuma = theSuma + distance(itsBitonicPoints.get(i), itsBitonicPoints.get(i + 1));
				
			}

			theB[j] = theMin;
			itsBitonicLines.add(new Line(theFirst.getX(), theFirst.getY(), theLast.getX(), theLast.getY()));
		}
		
		int theLastIndex = itsBitonicPoints.size() - 1;
		
		itsBitonicLines.add(new Line(itsBitonicPoints.get(theLastIndex).getX(), itsBitonicPoints.get(theLastIndex).getY(), 
							itsBitonicPoints.get(theLastIndex-1).getX(), itsBitonicPoints.get(theLastIndex-1).getY()));

		for (int k = 0; k < itsBitonicLines.size() ; k++) {
			
			System.out.println("X : " + itsBitonicLines.get(k).getStartX() + " Y: " + itsBitonicLines.get(k).getEndX());
		}
		
		for (int k = 0; k < theB.length ; k++) {
			
			System.out.println("B : " + theB[k]);
		}

		return theB[theB.length-1] + distance(itsBitonicPoints.get(theLastIndex),itsBitonicPoints.get(theLastIndex-1));
	}

	public double distance(MaximaPoint aA, MaximaPoint aB) {

		double distance = Math.pow(aA.getX() - aB.getX(), 2) + Math.pow(aA.getY() - aB.getY(), 2);

		return distance;
	}

	// --------------------------------------------------------------------------

	public ArrayList<MaximaPoint> sortByX(ArrayList<MaximaPoint> aData) {
		Comparator<MaximaPoint> theComp = new Comparator<MaximaPoint>() {
			@Override
			public int compare(MaximaPoint o1, MaximaPoint o2) {
				return new Integer(o1.getX()).compareTo(o2.getX());
			}
		};
		Collections.sort(aData, theComp);
		return aData;
	}

	// --------------------------------------------------------------------------

	public void printResult(ArrayList<MaximaPoint> aResult) {

		if (aResult != null) {
			for (int i = 0; i < aResult.size(); i++) {
				System.out.println("------------------------------------------------------------");
				System.out.println("X : " + aResult.get(i).getX() + " Y: " + aResult.get(i).getY());
				System.out.println("Let : " + aResult.get(i).getOrientation());
				System.out.println("------------------------------------------------------------");
			}
		}
	}
	
	public void printResultLength(double aResult) {

		System.out.println("------------------------ROUTE LENGTH------------------------------------");
		System.out.println(aResult);
		System.out.println("------------------------------------------------------------");
	}

}
