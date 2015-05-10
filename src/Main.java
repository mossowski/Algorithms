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

		/*
		 * cs for ClosestPoints 
		 * wq for WindowQuery 
		 * bi for Bitonic 
		 * m for Maxima
		 */

		String theWhich = "ce";

		new ConsoleManager(theWhich);

		if (theWhich == "wq" || theWhich == "bi" || theWhich == "ce") {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					new ApplicationManager(theWhich);
				}
			});
		}
	}
}