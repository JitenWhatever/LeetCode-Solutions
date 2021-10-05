/*
An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

Given an integer n, return true if n is an ugly number.

Example 1:

Input: n = 6
Output: true
Explanation: 6 = 2 × 3
Example 2:

Input: n = 8
Output: true
Explanation: 8 = 2 × 2 × 2
Example 3:

Input: n = 14
Output: false
Explanation: 14 is not ugly since it includes the prime factor 7.
Example 4:

Input: n = 1
Output: true
Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 

Constraints:

-2^31 <= n <= 2^31 - 1
*/

class Solution {
    public boolean isUgly(int num) {
        if(num == 0) {
            return false;
        }
        
        while(num != 1) {
         
            if(num % 2 == 0) {
                num = num / 2;
            }
            else if(num % 3 == 0) {
                num /= 3;
            }
            else if(num % 5 == 0){
                num /= 5;
            }
            else {
                return false;
            }
        }
        
        return true;
    }

}

class Solution {
    public boolean isUgly(int num) {
        if(num == 0) {
            return false;
        }
        
       for (int i=2; i<6 && num>0; i++) {
            while (num % i == 0) {
                num /= i;
            }
       }
        
        return num == 1;
    }

}
