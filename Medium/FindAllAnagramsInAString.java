import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given two strings s and p, return an array of all the start indices of p's anagrams in s. 
You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, 
typically using all the original letters exactly once.

Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 
Constraints:

1 <= s.length, p.length <= 3 * 10^4
s and p consist of lowercase English letters.
*/

class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<Integer>();
        if(s.length() < p.length()) {
            return result;
        }
        
        Integer windowSize = p.length(), windowLeft = 0, windowRight = 0;
        int[] hash = new int[26];
        
        for(int index = 0; index < p.length(); ++index) {
            hash[p.charAt(index) - 'a']++;
        }

        while(windowRight < s.length()) {
            
            if(hash[s.charAt(windowRight++) - 'a']-- >= 1) {
                
                windowSize--;
            }
            
            if(windowSize == 0) {
                result.add(windowLeft);
            }
            
            if(windowRight - windowLeft == p.length() && hash[s.charAt(windowLeft++) - 'a']++ >= 0) {
                windowSize++;
            }
        }
        
        return result;
    }

  public List<Integer> findAnagrams1(String s, String p) {
    int ns = s.length(), np = p.length();
    if (ns < np) return new ArrayList();

    Map<Character, Integer> pCount = new HashMap();
    Map<Character, Integer> sCount = new HashMap();
    // build reference hashmap using string p
    for (char ch : p.toCharArray()) {
      if (pCount.containsKey(ch)) {
        pCount.put(ch, pCount.get(ch) + 1);
      }
      else {
        pCount.put(ch, 1);
      }
    }

    List<Integer> output = new ArrayList();
    // sliding window on the string s
    for (int i = 0; i < ns; ++i) {
      // add one more letter 
      // on the right side of the window
      char ch = s.charAt(i);
      if (sCount.containsKey(ch)) {
        sCount.put(ch, sCount.get(ch) + 1);
      }
      else {
        sCount.put(ch, 1);
      }
      // remove one letter 
      // from the left side of the window
      if (i >= np) {
        ch = s.charAt(i - np);
        if (sCount.get(ch) == 1) {
          sCount.remove(ch);
        }
        else {
          sCount.put(ch, sCount.get(ch) - 1);
        }
      }
      // compare hashmap in the sliding window
      // with the reference hashmap
      if (pCount.equals(sCount)) {
        output.add(i - np + 1);
      }
    }
    return output;
  }

  public List<Integer> findAnagrams2(String s, String p) {
    int ns = s.length(), np = p.length();
    if (ns < np) return new ArrayList();

    int [] pCount = new int[26];
    int [] sCount = new int[26];
    // build reference array using string p
    for (char ch : p.toCharArray()) {
      pCount[(int)(ch - 'a')]++;
    }

    List<Integer> output = new ArrayList();
    // sliding window on the string s
    for (int i = 0; i < ns; ++i) {
      // add one more letter 
      // on the right side of the window
      sCount[(int)(s.charAt(i) - 'a')]++;
      // remove one letter 
      // from the left side of the window
      if (i >= np) {
        sCount[(int)(s.charAt(i - np) - 'a')]--;
      }
      // compare array in the sliding window
      // with the reference array
      if (Arrays.equals(pCount, sCount)) {
        output.add(i - np + 1);
      }
    }
    return output;
  }
}