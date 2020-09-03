/*
Given an array of 4 digits, return the largest 24 hour time that can be made.

The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.

Return the answer as a string of length 5.  If no valid time can be made, return an empty string.

 

Example 1:

Input: [1,2,3,4]
Output: "23:41"
Example 2:

Input: [5,5,5,5]
Output: ""
 

Note:

A.length == 4
0 <= A[i] <= 9
*/

class Solution {
public:
    string largestTimeFromDigits(vector<int>& A) {
        string ans="";
        string res="";
        
        for(int digit : A) {
            res += to_string(digit);
        }
        
        sort(res.begin(),res.end());
       
        do {
            string sub1=res.substr(0,2);
            string sub2=res.substr(2);
            
            int f=stoi(sub1);
            int sec=stoi(sub2);
            
            if(f >= 0 && f <= 23 && sec>=0 && sec <= 59){
                ans=max(ans, sub1 + ":" + sub2);
            }
           
        }while(next_permutation(res.begin(), res.end()));
        
        return ans;
    }
};