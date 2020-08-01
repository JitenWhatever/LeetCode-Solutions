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
public:
    bool detectCapitalUse(string word) {
        if(word.length() < 2) {
            return true;
        }
        
        if(word[0] >= 'A' && word[0] <= 'Z' && word[1] >= 'A' && word[1] <= 'Z') {
            for(int index = 2; index < word.length(); ++index) {
                if(!(word[index] >= 'A' && word[index] <= 'Z')) {
                    return false;
                }
            }
        }
        else {
            for(int index = 1; index < word.length(); ++index) {
                if(!(word[index] >= 'a' && word[index] <= 'z')) {
                    return false;
                }
            }
        }
        
        return true;
    }
};