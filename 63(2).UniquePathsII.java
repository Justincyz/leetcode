/*63. Unique Paths II
Medium: 
A robot is located at the top-left corner of a m x n grid 
(marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the grid 
(marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique 
paths would there be?

Example 1:

Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
*/

/*解题思路
这道题目的解法跟Unique Paths I差不多，唯一要注意的就是假如碰到1的话要跳过。第一种做法是常规
做法，用了一个mxn的 array。但是我们发现其实每一次只跟上一层和当前层有关。所以我们可以用一个
一维的滚动数组来替代。

*/

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if(obstacleGrid[0][0] ==1 || obstacleGrid[m-1][n-1] ==1) return 0;
        int[][] dp = new int[m+1][n+1];
        dp[0][1] = 1;
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(obstacleGrid[i-1][j-1] !=1)
                    dp[i][j] += (dp[i-1][j]+dp[i][j-1]);
            }
        }
        return dp[m][n];
    }
}

/*
注意两个if的条件
*/

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        
        int[] dp = new int[n];
        dp[0] = 1;
      
        for(int[] row: obstacleGrid){
            for(int j=0; j<n; j++){
                if(row[j] ==1){
                    dp[j] =0;
                }
                else if(j>0){
                	 //上一层同样位置的步数加上这一层从左边一位过来的步数
                    dp[j]+=dp[j-1];
                } 
            }
        }
        return dp[n-1];
    }
}