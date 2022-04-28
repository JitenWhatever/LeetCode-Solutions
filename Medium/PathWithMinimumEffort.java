import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

/*
You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, 
where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), 
and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). 
You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

Example 1:
https://assets.leetcode.com/uploads/2020/10/04/ex1.png

Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.

Example 2:
https://assets.leetcode.com/uploads/2020/10/04/ex2.png

Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
Output: 1
Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].

Example 3:
https://assets.leetcode.com/uploads/2020/10/04/ex3.png

Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
Output: 0
Explanation: This route does not require any effort.
 

Constraints:

rows == heights.length
columns == heights[i].length
1 <= rows, columns <= 100
1 <= heights[i][j] <= 10^6
*/

public class PathWithMinimumEffort {
    
    public int minimumEffortPath(int[][] heights) {
        return backtrack(0, 0, heights, heights.length, heights[0].length, 0);
    }

    int directions[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int maxSoFar = Integer.MAX_VALUE;

    int backtrack(int x, int y, int[][] heights, int row, int col, int maxDifference) {
        if (x == row - 1 && y == col - 1) {
            maxSoFar = Math.min(maxSoFar, maxDifference);
            return maxDifference;
        }
        int currentHeight = heights[x][y];
        heights[x][y] = 0;
        int minEffort = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            int adjacentX = x + directions[i][0];
            int adjacentY = y + directions[i][1];
            if (isValidCell(adjacentX, adjacentY, row, col) && heights[adjacentX][adjacentY] != 0) {
                int currentDifference = Math.abs(heights[adjacentX][adjacentY] - currentHeight);
                int maxCurrentDifference = Math.max(maxDifference, currentDifference);
                if (maxCurrentDifference < maxSoFar) {
                    int result = backtrack(adjacentX, adjacentY, heights, row, col, maxCurrentDifference);
                    minEffort = Math.min(minEffort, result);
                }
            }
        }
        heights[x][y] = currentHeight;
        return minEffort;
    }

    boolean isValidCell(int x, int y, int row, int col) {
        return x >= 0 && x <= row - 1 && y >= 0 && y <= col - 1;
    }
    
    

    public int minimumEffortPath1(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        int[][] differenceMatrix = new int[row][col];
        for (int[] eachRow : differenceMatrix)
            Arrays.fill(eachRow, Integer.MAX_VALUE);
        differenceMatrix[0][0] = 0;
        PriorityQueue<Cell> queue = new PriorityQueue<Cell>((a, b) -> (a.difference.compareTo(b.difference)));
        boolean[][] visited = new boolean[row][col];
        queue.add(new Cell(0, 0, differenceMatrix[0][0]));

        while (!queue.isEmpty()) {
            Cell curr = queue.poll();
            visited[curr.x][curr.y] = true;
            if (curr.x == row - 1 && curr.y == col - 1)
                return curr.difference;
            for (int[] direction : directions) {
                int adjacentX = curr.x + direction[0];
                int adjacentY = curr.y + direction[1];
                if (isValidCell(adjacentX, adjacentY, row, col) && !visited[adjacentX][adjacentY]) {
                    int currentDifference = Math.abs(heights[adjacentX][adjacentY] - heights[curr.x][curr.y]);
                    int maxDifference = Math.max(currentDifference, differenceMatrix[curr.x][curr.y]);
                    if (differenceMatrix[adjacentX][adjacentY] > maxDifference) {
                        differenceMatrix[adjacentX][adjacentY] = maxDifference;
                        queue.add(new Cell(adjacentX, adjacentY, maxDifference));
                    }
                }
            }
        }
        return differenceMatrix[row - 1][col - 1];
    }
    
    public int minimumEffortPath2(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        if (row == 1 && col == 1) return 0;
        UnionFind unionFind = new UnionFind(heights);
        List<Edge> edgeList = unionFind.edgeList;
        Collections.sort(edgeList, (e1, e2) -> e1.difference - e2.difference);

        for (int i = 0; i < edgeList.size(); i++) {
            int x = edgeList.get(i).x;
            int y = edgeList.get(i).y;
            unionFind.union(x, y);
            if (unionFind.find(0) == unionFind.find(row * col - 1)) return edgeList.get(i).difference;
        }
        return -1;
    }
    
