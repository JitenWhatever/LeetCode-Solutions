/*
Given a positive integer num, write a function which returns True if num is a perfect square else False.
Note: Do not use any built-in library function such as sqrt.

Example 1:
Input: 16
Output: true

Example 2:
Input: 14
Output: false
*/

class Solution {
public:
    bool isPerfectSquare(int num) {
        
        if(num == 1) return true;
        int low = 1, high = num/2;
        
        while(low <= high){
            double mid = low + (high - low)/2; // int create issue for odd numbers
            // auto sqr = (long long int)mid*mid; // to avoid overflow 
            if(mid == num/mid){
                return true;
            }
            else if(mid < num/mid){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        
        return false;
    }
};