/*
The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. 
So the median is the mean of the two middle values.

For examples, if arr = [2,3,4], the median is 3.
For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
You are given an integer array nums and an integer k. 
There is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.

 

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
Explanation: 
Window position                Median
---------------                -----
[1  3  -1] -3  5  3  6  7        1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7        3
 1  3  -1  -3 [5  3  6] 7        5
 1  3  -1  -3  5 [3  6  7]       6
Example 2:

Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
 

Constraints:

1 <= k <= nums.length <= 10^5
-2^31 <= nums[i] <= 2^31 - 1
*/

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        this.minHeap = new PriorityQueue<>();
        this.maxHeap = new PriorityQueue<>(1, Collections.reverseOrder());
        
        int len = nums.length;
        double[] medians = new double[len - k + 1];
       
        int itr = 0;
        for (int index = 0; index < len; ++index) {
            if (this.maxHeap.isEmpty() || this.maxHeap.peek() >= nums[index]) {
                this.maxHeap.add(nums[index]);
            } else {
                this.minHeap.add(nums[index]);
            }
            
            this.balance();
            if (index - k + 1 >= 0) {
                // System.out.println(itr +" : "+ minHeap + " : " + this.maxHeap);
                if (this.maxHeap.size() == this.minHeap.size()) {
                    double median = (double)(this.maxHeap.peek() * 0.5 + this.minHeap.peek() * 0.5);
                    medians[itr++] =  median;
                } else {
                    medians[itr++] = this.maxHeap.peek();
                }
                
                int remove = nums[index - k + 1];
                
                if (remove <= this.maxHeap.peek()) {
                    this.maxHeap.remove(remove);
                } else {
                   this.minHeap.remove(remove);
                }
                
                this.balance();
            }
        }
        
        return medians;
        
    }
    
    private PriorityQueue<Integer> minHeap, maxHeap;
    
    private void balance() {
        if (this.maxHeap.size() > this.minHeap.size() + 1) {
            this.minHeap.add(this.maxHeap.poll());
        } else if (this.maxHeap.size() < this.minHeap.size()) {
            this.maxHeap.add(this.minHeap.poll());
        }
    }
    
   
}

// Time complexity : O(nlogn)
// Space complexity: O(n)