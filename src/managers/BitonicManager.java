package managers;

import utilities.FileReader;
import algorithms.Bitonic;

public class BitonicManager {

	BitonicManager() {
		Bitonic bi = new Bitonic();
		bi.printResultLength(bi.makeBitonicRoute(FileReader.itsDataMaximaPoints));
	}

}