    public int minimumEffortPath3(int[][] heights) {
        int left = 0;
        int right = 1000000;
        int result = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (canReachDestinaton(heights, mid)) {
                result = Math.min(result, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    // use bfs to check if we can reach destination with max absolute difference k
    boolean canReachDestinaton(int[][] heights, int k) {
        int row = heights.length;
        int col = heights[0].length;
        Deque<Cell1> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[heights.length][heights[0].length];
        queue.addLast(new Cell1(0, 0));
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            Cell1 curr = queue.removeFirst();
            if(curr.x == row - 1 && curr.y == col - 1) {
                return true;
            }
            for (int[] direction : directions) {
                int adjacentX = curr.x + direction[0];
                int adjacentY = curr.y + direction[1];
                if (isValidCell(adjacentX, adjacentY, row, col) && !visited[adjacentX][adjacentY]) {
                    int currentDifference = Math.abs(heights[adjacentX][adjacentY] - heights[curr.x][curr.y]);
                    if (currentDifference <= k) {
                        visited[adjacentX][adjacentY] = true;
                        queue.addLast(new Cell1(adjacentX, adjacentY));
                    }
                }
            }
        }
        return false;
    }
   
    public int minimumEffortPath4(int[][] heights) {
        int left = 0;
        int right = 1000000;
        int result = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (dfsUtil(heights, mid)) {
                result = Math.min(result, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    boolean dfsUtil(int[][] heights, int mid) {
        int row = heights.length;
        int col = heights[0].length;
        boolean visited[][] = new boolean[row][col];
        return canReachDestinaton(0, 0, heights, visited, row, col, mid);
    }

    boolean canReachDestinaton(int x, int y, int[][] heights,
                                boolean[][] visited, int row, int col, int mid) {
        if (x == row - 1 && y == col - 1) {
            return true;
        }
        visited[x][y] = true;
        for (int[] direction : directions) {
            int adjacentX = x + direction[0];
            int adjacentY = y + direction[1];
            if (isValidCell(adjacentX, adjacentY, row, col) && !visited[adjacentX][adjacentY]) {
                int currentDifference = Math.abs(heights[adjacentX][adjacentY] - heights[x][y]);
                if (currentDifference <= mid) {
                    if (canReachDestinaton(adjacentX, adjacentY, heights, visited, row, col, mid))
                        return true;
                }
            }
        }
        return false;
    }
}
    
class Cell1 {
    int x;
    int y;

    Cell1(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
    
class UnionFind {
    int[] parent;
    int[] rank;
    List<Edge> edgeList;

    public UnionFind(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        parent = new int[row * col];
        edgeList = new ArrayList<>();
        rank = new int[row * col];
        for (int currentRow = 0; currentRow < row; currentRow++) {
            for (int currentCol = 0; currentCol < col; currentCol++) {
                if (currentRow > 0) {
                    edgeList.add(new Edge(currentRow * col + currentCol,
                            (currentRow - 1) * col + currentCol,
                            Math.abs(heights[currentRow][currentCol] - heights[currentRow - 1][currentCol]))
                    );
                }
                if (currentCol > 0) {
                    edgeList.add(new Edge(currentRow * col + currentCol,
                            currentRow * col + currentCol - 1,
                            Math.abs(heights[currentRow][currentCol] - heights[currentRow][currentCol - 1]))
                    );
                }
                parent[currentRow * col + currentCol] = currentRow * col + currentCol;
            }
        }
    }

    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);
        if (parentX != parentY) {
            if (rank[parentX] > rank[parentY]) parent[parentY] = parentX;
            else if (rank[parentX] < rank[parentY]) parent[parentX] = parentY;
            else {
                parent[parentY] = parentX;
                rank[parentX] += 1;
            }
        }
    }
}
    
class Edge {
    int x;
    int y;
    int difference;

    Edge(int x, int y, int difference) {
        this.x = x;
        this.y = y;
        this.difference = difference;
    }
}

class Cell {
    int x;
    int y;
    Integer difference;

    Cell(int x, int y, Integer difference) {
        this.x = x;
        this.y = y;
        this.difference = difference;
    }
}
