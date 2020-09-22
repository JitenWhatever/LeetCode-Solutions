/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]
Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]
*/

class Solution {
public:
    vector<int> majorityElement(vector<int>& nums) {
        int num1 = 0, num2 = 0;
        int index1 = 0, index2 = 0;
        
        for(int index = 0; index < nums.size(); ++index) {
            if(nums[index1] == nums[index]) {
                ++num1;
            }
            else if(nums[index2] == nums[index]) {
                ++num2;
            }
            else if(num1 <= 0) {
                num1 = 1;
                index1 = index;;
            }
            else if(num2 <= 0) {
                num2 = 1;
                index2 = index;
            }
            else {
                --num1;
                --num2;
            }
        }
        
        vector<int> result;
        num1 = 0;
        num2 = 0;
        for(int num : nums) {
            if(nums[index1] == num) {
                ++num1;
            }
            else if(nums[index2] == num) {
                ++num2;
            }
        }
        
        int n = nums.size();
        n /= 3;
        if(num1 > n) {
            result.push_back(nums[index1]);
        }
         if(num2 > n) {
            result.push_back(nums[index2]);
        }
        
        
        return result;
    }
};