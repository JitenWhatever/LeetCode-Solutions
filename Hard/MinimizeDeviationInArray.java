import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
You are given an array nums of n positive integers.

You can perform two types of operations on any element of the array any number of times:

If the element is even, divide it by 2.
For example, if the array is [1,2,3,4], then you can do this operation on the last element, and the array will be [1,2,3,2].
If the element is odd, multiply it by 2.
For example, if the array is [1,2,3,4], then you can do this operation on the first element, and the array will be [2,2,3,4].
The deviation of the array is the maximum difference between any two elements in the array.

Return the minimum deviation the array can have after performing some number of operations.

Example 1:

Input: nums = [1,2,3,4]
Output: 1
Explanation: You can transform the array to [1,2,3,2], then to [2,2,3,2], then the deviation will be 3 - 2 = 1.
Example 2:

Input: nums = [4,1,5,20,3]
Output: 3
Explanation: You can transform the array after two operations to [4,2,5,5,3], then the deviation will be 5 - 2 = 3.
Example 3:

Input: nums = [2,10,8]
Output: 3
 

Constraints:

n == nums.length
2 <= n <= 10^5
1 <= nums[i] <= 10^9
*/

class MinimizeDeviationInArray {

    // Time Complexity: O(Klog(N))=O(Nlog(M)log(N))
    // Space Complexity: O(N)
    public int minimumDeviation(int[] nums) {
        PriorityQueue<Integer> evens = new PriorityQueue<>(Collections.reverseOrder());
        int minimum = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num % 2 == 0) {
                evens.offer(num);
                minimum = Math.min(minimum, num);
            } else {
                evens.offer(num * 2);
                minimum = Math.min(minimum, num * 2);
            }
        }
        int minDeviation = Integer.MAX_VALUE;

        while (!evens.isEmpty()) {
            int currentValue = evens.poll();
            minDeviation = Math.min(minDeviation, currentValue - minimum);
            if (currentValue % 2 == 0) {
                evens.offer(currentValue / 2);
                minimum = Math.min(minimum, currentValue / 2);
            } else {
                // if the maximum is odd, break and return
                break;
            }
        }
        return minDeviation;
    }
 

    // Time Complexity: O(Klog(K))=O(Nlog(M)log(Nlog(M))
    // Space Complexity: O(K)=O(Nlog(M))
    public int minimumDeviation1(int[] nums) {
        int n = nums.length;
        List<int[]> possible = new ArrayList<>();
        // pretreatment
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                int temp = nums[i];
                possible.add(new int[] { temp, i });
                while (temp % 2 == 0) {
                    temp /= 2;
                    possible.add(new int[] { temp, i });
                }
            } else {
                possible.add(new int[] { nums[i], i });
                possible.add(new int[] { nums[i] * 2, i });
            }
        }
        possible.sort(Comparator.comparingInt(p -> p[0]));
        // start sliding window
        int minDeviation = Integer.MAX_VALUE;
        int notIncluded = n;
        int currentStart = 0;
        int[] needInclude = new int[n];
        for (int i = 0; i < n; i++) {
            needInclude[i] = 1;
        }
        for (int[] current : possible) {
            int currentValue = current[0];
            int currentItem = current[1];
            needInclude[currentItem] -= 1;
            if (needInclude[currentItem] == 0) {
                notIncluded -= 1;
            }
            if (notIncluded == 0) {
                while (needInclude[possible.get(currentStart)[1]] < 0) {
                    needInclude[possible.get(currentStart)[1]] += 1;
                    currentStart += 1;
                }
                if (minDeviation > currentValue - possible.get(currentStart)[0]) {
                    minDeviation = currentValue - possible.get(currentStart)[0];
                }
                needInclude[possible.get(currentStart)[1]] += 1;
                currentStart += 1;
                notIncluded += 1;
            }
        }
        return minDeviation;
    }

    // Time Complexity: O(Klog(K))=O(Nlog(M)log(Nlog(M))
    // Space Complexity: O(K)=O(Nlog(M))
    public int minimumDeviation2(int[] nums) {
        int n = nums.length;
        // pretreatment
        List<int[]> possible = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                int temp = nums[i];
                possible.add(new int[] { temp, i });
                while (temp % 2 == 0) {
                    temp /= 2;
                    possible.add(new int[] { temp, i });
                }
            } else {
                possible.add(new int[] { nums[i], i });
                possible.add(new int[] { nums[i] * 2, i });
            }
        }
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p[0]));
        priorityQueue.addAll(possible);
        int minDeviation = Integer.MAX_VALUE;
        int notIncluded = n;
        int currentStart = 0;
        int[] needInclude = new int[n];
        for (int i = 0; i < n; i++) {
            needInclude[i] = 1;
        }
        List<int[]> seen = new ArrayList<>();
        // get minimum from heap
        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            seen.add(current);
            int currentValue = current[0];
            int currentItem = current[1];
            needInclude[currentItem] -= 1;
            if (needInclude[currentItem] == 0) {
                notIncluded -= 1;
            }
            if (notIncluded == 0) {
                while (needInclude[seen.get(currentStart)[1]] < 0) {
                    needInclude[seen.get(currentStart)[1]] += 1;
                    currentStart += 1;
                }
                if (minDeviation > currentValue - seen.get(currentStart)[0]) {
                    minDeviation = currentValue - seen.get(currentStart)[0];
                }
                needInclude[seen.get(currentStart)[1]] += 1;
                currentStart += 1;
                notIncluded += 1;
            }
        }
        return minDeviation;
    }


    // Time Complexity: O(Klog(K))=O(Nlog(M)log(Nlog(M))
    // Space Complexity: O(N)
    public int minimumDeviation3(int[] nums) {
        // pretreatment
        int n = nums.length;
        List<List<Integer>> possibleList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> candidates = new ArrayList<>();
            if (nums[i] % 2 == 0) {
                int temp = nums[i];
                candidates.add(temp);
                while (temp % 2 == 0) {
                    temp /= 2;
                    candidates.add(temp);
                }
            } else {
                candidates.add(nums[i] * 2);
                candidates.add(nums[i]);
            }
            // reverse candidates to sort from small to large
            Collections.reverse(candidates);
            possibleList.add(candidates);
        }
        List<int[]> pointers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pointers.add(new int[] { possibleList.get(i).get(0), i, 0 });
        }
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p[0]));
        priorityQueue.addAll(pointers);

        int minDeviation = Integer.MAX_VALUE;
        int currentEnd = 0;
        for (int i = 0; i < n; i++) {
            currentEnd = Math.max(currentEnd, possibleList.get(i).get(0));
        }
        // get minimum from heap
        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int currentStart = current[0];
            int index = current[1];
            int indexInCandidates = current[2];
            if (minDeviation > currentEnd - currentStart) {
                minDeviation = currentEnd - currentStart;
            }
            // if the pointer reach last
            if (indexInCandidates + 1 == possibleList.get(index).size()) {
                return minDeviation;
            }
            int nextValue = possibleList.get(index).get(indexInCandidates + 1);
            currentEnd = Math.max(currentEnd, nextValue);
            priorityQueue.offer(new int[] { nextValue, index, indexInCandidates + 1 });
        }
        return minDeviation;
    }



    // Time Complexity: O(Klog(K))=O(Nlog(M)log(Nlog(M))
    // Space Complexity: O(K)
    public int minimumDeviation4(int[] nums) {
        int n = nums.length;
        // pretreatment
        List<List<Integer>> possibleList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> candidates = new ArrayList<>();
            if (nums[i] % 2 == 0) {
                int temp = nums[i];
                candidates.add(temp);
                while (temp % 2 == 0) {
                    temp /= 2;
                    candidates.add(temp);
                }
            } else {
                candidates.add(nums[i] * 2);
                candidates.add(nums[i]);
            }
            // reverse candidates to sort from small to large
            Collections.reverse(candidates);
            possibleList.add(candidates);
        }
        List<int[]> pointers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int size = possibleList.get(i).size();
            for (int j = 0; j < size; j++) {
                pointers.add(new int[] { possibleList.get(i).get(j), i, j });
            }
        }
        pointers.sort(Comparator.comparingInt(p -> p[0]));
        int minDeviation = Integer.MAX_VALUE;
        int currentEnd = 0;
        for (int i = 0; i < n; i++) {
            currentEnd = Math.max(currentEnd, possibleList.get(i).get(0));
        }
        for (int[] current : pointers) {
            int currentStart = current[0];
            int index = current[1];
            int indexInCandidates = current[2];
            if (minDeviation > currentEnd - currentStart) {
                minDeviation = currentEnd - currentStart;
            }
            // if the pointer reach last
            if (indexInCandidates + 1 == possibleList.get(index).size()) {
                return minDeviation;
            }
            int nextValue = possibleList.get(index).get(indexInCandidates + 1);
            currentEnd = Math.max(currentEnd, nextValue);
        }
        return minDeviation;
    }
}