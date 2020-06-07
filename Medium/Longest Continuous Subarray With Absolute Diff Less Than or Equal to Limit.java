/*
Given an array of integers nums and an integer limit, 
return the size of the longest non-empty subarray 
such that the absolute difference between any two elements of this subarray is less than or equal to limit.

Example 1:

Input: nums = [8,2,4,7], limit = 4
Output: 2 
Explanation: All subarrays are: 
[8] with maximum absolute diff |8-8| = 0 <= 4.
[8,2] with maximum absolute diff |8-2| = 6 > 4. 
[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
[2] with maximum absolute diff |2-2| = 0 <= 4.
[2,4] with maximum absolute diff |2-4| = 2 <= 4.
[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
[4] with maximum absolute diff |4-4| = 0 <= 4.
[4,7] with maximum absolute diff |4-7| = 3 <= 4.
[7] with maximum absolute diff |7-7| = 0 <= 4. 
Therefore, the size of the longest subarray is 2.

Example 2:

Input: nums = [10,1,2,4,7,2], limit = 5
Output: 4 
Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.

Example 3:

Input: nums = [4,2,2,2,4,4,2,2], limit = 0
Output: 3

Constraints:
    1 <= nums.length <= 10^5
    1 <= nums[i] <= 10^9
    0 <= limit <= 10^9

*/
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        
        int left = 0, right = 0, window = 0, min = 0, max = 0;
        LinkedList<Integer> minQueue = new LinkedList<Integer>();
        LinkedList<Integer> maxQueue = new LinkedList<Integer>();
        while(right < nums.length) {
            int currentElement = nums[right];
            
            while(!minQueue.isEmpty() && nums[minQueue.peekLast()] >= currentElement) {
                minQueue.pollLast();
            }
            
            minQueue.offer(right);
            
             while(!maxQueue.isEmpty() && nums[maxQueue.peekLast()] <= currentElement) {
                maxQueue.pollLast();
            }
            
            maxQueue.offer(right);
            
            min = nums[minQueue.peekFirst()];
            max = nums[maxQueue.peekFirst()];
            
            if(max - min > limit) {
                ++left;
                if(left > minQueue.peekFirst()){
                    minQueue.pollFirst();
                }
                 if(left > maxQueue.peekFirst()){
                    maxQueue.pollFirst();
                }
            }
            else {
                window = Math.max(window, right - left + 1);
                ++right;
            }
          
        }
        
        return window;
    }
}