/*
Given a list of the scores of different students, items, 
where items[i] = [IDi, scorei] represents one score from a student with IDi, 
calculate each student's top five average.

Return the answer as an array of pairs result, 
where result[j] = [IDj, topFiveAveragej] represents the student with IDj and their top five average. 
Sort result by IDj in increasing order.

A student's top five average is calculated by taking the sum of their top five scores and dividing it by 5 using integer division.

 

Example 1:

Input: items = [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
Output: [[1,87],[2,88]]
Explanation: 
The student with ID = 1 got scores 91, 92, 60, 65, 87, and 100. Their top five average is (100 + 92 + 91 + 87 + 65) / 5 = 87.
The student with ID = 2 got scores 93, 97, 77, 100, and 76. Their top five average is (100 + 97 + 93 + 77 + 76) / 5 = 88.6, but with integer division their average converts to 88.
Example 2:

Input: items = [[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100]]
Output: [[1,100],[7,100]]
 

Constraints:

1 <= items.length <= 1000
items[i].length == 2
1 <= IDi <= 1000
0 <= scorei <= 100
For each IDi, there will be at least five scores.
*/

class Solution {
    public int[][] highFive(int[][] items) {
        int avg = 5;
        Arrays.sort(items, (int[] a, int[] b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            
            return b[1] - a[1];
        });
        
        
        List<int[]> avgs = new ArrayList<>();
        int len = items.length, index = 0;
        
        while (index < len) {
            int id = items[index][0];
            int sum = 0;
            for (int itr = index; itr < index + avg; ++itr) {
                sum += items[itr][1];
            }
            while (index < len && items[index][0] == id) {
                ++index;
            }
            
            avgs.add(new int[] {id, sum / avg});
        }
        
        return avgs.toArray(new int[avgs.size()][]);
    }
}


class Solution {
    private int K;

    public int[][] highFive(int[][] items) {
        this.K = 5;
        TreeMap<Integer, Queue<Integer>> allScores = new TreeMap<>();
        for (int[] item : items) {
            int id = item[0];
            int score = item[1];
            if (!allScores.containsKey(id))
                // max heap
                allScores.put(id, new PriorityQueue<>((a,b) -> b - a));
            // Add score to the max heap
            allScores.get(id).add(score);
        }

        List<int[]> solution = new ArrayList<>();

        for (int id : allScores.keySet()) {
            int sum = 0;
            // obtain the top k scores (k = 5)
            for (int i = 0; i < this.K; ++i)
                sum += allScores.get(id).poll();
            solution.add(new int[] {id, sum / this.K});
        }
        int[][] solutionArray = new int[solution.size()][];
        return solution.toArray(solutionArray);
    }
}

class Solution {
    private int K;

    public int[][] highFive(int[][] items) {
        this.K = 5;
        TreeMap<Integer, Queue<Integer>> allScores = new TreeMap<>();
        for (int[] item : items) {
            int id = item[0];
            int score = item[1];
            if (!allScores.containsKey(id))
                allScores.put(id, new PriorityQueue<>());
            // insert the score in the min heap
            allScores.get(id).add(score);
            // remove the minimum element from the min heap in case the size of the min heap exceeds 5 
            if (allScores.get(id).size() > this.K)
                allScores.get(id).poll();
        }

        List<int[]> solution = new ArrayList<>();

        for (int id : allScores.keySet()) {
            int sum = 0;
            // min heap contains the top 5 scores
            for (int i = 0; i < this.K; ++i)
                sum += allScores.get(id).poll();
            solution.add(new int[] {id, sum / this.K});
        }
        int[][] solutionArray = new int[solution.size()][];
        return solution.toArray(solutionArray);
    }
}