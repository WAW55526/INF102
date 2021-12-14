package inf102.h21.lab4;

public class Main {

	public static void main(String[] args) {
		String inputFilename = "input/input0";
		TripInformation info = new TripInformation(inputFilename);
		System.out.println(info);
		System.out.println(info.solve());
	}
}
