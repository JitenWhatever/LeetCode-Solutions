/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/200px-Telephone-keypad2.svg.png

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.
*/


class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        
        if(digits == null || digits.length() == 0) {
            return result;
        }
        
        mapping = Arrays.asList("0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz");
        
        generate(0, "", digits, result);
        
        return result;
    
    }
    
    private List<String> mapping = null;

    private void generate(int currentIndex, String word, String digits, List<String> result) {
        if(currentIndex == digits.length()) {
            result.add(word);
            
            return ;
        }
        
        String str = mapping.get(digits.charAt(currentIndex) - '0');
        
        for(int index = 0; index < str.length(); ++index) {
            generate(currentIndex + 1, word + str.charAt(index), digits , result);
        }
    }
}

