/*
Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

Each person may dislike some other people, and they should not go into the same group. 

Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

Return true if and only if it is possible to split everyone into two groups in this way.

 
Example 1:

Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4], group2 [2,3]

Example 2:

Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false

Example 3:

Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false

Note:
    1 <= N <= 2000
    0 <= dislikes.length <= 10000
    1 <= dislikes[i][j] <= N
    dislikes[i][0] < dislikes[i][1]
    There does not exist i != j for which dislikes[i] == dislikes[j].
*/

class Solution {
    private List<Integer>[] graph;
    private  int[] colors;
    
    public boolean possibleBipartition(int N, int[][] dislikes) {
        graph = new ArrayList[N];
        
        for(int node = 0; node < N; ++node) {
            graph[node] = new ArrayList();
        }
        
        for(int[] edge : dislikes) {
            graph[edge[0] - 1].add(edge[1] - 1);
            graph[edge[1] - 1].add(edge[0] - 1);
        }
        
        colors = new int[N];
        Queue<Integer> Q = new LinkedList<>();
        
        for(int node = 0; node < N; ++node) {
            
            if(colors[node] != 0) continue;
            
            Q.add(node);
            colors[node] = 1;
            
            while(!Q.isEmpty())  {
                int currentNode = Q.poll();
                
                for(int neighbour : graph[currentNode]) {
                    if(colors[currentNode] == colors[neighbour]) {
                        return false;
                    }
                    if(colors[neighbour] != 0) continue;
                    
                    colors[neighbour] = -colors[currentNode];
                    
                    Q.add(neighbour);
                }
            }
        }
        return true;
    }
}