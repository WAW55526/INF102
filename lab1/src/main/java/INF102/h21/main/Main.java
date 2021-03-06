package INF102.h21.main;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import INF102.h21.triplicate.ITriplicate;
import INF102.h21.triplicate.MyFantasticTriplicateFinder;
import INF102.h21.triplicate.TriplicateBruteForce;


/**
 * Records the time of different algorithms for determining if a list contains
 * (at least) three of a given element.
 * 
 * @author Sondre Bolland
 *
 */
public class Main {

	/**
	 * Number of integers in generated list
	 */
	public static final int N_INTEGERS = 1000;
	/**
	 * Lower bound of integers in generated list
	 */
	public static final int LOWERBOUND = 0;
	/**
	 * Upper bound of integers in generated list
	 */
	public static final int UPPERBOUND = N_INTEGERS;

	public static DecimalFormat formatter = new DecimalFormat("#, ###");

	public static void main(String[] args) {
		List<ITriplicate<Integer>> algorithmList = new ArrayList<>();
		algorithmList.add(new TriplicateBruteForce<Integer>());
		algorithmList.add(new MyFantasticTriplicateFinder<Integer>());

		// Generate lists of integers with (and without) triplicates
		System.out.println("---Generating Integer Lists---");
		List<List<Integer>> integerLists = new ArrayList<>();
		int nLists = 10;
		for (int i = 0; i < nLists; i++) {
			if (i % 2 == 0)
				integerLists.add(generateList(true));
			else
				integerLists.add(generateList(false));
		}
		System.out.printf("%slists generated with %selements each.%n%n", formatter.format(nLists),
				formatter.format(N_INTEGERS));

		// Run containsThree of each algorithm on the generated lists.
		// Record time of each algorithm for every list
		System.out.println("---Processing Algorithms---");
		for (ITriplicate<Integer> algorithm : algorithmList) {
			long timeElapsedMicro = timeAlgorithm(algorithm, integerLists) / 1000;
			double timeElapsedSeconds = (timeElapsedMicro / 1000000.0);
			String algorithmName = algorithm.getClass().getSimpleName();
			System.out.printf("%-27s| time elapsed: %10d microseconds (%f seconds)%n", algorithmName, timeElapsedMicro,
					timeElapsedSeconds);
		}
	}

	/**
	 * Runs the given <code>algorithm</code> on several lists
	 * <code>integerLists</code> to find any occurrence of a triplicate. Records the
	 * time spent to find/not find triplicates in all the lists.
	 * 
	 * @param algorithm    findTriplicate algorithm
	 * @param integerLists list of lists of integers to be searched for triplicates
	 * @return long of nanoseconds spent
	 */
	public static long timeAlgorithm(ITriplicate<Integer> algorithm, List<List<Integer>> integerLists) {
		long startTime = System.nanoTime();
		for (List<Integer> integerList : integerLists) {
			algorithm.findTriplicate(integerList);
		}
		long endTime = System.nanoTime();
		long timeElapsed = (endTime - startTime);
		return timeElapsed;
	}

	/**
	 * Generate a list of <code>n</code> random integers between
	 * <code>lowerbound</code> and <code>upperbound</code>
	 * 
	 * @param n          integers to be generated
	 * @param lowerbound of integers generated
	 * @param upperbound of integers generated
	 * @return list of integers generated
	 */
	public static List<Integer> generateList(boolean withTriplet) {
		Random rand = new Random();
		List<Integer> integerList = new ArrayList<>(N_INTEGERS);

		while (integerList.size() < N_INTEGERS) {
			int number = rand.nextInt(UPPERBOUND - LOWERBOUND) + LOWERBOUND;
			if (!integerList.contains(number))
				integerList.add(number);
		}

		if (withTriplet) {
			int randNum = rand.nextInt(UPPERBOUND - LOWERBOUND) + UPPERBOUND;
			List<Integer> randIndecies = new ArrayList<>();
			while (randIndecies.size() < 3) {
				int randIdx = rand.nextInt(N_INTEGERS);
				if (!randIndecies.contains(randIdx)) {
					integerList.set(randIdx, randNum);
					randIndecies.add(randIdx);
				}
			}
		}
		return integerList;
	}

}