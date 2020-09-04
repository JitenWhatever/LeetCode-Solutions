/*
A string S of lowercase English letters is given. 
We want to partition this string into as many parts as possible so that each letter appears in at most one part, 
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
public:
    vector<int> partitionLabels(string S) {
        vector<int> hash(26, 0);
        
        int left = 0;
        for(char ch : S) {
            hash[ch - 'a'] = left++;
        }
        
        left = 0;
        
        vector<int> result;
        
        while(left < S.length()) {
            int right = left;
            int end = hash[S[left] - 'a'];
            
            while(right != end) {
                end = max(end, hash[S[right++] - 'a']);
            }
                
            result.push_back(right - left + 1);
            
            left = right + 1;
        }
        
        return result;
    }
};