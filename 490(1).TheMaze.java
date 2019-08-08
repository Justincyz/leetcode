/*490. The Maze
Medium: 
There is a ball in a maze with empty spaces and walls. The ball can 
go through empty spaces by rolling up, down, left or right, but it 
won't stop rolling until hitting a wall. When the ball stops, it could 
choose the next direction.

Given the ball's start position, the destination and the maze, determine 
whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0
 means the empty space. You may assume that the borders of the maze 
 are all walls. The start and destination coordinates are represented 
 by row and column indexes.

 
起点标注成S，终点标注成E
Example 1:
Input 1: a maze represented by a 2D array

0 0 1 0 S
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 E

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)
Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> 
down -> right.


Example 2:
Input 1: a maze represented by a 2D array

0 0 1 0 S
0 0 0 0 0
0 0 0 1 0
1 1 E 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false
Explanation: There is no way for the ball to stop at the destination.
*/

/*解题思路
这道题目告诉我们，在一个矩阵中，墙壁是1，其余位置是0。有一个弹球，在到达墙壁前不会停下。
给定一个起始点和终点，问弹球能不能到达终点。这里说明一下，弹球不能到达终点的原因例如例子2中
那样，弹球无法在撞到墙壁前停止。这道题目的一个小难点是如何让弹球往一个方向移动。在这里
我们还是用了四个向量代表四个不同的方向。在没有到达墙壁前不停地累加当前的方向。在嵌入墙壁
后(maze[x][y] ==1)记得要将当前位置往回走一位，相当于从墙里拔出来。假设停止的位置是
终点，那么就返回true。否则继续递归。那么问题是，这里我们如何判断是否可以到达终点呢？因为
我们的球停止的话一定是被1挡住了，所以visit[][]里面有值的地方只会存在在1的旁边。每一次撞墙
的时候我们都标注这个地方visit过了，那么下一次再visit的话直接返回false(注意是直接返回false)。
说明当前位不是终点。假如贴着1的这一圈我们全都visit过了且终点不在这一圈上面，说明我们没有办法
抵达终点。

*/

class Solution {
    int[][] dirs = {{-1,0}, {1,0}, {0,1}, {0,-1}};
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visit = new boolean[m][n];
        return dfs( maze,start[0], start[1],destination[0],destination[1], visit);
    }
    
    public boolean dfs(int[][] maze, int s1, int s2, int d1, int d2, boolean[][] visit){
        if(s1 == d1 && s2==d2) return true;
        
        if(visit[s1][s2] == true) return false;
        visit[s1][s2] = true;
        for(int[] dir: dirs){
            int x = s1;
            int y = s2;
            
            while(x>=0 && x<maze.length && y>=0 && y< maze[0].length && maze[x][y]!=1){
                x+=dir[0]; y+=dir[1];
                
            }
            x-=dir[0]; y-=dir[1];
            
            if(x == d1 && y == d2) return true;
            if(dfs(maze, x, y, d1, d2, visit)) return true;
        }
        
        return false;
    }
    
}