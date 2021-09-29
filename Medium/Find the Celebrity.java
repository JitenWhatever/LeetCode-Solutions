/*
Suppose you are at a party with n people (labeled from 0 to n - 1), and among them, there may exist one celebrity. 
The definition of a celebrity is that all the other n - 1 people know him/her, but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. 
The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" 
to get information about whether A knows B. 
You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. 
Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party. 
Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

 

Example 1:
https://assets.leetcode.com/uploads/2019/02/02/277_example_1_bold.PNG

Input: graph = [[1,1,0],[0,1,0],[1,1,1]]
Output: 1
Explanation: There are three persons labeled with 0, 1 and 2. 
graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j. 
The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
Example 2:
https://assets.leetcode.com/uploads/2019/02/02/277_example_2.PNG

Input: graph = [[1,0,1],[1,1,0],[0,1,1]]
Output: -1
Explanation: There is no celebrity.
 

Constraints:

n == graph.length
n == graph[i].length
2 <= n <= 100
graph[i][j] is 0 or 1.
graph[i][i] == 1
 

Follow up: If the maximum number of allowed calls to the API knows is 3 * n, 
could you find a solution without exceeding the maximum number of calls?
*/

public class Solution extends Relation {
    
    private int numberOfPeople;
    
    public int findCelebrity(int n) {
        numberOfPeople = n;
        for (int i = 0; i < n; i++) {
            if (isCelebrity(i)) {
                return i;
            }
        }
        return -1;
    }
    
    private boolean isCelebrity(int i) {
        for (int j = 0; j < numberOfPeople; j++) {
            if (i == j) continue; // Don't ask if they know themselves.
            if (knows(i, j) || !knows(j, i)) {
                return false;
            }
        }
        return true;
    }
}

public class Solution extends Relation {
    
    private int numberOfPeople;
    
    public int findCelebrity(int n) {
        numberOfPeople = n;
        int celebrityCandidate = 0;
        for (int i = 0; i < n; i++) {
            if (knows(celebrityCandidate, i)) {
                celebrityCandidate = i;
            }
        }
        if (isCelebrity(celebrityCandidate)) {
            return celebrityCandidate;
        }
        return -1;
    }
    
    private boolean isCelebrity(int i) {
        for (int j = 0; j < numberOfPeople; j++) {
            if (i == j) continue; // Don't ask if they know themselves.
            if (knows(i, j) || !knows(j, i)) {
                return false;
            }
        }
        return true;
    }
}

public class Solution extends Relation {
    
    private int numberOfPeople;
    private Map<Pair<Integer, Integer>, Boolean> cache = new HashMap<>(); 
    
    @Override
    public boolean knows(int a, int b) {
        if (!cache.containsKey(new Pair(a, b))) {
            cache.put(new Pair(a, b), super.knows(a, b));
        }
        return cache.get(new Pair(a, b));
    }
    
    public int findCelebrity(int n) {
        numberOfPeople = n;
        int celebrityCandidate = 0;
        for (int i = 0; i < n; i++) {
            if (knows(celebrityCandidate, i)) {
                celebrityCandidate = i;
            }
        }
        if (isCelebrity(celebrityCandidate)) {
            return celebrityCandidate;
        }
        return -1;
    }
    
    private boolean isCelebrity(int i) {
        for (int j = 0; j < numberOfPeople; j++) {
            if (i == j) continue; // Don't ask if they know themselves.
            if (knows(i, j) || !knows(j, i)) {
                return false;
            }
        }
        return true;
    }
}

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int[] inOutDegree = new int[n];
        
        for (int p1 = 0; p1 < n; ++p1) {
            for (int p2 = 0 ; p2 < n; ++p2) {
                if (p1 == p2) continue;
                if (inOutDegree[p2] >= 0) {
                    if (knows(p1, p2)) {
                        inOutDegree[p2]++;
                        inOutDegree[p1]--;
                    } else {
                        inOutDegree[p2]--;
                    }
                }
            }
        }
        
        for (int num = 0; num < n; ++num) {
            if (inOutDegree[num] == n - 1 && canCeleb(num, n)) {
                return num;
            }
        }
        
        return -1;
    }
    
    private boolean canCeleb(int c, int n) {
        for (int num  = 0; num < n; ++num) {
            if (num == c) continue;
            
            if (knows(c, num) || !knows(num, c)) {
                return false;
            }
        }
        return true;
    }
}