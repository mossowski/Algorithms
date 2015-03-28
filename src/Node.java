import java.util.ArrayList;
import javafx.scene.shape.Line;

public class Node {
	
	private double itsMedianaX;
	private ArrayList<Line> itsSMid = null;
	private ArrayList<Line> itsLLeft = null;
	private ArrayList<Line> itsLRight = null;
    private Node leftChildren;
    private Node rightChildren;
    
    Node (double aMedianaX, ArrayList<Line> aSMid, ArrayList<Line> aSLeft, ArrayList<Line> aSRight, ArrayList<Line> aLLeft, ArrayList<Line> aLRight) {
    	itsMedianaX = aMedianaX;
    	itsSMid = aSMid;
    	itsLLeft = aLLeft;
    	itsLRight = aLRight;
    	leftChildren = constructIntervalTree(aSLeft);
    	rightChildren = constructIntervalTree(aSRight);
    }
}