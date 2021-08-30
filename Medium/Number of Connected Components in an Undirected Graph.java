/*
You have a graph of n nodes. You are given an integer n and an array edges.
 where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.

Return the number of connected components in the graph.

 

Example 1:
https://assets.leetcode.com/uploads/2021/03/14/conn1-graph.jpg

Input: n = 5, edges = [[0,1],[1,2],[3,4]]
Output: 2

Example 2:
https://assets.leetcode.com/uploads/2021/03/14/conn2-graph.jpg

Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
Output: 1
 

Constraints:

1 <= n <= 2000
1 <= edges.length <= 5000
edges[i].length == 2
0 <= ai <= bi < n
ai != bi
There are no repeated edges.
*/

// Time complexity: O(E⋅α(n))
// Space complexity: O(V)O(V).
class Solution {
    public int countComponents(int n, int[][] edges) {
        UnionFind ds = new UnionFind(n);
        
        for (int[] edge : edges) {
            ds.union(edge[0], edge[1]);
        }
        
        return ds.getCount();
    }
}


class UnionFind {
    int root[];
    int rank[];
    int count;

    public UnionFind(int size) {
        root = new int[size];
        rank = new int[size];
        count = size;
        for (int i = 0; i < size; i++) {
            root[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (x == root[x]) {
            return x;
        }
        return root[x] = find(root[x]);
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else {
                root[rootY] = rootX;
                rank[rootX] += 1;
            }
            count--;
        }
    };

    int getCount() {
        return count;
    }
}

// Here E = Number of edges, V = Number of vertices.
// Time complexity: O(E + V).
// Space complexity: O(E+V).

class Solution {
    
     private void dfs(List<Integer>[] adjList, int[] visited, int startNode) {
        visited[startNode] = 1;
         
        for (int i = 0; i < adjList[startNode].size(); i++) {
            if (visited[adjList[startNode].get(i)] == 0) {
                dfs(adjList, visited, adjList[startNode].get(i));
            }
        }
    }
    
    public int countComponents(int n, int[][] edges) {
        int components = 0;
        int[] visited = new int[n];
        
        List<Integer>[] adjList = new ArrayList[n]; 
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < edges.length; i++) {
            adjList[edges[i][0]].add(edges[i][1]);
            adjList[edges[i][1]].add(edges[i][0]);
        }
        
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                components++;
                dfs(adjList, visited, i);
            }
        }
        return components;
    }
}