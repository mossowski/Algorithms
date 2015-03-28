import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.scene.shape.Line;

public class Main {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		/*
		 * FileReader fr = new FileReader("integers2.txt"); ClosestPoints cp =
		 * new ClosestPoints(); cp.sortByX(fr.itsDataPoints);
		 * cp.sortByY(fr.itsDataPoints); cp.partXY(); cp.deltaHalf();
		 * cp.makeDeltaPart(); cp.deltaHalfLast(); // fr.printDataPoints(); //
		 * cp.printParts(); cp.printDeltaSpace(); cp.printResults();
		 */

		System.out.print("Enter : ");
		Scanner in = new Scanner(System.in);
		int startX = in.nextInt();
		int startY = in.nextInt();
		int endX = in.nextInt();
		int endY = in.nextInt();
		in.close();
		Line left = new Line(startX, startY, startX, endY);
		Line right = new Line(endX, endY, endX, startY);
		System.out.println("left : " + left.getStartX() + "," + left.getStartY() + "   " + left.getEndX() + "," + left.getEndY());
		System.out.println("right : " + right.getStartX() + "," + right.getStartY() + "   " + right.getEndX() + "," + right.getEndY());

		FileReader fr = new FileReader("sector.txt", true);
		WindowQuery wq = new WindowQuery();
		wq.constructIntervalTree(fr.itsDataLines);
		
		// wq.printResults();
		// fr.printDataLines();
		// wq.printTree(wq.itsTree.get(0));
		wq.queryIntervalTree(wq.itsTree.get(0), left);
		wq.queryIntervalTree(wq.itsTree.get(0), right);
		wq.printResult(wq.itsResult);
	}
}