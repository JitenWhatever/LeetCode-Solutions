/*
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:

Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.

Example 2:

Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

Note: The length of the given binary array will not exceed 50,000. 
*/

class Solution {
public:
    int findMaxLength(vector<int>& nums) {
        unordered_map<int,int>mp;
        mp[0] = -1;
        int maxLen = 0;
        int sum = 0;    
        for(int i=0;i<nums.size();i++){
            sum += (nums[i]==0) ? -1 : 1;
            
            if(mp.find(sum) != mp.end()){
                if(maxLen < i - mp[sum]){
                    maxLen = i - mp[sum];
                }
            }
            else{
                mp[sum] = i;
             }
        }
        
        return maxLen;
        
    }
};