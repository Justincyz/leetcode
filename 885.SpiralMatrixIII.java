/*885. Spiral Matrix III
Medium:
https://leetcode.com/problems/spiral-matrix-iii/

On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) 
facing east.

Here, the north-west corner of the grid is at the first row and column, 
and the south-east corner of the grid is at the last row and column.

Now, we walk in a clockwise spiral shape to visit every position in this grid. 

Whenever we would move outside the boundary of the grid, we continue our 
walk outside the grid (but may return to the grid boundary later.) 

Eventually, we reach all R * C spaces of the grid.

Return a list of coordinates representing the positions of the grid in 
the order they were visited.

Example 1:
Input: R = 1, C = 4, r0 = 0, c0 = 0
Output: [[0,0],[0,1],[0,2],[0,3]]
(没办法插入图片， 意思就是对于一个matrix, 逆时针情况下不停地遍历这个matrix，把
路径保存下来)

Example 2:
Input: R = 5, C = 6, r0 = 1, c0 = 4
Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],
[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],
[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],
[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
*/

/*解题思路
这道题目要我们以回形的方式绕着给定的点旋转。遍历整个matrix。如果
这个点在matrix的外部则不用管它。
首先我们还是定义好四个点的方向。但是一定要按照顺序来。题目中给的是东南西北四个方向。
事实上就是对应着上下左右四个方向。还有两个重点，一个是步长。对应的是我们在相应
的方向走几步。还有一个d，对应的是我们应该往哪个方向走了。我们定义一个二维的数组，
长度为R*C。循环的结束条件就是当我们遍历的长度已经和matrix中所有cell的个数一样了。

*/

class Solution {
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int[][] res = new int[R*C][2];
        int[][] dirs = {{0,1}, {1,0},{0,-1},{-1,0}};//右，下，左，上。按顺序来

        int stride = 0, pos =0, d = 0;
        res[pos][0] = r0;
        res[pos++][1] = c0;
    
        while(pos <R*C){
            if(d == 0 || d==2) stride++;//步长只会在向右和向左时增加1
            for(int i= 0; i< stride; i++){
                r0 += dirs[d][0];
                c0 += dirs[d][1];
                if(r0<0 || c0<0 || r0>= R || c0 >= C) continue;
                
                res[pos][0] = r0;
                res[pos++][1] = c0;
            }
            d = (d+1)%4;
        } 
        return res;
    }
}