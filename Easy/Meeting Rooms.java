/*
Given an array of meeting time intervals where intervals[i] = [starti, endi], 
determine if a person could attend all meetings.

Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: true
 

Constraints:

0 <= intervals.length <= 10^4
intervals[i].length == 2
0 <= starti < endi <= 10^6
*/

class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        boolean discrete = true;
        
        for (int index = 0; index < intervals.length - 1; ++index) {
            if (intervals[index][1] > intervals[index + 1][0]) {
                discrete = false;
                break;
            }      
        }
      
        return discrete;
    }
}