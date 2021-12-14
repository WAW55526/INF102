package inf102.h21.lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.javatuples.Pair;

public class TripInformation {

	/**
	 * Number of trips taken
	 */
	private int nTrips;
	/**
	 * Pairs of country and year. Trip taken to country in the year.
	 */
	private List<Pair<String, Integer>> countryYearPairs;
	/**
	 * Number of trip queries
	 */
	private int nQueries;
	/**
	 * List of queries. A query is a pair of country and an integer k asking in what
	 * year the k'th trip to this country was.
	 */
	private List<Pair<String, Integer>> queries;
	
	private Map<String, List<Integer>> tripMap = new HashMap<>();

	public TripInformation(String filename) {
		countryYearPairs = new ArrayList<Pair<String, Integer>>();
		queries = new ArrayList<Pair<String, Integer>>();
		readFromFile(filename);
	}

	/**
	 * Answer all queries in <code>queries</code>. A query is a pair of country and
	 * the number k of a trip. The answer to the query should be a year of when the
	 * k'th trip to the country was taken.
	 * 
	 * @return list of years (answers to each query)
	 */
	public List<Integer> solve() {
		addTrips();
		sortTripByYear();
		
		List<Integer> yearList = new ArrayList<>();
		for (Pair<String, Integer> pair : queries) {
			String country = pair.getValue0();
			int tripK = pair.getValue1();
			
			int year = answerQuery(country, tripK);
			yearList.add(year);
		}
		return yearList;
	}
	
	public int answerQuery(String country, int tripK) {
		return tripMap.get(country).get(tripK-1);
	}
	
	public void addTrips() {
		for (Pair<String, Integer> pair : countryYearPairs) {
			String country = pair.getValue0();
			int year = pair.getValue1();
			
			if (tripMap.containsKey(country)) {
				tripMap.get(country).add(year);
			}
			else {
				List<Integer> yearList = new ArrayList<>();
				yearList.add(year);
				tripMap.put(country, yearList);
			}
		}
	}
	
	public void sortTripByYear() {
		for (String country : tripMap.keySet()) {
			Collections.sort(tripMap.get(country));
		}
	}

	/**
	 * Read input from file and store in field variables.
	 * Input contains information about the trips taken: when and where.
	 * @param filename
	 */
	private void readFromFile(String filename) {
		File inputFile = new File(filename);
		try {
			Scanner sc = new Scanner(inputFile);
			nTrips = sc.nextInt();
			sc.nextLine();
			for (int i = 0; i < nTrips; i++) {
				String[] line = sc.nextLine().split(" ");
				String country = line[0];
				int year = Integer.parseInt(line[1]);
				countryYearPairs.add(new Pair<>(country, year));
			}
			nQueries = sc.nextInt();
			sc.nextLine();
			for (int i = 0; i < nQueries; i++) {
				String[] line = sc.nextLine().split(" ");
				String country = line[0];
				int tripK = Integer.parseInt(line[1]);
				queries.add(new Pair<>(country, tripK));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("Could not find file:" + filename);
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(nTrips);
		s.append("\n");
		for (Pair<String, Integer> pair : countryYearPairs) {
			s.append(pair.getValue0());
			s.append(" ");
			s.append(pair.getValue1());
			s.append("\n");
		}
		s.append(nQueries);
		s.append("\n");
		for (Pair<String, Integer> pair : queries) {
			s.append(pair.getValue0());
			s.append(" ");
			s.append(pair.getValue1());
			s.append("\n");
		}
		return s.toString();
	}

}
