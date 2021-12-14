package inf102.h21.management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomStrategy extends AbstractStrategy {
	
	private List<Robot> selectedRobots;
	
	/*
	 * Shuffles the list of available robots and returns the x first robots
	 * Where x is the number of robots needed
	 */
	@Override
	protected List<Robot> selectRobots(Job job) {
		selectedRobots = new ArrayList<>();
		
		if (job.robotsNeeded > available.size()) {
			return selectedRobots;
		}
		
		Collections.shuffle(available);
		
		for (int i = 0; i < job.robotsNeeded; i++) {
			selectedRobots.add(available.get(i));
		}
			
		return selectedRobots;
	}

	@Override
	public String getName() {
		return "Random strategy";
	}
}
