package inf102.h21.guesser;

import inf102.h21.main.RandomNumber;

/**
 * Guesses every number sequentialy from lowerbound until number is found.
 * 
 * @author Sondre Bolland
 *
 */
public class SequentialGuesser implements IGuesser {

	
	@Override
	public int findNumber(RandomNumber number) {
		int lowerbound = number.getLowerbound();
		int numberGuess = lowerbound;
		
		while (number.guess(numberGuess) != 0) {
			numberGuess++;
		}

		return numberGuess;
	}

}
