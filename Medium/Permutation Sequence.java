/*
The set [1,2,3,...,n] contains a total of n! unique permutations.
By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
    "123"
    "132"
    "213"
    "231"
    "312"
    "321"

Given n and k, return the kth permutation sequence.

Note:
    Given n will be between 1 and 9 inclusive.
    Given k will be between 1 and n! inclusive.

Example 1:

Input: n = 3, k = 3
Output: "213"

Example 2:

Input: n = 4, k = 9
Output: "2314"
*/
class Solution {
    public String getPermutation(int n, int k) {
        
        List<Integer> nums = new ArrayList<>();
        int[] factorial = new int[n];
        
        
		
        for (int num = 0; num < n; ++num) {
            nums.add(num + 1);
            factorial[num] = num == 0 ? 1 : num * factorial[num - 1];
           
        }
        
        StringBuilder sb = new StringBuilder();
        --k;
        
        while(n-- != 0) {
            int index = (k)/factorial[n];
            
            sb.append(nums.get(index));
            nums.remove(index);
            k = ((k) % factorial[n]);
        }
        
			return sb.toString();
    }
}