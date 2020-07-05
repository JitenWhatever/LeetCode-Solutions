/*
You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. 
Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. 
If it does not exist, output -1 for this number.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.

Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.

Note:
All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.
*/

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        
        int[] result = new int[nums1.length];
        
        Map<Integer, Integer> hash = new HashMap<>();
        Stack<Integer> st = new Stack();
        
        for(int index = nums2.length - 1; index >= 0; --index) {
            while(!st.empty() && st.peek() <= nums2[index]) {
                st.pop();
            }
            
            if(st.empty()) {
                hash.put(nums2[index], -1);
            }
            else {
                hash.put(nums2[index], st.peek());
            }
            
            st.push(nums2[index]);  
        }
        
        
        for(int index = 0; index < nums1.length; ++index) {
            result[index] = hash.get(nums1[index]);
        }
      
        return result;
        
    }
}