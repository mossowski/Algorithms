import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

	private Scanner itsInFileData;
	public ArrayList<Point> itsData;

	FileReader(String fileName) throws FileNotFoundException {
		itsInFileData = new Scanner(new File(fileName));
		itsData = new ArrayList<Point>();

		while (itsInFileData.hasNextLine()) {
			String theLine = itsInFileData.nextLine();
			Scanner s = new Scanner(theLine);
			s.useDelimiter(" ");
			int x = s.nextInt();
			int y = s.nextInt();
			Point p = new Point(x, y);
			itsData.add(p);
			s.close();
		}
		itsInFileData.close();
	}

	// --------------------------------------------------------------------------

	/**
	 * Prints data
	 * 
	 */
	public void printData() {
		System.out.println("----------FILE DATA----------");
		for (int i = 0; i < itsData.size(); i++) {
			System.out.println("X : " + itsData.get(i).getX() + " Y : " + itsData.get(i).getY());
		}
		System.out.println("----------FILE DATA----------\n");
	}
}