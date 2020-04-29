/*
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

Example 1:

Input: [5,7]
Output: 4

Example 2:

Input: [0,1]
Output: 0
*/
class Solution {
    
private:
    /*
    int msb(int n){
        int msb_count = -1;
        while(n){
            n = n>>1;
            msb_count++;
        }
        
        return msb_count;
    }
   */ 
public:
    int rangeBitwiseAnd(int m, int n) {
        
        int res = 0;
        for(int i=30; i>=0; i--){
            if((m & (1<<i)) != (n & (1<<i))){
              break;
            }
            else{
                 res |= (m & (1<<i)); 
            }
            
           
        }
        
        return res;
        /*int res = 0;
        
        while(n != m){
            m >>= 1;
            n >>= 1;
            res++;
        }
        
        return m<<res;
        */
       /* while(m && n){
            
            int m1 = msb(m);
            int n1 = msb(n);
            
            if(n1 != m1){
                break;
            }
            
            int msb_val = (1<<m1);
            res += msb_val;
            
            m -= msb_val;
            n -= msb_val;
            
        }
        
        return res;*/
    }
};