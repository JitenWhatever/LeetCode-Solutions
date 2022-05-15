import java.util.ArrayDeque;
import java.util.Deque;

/* 
Given a binary tree, return the sum of values of its deepest leaves.
 

Example 1:
https://assets.leetcode.com/uploads/2019/07/31/1483_ex1.png


Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15
 

Constraints:

The number of nodes in the tree is between 1 and 10^4.
The value of nodes is between 1 and 100.
*/

public class DeepestLeavesSum {

    class Pair<T, V> {
        private T key;
        private V value;
        public Pair(T key, V value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int deepestLeavesSum(TreeNode root) {
       
        h = height(root);
        helper(root, 1);
        
        return sum;
    }
    
    private int h;
    private int sum = 0;
    
    private void helper(TreeNode root, int depth) {
        
        if(root == null) {
            return ;
        }
        if(depth == h) {
            sum += root.val;
            return ;
        }
        
        helper(root.left, depth + 1);
        helper(root.right, depth + 1);
    }
    
    private int height(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        int left = height(root.left);
        int right = height(root.right);
        
        return Math.max(left, right) + 1;
    }
    
    public int deepestLeavesSum1(TreeNode root) {
        int deepestSum = 0, depth = 0, currDepth;
        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, 0));
    
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> p = stack.pop();
            root = p.getKey();
            currDepth = p.getValue();
        
            if (root.left == null && root.right == null) {
                // if this leaf is the deepest one seen so far
                if (depth < currDepth) {
                    deepestSum = root.val;      // start new sum
                    depth = currDepth;          // note new depth    
                } else if (depth == currDepth) {
                // if there were already leaves at this depth
                    deepestSum += root.val;     // update existing sum    
                }
            } else {
                if (root.right != null) {
                    stack.push(new Pair<>(root.right, currDepth + 1));
                }
                if (root.left != null) {
                    stack.push(new Pair<>(root.left, currDepth + 1));
                }
            }
        }
        return deepestSum;
    }
    
    public int deepestLeavesSum2(TreeNode root) {
        int deepestSum = 0, depth = 0, currDepth;
        Deque<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        queue.offer(new Pair<>(root, 0));
    
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> p = queue.poll();
            root = p.getKey();
            currDepth = p.getValue();
    
            if (root.left == null && root.right == null) {
                // if this leaf is the deepest one seen so far
                if (depth < currDepth) {
                    deepestSum = root.val;      // start new sum
                    depth = currDepth;          // note new depth    
                } else if (depth == currDepth) {
                // if there were already leaves at this depth
                    deepestSum += root.val;     // update existing sum    
                }
            } else {
                if (root.left != null) {
                    queue.offer(new Pair<>(root.left, currDepth + 1));
                }
                if (root.right != null) {
                    queue.offer(new Pair<>(root.right, currDepth + 1));
                }
            }
        }
        return deepestSum;
    }

    public int deepestLeavesSum4(TreeNode root) {
        ArrayDeque<TreeNode>  nextLevel = new ArrayDeque<>(),
                            currLevel = new ArrayDeque<>();
        nextLevel.offer(root);
    
        while (!nextLevel.isEmpty()) {
        // prepare for the next level
            currLevel = nextLevel.clone();
            nextLevel.clear();
        
            for (TreeNode node: currLevel) {
                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null) {
                    nextLevel.offer(node.left);
                }
                if (node.right != null) {
                    nextLevel.offer(node.right);
                }
            }
        }
        int deepestSum = 0;
        for (TreeNode node: currLevel) {
            deepestSum += node.val;
        }
        return deepestSum;
    }
}