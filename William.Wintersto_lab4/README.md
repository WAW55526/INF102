# Grandpa Bernie
This exercise is taken from Kattis: https://open.kattis.com/problems/grandpabernie

Over the years, Grandpa Bernie has traveled all over the world. He doesn’t travel that much anymore, but he loves to tell his grandchildren stories from all these trips. He’ll tell them the story from when he went to Israel for the first time, or when he went to Greece for the third time.

His memory works in a funny way. He can easily remember his k'th trip to a particular country, but he’ll have a hard time remembering in which year he went on that trip. Given a list of all the trips Grandpa Bernie went on, can you answer a number of queries asking in which year he went on his k'th trip to a particular country?

## Task
You are to implement ``TripInformation::solve``. The field variable ``queries`` is a set of queries: (country, trip number). Given a country the method needs to figure out which year the k'th trip to the country took place. Return a list of years for all queries in ``queries``.

While implementing the method you can run ``Main::main`` to see some output.

To test if your implementation is correct run ``TripInformationTest``.

### Input
The input consists of:

1. one line with one integer n (1≤n≤105), the number of trips Grandpa Bernie went on. This is stored in ``nTrips``.

2. n lines each containing the name s (1≤|s|≤20) of a country and an integer y (1≤y≤106) representing a trip to country s that Grandpa Bernie went on in year y. This is stored in ``countryYearPairs``.

3. one line with one integer q (1≤q≤105), the number of queries. This is stored in ``nQueries``. 

4. q lines each containing the name s of a country and an integer k representing a query for the k'th time Grandpa Bernie went to country s. This is stored in ``queries``.

Each country name only consists of letters from the English alphabet. It is also guaranteed that, for each query asking for the k'th trip to country s, k is at least 1 and no greater than the number of times Grandpa Bernie went to country s. In particular, it is guaranteed that Grandpa Bernie has visited country s at least once.

### Output
For each query for the k'th trip Grandpa Bernie went to a country s, output a single line containing the year in which Grandpa Bernie went on that trip.

## Examples
Given an input file your solve-method must return a list equal to the the list of years in the following output.
### Example 1
Input:
```
4
Iceland 2016
Sweden 2015
Iceland 1982
Norway 1999
3
Sweden 1
Iceland 1
Iceland 2
```
Output:
```
2015
1982
2016
```

### Example 2
Input:
```
4
Iceland 2014
Iceland 2015
Iceland 2015
Iceland 2016
4
Iceland 4
Iceland 3
Iceland 2
Iceland 1
```
Output:
```
2016
2015
2015
2014
```
