/*
Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().

 

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Example 3:

Input: haystack = "", needle = ""
Output: 0
 

Constraints:

0 <= haystack.length, needle.length <= 5 * 104
haystack and needle consist of only lower-case English characters.
*/


// TLE
class Solution {
    public int strStr(String haystack, String needle) {
    
        for (int index = 0; index <= haystack.length() - needle.length(); ++index) {
            int itr = 0;
            for (itr = 0; itr < needle.length(); ++itr) {
                if (haystack.charAt(index + itr) != needle.charAt(itr)) {
                    break;
                }
            }
            
            if (itr == needle.length()) {
                return index;
            }
        }
        return -1;
    }
}

// KMP 12ms
class Solution {
    public int strStr(String haystack, String needle) {
       if (needle.isEmpty()) {
           return 0;
       }
        
       int[] lps = getLPS(needle);
        int hlen = haystack.length(), nlen = needle.length();
        int index = 0, itr = 0;
        
        while (index < hlen) {
            if (haystack.charAt(index) == needle.charAt(itr)) {
                ++index;
                ++itr;
            }
            
            if (itr == nlen) {
                return index - itr;
            }
            
            if (index < hlen && haystack.charAt(index) != needle.charAt(itr)) {
                if (itr != 0) {
                    itr = lps[itr - 1];
                } else {
                    ++index;
                }
            }
        }
        
        return -1;
    }
    
    private int[] getLPS(String str) {
        int size = str.length();
        int[] lps = new int[size];
        
        int len = 0;
        int index = 1;
        
        while (index < size) {
            if (str.charAt(index) == str.charAt(len)) {
                lps[index++] = ++len;
            } else if (len != 0){
                len = lps[len - 1];
            } else {
                lps[index++] = 0;
            }
        }
        
        return lps;
    }
}