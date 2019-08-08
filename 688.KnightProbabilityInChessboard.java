/*688. Knight Probability in Chessboard
Medium: DP, Recursive

On an NxN chessboard, a knight starts at the r-th row and c-th column
 and attempts to make exactly K moves. The rows and columns are 0 
 indexed, so the top-left square is (0, 0), and the bottom-right square 
 is (N-1, N-1).

A chess knight has 8 possible moves it can make, as illustrated below. 
Each move is two squares in a cardinal direction, then one square in
 an orthogonal direction.

Each time the knight is to move, it chooses one of eight possible 
moves uniformly at random (even if the piece would go off the chessboard) 
and moves there.

The knight continues moving until it has made exactly K moves or has moved 
off the chessboard. Return the probability that the knight remains on the 
board after it has stopped moving.

Example:

Input: 3, 2, 0, 0
Output: 0.0625
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.
*/

/*解题思路 
这道题的类似题目是, 935: Knight Dialer , 576: Out of Boundary Paths
这道题给了我们一个大小为NxN国际象棋棋盘，上面有个骑士，相当于我们中国象棋中的马，能走‘日’字，
给了我们一个起始位置，然后说允许我们走K步，问走完K步之后还能留在棋盘上的概率是多少。那么要求概率，
我们必须要先分别求出分子和分母，其中分子是走完K步还在棋盘上的走法，分母是没有限制条件的总共的走法。
那么分母最好算，每步走有8种跳法，那么K步就是8的K次方了。一开始向的方法是以给
定位置为起始点，然后进行BFS，每步遍历8种情况，当K==0时就把概率加在total里，结果TLE了。看大家的
解法，结果发现都是换了一个角度来解决问题的，并不很关心骑士的起始位置，而是把棋盘上所有位置上经过K步
还留在棋盘上的走法总和都算出来，那么最后直接返回输入位置的值即可。还是要用DP来做，其中dp[i][j]表示
在棋盘(i, j)位置上走完当前步骤还留在棋盘上的走法总和，初始化为1，我们其实将步骤这个维度当成了时间维度
在不停更新。好，下面我们先写出8种
‘日’字走法的位置的坐标，就像之前遍历迷宫上下左右四个方向坐标一样，这不过这次位置变了而已。然后我们一
步一步来遍历，每一步都需要完整遍历一遍棋盘的每个位置，新建一个临时数组temp，大小和dp数组相同，但是初始化
为0，然后对于遍历到的棋盘上的每一个格子，我们都遍历8中解法，如果新的位置不在棋盘上了，直接跳过，否则
就加上上一步中的dp数组中对应的值，遍历完棋盘后，将dp数组更新为这个临时数组temp.
!!注意，对于board上每个点(i, j)，我们是计算所有有效的(x, y)坐标可以到达(i, j坐标的方法和。

*/

//7ms, 45%
class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        if(K == 0) return 1;
        int[][] moves = {{-1,2},{1,-2},{-1,-2},{1,2},{-2,1},{2,-1},{-2,-1},{2,1}};
        double[][] board = new double[N][N];
        for(double[] b: board)
            Arrays.fill(b, 1);
        
        for(int s = 0; s <K; s++){
            double[][] temp = new double[N][N];
            for(int i=0; i<N; i++){
                for(int j=0; j< N; j++){
                    for(int[] move: moves){
                        int x = move[0]+i;
                        int y = move[1]+j;
                        if(x<0|| y<0 || x>=N || y>=N) continue;
                        temp[i][j] += board[x][y]; //从(x,y)可以跳到(i, j)
                    }
                }
            }
            board = temp;
        }
        return board[r][c] /Math.pow(8, K);
    }
}

/*
这是第一次尝试的递归的方法。用了一个三维的array做剪枝之后速度一下快很多。
递归下的memo数组其实就是迭代下的dp数组。在递归函数中，如果k为0了，说明已经走了k步，将到达这一步的
概率加到total中。如果memo[k][r][c]不为0，说明这种情况之前已经计算过，直接返回。然后遍历8种走法，
计算新的位置，如果不在棋盘上就跳过；然后更新memo[k][r][c]，使其加上对新位置调用递归的返回值，
注意此时带入k-1和新的位置，退出循环后返回memo[k][r][c]即可：
*/
//4ms, 98%
class Solution {
    int[][] moves = {{-1,2},{1,-2},{-1,-2},{1,2},{-2,1},{2,-1},{-2,-1},{2,1}};
    double total = 0;
    double[][][] memo;
    public double knightProbability(int N, int K, int r, int c) {
        int[][] board = new int[N][N];
        memo = new double[K+1][N][N];
        helper(r, c, K, board, 1);
        return total;
    }
    
    public double helper(int r, int c, int K, int[][] board, double prob){
        if(K == 0) {
            total+=prob;
            return prob;
        } 
        if(memo[K][r][c] != 0){
            total+=memo[K][r][c];
            return memo[K][r][c];
        }
        
        int invalid = 0;
        for(int[] move: moves){
            int x = r+move[0];
            int y = c+move[1];
            if(x<0 || y<0 || x>=board.length || y>=board.length){
                continue;
            }
            //累加，每一次递归的概率都是当前点的概率再除以8
            memo[K][r][c] += helper(x, y, K-1, board, prob/8);
        }
        return memo[K][r][c];
    }
}

