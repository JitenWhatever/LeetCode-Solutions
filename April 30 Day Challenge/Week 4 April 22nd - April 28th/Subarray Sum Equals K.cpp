/*
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:

Input:nums = [1,1,1], k = 2
Output: 2

Note:
    The length of the array is in range [1, 20,000].
    The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
*/

class Solution {
public:
    int subarraySum(vector<int>& nums, int k) {
        
        map<int, int> mp;
        int n = nums.size();
        int sum = 0;
        int count = 0;
        mp[0] = 1;
        for(int i=0;i<n;i++){
             sum += nums[i];
            
            if(mp.find(sum - k) != mp.end()){
                count += mp[sum-k];
            }
            
            mp[sum] += 1;
        }
        
        return count;
    }
};
