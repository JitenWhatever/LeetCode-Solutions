/*
Given a string array words, 
return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. 
If no such two words exist, return 0.

Example 1:

Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16
Explanation: The two words can be "abcw", "xtfn".
Example 2:

Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4
Explanation: The two words can be "ab", "cd".
Example 3:

Input: words = ["a","aa","aaa","aaaa"]
Output: 0
Explanation: No such pair of words.
 

Constraints:

2 <= words.length <= 1000
1 <= words[i].length <= 1000
words[i] consists only of lowercase English letters.
*/
public class MaximumProductOfWordLengths {
    
    public boolean noCommonLetters(String s1, String s2){
        for (char ch : s1.toCharArray())
        if (s2.indexOf(ch) != -1) return false;
        return true;
    }
    
    public int maxProduct(String[] words) {
        int n = words.length;

        int maxProd = 0;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j < n; ++j)
                if (noCommonLetters(words[i], words[j]))
                    maxProd = Math.max(maxProd, words[i].length() * words[j].length());

        return maxProd;
    }

    public int bitNumber(char ch) {
        return (int)ch - (int)'a';
    }
    
    public int maxProduct1(String[] words) {
        int n = words.length;
        int[] masks = new int[n];
        int[] lens = new int[n];
    
        int bitmask = 0;
        for (int i = 0; i < n; ++i) {
            bitmask = 0;
            for (char ch : words[i].toCharArray()) {
                // add bit number bit_number in bitmask
                bitmask |= 1 << bitNumber(ch);
            }
            masks[i] = bitmask;
            lens[i] = words[i].length();
        }
    
        int maxVal = 0;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j < n; ++j)
                if ((masks[i] & masks[j]) == 0)
                    maxVal = Math.max(maxVal, lens[i] * lens[j]);
    
        return maxVal;
    }
  
    public int maxProduct2(String[] words) {
        Map<Integer, Integer> hashmap = new HashMap();
    
        int bitmask = 0, bitNum = 0;
        for (String word : words) {
            bitmask = 0;
            for (char ch : word.toCharArray()) {
                // add bit number bitNumber in bitmask
                bitmask |= 1 << bitNumber(ch);
            }
            // there could be different words with the same bitmask
            // ex. ab and aabb
            hashmap.put(bitmask, Math.max(hashmap.getOrDefault(bitmask, 0), word.length()));
        }
    
        int maxProd = 0;
        for (int x : hashmap.keySet())
            for (int y : hashmap.keySet())
                if ((x & y) == 0) maxProd = Math.max(maxProd, hashmap.get(x) * hashmap.get(y));
    
        return maxProd;
    }
}