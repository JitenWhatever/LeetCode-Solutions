/*
Design a data structure that accepts a stream of integers and 
checks if it has a pair of integers that sum up to a particular value.

Implement the TwoSum class:

TwoSum() Initializes the TwoSum object, with an empty array initially.
void add(int number) Adds number to the data structure.
boolean find(int value) Returns true if there exists any pair of numbers whose sum is equal to value, 
otherwise, it returns false.
 

Example 1:

Input
["TwoSum", "add", "add", "add", "find", "find"]
[[], [1], [3], [5], [4], [7]]
Output
[null, null, null, null, true, false]

Explanation
TwoSum twoSum = new TwoSum();
twoSum.add(1);   // [] --> [1]
twoSum.add(3);   // [1] --> [1,3]
twoSum.add(5);   // [1,3] --> [1,3,5]
twoSum.find(4);  // 1 + 3 = 4, return true
twoSum.find(7);  // No two integers sum up to 7, return false
 

Constraints:

-10^5 <= number <= 10^5
-2^31 <= value <= 2^31 - 1
At most 104 calls will be made to add and find.
*/

import java.util.Collections;

class TwoSum {
  private ArrayList<Integer> nums;
  private boolean is_sorted;

  /** Initialize your data structure here. */
  public TwoSum() {
    this.nums = new ArrayList<Integer>();
    this.is_sorted = false;
  }

  /** Add the number to an internal data structure.. */
  public void add(int number) {
    this.nums.add(number);
    this.is_sorted = false;
  }

  /** Find if there exists any pair of numbers which sum is equal to the value. */
  public boolean find(int value) {
    if (!this.is_sorted) {
      Collections.sort(this.nums);
      this.is_sorted = true;
    }
    int low = 0, high = this.nums.size() - 1;
    while (low < high) {
      int twosum = this.nums.get(low) + this.nums.get(high);
      if (twosum < value)
        low += 1;
      else if (twosum > value)
        high -= 1;
      else
        return true;
    }
    return false;
  }
}


class TwoSum {
    private HashMap<Integer, Integer> num_counts;

    /** Initialize your data structure here. */
    public TwoSum() {
        this.num_counts = new HashMap<>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        this.num_counts.put(number, num_counts.getOrDefault(number, 0) + 1);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for(Integer key : this.num_counts.keySet()) {
            int complement = value - key;
            if(complement != key) {
                if(this.num_counts.containsKey(complement))
                    return true;
            } else if(this.num_counts.get(key) > 1)
                return true;
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */