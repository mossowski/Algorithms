package algorithms;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ClosestPoints {

	private ArrayList<Point> itsByX, itsByY = null;
	private ArrayList<Point> itsByX1, itsByX2 = null;
	private ArrayList<Point> itsByY1, itsByY2 = null;
	private ArrayList<Point> itsSd1, itsSd2 = null;

	private double itsDelta;
	private int itsMedianaX;

	private Point itsFirstPoint = new Point();
	private Point itsSecondPoint = new Point();

	public ClosestPoints() {
		itsByX = new ArrayList<Point>();
		itsByY = new ArrayList<Point>();
		itsByX1 = new ArrayList<Point>();
		itsByX2 = new ArrayList<Point>();
		itsByY1 = new ArrayList<Point>();
		itsByY2 = new ArrayList<Point>();
		itsSd1 = new ArrayList<Point>();
		itsSd2 = new ArrayList<Point>();

		itsDelta = 0;
		itsMedianaX = 0;

		itsFirstPoint.setLocation(0, 0);
		itsSecondPoint.setLocation(0, 0);
	}

	// --------------------------------------------------------------------------

	/**
	 * Sorts data by x
	 * 
	 * @param data
	 */
	public void sortByX(ArrayList<Point> aData) {
		Comparator<Point> theComp = new Comparator<Point>() {
			@Override
            public int compare(Point o1, Point o2) {
				return new Integer(o1.x).compareTo(o2.x);
			}
		};
		copy(itsByX, aData);
		Collections.sort(itsByX, theComp);
	}

	// --------------------------------------------------------------------------

	/**
	 * Sorts data by y
	 * 
	 * @param data
	 */
	public void sortByY(ArrayList<Point> aData) {
		Comparator<Point> theComp = new Comparator<Point>() {
			@Override
            public int compare(Point o1, Point o2) {
				return new Integer(o1.y).compareTo(o2.y);
			}
		};
		copy(itsByY, aData);
		Collections.sort(itsByY, theComp);
	}

	// --------------------------------------------------------------------------

	/**
	 * Divide sorted arrays into two parts (x1,x2/y1,y2)
	 * 
	 */
	public void partXY() {
		double x = 0;
		itsMedianaX = (int) itsByX.get((itsByX.size() / 2) - 1).getX();

		for (int i = 0; i < itsByX.size(); i++) {
			x = itsByX.get(i).getX();

			if (x <= itsMedianaX)
				itsByX1.add(itsByX.get(i));
			else
				itsByX2.add(itsByX.get(i));
		}

		for (int i = 0; i < itsByY.size(); i++) {
			x = itsByY.get(i).getX();

			if (x <= itsMedianaX)
				itsByY1.add(itsByY.get(i));
			else
				itsByY2.add(itsByY.get(i));
		}
	}

	// --------------------------------------------------------------------------

	/**
	 * Counts delta from two parts
	 * 
	 */
	public void deltaHalf() {
		double theDistance = 0;

		for (int i = 0; i < itsByX1.size(); i++) {
			for (int j = i; j < itsByX1.size() - 1; j++) {
				theDistance = Math.pow(itsByX1.get(j + 1).getX() - itsByX1.get(i).getX(), 2) + Math.pow(itsByX1.get(j + 1).getY() - itsByX1.get(i).getY(), 2);

				if (itsDelta == 0 || itsDelta > theDistance) {
					itsDelta = theDistance;
					itsFirstPoint.setLocation(itsByX1.get(i).getX(), itsByX1.get(i).getY());
					itsSecondPoint.setLocation(itsByX1.get(j + 1).getX(), itsByX1.get(j + 1).getY());
				}
			}
		}

		for (int i = 0; i < itsByX2.size(); i++) {
			for (int j = i; j < itsByX2.size() - 1; j++) {
				theDistance = Math.pow(itsByX2.get(j + 1).getX() - itsByX2.get(i).getX(), 2) + Math.pow(itsByX2.get(j + 1).getY() - itsByX2.get(i).getY(), 2);

				if (itsDelta == 0 || itsDelta > theDistance) {
					itsDelta = theDistance;
					itsFirstPoint.setLocation(itsByX2.get(i).getX(), itsByX2.get(i).getY());
					itsSecondPoint.setLocation(itsByX2.get(j + 1).getX(), itsByX2.get(j + 1).getY());
				}
			}
		}
	}

	// --------------------------------------------------------------------------

	/**
	 * Makes delta space
	 * 
	 */
	public void makeDeltaPart() {
		double x = 0;

		itsDelta = Math.sqrt(itsDelta);

		for (int i = 0; i < itsByY1.size(); i++) {
			x = itsByY1.get(i).getX();

			if (x > itsMedianaX - itsDelta)
				itsSd1.add(itsByY1.get(i));
		}

		for (int i = 0; i < itsByY2.size(); i++) {
			x = itsByY2.get(i).getX();

			if (x <= itsMedianaX + itsDelta)
				itsSd2.add(itsByY2.get(i));
		}

	}

	// --------------------------------------------------------------------------

	/**
	 * Searches for closest points in delta space
	 * 
	 */
	public void deltaHalfLast() {
		int theBlueIndex = 0;
		double theDistance = 0;
		double y1, y2 = 0;
		double x1, x2 = 0;

		for (int i = 0; i < itsSd1.size(); i++) {
			for (int j = theBlueIndex; j < itsSd2.size(); j++) {
				y1 = itsSd1.get(i).getY();
				y2 = itsSd2.get(j).getY();
				x1 = itsSd1.get(i).getX();
				x2 = itsSd2.get(j).getX();

				if (y2 < y1 - itsDelta)
					theBlueIndex++;

				else if (y2 > y1 - itsDelta && y2 < itsDelta + y1) {
					theDistance = Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2);

					if (itsDelta == 0 || itsDelta > theDistance) {
						itsDelta = Math.sqrt(theDistance);
						itsFirstPoint.setLocation(x1, y1);
						itsSecondPoint.setLocation(x2, y2);
					}
				}

				else if (y2 > itsDelta + y1)
					break;
			}
		}
	}

	// --------------------------------------------------------------------------

	/**
	 * Prints data
	 * 
	 */
	public void printData() {
		System.out.println("---------BY X---------");
		for (int i = 0; i < itsByX.size(); i++) {
			System.out.println("X : " + itsByX.get(i).getX() + " Y : " + itsByX.get(i).getY());
		}
		System.out.println("---------BY X---------\n");
		System.out.println("---------BY Y---------");
		for (int i = 0; i < itsByY.size(); i++) {
			System.out.println("X : " + itsByY.get(i).getX() + " Y : " + itsByY.get(i).getY());
		}
		System.out.println("---------BY Y---------\n");
	}

	// --------------------------------------------------------------------------

	/**
	 * Prints data divided into parts
	 * 
	 */
	public void printParts() {
		System.out.println("---------X1---------");
		for (int i = 0; i < itsByX1.size(); i++) {
			System.out.println("X : " + itsByX1.get(i).getX() + " Y : " + itsByX1.get(i).getY());
		}
		System.out.println("---------X1---------\n");
		System.out.println("---------X2---------");
		for (int i = 0; i < itsByX2.size(); i++) {
			System.out.println("X : " + itsByX2.get(i).getX() + " Y : " + itsByX2.get(i).getY());
		}
		System.out.println("---------X2---------\n");
		System.out.println("---------Y1---------");
		for (int i = 0; i < itsByY1.size(); i++) {
			System.out.println("X : " + itsByY1.get(i).getX() + " Y : " + itsByY1.get(i).getY());
		}
		System.out.println("---------Y1---------\n");
		System.out.println("---------Y2---------");
		for (int i = 0; i < itsByY2.size(); i++) {
			System.out.println("X : " + itsByY2.get(i).getX() + " Y : " + itsByY2.get(i).getY());
		}
		System.out.println("---------Y2---------\n");
	}

	// --------------------------------------------------------------------------

	/**
	 * Prints delta space
	 * 
	 */
	public void printDeltaSpace() {
		System.out.println("---------SD1---------");
		for (int i = 0; i < itsSd1.size(); i++) {
			System.out.println("X : " + itsSd1.get(i).getX() + " Y : " + itsSd1.get(i).getY());
		}
		System.out.println("---------SD1---------\n");
		System.out.println("---------SD2---------");
		for (int i = 0; i < itsSd2.size(); i++) {
			System.out.println("X : " + itsSd2.get(i).getX() + " Y : " + itsSd2.get(i).getY());
		}
		System.out.println("---------SD2---------\n");
	}

	// --------------------------------------------------------------------------

	/**
	 * Prints results
	 * 
	 */
	public void printResults() {
		System.out.println("---------Points---------");
		System.out.println("X : " + itsFirstPoint.getX() + " Y : " + itsFirstPoint.getY());
		System.out.println("X : " + itsSecondPoint.getX() + " Y : " + itsSecondPoint.getY());
		System.out.println("---------Points---------\n");

		System.out.println("\nDistance : " + itsDelta);
		System.out.println("\nMediana : " + itsMedianaX);
	}

	// --------------------------------------------------------------------------

	/**
	 * Makes copy of data
	 * 
	 */
	public void copy(ArrayList<Point> copy, ArrayList<Point> data) {
		for (int i = 0; i < data.size(); i++) {
			copy.add(data.get(i));
		}
	}
}