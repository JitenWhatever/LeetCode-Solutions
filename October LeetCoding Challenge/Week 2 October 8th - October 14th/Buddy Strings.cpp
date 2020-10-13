/*
Given two strings A and B of lowercase letters, return true if you can swap two letters in A so the result is equal to B, otherwise, return false.

Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at A[i] and A[j]. 
For example, swapping at indices 0 and 2 in "abcd" results in "cbad".

 

Example 1:

Input: A = "ab", B = "ba"
Output: true
Explanation: You can swap A[0] = 'a' and A[1] = 'b' to get "ba", which is equal to B.
Example 2:

Input: A = "ab", B = "ab"
Output: false
Explanation: The only letters you can swap are A[0] = 'a' and A[1] = 'b', which results in "ba" != B.
Example 3:

Input: A = "aa", B = "aa"
Output: true
Explanation: You can swap A[0] = 'a' and A[1] = 'a' to get "aa", which is equal to B.
Example 4:

Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true
Example 5:

Input: A = "", B = "aa"
Output: false
 

Constraints:

0 <= A.length <= 20000
0 <= B.length <= 20000
A and B consist of lowercase letters.
*/

class Solution {

private:
    bool equals(string a, string b) {
        int index = 0;
        while(index < a.length()) {
            if(a[index] != b[index]) {
                return false;
            }
            
            ++index;
        }
        return true;
    }
public:
    bool buddyStrings(string A, string B) {
        if(A.length() != B.length()) {
            return false;
        }
        
        if(equals(A, B)) {
            set<char> seen;
            for(char ch : A) {
                if(seen.count(ch)) {
                   return true; 
                }
                seen.insert(ch);
            }
        }
        else {
            vector<int> diff;
            for(int index = 0; index < A.length(); ++index) {
                if(A[index] != B[index]) {
                    diff.push_back(index);
                }
                
                if(diff.size() > 2) {
                    return false;
                }
            }
            
            return diff.size() == 2 && A[diff[0]] == B[diff[1]] && A[diff[1]] == B[diff[0]];
        }
        
        return false;
    }
};