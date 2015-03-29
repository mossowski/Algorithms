import java.awt.EventQueue;
import java.io.FileNotFoundException;

import managers.ApplicationManager;
import managers.ConsoleManager;

public class Main {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		new ConsoleManager();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ApplicationManager();
			}
		});
	}
}