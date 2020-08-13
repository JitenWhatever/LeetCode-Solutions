/*
Design an Iterator class, which has:

A constructor that takes a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
A function next() that returns the next combination of length combinationLength in lexicographical order.
A function hasNext() that returns True if and only if there exists a next combination.
 

Example:

CombinationIterator iterator = new CombinationIterator("abc", 2); // creates the iterator.

iterator.next(); // returns "ab"
iterator.hasNext(); // returns true
iterator.next(); // returns "ac"
iterator.hasNext(); // returns true
iterator.next(); // returns "bc"
iterator.hasNext(); // returns false
 

Constraints:

1 <= combinationLength <= characters.length <= 15
There will be at most 10^4 function calls per test.
It's guaranteed that all calls of the function next are valid.
*/

class CombinationIterator {

    private Queue<String> list = null;
    public CombinationIterator(String characters, int combinationLength) {
        list = new LinkedList<>();
        
        helper(characters, combinationLength, 0, "");
    }
    
    
    private void helper(String characters, int combinationLength, int index, String gstring) {
        if(combinationLength == 0) {
            list.add(gstring);
            return;
        }
        
        for(int itr = index; itr < characters.length(); ++itr) {
            helper(characters, combinationLength - 1, itr + 1, gstring + characters.charAt(itr));
        }
    }
    
    public String next() {
        return list.poll();     
    }
    
    public boolean hasNext() {
        return !list.isEmpty();
    }
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */