import java.io.FileNotFoundException;

public class Main {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		FileReader fr = new FileReader("integers2.txt");
		ClosestPoints cp = new ClosestPoints();
		cp.sortByX(fr.data);
		cp.sortByY(fr.data);
		cp.partXY();
		cp.deltaHalf();
		cp.makeDeltaPart();
		cp.deltaHalfLast();
		//fr.printData();
		//cp.printParts();
		cp.printDeltaSpace();
		cp.printResults();
	}
}