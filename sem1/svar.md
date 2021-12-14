# Answer File - Mandatory 1
# Description of each Implementation
Briefly describe your implementation in the different strategies. What was your plan and how did you execute it? If there were any problems and/or issues failed implementations please add a description.

## Task 1 - RandomStrategy
I overrode selectRobots() and shuffled the list of available robots and returned the first X robots in the list, where X is job.robotsNeeded. As long as there were enough available robots to do the job.
My plan was to use Collections.shuffle to make it random and that's what i did.

## Task 2 - ClosestStrategy
Similar to RandomStrategy except i sort the robots using ClosestComparator(job) that sorts by how close the robots are to the job. 
This was my plan and i knew i had to make a custom comparator.

## Task 3 - BetterStrategy
I use the same code in selectRobots() as in ClosestStrategy, but i sort jobs based on how long they take to finish and do the fastest first. I also implemented moveFreeRobots that move free robots to the closest finished job that is not were they are standing.
My original plan for this was to do the jobs that required the least amount of robots first but after talking to other students i found that this was not optimal. My plans constantly changed as I failed to use some dataStructures or the new solution would just be worse than the original.
I tried implementing PriorityQueue for the backLog but had problems getting it to work so I gave up and just transfered it over to an arrayList and sorted the jobs there.

# Runtime Analysis
For each method of the different strategies give a runtime analysis in Big-O notation and a description of why it has this runtime.

**If you have implemented new methods not listed you must add these as well.**
## Task 1 - RandomStrategy
### IStrategy
* ``registerRobots(List<Robot> robots)``: O(n)
	- Filling up an arrayList with robots is O(n)
* ``registerNewJob(Job job)``: O(m*k*(log m) + mkn + m^2)
	- backLog.add is O(1)
	- doJobs() is O(m*k*(log m) + mkn + m^2)
* ``registerJobAsFulfilled(Job job)``: O(m*k*(log m) + mkn + m^2)
	- available.addAll(robots) is O(n)
	- doJobs() is O(m*k*(log m) + mkn + m^2)

### AbstractStrategy (if you use it)
* ``doJobs()``: O(m*k*(log m) + mkn + m^2)
	- The while loop loops m times and the code inside is O(k*(log m) + kn + m) --> O(m*k*(log m) + mkn + m^2)
		- selectRobots from RandomStrategy is  O(n+k)
		- Worst case for the if else statement is assignRobots: O(k*(log m) + kn) + removeJob(job): O(m) --> O(k*(log m) + kn + m)
	- moveFreeRobots is not implemented so the rest is O(1)
* ``selectJob()``: O(1)
	- backLog.peek() is O(1)
* ``removeJob(Job job)``: O(m)
	- backLog.poll() is O(1)
	- backLog.remove(job) is O(m) (worst case)
* ``assignRobots(List<Robot> selected, Job job)``: O(k*(log m) + kn) 
	- selected.isEmpty() and selected.size() is O(1) so everything before the for loop is O(1)
	- for(Robot r : selected) loops k times and the code inside is O(1) since the length of the string is constant --> O(k)
	- The if else statement is O(k*(log m) + kn)) because:
		- The for loop in both the if and else statement loops k times and the code inside that is:
			- If: robot.move(job) is O(log m) and available.remove(robot) is O(n) --> O((log m) + n) (worst case)
			- Else: r.move(job.location) is O(log m)
		
* ``getAvailableRobots()``: O(1)
	- It just returns the list stored in the variable

### RandomStrategy
* ``selectRobots(Job job, List<Robot> available)``: O(n+k)
	- Shuffling "available" takes O(n)
	- The for loop loops k times and selectedRobots.add is O(1) --> k

## Task 2 - ClosestStrategy
### IStrategy
* ``registerRobots(List<Robot> robots)``: O(n)
	Same as in Task 1
* ``registerNewJob(Job job)``: O(m*k*(log m) + m*n*(log n) + mkn + m^2)
	- backLog.add is O(1)
	- doJobs() is O(m*k*(log m) + m*n*(log n) + mkn + m^2)
* ``registerJobAsFulfilled(Job job)``: O(m*k*(log m) + m*n*(log n) + mkn + m^2)
	- available.addAll(robots) is O(n)
	- doJobs() is O(m*k*(log m) + m*n*(log n) + mkn + m^2)

