/*
You have a queue of integers, you need to retrieve the first unique integer in the queue.

Implement the FirstUnique class:

FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
void add(int value) insert value to the queue.
 

Example 1:

Input: 
["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
[[[2,3,5]],[],[5],[],[2],[],[3],[]]
Output: 
[null,2,null,2,null,3,null,-1]
Explanation: 
FirstUnique firstUnique = new FirstUnique([2,3,5]);
firstUnique.showFirstUnique(); // return 2
firstUnique.add(5);            // the queue is now [2,3,5,5]
firstUnique.showFirstUnique(); // return 2
firstUnique.add(2);            // the queue is now [2,3,5,5,2]
firstUnique.showFirstUnique(); // return 3
firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
firstUnique.showFirstUnique(); // return -1
Example 2:

Input: 
["FirstUnique","showFirstUnique","add","add","add","add","add","showFirstUnique"]
[[[7,7,7,7,7,7]],[],[7],[3],[3],[7],[17],[]]
Output: 
[null,-1,null,null,null,null,null,17]
Explanation: 
FirstUnique firstUnique = new FirstUnique([7,7,7,7,7,7]);
firstUnique.showFirstUnique(); // return -1
firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7]
firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3]
firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3,3]
firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7,3,3,7]
firstUnique.add(17);           // the queue is now [7,7,7,7,7,7,7,3,3,7,17]
firstUnique.showFirstUnique(); // return 17
Example 3:

Input: 
["FirstUnique","showFirstUnique","add","showFirstUnique"]
[[[809]],[],[809],[]]
Output: 
[null,809,null,-1]
Explanation: 
FirstUnique firstUnique = new FirstUnique([809]);
firstUnique.showFirstUnique(); // return 809
firstUnique.add(809);          // the queue is now [809,809]
firstUnique.showFirstUnique(); // return -1
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^8
1 <= value <= 10^8
At most 50000 calls will be made to showFirstUnique and add.
*/

// add => O(N), showFirstUnique => O(uniqueElements)
class FirstUnique {

    private Map<Integer, Integer> occurrence;
    public FirstUnique(int[] nums) {
        occurrence = new LinkedHashMap<>();
        for (int num : nums) {
            occurrence.put(num, occurrence.getOrDefault(num, 0) + 1);
        }
    }
    
    public int showFirstUnique() {
        for (int key : occurrence.keySet()) {
            if (occurrence.get(key) == 1) {
                return key;
            }
        }
        
        return -1;
    }
    
    public void add(int value) {
        occurrence.put(value, occurrence.getOrDefault(value, 0) + 1);
    }
}

class FirstUnique {

    private Set<Integer> occurrence;
    private Set<Integer> unique;
    public FirstUnique(int[] nums) {
        this.occurrence = new HashSet<>();
        this.unique = new LinkedHashSet<>();
        
        for (int num : nums) {
            this.add(num);
        }
    }
    
//     O(1)
    public int showFirstUnique() {
        if (!this.unique.isEmpty()) {
            return this.unique.iterator().next();
        }
        
        return -1;
    }
    
//     O(log(N))
    public void add(int value) {
        if (this.occurrence.add(value)) {
            this.unique.add(value);
        } else {
            this.unique.remove(value);
        }
    }
}

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */

// Brute Force
 class FirstUnique {

  private Queue<Integer> queue = new ArrayDeque<>();
  
  public FirstUnique(int[] nums) {
    for (int num : nums) {
      queue.add(num);
    }
  }
    
  public int showFirstUnique() {
    for (int num : queue) {
      int count = Collections.frequency(queue, num);
      if (count == 1) {
        return num;
      }
    }
    return -1;
  }
    
  public void add(int value) {
    queue.add(value);    
  }
}
/*
Complexity Analysis

Let K be the length of the initial array passed into the constructor. Let N be the total number of items added onto the queue so far (including those from the constructor).

Time complexity :

constructor: O(K).

We create a new copy of the passed in numbers; this has a cost of O(K) time to create.

add(): O(1)

We perform a single append to a queue; this has a cost of O(1)

showFirstUnique(): O(N^2)

Counting how many times a given item appears in the queue has a cost of O(N). This is true even for the library functions we're using.

In the worst case, we search through the entire queue without finding a unique number. At a cost of O(N) each time, this gives us a cost of O(N^2).

Space complexity : O(N).

The memory used is simply the total number of items currently in the queue.
*/

// Queue and HashMap of Unique-Status
class FirstUnique {

  private Queue<Integer> queue = new ArrayDeque<>();
  private Map<Integer, Boolean> isUnique = new HashMap<>();

  public FirstUnique(int[] nums) {
    for (int num : nums) {
      // Notice that we're calling the "add" method of FirstUnique; not of the queue. 
      this.add(num);
    }
  }

