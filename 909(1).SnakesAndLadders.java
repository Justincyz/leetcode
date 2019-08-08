/*909. Snakes and Ladders
Medium: 
On an N x N board, the numbers from 1 to N*N are written boustrophedonically 
starting from the bottom left of the board, and alternating direction each 
row.  For example, for a 6 x 6 board, the numbers are written as follows:
(没有图，要回leetcode看)
You start on square 1 of the board (which is always in the last row and first 
column).  Each move, starting from square x, consists of the following:

You choose a destination square S with number x+1, x+2, x+3, x+4, x+5, or x+6, 
ovided this number is <= N*N.
(This choice simulates the result of a standard 6-sided die roll: ie., there 
are always at most 6 destinations, regardless of the size of the board.)
If S has a snake or ladder, you move to the destination of that snake or 
ladder.  Otherwise, you move to S.
A board square on row r and column c has a "snake or ladder" if board[r][c] != -1.
  The destination of that snake or ladder is board[r][c].

Note that you only take a snake or ladder at most once per move: if the 
destination to a snake or ladder is the start of another snake or ladder,
 you do not continue moving.  (For example, if the board is `[[4,-1],[-1,3]]`,
  and on the first move your destination square is `2`, then you finish your 
  first move at `3`, because you do not continue moving to `4`.)

Return the least number of moves required to reach square N*N.  If it is not
 possible, return -1.

Example 1:

每一步的操作
Input: [
[-1,-1,-1,-1,-1,-1],
[-1,-1,-1,-1,-1,-1],
[-1,-1,-1,-1,-1,-1],
[-1,35,-1,-1,13,-1],
[-1,-1,-1,-1,-1,-1],
[-1,15,-1,-1,-1,-1]]

步数是蛇形的
[36,35,34,33,32,31],
[25,26,27,28,29,30],
[24,23,22,21,20,19],
[13,14,15,16,17,18],
[12,11,10,9, 8, 7],
[1, 2, 3, 4, 5, 6]]

Output: 4
Explanation: 
At the beginning, you start at square 1 [at row 5, column 0].
You decide to move to square 2, and must take the ladder to square 15.
You then decide to move to square 17 (row 3, column 5), and must take the snake to square 13.
You then decide to move to square 14, and must take the ladder to square 35.
You then decide to move to square 36, ending the game.
It can be shown that you need at least 4 moves to reach the N*N-th square, so the answer is 4.
*/

/*解题思路
这道题目非常的长，但是实际上要解决的问题比较简单。在一个有序的回形棋盘中(我画的第二个矩阵，
可以看出来步数是回形的，起始点在左下角。boustrophedonically的意思就是迂回曲折)
，每一个位置要不是-1，或者是一个正数。-1代表的意思就是普通的一个位置x，在普通的位置上可以到达
往后的六个位置，x+1,.., x+6。如果不是-1的话，意思是相当于一个桥，我们可以直接到达这个数字
代表的位置上(必须走)，注意，这个位置可以比当前位大也可以比当前位小。题目的问的是我们可以从起始点1到达
终点 m*n的最短步数。实际上这道题目是一个BFS的问题。既然是BFS那我们就要用到queue来辅助我们
做题，同时创建一个新的list记录我们已经遍历过的点避免重复。这个题目是蛇形的走位所以比较难以
定位下一位的位置，首先我做的就是把这个二维数组拉直，因为反正步数都是递增的。然后从第一位开始，
遍历每个点位后面的六个位置，如果任意一个点已经到达了终点，那么返回steps。否则的话检查新到达
的点有没有曾经被到达过，如果没有的话那么我们把这一位标记为visit, 并且加入到queue中。每一次BFS
都记得把step++。

*/

class Solution {
    public int snakesAndLadders(int[][] board) {
        int m = board.length, index = 0, dir =1, x = board.length-1, y = 0;
        int[] array = new int[m*m];
        while(index < m*m){
            array[index++] = board[x][y];
            y+=dir;
            if(y == board.length || y ==-1){
                dir  = -dir;
                x--;
                y+=dir;
            }
        }
        
        boolean[] visit = new boolean[m*m];
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        visit[0] = true;
        int step =0;
        while(!q.isEmpty()){
            int size = q.size();
            while(size -- >0){
                int pos = q.remove();
                if(pos == (m*m-1)) return step;
              
                for(int i=pos+1; i<=(pos+6) && i< m*m ; i++){
                    int dst = array[i] == -1? i : array[i]-1;
                    if(visit[dst]) continue;
                    q.add(dst);    
                    visit[dst] = true;
                }
            }
            step++;
        }
        
        return -1;    
    }
}


//没有拉直，每次直接计算下一个点位
class Solution {
    public int snakesAndLadders(int[][] board) {
   
        int n = board.length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        boolean[] visited = new boolean[n * n + 1];
        for (int move = 0; !queue.isEmpty(); move++) {
            for (int size = queue.size(); size > 0; size--) {
                int num = queue.poll();
                if (visited[num]) continue;
                visited[num] = true;
                if (num == n * n) return move;
                for (int i = 1; i <= 6 && num + i <= n * n; i++) {
                    int next = num + i;
                    int value = getBoardValue(board, next);
                    if (value > 0) next = value;
                    if (!visited[next]) queue.offer(next);
                }
            }
        }
        return -1;
    }

    private int getBoardValue(int[][] board, int num) {
        int n = board.length;
        int r = (num - 1) / n;
        int x = n - 1 - r;
        int y = r % 2 == 0 ? num - 1 - r * n : n + r * n - num;
        return board[x][y];
    
    }
}