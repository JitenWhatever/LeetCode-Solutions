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
public:
    bool isPowerOfFour(int n) {
       if(n == 0) {
           return false;
       }
        
        return (floor(log(n)/log(4)) == ceil(log(n)/log(4)));
    }
};