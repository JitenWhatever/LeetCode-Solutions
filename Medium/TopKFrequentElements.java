/*
Given an integer array nums and an integer k, return the k most frequent elements. 
You may return the answer in any order.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
 

Constraints:

1 <= nums.length <= 10^5
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.
 
Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
*/

class TopKFrequentElements {
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
