package managers;

import javax.swing.JFrame;

import controllers.BitonicController;
import controllers.WindowQueryController;

public class ApplicationManager extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplicationManager(String aWhich) {
		super("Algorithms");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocation(100, 100);
		if (aWhich == "wq")
			setContentPane(new WindowQueryController());
		else if (aWhich.equals("bi"))
			setContentPane(new BitonicController());
		setVisible(true);
	}
}