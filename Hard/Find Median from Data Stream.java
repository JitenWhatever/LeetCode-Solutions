/*
The median is the middle value in an ordered integer list. 
If the size of the list is even, there is no middle value and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 

Example 1:

Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0
 

Constraints:

-105 <= num <= 105
There will be at least one element in the data structure before calling findMedian.
At most 5 * 104 calls will be made to addNum and findMedian.
 

Follow up:

If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
*/

class MedianFinder {

    private List<Integer> stream;
    public MedianFinder() {
        this.stream = new ArrayList<>();
    }
    
    public void addNum(int num) {
        this.stream.add(num);
    }
    
    public double findMedian() {
        Collections.sort(this.stream);
        
        int len = this.stream.size();
        
        double median = 0;
        
        if (len % 2 == 0) {
            int index = len / 2;
            median = ((double)(this.stream.get(index) + this.stream.get(index - 1)))/2;
        } else {
            median = (double)this.stream.get(len / 2);
        }
        
        return median;
    }
}

// Time complexity : O(nlogn)
// Space complexity: O(n)

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */


 class MedianFinder {
    
    private List<Integer> stream;
    public MedianFinder() {
        this.stream = new ArrayList<>();
    }
    
    public void addNum(int num) {
        if (this.stream.isEmpty()) {
            this.stream.add(num);
        } else {
            this.stream.add(this.lowerBound(num), num);
        }
    }
    
    public double findMedian() {
        int len = this.stream.size();
        
        double median = 0;
        int index = 0;
        if (len % 2 == 0) {
            index = len / 2;
            median = ((double)(this.stream.get(index) + this.stream.get(index - 1)))/2;
        } else {
            index = len / 2;
            median = (double)this.stream.get(index);
        }
        
        return median;
    }
    
    private int lowerBound(int num) {
        int len = this.stream.size();
        int low = 0, high = len;
        int mid;
        
        while (low < high) {
            mid = low + (high - low) / 2;
            
            if (num <= this.stream.get(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        
        if (low < len && this.stream.get(low) < num) {
            ++low;
        }
        
        return low;
    }
}


// Time complexity : O(logn) + O(n)
// Space complexity: O(n)

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

 class MedianFinder {

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    public MedianFinder() {
        this.minHeap = new PriorityQueue<>();
        this.maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }
    
    public void addNum(int num) {
        this.maxHeap.add(num);
        this.minHeap.add(this.maxHeap.poll());
        
        if (this.maxHeap.size() < this.minHeap.size()) {
            this.maxHeap.add(this.minHeap.poll());
        }
    }
    
    public double findMedian() {
        int maxSize = this.maxHeap.size();
        int minSize = this.minHeap.size();
        
        if (maxSize == minSize) {
            return (double)(this.maxHeap.peek() + this.minHeap.peek()) * 0.5;
        }
        
        return this.maxHeap.peek();
    }
}


// Time complexity : O(logn)
// Space complexity: O(n)

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */