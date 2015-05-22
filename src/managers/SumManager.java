package managers;

import utilities.FileReader;
import algorithms.Sum;

public class SumManager {

	SumManager() {
		Sum su = new Sum();
		su.sumAlgorithm(FileReader.itsDataNumbers, FileReader.itsSumT, FileReader.itsSumEpsilon);
	}

}
