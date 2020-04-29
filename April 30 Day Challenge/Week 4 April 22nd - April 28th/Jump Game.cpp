/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.

 

Constraints:

    1 <= nums.length <= 3 * 10^4
    0 <= nums[i][j] <= 10^5

*/

class Solution {
public:
    bool canJump(vector<int>& nums) {
            int n = nums.size();
                        
            int can_reach = 0;
            bool ans  = false;
            for(int i=0;i<=can_reach; i++){
                if(can_reach >= n-1){
                    ans  = true;
                    break;
                }
                        
                can_reach = max(can_reach, i + nums[i]);
            }
            return ans;
        }
    };