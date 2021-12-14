package inf102.h21.management;

import java.util.Comparator;

public class ClosestComparator implements Comparator<Robot> {

	private Job job;
	private Robot robot;
	private String whatAreWeComparingWith;
	
    public ClosestComparator(Job job){
    	this.job = job;
    	whatAreWeComparingWith = "job";
    }
    
    public ClosestComparator(Robot robot){
    	this.robot = robot;
    	whatAreWeComparingWith = "robot";
    }
	
    /*
     * Sorts the robots based on how close they are to the job
     */
	@Override
	public int compare(Robot o1, Robot o2) {
		
		if (whatAreWeComparingWith == "job") {
			if (o1.getLocation().dist(job.location) < o2.getLocation().dist(job.location)) {
				return -1;
			}
			else if (o1.getLocation().dist(job.location) > o2.getLocation().dist(job.location)) {
				return 1;
			}
			return 0;
		}
		else if (whatAreWeComparingWith == "robot") {
			if (o1.getLocation().dist(robot.getLocation()) < o2.getLocation().dist(robot.getLocation())) {
				return -1;
			}
			else if (o1.getLocation().dist(robot.getLocation()) > o2.getLocation().dist(robot.getLocation())) {
				return 1;
			}
			return 0;
		}
		throw new IllegalArgumentException("'whatAreWeComparingWith' variable was not updated");
	}

}
