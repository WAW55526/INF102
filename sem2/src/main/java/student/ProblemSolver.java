package student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

import graph.*;

public class ProblemSolver implements IProblem {
	
	/*
	 *  NOTE:
	 *  It may look like i have repeated a lot of code from Martin's lecture,
	 *  but in all the places there is a small change to the while loop which made it impossible to make it it's own method.
	 *  All instances of BFS/DFS is a unique version of it.
	 */
	
	/*
	 * The majority of this code is taken from Martin Vatshelle's implementation of Breadth-first search (Lecture 12)
	 */
	@Override
	public <T, E extends Comparable<E>> ArrayList<Edge<T>> mst(WeightedGraph<T, E> g) { // O(m * log m)          (m > degree)
		ArrayList<Edge<T>> allEdges = new ArrayList<Edge<T>>();
		
		PriorityQueue<Edge<T>> toSearch = new PriorityQueue<Edge<T>>(g);
		
		HashSet<T> found = new HashSet<T>();
		
		T firstNode = g.vertices().iterator().next(); // O(1)
		update(g, found, toSearch, firstNode); // O(degree * log m)
	
		while(allEdges.size() != g.numVertices()-1) { // m iterations, before if is O(m * log m), after is O(n * degree) = O(m) --> O(m * log m + m) --> O(m * log m)
			Edge<T> e = toSearch.remove(); //O(log m)
			T foundNode = getFoundNode(e, found); // O(1)
			T newNode = e.other(foundNode); // O(1)
			if (found.contains(newNode)) { // passes the if n times
				continue;
			}
			
			allEdges.add(e);
			
			update(g, found, toSearch, newNode); // O(degree * log m) happening n times is O(m * log m)
		}
		
		return allEdges; 
	}
	
	private static <T, E extends Comparable<E>> void update(WeightedGraph<T, E> g, HashSet<T> found, PriorityQueue<Edge<T>> toSearch, T newNode) { // O(degree * log m)
		found.add(newNode);
		for(Edge<T> edge : g.adjacentEdges(newNode)) { // O(degree) * O(log m)
			if(found.contains(edge.a) && found.contains(edge.b)) {
				continue;
			}
			toSearch.add(edge); // O(log m)
		}
	}
	
	/*
	 * This code is taken from Martin Vatshelle's implementation of Breadth-first search (Lecture 12)
	 */
	private static <T> T getFoundNode(Edge<T> e, HashSet<T> found) {// O(1)
		if(found.contains(e.a))
			return e.a;
		if(found.contains(e.b))
			return e.b;
		
		throw new IllegalArgumentException("e should allways have one endpoint in found");
	}

	
	
	
	
	
	
	
	
	
	/*
	 * The majority of this code is taken from Martin Vatshelle's implementation of Breadth-first search (Lecture 12)
	 */
	@Override 
	public <T> T lca(Graph<T> g, T root, T u, T v) { // O(degree + 2m) --> O(m)
		if (u == root || v == root) {
			return root;
		}
		
		HashSet<T> paths = new HashSet<T>();
		paths.add(u);
		paths.add(v);
		
		HashSet<T> found = new HashSet<T>();
		
		LinkedList<Edge<T>> toSearch = new LinkedList<Edge<T>>();

		HashMap<T,T> childParent = new HashMap<>();
		childParent.put(root, root); // O(1)
		
		update(g, found, toSearch, root); //O(degree)
		
		while (!toSearch.isEmpty()) { // m iterations, before if is O(m * 1) --> , after is O(n * degree) = O(m) --> O(m + m) --> O(m)
			Edge<T> e = toSearch.removeLast();
			T foundNode = getFoundNode(e,found); // O(1)
			T newNode = e.other(foundNode); // O(1)
			if(found.contains(newNode)) { // passes the if n times
				continue;
			}
			
			childParent.put(newNode, foundNode);
			
			update(g, found, toSearch, newNode); // O(degree) happening n times is O(m)
		}
		
		T upath = u;
		T vpath = v;
		while (true) { // O(m)
			           // Worst case is that the tree is one long line and u is root and v is at the bottom
					   // v has to move across all edges (m iteration) to reach the top
			if (upath != root) {
				upath = childParent.get(upath); // O(1)
				if (paths.contains(upath)) { // O(1)
					return upath;
				}
				paths.add(upath); // O(1)
			}
			
			if (vpath != root) {
				vpath = childParent.get(vpath); // O(1)
				if (paths.contains(vpath)) { // O(1)
					return vpath;
				}
				paths.add(vpath); // O(1)
			}
		}
	}
	
