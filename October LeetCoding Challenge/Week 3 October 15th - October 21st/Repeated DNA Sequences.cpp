/*
All DNA is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T', for example: "ACGAATTCCG". 
When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

 

Example 1:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC","CCCCCAAAAA"]
Example 2:

Input: s = "AAAAAAAAAAAAA"
Output: ["AAAAAAAAAA"]
 

Constraints:

0 <= s.length <= 105
s[i] is 'A', 'C', 'G', or 'T'.
*/

class Solution {
public:
    vector<string> findRepeatedDnaSequences(string s) {
	    vector<string> result;
	    if(s.length() <= 10) {
            return result;
        }
        
        unordered_map<string,int> seen;
    
        for(int index = 0; index <= s.length() - 10; ++index) {
            seen[s.substr(index, 10)]++;
        }

        for(auto itr : seen) {
            if(itr.second > 1) {
                result.push_back(itr.first);
            }
        }
    
    return result;
    }
};

