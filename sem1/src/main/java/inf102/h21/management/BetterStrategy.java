package inf102.h21.management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BetterStrategy extends AbstractStrategy {

	private List<Robot> selectedRobots;
	private List<Job> jobs; // All jobs that we know of, both finished and the ones that are being worked on
	
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
		return "Better strategy";
	}
	
	@Override
	protected void doJobs() {
		
		while (!backLog.isEmpty()) {
			Job job = selectJob();
			if (job == null) { // If job is null then there is not enough robots to do the job
				moveFreeRobots();
				break;
			}
			
			List<Robot> selected = selectRobots(job);
			
			if(assignRobots(selected, job)) {
				removeJob(job);
			}
			else
				break;
		}
		if(backLog.isEmpty())
			moveFreeRobots();
	}
	
	@Override
	protected Job selectJob() {
		Job fastestJob = backLog.peek();
		double fastestJobDistance = 0.0; // This value does not matter i just need it to be initialised, see "else if (firstJob)"
		boolean firstJob = true;
		List<Robot> closestRobots = new ArrayList<>();
		
		for (Job job : backLog) {
			closestRobots = selectRobots(job);
			
			if (closestRobots.size() == 0) {
				return null;
			}
			else if (firstJob) {
				fastestJobDistance = closestRobots.get(closestRobots.size()-1).getLocation().dist(job.location);
				firstJob = false;
			}
			else if (closestRobots.get(closestRobots.size()-1).getLocation().dist(job.location) < fastestJobDistance) {
				fastestJob = job;
				fastestJobDistance = closestRobots.get(closestRobots.size()-1).getLocation().dist(job.location);
			}
		}
		return fastestJob;
	}
	
	@Override
	protected void moveFreeRobots() {  
		for (Robot robot : available) {
				
			Collections.sort(jobs, new ClosestJobComparator(robot));
			for (Job job : jobs) {
					
				if (job.location != robot.getLocation()) {
					robot.move(job.location);
					break;
				}
			}
		}
	}
	
	@Override
	public void registerNewJob(Job job) {
		backLog.add(job);
		jobs.add(job);
		doJobs();
	}
	
	@Override
	public void registerRobots(List<Robot> robots) {
		this.jobs = new ArrayList<>();
		this.robots = new ArrayList<Robot>(robots); 
		available = new ArrayList<>(robots);
	}
	
}
