/*
There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.

For each house i, we can either build a well inside it directly with cost wells[i - 1] (note the -1 due to 0-indexing), 
or pipe in water from another well to it. The costs to lay pipes between houses are given by the array pipes, 
where each pipes[j] = [house1j, house2j, costj] represents the cost to connect house1j and house2j together using a pipe. Connections are bidirectional.

Return the minimum total cost to supply water to all houses.

 

Example 1:

        1     3
         \   /  1
       1  \ /
           2

Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
Output: 3
Explanation: 
The image shows the costs of connecting houses using pipes.
The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.
Example 2:

Input: n = 2, wells = [1,1], pipes = [[1,2,1]]
Output: 2
 

Constraints:

2 <= n <= 104
wells.length == n
0 <= wells[i] <= 105
1 <= pipes.length <= 104
pipes[j].length == 3
1 <= house1j, house2j <= n
0 <= costj <= 105
house1j != house2j
*/

// Time Complexity: O((N+M)⋅log(N+M))
// Space Complexity: O(N+M)O(N+M)
class Solution {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        // min heap to maintain the order of edges to be visited.
        PriorityQueue<Pair<Integer, Integer>> edgesHeap =
                new PriorityQueue<>(n, (a, b) -> (a.getKey() - b.getKey()));

        // representation of graph in adjacency list
        List<List<Pair<Integer, Integer>>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i < n + 1; ++i) {
            graph.add(new ArrayList<Pair<Integer, Integer>>());
        }

        // add a virtual vertex indexed with 0,
        //   then add an edge to each of the house weighted by the cost
        for (int i = 0; i < wells.length; ++i) {
            Pair<Integer, Integer> virtualEdge = new Pair<>(wells[i], i + 1);
            graph.get(0).add(virtualEdge);
            // initialize the heap with the edges from the virtual vertex.
            edgesHeap.add(virtualEdge);
        }

        // add the bidirectional edges to the graph
        for (int i = 0; i < pipes.length; ++i) {
            int house1 = pipes[i][0];
            int house2 = pipes[i][1];
            int cost = pipes[i][2];
            graph.get(house1).add(new Pair<Integer, Integer>(cost, house2));
            graph.get(house2).add(new Pair<Integer, Integer>(cost, house1));
        }

        // kick off the exploration from the virtual vertex 0
        Set<Integer> mstSet = new HashSet<>();
        mstSet.add(0);

        int totalCost = 0;
        while (mstSet.size() < n + 1) {
            Pair<Integer, Integer> edge = edgesHeap.poll();
            int cost = edge.getKey();
            int nextHouse = edge.getValue();
            if (mstSet.contains(nextHouse)) {
                continue;
            }

            // adding the new vertex into the set
            mstSet.add(nextHouse);
            totalCost += cost;

            // expanding the candidates of edge to choose from in the next round
            for (Pair<Integer, Integer> neighborEdge : graph.get(nextHouse)) {
                if (!mstSet.contains(neighborEdge.getValue())) {
                    edgesHeap.add(neighborEdge);
                }
            }
        }

        return totalCost;
    }
}


class UnionFind {
    /**
     * Implementation of UnionFind without load-balancing.
     */
    private int[] group;
    private int[] rank;

    public UnionFind(int size) {
        // container to hold the group id for each member
        // Note: the index of member starts from 1,
        //   thus we add one more element to the container.
        group = new int[size + 1];
        rank = new int[size + 1];
        for (int i = 0; i < size + 1; ++i) {
            group[i] = i;
            rank[i] = 0;
        }
    }

    /**
     * return the group id that the person belongs to.
     */
    public int find(int person) {
        if (group[person] != person) {
            group[person] = find(group[person]);
        }
        return group[person];
    }

    /**
     * Join the groups together.
     * return:
     * false when the two persons belong to the same group already,
     * otherwise true
     */
    public boolean union(int person1, int person2) {
        int group1 = find(person1);
        int group2 = find(person2);
        if (group1 == group2) {
            return false;
        }

        // attach the group of lower rank to the one with higher rank
        if (rank[group1] > rank[group2]) {
            group[group2] = group1;
        } else if (rank[group1] < rank[group2]) {
            group[group1] = group2;
        } else {
            group[group1] = group2;
            rank[group2] += 1;
        }

        return true;
    }
}


class Solution {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        List<int[]> orderedEdges = new ArrayList<>(n + 1 + pipes.length);

        // add the virtual vertex (index with 0) along with the new edges.
        for (int i = 0; i < wells.length; ++i) {
            orderedEdges.add(new int[]{0, i + 1, wells[i]});
        }

        // add the existing edges
        for (int i = 0; i < pipes.length; ++i) {
            int[] edge = pipes[i];
            orderedEdges.add(edge);
        }

        // sort the edges based on their cost
        Collections.sort(orderedEdges, (a, b) -> a[2] - b[2]);

        // iterate through the ordered edges
        UnionFind uf = new UnionFind(n);
        int totalCost = 0;
        for (int[] edge : orderedEdges) {
            int house1 = edge[0];
            int house2 = edge[1];
            int cost = edge[2];
            // determine if we should add the new edge to the final MST
            if (uf.union(house1, house2)) {
                totalCost += cost;
            }
        }

        return totalCost;
    }
}