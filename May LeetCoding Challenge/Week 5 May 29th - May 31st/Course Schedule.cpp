/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.

Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.

Constraints:
    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.
    1 <= numCourses <= 10^5
*/

class Solution {
    
private:
    vector<vector<int>> graph;
    vector<int> inDegree;
    
    bool topologicalSortByKahnAlgo(int totalCourses) {
        
        queue<int> Q;
        for(int node = 0; node < totalCourses; ++node) {
            if(inDegree[node] == 0) {
                Q.push(node);
            }
        }
        
        while(!Q.empty()) {
            int currentNode = Q.front();
            Q.pop();
            --totalCourses;
            for(int neighbour : graph[currentNode]) {
                if(--inDegree[neighbour] == 0) {
                    Q.push(neighbour);
                }
            }
        }
        
        return totalCourses == 0;
    }
        
public:
    bool canFinish(int numCourses, vector<vector<int>>& prerequisites) {
        graph = vector<vector<int>>(numCourses);
        inDegree = vector<int>(numCourses);
        
        for(vector<int> edge : prerequisites) {
            graph[edge[1]].push_back(edge[0]);
            ++inDegree[edge[0]];
        }
        
        return topologicalSortByKahnAlgo(numCourses);
    }
};

class Solution { //DFS to find cycle in DAG
private:
    vector<vector<int>> graph;
    vector<bool> visited;
    vector<bool> recurseStack;
    
    bool dfs(int node) {
        if(!visited[node]) {
            visited[node] = true;
            recurseStack[node] = true; 
            for(int neighbour : graph[node]) {
                if(!visited[neighbour] && dfs(neighbour)) {
                    return true;
                }
                else if(recurseStack[neighbour]) {
                    return true;
                }
            }
        }
        
        recurseStack[node] = false;
        return false;
    }
public:
    bool canFinish(int numCourses, vector<vector<int>>& prerequisites) {
        graph = vector<vector<int>>(numCourses);
        visited = vector<bool>(numCourses, false);
        recurseStack = vector<bool>(numCourses, false);
        for(int edge = 0; edge < prerequisites.size(); ++edge) {
            graph[prerequisites[edge][0]].push_back(prerequisites[edge][1]); 
        }
        
        for(int node = 0; node < numCourses; ++node) {
            if(dfs(node)){
                return false;
            }
        }
        
        return true;
        
    }
};

class Solution { // topological sort by DFS(coloring)
private:
    vector<vector<int>> graph;
    vector<int> visited;

    bool dfs(int node){
        if(visited[node] == 1) {
            return true;
        }
        if(visited[node] == 2) {
            return false;
        }
        
        visited[node] = 1;
        for(int neighbour : graph[node]) {
            
            if(dfs(neighbour)) {
                return true;
            }
        }
        visited[node] = 2;
        
        return false;
        
    }
public:
    bool canFinish(int numCourses, vector<vector<int>>& prerequisites) {
        graph = vector<vector<int>>(numCourses);
        visited = vector<int>(numCourses, 0);
        for(int edge = 0; edge < prerequisites.size(); ++edge) {
            graph[prerequisites[edge][0]].push_back(prerequisites[edge][1]); 
        }
        
        for(int node = 0; node < numCourses; ++node) {
            if(dfs(node)){
                return false;
            }
        }
        
        return true;
        
    }
};
