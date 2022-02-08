/*
Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.

Example 1:

Input: num = 38
Output: 2
Explanation: The process is
38 --> 3 + 8 --> 11
11 --> 1 + 1 --> 2 
Since 2 has only one digit, return it.
Example 2:

Input: num = 0
Output: 0
 
Constraints:

0 <= num <= 231 - 1
 
Follow up: Could you do it without any loop/recursion in O(1) runtime?
*/

// digital roots

class AddDigits {
    public int addDigits(int num) {
        int digitalRoot = 0;
        while (num > 0) {
            digitalRoot += num % 10;
            num = num / 10;
            
            if (num == 0 && digitalRoot > 9) {
                num = digitalRoot;
                digitalRoot = 0;  
            }    
        }     
        return digitalRoot;
    }


    public int addDigits1(int num) {
        if (num == 0) return 0;
        if (num % 9 == 0) return 9;
        return num % 9;
    }

    public int addDigits2(int num) {
        return num == 0 ? 0 : 1 + (num - 1) % 9;
    }
}