package managers;

import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.scene.shape.Line;

public class ConsoleManager {
	
	public static Line theLeft = null;
	public static Line theRight = null;

	public ConsoleManager() throws FileNotFoundException {
		System.out.print("Enter : ");
		Scanner in = new Scanner(System.in);
		int startX = in.nextInt();
		int startY = in.nextInt();
		int endX = in.nextInt();
		int endY = in.nextInt();
		in.close();

		theLeft = new Line(startX, startY, startX, endY);
		theRight = new Line(endX, endY, endX, startY);
		System.out.println("left : " + theLeft.getStartX() + "," + theLeft.getStartY() + "   " + theLeft.getEndX() + "," + theLeft.getEndY());
		System.out.println("right : " + theRight.getStartX() + "," + theRight.getStartY() + "   " + theRight.getEndX() + "," + theRight.getEndY());

		new FileReaderManager();
		new WindowQueryManager(theLeft, theRight);
	}
}