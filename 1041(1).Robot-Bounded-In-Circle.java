/*1041. Robot Bounded In Circle
链接：https://leetcode.com/problems/robot-bounded-in-circle/
Medium: 

On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:

"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degress to the right.
The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.

 

Example 1:

Input: "GGLLGG"
Output: true
Explanation: 
The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
Example 2:

Input: "GG"
Output: false
Explanation: 
The robot moves north indefinitely.
Example 3:

Input: "GL"
Output: true
Explanation: 
The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 

Note:

1 <= instructions.length <= 100
instructions[i] is in {'G', 'L', 'R'}
*/

/*解题思路
题目让我们判断按照给定的路线如果无线重复这个路线走的话，是否会形成一个圈且
这个人永远走不出这个圈里。
其实判断的条件出乎意料的简单，但是需要仔细的想。
1. 如果走完一遍路径可以回到原点的话，那么一定是可以回到原点的，无论方向朝哪边。
2. 如果走完一遍instruction的路径的时候，结尾时面对的方向和出发点不一样，那么
一定也可以通过重复几次instructions回到原点。

主要是如何去思考第二个方法为什么是正确的。
首先我们不要受中间路径的影响。假设起点是(0,0)此时走完一遍instruction之后，
到达的位置是(x, y). 我们只考虑起点和终点，那么我们以 (0,0)和(x, y)为斜边
可以构建出来一个虚拟的直角三角形。而走完一个instruction之后此时我们站在直角
三角形另一个非直角边上。因为其实中间的路径并不重要，所以我们假设路径就是两条
直角边。我们相当于此时已经构建出一个正方形的一个直角和一条完整的直角边了。所
以我们此时再出发走一遍instruction的话，相当于又构建了一个直角。从起始点开始，我们最多重复四次上述路径就可以回到原点，相当于构建了一个正方形。

这是网上有人尝试证明的，我写的其实已经很清楚了
Let's say the robot starts with facing up. It moves [dx, dy] by executing the instructions once.
If the robot starts with facing
right, it moves [dy, -dx];
left, it moves [-dy, dx];
down, it moves [-dx, -dy]

If the robot faces right (clockwise 90 degree) after executing the instructions once,
the direction sequence of executing the instructions repeatedly will be up -> right -> down -> left -> up
The resulting move is [dx, dy] + [dy, -dx] + [-dx, -dy] + [-dy, dx] = [0, 0] (back to the original starting point)

All other possible direction sequences:
up -> left -> down -> right -> up. The resulting move is [dx, dy] + [-dy, dx] + [-dx, -dy] + [dy, -dx] = [0, 0]
up -> down -> up. The resulting move is [dx, dy] + [-dx, -dy] = [0, 0]
up -> up. The resulting move is [dx, dy]


*/

class Solution {
    int[][] dirs = {{-1,0},{0,-1},{1,0},{0,1}};
    public boolean isRobotBounded(String instructions) {
        char[] list = instructions.toCharArray();
       
        int pos =0, x =0, y=0, dir = 0, count=1;

        while(pos < list.length){
            if(list[pos] == 'G'){
                x+=dirs[dir][0];
                y+=dirs[dir][1];
            }else if(list[pos] == 'L'){
                dir+=1;
                dir%=4;
            }else{
                dir-=1;
                if(dir == -1) dir = 3;
            }
            
            pos++;
        }
        
        if(dir!=0 || (x==0 && y==0)) return true;
        
        return false;
    }
}