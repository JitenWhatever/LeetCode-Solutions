/*

Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.

 

Example 1:

Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:

Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 

Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
*/

class Solution {
    public int countSubstrings(String s) {
        
        if(Objects.isNull(s) || s.isEmpty()) {
            return 0;
        }
        
        int palindromes = 0;
        
        for(int index = 0; index < s.length(); ++index) {
            palindromes += getPalindromes(index, index, s);
            palindromes += getPalindromes(index, index + 1, s);
        }
        
        return palindromes;
    }
    
    private int getPalindromes(int left, int right, String s) {
        int palindromes = 0;
        
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
            ++palindromes;
        }
        
        return palindromes;
    }
}

/* 
Better approaches do exist to solve this problem in sub-quadratic time, however those are significantly complex and impractical to implement in most interviews.

Some known approaches are:

Binary Search with a fast rolling hash algorithm (like Rabin-Karp). 
This approach tries to optimize Approach #3 by speeding up the time to figure out the largest palindrome for each of the 2N-12N−1 centers in logarithmic time. 
This approach counts all palindromic substrings in O(N \log{N})O(NlogN) time. Here's a Quora answer by T.V. Raziman which explains this approach well.
https://www.quora.com/How-can-we-find-the-number-of-palindromic-substrings-in-a-string-in-linear-time/answer/Raziman-T-V?ch=10&share=4957c9e6&srid=OVm2

Palindromic trees (also known as EERTREE). 
It is a data structure invented by Mikhail Rubinchik which links progressively larger palindromic substrings within a string. 
The tree construction takes linear time, and the number of palindromic substrings can be counted while constructing the tree in O(N)O(N) time. 
Additionally, the tree can be used to compute how many distinct palindromic substrings are in a string (it's just the number of nodes in the tree) and how frequently each such palindrome occurs. 
This blog post does a good job of explaining the construction of a palindromic tree.
https://arxiv.org/abs/1506.04862
http://adilet.org/blog/palindromic-tree/https://medium.com/@alessiopiergiacomi/eertree-or-palindromic-tree-82453e75025b

Suffix Arrays with quick Lowest common Ancestor (LCA) lookups. 
This approach utilizes Ukonnen's algorithm to build suffix trees for the input string and its reverse in linear time. 
Subsequently, quick LCA lookups can be used to find maximum palindromes, which are themselves composed of smaller palindromes. 
This approach can produce a count of all palindromic substrings in O(N)O(N) time. The original paper describes the algorithm, a
nd this Quora answer demonstrates an example.
http://par.cse.nsysu.edu.tw/~algo/paper/paper06/A24.pdf
https://www.quora.com/How-can-we-find-the-number-of-palindromic-substrings-in-a-string-in-linear-time/answer/Aniket-Alshi?ch=10&share=81e48fc8&srid=OVm2

Manacher's algorithm. 
It's basically Approach #3, on steroids.TM The algorithm reuses computations done for previous palindromic centers to process new centers in sub-linear time (which reduces progressively for each new center). 
This algorithm counts all palindromic substrings in O(N)O(N) time. This e-maxx post provides a fairly simple implementation of this algorithm.
https://cp-algorithms.com/string/manacher.html
*/