/*
Design and implement a data structure for Least Recently Used (LRU) cache. 
It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. 
When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2  capacity  );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. 
Otherwise, add the key-value pair to the cache. 
If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.

Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
 

Constraints:

1 <= capacity <= 3000
0 <= key <= 10^4
0 <= value <= 10^5
At most 2 * 10^5 calls will be made to get and put.
*/
class LRUCache {

    private DLL dll;
    private Map<Integer, Node> keyToNodeMap;
    public LRUCache(int capacity) {
        this.dll = new DLL(capacity);
        this.keyToNodeMap = new HashMap<>();
    }
    
    public int get(int key) {
        int found = -1;
        Node node = this.keyToNodeMap.get(key);
        if(Objects.nonNull(node)){
            found = node.value;
            this.dll.remove(node);
            this.dll.add(node);
        }
        
        return found;
    }
    
    public void put(int key, int value) {
        Node node  = this.keyToNodeMap.get(key);
        if(Objects.nonNull(node)){
            this.dll.remove(node);
            node.value = value;
            this.dll.add(node);
        }
        else{
            if(this.keyToNodeMap.size() == this.dll.capacity){
                this.keyToNodeMap.remove(this.dll.tail.prev.key);
                this.dll.remove(this.dll.tail.prev);
            }
            
            Node newNode = new Node(key, value);
            this.keyToNodeMap.put(key, newNode);
            this.dll.add(newNode);
        }
    }
}

class DLL {
    Node head, tail;
    int capacity;
    public DLL(int capacity) {
        this.capacity = capacity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    
    public void remove(Node node) {
        Node nextNode = node.next;
        Node prevNode = node.prev;
        nextNode.prev = prevNode;
        prevNode.next = nextNode;
    }
    
    public void add(Node node) {
        Node headNext = this.head.next;
        this.head.next = node;
        node.prev = this.head;
        node.next = headNext;
        headNext.prev = node;
    }
}

class Node {
    Node prev, next;
    int value, key;
    
    public Node (int key, int value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

 class LRUCache extends LinkedHashMap<Integer, Integer>{
    private int capacity;
    
    public LR3) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity; 
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


 public class LRUCache {

  class DLinkedNode {
    int key;
    int value;
    DLinkedNode prev;
    DLinkedNode next;
  }

  private void addNode(DLinkedNode node) {
    /**
     * Always add the new node right after head.
     */
    node.prev = head;
    node.next = head.next;

    head.next.prev = node;
    head.next = node;
  }

  private void removeNode(DLinkedNode node){
    /**
     * Remove an existing node from the linked list.
     */
    DLinkedNode prev = node.prev;
    DLinkedNode next = node.next;

    prev.next = next;
    next.prev = prev;
  }

  private void moveToHead(DLinkedNode node){
    /**
     * Move certain node in between to the head.
     */
    removeNode(node);
    addNode(node);
  }

  private DLinkedNode popTail() {
    /**
     * Pop the current tail.
     */
    DLinkedNode res = tail.prev;
    removeNode(res);
    return res;
  }

  private Map<Integer, DLinkedNode> cache = new HashMap<>();
  private int size;
  private int capacity;
  private DLinkedNode head, tail;

  public LRUCache(int capacity) {
    this.size = 0;
    this.capacity = capacity;

    head = new DLinkedNode();
    // head.prev = null;

    tail = new DLinkedNode();
    // tail.next = null;

    head.next = tail;
    tail.prev = head;
  }

  public int get(int key) {
    DLinkedNode node = cache.get(key);
    if (node == null) return -1;

    // move the accessed node to the head;
    moveToHead(node);

    return node.value;
  }

  public void put(int key, int value) {
    DLinkedNode node = cache.get(key);

    if(node == null) {
      DLinkedNode newNode = new DLinkedNode();
      newNode.key = key;
      newNode.value = value;

      cache.put(key, newNode);
      addNode(newNode);

      ++size;

      if(size > capacity) {
        // pop the tail
        DLinkedNode tail = popTail();
        cache.remove(tail.key);
        --size;
      }
    } else {
      // update the value.
      node.value = value;
      moveToHead(node);
    }
  }
}