/*562. Longest Line of Consecutive One in Matrix
Medium: 
Given a 01 matrix M, find the longest line of consecutive one 
in the matrix. The line could be horizontal, vertical, diagonal 
or anti-diagonal.

Example:
Input:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3
*/

/*解题思路
这道题目让我们找到二维数组中最长的连续1，这个连续的1可以是竖着，横着，或者左上往右下的斜线，
也可以是右上到左下的斜线。最简单的办法就是用dp来做。我们首先定义一个Node 节点，储存了当前
节点从四个方向，左上，右上，左边，上边到达当前节点的最大连续1的长度。最初都赋值为0个。我们
首先来看一个用二维数组记录状态的做法(之后可以用滚动数组压缩到一维数组)。首先对于第一列和最
后一列来说，为了防止越界，我们相当于每一行多加上两个用以给第一列和最后一列数来取左上角和右
上角的值。对于M每一位来说，如果是0的话，那么到达当前位肯定就无法构成连续序列了，直接用原始值。
否则如果当前位是1的话，那么左上，右上，从左往右和从上往下所构成的连续序列长度都加一，然后将这些值都
赋给当前Node。同时比较一下最大值是否出现在当前节点，同时更新最大值。最后返回最大值就可以了

*/

class Solution {
    public int longestLine(int[][] M) {
        if(M.length ==0 || M[0].length ==0) return 0;
        int m = M.length, n = M[0].length;
        Node[][] dp = new Node[m+2][n+2];

        Arrays.fill(dp[0], new Node());
        int max = 0;
        for(int i=1; i<=m; i++){
            dp[i][0] = new Node();
            dp[i][n+1] = new Node();
            for(int j =1; j<=n; j++){
                Node node = new Node();
                if(M[i-1][j-1] != 0){
                    node.row = dp[i][j-1].row +1;
                    node.col = dp[i-1][j].col+1;
                    node.diag1 = dp[i-1][j-1].diag1+1;
                    node.diag2 = dp[i-1][j+1].diag2+1;
                    max = Math.max(max, Math.max(node.row, Math.max(node.col,Math.max(node.diag1, node.diag2))));
                }
                dp[i][j] = node;
            }
        }
        return max;
    }
}

class Node{
    int col = 0;
    int row = 0;
    int diag1 = 0;
    int diag2 = 0;
    public Node(){};
}

/*
用滚动数组做了状态压缩，beats 93%
注意每一次prev是记录当前节点的diag1(左上对角线)的数值，否则的话当前节点更新后会覆盖掉
左上角的值。注意dp[j]实际上是 M[i][j-1]的值。每一次都要在最后更新prev

*/

class Solution {
    public int longestLine(int[][] M) {
        if(M.length ==0 || M[0].length ==0) return 0;
        int m = M.length, n = M[0].length;
        Node[] dp = new Node[n+2];
        
        Arrays.fill(dp, new Node());
        int max = 0;
        for(int i=1; i<=m; i++){
            int prev = dp[0].diag1;
            
            for(int j =1; j<=n; j++){
                Node node = new Node();
                
                if(M[i-1][j-1] != 0){
                    node.row = dp[j-1].row +1;//左边累加
                    node.col = dp[j].col+1;//上面累加
                    node.diag1 = prev+1;//左上累加
                    node.diag2 = dp[j+1].diag2+1;//右上累加
                    max = Math.max(max, Math.max(node.row, Math.max(node.col,Math.max(node.diag1, node.diag2))));
                }
                prev = dp[j].diag1;
                dp[j] = node;
            }
        }
        return max;
    }
}

class Node{
    int col = 0;
    int row = 0;
    int diag1 = 0;
    int diag2 = 0;
    public Node(){};
}