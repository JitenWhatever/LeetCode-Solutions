/*
Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:

Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Example 2:

Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
*/

class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {
        vector<int> newDigits;
        
        int carry = 1;
        int sum = 0;
        
        for(int index = digits.size() - 1; index >= 0; --index) {
            sum = digits[index] + carry;
            
            carry = sum / 10;
            sum %= 10;
            // cout<<sum;
            newDigits.push_back(sum);
        }
        
        if(carry > 0) {
            newDigits.push_back(carry);
        }
        
        reverse(newDigits.begin(), newDigits.end());
        
        return newDigits;
    }
};