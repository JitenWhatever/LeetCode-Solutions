/*
Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

Example:

Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
*/

class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        
        if(A == null || B == null || C == null || D == null || A.length == 0 || B.length == 0 || C.length == 0 || D.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> sumCount = new HashMap<>();
        
        for(int a = 0; a < A.length; ++a) {
            for(int b = 0; b < B.length; ++b) {
                Integer count = sumCount.getOrDefault(A[a] + B[b], 0);
                sumCount.put(A[a] + B[b], count + 1);
            }
        }
        
        int fourSumCount = 0;
        
        for(int c = 0; c < C.length; ++c) {
            for(int d = 0; d < D.length; ++d) {
                fourSumCount += sumCount.getOrDefault(-(C[c] + D[d]), 0);
            }
        }
        
        return fourSumCount;
    }
}