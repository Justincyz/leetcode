/*348. Design Tic-Tac-Toe
Medium: 

Design a Tic-tac-toe game that is played between two players on a n x n grid.

You may assume the following rules:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves is allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, 
or diagonal row wins the game.

Example:
Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
TicTacToe toe = new TicTacToe(3);

toe.move(0, 0, 1); -> Returns 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

toe.move(0, 2, 2); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

toe.move(2, 2, 1); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

toe.move(1, 1, 2); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

toe.move(2, 0, 1); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

toe.move(1, 0, 2); -> Returns 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
*/

/*解题思路
这道题目要求找的是在连连看的游戏里，哪一个玩家先赢。Initialize输入是棋盘的大小n*n。
然后每一次move给定的是玩家在哪一个位置落子。一开始做题的时候没注意到。每一步落子都假设是
valid的。不会一个位置被下两次。当然，即使没加这个条件的话加一个hashSet储存每一次落棋点就
可以了。题目要求的是在Move的时候在O(1)的时间内找到是否有胜利者。那么肯定不能够去遍历当前
的row 和 col了。首先想到的是建立一个新的Node class, 里面存放当前行和列还有斜线每个棋子的
个数。但是这样每次还是需要对这行和这列的所有位置进行更新。所以这个方法不行。
题目的hint给出了一个很重要的线索。其实我们不需要一整个table记录每个位置是什么。只需要对
所有的行，列，和两个对角线追踪就可以了。那么我们可以设置player 1落子的值是1， player 2落子
的值是-1。那么每一次落子之后，我们看所在的行和列(特殊位置要检查斜线)的值有没有累加到n或者-n。
如果有的话，那么就知道当前落子的player胜利了。否则就继续。

*/

/* Player {player} makes a move at ({row}, {col}).
    @param row The row of the board.
    @param col The column of the board.
    @param player The player, can be either 1 or 2.
    @return The current winning condition, can be either:
            0: No one wins.
            1: Player 1 wins.
            2: Player 2 wins. */
    

class TicTacToe {
    int[] r, c;
    int diag, antidiag;
    //Set<int[]> set = new HashSet<>();
    int size;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        r = new int[n];
        c = new int[n];
        diag = 0;
        antidiag = 0;
        size = n;
    }

    public int move(int row, int col, int player) {
        int p = player == 1? 1: -1;
        r[row] +=p;
        c[col] +=p;
        if(row == col) diag +=p;
        if((size-col-1) == row) antidiag+=p;
        if(r[row] == size || c[col] == size || diag == size || antidiag == size){
            return player;
        }
        if(r[row] == -size || c[col] ==-size || diag ==-size|| antidiag == -size){
            return player;
        }
        return 0;
    }
}

