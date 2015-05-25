package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

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
		
		int max = Collections.max(itsL);

		System.out.println("----------------------------------");
		System.out.println("Result: " + max);
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
		
		HashSet<Integer> theHash = new HashSet<Integer>();
		theHash.addAll(aDataPoints);
		
		for(int i = 0 ; i < aDataPoints.size(); i++)
		{
			theHash.add(aDataPoints.get(i) + aAi);
		}
		
		ArrayList<Integer> theMerged = new ArrayList<Integer>();
		
		theMerged.addAll(theHash);
		
		Collections.sort(theMerged);

		return theMerged;
	}

}
