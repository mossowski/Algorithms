import java.util.ArrayList;
import javafx.scene.shape.Line;

public class Node {

	private double itsMedianaX;
	private ArrayList<Line> itsSMid = null;
	private ArrayList<Line> itsLLeft = null;
	private ArrayList<Line> itsLRight = null;
	public Node leftChildren;
	public Node rightChildren;

	Node(double aMedianaX, ArrayList<Line> aSMid, ArrayList<Line> aSLeft, ArrayList<Line> aSRight, ArrayList<Line> aLLeft, ArrayList<Line> aLRight) {
		itsMedianaX = aMedianaX;
		itsSMid = aSMid;
		itsLLeft = aLLeft;
		itsLRight = aLRight;
	}
}