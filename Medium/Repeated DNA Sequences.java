/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". 
When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

Output: ["AAAAACCCCC", "CCCCCAAAAA"]
*/

class Solution {
    public List<String> findRepeatedDnaSequences(String S) {
        Set<String> hash = new HashSet<>();
        
        List<String> result = new ArrayList<>();
        
        if(S == null || S.length() < 10) {
            return result;
        }
    
        for(int index = 0; index <= S.length() - 10; index++) {     
            String str = S.substring(index, index + 10);
            if(!hash.add(str) && !result.contains(str)){
                // System.out.println(str);
                result.add(str);
            }
        }
        return result;
    }
}