/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]

*/

class Solution {
private:
    vector<vector<int>> result;
public:
    vector<vector<int>> combinationSum3(int k, int n) {
        vector<int> current;
        
        generate(k, 1, n, current);
        
        return result;
    }
    
    void generate(int k, int index, int target, vector<int> current) {
        if(target == 0 && current.size() == k) {
            result.push_back(current);
            return ;
        }
        
        if(target < 0 || current.size() == k) {
            return ;
        }
        
        for(int i = index; i <= 9; ++i) {
            current.push_back(i);
            generate(k, i + 1,  target - i, current);
            current.erase(current.end() - 1);
        }
    }
};