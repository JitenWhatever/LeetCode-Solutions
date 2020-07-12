/*
Given a string s, find the length of the longest substring t that contains at most 2 distinct characters.
Example 1:

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.

Example 2:

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.
*/

class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {

        return helper(s.toCharArray(), 2);
    }

    private int helper(char[] A, int K){
        int left = 0, right = 0;
        int windows = 0;
        
        HashMap<character, Integer> hash = new HashMap<>();
        
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

