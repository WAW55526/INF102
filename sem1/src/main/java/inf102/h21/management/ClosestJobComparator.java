package inf102.h21.management;

import java.util.Comparator;
import java.util.List;

public class ClosestJobComparator implements Comparator<Job> {

	private Robot robot;
	
	public ClosestJobComparator(Robot robot) {
		this.robot = robot;
	}
	
	/*
	 * Sorts the jobs by how close they are to the robot
	 */
	@Override
	public int compare(Job o1, Job o2) {
		if (o1.location.dist(robot.getLocation()) < o2.location.dist(robot.getLocation())) {
			return -1;
		}
		else if (o1.location.dist(robot.getLocation()) > o2.location.dist(robot.getLocation())) {
			return 1;
		}
		return 0;
	}

}
