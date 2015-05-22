package managers;

import java.io.FileNotFoundException;

public class ConsoleManager {

	public ConsoleManager(String aKey) throws FileNotFoundException {

		new FileReaderManager();

		switch (aKey) {

		case "cp":
			new ClosestPointsManager();
			break;
		case "wq":
			new WindowQueryManager();
			break;
		case "m":
			new MaximaManager();
			break;
		case "bi":
			new BitonicManager();
			break;
		case "ce":
			new CentrumManager();
			break;
		case "su":
			new SumManager();
			break;
		default:
			break;

		}
	}
}