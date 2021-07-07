/*
Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.

 

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: The substring is "ece" with length 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: The substring is "aa" with length 2.
 

Constraints:

1 <= s.length <= 5 * 104
0 <= k <= 50 
*/


// 7 MS
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
     
        if (Objects.isNull(s) || s.isEmpty() || s.isBlank()) {
           return 0; 
        }
        
        return getMaxLength(s.toCharArray(), k);
    }
    
    
    private int getMaxLength(char[] S, int distinctCharacter) {
        int left = 0, right = 0, maxLength = 0;
        Map<Character, Integer> window = new HashMap<>();
        
        while (right < S.length) {
            window.put(S[right], window.getOrDefault(S[right++], 0) + 1);
            
            while(left < S.length && window.size() > distinctCharacter) {
                window.put(S[left], window.get(S[left]) - 1);
                
                if(window.get(S[left]) == 0) {
                    window.remove(S[left]);
                }
                ++left;
            }
            
            maxLength = Math.max(maxLength, right - left);
        }
        
        
        return maxLength;
    }
}

// 10 MS
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
     
        if (Objects.isNull(s) || s.isEmpty() || s.isBlank()) {
           return 0; 
        }
        
        return getMaxLength(s.toCharArray(), k);
    }
    
    
    private int getMaxLength(char[] S, int distinctCharacter) {
        int left = 0, right = 0, maxLength = 0;
        Map<Character, Integer> window = new HashMap<>();
        
        while (right < S.length) {
            window.put(S[right], right++);
            
            if (left < S.length && window.size() > distinctCharacter) {
                
                int deleteIndex = Collections.min(window.values());
                window.remove(S[deleteIndex]);
                left = deleteIndex + 1;
            }
            
            maxLength = Math.max(maxLength, right - left);
        }
        
        
        return maxLength;
    }
}

// 13 MS
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        if (n * k == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;

        Map<Character, Integer> rightmostPosition = new LinkedHashMap<>();

        int maxLength = 0;

        while (right < n) {
            Character character = s.charAt(right);
            if (rightmostPosition.containsKey(character)) {
                rightmostPosition.remove(character);
            }
            rightmostPosition.put(character, right++);

            if (rightmostPosition.size() == k + 1) {
                Map.Entry<Character, Integer> leftmost =
                    rightmostPosition.entrySet().iterator().next();
                rightmostPosition.remove(leftmost.getKey());
                left = leftmost.getValue() + 1;
            }

            maxLength = Math.max(maxLength, right - left);
        }
        return maxLength;
    }
}
