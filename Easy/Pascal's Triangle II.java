/*
Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.

Note that the row index starts from 0.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:
https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif

Input: 3
Output: [1,3,3,1]
Follow up:

Could you optimize your algorithm to use only O(k) extra space?
*/

class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        int current = 1;
        result.add(current);
        for(int index = 1; index <= rowIndex; ++index) {
            long next = (long)current*(rowIndex - index + 1)/index;
            result.add((int)next);
            current = result.get(index);
        }
        
        return result;
    }
}