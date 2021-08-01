/*
Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.

The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.  Each node has labels in the set {0, 1, ..., edges.length}.

 

Example 1:

https://assets.leetcode.com/uploads/2019/06/14/1397_example_1.PNG

Input: edges = [[0,1],[0,2]]
Output: 2
Explanation: 
A longest path of the tree is the path 1 - 0 - 2.

Example 2:
https://assets.leetcode.com/uploads/2019/06/14/1397_example_2.PNG


Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
Output: 4
Explanation: 
A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.
 

Constraints:

0 <= edges.length < 10^4
edges[i][0] != edges[i][1]
0 <= edges[i][j] <= edges.length
The given edges form an undirected tree.
*/


class Solution {
    public int treeDiameter(int[][] edges) {
        List<Set<Integer>> graph = new ArrayList<>();
        for (int itr = 0; itr <= edges.length; ++itr) {
            graph.add(new HashSet<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
    
        int[] nodeDistance = bfs(0, graph);        
        nodeDistance = bfs(nodeDistance[0], graph);
        return nodeDistance[1];
    }
    
    private int[] bfs(int start, List<Set<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        visited[start] = true;
        
        Queue<Integer> Q = new LinkedList<>();
        Q.add(start);
        int lastNode = start, distance = -1;
        
        while (Q.size() > 0) {
            Queue<Integer> nextLevel = new LinkedList<>();
            while (Q.size() > 0) {
                int node = Q.poll();
                for (Integer nbr : graph.get(node)) {
                    if (!visited[nbr]) {
                        visited[nbr] = true;
                        nextLevel.add(nbr);
                        lastNode = nbr;
                    }
                }
            }
            
            ++distance;
            Q = nextLevel;
        }
        
        return new int[] {lastNode, distance};
    }
}

class Solution {

    public int treeDiameter(int[][] edges) {

        // build the adjacency list representation of the graph.
        List<Set<Integer>> graph = new ArrayList<Set<Integer>>();
        for (int i = 0; i < edges.length + 1; ++i) {
            graph.add(new HashSet<Integer>());
        }
        for (int[] edge : edges) {
            Integer u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // find the outer most nodes, i.e. leaf nodes
        LinkedList<Integer> leaves = new LinkedList<Integer>();
        for (int vertex = 0; vertex < graph.size(); ++vertex) {
            if (graph.get(vertex).size() == 1)
                leaves.add(vertex);
        }

        // "peel" the graph layer by layer,
        // until we have the centroids left.
        int layers = 0;
        int vertexLeft = edges.length + 1;
        while (vertexLeft > 2) {
            vertexLeft -= leaves.size();

            LinkedList<Integer> nextLeaves = new LinkedList<Integer>();

            for (int leaf : leaves) {
                // the only neighbor left on the leaf node.
                int neighbor = graph.get(leaf).iterator().next();
                graph.get(neighbor).remove(leaf);
                if (graph.get(neighbor).size() == 1)
                    nextLeaves.add(neighbor);
            }

            layers += 1;
            leaves = nextLeaves;
        }

        if (vertexLeft == 1)
            return layers * 2;
        else
            return layers * 2 + 1;
    }
}

class Solution {
    private List<List<Integer>> graph;
    private Integer diameter = 0;

    public int treeDiameter(int[][] edges) {

        // build the adjacency list representation of the graph.
        this.graph = new ArrayList<List<Integer>>();
        boolean[] visited = new boolean[edges.length + 1];
        for (int i = 0; i < edges.length + 1; ++i) {
            this.graph.add(new ArrayList<Integer>());
            visited[i] = false;
        }
        for (int[] edge : edges) {
            Integer u = edge[0], v = edge[1];
            this.graph.get(u).add(v);
            this.graph.get(v).add(u);
        }

        dfs(0, visited);

        return this.diameter;
    }

    /**
     * return the max distance
     *   starting from the 'curr' node to its the leaf nodes
     */
    private int dfs(int curr, boolean[] visited) {
        // the top 2 distance starting from this node
        Integer topDistance1 = 0, topDistance2 = 0;

        visited[curr] = true;
        for (Integer neighbor : graph.get(curr)) {
            int distance = 0;
            if (!visited[neighbor])
                distance = 1 + this.dfs(neighbor, visited);

            if (distance > topDistance1) {
                topDistance2 = topDistance1;
                topDistance1 = distance;
            } else if (distance > topDistance2) {
                topDistance2 = distance;
            }
        }

        // with the top 2 distance, we can update the current diameter
        this.diameter = Math.max(this.diameter, topDistance1 + topDistance2);

        return topDistance1;
    }
}