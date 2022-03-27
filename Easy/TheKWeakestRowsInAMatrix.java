import java.util.Arrays;

/*
You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians). 
The soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of all the 0's in each row.

A row i is weaker than a row j if one of the following is true:

The number of soldiers in row i is less than the number of soldiers in row j.
Both rows have the same number of soldiers and i < j.
Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.

Example 1:

Input: mat = 
[[1,1,0,0,0],
 [1,1,1,1,0],
 [1,0,0,0,0],
 [1,1,0,0,0],
 [1,1,1,1,1]], 
k = 3
Output: [2,0,3]
Explanation: 
The number of soldiers in each row is: 
- Row 0: 2 
- Row 1: 4 
- Row 2: 1 
- Row 3: 2 
- Row 4: 5 
The rows ordered from weakest to strongest are [2,0,3,1,4].
Example 2:

Input: mat = 
[[1,0,0,0],
 [1,1,1,1],
 [1,0,0,0],
 [1,0,0,0]], 
k = 2
Output: [0,2]
Explanation: 
The number of soldiers in each row is: 
- Row 0: 1 
- Row 1: 4 
- Row 2: 1 
- Row 3: 1 
The rows ordered from weakest to strongest are [0,2,3,1].
 

Constraints:

m == mat.length
n == mat[i].length
2 <= n, m <= 100
1 <= k <= m
matrix[i][j] is either 0 or 1.
*/

class TheKWeakestRowsInAMatrix {
    public int[] kWeakestRows(int[][] grid, int k) {
        
        if(grid == null || grid.length == 0) {
            return new int[]{};
        }
        
        Soldier[] soldiers = new Soldier[grid.length];   
        for(int row = 0; row < grid.length; ++row) {
            int count = 0;
            for(int col = 0; col < grid[row].length; ++col) {
                if(grid[row][col] == 1) {
                    count++;
                }
                else {
                    break;
                }
            }
            
            // System.out.println(count);
            soldiers[row] = new Soldier(row, count);
        }
        
        Arrays.sort(soldiers, (a, b) -> {
            if(a.count == b.count) {
                return a.rowIndex - b.rowIndex;
            }
            
            return a.count - b.count;
        });
        
        // for(Soldier s : soldiers) {
        //     System.out.println(s.toString());
        // }
        
        int[] result = new int[k];
        
        for(int index = 0; index < k; ++index) {
            result[index] = soldiers[index].rowIndex;  
        }
        
        return result;
    }
    
    class Soldier {
        int rowIndex;
        int count;
        
        public Soldier(int _rowIndex, int _count) {
            rowIndex = _rowIndex;
            count = _count;
        }
        
        public String toString() {
            return Integer.toString(rowIndex) + " " + Integer.toString(count);
        }
    }
}

