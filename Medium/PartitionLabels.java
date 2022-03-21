import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.

Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.

Return a list of integers representing the size of these parts.

 

Example 1:

Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
Example 2:

Input: s = "eccbbbbdec"
Output: [10]
 

Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
*/

class PartitionLabels {
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