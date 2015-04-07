package managers;

import java.io.FileNotFoundException;

import utilities.FileReader;

public class FileReaderManager {

	public FileReaderManager() throws FileNotFoundException {
		// ClosestPoints ---> new FileReader("integers.txt");
		// WindowQuery ---> new FileReader("sector.txt", true);
		new FileReader("integers.txt");
	}
}