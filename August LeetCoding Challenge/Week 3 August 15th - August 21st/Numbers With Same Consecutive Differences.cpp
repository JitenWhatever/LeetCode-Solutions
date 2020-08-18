/*
Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.

Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.

You may return the answer in any order.

 

Example 1:

Input: N = 3, K = 7
Output: [181,292,707,818,929]
Explanation: Note that 070 is not a valid number, because it has leading zeroes.
Example 2:

Input: N = 2, K = 1
Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 

Note:

1 <= N <= 9
0 <= K <= 9
*/

class Solution {

private:
    vector<int> result;
    void helper(int len, int currNum, int prevNum, int totalDigits, int diff) {
        if(len == totalDigits - 1) {
            result.push_back(currNum);
            return;
        }
        
        int nextNum = diff + prevNum;
        
        if(nextNum < 10) {
            helper(len + 1, currNum*10 + nextNum, nextNum, totalDigits, diff);
        }
        
        nextNum = prevNum - diff;
        
        if(diff != 0 && nextNum >= 0) {
            helper(len + 1, currNum * 10 + nextNum, nextNum, totalDigits, diff);
        }
        
    }
public:
    vector<int> numsSameConsecDiff(int N, int K) {
        if(N == 1) {
            return {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        }
        
        for(int num = 1; num <= 9; num++) {
            helper(0, num, num, N, K);
        }
        
        return result;
    }
};