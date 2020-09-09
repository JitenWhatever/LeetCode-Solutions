/*
A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, 
and return a list of integers representing the size of these parts.

 

Example 1:

Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 

Note:

S will have length in range [1, 500].
S will consist of lowercase English letters ('a' to 'z') only.
*/

class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        
        if(S == null || S.length() == 0) {
            return result;
        }
        
        HashMap<Character, Integer> hash = new HashMap<>();
        
        int left = 0;
        for(char ch : S.toCharArray()) {
            hash.put(ch, left++);
        }
        
        left = 0;
        int right = 0;
        while(left < S.length()) {
            int end = hash.get(S.charAt(left));
            right = left;
            while(right != end) {
                end = Math.max(end, hash.get(S.charAt(right++)));
            }
            
            result.add(right - left + 1);
            left = right + 1;
        }
        
        return result;
    }
}