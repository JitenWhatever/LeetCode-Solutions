/*
Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"
Note: The result may be very large, so you need to return a string instead of an integer.
*/


class Solution {
    public String largestNumber(int[] nums) {
        String[] num = new String[nums.length];
        for(int index = 0; index < nums.length; ++index) {
            num[index] = String.valueOf(nums[index]);
        }
        
        Arrays.sort(num, (a, b)->(b + a).compareTo(a + b));
        
        StringBuilder sb = new StringBuilder();
        for(String str : num) {
            sb.append(str);
        }
        
        String result = sb.toString();
        
        return result.charAt(0) == '0' ? "0" : result;
    }
}