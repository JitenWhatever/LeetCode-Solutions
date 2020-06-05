/*
Given an array w of positive integers, where w[i] describes the weight of index i, 
write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

    1 <= w.length <= 10000
    1 <= w[i] <= 10^5
    pickIndex will be called at most 10000 times.

Example 1:

Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]

Example 2:

Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]

Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. 
Solution's constructor has one argument, the array w. pickIndex has no arguments. 
Arguments are always wrapped with a list, even if there aren't any.
*/

class Solution {
    
private:
    vector<int> prefixSum;
public:
    Solution(vector<int>& w) {
        prefixSum = vector<int>(w.size());
        prefixSum[0] = w[0];
        for(int index = 1; index < w.size(); ++index) {
           prefixSum[index] = prefixSum[index - 1] + w[index];
        }
    }
    
    int pickIndex() {
        int totalWeight =  prefixSum[prefixSum.size() - 1];
        // cout<<totalWeight;
        int randomWeight = rand()%totalWeight + 1 ;
        
        int low = 0, high = prefixSum.size() - 1;
        
        while(low < high) {
            int mid = low + (high - low)/2;
            
            if(prefixSum[mid] < randomWeight) {
               low = mid + 1;
            }
            else {
                 high = mid;   
            }
        }
       /* auto itr = upper_bound(prefixSum.begin(), prefixSum.end(), randomWeight) ;
         return itr - prefixSum.begin();
        */
        
        return low;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(w);
 * int param_1 = obj->pickIndex();
 */