/*
Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
*/

class Solution {
    public int mySqrt(int x) {
        
        if(x < 2) {
            return x;
        }
        int low = 2, high = x/2;
        while(low <= high) {
            int mid =  low + (high - low)/2;
            if(mid  > x/mid) {
                high = mid - 1;
            } else if (mid < x/mid){
               low = mid + 1;
            } else {
                return mid;
            }
            
        }
        
        return high;
    }
}

class Solution {
  public int mySqrt(int x) {
    if (x < 2) return x;

    double x0 = x;
    double x1 = (x0 + x / x0) / 2.0;
    while (Math.abs(x0 - x1) >= 1) {
      x0 = x1;
      x1 = (x0 + x / x0) / 2.0;
    }

    return (int)x1;
  }
}