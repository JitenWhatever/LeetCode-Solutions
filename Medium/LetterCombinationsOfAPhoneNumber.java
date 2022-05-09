import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. 
Return the answer in any order.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/200px-Telephone-keypad2.svg.png

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = ""
Output: []
Example 3:

Input: digits = "2"
Output: ["a","b","c"]
 

Constraints:

0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
*/


public class LetterCombinationsOfAPhoneNumber  {
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

