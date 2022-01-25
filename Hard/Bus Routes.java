/*
You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.

For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
You will start at the bus stop source (You are not on any bus initially), and 
you want to go to the bus stop target. You can travel between bus stops by buses only.

Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.

Example 1:

Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
Example 2:

Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
Output: -1
 

Constraints:

1 <= routes.length <= 500.
1 <= routes[i].length <= 10^5
All the values of routes[i] are unique.
sum(routes[i].length) <= 10^5
0 <= routes[i][j] < 10^6
0 <= source, target < 10^6
*/


class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        
        for (int bus = 0; bus < routes.length; ++bus) {
            for (int st : routes[bus]) {
                graph.computeIfAbsent(st, key -> new HashSet<>()).add(bus);
            }
        }
        
        if (!graph.containsKey(source) || !graph.containsKey(target)) return -1;
        
        if (source == target) return 0;
    
        Queue<Integer> Q = new LinkedList<>();
        Set<Integer> busTaken = new HashSet<>();
        Set<Integer> stopVisited = new HashSet<>();
        
        int minBuses = 0;
        Q.add(source);
        stopVisited.add(source);
        while (!Q.isEmpty()) {
            minBuses++;
            int size = Q.size();
            
            while (size-- > 0) {
                int currStop = Q.poll();
                for (int bus : graph.get(currStop)) {
                    if (busTaken.contains(bus)) continue;
                    busTaken.add(bus);
                    for (int nextStop : routes[bus]) {
                        if (stopVisited.contains(nextStop)) continue;
                        stopVisited.add(nextStop);
                        if (nextStop == target) {
                            return minBuses;
                        }
                        
                        Q.add(nextStop);
                    }
                }
            }
        }
        
        return -1;
    }
}


// Author solution
// import java.awt.Point;

class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S==T) return 0;
        int N = routes.length;

        List<List<Integer>> graph = new ArrayList();
        for (int i = 0; i < N; ++i) {
            Arrays.sort(routes[i]);
            graph.add(new ArrayList());
        }
        Set<Integer> seen = new HashSet();
        Set<Integer> targets = new HashSet();
        Queue<Point> queue = new ArrayDeque();

        // Build the graph.  Two buses are connected if
        // they share at least one bus stop.
        for (int i = 0; i < N; ++i)
            for (int j = i+1; j < N; ++j)
                if (intersect(routes[i], routes[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }

        // Initialize seen, queue, targets.
        // seen represents whether a node has ever been enqueued to queue.
        // queue handles our breadth first search.
        // targets is the set of goal states we have.
        for (int i = 0; i < N; ++i) {
            if (Arrays.binarySearch(routes[i], S) >= 0) {
                seen.add(i);
                queue.offer(new Point(i, 0));
            }
            if (Arrays.binarySearch(routes[i], T) >= 0)
                targets.add(i);
        }

        while (!queue.isEmpty()) {
            Point info = queue.poll();
            int node = info.x, depth = info.y;
            if (targets.contains(node)) return depth+1;
            for (Integer nei: graph.get(node)) {
                if (!seen.contains(nei)) {
                    seen.add(nei);
                    queue.offer(new Point(nei, depth+1));
                }
            }
        }

        return -1;
    }

    public boolean intersect(int[] A, int[] B) {
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            if (A[i] == B[j]) return true;
            if (A[i] < B[j]) i++; else j++;
        }
        return false;
    }
}