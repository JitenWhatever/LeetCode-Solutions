/*
Given a 32-bit signed integer, reverse digits of an integer.

Example 1:
Input: 123
Output: 321

Example 2:
Input: -123
Output: -321

Example 3:
Input: 120
Output: 21

Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

*/

class Solution {
    public int reverse1(int x) {
        int reverseNumber  = 0;
        while(x != 0){
           
            int digit  = x%10;
            if (reverseNumber > Integer.MAX_VALUE/10 || (reverseNumber == Integer.MAX_VALUE / 10 && digit > 7)) return 0;
            if (reverseNumber < Integer.MIN_VALUE/10 || (reverseNumber == Integer.MIN_VALUE / 10 && digit < -8)) return 0;
            reverseNumber = reverseNumber*10 + (digit);
            x /= 10; 
            
        }
        
        return reverseNumber;
    }


    public int reverse2(int x) {
        int reverseNumber = 0;

        while(x != 0) {
            int  digit = x % 10;
            int dummy = reverseNumber*10 + digit;

            if((reverseNumber - digit)/10 != reverseNumber) {
                return 0;
            }

            x /= 10;
            reverseNumber = dummy;
        }

        return reverseNumber;
    }

    public int reverse(int x) {
        return reverse2(x);
    }
}