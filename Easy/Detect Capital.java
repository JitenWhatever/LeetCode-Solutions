/*
Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.
 

Example 1:

Input: "USA"
Output: True
 

Example 2:

Input: "FlaG"
Output: False
 

Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.
*/

class Solution {
    public boolean detectCapitalUse(String word) {
        if(word.length() < 2) {
            return true;
        }

        if(word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' && word.charAt(1) >= 'A' && word.charAt(1) <= 'Z') {
            for(int index = 2; index < word.length(); ++index) {
                if(!(word.charAt(index) >= 'A' && word.charAt(index) <= 'Z')) {
                    return false;
                }
            }
        }
        else {
            for(int index = 1; index < word.length(); ++index) {
                if(!(word.charAt(index) >= 'a' && word.charAt(index) <= 'z')) {
                    return false;
                }
            }
        }
        return true;
    }
}