/*
Given two binary strings a and b, return their sum as a binary string.

 

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
 

Constraints:

1 <= a.length, b.length <= 104
a and b consist only of '0' or '1' characters.
Each string does not contain leading zeros except for the zero itself.
*/

class Solution {
    public String addBinary(String a, String b) {
        String result = "";
        int index_a = a.length() - 1, index_b = b.length() - 1;
        int sum = 0;
        
        while(index_a >= 0|| index_b >= 0 || sum == 1) {
            
            sum += (index_a >= 0) ? a.charAt(index_a) - '0' : 0;
            sum += (index_b >= 0) ? b.charAt(index_b) - '0' : 0;
                
            result = sum%2 + result;
            sum /= 2;
            --index_a;
            --index_b;
        }
        
        return result;
    }
}

class Solution {
  public String addBinary(String a, String b) {
    return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
  }
}

import java.math.BigInteger;
class Solution {
  public String addBinary(String a, String b) {
    BigInteger x = new BigInteger(a, 2);
    BigInteger y = new BigInteger(b, 2);
    BigInteger zero = new BigInteger("0", 2);
    BigInteger carry, answer;
    while (y.compareTo(zero) != 0) {
      answer = x.xor(y);
      carry = x.and(y).shiftLeft(1);
      x = answer;
      y = carry;
    }
    return x.toString(2);
  }
}