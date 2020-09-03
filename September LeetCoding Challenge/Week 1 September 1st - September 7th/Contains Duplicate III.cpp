/*
Given an array of integers, find out whether there are two distinct indices i and j in the array 
such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3, t = 0
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1, t = 2
Output: true
Example 3:

Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false
*/


#define ll long long
class Solution {
public:
    bool containsNearbyAlmostDuplicate(vector<int>& nums, int k, int t) {
        set<ll> st;
        int left = 0, right = 0;
        
        while(right < nums.size()) {
            
            auto itr = st.lower_bound((ll)nums[right] - t);
            if(itr != st.end() && *itr <= (ll)nums[right] + t){
                return true;
            }
            
            st.insert(nums[right++]);
            if(left < nums.size() && st.size() > k) {
                st.erase(nums[left++]);
            }
        }
        
        return false;
    }
};