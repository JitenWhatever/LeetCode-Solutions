/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
*/

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<Integer>();
        if(s.length() < p.length()) {
            return result;
        }
        
        Integer windowSize = p.length(), windowLeft = 0, windowRight = 0;
        int[] hash = new int[26];
        
        for(int index = 0; index < p.length(); ++index) {
            hash[p.charAt(index) - 'a']++;
        }

        while(windowRight < s.length()) {
            
            if(hash[s.charAt(windowRight++) - 'a']-- >= 1) {
                
                windowSize--;
            }
            
            if(windowSize == 0) {
                result.add(windowLeft);
            }
            
            if(windowRight - windowLeft == p.length() && hash[s.charAt(windowLeft++) - 'a']++ >= 0) {
                windowSize++;
            }
        }
        
        return result;
    }
}