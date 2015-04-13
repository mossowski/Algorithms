package utilities;

import java.util.ArrayList;
import javafx.scene.shape.Line;

public class Node {

    private double itsMedianaX;
    private ArrayList<Line> itsSMid = null;
    private ArrayList<Line> itsSLeft = null;
    private ArrayList<Line> itsSRight = null;
    private ArrayList<Line> itsLLeft = null;
    private ArrayList<Line> itsLRight = null;
    public Node leftChildren;
    public Node rightChildren;

    public Node(double aMedianaX, ArrayList<Line> aSMid, ArrayList<Line> aSLeft, ArrayList<Line> aSRight,
        ArrayList<Line> aLLeft, ArrayList<Line> aLRight) {
        itsMedianaX = aMedianaX;
        itsSMid = aSMid;
        itsSLeft = aSLeft;
        itsSRight = aSRight;
        itsLLeft = aLLeft;
        itsLRight = aLRight;
    }

    // --------------------------------------------------------------------------

    public double getItsMedianaX() {
        return itsMedianaX;
    }

    // --------------------------------------------------------------------------

    public void setItsMedianaX(double itsMedianaX) {
        this.itsMedianaX = itsMedianaX;
    }

    // --------------------------------------------------------------------------

    public ArrayList<Line> getItsSMid() {
        return itsSMid;
    }

    // --------------------------------------------------------------------------

    public void setItsSMid(ArrayList<Line> itsSMid) {
        this.itsSMid = itsSMid;
    }

    // --------------------------------------------------------------------------

    public ArrayList<Line> getItsSLeft() {
        return itsSLeft;
    }

    // --------------------------------------------------------------------------

    public void setItsSLeft(ArrayList<Line> itsSLeft) {
        this.itsSLeft = itsSLeft;
    }

    // --------------------------------------------------------------------------

    public ArrayList<Line> getItsSRight() {
        return itsSRight;
    }

    // --------------------------------------------------------------------------

    public void setItsSRight(ArrayList<Line> itsSRight) {
        this.itsSRight = itsSRight;
    }

    // --------------------------------------------------------------------------

    public ArrayList<Line> getItsLLeft() {
        return itsLLeft;
    }

    // --------------------------------------------------------------------------

    public void setItsLLeft(ArrayList<Line> itsLLeft) {
        this.itsLLeft = itsLLeft;
    }

    // --------------------------------------------------------------------------

    public ArrayList<Line> getItsLRight() {
        return itsLRight;
    }

    // --------------------------------------------------------------------------

    public void setItsLRight(ArrayList<Line> itsLRight) {
        this.itsLRight = itsLRight;
    }
}