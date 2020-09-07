/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Example 1:

Input: pattern = "abba", str = "dog cat cat dog"
Output: true
Example 2:

Input:pattern = "abba", str = "dog cat cat fish"
Output: false
Example 3:

Input: pattern = "aaaa", str = "dog cat cat dog"
Output: false
Example 4:

Input: pattern = "abba", str = "dog dog dog dog"
Output: false
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a single space.
*/

class Solution {
public:
    bool wordPattern(string pattern, string str) {
        stringstream s(str);
        string word;
        stringstream ss(str);
        int count = 0;
       
       
        while(ss >> word) ++count;
        
        if(pattern.length() != count) {
            return false;
        }
        int index = 0;
        map<char, string> hash;
        map<string, char> hash1;
        
      
        while(s >> word && index < pattern.length()) {
            
            if(hash.find(pattern[index]) == hash.end() && hash1.find(word) == hash1.end()) {
                  hash[pattern[index]] = word;
                 hash1[word] = pattern[index];
            }
            else {
                if(!isEquals(hash[pattern[index]], word)){
                    return false;
                }
            }
            
            ++index;
        }
        
        
        return true;
    }
    
    bool isEquals(string s1, string s2) {
        if(s1.length() != s2.length()) {
            return false;
        }
        
        for(int index = 0; index < s1.length(); ++index) {
            if(s1[index] != s2[index]) {
                return false;
            }
        }
        
        return true;
    }
};