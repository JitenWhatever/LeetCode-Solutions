/*
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, 
find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i 
(i.e., there is a directed edge from node i to node graph[i][j]).

Example 1:
Input: [[1,2], [3], [3], []] 
Output: [[0,1,3],[0,2,3]] 
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

Example 2:

Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
Example 3:

Input: graph = [[1],[]]
Output: [[0,1]]
Example 4:

Input: graph = [[1,2,3],[2],[3],[]]
Output: [[0,1,2,3],[0,2,3],[0,3]]
Example 5:

Input: graph = [[1,3],[2],[3],[]]
Output: [[0,1,2,3],[0,3]]
 

Constraints:

n == graph.length
2 <= n <= 15
0 <= graph[i][j] < n
graph[i][j] != i (i.e., there will be no self-loops).
All the elements of graph[i] are unique.
The input graph is guaranteed to be a DAG.
*/

class Solution {
    
    private List<List<Integer>> paths;
    private List<Integer> path;
    
     
    private void dfs(int node, int dst, int[][] graph) {
        path.add(node);
        
        if(node == dst) {
            // System.out.println(path);
            paths.add(new ArrayList(path));
        }
        
        for(int nbr : graph[node]) {
            dfs(nbr, dst, graph);
        }
        
        path.remove(path.size() - 1);
    }
    
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        path = new ArrayList<>();
        paths = new ArrayList<>();
        
        dfs(0, graph.length - 1, graph);
        
        return paths;
    }  
}

class Solution {
    private int target;
    private int[][] graph;
    private HashMap<Integer, List<List<Integer>>> memo;

    protected List<List<Integer>> allPathsToTarget(int currNode) {
        // memoization. check the result in the cache first
        if (memo.containsKey(currNode))
            return memo.get(currNode);

        List<List<Integer>> results = new ArrayList<>();
        // base case
        if (currNode == this.target) {
            ArrayList<Integer> path = new ArrayList<>();
            path.add(target);
            results.add(path);
            return results;
        }

        // iterate through the paths starting from each neighbor.
        for (int nextNode : this.graph[currNode]) {
            for (List<Integer> path : allPathsToTarget(nextNode)) {
                ArrayList<Integer> newPath = new ArrayList<>();
                newPath.add(currNode);
                newPath.addAll(path);
                results.add(newPath);
            }
        }
        memo.put(currNode, results);
        return results;
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        this.target = graph.length - 1;
        this.graph = graph;
        this.memo = new HashMap<>();

        return this.allPathsToTarget(0);
    }
}