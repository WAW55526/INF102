package inf102.h21.lab4;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class TripInformationTest {

	@Test
	public void tripTest() {
		int nInputFiles = 2;
		for (int i = 0; i < nInputFiles; i++) {
			String inputFilename = "input/input" + i;
			String outputFilename = "input/output" + i;
			
			TripInformation info = new TripInformation(inputFilename);
			List<Integer> queryGuesses = info.solve();
			List<Integer> queryAnswers = readOutput(outputFilename);
			for (int j = 0; j < queryAnswers.size(); j++) {
				int queryGuess = queryGuesses.get(j);
				int queryAnswer = queryAnswers.get(j);
				assertEquals(queryGuess, queryAnswer);
			}
			
		}
	}
	
	public List<Integer> readOutput(String filename) {
		File outputfile = new File(filename);
		if (!outputfile.isFile())
			throw new IllegalArgumentException("No such file: " + filename);
		List<Integer> years = new ArrayList<>();
		try {
			Scanner sc = new Scanner(outputfile);
			while (sc.hasNext()) {
				years.add(sc.nextInt());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return years;
	}
}
