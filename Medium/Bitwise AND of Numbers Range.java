/*
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

Example 1:

Input: [5,7]
Output: 4

Example 2:

Input: [0,1]
Output: 0
*/

class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int result = 0;
        
        for(int bit = 31; bit >= 0; --bit) {
            if((m & (1<<bit)) == (n & (1<<bit))) {
                result |= (m &(1<<bit));
            }
            else {
                break;
            }
        }
        
        return result;
    }
}