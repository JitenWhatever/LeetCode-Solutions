/*
Given a string S, consider all duplicated substrings: (contiguous) substrings of S that occur 2 or more times.  (The occurrences may overlap.)

Return any duplicated substring that has the longest possible length.  (If S does not have a duplicated substring, the answer is "".)

Example 1:

Input: "banana"
Output: "ana"

Example 2:

Input: "abcd"
Output: ""

Note:
    2 <= S.length <= 10^5
    S consists of lowercase English letters.
*/


class Solution {
    
    private int prime = 10000007;
    private int[] power = null;
    
    private String rabinkarp(int len, String S) {
        
        String res = "";
        if(len == 0) {
            return res;
        }
        
        int n = S.length();
        long curr = 0;
        
        Map<Long, List<Integer>>  hash = new HashMap<>();
        
        for(int index = 0; index < len; ++index) {
            curr = (curr * 26  + (S.charAt(index) - 'a')) % prime;
        }
        
        hash.put(curr, new ArrayList<>(Arrays.asList(0)));
        
        for(int index = len; index < n; ++index) {
             curr = ((curr - power[len-1] * (S.charAt(index - len) - 'a')) % prime + prime) % prime;
             curr = (curr * 26 + (S.charAt(index) - 'a')) % prime;
            
             if(!hash.containsKey(curr)) {
                 hash.put(curr, new ArrayList<>(Arrays.asList(index - len + 1)));
             } 
            else {
                
                String ans = S.substring(index - len + 1, index + 1);
                for(int startIndex: hash.get(curr)) {
                    if(S.substring(startIndex, startIndex + len).equals(ans)){
                        return ans;
                    }  
                }

                hash.get(curr).add(index - len + 1);
            }
        }
        
        return ""; 
    }
    
    public String longestDupSubstring(String S) {
        int len = S.length();
        
        power = new int[len];
        
        for(int i = 0; i < len; i++)  {
            power[i] = (i == 0) ? 1 : (power[i-1] * 26) % prime;
        }
        
        int low = 0, high = len - 1;
        
       String  res = "";
        
        while(low <= high) {
            int mid = low + (high - low)/2;
            // System.out.println(mid);
            
            String dup = rabinkarp(mid, S);
            
            if(!dup.isEmpty()) {
                res = dup;
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        
        return res;
    }
}
