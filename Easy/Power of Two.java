/*
Given an integer, write a function to determine if it is a power of two.

Example 1:

Input: 1
Output: true 
Explanation: 20 = 1

Example 2:

Input: 16
Output: true
Explanation: 24 = 16

Example 3:

Input: 218
Output: false
*/

class Solution {
    public boolean isPowerOfTwo(int n) {
        
        if(n == Integer.MIN_VALUE || n == Integer.MAX_VALUE || n == 0) {
            return false;
        }
        
        int result = n&(n-1);
        
        if(result == 0) {
            return true;
        }
        
        return false;
    }
}