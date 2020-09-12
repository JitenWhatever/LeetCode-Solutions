/*
Given an array of meeting time intervals consisting of start and end times [s1, e1], [s2, e2], ... , determine if a person could attend all meetings.

For example,
Given [ [0, 30], [5, 10], [15, 20] ],
return false

For example,
Given [ [7, 10], [2, 4]],
return true
*/

/**
Definition for an interval.
public class Interval {
    int start;
    int end;
    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}
*/
public boolean canAttendMeetings(Interval[] intervals) {
    Arrays.sort(intervals, (Interval a, interval b) -> {
        return a.start - b.start;
    });
 
    for(int index = 0; index < intervals.length - 1; index++){
        if(intervals[index].end > intervals[index + 1].start){
            return false;
        }
    }
 
    return true;
}
