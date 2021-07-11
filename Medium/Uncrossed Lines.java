/*
We write the integers of nums1 and nums2 (in the order they are given) on two separate horizontal lines.

Now, we may draw connecting lines: a straight line connecting two numbers nums1[i] and nums2[j] such that:

nums1[i] == nums2[j];
The line we draw does not intersect any other connecting (non-horizontal) line.
Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.

Return the maximum number of connecting lines we can draw in this way.

 

Example 1:
https://assets.leetcode.com/uploads/2019/04/26/142.png

Input: nums1 = [1,4,2], nums2 = [1,2,4]
Output: 2
Explanation: We can draw 2 uncrossed lines as in the diagram.
We cannot draw 3 uncrossed lines, because the line from nums1[1]=4 to nums2[2]=4 will intersect the line from nums1[2]=2 to nums2[1]=2.
Example 2:

Input: nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
Output: 3
Example 3:

Input: nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
Output: 2
 

Note:

1 <= nums1.length <= 500
1 <= nums2.length <= 500
1 <= nums1[i], nums2[i] <= 2000
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

class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
     
        this.nums1 = nums1;
        this.nums2 = nums2;
        
        dp = new Integer[nums1.length + 1][nums2.length + 1];
        
        recurse(nums1.length, nums2.length);
        
        return dp[nums1.length][nums2.length];
    }
    
    private int[] nums1, nums2;
    private Integer[][] dp;
     
    private int recurse(int index_i, int index_j) {
        if (index_i == 0 || index_j == 0) {
            return dp[index_i][index_j] = 0;
        }
        
        if (Objects.nonNull(dp[index_i][index_j])) {
            return dp[index_i][index_j];
        }
        
        if (this.nums1[index_i - 1] == nums2[index_j - 1]) {
            return dp[index_i][index_j] = 1 + recurse(index_i - 1, index_j - 1);
        }
        
        
        return dp[index_i][index_j] = Math.max(recurse(index_i, index_j - 1), recurse(index_i - 1, index_j));
    }
}