/*
A tree is an undirected graph in which any two vertices are connected by exactly one path. 
In other words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges 
where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, 
you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h.
Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf

Example 1:
https://assets.leetcode.com/uploads/2020/09/01/e1.jpg

Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
Example 2:
https://assets.leetcode.com/uploads/2020/09/01/e2.jpg

Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]
Example 3:

Input: n = 1, edges = []
Output: [0]
Example 4:

Input: n = 2, edges = [[0,1]]
Output: [0,1]
 

Constraints:

1 <= n <= 2 * 10^4
edges.length == n - 1
0 <= ai, bi < n
ai != bi
All the pairs (ai, bi) are distinct.
The given input is guaranteed to be a tree and there will be no repeated edges.
*/

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        // base cases
        if (n < 2) {
            ArrayList<Integer> centroids = new ArrayList<>();
            for (int i = 0; i < n; i++)
                centroids.add(i);
            return centroids;
        }

        // Build the graph with the adjacency list
        ArrayList<Set<Integer>> neighbors = new ArrayList<>();
        for (int i = 0; i < n; i++)
            neighbors.add(new HashSet<Integer>());

        for (int[] edge : edges) {
            Integer start = edge[0], end = edge[1];
            neighbors.get(start).add(end);
            neighbors.get(end).add(start);
        }

        // Initialize the first layer of leaves
        ArrayList<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (neighbors.get(i).size() == 1)
                leaves.add(i);

        // Trim the leaves until reaching the centroids
        int remainingNodes = n;
        while (remainingNodes > 2) {
            remainingNodes -= leaves.size();
            ArrayList<Integer> newLeaves = new ArrayList<>();

            // remove the current leaves along with the edges
            for (Integer leaf : leaves) {
                // the only neighbor left for the leaf node
                Integer neighbor = neighbors.get(leaf).iterator().next();
                // remove the edge along with the leaf node
                neighbors.get(neighbor).remove(leaf);
                if (neighbors.get(neighbor).size() == 1)
                    newLeaves.add(neighbor);
            }

            // prepare for the next round
            leaves = newLeaves;
        }

        // The remaining nodes are the centroids of the graph
        return leaves;
    }
}

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        
        /*
            0 1 
            1 0 2 3
            2 1
            3 1
        */
        
        List<Integer> ans = new ArrayList<>();
        if (n <= 0) return ans;
        
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        
        List<Integer>[] neighbors = new ArrayList[n];
        int[] degrees = new int[n];
        
        for(int i = 0; i < neighbors.length; i++){
            neighbors[i] = new ArrayList<>();
        }
        
        for(int[] pair : edges){
            
            degrees[pair[0]]++;
            degrees[pair[1]]++;
            neighbors[pair[0]].add(pair[1]);
            neighbors[pair[1]].add(pair[0]);
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        for(int i = 0; i < degrees.length; i++){
            if(degrees[i] == 1)
                q.add(i);
        }
        
        while(n > 2){
            
            int size = q.size();
            n -= size;
            
            for(int i = 0; i < size; i++){
                int curr = q.poll();
                
                for(int neighbor : neighbors[curr]){
                    if(--degrees[neighbor] == 1){
                        q.add(neighbor);
                    }
                }
            }
            
           
        }
        
        ans.addAll(q);
        return ans;
    }
}