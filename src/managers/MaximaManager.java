package managers;

import utilities.FileReader;
import algorithms.Maxima;

public class MaximaManager {

	MaximaManager() {
		Maxima mx = new Maxima();
		mx.printResult(mx.detectMaxima(FileReader.itsDataMaximaPoints));
	}
}