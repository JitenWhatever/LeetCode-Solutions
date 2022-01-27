/*
Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.

Example 1:

Input: nums = [3,10,5,25,2,8]
Output: 28
Explanation: The maximum result is 5 XOR 25 = 28.
Example 2:

Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
Output: 127
 

Constraints:

1 <= nums.length <= 2 * 10^5
0 <= nums[i] <= 2^31 - 1
*/

class Solution {
  public int findMaximumXOR(int[] nums) {
    int maxNum = nums[0];
    for(int num : nums) maxNum = Math.max(maxNum, num);
    // length of max number in a binary representation
    int L = (Integer.toBinaryString(maxNum)).length();

    int maxXor = 0, currXor;
    Set<Integer> prefixes = new HashSet<>();
    for(int i = L - 1; i > -1; --i) {
      // go to the next bit by the left shift
      maxXor <<= 1;
      // set 1 in the smallest bit
      currXor = maxXor | 1;
      prefixes.clear();
      // compute all possible prefixes 
      // of length (L - i) in binary representation
      for(int num: nums) prefixes.add(num >> i);
      // Update maxXor, if two of these prefixes could result in currXor.
      // Check if p1^p2 == currXor, i.e. p1 == currXor^p2.
      for(int p: prefixes) {
        if (prefixes.contains(currXor^p)) {
          maxXor = currXor;
          break;
        }
      }
    }
    return maxXor;
  }
}

class TrieNode {
  HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
  public TrieNode() {}
}

class Solution {
  public int findMaximumXOR(int[] nums) {
    // Compute length L of max number in a binary representation
    int maxNum = nums[0];
    for(int num : nums) maxNum = Math.max(maxNum, num);
    int L = (Integer.toBinaryString(maxNum)).length();

    // zero left-padding to ensure L bits for each number
    int n = nums.length, bitmask = 1 << L;
    String [] strNums = new String[n];
    for(int i = 0; i < n; ++i) {
      strNums[i] = Integer.toBinaryString(bitmask | nums[i]).substring(1);
    }

    TrieNode trie = new TrieNode();
    int maxXor = 0;
    for (String num : strNums) {
      TrieNode node = trie, xorNode = trie;
      int currXor = 0;
      for (Character bit : num.toCharArray()) {
        // insert new number in trie  
        if (node.children.containsKey(bit)) {
          node = node.children.get(bit);
        } else {
          TrieNode newNode = new TrieNode();
          node.children.put(bit, newNode);
          node = newNode;
        }

        // compute max xor of that new number 
        // with all previously inserted
        Character toggledBit = bit == '1' ? '0' : '1';
        if (xorNode.children.containsKey(toggledBit)) {
          currXor = (currXor << 1) | 1;
          xorNode = xorNode.children.get(toggledBit);
        } else {
          currXor = currXor << 1;
          xorNode = xorNode.children.get(bit);
        }
      }
      maxXor = Math.max(maxXor, currXor);
    }

    return maxXor;
  }
}

class Solution {
    
    class TrieNode {
        TrieNode left;
        TrieNode right;
        
        TrieNode(){
            this.left = null;
            this.right = null;
        }
    }
    
    private TrieNode root = new TrieNode();
    
    private void insert(int num, TrieNode root) {
        TrieNode curr = root;
        
        for(int bit = 31; bit >= 0; --bit) {
            int mask = (num>>bit)&1;
            
            if(mask == 0) {
                if(curr.left == null) {
                    curr.left = new TrieNode();
                }
                
                curr = curr.left;
            }
            else {
                if(curr.right == null) {
                    curr.right = new TrieNode();
                }
                
                curr = curr.right;
            }
        }
    }
    
    public int findMaximumXOR(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        for(int num : nums) {
            insert(num, root);
        }
        
        TrieNode curr =  root;
        int maxXor = Integer.MIN_VALUE;
        int currXor = 0;

        for(int num : nums) {            
            for(int bit = 31; bit >= 0; --bit) {
                int mask = (num>>bit)&1;
                
                if(mask == 0) {
                    if(curr.right != null) {
                        currXor += (1<<bit);
                        curr = curr.right;
                    }   
                    else {
                        curr = curr.left;
                    }
                }
                else {
                     if(curr.left != null) {
                        currXor += (1<<bit);
                         curr = curr.left;
                    }   
                    else {
                        curr = curr.right;
                    }
                }
            }
            
            maxXor = Math.max(maxXor, currXor);
            currXor  = 0;
            curr = root;
        }
        
        return maxXor;
    }
}