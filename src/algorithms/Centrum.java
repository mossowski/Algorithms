package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Centrum {

	public static ArrayList<Point> itsCentrumPoints = null;

	public Centrum() {
		itsCentrumPoints = new ArrayList<Point>();
	}

	public ArrayList<Point> makeCentrum(ArrayList<Point> aDataPoints, int aK, int aN) {

		if (aN > aK) {

			Point theFurthestPoint = new Point(0, 0);
			Random rand = new Random();

			// nextInt is normally exclusive of the top value,
			// so add 1 to make it inclusive
			int randomNum = rand.nextInt((aDataPoints.size() - 0) + 1) + 0;

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
		double theCurrentDistance = 0;
		double theDistanceMax = 0;

		for (int j = 0; j < aDataPoints.size(); j++) {

			for (int i = 0; i < aPointsToCompare.size(); i++) {
				theCurrentDistance = theCurrentDistance + distance(aDataPoints.get(j), aPointsToCompare.get(i));

				if (theCurrentDistance > theDistanceMax) {
					theDistanceMax = theCurrentDistance;
					theResult = aDataPoints.get(j);
				}
			}

			theCurrentDistance = 0;
		}

		return theResult;
	}

	// --------------------------------------------------------------------------

	public double distance(Point aA, Point aB) {

		double distance = Math.pow(aA.getX() - aB.getX(), 2) + Math.pow(aA.getY() - aB.getY(), 2);

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
		}
	}

}
