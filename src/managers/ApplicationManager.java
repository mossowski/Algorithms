package managers;

import javax.swing.JFrame;

import controllers.BitonicController;
import controllers.WindowQueryController;

public class ApplicationManager extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplicationManager() {
		super("Algorithms");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocation(100, 100);
		//setContentPane(new WindowQueryController());
		setContentPane(new BitonicController());
		setVisible(true);
	}
}