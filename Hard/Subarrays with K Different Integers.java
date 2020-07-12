/*
Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of A.

 

Example 1:

Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
Example 2:

Input: A = [1,2,1,3,4], K = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 

Note:

1 <= A.length <= 20000
1 <= A[i] <= A.length
1 <= K <= A.length
*/

class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        
        return helper(A, K) - helper(A, K - 1);
    }
    
    private int helper(int[] A, int K){
        int left = 0, right = 0;
        int windows = 0;
        
        HashMap<Integer, Integer> hash = new HashMap<>();
        
        while(right < A.length) {
            
            hash.put(A[right], hash.getOrDefault(A[right++], 0) + 1);
            
            while(left < A.length && hash.size() > K) {
                hash.put(A[left], hash.get(A[left]) - 1);
                if(hash.get(A[left]) == 0) {
                    hash.remove(A[left]);
                }
                ++left;
            }
            
            windows += (right- left);
        }
        
        return windows;
    }
}

//https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/523136/JavaC%2B%2BPython-Sliding-Window