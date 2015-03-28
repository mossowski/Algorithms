import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.shape.Line;

public class WindowQuery {

	private ArrayList<Line> itsS = null;
	private ArrayList<Line> itsSMid = null;
	private ArrayList<Line> itsSLeft = null;
	private ArrayList<Line> itsSRight = null;
	private int itsXMed = 0;

	public void makeSParts() {

		double theXStart = 0;
		double theXEnd = 0;

		for (int i = 0; i < itsS.size(); i++) {

			theXStart = itsS.get(i).getStartX();
			theXEnd = itsS.get(i).getEndX();

			if (theXEnd < itsXMed)
				itsSLeft.add(itsS.get(i));
			else if (theXEnd >= itsXMed && theXStart <= itsXMed)
				itsSMid.add(itsS.get(i));
			else if (theXEnd < itsXMed)
				itsSRight.add(itsS.get(i));
		}

	}
}
