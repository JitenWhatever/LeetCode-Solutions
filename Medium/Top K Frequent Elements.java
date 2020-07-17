/*
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
You can return the answer in any order.

*/

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        int max_count = 0;
        HashMap<Integer, Integer> hash =  new HashMap<>();
        
        for(int num : nums) {
            int count = hash.getOrDefault(num, 0) + 1;
            
            hash.put(num, count);
            
            max_count = Math.max(max_count, count);
        }
        
        List<Integer>[] rooms = new List[max_count + 1];
        
        
        for(int key : hash.keySet()) {
            int count = hash.get(key);
            
            if(rooms[count] == null) {
                rooms[count] = new ArrayList();
            }
            rooms[count].add(key);
        }
        
        int[] result = new int[k];
        int itr = 0;
        for(int index = max_count; index >= 0 &&  k > 0; --index) {
            if(rooms[index] != null) {
                for(int num : rooms[index]) {
                    result[itr++] = num;
                }
                
                k -= rooms[index].size();
            }
        }
    
    return result;
    }
}
