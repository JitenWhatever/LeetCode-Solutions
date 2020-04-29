/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

*/

class Solution {
public:
    int search(vector<int>& nums, int t) {
        
        if(nums.size() == 0) return -1;
        int l = 0, r = nums.size() - 1;
    
        while(l < r){
            int mid = l + (r-l)/2;
            if(nums[mid] > nums[r]){
                l = mid + 1;
            }
            else{
                r = mid;
            }
        }
        cout<<l<<" ";
        
        int pivot  = l;
        l = 0, r = nums.size() - 1;
        if(nums[pivot] <= t && t <= nums[r]){
            l = pivot;
        }
        else{
            r = pivot;
        }
        
        while(l <= r){
            int mid =  l + (r - l)/2;
            if(nums[mid] == t){
                return mid;
            }
            else if(nums[mid] < t){
                l = mid + 1;
            }
            else{
                r = mid - 1;
            }
        }
        return -1;
    }
};