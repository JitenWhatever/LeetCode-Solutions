/*
You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and 
a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.

Return true if the edges of the given graph make up a valid tree, and false otherwise.

 

Example 1:
https://assets.leetcode.com/uploads/2021/03/12/tree1-graph.jpg

Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
Output: true
Example 2:
https://assets.leetcode.com/uploads/2021/03/12/tree2-graph.jpg

Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
Output: false
 

Constraints:

1 <= 2000 <= n
0 <= edges.length <= 5000
edges[i].length == 2
0 <= ai, bi < n
ai != bi
There are no self-loops or repeated edges.

*/

// DFS 27ms
class Solution {
    public boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();
        
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], key -> new ArrayList<>()).add(edge[0]);
        }
        
        Stack<Integer> stck = new Stack<>();
        stck.push(0);
        parent.put(0, -1);
        
        while(!stck.isEmpty()) {
            int node = stck.pop();
            
            if(Objects.nonNull(graph.get(node))) {
                for (int nbr : graph.get(node)) {
                    if(parent.get(node) == nbr) {
                        continue;
                    }

                    if (parent.containsKey(nbr)) {
                        return false;
                    }

                    stck.push(nbr);
                    parent.put(nbr, node);
                }
            }
        }
        
        return parent.size() == n;
    }
}

class Solution {
    
    private List<List<Integer>> adjacencyList = new ArrayList<>();
    private Set<Integer> seen = new HashSet<>();
    
    
    public boolean validTree(int n, int[][] edges) {
        
        if (edges.length != n - 1) return false;
        
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        
        // We return true iff no cycles were detected,
        // AND the entire graph has been reached.
        return dfs(0, -1) && seen.size() == n;   
    }
    
    public boolean dfs(int node, int parent) {
        if (seen.contains(node)) return false;
        seen.add(node);
        for (int neighbour : adjacencyList.get(node)) {
            if (parent != neighbour) {
                boolean result = dfs(neighbour, node);
                if (!result) return false;
            }
        }
        return true;
    }
}

class Solution {
    public boolean validTree(int n, int[][] edges) {

        if (edges.length != n - 1) {
            return false;
        }
        
        UnionFind disJointSet = new UnionFind(n);
        
        for (int[] edge : edges) {
            if(!disJointSet.union(edge[0], edge[1])) {
                return false;
            }
        }
        
        return true;
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
    
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        boolean mergeRequired = false;
        if (rootX != rootY) {
            if (this.rank[rootX] > this.rank[rootY]) {
                this.root[rootY] = rootX;
            } else if (this.rank[rootX] < this.rank[rootY]) {
                this.root[rootX] = rootY;
            } else {
                this.root[rootY] = rootX;
                this.rank[rootX] += 1;
            }
            
            mergeRequired = true;
        }
        
        return mergeRequired;
    }
}


// BFS
public boolean validTree(int n, int[][] edges) {
            
    List<List<Integer>> adjacencyList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        adjacencyList.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
        adjacencyList.get(edge[0]).add(edge[1]);
        adjacencyList.get(edge[1]).add(edge[0]);
    }
    
    Map<Integer, Integer> parent = new HashMap<>();
    parent.put(0, -1);
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(0);

    while (!queue.isEmpty()) {
        int node = queue.poll();
        for (int neighbour : adjacencyList.get(node)) {
            if (parent.get(node) == neighbour) {
                continue;
            }
            if (parent.containsKey(neighbour)) {
                return false;
            }
            queue.offer(neighbour);
            parent.put(neighbour, node);
        }
    }
    
    return parent.size() == n;   
}

/*
For the graph to be a valid tree, it must have exactly n - 1 edges. Any less, and it can't possibly be fully connected. Any more, 
and it has to contain cycles. Additionally, if the graph is fully connected and contains exactly n - 1 edges, 
it can't possibly contain a cycle, and therefore must be a tree!

These facts are fairly straightforward to prove. We won't go into why they are true here, but if you're not familiar with these facts, 
then we recommend reading up on graph theory. It is very important to be confident with graph theory in-order to pass the interviews at a top tech company.

Going by this definition, our algorithm needs to do the following:

Check whether or not there are n - 1 edges. If there's not, then return false.
Check whether or not the graph is fully connected. Return true if it is, false if otherwise.
*/

public boolean validTree(int n, int[][] edges) {
        
    if (edges.length != n - 1) return false;
    
    // Make the adjacency list.
    List<List<Integer>> adjacencyList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        adjacencyList.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
        adjacencyList.get(edge[0]).add(edge[1]);
        adjacencyList.get(edge[1]).add(edge[0]);
    }
    
    Stack<Integer> stack = new Stack<>();
    Set<Integer> seen = new HashSet<>();
    stack.push(0);
    seen.add(0);
    
    while (!stack.isEmpty()) {
        int node = stack.pop();
        for (int neighbour : adjacencyList.get(node)) {
            if (seen.contains(neighbour)) continue;
            seen.add(neighbour);
            stack.push(neighbour);
        }
    }
    
    return seen.size() == n;   
}

class Solution {
    
    private List<List<Integer>> adjacencyList = new ArrayList<>();
    private Set<Integer> seen = new HashSet<>();
    
    
    public boolean validTree(int n, int[][] edges) {
        
        if (edges.length != n - 1) return false;
        
        // Make the adjacency list.
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        
        // Carry out depth first search.
        dfs(0);
        // Inspect result and return the verdict.
        return seen.size() == n;   
    }
    
    public void dfs(int node) {
        if (seen.contains(node)) return;
        seen.add(node);
        for (int neighbour : adjacencyList.get(node)) {
            dfs(neighbour);
        }
    }
}

public boolean validTree(int n, int[][] edges) {
    
    if (edges.length != n - 1) return false;
    
    // Make the adjacency list.
    List<List<Integer>> adjacencyList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        adjacencyList.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
        adjacencyList.get(edge[0]).add(edge[1]);
        adjacencyList.get(edge[1]).add(edge[0]);
    }
    
    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> seen = new HashSet<>();
    queue.offer(0);
    seen.add(0);
    
    while (!queue.isEmpty()) {
        int node = queue.poll();
        for (int neighbour : adjacencyList.get(node)) {
            if (seen.contains(neighbour)) continue;
            seen.add(neighbour);
            queue.offer(neighbour);
        }
    }
    
    return seen.size() == n;   
}