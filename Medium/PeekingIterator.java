/*
Design an iterator that supports the peek operation on an existing iterator in addition to the hasNext and the next operations.

Implement the PeekingIterator class:

PeekingIterator(Iterator<int> nums) Initializes the object with the given integer iterator iterator.
int next() Returns the next element in the array and moves the pointer to the next element.
boolean hasNext() Returns true if there are still elements in the array.
int peek() Returns the next element in the array without moving the pointer.
Note: Each language may have a different implementation of the constructor and Iterator, 
but they all support the int next() and boolean hasNext() functions.

 
Example 1:

Input
["PeekingIterator", "next", "peek", "next", "next", "hasNext"]
[[[1, 2, 3]], [], [], [], [], []]
Output
[null, 1, 2, 2, 3, false]

Explanation
PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [1,2,3]
peekingIterator.next();    // return 1, the pointer moves to the next element [1,2,3].
peekingIterator.peek();    // return 2, the pointer does not move [1,2,3].
peekingIterator.next();    // return 2, the pointer moves to the next element [1,2,3]
peekingIterator.next();    // return 3, the pointer moves to the next element [1,2,3]
peekingIterator.hasNext(); // return False
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 1000
All the calls to next and peek are valid.
At most 1000 calls will be made to next, hasNext, and peek.
 
Follow up: How would you extend your design to be generic and work with all types, not just integer?
*/
import java.util.Iterator;
import java.util.NoSuchElementException;
class PeekingIterator implements Iterator<Integer> {
    
    private Iterator<Integer> iter;
    private Integer peekedValue = null;

    public PeekingIterator(Iterator<Integer> iterator) {
        iter = iterator;
    }

    public Integer peek() {
        /* If there's not already a peeked value, get one out and store
            * it in the peekedValue variable. We aren't told what to do if
            * the iterator is actually empty -- here I have thrown an exception
            * but in an interview you should definitely ask! This is the kind of
            * thing they expect you to ask about. */
        if (peekedValue == null) {
            if (!iter.hasNext()) {
                throw new NoSuchElementException();
            }
            peekedValue = iter.next();
        }
        return peekedValue;
    }

    @Override
    public Integer next() {
        /* Firstly, we need to check if we have a value already
            * stored in the peekedValue variable. If we do, we need to
            * return it and also set peekedValue to null so that the value
            * isn't returned again. */
        if (peekedValue != null) {
            Integer toReturn = peekedValue;
            peekedValue = null;
            return toReturn;
        }
        /* As per the Java Iterator specs, we should throw a NoSuchElementException
            * if the next element doesn't exist. */
        if (!iter.hasNext()) {
            throw new NoSuchElementException();
        }
        /* Otherwise, we need to return a new value. */
        return iter.next();
    }

    @Override
    public boolean hasNext() {
        /* If there's a value waiting in peekedValue, or if there are values
            * remaining in the iterator, we should return true. */
        return peekedValue != null || iter.hasNext();
    }
}

class PeekingIterator1 implements Iterator<Integer> {

    private Iterator<Integer> iter;
    private Integer next = null;

    public PeekingIterator1(Iterator<Integer> iterator) {
        // Avoid an exception being thrown in the constructor.
        if (iterator.hasNext()) {
            next = iterator.next();
        }
        iter = iterator;
    }

    public Integer peek() {
        return next;
    }

    @Override
    public Integer next() {
        /* As per the Java Iterator specs, we should throw a NoSuchElementException
         * if the next element doesn't exist. */
        if (next == null) {
            throw new NoSuchElementException();
        }
        Integer toReturn = next;
        next = null;
        if (iter.hasNext()) {
            next = iter.next();
        }
        return toReturn;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }
}
