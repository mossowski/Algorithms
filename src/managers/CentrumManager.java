package managers;

import utilities.FileReader;
import algorithms.Centrum;

public class CentrumManager {
	
	CentrumManager() {
		Centrum ce = new Centrum();
		ce.printResult(ce.makeCentrum(FileReader.itsDataPoints,FileReader.itsCentrumK,FileReader.itsCentrumN));
	}

}
