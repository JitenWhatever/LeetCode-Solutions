/*
Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"
Note: The result may be very large, so you need to return a string instead of an integer.
*/

class Solution {
public:
    string largestNumber(vector<int>& nums) {
        
        sort(nums.begin(), nums.end(), [](int a, int b){
           string ab = to_string(a) + to_string(b);
            string ba = to_string(b) + to_string(a);
            return ab > ba;
        });
        
        string result = "";
        for(int num : nums) {
            result += to_string(num);
        }
        if(result[0] == '0') {
            return "0";
        }
        return result;
    }
};
