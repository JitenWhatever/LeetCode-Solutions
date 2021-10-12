/*
Given the root of a binary tree, return the vertical order traversal of its nodes' values. 
(i.e., from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

 

Example 1:
https://assets.leetcode.com/uploads/2021/01/28/vtree1.jpg
Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation: 
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with value 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with value 20 occurs at position (1, -1);
The node with value 7 occurs at position (2, -2).

Example 2:
https://assets.leetcode.com/uploads/2021/01/28/vtree2-1.jpg

Input: root = [3,9,8,4,0,1,7]
Output: [[4],[9],[3,0,1],[8],[7]]

Example 3:
https://assets.leetcode.com/uploads/2021/01/28/vtree2.jpg

Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
Output: [[4],[9,5],[3,0,1],[8,2],[7]]
Example 4:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        dfs(root, 0, 0);
        List<List<Integer>> result = new ArrayList();
        for (Map.Entry<Integer, List<Pair>> entry : this.cache.entrySet()) {
            List<Pair> list = entry.getValue();
            
            Collections.sort(list, (a, b) ->  a.y - b.y);
            
            result.add(list.stream().map(Pair::getX).collect(Collectors.toList()));
        }
        
        return result;
        
    }
    
    private Map<Integer, List<Pair>>  cache = new TreeMap<>();
    
    private void dfs(TreeNode node, int y, int x) {
        if (Objects.isNull(node)) {
            return ;
        }
        
        this.cache.computeIfAbsent(x, key -> new ArrayList()).add(new Pair(node.val, y));
    
        dfs(node.left, y + 1, x - 1);
        dfs(node.right, y + 1, x + 1);
    }
    
    
}

class Pair {
    int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> output = new ArrayList();
    if (root == null) {
      return output;
    }

    Map<Integer, ArrayList> columnTable = new HashMap();
    Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque();
    int column = 0;
    queue.offer(new Pair(root, column));

    while (!queue.isEmpty()) {
      Pair<TreeNode, Integer> p = queue.poll();
      root = p.getKey();
      column = p.getValue();

      if (root != null) {
        if (!columnTable.containsKey(column)) {
          columnTable.put(column, new ArrayList<Integer>());
        }
        columnTable.get(column).add(root.val);

        queue.offer(new Pair(root.left, column - 1));
        queue.offer(new Pair(root.right, column + 1));
      }
    }

    List<Integer> sortedKeys = new ArrayList<Integer>(columnTable.keySet());
    Collections.sort(sortedKeys);
    for(int k : sortedKeys) {
      output.add(columnTable.get(k));
    }

    return output;
  }
}

class Solution {
  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> output = new ArrayList();
    if (root == null) {
      return output;
    }

    Map<Integer, ArrayList> columnTable = new HashMap();
    // Pair of node and its column offset
    Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque();
    int column = 0;
    queue.offer(new Pair(root, column));

    int minColumn = 0, maxColumn = 0;

    while (!queue.isEmpty()) {
      Pair<TreeNode, Integer> p = queue.poll();
      root = p.getKey();
      column = p.getValue();

      if (root != null) {
        if (!columnTable.containsKey(column)) {
          columnTable.put(column, new ArrayList<Integer>());
        }
        columnTable.get(column).add(root.val);
        minColumn = Math.min(minColumn, column);
        maxColumn = Math.max(maxColumn, column);

        queue.offer(new Pair(root.left, column - 1));
        queue.offer(new Pair(root.right, column + 1));
      }
    }

    for(int i = minColumn; i < maxColumn + 1; ++i) {
      output.add(columnTable.get(i));
    }

    return output;
  }
}