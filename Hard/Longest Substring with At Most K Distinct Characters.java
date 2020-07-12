/*
Given a string s, find the length of the longest substring T that contains at most k distinct characters.

Example
For example, Given s = "eceba", k = 3,
T is "eceb" which its length is 4
*/

public class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {

        return helper(s.toCharArray(), k);
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