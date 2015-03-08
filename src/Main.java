import java.io.FileNotFoundException;

public class Main {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		FileReader fr = new FileReader("dane.txt");
		fr.printData();
		ClosestPoints cp = new ClosestPoints();
		cp.sortByX(fr.data);
		cp.sortByY(fr.data);
		cp.printData(fr.data);
	}
}