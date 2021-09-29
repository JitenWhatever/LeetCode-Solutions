/*
In a town, there are n people labeled from 1 to n. 
There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:

The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.
You are given an array trust where trust[i] = [ai, bi] 
representing that the person labeled ai trusts the person labeled bi.

Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.

 

Example 1:

Input: n = 2, trust = [[1,2]]
Output: 2
Example 2:

Input: n = 3, trust = [[1,3],[2,3]]
Output: 3
Example 3:

Input: n = 3, trust = [[1,3],[2,3],[3,1]]
Output: -1
Example 4:

Input: n = 3, trust = [[1,2],[2,3]]
Output: -1
Example 5:

Input: n = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
Output: 3
 

Constraints:

1 <= n <= 1000
0 <= trust.length <= 10^4
trust[i].length == 2
All the pairs of trust are unique.
ai != bi
1 <= ai, bi <= n
*/

import java.util.*;

class Solution {
    public int findJudge(int N, int[][] trust) {
        int[] hash = new int[N + 1];
        Set<Integer> cannotBeJudge = new HashSet<Integer>();
        
        for(int index = 0; index < trust.length; ++index) {
            hash[trust[index][1]]++;
            cannotBeJudge.add(trust[index][0]);
        }
        
        for(int index = 1; index <=N; ++index) {
            if(hash[index] == N - 1 && !cannotBeJudge.contains(index)){
                return index;
            }
        }
        return -1;
    }
}


public int findJudge(int N, int[][] trust) {
    
    if (trust.length < N - 1) {
        return -1;
    }
    
    int[] indegrees = new int[N + 1];
    int[] outdegrees = new int[N + 1];

    for (int[] relation : trust) {
        outdegrees[relation[0]]++;
        indegrees[relation[1]]++; 
    }

    for (int i = 1; i <= N; i++) {
        if (indegrees[i] == N - 1 && outdegrees[i] == 0) {
            return i;
        }
    }
    return -1;
}

public int findJudge(int N, int[][] trust) {
        
    if (trust.length < N - 1) {
        return -1;
    }
    
    int[] trustScores = new int[N + 1];

    for (int[] relation : trust) {
        trustScores[relation[0]]--;
        trustScores[relation[1]]++; 
    }
    
    for (int i = 1; i <= N; i++) {
        if (trustScores[i] == N - 1) {
            return i;
        }
    }
    return -1;
}