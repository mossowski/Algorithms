package managers;

import algorithms.ClosestPoints;
import utilities.FileReader;

public class ClosestPointsManager {

    public ClosestPointsManager() {
        ClosestPoints cp = new ClosestPoints();
        cp.sortByX(FileReader.itsDataPoints);
        cp.sortByY(FileReader.itsDataPoints);
        cp.partXY();
        cp.deltaHalf();
        cp.makeDeltaPart();
        cp.deltaHalfLast();
        cp.printParts();
        cp.printDeltaSpace();
        cp.printResults();
    }
}