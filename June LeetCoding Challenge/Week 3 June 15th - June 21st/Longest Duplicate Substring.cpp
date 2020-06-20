/*
Given a string S, consider all duplicated substrings: (contiguous) substrings of S that occur 2 or more times.  (The occurrences may overlap.)

Return any duplicated substring that has the longest possible length.  (If S does not have a duplicated substring, the answer is "".)

Example 1:

Input: "banana"
Output: "ana"

Example 2:

Input: "abcd"
Output: ""

Note:
    2 <= S.length <= 10^5
    S consists of lowercase English letters.
*/

class Solution {


private:
   
    int prime = 10000007;
    vector<int> power;
    
    string rabinKarp(string s, int len) {
        if(len == 0)    return "";
        int n = s.size(), curr = 0;
        
        unordered_map<int, vector<int>> hash;
        
        for(int i = 0; i < len; i++) {
            curr = (curr * 26 + (s[i]-'a')) % prime;
        }
        hash[curr] = {0};  
        for(int i = len; i < n; i++) {
            
            curr = ((curr - power[len-1] * (s[i-len]-'a')) % prime + prime) % prime;
            curr = (curr * 26 + (s[i]-'a')) % prime;
            
           
            if(hash.find(curr) == hash.end())   hash[curr] = {i-len+1};
           
            else {
                for(int index: hash[curr]) {
                    if(s.substr(index, len) == s.substr(i-len+1, len))  return s.substr(index, len);
                }

                hash[curr].push_back({i-len+1});
            }
        }
        return ""; 
    }
    
public:
    string longestDupSubstring(string s) {
        int n = s.size(), low = 0, high = n-1;
    
        power = vector<int>(n);
        for(int i = 0; i < n; i++)  power[i] = (i == 0) ? 1 : (power[i-1] * 26) % prime;
  
        string res;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            string dup = rabinKarp(s, mid);
            
            if((int)dup.size() > (int)res.size()) {
                res = dup;
                low = mid+1;
            } 
            else {
               high = mid-1; 
            } 
        }
        return res;
    }
    
};