  public int showFirstUnique() {
    // We need to start by "cleaning" the queue of any non-uniques at the start.
    // Note that we know that if a value is in the queue, then it is also in
    // isUnique, as the implementation of add() guarantees this.
    while (!queue.isEmpty() && !isUnique.get(queue.peek())) {
      queue.remove();
    }
    // Check if there is still a value left in the queue. There might be no uniques.
    if (!queue.isEmpty()) {
      return queue.peek(); // We don't want to actually *remove* the value.
    }
    return -1;
  }

  public void add(int value) {
    // Case 1: We need to add the number to the queue and mark it as unique. 
    if (!isUnique.containsKey(value)) {
      isUnique.put(value, true);
      queue.add(value);
    // Case 2 and 3: We need to mark the number as no longer unique.
    } else {
      isUnique.put(value, false);
    }
  }
}
/*
Let K be the length of the initial array passed into the constructor.

Let N be the total number of items added onto the queue so far (including those from the constructor).

Time complexity :

constructor: O(K).

For each of the K numbers passed into the constructor, we're making a call to add(). 
As we will determine below, each call to add() has a cost of O(1).
Therefore, for the K numbers added in the constructor, we get a total cost of K.O(1) = O(K).

add(): O(1)

We check the status of the number in a HashMap, which is an O(1) operation. 
We also optionally modify its status in the HashMap, which, again, is an O(1) operation.

Depending on the status of the number, we add it to the queue, which is again, an O(1) operation.

Therefore, we get an O(1) time complexity for the add() method.

showFirstUnique(): O(1) (amortized).

For this implementation, the showFirstUnique() method needs to iterate down the queue until it finds a unique number. 
For each unique number it encounters along the way, it removes it. 
Removing an item from a queue has a cost of O(1). 
The total number of these removals we need to carry out is proportional to the number of calls to add(), 
because each add() corresponds to at most one removal that will ultimately have to happen. 
Then when we find a unique number, it is an O(1) operation to return it.

Because the number of O(1) removals is proportional to the number of calls to add(), 
we say that the time complexity amortizes across all calls to add() and showFirstUnique(), giving an overall time complexity of O(1) (amortized).

If you're not familiar with amortized time complexity, check out the Wikipedia page.

Space complexity : O(N)

Each number is stored up to once in the queue, and up to once in the HashMap. Therefore, we get an overall space complexity of O(N)
*/

    private static final int mi = Integer.MIN_VALUE, mx = Integer.MAX_VALUE; // dummy values of head and tail.
    private static final DoublyLinkedList head = new DoublyLinkedList(mi),
                                            tail = new DoublyLinkedList(mx);
    private static class DoublyLinkedList {
        
        public DoublyLinkedList prev, next;
        public int val;
        
        public DoublyLinkedList(int v) {
            val = v;
        }
        
    }
    
    private final Map<Integer, DoublyLinkedList> intToNode = new HashMap<>(); // map values to nodes.
    
    public FirstUnique(int[] nums) {
        head.next = tail; // construct empty list: head connects tail.
        tail.prev = head; // construct empty list: tail connects head.
        for (int num : nums) { // add unique numbers to doubly linked list and all entries corresponding to numbers to HashMap.
            add(num);
        }
    }
    
    public int showFirstUnique() {
        return head.next == tail ? -1 : head.next.val; // return -1 if empty list; or the first unique value.
    }

    public void add(int value) {
        DoublyLinkedList node = intToNode.putIfAbsent(value, new DoublyLinkedList(value));
        if (node != null) { // HashMap intToNode already contains entry (value=node).
            remove(node); // value is NOT unique, hence remove it from the doubly linked list.
        }else { // HashMap intToNode does NOT contains key value.
            putToEnd(intToNode.get(value)); // value is unique, hence put it to the end of the doubly linked list.
        }
    }
    
    private boolean remove(DoublyLinkedList node) {
        if (node.prev == null || node.next == null) { // node NOT in the list.
            return false; // remove operation failed.
        }
        node.prev.next = node.next; // modify next pointer of the previous node.
        node.next.prev = node.prev; // modify previous pointer of the next node.
        node.prev = null; // cut off the previous pointer from node to list.
        node.next = null; // cut off the next pointer from node to list.
        return true; // remove operation succeeded.
    }
    
    private boolean putToEnd(DoublyLinkedList node) {
        if (tail == null || tail.prev == null) { // tail node error.
            return false; // operation failed.
        }
        node.next = tail; // assign the tail to the next pointer of node.
        node.prev = tail.prev; // assign the previous node of the tail to the next pointer of node.
        tail.prev = node; // modify previous pointer of the tail.
        node.prev.next = node; // modify next pointer of the previous node.
        return true; // operation succeeded.
    }