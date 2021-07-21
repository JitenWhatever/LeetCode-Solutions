/*
Given a string s, return true if a permutation of the string could form a palindrome.

Example 1:

Input: s = "code"
Output: false
Example 2:

Input: s = "aab"
Output: true
Example 3:

Input: s = "carerac"
Output: true
 

Constraints:

1 <= s.length <= 5000
s consists of only lowercase English letters.
*/


class Solution {
    public boolean canPermutePalindrome(String s) {
        
        int[] hash = new int[26];
        
        int result = 0;
        for (char ch : s.toCharArray()) {
            ++hash[ch - 'a'];
        }
        
        for (int count : hash) {
            result += (count/2)*2;
            
            if (result % 2 == 0 && count % 2 == 1) {
                ++result;
            }
        }
        
        return result == s.length();
    }
}

public class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] map = new int[128];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] % 2 == 0)
                count--;
            else
                count++;
        }
        return count <= 1;
    }
}

public class Solution {
    public boolean canPermutePalindrome(String s) {
        Set < Character > set = new HashSet < > ();
        for (int i = 0; i < s.length(); i++) {
            if (!set.add(s.charAt(i)))
                set.remove(s.charAt(i));
        }
        return set.size() <= 1;
    }
}
