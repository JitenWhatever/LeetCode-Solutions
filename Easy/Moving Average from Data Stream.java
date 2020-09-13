/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Example:
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
*/

class MovingAverage {
    private Queue<integer> window;
    private int sum = 0;
    private int size;

    public MovingAverage(int s) {
        window = new LinkedList<>();
        size = s;
    }

    public double next(int val) {
        if(window.size() == size){
            sum -= window.poll();
        }

        window.add(val);
        sum += val;
        return sum/window.size();
    }
};