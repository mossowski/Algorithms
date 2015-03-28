import java.io.FileNotFoundException;

public class Main {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {		
		
		/*FileReader fr = new FileReader("integers2.txt");
		ClosestPoints cp = new ClosestPoints();
		cp.sortByX(fr.itsDataPoints);
		cp.sortByY(fr.itsDataPoints);
		cp.partXY();
		cp.deltaHalf();
		cp.makeDeltaPart();
		cp.deltaHalfLast();
		// fr.printDataPoints();
		// cp.printParts();
		cp.printDeltaSpace();
		cp.printResults();*/
		
		FileReader fr = new FileReader("sector.txt",true);
	    fr.printDataLines();
		
		
	}
}