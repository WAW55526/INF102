# Answer File - Semester 2
# Description of each Implementation
Briefly describe your implementation of the different methods. What was your idea and how did you execute it? If there were any problems and/or failed implementations please add a description.

## Task 1 - mst
I modified Martins lecture code (lecture 12) so that it collected the edges and not distance. I used a PriorityQueue to sort the edges by weight (the WeightedGraph was the comparator), and the while loop only iterates until i have all the edges and not until toSearch is empty (when i reach a leaf it will check the edge it came from which will fail, i know how that the number of edges is nodes-1 so it is a waste of time to continue after that).

## Task 2 - lca
I modified Martins lecture code (lecture 12) using DFS to map every node in the tree to it's parent in a HashMap. After this u and v is moved to it's parent one step at a time (stopping at root), adding all nodes visited to the HashSet "paths". When either u or v moves to a node that is in the HashSet, i have found the crossing of the paths (the lca).

## Task 3 - addRedundant
I use findSubRoots to find the start-nodes of all subtrees of root. I loop trough them and use getSubtree(g, node, root).size() to find the two largest subtrees. I then use getLeaf on both subtrees, which finds the biggest subtree, and the biggest subtree in that subtree etc. until i am at a leaf node. Those two leaf nodes combine to the redundantEdge that is returned.


# Runtime Analysis
For each method of the different strategies give a runtime analysis in Big-O notation and a description of why it has this runtime.

NOTE: I will not focus a lot on things that are O(1) unless i have to, so if there is something important I did'nt mention, I came to the conclusion that it's O(1). There will also be comments in the code about bigO.

**If you have implemented any helper methods you must add these as well.**

(SEE CODE COMMENTS FOR CALCULATION)
* ``mst(WeightedGraph<T, E> g)``: O(m * log m)
	- Update is O(degree * log m)
	- While loop iterates m but only passes the if n times, so the entire loop is O(m * log m):
		- PriorityQueue.remove() is O(log m) happening m times --> O(m * log m)
		- Update is O(degree * log m) happening n times --> O(m * log m)
* ``update(WeightedGraph<T, E> g, HashSet<T> found, PriorityQueue<Edge<T>> toSearch, T newNode)``: O(degree * log m)
	- For loop iterates over the neighbours O(degree) and adds an edge to the PriorityQueue O(log m)
* ``getFoundNode(Edge<T> e, HashSet<T> found)``: O(1)
	- HashSet.contains is O(1)
* ``other(V v)``: O(1)
	- Just checking stored variables
* ``adjacentEdges(V v)``: O(degree)
	- Number of neighbours a node has is it's degree
	
(SEE CODE COMMENTS FOR CALCULATION)	
* ``lca(Graph<T> g, T root, T u, T v)``: O(m)
	- update is O(degree)
	- While loop iterates m but only passes the if n times, so the entire loop is O(m):
		- Before if is O(1) happening m times --> O(m)
		- Update is O(degree) happening n times --> = O(m)
	- While loop loops m times and code inside is O(1) --> O(m)
* ``update(Graph<T> g, HashSet<T> found, LinkedList<Edge<T>> toSearch, T newNode)``: O(degree)
	- For loop iterates over the neighbours O(degree) and adds an edge to the LinkedList O(1)
	
(SEE CODE COMMENTS FOR CALCULATION)
* ``addRedundant(Graph<T> g, T root)``: O(m^2)
	- findSubRoots is O(degree)
	- For loops degree times and the code inside is m --> O(degree * m)
		- getSubtree is O(m)
	- getLeaf is O(m^2)
* ``getLeaf(Graph<T> g, T root, T parent)``: O(m^2)
	- While loop loops m times and code inside is O(m) --> O(m^2)
		- For loop loops degree times and code inside is O(m) (SEE COMMENTS IN CODE) (in worst case degree is 1)
			- getSubtree is O(m)
* ``getSubtree(Graph<T> g, T node, T parent)``: O(m)
	- Update is O(degree)
	- While loop iterates m but only passes the if n times, so the entire loop is O(m):
		- Before if is O(1) happening m times --> O(m)
		- Update is O(degree) happening n times --> = O(m)
* ``findSubRoots(Graph<T> g, T root, T parent)``: O(degree)
	- For loop loops degree times and code inside is O(1) --> O(degree)
	- While loop loops degree times and code inside is O(1) --> O(degree)
