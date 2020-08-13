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
public:
    queue<string> list;
    CombinationIterator(string characters, int combinationLength) {
        helper(characters, 0, "", combinationLength);
    }
    
    void helper(string characters, int index, string gstring, int combinationLength) {
        if(combinationLength == 0) {
            list.push(gstring);
            return;
        }
        
        for(int itr = index; itr < characters.length(); itr++) {
            helper(characters, itr + 1, gstring + characters[itr], combinationLength - 1);
        }
    }
    
    string next() {
        string ans = list.front();
        list.pop();
        
        return ans;
    }
    
    bool hasNext() {
        return !list.empty();
    }
};

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator* obj = new CombinationIterator(characters, combinationLength);
 * string param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */