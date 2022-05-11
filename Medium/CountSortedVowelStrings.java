/*
Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) 
and are lexicographically sorted.

A string s is lexicographically sorted if for all valid i, s[i] 
is the same as or comes before s[i+1] in the alphabet.


Example 1:

Input: n = 1
Output: 5
Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].

Example 2:

Input: n = 2
Output: 15
Explanation: The 15 sorted strings that consist of vowels only are
["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.

Example 3:

Input: n = 33
Output: 66045

Constraints:
    1 <= n <= 50 
*/

public class CountSortedVowelStrings {
    
    public int countVowelStrings(int n) {
        return countVowelStringUtil(n, 1);
    }

    int countVowelStringUtil(int n, int vowels) {
        if (n == 0)
            return 1;
        int result = 0;
        for (int i = vowels; i <= 5; i++) {
            result += countVowelStringUtil(n - 1, i);
        }
        return result;
    }

    public int countVowelStrings1(int n) {
        return countVowelStringUtil(n, 5);
    }

    int countVowelStringUtil1(int n, int vowels) {
        if (n == 1)
            return vowels;
        if (vowels == 1)
            return 1;
        return countVowelStringUtil1(n - 1, vowels) +
                countVowelStringUtil1(n, vowels - 1);
    }

    public int countVowelStrings2(int n) {
        int memo[][] = new int[n + 1][6];
        return countVowelStringUtil2(n, 5, memo);
    }

    int countVowelStringUtil2(int n, int vowels, int memo[][]) {
        if (n == 1)
            return vowels;
        if (vowels == 1)
            return 1;
        if (memo[n][vowels] != 0)
            return memo[n][vowels];
        int res = countVowelStringUtil2(n - 1, vowels, memo) +
                countVowelStringUtil2(n, vowels - 1, memo);
        memo[n][vowels] = res;
        return res;
    }


    public int countVowelStrings3(int n) {
        int[][] dp = new int[n + 1][6];
        for (int vowels = 1; vowels <= 5; vowels++)
            dp[1][vowels] = vowels;
        for (int nValue = 2; nValue <= n; nValue++) {
            dp[nValue][1] = 1;
            for (int vowels = 2; vowels <= 5; vowels++) {
                dp[nValue][vowels] = dp[nValue][vowels - 1] + dp[nValue - 1][vowels];
            }
        }
        return dp[n][5];
    }

    public int countVowelStrings4(int n) {
        return (n + 4) * (n + 3) * (n + 2) * (n + 1) / 24;
    }
    
}
