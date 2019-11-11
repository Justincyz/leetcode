/*1091. Shortest Path in Binary Matrix
链接：https://leetcode.com/problems/shortest-path-in-binary-matrix/
Medium: 
n an N by N square grid, each cell is either empty (0) or blocked (1).

A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:

Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
C_1 is at location (0, 0) (ie. has value grid[0][0])
C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.

Example 1:
Input: [[0,1],
        [1,0]]
从左上角斜向下走一步，总共两步

Example 2:
Input: [[0,0,0],
        [1,1,0],
        [1,1,0]]
左上角开始，往右，斜右下角，再往下，总共四步
*/

/*解题思路
就是一个简单的bfs. 注意只能走位置为0 的点。这道题目可以有八个方向走，每一次经过一个0的时候变成1，也不用新
开一个数组来储存走过的节点了，反正只要是1就不能走，所以不会有回头路。
最后直接返回步数就可以了


*/
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0] == 1) return -1;
        int n = grid.length, m = grid[0].length;
        if(n == 1 && m==1) return 1;
      
        int[][] moves = {{-1,0},{1,0},{0,1},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});
        int step =0;
        
        while(!q.isEmpty()){
            int len = q.size();
            step++;
            while(len -- > 0){
                int[] p = q.poll();
                for(int[] move : moves){
                    int x = move[0]+p[0];
                    int y = move[1]+p[1];
                    if(x <0 || y <0 || x>=n || y>= m ||  grid[x][y] == 1) continue;
                  
                    if(x == n-1 &&  y == m-1) return step+1;
                    q.add(new int[]{x, y});
                    grid[x][y] =1;
                }
            }
        }
        return -1;
    }
}