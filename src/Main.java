import java.awt.EventQueue;
import java.io.FileNotFoundException;

import managers.ApplicationManager;
import managers.ConsoleManager;

public class Main {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("unused")
    public static void main(String[] args) throws FileNotFoundException {

	    /*  cs for ClosestPoints
	     *  wq for WindowQuery 
	        m for Maxima */
	    
	    String theWhich = "m";
	    
		new ConsoleManager(theWhich);

		if (theWhich == "wq") {
		    EventQueue.invokeLater(new Runnable() {
		        @Override
                public void run() {
		            new ApplicationManager();
		        }
		    });
		}
	}
}