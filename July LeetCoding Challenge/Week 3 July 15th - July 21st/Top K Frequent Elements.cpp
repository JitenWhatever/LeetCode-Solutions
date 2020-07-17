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
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int, int> hash;
        
        int max_count = 0;
        for(int num : nums) {
            hash[num]++;
            
            max_count = max(max_count, hash[num]);
        }
        
      
        
        vector<int> room[max_count + 1];
         
        for(auto pair : hash) {
            room[pair.second].push_back(pair.first);
        }

        vector<int> result;
        for(int index = max_count; index >= 0 && k > 0; --index) {
            
            if(room[index].size() != 0) {
                for(int num : room[index]) {
                    result.push_back(num);
                }
                
                k -= room[index].size();
            }
        }
        
        return result;
    }
};