/*
Given a string S, check if the letters can be rearranged so that two characters 
that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].
*/

class Solution {

    // 1 ms
     public String reorganizeString(String S) {
        int N = S.length(), maxChar = 0;
        int[] counts = new int[26];
        for (char ch : S.toCharArray()) {
            if(++counts[ch - 'a'] >  counts[maxChar]) {
                maxChar = ch - 'a';
            }
        }
        
        if(2*counts[maxChar] - 1 > N) {
            return "";
        }
        
        char[] result = new char[N];
        int index = 0;
        
        while(counts[maxChar] > 0) {
            result[index] = (char)('a' + maxChar);
            counts[maxChar]--;
            index += 2;
        }
    
        for(int ch = 0; ch < 26; ++ch) {
            while(counts[ch] > 0) {
                if(index >= N) {
                    index = 1;
                }
                 // System.out.println(index);
                result[index] = (char)('a' + ch);
                counts[ch]--;
                index += 2;
            }
        }

        return String.valueOf(result);
    }

    // 11 ms
    public String reorganizeString1(String S) { 
        HashMap<Character, Integer> hash = new HashMap<>();
        
        for(char ch : S.toCharArray()) {
            hash.put(ch, hash.getOrDefault(ch, 0) + 1);
        }
        
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> hash.get(b) - hash.get(a));
        
        maxHeap.addAll(hash.keySet());
        
        StringBuilder sb = new StringBuilder();
        
        while(maxHeap.size() > 1) {
            char  first = maxHeap.poll();
            char  second = maxHeap.poll();
            
            sb.append(first);
            sb.append(second);
            
            hash.put(first, hash.get(first) - 1);
            hash.put(second, hash.get(second) - 1);
            
            if(hash.get(first) > 0) {
                maxHeap.add(first);
            }
            
            
            if(hash.get(second) > 0) {
                maxHeap.add(second);
            }
        }
        
        if(!maxHeap.isEmpty()) {
            char ch = maxHeap.poll();
            if(hash.get(ch) > 1) {
                return "";
            }
            
            sb.append(ch);
        }
         return sb.toString();   
    }
}