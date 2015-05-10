package managers;

import java.io.FileNotFoundException;

import utilities.FileReader;

public class FileReaderManager {

	public FileReaderManager() throws FileNotFoundException {
	    /*  1 for ClosestPoints
            2 for Maxima
            3 for WindowQuery
            4 for Bitonic
            5 for Centrum */
		FileReader fr = new FileReader("integerscentrum.txt");
		fr.loadData(5);
	}
}