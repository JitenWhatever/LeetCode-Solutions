/*
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
 

Constraints:

Each string consists only of '0' or '1' characters.
1 <= a.length, b.length <= 10^4
Each string is either "0" or doesn't contain any leading zero.
*/

class Solution {
public:
    string addBinary(string a, string b) {
        int n = a.length()  , m = b.length();
        int sum = 0;
        int index_a = n - 1, index_b = m - 1;
        
        string result = "";
        while(index_a >= 0 || index_b >= 0 || sum == 1) {
            sum += (index_a >= 0) ? (a[index_a] - '0') : 0;
            sum += (index_b >= 0) ? (b[index_b] - '0') : 0;
            
            result = to_string(sum%2) + result;
            sum /= 2;
            --index_a;
            --index_b;
        }
        
        return result;
    }
};