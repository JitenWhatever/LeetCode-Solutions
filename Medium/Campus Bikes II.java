/*
On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.
We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.
The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.

Example 1:
https://assets.leetcode.com/uploads/2019/03/06/1261_example_1_v2.png

Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: 6
Explanation: 
We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3, so the output is 6.

Example 2:
https://assets.leetcode.com/uploads/2019/03/06/1261_example_2_v2.png

Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: 4
Explanation: 
We first assign bike 0 to worker 0, then assign bike 1 to worker 1 or worker 2, bike 2 to worker 2 or worker 1. Both assignments lead to sum of the Manhattan distances as 4.

Note:
0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
All worker and bike locations are distinct.
1 <= workers.length <= bikes.length <= 10
*/

class Solution {
    
    public int assignBikes(int[][] workers, int[][] bikes) {

        // assigned = new boolean[bikes.length];
        // dfs1(workers, bikes, 0, 0); // TLE
        // return result;

        dp = new int[1<<bikes.length];

        return dfs(workers, bikes, 0, 0); // bit masking
    }

    private int[] dp;
    
    private int dfsint[][] workers, int[][] bikes, int index, int state) {
        if(index == workers.length) {
            return 0;
        }

        if(dp[state] != 0) {
            return dp[state];
        }

        int result = Integer.MAX_VALUE;

        for(int b = 0; b < bikes.length; ++b) {
            if(state&(1<<b) == 0) {
                int distance = Math.abs(workers[index][0] - bikes[b][0]) + Math.abs(workers[index][1] - bikes[b][1]);
                result =  Math.min(result, distance + dfs(workers, bikes, index + 1, state | (1<<b)));
            }
        }

        dp[state] = result;

        return dp[state];
    }
    
    
    private int result = Integer.MAX_VALUE;
    private boolean[] assigned;
    
    private void dfs1(int[][] workers, int[][] bikes, int index, int distance) {
        if(index == workers.length) {
            result = Math.min(result, distance);
            return;
        }

        if(distance > result) {
            return ;
        }

        for(int b = 0; b < bikes.length; ++b) {
            if(!assigned[b]) {
                assigned[b] = true;
                int d = Math.abs(workers[index][0] - bikes[b][0]) + Math.abs(workers[index][1] - bikes[b][1]);
                dfs(workers, bikes, index + 1, d + distance);
                assigned[b] = false;
            }
        }
    }
}