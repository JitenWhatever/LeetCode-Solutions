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

class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
     
        int[] hash = new int[26];
        for(int index = 0; index < 26; ++index) {
            hash[index] = index + 1;
        }
        
        int len = s.length();
        int result = 0;
        for(int index = 0; index < s.length(); index++) {
            result += hash[s.charAt(index) - 'A']*Math.pow(26, len - index - 1);
        }
        
        return result;
    }

    public int titleToNumber1(String s) {
        int result = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            result = result * 26;
            // In Java, subtracting characters is subtracting ASCII values of characters
            result += (s.charAt(i) - 'A' + 1);
        }
        return result;
    }
}