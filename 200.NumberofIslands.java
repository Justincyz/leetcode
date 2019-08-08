/*200. Number of Islands
Medium

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands 
horizontally or vertically. You may assume all four edges of the grid are all 
surrounded by water.

Example 1:
11110
11010
11000
00000

Output: 1

Example 2:
11000
11000
00100
00011

Output: 3
*/

/*解题思路
比较简单直接，对每一个是'1'的点做dfs,把所有与之相连的'1'都变成'0'。然后增加count. 下一次再遇到'1'
就说明这个和之前的那个是断开的两个岛
这道题要注意是一个 char[][] 而不是 int[][]
*/

class Solution {
    public int numIslands(char[][] grid) {
        int count =0;
        for(int i=0; i< grid.length; i++){
            for(int j=0; j< grid[0].length; j++){
                if(grid[i][j] !='0'){
                    helper(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    
    public void helper(char[][] grid, int i, int j){
        if(i<0 || j<0 || i>=grid.length|| j>=grid[0].length || grid[i][j]=='0') return;
        
        grid[i][j] = '0';
        helper(grid, i+1, j);
        helper(grid, i-1, j);
        helper(grid, i, j+1);
        helper(grid, i, j-1);
    }
}

