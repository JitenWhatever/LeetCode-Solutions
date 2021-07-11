/*
Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.

A string s is said to be one distance apart from a string t if you can:

Insert exactly one character into s to get t.
Delete exactly one character from s to get t.
Replace exactly one character of s with a different character to get t.
 

Example 1:

Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.
Example 2:

Input: s = "", t = ""
Output: false
Explanation: We cannot get t from s by only one step.
Example 3:

Input: s = "a", t = ""
Output: true
Example 4:

Input: s = "", t = "A"
Output: true
 

Constraints:

0 <= s.length <= 104
0 <= t.length <= 104
s and t consist of lower-case letters, upper-case letters and/or digits.
*/

public class Solution {
    public boolean isOneEditDistance(String A, String B) {
        if ((A == null || A.length() == 0) && (B == null || B.length() == 0)) {
            return false;
        }
         
        if (A == null || A.length() == 0) {
            return B.length() == 1;
        }
         
        if (B == null || B.length() == 0) {
            return A.length() == 1;
        }
         
        if (Math.abs(A.length() - B.length()) > 1) {
            return false;
        }
         
        int distance = 0, index_a = 0, index_b = 0;

        while (index_a < A.length() && index_b < B.length()) {
            if (A.charAt(index_a) != B.charAt(index_b)) {
                distance++;
                if (distance > 1) {
                    return false;
                }
                 
                if (A.length() > B.length()) {
                    index_a++;
                } else if (A.length() < B.length()) {
                    index_b++;
                } else {
                    index_a++;
                    index_b++;
                }
            } else {
                index_a++;
                index_b++;
            }
        }
         
        if (index_a < A.length() || index_b < B.length()) {
            distance++;
        }
         
        return distance == 1;
    }
}

class Solution {
  public boolean isOneEditDistance(String s, String t) {
    int ns = s.length();
    int nt = t.length();

    // Ensure that s is shorter than t.
    if (ns > nt)
      return isOneEditDistance(t, s);

    // The strings are NOT one edit away distance  
    // if the length diff is more than 1.
    if (nt - ns > 1)
      return false;

    for (int i = 0; i < ns; i++)
      if (s.charAt(i) != t.charAt(i))
        // if strings have the same length
        if (ns == nt)
          return s.substring(i + 1).equals(t.substring(i + 1));
        // if strings have different lengths
        else
          return s.substring(i).equals(t.substring(i + 1));

    // If there is no diffs on ns distance
    // the strings are one edit away only if
    // t has one more character. 
    return (ns + 1 == nt);
  }
}