package inf102.h21.guesser;

import inf102.h21.main.RandomNumber;

public class MyGeniusGuesser implements IGuesser {
	int upper;
	int lower;
	int myguess;
	
	@Override
	public int findNumber(RandomNumber number) {
		upper = number.getUpperbound();
		lower = number.getLowerbound();
		makeNewGuess();
		
		while (lower < upper) {
			int higherOrLower = number.guess(myguess);
			if (higherOrLower == -1) {
				updatebound(lower);
				makeNewGuess();
			} else if (higherOrLower == 1) {
				updatebound(upper);
				makeNewGuess();
			} else {
				return myguess;
			}
		}
		return myguess;
	}
	
	public void makeNewGuess() {
		myguess = (upper+lower)/2;
	}
	
	public void updatebound(int bound) {
		if (bound == lower) {
			lower = myguess+1;
		} else {
			upper = myguess-1;
		}
	}

}
