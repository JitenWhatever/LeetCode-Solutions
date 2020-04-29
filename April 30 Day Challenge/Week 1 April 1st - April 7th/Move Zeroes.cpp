/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]

Note:

    1. You must do this in-place without making a copy of the array.
    2. Minimize the total number of operations.

*/

class Solution {
public:
    void moveZeroes(vector<int>& nums) {
        
        int k = 0;
        int countZero = 0;
        for(int x : nums){
            if(x == 0){
                countZero++;
            }
            else{
                nums[k++] = x;
            }
        }
        
        while(countZero--){
            nums[k++] =0;
        }
            
    }
};