package managers;

import java.util.ArrayList;
import java.util.Scanner;

import utilities.FileReader;
import algorithms.WindowQuery;
import javafx.scene.shape.Line;

public class WindowQueryManager {
	
	public static ArrayList<Line> itsQueryBox = new ArrayList<Line>();

	public WindowQueryManager() {
		WindowQuery wq = new WindowQuery();
		System.out.print("Enter : ");
		Scanner in = new Scanner(System.in);
		int startX = in.nextInt();
		int startY = in.nextInt();
		int endX = in.nextInt();
		int endY = in.nextInt();
		in.close();

		Line theLeft = new Line(startX, startY, startX, endY);
		Line theRight = new Line(endX, endY, endX, startY);
		
		itsQueryBox.add(theLeft);
		itsQueryBox.add(theRight);
		itsQueryBox.add(new Line(theLeft.getEndX(),theLeft.getEndY(),
						theRight.getStartX(),theRight.getStartY()));
		itsQueryBox.add(new Line(theLeft.getStartX(),theLeft.getStartY(),
				theRight.getEndX(),theRight.getEndY()));
		
		System.out.println("left : " + theLeft.getStartX() + "," + theLeft.getStartY() + "   " + theLeft.getEndX() + "," + theLeft.getEndY());
		System.out.println("right : " + theRight.getStartX() + "," + theRight.getStartY() + "   " + theRight.getEndX() + "," + theRight.getEndY());

		wq.constructIntervalTree(FileReader.itsDataLines);
		wq.queryIntervalTree(wq.itsTree.get(0), theLeft);
		wq.queryIntervalTree(wq.itsTree.get(0), theRight);
		wq.printResult(WindowQuery.itsResult);
	}
}