/*489. Robot Room Cleaner
Hard: 
Given a robot cleaner in a room modeled as a grid.

Each cell in the grid can be empty or blocked.
The robot cleaner with 4 given APIs can move forward, turn left or turn right. 
Each turn it made is 90 degrees.

When it tries to move into a blocked cell, its bumper sensor detects the 
obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs 
shown below.

interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean move();

  // Robot will stay on the same cell after calling turnLeft/turnRight.
  // Each turn will be 90 degrees.
  void turnLeft();
  void turnRight();

  // Clean the current cell.
  void clean();
}
Example:

Input:
room = [
  [1,1,1,1,1,0,1,1],
  [1,1,1,1,1,0,1,1],
  [1,0,1,1,1,1,1,1],
  [0,0,0,1,0,0,0,0],
  [1,1,1,1,1,1,1,1]
],
row = 1,
col = 3

Explanation:
All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.
*/

/*解题思路
这道题目比较奇特，用一个二维的矩阵来模拟一个房间，房间中有一些位置是障碍，其余位置可以通过。
但是这个房间内部对于我们来说是不可见的，我们唯一能用的就是四个API, 左转，右转，
前进和清理。如果调用move()返回false的话就说明前方有障碍。让我们实现一个办法可以清理完
整个房间的垃圾。
这里用的方法是DFS，基本所有的答案都是DFS的做法。我们首先需要一个hashset来记录已经遍历过
的坐标，因为没有给出初始坐标，我们就假设一开始的位置标为(0,0),这是个相对坐标不是绝对坐标。
我们建立四个方向的位置，然后每一次都遍历四个方向。这道题目唯一比较难理解的就是在调用dfs之后
机器人操作的五个步骤是干嘛的。其实这个是为了回到最初的原点，因为在if中我们调用了robot.move()。
所以机器人会前进一步，而做dfs的时候我们需要在每一个位置都对四个方向做dfs，所以机器人前进之后还需要
回原点。需要先掉个头，再走一步，然后再掉个头。这样面对的位置还是在移动之前的位置。在这之后
再往左或者往右转一个方向(根据设定的dirs方向来)

*/

/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */
class Solution {
  //这个位置很重要，向右，向下，向左，向上,这个和每一次机器人转动的方向要一致，我们这里用的是robot.turnRight()
    int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
    Set<String> visited; 
    public void cleanRoom(Robot robot) {
        visited = new HashSet<>();
        helper(robot, 0,0,0);
    }
    
    public void helper(Robot robot, int x, int y, int dir){
        robot.clean();
        visited.add(String.valueOf(x)+"-"+String.valueOf(y));
        for(int i=0; i<4; i++){
            int cur = (i+dir)%4;
            int newX = x+dirs[cur][0], newY = y+dirs[cur][1];
            if(!visited.contains(String.valueOf(newX)+"-"+String.valueOf(newY)) && robot.move()){
                helper(robot, newX, newY, cur);
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnLeft();
                robot.turnLeft();
            }
            robot.turnRight();
        }
    }
}

