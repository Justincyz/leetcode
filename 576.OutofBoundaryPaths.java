/*576. Out of Boundary Paths
Medium: 

There is an m by n grid with a ball. Given the start coordinate (i,j) 
of the ball, you can move the ball to adjacent cell or cross the grid 
boundary in four directions (up, down, left, right). However, you can 
at most move N times. Find out the number of paths to move the ball out 
of grid boundary. The answer may be very large, return it after mod 10^9 + 7.

Example 1:

Input: m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6
Explanation:
_________
|X  |   |
---------
|   |   |
_________
球在(0,0)位置的时候，如果在两步之内总共有六种方法可以越界。一步的时候有两种，两步的时候有四种

*/

/*解题思路: 近似题目 688. Knight Probability in Chessboard
这道题给了我们一个二维的数组，某个位置放个足球，每次可以在上下左右四个方向中任意移动一步，
总共可以移动N步，问我们总共能有多少种移动方法能把足球移除边界，
这里的两个方法都和688题里的一样。只是在判断条件的时候稍有不同。

下面这种方法虽然也是用的DP解法，但是DP数组的定义和上面的不一样，这种解法相当于使用了BFS搜索，
以(i, j)为起始点。初始化dp[i][j]为1，总共N步，进行N次循环，每次都新建一个mxn大小的临时数组temp，
然后就是对于遍历到的每个位置，都遍历其四个相邻位置，如果相邻位置越界了，那么我们用当前位置的dp值更
新结果res，因为此时dp值的意义就是从(i,j)到越界位置的路径数。如果没有，我们将当前位置的dp值赋给temp数
组的对应位置，这样在遍历完所有的位置时，将数组t整个赋值给dp，然后进入下一步的循环：

*/ 
//11ms, beats 30
class Solution {
    
    public int findPaths(int m, int n, int N, int i, int j) {
        int[][] board = new int[m][n];
        board[i][j] = 1;
        int mod = 1000000007;
        int total = 0;
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        for(int s =0; s<N; s++){
            int[][] temp = new int[m][n];
            for(int p=0; p< m; p++){
                for(int q = 0; q< n; q++){
                    for(int[] dir : dirs){
                        int x = p+dir[0];
                        int y = q+dir[1];
                        if(x<0 || y< 0 || x>=m || y>=n){
                            total = (total+board[p][q])%mod;
                        }else{
                            temp[x][y]=(temp[x][y]+board[p][q]) %mod;
                        } 
                    }
                }
            }
            board = temp;
        }

        return total;
    }
}

/*剪枝法, 3ms, beats 99
这个解法有一个重点，一开始我是把所有位置的值都默认为1。但是这样的话会TLE。
只有当都init成 -1的时候才剪枝有效。因为如果最后N =0的时候，那么这个坐标落在的位置
是在table里面(因为我们先过滤了不在table中的值)。所以当N=0时，这个不算。就返回0。
其中memo[i][j][k]表示总共走k步，从(i,j)位置走出边界的总路径数。那么我们来找递推式，
对于dp[k][i][j]，走k步出边界的总路径数等于其周围四个位置的走k-1步出边界的总路径数之和，
如果周围某个位置已经出边界了，那么就直接加上1，否则就在dp数组中找出该值，这样整个更新下来，
我们就能得出每一个位置走任意步数的出界路径数了，最后相当于返回dp[i][j][N]：
*/
class Solution {
    int M=1000000007;
    public int findPaths(int m, int n, int N, int i, int j) {
        double[][][] memo=new double[m][n][N+1];
        for(double[][] l:memo)
            for(double[] sl:l)
                Arrays.fill(sl,-1);
        return (int)findPaths(m,n,N,i,j,memo);
    }
    
    public double findPaths(int m,int n,int N,int i,int j,double[][][] memo){
        if(i < 0 || j < 0 || i == m || j == n)  return 1;
        if(N == 0)  return 0;
        if(memo[i][j][N] >= 0)   return memo[i][j][N];
        memo[i][j][N] = (findPaths(m,n,N-1,i+1,j,memo) + 
        				 findPaths(m,n,N-1,i-1,j,memo) + 
        				 findPaths(m,n,N-1,i,j+1,memo) + 
        				 findPaths(m,n,N-1,i,j-1,memo))%M;
        return memo[i][j][N];
    }
}


/*
传统的dp方法，对于每一个点来说，总共有上下左右四个不同的位置可以到达这个点。
每一次计算上下左右可以到达(x, y)的位置。如果是从边界过来的值，那么当前位
加一，否则就是可以到达上一位的步数总和的累加。
*/
class Solution {
    int M=1000000007;
    public int findPaths(int m, int n, int N, int i, int j) {
        double[][][] dp=new double[N+1][m][n];
        for (int k = 1; k <= N; k++) {
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    double v1 = (x == 0) ?1 : dp[k-1][x-1][y];
                    double v2 = (x == m -1) ? 1: dp[k-1][x+1][y];
                    double v3 = (y == 0) ? 1: dp[k-1][x][y-1];
                    double v4 = (y == n-1) ? 1: dp[k-1][x][y+1];
                    dp[k][x][y] = (v1+v2+v3+v4) % 1000000007;
                }
            }
        }
        return (int) dp[N][i][j];
    }
}