### AbstractStrategy (if you use it)
* ``doJobs()``: O(m*k*(log m) + m*n*(log n) + mkn + m^2)
	- The while loop loops m times and the code inside is O(k*(log m) + n*(log n) + kn + m) --> O(m*k*(log m) + m*n*(log n) + mkn + m^2)
		- selectRobots from ClosestStrategy is  O(n*(log n) + k)
		- Worst case for is else statement is assignRobots: O(k*(log m) + kn) + removeJob(job): O(m) --> O(k*(log m) + kn + m)
	- moveFreeRobots is not implemented so the rest is O(1)
* ``selectJob()``: O(1)
	Same as in Task 1
* ``removeJob(Job job)``: O(m)
	Same as in Task 1
* ``assignRobots(List<Robot> selected, Job job)``: O(k*(log m) + kn)
	Same as in Task 1
* ``getAvailableRobots()``: O(1)
	Same as in Task 1

### ClosestStrategy
* ``selectRobots(Job job, List<Robot> available)``: O(n*(log n) + k)
	- Collections.sort(available) is O(n*(log n))
	- The for loop loops k times and selectedRobots.add is O(1) --> k

## Task 3 - BetterStrategy
### IStrategy
* ``registerJobAsFulfilled(Job job)``: O((m^2)n(log n) + (m^2)k + n(m^2)(log m) + mkn)
	- available.addAll(robots) is O(n)
	- doJob() is O((m^2)n(log n) + (m^2)k + n(m^2)(log m) + mkn)

### AbstractStrategy (if you use it)
* ``removeJob(Job job)``: O(m)
	Same as in Task 1
* ``assignRobots(List<Robot> selected, Job job)``: O(k*(log m) + kn)
	Same as in Task 1
* ``getAvailableRobots()``: O(1)
	Same as in Task 1

### BetterStrategy
* ``selectRobots(Job job, List<Robot> available)``: O(n*(log n) + k)
	- Collections.sort(available) is O(n*(log n))
	- The for loop loops k times and the code inside is O(1) since selectedRobots.add is O(1) --> k
* ``doJobs()``: O((m^2)n(log n) + (m^2)k + n(m^2)(log m) + mkn)
	- The while loop is O((m^2)n(log n) + (m^2)k + n(m^2)(log m) + mkn)
	  because it loops m times and the code inside is O(mn(log n) + mk + nm(log m) + kn)
		- selectJob is O(mn*(log n) + mk)
		- The next code will either break early or late, but we can't tell which one is bigger so we add them together
		 --> O(nm(log m) + n*(log n) + k*(log m) + kn)
			- The first if sentence is O(nm(log m)) (because that is the big O of moveFreeRobots) (early break)
			- The code after the if is O(n*(log n) + k*(log m) + kn + m)
				- selectRobots from BetterStrategy is O(n*(log n) + k)
				- The if sentence is the worst case and is O(k*(log m) + kn + m) because:
					- assignRobots(selected, job) is O(k*(log m) + kn)
					- removeJob(job) is O(m)
	- moveFreeRobots is O(nm(log m))
* ``selectJob()``: O(mn*(log n) + mk)
	- Since backLog.peek() is O(1), everything before the for loop is O(1)
	- The for loop is O(mn*(log n) + mk) because it loops m times and the code inside is O(n*(log n) + k)
		- selectRobots(job) is O(n*(log n) + k)
		- closestRobots.get(), closestRobots.size(), getLocation(), and dist(job.location) are all O(1) so all the if statement is O(1) (worst case)
* ``moveFreeRobots()``: O(nm(log m))
	- The first for loop is O(nm(log m)) because it loops n times and the code inside is O(m(log m))
		- Collections.sort(jobs) is O(m*(log m))
		- The second for loop is O(m*(log m)) because it loops m times and the code inside is O(log m)
			- robot.move(job) is O(log m)
* ``registerNewJob(Job job)``: O((m^2)n(log n) + (m^2)k + n(m^2)(log m) + mkn)
	- backLog.add is O(1)
	- doJobs() is O((m^2)n(log n) + (m^2)k + n(m^2)(log m) + mkn)
* ``registerRobots(List<Robot> robots)``: O(n)
	- Declaring an arrayList is O(1)
	- Filling up an arrayList with robots is O(n)
	

