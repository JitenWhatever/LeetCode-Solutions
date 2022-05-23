import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections 
forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. 
Any server can reach other servers directly or indirectly through the network.

A critical connection is a connection that, if removed, 
will make some servers unable to reach some other server.

Return all critical connections in the network in any order.

Example 1:
https://assets.leetcode.com/uploads/2019/09/03/1537_ex1_2.png

Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.

Example 2:

Input: n = 2, connections = [[0,1]]
Output: [[0,1]]


Constraints:

    2 <= n <= 10^5
    n - 1 <= connections.length <= 10^5
    0 <= ai, bi <= n - 1
    ai != bi
    There are no repeated connections.
*/

public class CriticalConnectionsInANetwork {

    private Map<Integer, List<Integer>> graph;
    private Map<Integer, Integer> rank;
    private Map<Pair<Integer, Integer>, Boolean> connDict;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        
        this.formGraph(n, connections);
        this.dfs(0, 0);
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (Pair<Integer, Integer> criticalConnection : this.connDict.keySet()) {
            result.add(new ArrayList<Integer>(Arrays.asList(criticalConnection.getKey(), criticalConnection.getValue())));
        }
        
        return result;
    }
    
    private int dfs(int node, int discoveryRank) {
        
        // That means this node is already visited. We simply return the rank.
        if (this.rank.get(node) != null) {
            return this.rank.get(node);
        }
        
        // Update the rank of this node.
        this.rank.put(node, discoveryRank);
        
        // This is the max we have seen till now. So we start with this instead of INT_MAX or something.
        int minRank = discoveryRank + 1;
        
        for (Integer neighbor : this.graph.get(node)) {
            
            // Skip the parent.
            Integer neighRank = this.rank.get(neighbor);
            if (neighRank != null && neighRank == discoveryRank - 1) {
                continue;
            }
            
            // Recurse on the neighbor.
            int recursiveRank = this.dfs(neighbor, discoveryRank + 1);
            
            // Step 1, check if this edge needs to be discarded.
            if (recursiveRank <= discoveryRank) {
                int sortedU = Math.min(node, neighbor), sortedV = Math.max(node, neighbor);
                this.connDict.remove(new Pair<Integer, Integer>(sortedU, sortedV));
            }
            
            // Step 2, update the minRank if needed.
            minRank = Math.min(minRank, recursiveRank);
        }
        
        return minRank;
    }
    
    private void formGraph(int n, List<List<Integer>> connections) {
        
        this.graph = new HashMap<Integer, List<Integer>>();
        this.rank = new HashMap<Integer, Integer>();
        this.connDict = new HashMap<Pair<Integer, Integer>, Boolean>();
        
        // Default rank for unvisited nodes is "null"
        for (int i = 0; i < n; i++) {
            this.graph.put(i, new ArrayList<Integer>());
            this.rank.put(i, null);
        }
        
        for (List<Integer> edge : connections) {
            
            // Bidirectional edges
            int u = edge.get(0), v = edge.get(1);
            this.graph.get(u).add(v);
            this.graph.get(v).add(u);
            
            int sortedU = Math.min(u, v), sortedV = Math.max(u, v);
            connDict.put(new Pair<Integer, Integer>(sortedU, sortedV), true);
        }
    }

    class Pair<T, V> {
        private T key;
        private V value;
        public Pair(T key, V value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }
    }
}