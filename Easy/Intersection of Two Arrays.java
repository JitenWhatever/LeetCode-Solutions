/*
Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Note:

Each element in the result must be unique.
The result can be in any order.
*/

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1 == null) {
            return nums2;
        }
        if(nums2 == null) {
            return nums1;
        }
        
        if(nums1.length > nums2.length) {
            return intersection(nums2, nums1);
        }
        
        List<Integer> result = new ArrayList<>();
        
        for(int index = 0; index < nums1.length; ++index) {
            if(found(nums2, nums1[index]) && !result.contains(nums1[index])) {
                result.add(nums1[index]);
            }
        }
        
        int[] intersection = new int[result.size()];
        for(int index = 0; index < result.size(); ++index) {
            intersection[index] = result.get(index);
        }
        return intersection;
    
    }
    
    private boolean found(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        Arrays.sort(nums);
        while(low <= high) {
            int mid = low + (high - low)/2;
            
            if(nums[mid] == target) {
                return true;
            }
            else if(nums[mid] > target) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        
        return false;
    }
}