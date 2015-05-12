package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Centrum {

	public static ArrayList<Point> itsCentrumPoints = null;
	public static double itsFurthestPoint = 0;

	public Centrum() {
		itsCentrumPoints = new ArrayList<Point>();
	}

	public ArrayList<Point> makeCentrum(ArrayList<Point> aDataPoints, int aK, int aN) {

		if (aN > aK) {

			Point theFurthestPoint = new Point(0, 0);
			Random rand = new Random();

			// nextInt is normally exclusive of the top value,
			// so add 1 to make it inclusive
			int randomNum = rand.nextInt(aDataPoints.size());

			itsCentrumPoints.add(aDataPoints.get(randomNum));
			aDataPoints.remove(itsCentrumPoints.get(0));

			for (int i = 1; i < aK; i++) {
				theFurthestPoint = checkForFurthest(aDataPoints, itsCentrumPoints);
				aDataPoints.remove(theFurthestPoint);
				itsCentrumPoints.add(theFurthestPoint);
			}
		}

		return itsCentrumPoints;
	}

	// --------------------------------------------------------------------------

	public Point checkForFurthest(ArrayList<Point> aDataPoints, ArrayList<Point> aPointsToCompare) {

		Point theResult = new Point(0, 0);
		double theCurrentDistance = 1000000;
		double theDistanceMax = 0;

		for (int j = 0; j < aDataPoints.size(); j++) {

			for (int i = 0; i < aPointsToCompare.size(); i++) {

				double theDistance = distance(aDataPoints.get(j), aPointsToCompare.get(i));

				if (theCurrentDistance > theDistance)
					theCurrentDistance = theDistance;

			}

			// theCurrentDistance == min

			if (theCurrentDistance > theDistanceMax) {
				theDistanceMax = theCurrentDistance;
				theResult = aDataPoints.get(j);
			}

			itsFurthestPoint = theDistanceMax;
			theCurrentDistance = 1000000;
		}

		return theResult;
	}

	// --------------------------------------------------------------------------

	public double distance(Point aA, Point aB) {

		double distance = Math.sqrt(Math.pow(aA.getX() - aB.getX(), 2) + Math.pow(aA.getY() - aB.getY(), 2));

		return distance;
	}

	// --------------------------------------------------------------------------

	public void printResult(ArrayList<Point> aResult) {

		if (aResult != null) {
			for (int i = 0; i < aResult.size(); i++) {
				System.out.println("------------------------------------------------------------");
				System.out.println("X : " + aResult.get(i).getX() + " Y: " + aResult.get(i).getY());
				System.out.println("------------------------------------------------------------");
			}

			System.out.println("------------------------------------------------------------");
			System.out.println("DISTANCE : " + itsFurthestPoint);
			System.out.println("------------------------------------------------------------");
		}
	}

}
