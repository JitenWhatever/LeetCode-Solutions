/*
We write the integers of A and B (in the order they are given) on two separate horizontal lines.

Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:
    A[i] == B[j];
    The line we draw does not intersect any other connecting (non-horizontal) line.

Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.

Return the maximum number of connecting lines we can draw in this way.

Example 1:

Input: A = [1,4,2], B = [1,2,4]
Output: 2
Explanation: We can draw 2 uncrossed lines as in the diagram.
We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.

Example 2:

Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
Output: 3

Example 3:

Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
Output: 2

Note:
    1 <= A.length <= 500
    1 <= B.length <= 500
    1 <= A[i], B[i] <= 2000
*/

class Solution {
    public int maxUncrossedLines(int[] A, int[] B) {
        int N = A.length, M = B.length;
        
        int[][] dp = new int[N + 1][M + 1];
        
        for(int index_a = 1; index_a <= N; ++index_a) {
            for(int index_b = 1; index_b <= M; ++index_b) {
                if(A[index_a - 1] == B[index_b - 1]) {
                    dp[index_a][index_b] = dp[index_a - 1][index_b - 1] + 1;
                }
                else {
                   dp[index_a][index_b] = Math.max(dp[index_a - 1][index_b], dp[index_a][index_b - 1]);
                }
            }
        }
        
        return dp[N][M];
    }
}