	/*
	 * This code is taken from Martin Vatshelle's implementation of Breadth-first search (Lecture 12)
	 */
	private static <T> void update(Graph<T> g, HashSet<T> found, LinkedList<Edge<T>> toSearch, T newNode) { // O(degree)
		found.add(newNode);
		for(Edge<T> edge : g.adjacentEdges(newNode)) { // O(degree)
			if(found.contains(edge.a) && found.contains(edge.b)) {
				continue;
			}
			toSearch.addLast(edge); // O(1)
		}
	}



	

	
	
	
	
	
	@Override
	public <T> Edge<T> addRedundant(Graph<T> g, T root) { // O(degree + degree * m + m^2) --> O(m^2)
		LinkedList<T> subRoots = findSubRoots(g, root, root); // O(degree)
		
		T largest_1 = null;
		T largest_2 = null;
		int largest_1_size = 0;
		int largest_2_size = 0;
		for (T node : subRoots) { // O(degree * m)
			int treeSize = getSubtree(g, node, root).size(); // O(m)
			
			if (largest_1 == null || treeSize > largest_1_size) {
				largest_2 = largest_1;
				largest_2_size = largest_1_size;
				largest_1 = node;
				largest_1_size = treeSize;
			}
			else if (largest_2 == null || treeSize > largest_2_size) {
				largest_2 = node;
				largest_2_size = treeSize;
			}
		}
		
		Edge<T> redundant = new Edge<T>(getLeaf(g, largest_1, root), getLeaf(g, largest_2, root)); // O(m^2)
		
		return redundant;
	}
	
	/*
	 * Find the biggest subtree of root, then find the biggest subtree in that subtree etc. until you end on a leaf node
	 */
	public <T> T getLeaf(Graph<T> g, T root, T parent) { // O(m^2)
		if (root == null) { // size of subtree is 0
			return parent;
		}
		
		T currentParent = parent;
		T currentRoot = root;
		while (true) { // O(m * degree * m) --> O(m^2)
					   // in worst case the tree is one long line, but in that case degree is 1 (getSubRoots ignore edge between root and parent)
			LinkedList<T> subRoots = findSubRoots(g, currentRoot, currentParent); // O(degree)
			
			T largest = null;
			int largest_size = 0;
			for (T node : subRoots) { // O(degree * m)
				int treeSize = getSubtree(g, node, currentRoot).size(); // O(m)
				
				if (largest == null || treeSize > largest_size) {
					largest = node;
					largest_size = treeSize;
				}
			}
			
			if (largest == null) { // reached the leaf, no subnodes
				break;
			}
			
			currentParent = currentRoot;
			currentRoot = largest;
		}
		
		return currentRoot;
	}
	
	/*
	 * The majority of this code is taken from Martin Vatshelle's implementation of Breadth-first search (Lecture 12)
	 * Returns tree that has node as it's root
	 */
	public <T> HashSet<T> getSubtree(Graph<T> g, T node, T parent) { // O(degree + m) --> O(m)
		HashSet<T> subtree = new HashSet<T>();
		
		HashSet<T> found = new HashSet<T>();
		found.add(parent);
		
		LinkedList<Edge<T>> toSearch = new LinkedList<Edge<T>>();

		update(g, found, toSearch, node); //O(degree)
		
		while(!toSearch.isEmpty()) { // m iterations, before if is O(m * 1) --> , after is O(n * degree) = O(m) --> O(m + m) --> O(m)
			Edge<T> e = toSearch.removeLast();
			T foundNode = getFoundNode(e,found); // O(1)
			T newNode = e.other(foundNode); // O(1)
			if(found.contains(newNode)) { // passes the if n times
				continue;
			}
			
			subtree.add(newNode);
			
			update(g, found, toSearch, newNode); // O(degree) happening n times is O(m)		
			
		}
		
		return subtree;
	}
	
	/*
	 * The majority of this code is taken from Martin Vatshelle's implementation of Breadth-first search (Lecture 12)
	 * Finds the top of all the subtrees of root
	 */
	public <T> LinkedList<T> findSubRoots(Graph<T> g, T root, T parent) { // O(degree + degree) --> O(degree)
		LinkedList<T> subRoots = new LinkedList<T>();
		
		HashSet<T> found = new HashSet<T>();
		found.add(parent);
		found.add(root);
		
		LinkedList<Edge<T>> toSearch = new LinkedList<Edge<T>>();
		for(Edge<T> edge : g.adjacentEdges(root)) { // O(degree)
			if(found.contains(edge.a) && found.contains(edge.b)) {
				continue;
			}
			toSearch.addLast(edge); // O(1)
		}
		
		while(!toSearch.isEmpty()) { // O(degree)
			Edge<T> e = toSearch.removeFirst();
			subRoots.add(e.other(root)); // O(1)
		}
		
		return subRoots;
	}
	
}
