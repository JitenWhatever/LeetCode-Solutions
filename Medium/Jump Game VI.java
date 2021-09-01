/*
You are given a 0-indexed integer array nums and an integer k.

You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. 
That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.

You want to reach the last index of the array (index n - 1). 
Your score is the sum of all nums[j] for each index j you visited in the array.

Return the maximum score you can get.

 

Example 1:

Input: nums = [1,-1,-2,4,-7,3], k = 2
Output: 7
Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.
Example 2:

Input: nums = [10,-5,-2,4,0,3], k = 3
Output: 17
Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.
Example 3:

Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
Output: 0
 

Constraints:

1 <= nums.length, k <= 105
-104 <= nums[i] <= 104
*/

// time O(N), space O(N + k)
class Solution {
    public int maxResult(int[] nums, int k) {
        int size = nums.length;
        int[] score = new int[size]; // max score ending at index i
        Deque<Integer> dq = new LinkedList<>();
        score[0] = nums[0];
        dq.offerLast(0);
        
        for (int index = 1; index < size; ++index) {
            while(Objects.nonNull(dq.peekFirst()) && dq.peekFirst() < index - k) {
                dq.pollFirst();
            }
            
            score[index] = score[dq.peek()] + nums[index];
            
            while (Objects.nonNull(dq.peekLast()) && score[index] >= score[dq.peekLast()]) {
                dq.pollLast();
            }
            
            dq.offerLast(index);
        }
        
        return score[size - 1];
        
    }
}

class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int score = nums[0];
        Deque<int[]> dq = new LinkedList<>();
        dq.offerLast(new int[] { 0, score });
        for (int i = 1; i < n; i++) {
            // pop the old index
            while (dq.peekFirst() != null && dq.peekFirst()[0] < i - k) {
                dq.pollFirst();
            }
            score = dq.peek()[1] + nums[i];
            // pop the smaller value
            while (dq.peekLast() != null && score >= dq.peekLast()[1]) {
                dq.pollLast();
            }
            dq.offerLast(new int[] { i, score });
        }
        return score;
    }
}

// time O(NlogN), space( O(2N))
class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] score = new int[n];
        score[0] = nums[0];
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        priorityQueue.add(new int[] { nums[0], 0 });
        for (int i = 1; i < n; i++) {
            // pop the old index
            while (priorityQueue.peek()[1] < i - k) {
                priorityQueue.remove();
            }
            score[i] = nums[i] + score[priorityQueue.peek()[1]];
            priorityQueue.add(new int[] { score[i], i });
        }
        return score[n - 1];
    }
}

class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int score = nums[0];
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        priorityQueue.add(new int[] { nums[0], 0 });
        for (int i = 1; i < n; i++) {
            // pop the old index
            while (priorityQueue.peek()[1] < i - k) {
                priorityQueue.remove();
            }
            score = nums[i] + priorityQueue.peek()[0];
            priorityQueue.add(new int[] { score, i });
        }
        return score;
    }
}


class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] tree = new int[2 * n];
        update(0, nums[0], tree, n);
        for (int i = 1; i < n; i++) {
            int maxi = query(Math.max(0, i - k), i, tree, n);
            update(i, maxi + nums[i], tree, n);
        }
        return tree[2 * n - 1];
    }

    // implement Segment Tree
    private void update(int index, int value, int[] tree, int n) {
        index += n;
        tree[index] = value;
        for (index >>= 1; index > 0; index >>= 1) {
            tree[index] = Math.max(tree[index << 1], tree[(index << 1) + 1]);
        }
    }

    private int query(int left, int right, int[] tree, int n) {
        int result = Integer.MIN_VALUE;
        for (left += n, right += n; left < right; left >>= 1, right >>= 1) {
            if ((left & 1) == 1) {
                result = Math.max(result, tree[left++]);
            }
            if ((right & 1) == 1) {
                result = Math.max(result, tree[--right]);
            }
        }
        return result;
    }
}