package managers;

import utilities.FileReader;
import algorithms.Maxima;

public class MaximaManager {

	MaximaManager() {
		Maxima mx = new Maxima();
		mx.detectMaxima(FileReader.itsDataPoints);
		mx.printResult(Maxima.itsResult);
	}
}