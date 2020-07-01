/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
*/
// https://www.youtube.com/watch?v=LPFhl65R7ww

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int low = 0, high = nums1.length, m = nums1.length, n = nums2.length;
        
        while(low <= high) {
            int partX = (low + high)/2;
            int partY = (m + n + 1)/2 - partX;
            
            int maxLeftX = (partX == 0) ? Integer.MIN_VALUE : nums1[partX - 1];
            int minRightX = (partX == m) ? Integer.MAX_VALUE : nums1[partX];
            
            int maxLeftY = (partY == 0) ? Integer.MIN_VALUE : nums2[partY - 1];
            int minRightY = (partY == n) ? Integer.MAX_VALUE : nums2[partY];
            
            if(maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if((m + n)%2 == 0) {
                    return ((double)Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY))/2;
                }
                else {
                    return (double)Math.max(maxLeftX, maxLeftY);
                }
            }
            else if(maxLeftX > minRightY) {
                high = partX - 1;
            }
            else {
                low = partX + 1;
            }
        }
                            
        return -1;
    }
}