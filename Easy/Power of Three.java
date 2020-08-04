/*
Given an integer, write a function to determine if it is a power of three.

Example 1:

Input: 27
Output: true
Example 2:

Input: 0
Output: false
Example 3:

Input: 9
Output: true
Example 4:

Input: 45
Output: false
Follow up:
Could you do it without using any loop / recursion?
*/

class Solution {
    public boolean isPowerOfThree(int num) {
        if(num == 0) {
            return false;
        }
        
        return (Math.floor(getLog(num, 3)) == Math.ceil(getLog(num, 3)));
    }
    
    private double getLog(int num, int base) {
        return Math.log10(num)/Math.log10(base);
    }
}



