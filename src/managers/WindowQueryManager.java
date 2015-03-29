package managers;

import utilities.FileReader;
import algorithms.WindowQuery;
import javafx.scene.shape.Line;

public class WindowQueryManager {

	public WindowQueryManager(Line aLeft, Line aRight) {
		WindowQuery wq = new WindowQuery();
		wq.constructIntervalTree(FileReader.itsDataLines);
		wq.queryIntervalTree(wq.itsTree.get(0), aLeft);
		wq.queryIntervalTree(wq.itsTree.get(0), aRight);
		wq.printResult(wq.itsResult);
	}
}