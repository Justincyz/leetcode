/*980. Unique Paths III
Hard
链接: https://leetcode.com/problems/unique-paths-iii/

On a 2-dimensional grid, there are 4 types of squares:

1 represents the starting square.  There is exactly one starting square.
2 represents the ending square.  There is exactly one ending square.
0 represents empty squares we can walk over.
-1 represents obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

 

Example 1:

Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
Example 2:

Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
Example 3:

Input: [[0,1],[2,0]]
Output: 0
Explanation: 
There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.

*/

/*解题思路
题目要求我们从起始点1到终点2之间，要经过所有不为-1的位置，也就是0的位置。问这样的路径一共有多少种。
其实就是一个比较简单的Backtracking + DFS的解法。我们Backtract的时候每一次把走过
的位置先标为-1，结束之后再标记回来。

*/

class Solution {
    int res =0 ;
    int[][] dirs = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    public int uniquePathsIII(int[][] grid) {
        int n = grid.length, m = grid[0].length, countZero = 0;
        int[] start = new int[2], end = new int[2];
        
        for(int i=0; i< n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == 1){
                    start[0] = i; start[1] = j;
                }else if(grid[i][j] == 2){
                    end[0] = i; end[1] = j;
                }else if(grid[i][j] == 0){
                    countZero++;
                }
            }
        }
       
        grid[start[0]][start[1]] = -1;
     
        dfs(grid, start, end, countZero);
        return res;
    }
    
    public void dfs(int[][] grid, int[] start, int[] end, int countZero){    
        if(start[0] == end[0] && start[1] == end[1]){
            if(countZero == -1){
                 res++;
            }
            return;
        }
        
        for(int[] dir: dirs){
            int x = start[0]+dir[0];
            int y = start[1]+dir[1];
            if(x < 0 || y<0 || x>= grid.length || y >= grid[0].length || grid[x][y] == -1) continue;
            int t  = grid[x][y];
            grid[x][y] = -1;
            dfs(grid, new int[]{x, y}, end, countZero-1);
            grid[x][y] = t;
        }
        
    }
}