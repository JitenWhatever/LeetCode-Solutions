/*
We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, 
return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.

 
Example 1:
https://assets.leetcode.com/uploads/2019/10/10/sample1_1584.png
Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job. 
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.

Example 2:
https://assets.leetcode.com/uploads/2019/10/10/sample22_1584.png

Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job. 
Profit obtained 150 = 20 + 70 + 60.

Example 3:
https://assets.leetcode.com/uploads/2019/10/10/sample3_1584.png

Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6
 

Constraints:

1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
1 <= startTime[i] < endTime[i] <= 10^9
1 <= profit[i] <= 10^4
*/


class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int numberOfJobs = profit.length;
        Job[] jobs = new Job[numberOfJobs];
        
        for (int index = 0; index < numberOfJobs; ++index) {
            jobs[index] = new Job(startTime[index], endTime[index], profit[index]);
        }
        
        Arrays.sort(jobs, (job1, job2) -> job1.startTime - job2.startTime);
        int[] dp = new int[numberOfJobs];
        dp[numberOfJobs - 1] = jobs[numberOfJobs - 1].profit;
        
        for (int index = numberOfJobs - 2; index >= 0; --index) {
            int l = index + 1, r = numberOfJobs, prevJobEndTime = jobs[index].endTime;
            int nextJob = findNextJob(l, r, jobs, prevJobEndTime);
            
            dp[index] = Math.max(jobs[index].profit + (nextJob < numberOfJobs ? dp[nextJob] : 0), dp[index + 1]);
        }
        
        return dp[0];
        
    }
    
    private int findNextJob(int l, int r, Job[] jobs, int prevJobEndTime) {
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (jobs[mid].startTime >= prevJobEndTime) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        
        return l;
    }
    
    
    class Job {
        int startTime, endTime, profit;
    
        public Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
    }

     public int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b)->a[1] - b[1]);
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);
        for (int[] job : jobs) {
            int cur = dp.floorEntry(job[0]).getValue() + job[2];
            if (cur > dp.lastEntry().getValue())
                dp.put(job[1], cur);
        }
        return dp.lastEntry().getValue();
    }
}