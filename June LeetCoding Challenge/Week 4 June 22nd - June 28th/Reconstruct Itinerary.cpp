/*
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. 
All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. 
For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
One must use all the tickets once and only once.

Example 1:

Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
Example 2:

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
*/

class Solution {
private:
     map<string, deque<string>> adj;
     vector<string> path;
    
    void dfs(const string& from) {
        auto& dst = adj[from];
        
        while(!dst.empty()) {
            string to = dst.front();
            dst.pop_front();
            dfs(to);
        }
        
        path.push_back(from);
    }
public:
    vector<string> findItinerary(vector<vector<string>>& tickets) {
        
        for(const auto& trip : tickets) {
            adj[trip[0]].push_back(trip[1]);
        }
        
        for(auto& trip :  adj) {
            auto& dst = trip.second;
            sort(dst.begin(), dst.end());
        }
        
        const string src = "JFK";
        dfs(src);
        
        reverse(path.begin(), path.end());
        return path;
    }
};
