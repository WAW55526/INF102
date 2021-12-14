package inf102.h21.management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClosestStrategy extends AbstractStrategy {

	private List<Robot> selectedRobots;
	
	@Override
	protected List<Robot> selectRobots(Job job) {
		selectedRobots = new ArrayList<>();
		
		if (job.robotsNeeded > available.size()) {
			return selectedRobots;
		}
	
		Collections.sort(available, new ClosestComparator(job));
		
		for (int i = 0; i < job.robotsNeeded; i++) {
			selectedRobots.add(available.get(i));
		}
			
		return selectedRobots;
	}

	@Override
	public String getName() {
		return "Closest strategy";
	}

}
