/*
Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. 
(i.e., "ace" is a subsequence of "abcde" while "aec" is not).

 

Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true
Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false
 

Constraints:

0 <= s.length <= 100
0 <= t.length <= 104
s and t consist only of lowercase English letters.
 

Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109, and you want to check one by one to see if t has its subsequence. 
In this scenario, how would you change your code?
*/

class Solution {
    public boolean isSubsequence1(String A, String B) {
        int N = A.length();
        int M = B.length();
        
        if(N > M) {
            return false;
        }
        
        int index_a = 0, index_b = 0;
        
        while(index_a < N && index_b < M) {
            if(A.charAt(index_a) == B.charAt(index_b)) {
                ++index_a;
                ++index_b;
            }
            else {
                ++index_b;
            }
        }
        
        return index_a == N;
    }

     public boolean isSubsequence(String s, String t) {
        List<Integer>[] idx = new List[256]; // Just for clarity
        for (int i = 0; i < t.length(); i++) {
            if (idx[t.charAt(i)] == null)
                idx[t.charAt(i)] = new ArrayList<>();
            idx[t.charAt(i)].add(i);
        }
        
        int prev = 0;
        for (int i = 0; i < s.length(); i++) {
            if (idx[s.charAt(i)] == null) return false; // Note: char of S does NOT exist in T causing NPE
            int j = Collections.binarySearch(idx[s.charAt(i)], prev);
            if (j < 0) j = -j - 1;
            if (j == idx[s.charAt(i)].size()) return false;
            prev = idx[s.charAt(i)].get(j) + 1;
        }
        return true;
    }
}

class Solution {

    public boolean isSubsequence(String s, String t) {

        // precomputation, build the hashmap out of the target string
        HashMap<Character, List<Integer>> letterIndicesTable = new HashMap<>();
        for (int i = 0; i < t.length(); ++i) {
            if (letterIndicesTable.containsKey(t.charAt(i)))
                letterIndicesTable.get(t.charAt(i)).add(i);
            else {
                ArrayList<Integer> indices = new ArrayList<Integer>();
                indices.add(i);
                letterIndicesTable.put(t.charAt(i), indices);
            }
        }

        Integer currMatchIndex = -1;
        for (char letter : s.toCharArray()) {
            if (!letterIndicesTable.containsKey(letter))
                return false; // no match, early exist

            boolean isMatched = false;
            // greedy match with linear search
            for (Integer matchIndex : letterIndicesTable.get(letter)) {
                if (currMatchIndex < matchIndex) {
                    currMatchIndex = matchIndex;
                    isMatched = true;
                    break;
                }
            }

            if (!isMatched)
                return false;
        }

        // consume all characters in the source string
        return true;
    }
}

class Solution {

    public boolean isSubsequence(String s, String t) {

        Integer sourceLen = s.length(), targetLen = t.length();
        // the source string is empty
        if (sourceLen == 0)
            return true;

        int[][] dp = new int[sourceLen + 1][targetLen + 1]; // max match
        // DP calculation, we fill the matrix column by column, bottom up
        for (int col = 1; col <= targetLen; ++col) {
            for (int row = 1; row <= sourceLen; ++row) {
                if (s.charAt(row - 1) == t.charAt(col - 1))
                    // find another match
                    dp[row][col] = dp[row - 1][col - 1] + 1;
                else
                    // retrieve the maximal result from previous prefixes
                    dp[row][col] = Math.max(dp[row][col - 1], dp[row - 1][col]);
            }
            // check if we can consume the entire source string,
            // with the current prefix of the target string.
            if (dp[sourceLen][col] == sourceLen)
                return true;
        }

        // matching failure
        return false;
    }
}