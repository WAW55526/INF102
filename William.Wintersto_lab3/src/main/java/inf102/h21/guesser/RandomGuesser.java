package inf102.h21.guesser;

import java.util.Random;

import inf102.h21.main.RandomNumber;

/**
 * Guesses a random number between the lower and upper bound until the correct is found.
 * 
 * @author Sondre Bolland
 *
 */
public class RandomGuesser implements IGuesser {

	private Random rand = new Random();
	private int lowerbound;
	private int upperbound;
	
	@Override
	public int findNumber(RandomNumber number) {
		lowerbound = number.getLowerbound();
		upperbound = number.getUpperbound();
		
		int numberGuess;
		do {
			numberGuess = makeGuess();
		} while (number.guess(numberGuess) != 0);

		return numberGuess;
	}

	private int makeGuess() {
		return rand.nextInt(upperbound-lowerbound) + lowerbound;
	}

}
