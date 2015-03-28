import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.scene.shape.Line;

public class WindowQuery {

	private ArrayList<Line> itsSMid = null;
	private ArrayList<Line> itsSLeft = null;
	private ArrayList<Line> itsSRight = null;
	private double itsMedianaX;

	WindowQuery() {
		itsMedianaX = 0;
		itsSMid = new ArrayList<Line>();
		itsSLeft = new ArrayList<Line>();
		itsSRight = new ArrayList<Line>();
	}

	// --------------------------------------------------------------------------

	/**
	 * Makes S parts
	 * 
	 * @param S
	 */
	public void makeSParts(ArrayList<Line> S) {

		double theXStart = 0;
		double theXEnd = 0;

		for (int i = 0; i < S.size(); i++) {

			theXStart = S.get(i).getStartX();
			theXEnd = S.get(i).getEndX();

			if (theXEnd < itsMedianaX)
				itsSLeft.add(S.get(i));
			else if (theXEnd >= itsMedianaX && theXStart <= itsMedianaX)
				itsSMid.add(S.get(i));
			else if (theXEnd < itsMedianaX)
				itsSRight.add(S.get(i));
		}

	}

	// --------------------------------------------------------------------------

	/**
	 * Compute medianaX
	 * 
	 * @param aDataLines
	 */
	public void computeMedianaX(ArrayList<Line> aDataLines) {
		ArrayList<Double> theData = new ArrayList<Double>();
		for (int i = 0; i < aDataLines.size(); i++) {
			theData.add(aDataLines.get(i).getStartX());
			theData.add(aDataLines.get(i).getEndX());
		}
		theData = sortByX(theData);
		if (theData.size() % 2 == 1)
			itsMedianaX = theData.get(theData.size() / 2);
		else
			itsMedianaX = (theData.get(theData.size() / 2) + theData.get(theData.size() / 2 - 1)) / 2;
	}

	// --------------------------------------------------------------------------

	/**
	 * Sorts data
	 * 
	 * @param aData
	 */
	public ArrayList<Double> sortByX(ArrayList<Double> aData) {
		Comparator<Double> theComp = new Comparator<Double>() {
			public int compare(Double o1, Double o2) {
				return new Double(o1).compareTo(o2);
			}
		};
		Collections.sort(aData, theComp);
		return aData;
	}

	// --------------------------------------------------------------------------

	/**
	 * Prints results
	 * 
	 */
	public void printResults() {
		System.out.println("\nMediana : " + itsMedianaX);
	}
}
