/*1030. Matrix Cells in Distance Order

链接：https://leetcode.com/problems/matrix-cells-in-distance-order/
Easy: 
We are given a matrix with R rows and C columns has cells with integer coordinates (r, c), where 0 <= r < R and 0 <= c < C.

Additionally, we are given a cell in that matrix with coordinates (r0, c0).

Return the coordinates of all cells in the matrix, sorted by their distance from (r0, c0) from smallest distance to largest distance.  Here, the distance between two cells (r1, c1) and (r2, c2) is the Manhattan distance, |r1 - r2| + |c1 - c2|.  (You may return the answer in any order that satisfies this condition.)

 

Example 1:

Input: R = 1, C = 2, r0 = 0, c0 = 0
Output: [[0,0],[0,1]]
Explanation: The distances from (r0, c0) to other cells are: [0,1]
Example 2:

Input: R = 2, C = 2, r0 = 0, c0 = 1
Output: [[0,1],[0,0],[1,1],[1,0]]
Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2]
The answer [[0,1],[1,1],[0,0],[1,0]] would also be accepted as correct.
Example 3:

Input: R = 2, C = 3, r0 = 1, c0 = 2
Output: [[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2,2,3]
There are other answers that would also be accepted as correct, such as [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]].
*/

/*解题思路
这是一道简单题，给出一个矩阵的长宽，然后告诉我们一个起始点。让我们按照曼哈顿距离输出
距离起始点从小到大的所有点的坐标。我们用一个BFS来做就好。
注意要用一个boolean[][] table 表示我们已经遍历过的位置。

*/


class Solution {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r0, c0});
        int[][] res = new int[R*C][2];
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}}; 
        boolean[][] table = new boolean[R][C];
        table[r0][c0] = true;
        int i = 0;
        while(!q.isEmpty()){
            int[] pos = q.remove();
            res[i++] = pos;
            for(int[] dir: dirs){
                int x = pos[0]+dir[0];
                int y = pos[1]+dir[1];
                if(x <0 || x>=R || y<0 || y>=C|| table[x][y]) continue;            
                q.add(new int[]{x, y});
                table[x][y] = true;
            }
        }
        
        return res;
    }
}