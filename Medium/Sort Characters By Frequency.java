/*
Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.

Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
*/

class Solution {
    public String frequencySort(String s) {
        
        int[] hash = new int[128];
        
        for(char ch : s.toCharArray()) {
            hash[ch]++;
        }
   
        List<Integer> list = new ArrayList<Integer>();
        
        for(int count : hash) {
            if(count != 0) {
                list.add(count);
            }
        }
        
        Collections.sort(list,Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        
        for(int charCount : list) {
            
            for(int index = 0; index < 128; ++index) {
                if(charCount == hash[index]) {
                    
                    while(hash[index]-- > 0) {
                        sb.append((char)index);
                    }
                    break;
                }
            }
        }
        
        return sb.toString();
    }
}