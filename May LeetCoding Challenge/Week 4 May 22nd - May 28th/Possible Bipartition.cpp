/*
Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

Each person may dislike some other people, and they should not go into the same group. 

Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

Return true if and only if it is possible to split everyone into two groups in this way.

 
Example 1:

Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4], group2 [2,3]

Example 2:

Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false

Example 3:

Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false

Note:
    1 <= N <= 2000
    0 <= dislikes.length <= 10000
    1 <= dislikes[i][j] <= N
    dislikes[i][0] < dislikes[i][1]
    There does not exist i != j for which dislikes[i] == dislikes[j].
*/

class Solution { // DFS
private:
    vector<vector<int>> graph;
    vector<int> colors;
    
    bool dfs(int currentIndex, int color) {
        colors[currentIndex] = color;
        for(int neighbour : graph[currentIndex]) {
            if(colors[neighbour] == color){
                return false;
            }
            if(colors[neighbour] == 0 && !dfs(neighbour, -color)) {
                return false;
            }
        }
        
        return true;
    }
public:
    bool possibleBipartition(int N, vector<vector<int>>& dislikes) {
        graph = vector<vector<int>>(N);
        
        for(int index = 0; index < dislikes.size(); ++index) {
            graph[dislikes[index][0] - 1].push_back(dislikes[index][1] - 1);
            graph[dislikes[index][1] - 1].push_back(dislikes[index][0] - 1);
        }
        
        colors = vector<int>(N, 0);
        
        for(int index = 0; index < N; ++index) {
            if(colors[index] == 0 && !dfs(index, 1)) {
                return false;
            }
        }
        
        return true;
    }
};

class Solution {  // BFS
public:
    bool possibleBipartition(int N, vector<vector<int>>& dislikes) {
        vector<vector<int>>graph(N);
        
        for(int index = 0; index < dislikes.size(); ++index) {
            graph[dislikes[index][0] - 1].push_back(dislikes[index][1] - 1);
            graph[dislikes[index][1] - 1].push_back(dislikes[index][0] - 1);
        }
        
        vector<int> colors(N, 0);
        queue<int> Q;
        for(int index = 0; index < N; ++index) {
            if(colors[index] != 0) continue;
            
            Q.push(index);
            colors[index] = 1;
            while(!Q.empty()) {
                int currentNode = Q.front();
                Q.pop();
                for(int neighbour : graph[currentNode]) {
                    if(colors[currentNode] == colors[neighbour]) {
                        return false;
                    }
                    if(colors[neighbour] != 0) continue;
                    
                    colors[neighbour] = -colors[currentNode];
                    
                    Q.push(neighbour);
                }
            } 
        }
        
        return true;
    }
};