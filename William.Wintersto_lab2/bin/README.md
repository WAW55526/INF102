# LinkedList VS ArrayList

ArrayList and LinkedList are two implementations of the List interface. 
Each have their own appealing features which we will explore in this task.

In this task LinkedList is singly linked.

## Task
In this task we have given you a simplified version of the List interface. The two classes ``ArrayList`` and ``LinkedList`` implements this interface. Both classes already have a decent amount of code, but is missing a couple methods.

For both **ArrayList** and **LinkedList** implement:
1. ``get(index)``
2. ``add(index, element)``

Run LinkedListTest.java and ArrayListTest.java to see if your implementation is correct.

Main.java performs a set of operations using both ArrayList and LinkedList:
  * Insertion
    - Insert an element at a random index
    - Insert an element at index 0
    - Insert an element at index size()-1
  * Access
    - Access an element at a random index
    - Access an element at index 0
    - Access an element at index size()-1

Each operation is repeated ``n`` times. Each operation is timed and compared for the two different datastructures.

Why do ArrayList and LinkedList perform differently at these operations? **Give a Big-O analysis**.

## Expected output
```
----100 000  Random Insertions----
  ArrayList      | time elapsed: ?? microseconds (?? seconds)
  LinkedList     | time elapsed: ?? microseconds (?? seconds)
  ?? BEST
  LinkedList spent ?? % of the time ArrayList did.

  ----100 000  Head Insertions----
  ArrayList      | time elapsed: ?? microseconds (?? seconds)
  LinkedList     | time elapsed: ?? microseconds (?? seconds)
  ?? BEST
  LinkedList spent ?? % of the time ArrayList did.

  ----100 000  Tail Insertions----
  ArrayList      | time elapsed: ?? microseconds (?? seconds)
  LinkedList     | time elapsed: ?? microseconds (?? seconds)
  ?? BEST
  LinkedList spent ?? % of the time ArrayList did.

  ----100 000  Random Access----
  ArrayList      | time elapsed: ?? microseconds (?? seconds)
  LinkedList     | time elapsed: ?? microseconds (?? seconds)
  ?? BEST
  LinkedList spent ?? % of the time ArrayList did.

  ----100 000  Head Access----
  ArrayList      | time elapsed: ?? microseconds (?? seconds)
  LinkedList     | time elapsed: ?? microseconds (?? seconds)
  ?? BEST
  LinkedList spent ?? % of the time ArrayList did.

  ----100 000  Tail Access----
  ArrayList      | time elapsed: ?? microseconds (?? seconds)
  LinkedList     | time elapsed: ?? microseconds (?? seconds)
  ?? BEST
  LinkedList spent ?? % of the time ArrayList did.
  ```
The question marks will be filled in with the timing results after implementation.
