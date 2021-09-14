/*
Implement a last-in-first-out (LIFO) stack using only two queues. 
The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).

Implement the MyStack class:

void push(int x) Pushes element x to the top of the stack.
int pop() Removes the element on the top of the stack and returns it.
int top() Returns the element on the top of the stack.
boolean empty() Returns true if the stack is empty, false otherwise.
Notes:

You must use only standard operations of a queue, which means that only push to back, 
peek/pop from front, size and is empty operations are valid.
Depending on your language, the queue may not be supported natively.
 You may simulate a queue using a list or deque (double-ended queue)
  as long as you use only a queue's standard operations.
 

Example 1:

Input
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 2, 2, false]

Explanation
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // return 2
myStack.pop(); // return 2
myStack.empty(); // return False
 

Constraints:

1 <= x <= 9
At most 100 calls will be made to push, pop, top, and empty.
All the calls to pop and top are valid.
 

Follow-up: Can you implement the stack using only one queue?
*/


class MyStack {

    /** Initialize your data structure here. */
    private DLL stack;
    public MyStack() {
        this.stack = new DLL();
    }
    
    /** Push element x onto stack. */
    public void push(int val) {
        Node node = new Node(val);
        node.next = this.stack.tail;
        node.prev = this.stack.tail.prev;
        this.stack.tail.prev = this.stack.tail.prev.next = node;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        Node node = this.stack.tail.prev;
        this.stack.tail.prev = node.prev;
        node.prev.next = this.stack.tail;
        
        return node.val;
    }
    
    /** Get the top element. */
    public int top() {
        return this.stack.tail.prev.val;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return this.stack.head.next == this.stack.tail;
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
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

 class MyStack {

    /** Initialize your data structure here. */
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();
    private int top;
    public MyStack() {
        
    }
    
    /** Push element x onto stack. */
    
    // O(N)
    public void push(int x) {
        q2.add(x);
        top = x;
        while (!q1.isEmpty()) {                
            q2.add(q1.remove());
        }
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }
    
    // O(1)
   public void push(int x) {
        q1.add(x);
        top = x;
    }

    // O(N)
    public void push(int x) {
        q1.add(x);
        int sz = q1.size();
        while (sz > 1) {
            q1.add(q1.remove());
            sz--;
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    
    // O(1)
    public void pop() {
        q1.remove();
        if (!q1.isEmpty()) {
            top = q1.peek();
        }
    }
    // O(N)
    public int pop() {
        while (q1.size() > 1) {
            top = q1.remove();
            q2.add(top);
        }
        q1.remove();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }
    
    /** Get the top element. */
    // O(1)
    public int top() {
        return q1.peek();
    }
    
    public int top() {
         return top;
    }
    
    /** Returns whether the stack is empty. */
    // O(1)
   public boolean empty() {
        return q1.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */