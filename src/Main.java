import java.io.FileNotFoundException;

public class Main {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		FileReader fr = new FileReader("dane2.txt");
		fr.printData();
		ClosestPoints cp = new ClosestPoints();
		cp.sortByX(fr.data);
		cp.sortByY(fr.data);
		cp.partXY();
		cp.printData(fr.data);
		cp.printParts();
		cp.deltaHalf(cp.byX1);
		cp.deltaHalf(cp.byX2);
		cp.makeDeltaPart(cp.byX1, cp.byX2);
		cp.printDeltaSpace();
		// cp.deltaHalfLast(cp.sd1);  Need to add this
		cp.printResults();
	}
}