/*
Given a non-empty array of unique positive integers A, consider the following graph:

There are A.length nodes, labelled A[0] to A[A.length - 1];
There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
Return the size of the largest connected component in the graph.

 

Example 1:
https://assets.leetcode.com/uploads/2018/12/01/ex1.png

Input: [4,6,15,35]
Output: 4

Example 2:
https://assets.leetcode.com/uploads/2018/12/01/ex2.png

Input: [20,50,9,63]
Output: 2

Example 3:
https://assets.leetcode.com/uploads/2018/12/01/ex3.png

Input: [2,3,6,7,4,12,21,39]
Output: 8

Note:

1 <= A.length <= 20000
1 <= A[i] <= 100000
*/

class Solution {
public:
    
    int largestComponentSize(vector<int>& A) {
     
        int MAX = *max_element(A.begin(), A.end());
        dsu = vector<int> (MAX + 1);
        
        cout<<MAX;
        for(int index = 0; index <= MAX; index++) {
            dsu[index] = index;
        }
        
        for(int a : A) {
            for(int num = 2; num*num <= a; ++num) {
                if(a%num == 0) {
                    merge(a, num);
                    merge(a, a/num);
                }
            }
        }
        
        int maxLen = 1;
        map<int, int> hash;
        
        for(int a : A) {
            int root = find(a);
            hash[root]++;
            maxLen = max(maxLen, hash[root]);
        }
        
        return maxLen;
    }
    
    vector<int> dsu;
    
    int find(int u) {
        if(u != dsu[u]) {
            dsu[u] = find(dsu[u]);
        }
        
        return dsu[u];
    }
    
    void merge(int u, int v) {
        dsu[find(u)]= dsu[find(v)];
    }
};