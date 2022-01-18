/**
 
Design and implement a data structure for a Least Frequently Used (LFU) cache.

Implement the LFUCache class:

LFUCache(int capacity) Initializes the object with the capacity of the data structure.
int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. 
When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. 
For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
To determine the least frequently used key, a use counter is maintained for each key in the cache. 
The key with the smallest use counter is the least frequently used key.

When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). 
The use counter for a key in the cache is incremented either a get or put operation is called on it.

The functions get and put must each run in O(1) average time complexity.

 

Example 1:

Input
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

Explanation
// cnt(x) = the use counter for key x
// cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
LFUCache lfu = new LFUCache(2);
lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lfu.get(1);      // return 1
                 // cache=[1,2], cnt(2)=1, cnt(1)=2
lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
                 // cache=[3,1], cnt(3)=1, cnt(1)=2
lfu.get(2);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,1], cnt(3)=2, cnt(1)=2
lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                 // cache=[4,3], cnt(4)=1, cnt(3)=2
lfu.get(1);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,4], cnt(4)=1, cnt(3)=3
lfu.get(4);      // return 4
                 // cache=[4,3], cnt(4)=2, cnt(3)=3
 

Constraints:

0 <= capacity <= 104
0 <= key <= 105
0 <= value <= 109
At most 2 * 105 calls will be made to get and put.
*/

class LFUCache {

    private int minFreq, size, capacity;
    private Map<Integer, Node> cache;
    private Map<Integer, DLL> freqMap;
    public LFUCache(int capacity) {
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.minFreq = 0;
        this.size = 0;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Node found = this.cache.get(key);
        int value = -1;
        
        if (found != null) {
            value = found.value;
            updateNode(found);
        }
        
        return value;
    }
    
    public void put(int key, int value) {
        if (this.capacity == 0) {
            return ; // idemp.
        }
        
        Node found = this.cache.get(key);
        if (found != null) {
            found.value = value;
            updateNode(found);
        } else {
            ++this.size;
            
            if (this.size >  this.capacity) {
                DLL minFreqDll = freqMap.get(minFreq);
                Node deleteNode = minFreqDll.removeTail();
                this.cache.remove(deleteNode.key);
                --this.size;
            }
            
            minFreq = 1;
            Node newNode = new Node(key, value);
            
            DLL currDll = freqMap.getOrDefault(1, new DLL());
            currDll.add(newNode);
            freqMap.put(1, currDll);
            this.cache.put(key, newNode);
        }
        
    }
    
    private void updateNode(Node node) {
        DLL currDll = this.freqMap.get(node.freq);
        currDll.remove(node);
        if (node.freq == this.minFreq && currDll.capacity == 0) {
            ++this.minFreq;
        }
        
        node.freq++;
        
        freqMap.computeIfAbsent(node.freq, key -> new DLL()).add(node);
    }
}

class DLL {
    Node head, tail;
    int capacity;
    
    public DLL() {
        this.capacity = 0;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    
    public void remove(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        nextNode.prev = prevNode;
        prevNode.next = nextNode;
        --this.capacity;
    }
    
    public void add(Node node) {
        Node headNext = this.head.next;
        this.head.next = node;
        node.next = headNext;
        node.prev = this.head;
        headNext.prev = node;
        ++this.capacity;
    }
    
    public Node removeTail() {
        if (this.capacity > 0) {
            Node tailNode = this.tail.prev;
            remove(tailNode);
            return tailNode;
        }
        
        return null;
    }
}

class Node {
    Node next, prev;
    int key, value, freq;
    
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.freq = 1;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */