/*
You are given an array of variable pairs equations and an array of real numbers values, 
where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

 

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
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.

*/

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        GraphUtil graphUtil = new GraphUtil(equations, values);
        int len = queries.size();
        double[] results = new double[len];
        
        for (int index = 0; index < len; ++index) {
            graphUtil.resetVisited();
            results[index] = graphUtil.dfs(queries.get(index).get(0), queries.get(index).get(1));
        }
        
        return results;
    }
    
}

class GraphUtil {
    private Map<String, List<Pair<String, Double>>> graph;
    private Set<String> visited;
    
    public GraphUtil(List<List<String>> edges, double[] values) {
        int N = values.length;
        this.graph = new HashMap<>();
        this.visited = new HashSet<>();
        for (int index = 0; index < N; ++index) {
            String src = edges.get(index).get(0);
            String dst = edges.get(index).get(1);
            graph.computeIfAbsent(src, key -> new ArrayList<>()).add(new Pair(dst, values[index]));
            graph.computeIfAbsent(dst, key -> new ArrayList<>()).add(new Pair(src, 1 / values[index]));
        }
    }
    
    public double dfs(String src, String dst) {
        if (!(this.graph.containsKey(src) && this.graph.containsKey(dst))) {
            return -1.0;
        }
        
        if (src.equals(dst)) {
            return 1.0;
        }
        
        this.visited.add(src);
        
        for (Pair<String, Double> nbr : this.graph.get(src)) {
            if (this.visited.add(nbr.getKey())) {
                double result = dfs(nbr.getKey(), dst);
                if (result != -1.0) {
                    return result * nbr.getValue();
                }
            }
        }
        
        return -1.0;
    }
    
    public void resetVisited(){
        this.visited.clear();
    }
}


class Solution {

    public double[] calcEquation(List<List<String>> equations, double[] values,
            List<List<String>> queries) {

        HashMap<String, Pair<String, Double>> gidWeight = new HashMap<>();

        // Step 1). build the union groups
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String dividend = equation.get(0), divisor = equation.get(1);
            double quotient = values[i];

            union(gidWeight, dividend, divisor, quotient);
        }

        // Step 2). run the evaluation, with "lazy" updates in find() function
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String dividend = query.get(0), divisor = query.get(1);

            if (!gidWeight.containsKey(dividend) || !gidWeight.containsKey(divisor))
                // case 1). at least one variable did not appear before
                results[i] = -1.0;
            else {
                Pair<String, Double> dividendEntry = find(gidWeight, dividend);
                Pair<String, Double> divisorEntry = find(gidWeight, divisor);

                String dividendGid = dividendEntry.getKey();
                String divisorGid = divisorEntry.getKey();
                Double dividendWeight = dividendEntry.getValue();
                Double divisorWeight = divisorEntry.getValue();

                if (!dividendGid.equals(divisorGid))
                    // case 2). the variables do not belong to the same chain/group
                    results[i] = -1.0;
                else
                    // case 3). there is a chain/path between the variables
                    results[i] = dividendWeight / divisorWeight;
            }
        }

        return results;
    }

    private Pair<String, Double> find(HashMap<String, Pair<String, Double>> gidWeight, String nodeId) {
        if (!gidWeight.containsKey(nodeId))
            gidWeight.put(nodeId, new Pair<String, Double>(nodeId, 1.0));

        Pair<String, Double> entry = gidWeight.get(nodeId);
        // found inconsistency, trigger chain update
        if (!entry.getKey().equals(nodeId)) {
            Pair<String, Double> newEntry = find(gidWeight, entry.getKey());
            gidWeight.put(nodeId, new Pair<String, Double>(
                    newEntry.getKey(), entry.getValue() * newEntry.getValue()));
        }

        return gidWeight.get(nodeId);
    }

    private void union(HashMap<String, Pair<String, Double>> gidWeight, String dividend, String divisor, Double value) {
        Pair<String, Double> dividendEntry = find(gidWeight, dividend);
        Pair<String, Double> divisorEntry = find(gidWeight, divisor);

        String dividendGid = dividendEntry.getKey();
        String divisorGid = divisorEntry.getKey();
        Double dividendWeight = dividendEntry.getValue();
        Double divisorWeight = divisorEntry.getValue();

        // merge the two groups together,
        // by attaching the dividend group to the one of divisor
        if (!dividendGid.equals(divisorGid)) {
            gidWeight.put(dividendGid, new Pair<String, Double>(divisorGid,
                    divisorWeight * value / dividendWeight));
        }
    }
}


class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Integer> varIndex  = new HashMap<>();
        int M = equations.size();
        int n = M *2;
        UnionFind uf = new UnionFind(n);
        int id = 0;
        for(int i = 0 ;i < M; i++){
            String v1 = equations.get(i).get(0);
            String v2 = equations.get(i).get(1);
            double q = values[i];
            if(!varIndex.containsKey(v1)){
                varIndex.put(v1,id++);
            }
            if(!varIndex.containsKey(v2)){
                varIndex.put(v2,id++);
            }
            uf.union(varIndex.get(v1), varIndex.get(v2), q);
        }
        
        double[] ans = new double[queries.size()];
        for(int i = 0; i < queries.size(); i++){
            String v1 = queries.get(i).get(0);
            String v2 = queries.get(i).get(1);
            if(!varIndex.containsKey(v1) || !varIndex.containsKey(v2)){
                ans[i] = -1.0;
            }else if(v1.equals(v2)){
                ans[i] = 1.0;
            }else{
                ans[i] = uf.getQuotient(varIndex.get(v1), varIndex.get(v2));
            }
        }
        return ans;
    }    
}

class UnionFind{
    int[] parent;
    int[] rank;
    double[] weight;
    int count;
    
    public UnionFind(int size){
        parent = new int[size];
        rank = new int[size];
        weight = new double[size];
        for(int i = 0; i<size; i++){
            parent[i] = i;
            rank[i] =1;
            weight[i] = 1.0;
        }
        count = size;
    }
    
    public int find(int x){
        if(x != parent[x]){
            int origx = parent[x];
            parent[x] = find(parent[x]);
            weight[x] *= weight[origx];
        }
        return parent[x];
    }
    
    public void union(int x, int y, double v){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY) return;
        if(rank[rootX] > rank[rootY]){
            parent[rootY] = rootX;
            rank[rootX] += rank[rootY];
            weight[rootY] = weight[x] / v / weight[y]; 
        }else{
            parent[rootX] = rootY;
            rank[rootY] += rank[rootX];
            weight[rootX] = weight[y]  * v /weight[x];
        }
        count--;  
    }
    
    public boolean connected(int x, int y){
        return find(x) == find(y);
    }
    
    public double getQuotient(int x, int y){
        if(connected(x, y)){
            return weight[x] / weight[y];
        }
        return -1.0;
    }
}