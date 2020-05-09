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
        if(n < 1) return false;
        int bitCount = 0;
        for(int bit = 0; bit < 32; ++bit){
            if((n % 2) == 1){
                ++bitCount;
            }
            
            n = n/2;
            
            if(bitCount > 1){
                return false;
            }
        }
        
        return true;
    }
}