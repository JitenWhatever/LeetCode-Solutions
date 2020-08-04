/*
Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example 1:

Input: 16
Output: true
Example 2:

Input: 5
Output: false
Follow up: Could you solve it without loops/recursion?
*/

class Solution {
    public boolean isPowerOfFour(int num) {
        if(num == 0) {
            return false;
        }
        
        return (Math.floor(getLog(num, 4)) == Math.ceil(getLog(num, 4)));    
    }
    
    private double getLog(int num, int base) {
        return Math.log(num)/Math.log(base);
    }
}