# Find Triplicate
Given a list of elements find an element that occurs (at least) three times.

Code currently includes only one algorithm for doing this:
* **TriplicateBruteForce**
  * Simple solution: Triple for-loop (brute force)
  * Time Complexity: O(n<sup>3</sup>)

Write a better/faster algorithm.

## Task
1. Implement the ``ITriplicate`` interface. 
```java
public class MyFantasticTriplicateFinder implements ITriplicate {
 ...
}
```
 Write an algorithm which performs better than ``TriplicateBruteForce``.
 
2. Run ``TriplicateTest``. Your algorithm is correct if the tests pass.

3. Add your algorithm/class to the list of algorithms/classes in ``Main::main`` (line 40). Run ``Main`` to see how fast (or slow) your solution is compared to          ``TriplicateBruteForce``. 

4. What time complexity does your algorithm have? Increase ``N_INTEGERS`` to see what it does to the running time of the different algorithms.
   Can you improve your time/write a better algorithm?


## Expected output
```
---Generating Integer Lists---
10 lists generated with 10 000 elements each.

---Processing Algorithms---
TriplicateBruteForce        | time elapsed: 2826194217 microseconds (2826,194217 seconds)
MyFantasticTriplicateFinder | time elapsed: ?????????? microseconds (??????????? seconds)
```
