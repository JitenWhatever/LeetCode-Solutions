/*
A string is called happy if it does not have any of the strings 'aaa', 'bbb' or 'ccc' as a substring.

Given three integers a, b and c, return any string s, which satisfies following conditions:

s is happy and longest possible.
s contains at most a occurrences of the letter 'a', 
at most b occurrences of the letter 'b' and at most c occurrences of the letter 'c'.
s will only contain 'a', 'b' and 'c' letters.
If there is no such string s return the empty string "".

 

Example 1:

Input: a = 1, b = 1, c = 7
Output: "ccaccbcc"
Explanation: "ccbccacc" would also be a correct answer.
Example 2:

Input: a = 2, b = 2, c = 1
Output: "aabbc"
Example 3:

Input: a = 7, b = 1, c = 0
Output: "aabaa"
Explanation: It's the only correct answer in this case.
 

Constraints:

0 <= a, b, c <= 100
a + b + c > 0
*/

class Solution {
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder result = new StringBuilder();
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((p1, p2) -> p2.value - p1.value);
       
        if (a > 0) {
            maxHeap.add(new Pair('a', a));
        }
        if (b > 0) {
            maxHeap.add(new Pair('b', b));
        }
        if (c > 0) {
            maxHeap.add(new Pair('c', c));
        }
        
        while (!maxHeap.isEmpty()) {
            Pair first = maxHeap.poll();
            
            for (int itr = 0; itr < 2 && first.value > 0; ++itr, first.value -= 1){
                result.append(first.key);
                 // System.out.println(itr);
            }
           
            if (first.value > 0 && !maxHeap.isEmpty()) {
                if (maxHeap.peek().value <= first.value) {
                    Pair second = maxHeap.poll();
                    result.append(second.key);
                    second.value -= 1;
                    
                    if (second.value > 0) {
                        maxHeap.offer(second);
                    }
                }
                
                maxHeap.offer(first);
            }
        }
     
        return result.toString();
    }
}

class Pair {
    public char key;
    public int value;
    
    public Pair(char key, int value) {
        this.key = key;
        this.value = value;
    }
}

class Solution {
    public String longestDiverseString(int a, int b, int c) {
        return generate(a, b, c, "a", "b", "c");
    }
    
    private String generate(int a, int b, int c, String aa, String bb, String cc) {
        if (a < b)
            return generate(b, a, c, bb, aa, cc);
        if (b < c)
            return generate(a, c, b, aa, cc, bb);
        if (b == 0)
            return aa.repeat(Math.min(2, a));
        int use_a = Math.min(2, a), use_b = a - use_a >= b ? 1 : 0; 
        return aa.repeat(use_a) + bb.repeat(use_b) +
            generate(a - use_a, b - use_b, c, aa, bb, cc);    
    }
}

class Solution {
    public static String longestDiverseString(int a, int b, int c) {
        int aCount = 0, bCount = 0, cCount = 0;
        StringBuilder sb = new StringBuilder();
        while(!((a==0 && b==0 && c==0) || ((a+b==0||b+c==0||a+c==0)&&(aCount==2 || bCount==2 || cCount==2)))){
            if((a>=b && a>=c && aCount<2) || (bCount==2 && a>0) || (cCount==2 && a>0)){
                sb.append('a');
                a--;
                aCount++;
                bCount = 0;
                cCount = 0;
            }
            else if((b>=a && b>=c && bCount<2) || (aCount==2 && b>0) || (cCount==2 && b>0)){
                sb.append('b');
                b--;
                bCount++;
                aCount = 0;
                cCount = 0;
            }
            else if((c>=a && c>=b && cCount<2) || (aCount==2 && c>0) || (bCount==2 && c>0)){
                sb.append('c');
                c--;
                cCount++;
                aCount = 0;
                bCount = 0;
            }
        }
        return sb.toString();
    }
}