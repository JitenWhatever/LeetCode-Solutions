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
        if(s == null || s.isEmpty()) {
            return s;
        }
        
        Map<Character, Integer> freq = new HashMap<>();
        for(char ch : s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        
        List<Character> sortedCharacters = new ArrayList<>(freq.keySet());
        Collections.sort(sortedCharacters, (a, b) -> freq.get(b) - freq.get(a));
        
        StringBuilder result = new StringBuilder();
        
        for(char ch : sortedCharacters) {
            int repeat = freq.get(ch);
            while(repeat > 0) {
                result.append(ch);
                --repeat;
            }
        }
        
        return result.toString();
    }
}

// bucket Sort
class Solution {
    public String frequencySort(String s) {
        if(s == null || s.isEmpty()) {
            return s;
        }
        
        Map<Character, Integer> freq = new HashMap<>();
        for(char ch : s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        
        int maxCount = Collections.max(freq.values());
        List<List<Character>> buckets = new ArrayList<>();
        for (int bucket = 0; bucket <= maxCount; bucket++) {
            buckets.add(new ArrayList<Character>());
        }
        for(char ch : freq.keySet()) {
            int count = freq.get(ch);
            buckets.get(count).add(ch);
        }
        
        StringBuilder result = new StringBuilder();
        
        for(int bucket = buckets.size() - 1; bucket >= 1; --bucket) {
           for(Character ch : buckets.get(bucket)) {
               for(int repeat = 0; repeat < bucket; ++repeat) {
                   result.append(ch);
               }
           }
        }
    
        return result.toString();
    }
}