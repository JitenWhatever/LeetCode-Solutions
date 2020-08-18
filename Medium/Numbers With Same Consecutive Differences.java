/*
Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.

Note that every number in the answer must not have leading zeros except for the number 0 itself. 
For example, 01 has one leading zero and is invalid, but 0 is valid.

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
    
    public int[] numsSameConsecDiff(int N, int K) {
        if(N == 1) {
            return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        }
        
        List<Integer> result = new ArrayList<>();
        
        for(int num = 1; num <= 9; num++) {
            dfs(0, N, K, num, num, result);
        }
        
        int[] ans = new int[result.size()];
        for(int index = 0; index < result.size(); ++index) {
            ans[index] = result.get(index);
        }
        
        return ans;
    }
    
    private void dfs(int len, int N, int K, int currNum, int prevNum, List<Integer> result){
        if(len == N - 1) {
            result.add(currNum);
            return;
        }
        
        int nextNum = K + prevNum;
        
        if(nextNum < 10) {
            dfs(len + 1, N, K, currNum * 10 + nextNum, nextNum, result);
        }
        
        nextNum = prevNum - K;
        
        if(K != 0 && nextNum >= 0) {
            dfs(len + 1, N, K, currNum * 10 + nextNum, nextNum, result);
        }
    }
}