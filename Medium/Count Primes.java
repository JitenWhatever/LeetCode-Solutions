/*
Given an integer n, return the number of prime numbers that are strictly less than n.

Example 1:

Input: n = 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
Example 2:

Input: n = 0
Output: 0
Example 3:

Input: n = 1
Output: 0
 

Constraints:

0 <= n <= 5 * 10^6
*/

class Solution {
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        
        this.iSNotPrimes = new boolean[n];
        
        this.sieve(n);
        
        int primes = 0;
        
        for (int num = 2; num < n; ++num) {
            if (!iSNotPrimes[num]) {
                ++primes;
            }
        }
        
        return primes;
    }
    
    private boolean[] iSNotPrimes;
    
    
    private void sieve(int n) {
        for (int num = 2; num*num <= n; ++num) {
            if (!this.iSNotPrimes[num]) {
                for (int mul = num * num; mul < n; mul += num) {
                    this.iSNotPrimes[mul] = true;
                }
            }
        }
    }
    
}