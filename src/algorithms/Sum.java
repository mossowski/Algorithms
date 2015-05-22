package algorithms;

import java.util.ArrayList;

public class Sum {

	public static ArrayList<Integer> itsL = null;

	public Sum() {
		itsL = new ArrayList<Integer>();
	}

	public void sumAlgorithm(ArrayList<Integer> aDataPoints, double aT, double aEpsilon) {

		ArrayList<Integer> theE = new ArrayList<Integer>();
		itsL.add(0);

		for (int i = 0; i < aDataPoints.size(); i++) {
			theE = merge(itsL, aDataPoints.get(i));
			itsL = trim(theE, aEpsilon / aDataPoints.size());
			for (int j = itsL.size() - 1; j >= 0; j--) {
				if (itsL.get(j) > aT)
					itsL.remove(j);
			}
		}

		System.out.println("----------------------------------");
		System.out.println("Result: " + itsL.get(itsL.size() - 1));
		System.out.println("----------------------------------");
	}

	// --------------------------------------------------------------------------

	public ArrayList<Integer> trim(ArrayList<Integer> aDataPoints, double aDelta) {

		ArrayList<Integer> theLOut = new ArrayList<Integer>();
		theLOut.add(aDataPoints.get(0));

		int tmp = aDataPoints.get(0);

		for (int i = 1; i < aDataPoints.size(); i++) {

			int theNewNumber = aDataPoints.get(i);

			if (tmp < (1 - aDelta) * theNewNumber) {
				theLOut.add(theNewNumber);
				tmp = theNewNumber;
			}
		}

		return theLOut;
	}

	// --------------------------------------------------------------------------

	public ArrayList<Integer> merge(ArrayList<Integer> aDataPoints, int aAi) {

		ArrayList<Integer> theMerged = new ArrayList<Integer>();
		theMerged.add(aDataPoints.get(0));

		boolean theEndI = true;
		boolean theEndJ = true;
		int theI = 1;
		int theJ = 0;
		int tmp = aDataPoints.get(0);

		if (aDataPoints.size() == 1)
			theMerged.add(aDataPoints.get(0) + aAi);
		else {
			while (theEndI || theEndJ) {
				// if first array end just check for duplicates
				if (!theEndI) {
					if (tmp != aDataPoints.get(theJ) + aAi) {
						theMerged.add(aDataPoints.get(theJ) + aAi);
						if (theJ < aDataPoints.size() - 1)
							theJ++;
						else
							theEndJ = false;
					}
					else{
						if (theJ < aDataPoints.size() - 1)
							theJ++;
						else
							theEndJ = false;
					}
				}
				// if first array hold value <= then second array value and not
				// duplicate, add from first array
				// and increment array
				else if ((aDataPoints.get(theI) <= (aDataPoints.get(theJ) + aAi)) && tmp != aDataPoints.get(theJ) + aAi) {
					theMerged.add(aDataPoints.get(theI));
					tmp = aDataPoints.get(theI);

					if (theI < aDataPoints.size() - 1)
						theI++;
					else
						theEndI = false;
				}
				// if first array hold value > then second array value and not
				// duplicate, add from second array
				// and increment array
				else if (aDataPoints.get(theI) > aDataPoints.get(theJ) + aAi && tmp != aDataPoints.get(theJ) + aAi) {
					theMerged.add(aDataPoints.get(theJ) + aAi);
					tmp = aDataPoints.get(theJ) + aAi;

					if (theJ < aDataPoints.size() - 1)
						theJ++;
					else
						theEndJ = false;
				}
				// if duplicate, increment second array
				else if (tmp == aDataPoints.get(theJ) + aAi) {
					theJ++;
				}
			}
		}

		return theMerged;
	}

}
