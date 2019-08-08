/*741. Cherry Pickup
Hard: 

In a N x N grid representing a field of cherries, each cell is one of three 
possible integers.


0 means the cell is empty, so you can pass through;
1 means the cell contains a cherry, that you can pick up and pass through;
-1 means the cell contains a thorn that blocks your way.
 

Your task is to collect maximum number of cherries possible by following 
the rules below:

1.Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or 
down through valid path cells (cells with value 0 or 1);
2.After reaching (N-1, N-1), returning to (0, 0) by moving left or up through 
valid path cells;
3.When passing through a path cell containing a cherry, you pick it up and the 
cell becomes an empty cell (0);
If there is no valid path between (0, 0) and (N-1, N-1), then no cherries 
can be collected.
 
Example 1:
Input: grid =
[[0, 1, -1],
 [1, 0, -1],
 [1, 1,  1]]
Output: 5
Explanation: 
The player started at (0, 0) and went down, down, right right to reach (2, 2).
4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
Then, the player went left, up, up, left to return home, picking up one more cherry.
The total number of cherries picked up is 5, and this is the maximum possible.
*/

/*解题思路:
https://www.youtube.com/watch?v=vvPSWORCKow
这道题目思维很巧妙。具体的解法有好几种，可以参考链接。
这道题目首先看起来和在矩阵中找最大path sum差不多。只不过现在是走两次而已。那我们可以先
计算一次从(0,0) 到(n-1, n-1)的最大path sum, 然后再计算一次 (n-1, n-1)到(0,0)
的最大path sum。但是这样会掉到题目设计的一个陷阱里面。来看下面这个例子。
1 1 1 0 0
0 0 1 0 1
1 0 1 0 0
0 0 1 0 0
0 0 1 1 1

因为题目限制了向下和向上走时不能走回头路，所以我们会首先选择最大path sum, 也就是
(0,0) -> (0,2) -> (4,2) -> (4,4) 这一条路径。回去的时候只能选择获取(0,2)或者
(1,4)其中的一个节点走。但是事实上我们可以将所有的1都遍历完。为了避免这个陷阱。我们需要
重新考虑这个问题怎么变成我们熟悉的dp问题。
我们可以将以上问题转变成下面这个问题，那就是从(n-1, n-1)这个节点往(0,0)节点走两次。
每一次如果有遇到1的话，只有一条路径可以“吃掉”这个值，来避免重复计算。对于每个点(i,j)
来说，我们如何利用dp来计算路径呢？首先我们知道，每个从右下角到左上角的节点只能从上面或者左边来。
而这两条进入当前节点的路径我们设为(m, n)和(x,y)， 我们知道 m+n=x+y，因为每一次只能往一个
方向行走一步。所以我们可以通过计算求出第二个点的横坐标n = x+y-m。因为两个节点总共有两条
路径可以选择，所以相当于总共有四条路径的选择。对于dp[x][y][m]点我们求一个最大的值。dp[x][y][m]
代表的意思是对于当前位置来说，在四个不同组合的进入方式中选择一个最大值。同时加上grid[x][y]的值。
如果grid[m][n] != grid[i][j], 那么说明这两条路径并没有选择同一位置，那么可以累加。
最后得出右下角的总和。

*/

class Solution {
    int[][][] dp;
    int[][] grid ;
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        this.grid = grid;
        dp = new int[n][n][n];
        
        for(int[][] i: dp){
            for(int[] j: i){
                Arrays.fill(j, Integer.MIN_VALUE);
            }
        }
        return Math.max(0, helper( n-1, n-1, n-1));
    }
    
    public int helper( int x, int y, int m){
        int n = x+y-m;
        if(x < 0 || y<0 || m<0 || n<0) return -1;
        if(grid[x][y] <0 || grid[m][n] <0) return -1;
        if(x==0 && y == 0) return grid[x][y];
        if(dp[x][y][m] != Integer.MIN_VALUE) return dp[x][y][m];
        dp[x][y][m] = Math.max(helper(x-1,y,m-1), Math.max(helper(x-1,y,m), 
        	           Math.max(helper(x,y-1,m-1), helper(x,y-1,m))));
       
        if( dp[x][y][m] >= 0){
        	dp[x][y][m]+= grid[x][y];
        	 if(x!=m ) dp[x][y][m]+=grid[m][n];
        }

        return dp[x][y][m];
    }

    //参考链接 https://www.cnblogs.com/grandyang/p/8215787.html
}