/*64. Minimum Path Sum
Medium: 
Given a m x n grid filled with non-negative numbers, find a path 
from top left to bottom right which minimizes the sum of all numbers 
along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
*/

/*解题思路
题目告诉我们，有一个二维数组，数组中每个元素的值都不一样，那么要求我们找到从左上角到右下角
的线路并且经过的path总和最小。其实62, 63, 64这三道题目用的解法都一样，除了普通的二维dp数组
之外，也都可以用滚动数组的办法来做。这道题目用状态压缩有一个点要特别注意。对于第一行和第一列来说，
只能取到从左边来的和从上面来的值。那么首先我们要处理数组的初始化，我们给每一位赋值成MAX_VALUE
让第一行的元素只能取从左边过来的值。同时每一次我们往下遍历一行的时候，首先要把第0位的值变成
MAX_VALUE, 以至于左边和上面的值两者只能取到上面的值。

那第二种就是普通的用二维数组来解决问题了。同样需要给第一行和第一列的值都赋为MAX_VALUE.否则
会有取值问题。
*/


// 1ms, 滚动数组dp
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] =0;
        for(int i=0; i<m; i++){
            dp[0] = Integer.MAX_VALUE;
            for(int j =1; j<=n; j++){
                dp[j] = Math.min(dp[j-1], dp[j])+grid[i][j-1];
              
            }
        }
        return dp[n];
    }
}

// 4ms 二维普通数组
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        for (int i = 1; i < n; i++) grid[0][i] += grid[0][i - 1];
        for (int i = 1; i < m; i++) grid[i][0] += grid[i - 1][0];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }
}