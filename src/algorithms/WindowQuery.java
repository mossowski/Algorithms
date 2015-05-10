package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import utilities.Node;
import javafx.scene.shape.Line;

public class WindowQuery {

	public static ArrayList<Line> itsResult = null;
	public ArrayList<Node> itsTree = null;
	private double itsMedianaX;

	public WindowQuery() {
		itsMedianaX = 0;
		itsResult = new ArrayList<Line>();
		itsTree = new ArrayList<Node>();
	}

	// --------------------------------------------------------------------------

	/**
	 * Makes S parts
	 * 
	 * @param S
	 * @return
	 */
	public ArrayList<ArrayList<Line>> makeSParts(ArrayList<Line> aS) {

		double theXStart = 0;
		double theXEnd = 0;
		ArrayList<Line> theSMid = new ArrayList<Line>();
		ArrayList<Line> theSLeft = new ArrayList<Line>();
		ArrayList<Line> theSRight = new ArrayList<Line>();

		for (int i = 0; i < aS.size(); i++) {

			theXStart = aS.get(i).getStartX();
			theXEnd = aS.get(i).getEndX();
			if (theXEnd < itsMedianaX)
				theSLeft.add(aS.get(i));
			else if (theXEnd >= itsMedianaX && theXStart <= itsMedianaX)
				theSMid.add(aS.get(i));
			else if (theXStart > itsMedianaX)
				theSRight.add(aS.get(i));
		}

		ArrayList<ArrayList<Line>> theMyList = new ArrayList<ArrayList<Line>>();
		theMyList.add(theSMid);
		theMyList.add(theSLeft);
		theMyList.add(theSRight);

		return theMyList;

	}

	// --------------------------------------------------------------------------

	/**
	 * Makes L parts
	 * 
	 */
	public ArrayList<ArrayList<Line>> makeLParts(ArrayList<Line> aSMid) {

		Comparator<Line> theCompLeft = new Comparator<Line>() {
			@Override
			public int compare(Line o1, Line o2) {
				return new Double(o1.getStartX()).compareTo(o2.getStartX());
			}
		};

		Comparator<Line> theCompRight = new Comparator<Line>() {
			@Override
			public int compare(Line o1, Line o2) {
				return new Double(o1.getEndX()).compareTo(o2.getEndX());
			}
		};

		ArrayList<Line> theLLeft = aSMid;
		ArrayList<Line> theLRight = aSMid;
		Collections.sort(theLLeft, theCompLeft);
		Collections.sort(theLRight, theCompRight);

		ArrayList<ArrayList<Line>> theMyList = new ArrayList<ArrayList<Line>>();
		theMyList.add(theLLeft);
		theMyList.add(theLRight);

		return theMyList;

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
			@Override
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

	// --------------------------------------------------------------------------

	/**
	 * Constructs interval tree
	 * 
	 * @param S
	 */
	public Node constructIntervalTree(ArrayList<Line> aS) {
		if (aS.size() < 1) {
			return null;
		}
		computeMedianaX(aS);
		ArrayList<ArrayList<Line>> theSParts = makeSParts(aS);

		ArrayList<Line> theSMid = theSParts.get(0);
		ArrayList<Line> theSLeft = theSParts.get(1);
		ArrayList<Line> theSRight = theSParts.get(2);

		ArrayList<ArrayList<Line>> theLParts = makeLParts(theSMid);

		ArrayList<Line> theLLeft = theLParts.get(0);
		ArrayList<Line> theLRight = theLParts.get(1);

		Node n = new Node(itsMedianaX, theSMid, theSLeft, theSRight, theLLeft, theLRight);
		itsTree.add(n);
		n.leftChildren = constructIntervalTree(n.getItsSLeft());
		n.rightChildren = constructIntervalTree(n.getItsSRight());

		return n;
	}

	// --------------------------------------------------------------------------

	/**
	 * Query interval tree
	 * 
	 * @param root
	 */
	public void queryIntervalTree(Node v, Line a) {
		if (v != null) {
			double x = a.getStartX();
			double y1 = a.getStartY();
			double y2 = a.getEndY();

			if (y1 > y2) {
				double tmp = y2;
				y2 = y1;
				y1 = tmp;

			}

			if (a.getStartX() <= v.getItsMedianaX()) {
				for (int i = 0; i < v.getItsLLeft().size(); i++) {
					double xLeft = v.getItsLLeft().get(i).getStartX();
					double xRight = v.getItsLLeft().get(i).getEndX();
					double y = v.getItsLLeft().get(i).getStartY();
					if ((xLeft <= x && xRight >= x) && (y1 <= y && y2 >= y)) {
						itsResult.add(v.getItsLLeft().get(i));
					}
				}
				queryIntervalTree(v.leftChildren, a);
			} else {
				for (int i = 0; i < v.getItsLRight().size(); i++) {
					double xLeft = v.getItsLRight().get(i).getStartX();
					double xRight = v.getItsLRight().get(i).getEndX();
					double y = v.getItsLRight().get(i).getStartY();
					if ((xLeft <= x && xRight >= x) && (y1 <= y && y2 >= y)) {
						itsResult.add(v.getItsLRight().get(i));
					}
				}
				queryIntervalTree(v.rightChildren, a);
			}
		}
	}

	// --------------------------------------------------------------------------

	public void printTree(Node Data1) {

		if (Data1 != null) {
			System.out.println("\nMediana : " + Data1.getItsMedianaX());

			for (int i = 0; i < Data1.getItsLRight().size(); i++) {
				System.out.println("\nLRight : " + Data1.getItsLRight().get(i).getEndX());
			}
			for (int i = 0; i < Data1.getItsLLeft().size(); i++) {
				System.out.println("\nLLeft : " + Data1.getItsLLeft().get(i).getEndX());
			}

			printTree(Data1.leftChildren);
			printTree(Data1.rightChildren);
		}
	}

	// --------------------------------------------------------------------------

	public void printResult(ArrayList<Line> aResult) {

		if (aResult != null) {
			for (int i = 0; i < aResult.size(); i++) {
				System.out.println("------------------------------------------------------------");
				System.out.println("X : " + aResult.get(i).getStartX() + " Y: " + aResult.get(i).getStartY());
				System.out.println("X : " + aResult.get(i).getEndX() + " Y: " + aResult.get(i).getEndY());
				System.out.println("------------------------------------------------------------");
			}
		}
	}
}