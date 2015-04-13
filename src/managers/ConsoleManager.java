package managers;

import java.io.FileNotFoundException;

public class ConsoleManager {

    @SuppressWarnings("unused")
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
        default:
            break;

        }
    }
}