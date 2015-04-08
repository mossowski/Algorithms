package managers;

import java.io.FileNotFoundException;

public class ConsoleManager {

	public ConsoleManager() throws FileNotFoundException {

		new FileReaderManager();
		// ClosestPoints ---> new ClosestPointsManager();
		// WindowQuery ---> new WindowQueryManager();
		new MaximaManager();
	}
}