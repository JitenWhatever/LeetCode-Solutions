/*
Given an array of meeting time intervals consisting of start and end times [s1, e1], [s2, e2], ... , find the minimum number of conference rooms required.

For example,
Given [ [0, 30], [5, 10], [15, 20] ],
return 2

For example,
Given [ [7, 10], [2, 4]],
return 1
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

public int minMeetingRooms(int[][] intervals) {
    
    if(intervals == null || intervals.length == 0) {
        return 0;
    }

    Arrays.sort(intervals, (a, b) -> a.start - b.start);
    PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a.end - b.end));
    minHeap.add(interval[0]);
    
    for(int index = 1; index < intervals.length; ++index) {
        Interval current = intervals[index];
        Interval early = minHeap.remove();

        if(current.start >= early.end) {
            early.end = current.end;
        }
        else {
            minHeap.add(current);
        }

        minHeap.add(early);
    }
    return minHeap.size();
}