/*
There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). 
The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. 
Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex start to vertex end.

Given edges and the integers n, start, and end, return true if there is a valid path from start to end, or false otherwise.

 

Example 1:

    0---------1
     \       /
      \     /
       \   /
        \ /
         2

Input: n = 3, edges = [[0,1],[1,2],[2,0]], start = 0, end = 2
Output: true
Explanation: There are two paths from vertex 0 to vertex 2:
- 0 → 1 → 2
- 0 → 2
Example 2:
           1           3
          /            | \
         /             |  \
        0              |   5
         \             |  /
          \            | /
           2           4 
Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], start = 0, end = 5
Output: false
Explanation: There is no path from vertex 0 to vertex 5.
 

Constraints:

1 <= n <= 2 * 105
0 <= edges.length <= 2 * 105
edges[i].length == 2
1 <= ui, vi <= n - 1
ui != vi
1 <= start, end <= n - 1
There are no duplicate edges.
There are no self edges.
*/

// DFS
class Solution {
    public boolean validPath(int n, int[][] edges, int start, int end) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], key -> new ArrayList<>()).add(edge[0]);
        }
        
        boolean[] visited = new boolean[n];
        visited[start] = true;
        
        Stack<Integer> stck = new Stack<>(); // use Queue for BFS
        stck.push(start);
        
        while (!stck.isEmpty()) {
            int node = stck.pop();
            if (node == end) {
                return true;
            }
            
            for (int nbr : graph.get(node)) {
                if (!visited[nbr]) {
                    visited[nbr] = true;
                    stck.push(nbr);
                }
            }
        }
        return false;
    }
}



// Union and Find
class Solution {
    public boolean validPath(int n, int[][] edges, int start, int end) {
        UnionFind disJointSet = new UnionFind(n);
        
        for (int[] edge : edges) {
            disJointSet.union(edge[0], edge[1]);
        }
        
        return disJointSet.isConnected(start, end);
    }
}

class UnionFind {
    private int size;
    private int[] root;
    private int[] rank;
    public UnionFind(int size) {
        this.size = size;
        this.root = new int[this.size];
        this.rank = new int[this.size];
        for (int node = 0; node < size; ++node) {
            this.root[node] = node;
            this.rank[node] = 1;
        }
    }
    
    public int find(int x) {
        if (x == this.root[x]) {
            return x;
        }
        return this.root[x] = find(this.root[x]);
    }
    
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (this.rank[rootX] > this.rank[rootY]) {
                this.root[rootY] = rootX;
            } else if (this.rank[rootX] < this.rank[rootY]) {
                this.root[rootX] = rootY;
            } else {
                this.root[rootY] = rootX;
                this.rank[rootX] += 1;
            }
        }
    }
    
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}