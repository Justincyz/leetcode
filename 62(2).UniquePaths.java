/*62. Unique Paths
Medium: 
A robot is located at the top-left corner of a m x n grid (marked 'Start' 
in the diagram below).

The robot can only move either down or right at any point in time. The robot 
is trying to reach the bottom-right corner of the grid (marked 'Finish' in 
the diagram below).

How many possible unique paths are there?

S X X X X X X
X X X X X X X
X X X X X X E

Above is a 7 x 3 grid. How many possible unique paths are there?

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28

*/

/*解题思路
这道题目还是很通俗易懂的。每一个位置只能要不从上面走过来，要不从左边走过来。所以对于每一位来说。
我们就累加dp[i-1][j]和dp[i][j-1]的值就好了。那么同时我们可以做一个状态压缩，因为每一层
只跟上一层有关系，所以我们完全可以用一维数组来替代。

*/

//状态压缩后用一维数组就可以了
class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[m];
        dp[0] =1;
        for(int i=0; i<n;i++){
            for(int j =1; j<m; j++){
                dp[j] += dp[j-1];
            }
        }
        return dp[m-1];
    }
}

//没有经过状态压缩的二维数组
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[n+1][m+1];
        dp[1][1] =1;
        for(int i=1; i<=n;i++){
            for(int j =1; j<=m; j++){
                dp[i][j] += (dp[i-1][j]+dp[i][j-1]);
            }
        }
        return dp[n][m];
    }
}