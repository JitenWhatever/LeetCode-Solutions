/*
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. 
The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix.

For example, costs[0][0] is the cost of painting house 0 with color red; 
costs[1][2] is the cost of painting house 1 with color green, and so on...
Find the minimum cost to paint all houses.

Example:

Input: [[17, 2, 17], [16, 16, 5], [14, 3, 19]]
Output: 10
Explanation: Paint house 0 into blue, Paint house 1 into green, Paint house 2 into blue.
            Minimum Cost: 2 + 5 + 3 = 10.
*/

class Solution {
    public int minCost(int[][] costs) {
        if(costs == null || costs.length == 0) {
            return 0;
        }

        for(int index = 1; index < costs.length; ++index) {
            costs[index][0] += Math.min(costs[index - 1][1], costs[index - 1][2]);
            costs[index][1] += Math.min(costs[index - 1][0], costs[index - 1][2]);
            costs[index][2] += Math.min(costs[index - 1][1], costs[index - 1][0]);
        }

        return  Math.min(costs[costs.length - 1][0], Math.min(costs[costs.length - 1][0], costs[costs.length - 1][0]);
    }
}