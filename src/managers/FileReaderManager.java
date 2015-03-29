package managers;

import java.io.FileNotFoundException;

import utilities.FileReader;

public class FileReaderManager {

	public FileReaderManager() throws FileNotFoundException {
		new FileReader("sector.txt", true);
	}
}