/*
There is an undirected star graph consisting of n nodes labeled from 1 to n. 
A star graph is a graph where there is one center node and exactly n - 1 edges 
that connect the center node with every other node.

You are given a 2D integer array edges where each edges[i] = [ui, vi] 
indicates that there is an edge between the nodes ui and vi. 
Return the center of the given star graph.

Example 1:
https://assets.leetcode.com/uploads/2021/02/24/star_graph.png
Input: edges = [[1,2],[2,3],[4,2]]
Output: 2
Explanation: As shown in the figure above, node 2 is connected to every other node, so 2 is the center.
Example 2:

Input: edges = [[1,2],[5,1],[1,3],[1,4]]
Output: 1
 

Constraints:

3 <= n <= 10^5
edges.length == n - 1
edges[i].length == 2
1 <= ui, vi <= n
ui != vi
The given edges represent a valid star graph.
*/

class Solution {
    public int findCenter(int[][] edges) {
        int n = edges.length;
        int[] degree = new int[n + 1];
        
        for (int[] edge : edges) {
            ++degree[--edge[0]];
            ++degree[--edge[1]];
        }
        
        
        for (int node = 0; node <= n; ++node) {
            if (degree[node] == n) {
                return node + 1;
            }
        }
        
        return -1;
        
    }

    public int findCenter(int[][] edges) {
        // if first node of edge-0 occurs in edge-1 as well, it's the center one
        if(edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1])
            return edges[0][0];
        // otherwise, the second node of edge-0 will be center node for sure
        return edges[0][1];
    }
}