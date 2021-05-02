# Project Logistics and Implementation

I have used Java for coding of this project. For the first part, I have created a collection of general graph functions.
The functions will apply to any graph, provided it is presented in the correct form. In the second part I will translate movie 
rating data from Netflix into graphs and analyze the graphs with the implemented functions.

I have implemented the following graph algorithms as operations :
1. connected_components() : uses depth-first search to return the list of connected components.
2. one_cycle(): uses depth-first search to return a cycle if there is one.
3. shortest_paths(): uses Dijkstra’s algorithm and returns a map of shortest paths. As this is an un-weighted and un-directed graph, we have used BFS Algorithm to return path length from source to destination.

I have used the following graph structures to generate simulated data and to test
them with the operations from above :
1.  n-cycle
2.  A complete graph on n vertices
3.  An empty graph on n vertices
4.  A heap
5.  Equivalence mod k
