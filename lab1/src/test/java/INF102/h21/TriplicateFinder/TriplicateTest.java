package INF102.h21.TriplicateFinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import INF102.h21.triplicate.TriplicateBruteForce;
import INF102.h21.triplicate.ITriplicate;

public class TriplicateTest {
	
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
	public static final int UPPERBOUND = N_INTEGERS*1000;
	
	// TODO: Initiate variable with own algorithm
	ITriplicate<Integer> algorithm = new TriplicateBruteForce<>();
	/**
	 * List of integers with at least one element occurring three times
	 */
	List<Integer> integerListWithTriplet;
	/**
	 * List of integers with no element occurring three times
	 */
	List<Integer> integerListWithoutTriplet;
	
	@Before
	public void setup() {
		integerListWithTriplet = generateList(true);
		integerListWithoutTriplet = generateList(false);
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
	
	/**
	 * Find all elements in list that occur (at least) three times
	 * 
	 * @param list
	 * @return list of integers occurring three times in the input list
	 */
	public static List<Integer> getTripletOccurrences(List<Integer> list) {
		List<Integer> threes = new ArrayList<>();
		Map<Integer, Integer> frequencies = new HashMap<>();
		for (Integer e : list) {
			if (frequencies.containsKey(e))
				frequencies.put(e, frequencies.get(e) + 1);
			else
				frequencies.put(e, 1);
		}
		for (Integer e : list) {
			if (frequencies.get(e) >= 3) {
				threes.add(e);
			}
		}
		return threes;
	}
	
	/* ############### TESTS ############### */
	@Test
	public void knownThreeTest() {
		for (Integer element: getTripletOccurrences(integerListWithTriplet)) {
			Integer foundElement = algorithm.findTriplicate(integerListWithTriplet);
			if (element.equals(foundElement))
				return;
		}
		fail();
	}
	
	@Test
	public void noKnownThreeTest() {
		Integer foundElement = algorithm.findTriplicate(integerListWithoutTriplet);
		assertEquals(foundElement, null);
	}
}