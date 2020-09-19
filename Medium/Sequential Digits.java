/*
An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

 

Example 1:

Input: low = 100, high = 300
Output: [123,234]
Example 2:

Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]
 

Constraints:

10 <= low <= high <= 10^9
*/

class Solution {
    
    private List<Integer> result;
    public List<Integer> sequentialDigits(int low, int high) {
        result = new ArrayList<>();
        
        String digits = "123456789";
        for(int len = 2; len <= digits.length(); ++len) {
            for(int index = 0; index <= digits.length() - len; ++index) {
                int value  = Integer.valueOf(digits.substring(index, index + len));
                if(value > high) {
                    return result;
                }
                else if(value >= low) {
                    result.add(value);
                }
            }
        }
        return result;
    }
}