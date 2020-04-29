/*
Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note:
    All inputs will be in lowercase.
    The order of your output does not matter.
*/

class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        vector<vector<string>> res ;
        map<string, vector<string>> hash;
        for(int i = 0; i<strs.size();i++){
            string k = strs[i];
            sort(k.begin(), k.end());
            if(hash.find(k) == hash.end()){
                hash[k] = vector<string>(1, strs[i]);
            }
            else{
                hash[k].push_back(strs[i]);
            }
        }
        for(auto it=hash.begin(); it!=hash.end(); it++){
            res.push_back(it->second);
        }
        return res;
    }
    
};
