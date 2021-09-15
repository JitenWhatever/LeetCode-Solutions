/*
Implement a first in first out (FIFO) queue using only two stacks. 
The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).

Implement the MyQueue class:

void push(int x) Pushes element x to the back of the queue.
int pop() Removes the element from the front of the queue and returns it.
int peek() Returns the element at the front of the queue.
boolean empty() Returns true if the queue is empty, false otherwise.
Notes:

You must use only standard operations of a stack, 
which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, the stack may not be supported natively. 
You may simulate a stack using a list or deque (double-ended queue) as long as 
you use only a stack's standard operations.
 

Example 1:

Input
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 1, 1, false]

Explanation
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false
 

Constraints:

1 <= x <= 9
At most 100 calls will be made to push, pop, peek, and empty.
All the calls to pop and peek are valid.
 

Follow-up: Can you implement the queue such that each operation is amortized O(1) time complexity? 
In other words, performing n operations will take overall O(n) time even if one of those operations may take longer.

*/


class MyQueue {

    /** Initialize your data structure here. */
    private DLL queue;
    private int front;
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>()
    public MyQueue() {
        this.queue = new DLL();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        Node node = new Node(x);
        node.prev = this.queue.tail.prev;
        node.next = this.queue.tail;
        this.queue.tail.prev.next = this.queue.tail.prev = node;
    }
    public void push(int x) {
        if (s1.empty())
            front = x;
        while (!s1.isEmpty())
            s2.push(s1.pop());
        s2.push(x);
        while (!s2.isEmpty())
            s1.push(s2.pop());
    }

    public void push(int x) {
        if (s1.empty())
            front = x;
        s1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        Node node = this.queue.head.next;
        this.queue.head.next = node.next;
        node.next.prev = this.queue.head;
        return node.val;
    }

    public void pop() {
        s1.pop();
        if (!s1.empty())
            front = s1.peek();
    }

    public void pop() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty())
                s2.push(s1.pop());
        }
        s2.pop();    
    }
    
    /** Get the front element. */
    public int peek() {
        return this.queue.head.next.val;
    }

    public boolean empty() {
        return s1.isEmpty();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return this.queue.head.next == this.queue.tail;
    }
    public int peek() {
        return front;
    }

    public int peek() {
        if (!s2.isEmpty()) {
                return s2.peek();
        }
        return front;
    }
}


class DLL {
    Node head, tail;
    
    public DLL() {
        this.head = new Node(-1);
        this.tail = new Node(-1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
}

class Node {
    Node prev, next;
    int val;
    
    public Node (int val) {
        this.val = val;
    }
}


/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */