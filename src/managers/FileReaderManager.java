package managers;

import java.io.FileNotFoundException;

import utilities.FileReader;

public class FileReaderManager {

	public FileReaderManager() throws FileNotFoundException {
	    /*  1 for ClosestPoints
         *  2 for Maxima
            3 for WindowQuery */
		FileReader fr = new FileReader("integers3.txt");
		fr.loadData(4);
	}
}