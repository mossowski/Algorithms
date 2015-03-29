package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import utilities.Node;

import javafx.scene.shape.Line;

public class WindowQuery {

	private ArrayList<Line> itsSMid = null;
	private ArrayList<Line> itsSLeft = null;
	private ArrayList<Line> itsSRight = null;
	private ArrayList<Line> itsLLeft = null;
	private ArrayList<Line> itsLRight = null;
	public ArrayList<Line> itsResult = null;
	public ArrayList<Node> itsTree = null;
	private double itsMedianaX;

	public WindowQuery() {
		itsMedianaX = 0;
		itsSMid = new ArrayList<Line>();
		itsSLeft = new ArrayList<Line>();
		itsSRight = new ArrayList<Line>();
		itsLLeft = new ArrayList<Line>();
		itsLRight = new ArrayList<Line>();
		itsResult = new ArrayList<Line>();
		itsTree = new ArrayList<Node>();
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

		itsSLeft.clear();
		itsSMid.clear();
		itsSRight.clear();
		
		
		for (int i = 0; i < S.size(); i++) {

			theXStart = S.get(i).getStartX();
			theXEnd = S.get(i).getEndX();
            if (theXEnd < itsMedianaX)
				itsSLeft.add(S.get(i));
			else if (theXEnd >= itsMedianaX && theXStart <= itsMedianaX)
				itsSMid.add(S.get(i));
			else if (theXStart > itsMedianaX)
				itsSRight.add(S.get(i));
		}

	}

	// --------------------------------------------------------------------------

	/**
	 * Makes L parts
	 * 
	 */
	public void makeLParts() {

		Comparator<Line> theCompLeft = new Comparator<Line>() {
			public int compare(Line o1, Line o2) {
				return new Double(o1.getStartX()).compareTo(o2.getStartX());
			}
		};

		Comparator<Line> theCompRight = new Comparator<Line>() {
			public int compare(Line o1, Line o2) {
				return new Double(o1.getEndX()).compareTo(o2.getEndX());
			}
		};

		itsLLeft.clear();
		itsLRight.clear();
		copy(itsLLeft, itsSMid);
		copy(itsLRight, itsSMid);
		Collections.sort(itsLLeft, theCompLeft);
		Collections.sort(itsLRight, theCompRight);

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

	// --------------------------------------------------------------------------

	/**
	 * Makes copy of data
	 * 
	 */
	public void copy(ArrayList<Line> copy, ArrayList<Line> data) {
		for (int i = 0; i < data.size(); i++) {
			copy.add(data.get(i));
		}
	}

	// --------------------------------------------------------------------------

	/**
	 * Constructs interval tree
	 * 
	 * @param S
	 */
	public Node constructIntervalTree(ArrayList<Line> S) {
		if (S.size() < 1) {
			return null;
		}
		else {
			computeMedianaX(S);
			makeSParts(S);
			makeLParts();
			
			ArrayList<Line> theSMid = new ArrayList<Line>();
			ArrayList<Line> theSLeft = new ArrayList<Line>();
			ArrayList<Line> theSRight = new ArrayList<Line>();
			ArrayList<Line> theLLeft = new ArrayList<Line>();
			ArrayList<Line> theLRight = new ArrayList<Line>();
			
			copy(theSMid, itsSMid);
			copy(theSLeft, itsSLeft);
			copy(theSRight, itsSRight);
			copy(theLLeft, itsLLeft);
			copy(theLRight, itsLRight);
			
			
			Node n = new Node(itsMedianaX, theSMid, theSLeft, theSRight, theLLeft, theLRight);
			itsTree.add(n);
			n.leftChildren = constructIntervalTree(n.getItsSLeft());
			n.rightChildren = constructIntervalTree(n.getItsSRight());
			
			return n;
		}
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
			
			if(y1 > y2)
			{
				double tmp = y2;
				y2 = y1;
				y1 = tmp;
				
			}
		    
			if (a.getStartX() <= v.getItsMedianaX()) {
				for (int i=0; i < v.getItsLLeft().size(); i++) {
					double xLeft = v.getItsLLeft().get(i).getStartX();
					double xRight = v.getItsLLeft().get(i).getEndX();
					double y = v.getItsLLeft().get(i).getStartY();
					if ((xLeft <= x && xRight >= x) && (y1 <= y && y2 >= y)) {
						itsResult.add(v.getItsLLeft().get(i));
					}
				}
				queryIntervalTree(v.leftChildren, a);
			} else {
				for (int i=0; i < v.getItsLRight().size(); i++) {
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