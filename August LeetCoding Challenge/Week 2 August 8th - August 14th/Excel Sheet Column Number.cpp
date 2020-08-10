/*
Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
Example 1:

Input: "A"
Output: 1
Example 2:

Input: "AB"
Output: 28
Example 3:

Input: "ZY"
Output: 701
 

Constraints:

1 <= s.length <= 7
s consists only of uppercase English letters.
s is between "A" and "FXSHRXW".
*/

class Solution {
public:
    int titleToNumber(string s) {
        int num = 0;
        int hash[26];
        for(int index = 0; index < 26; index++) {
            hash[index] = index + 1;
        }
        
        int len = s.length();
        
        for(int index = 0; index < len; index++) {
            num += hash[s[index] - 'A']*pow(26, len - index - 1);
        }
        
        return num;
    }
};