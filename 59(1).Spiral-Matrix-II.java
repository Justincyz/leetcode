/*59. Spiral Matrix II
Medium: 
Given a positive integer n, generate a square matrix filled with 
elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/

/*解题思路
这道题目给定一个nxn的棋盘，让我们从左上角开始按照回形排列出 1到n^2个棋子。
这道题目的难点还是找准确下标的问题。首先推荐第一种，这种办法写起来很简单。
我们先按照右，下，左，上 四个方向给出前进的方向。每一次先计算下一步是否是越界，
(回形内部越界的条件是下一位不为0)。如果不会越界的话将下一位赋予当前位。如果会
越界的话，那么下一位的方向就要改变了。我们用pos来控制每一次前进的方向。

*/

class Solution {
        public int[][] generateMatrix(int n) {
        int[][] table = new int[n][n]; 
        int step = 1,pos = 0 ;
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};//右，下，左，上
        int x = 0, y = 0;
       
        for(int j=1; j<=n*n;j++){
            table[x][y] = step++;
            
            int nextX = x+ dirs[pos][0];
            int nextY = y+ dirs[pos][1];
            
            if(nextX >= n || nextY >= n || nextX< 0 || nextY<0 || table[nextX][nextY] !=0){
                pos = (pos+1)%4;
                nextX = x+ dirs[pos][0];
                nextY = y+ dirs[pos][1];
            }
            
            x = nextX;
            y = nextY;
        }   
        
        return table;
    }
}

/*
这种办法也还可以，但是更加需要注意下标、每一次上下左右都遍历一次。
*/

class Solution {
        public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n]; 
            
        int up = 0, down = n - 1, left = 0, right = n - 1, val = 1;
        while (true) {
            for (int j = left; j <= right; ++j) res[up][j] = val++;
            if (++up > down) break;
            for (int i = up; i <= down; ++i) res[i][right] = val++;
            if (--right < left) break;
            for (int j = right; j >= left; --j) res[down][j] = val++;
            if (--down < up) break;
            for (int i = down; i >= up; --i) res[i][left] = val++;
            if (++left > right) break;
        }
        return res;
    }
}

