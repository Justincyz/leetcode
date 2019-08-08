/*935. Knight Dialer
Medium: 
A chess knight can move as indicated in the chess diagram below:

1 2 3
4 5 6
7 8 9
  0

This time, we place our chess knight on any numbered key of a phone 
pad (indicated above), and the knight makes N-1 hops.  Each hop must 
be from one key to another numbered key.

Each time it lands on a key (including the initial placement of the 
knight), it presses the number of that key, pressing N digits total.

How many distinct numbers can you dial in this manner?

Since the answer may be large, output the answer modulo 10^9 + 7.


Example 1:
Input: 1
Output: 10

Example 2:
Input: 2
Output: 20

Example 3:
Input: 3
Output: 46

*/

/*解题思路
这道题目要找的是在手机拨号键盘上，以马字走过N次键盘，总共有多少种可能得到的数字组合。
首先这道题还是一道经典的dp题目。最后的值可能有很多，所以要mod 10^9+7次。
有两种解法，首先来说第一种。
第一种办法就是假设我们建立了一个4x3的棋盘，让每个位置的值都为1，同时注意。这个棋盘里
左下角和右下角([3,0],[3,2])是空白，不能走，所以设置为0。我们总共有N步，第一步的走法就是
把所有键盘位都变为1。然后我们从第二步开始，遍历整个棋盘。对于每个位置我们调用预设好的
jumps[][] 数组。因为这个数组都是以(i,j)为原点，所有走日字型的方向。所以只要不越界，那么
说明当前走法是valid的。现在重点来了，对于棋盘当中每一个有效的位置(m,n)，我们要找到的是从
棋盘别的有效位置可以到达(m,n)的所有走法的总和，加在第(m,n)位，而不是找从(m,n)可以到达的
所有位置。(其实也可以那样，只是不方便计算而已)。每一次记得用一个新的2-D array记录新的，因为
直接更改原来的array会导致值得变化。每一次遍历后再赋值给原array.最后计算这个棋盘的总和。

第二种办法也是一开始所想的。jumps[i]记录的是可以到达jumps[i]的其他位置坐标。其余的思路
基本和上面一致。注意，第五位只有N = 1的时候记录一次。其余时候别的坐标根本没办法reach 5。

*/
class Solution {
	int[][] jump = {{-1,-2},{1,-2},{-1,2},{1,2},{-2,-1},{-2,1},{2,1},{2,-1}};
    public int knightDialer(int N) {
        if(N == 0) return 0;
        int total = 0, kMod = 1000000007;
        int[][] board = new int[4][3];
        for(int[] b: board) Arrays.fill(b, 1);
       
        board[3][0] = 0;
        board[3][2] = 0;

        for(int i=1; i<N; i++){
        	int[][] temp = new int[4][3];
        	for(int m = 0; m < 4; m++){
        		for(int n = 0; n< 3; n++){
        			if (m == 3 && n != 1) continue;
        			for(int[] dir: jump){
        				int x = dir[0] + m;
        				int y = dir[1] + n;
        				if(x<0 || y<0||x>=4 || y>=3) continue;
        				temp[m][n] = (temp[m][n]+board[x][y])%kMod;
        			}
        		}
        	}
            
        	board = temp;
        }

        for(int m=0; m<4; m++){
        	for(int n = 0; n < 3; n++){
        		total+=(board[m][n]);
                total%=kMod;
        	}
        	
        }
        return total;
    }
}


//更加简单的办法
class Solution {
	int[][] jumps = {{4,6},{6,8},{7,9},{4,8},{0,3,9},{},{0,1,7},{2,6},{1,3},{2,4}};
    public int knightDialer(int N) {
        if(N == 0) return 0;
        int total = 0, kMod = 1000000007;
        int[] board = new int[10];
        Arrays.fill(board, 1);

        for(int i=1; i<N; i++){
        	int[] temp = new int[10];            
            for(int j=0; j<10; j++){
                for(int jump : jumps[j]){
                    temp[j] = (temp[j]+board[jump])%kMod;
                }
            }
            board = temp;
        }
        
        for(int i=0; i<10;i++){
            total+=board[i];
            total%=kMod;
        }
        return total;
    }
}