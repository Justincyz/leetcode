/* 529. Minesweeper
Medium: 
Let's play the minesweeper game (Wikipedia, online game)!

You are given a 2D char matrix representing the game board. 'M' 
represents an unrevealed mine, 'E' represents an unrevealed empty 
square, 'B' represents a revealed blank square that has no adjacent 
(above, below, left, right, and all 4 diagonals) mines, digit 
('1' to '8') represents how many mines are adjacent to this 
revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among 
all the unrevealed squares ('M' or 'E'), return the board after 
revealing this position according to the following rules:

If a mine ('M') is revealed, then the game is over - change it to 'X'.
If an empty square ('E') with no adjacent mines is revealed, then 
change it to revealed blank ('B') and all of its adjacent unrevealed 
squares should be revealed recursively.
If an empty square ('E') with at least one adjacent mine is revealed, 
then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.

Example 1:
Input: 
[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

Click : [3,0]
Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

continue from the above

Example 2:
Input: 
[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Click : [1,2]

Output: 
[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]
*/

/*解题思路
题目写的很长，其实就是让我们解决一个简单的扫雷问题。这个游戏中我们只有一个初始点击位置。然后
从这个位置出发判断总共可以获知多大范围的棋盘。注意迭代结束的条件。如果当前位是雷，那么
直接return 就可以了。如果当前位不是雷，但是周围有雷，那么对当前位的周围扫一遍，记录当前位
四周雷的个数。此种情况就不继续往下迭代了，也就是例子一中为什么雷的上面有一个没有被翻转的'E'.
如果当前位不是雷且四周没有一个是雷，这种情况下才要继续迭代。一开始被这个条件搞得有点迷。

*/
//0MS
class Solution {
    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,1},{1,-1},{-1,-1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        helper(board, click[0], click[1]);
        return board;
    }

    public void helper(char[][] board, int row, int col){
        if(board[row][col] == 'M'){
            board[row][col] = 'X';
            return;
        } 
        
        board[row][col] = 'B';
        int count =0;
        for(int[] dir : dirs){
            int x = row+dir[0];
            int y = col+dir[1];
            if(x <0 || y<0 || x>=board.length || y>= board[0].length) continue;
            if(board[x][y] == 'M'){
               count++;
            }
        }
        if(count>0){
            board[row][col] = (char)('0'+count);
            return;
        } 
        
        for(int[] dir : dirs){
            int x = row+dir[0];
            int y = col+dir[1];
            if(x <0 || y<0 || x>=board.length || y>= board[0].length) continue;
            if(board[x][y] == 'E')
                helper(board, x, y);
        }
    }
}
