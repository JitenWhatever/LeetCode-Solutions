/*
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]

Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
*/

class Solution {
public:
    vector<int> productExceptSelf(vector<int>& nums) {
        // int p = totalProduct(nums);
        vector<int> sumleft(nums.size());
        sumleft[0] = 1;
        
        for(int i=1; i<nums.size();i++){
            sumleft[i] = nums[i-1]*sumleft[i-1];
            // cout<<sumleft[i]<<" ";
        }
        
        int sumright = 1;
        int n = nums.size();
        for(int i=n-1; i>=0; i--){
            sumleft[i] *= sumright; 
            sumright *= nums[i];
           // cout<<sumleft[i]<<" ";
        }
        
        return sumleft;
        
    }
    
//     int totalProduct(vector<int> nums){
        
//         int ans = 1;
//         int n = nums.size();
//         for(int x: nums){
//             ans *= x;
//         }
        
//         return ans;
        
//     }
    
//     int divide(int a, int b){
//         long n = a, m = b;
//         int sign = (n < 0 ^ m < 0)? -1:1;
//         long long res = 0;
//         n = abs(n);
//         m = abs(m);
        
//         for(long t = 0, i = 31; i>=0 ; --i){
//             if(t + (m<<i) <= n){
//                 t += m<<i, res |= 1LL << i;   
//             }
            
//             // cout<<t<<" "<<res<<" ";
//         }
        
//         if(sign < 0){
//             res = -res; 
//         }
        
//         return (res >= INT_MAX || res < INT_MIN)? 0:res;
//     }
    
};