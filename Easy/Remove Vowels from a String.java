import java.util.HashSet;

/*
Given a string s, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and return the new string.

Example 1:
    Input: "leetcodeisacommunityforcoders"
    Output: "ltcdscmmtyfrcdrs"

Example 2:
    Input: "aeiou"
    Output: ""

Note:
    s consists of lowercase English letters only.
    1 <= s.length <= 1000
*/

class Solution {
    public String removeVowels(String S) {
        Set<Character> vowels = new HashSet<>();
        
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        
        StringBuilder sb = new StringBuilder();

        for(char ch : S.toCharArray()) {
            if(!vowels.contains(ch)) {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}