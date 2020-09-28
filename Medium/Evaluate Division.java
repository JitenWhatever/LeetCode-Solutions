/*
You are given equations in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating-point number). Given some queries, return the answers. 
If the answer does not exist, return -1.0.

The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.

 

Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation: 
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
Example 3:

Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]
 

Constraints:

1 <= equations.length <= 20
equations[i].length == 2
1 <= equations[i][0], equations[i][1] <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= queries[i][0], queries[i][1] <= 5
equations[i][0], equations[i][1], queries[i][0], queries[i][1] consist of lower case English letters and digits.

*/

class Solution {
    public double[] calcEquation(List<List<String>> e, double[] v, List<List<String>> q) {
        
        Graph(e, v);
        
        double[] result = new double[q.size()];
        
        for(int index = 0; index < q.size(); ++index) {
            visi = new HashSet();
            result[index] = dfs(q.get(index).get(0), q.get(index).get(1));
        }
            
        
        return result;
    }
    
    private double dfs(String src, String dst) {
        if(!(graph.containsKey(src) && graph.containsKey(dst))) {
            return -1.0;
        }
    
        if(src.equals(dst)) {
            return 1.0;
        }
        
        visi.add(src);
        
        for(Pair<String, Double> nbr : graph.get(src)) {
            if(!visi.contains(nbr.getKey())) {
                double result = dfs(nbr.getKey(), dst);
                if(result != -1.0)
                    return result * nbr.getValue();
            }
        }
        
        return -1.0;
    }
    
    private Map<String, List<Pair<String, Double>>> graph;
    private Set<String> visi;
    
    private void Graph(List<List<String>> e, double[] v) {
        graph = new HashMap();
        for(int index = 0; index < v.length; ++index) {
            String src = e.get(index).get(0);
            String dst = e.get(index).get(1);
            graph.computeIfAbsent(src, k -> new ArrayList()).add(new Pair(dst, v[index])); 
            graph.computeIfAbsent(dst, k -> new ArrayList()).add(new Pair(src, 1 / v[index]));
        }
    }
}