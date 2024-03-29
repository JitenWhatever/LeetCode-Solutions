import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;


/*
You are given a string s, and an array of pairs of indices in the string pairs 
where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.

You can swap the characters at any pair of indices in the given pairs any number of times.

Return the lexicographically smallest string that s can be changed to after using the swaps.

 

Example 1:

Input: s = "dcab", pairs = [[0,3],[1,2]]
Output: "bacd"
Explaination: 
Swap s[0] and s[3], s = "bcad"
Swap s[1] and s[2], s = "bacd"
Example 2:

Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
Output: "abcd"
Explaination: 
Swap s[0] and s[3], s = "bcad"
Swap s[0] and s[2], s = "acbd"
Swap s[1] and s[2], s = "abcd"
Example 3:

Input: s = "cba", pairs = [[0,1],[1,2]]
Output: "abc"
Explaination: 
Swap s[0] and s[1], s = "bca"
Swap s[1] and s[2], s = "bac"
Swap s[0] and s[1], s = "abc"
 

Constraints:

1 <= s.length <= 10^5
0 <= pairs.length <= 10^5
0 <= pairs[i][0], pairs[i][1] < s.length
s only contains lower case English letters.
*/

class SmallestStringWithSwaps {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs.size() == 0) {
            return s;
        }
        
        int len = s.length();
        UnionFind dsj = new UnionFind(len);
        
        for (List<Integer> pair : pairs) {
            dsj.union(pair.get(0), pair.get(1));
        }
        
        Map<Integer, PriorityQueue<Character>> rootToCharacterMap = new HashMap<>();
        
        for (int index = 0; index < len; ++index) {
            int root = dsj.find(index);
            rootToCharacterMap.computeIfAbsent(root, key -> new PriorityQueue<>()).offer(s.charAt(index));
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int index = 0; index < len; ++index) {
            sb.append(rootToCharacterMap.get(dsj.find(index)).poll());
        }
        
        return sb.toString();
    }

    class UnionFind {
        int root[];
        int rank[];
        int count;

        public UnionFind(int size) {
            root = new int[size];
            rank = new int[size];
            count = size;
            for (int i = 0; i < size; i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (x == root[x]) {
                return x;
            }
            return root[x] = find(root[x]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    root[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    root[rootX] = rootY;
                } else {
                    root[rootY] = rootX;
                    rank[rootX] += 1;
                }
                count--;
            }
        };

        int getCount() {
            return count;
        }
    }


    public String smallestStringWithSwaps1(String s, List<List<Integer>> pairs) {
        int len = s.length();
        
        if (len == 0) {
            return s;
        }
        
        this.graph = new HashMap<>();
        this.visited = new boolean[len];
        this.indices = new TreeSet<>(); // component Indexes
        this.characters = new ArrayList<>(); // component chracters
        
        for (List<Integer> pair : pairs) {
            graph.computeIfAbsent(pair.get(0), key -> new ArrayList<>()).add(pair.get(1));
            graph.computeIfAbsent(pair.get(1), key -> new ArrayList<>()).add(pair.get(0));
        }
        
        char[] result = s.toCharArray();
        for (int index = 0; index < len; ++index) {
            if (!visited[index]) {
                this.indices.clear();
                this.characters.clear();
                dfs(s, index);
                Collections.sort(this.characters);
                Iterator<Character> iterate = this.characters.iterator();
                for (int itr : this.indices) {
                    result[itr] = iterate.next();
                }
            }
        }
            
        return new String(result); 
    }
    
    private boolean[] visited;
    private Map<Integer, List<Integer>> graph;
    private Set<Integer> indices;
    private List<Character> characters;
    
    private void dfs(String s, int index) {
        visited[index] = true;
        this.indices.add(index);
        this.characters.add(s.charAt(index));
        if (Objects.nonNull(this.graph.get(index))) {
            for (int nbr : this.graph.get(index)) {
                if (!visited[nbr]) {
                    dfs(s, nbr);    
                }
            }
        }
        
    }
}