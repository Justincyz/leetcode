/*174. Dungeon Game
链接：https://leetcode.com/problems/dungeon-game/
Hard: 

The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

 

Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

_-2__|_-3__|_3__
_-5__|_-10_|_1__
 10  |  30 |-5

*/

/*解题思路
视频参考 https://www.youtube.com/watch?v=pt-xIS6huIg&t=623s
题目告诉我们，骑士从左上角出发，要到达右下角救公主。每一个房间可能会加血也可能掉血，
问骑士最少需要多少的初始值血量。只要在某一个房间血量小于1了，骑士马上就死了。
先考虑每个位置的血量是由什么决定的，骑士会挂主要是因为去了下一个房间时，掉血量大于本身的血值，
而能去的房间只有右边和下边，所以当前位置的血量是由右边和下边房间的最少可生存血量决定的，
进一步来说，应该是由较小的可生存血量决定的，因为这样进去之前我们需要起始血量可以更少，因为我们是逆着往回推，骑士逆向进入房间后 PK 后所剩的血量就是骑士正向进入房间时 pk 前的起始血量。
所以用当前房间的右边和下边房间中骑士的较小血量减去当前房间的数字，如果是负数或着0，说明当前房间是正数，这样骑士进入当前房间后的生命值是1就行了，因为不会减血。而如果差是正数的话，当前房间的血量可能是正数也可能是负数，但是骑士进入当前房间后的生命值就一定要是这个差值。所以我们的状态转移方程是 dp[i][j] = max(1, min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j])。为了更好的处理边界情况，我们的二维 dp 数组比原数组的行数列数均多1个，先都初始化为整型数最大值 INT_MAX，由于我们知道到达公主房间后，骑士火拼完的血量至少为1，那么此时公主房间的右边和下边房间里的数字我们就都设置为1，这样到达公主房间的生存血量就是1减去公主房间的数字和1相比较，取较大值，就没有问题了，


*/
//res 储存的是在这一步需要的hp，从右下角到左上角
//dp每一个位置代表代表从这个位置到达右下角所需要的最少血量
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m+1][n+1];
        Arrays.fill(dp[m], Integer.MAX_VALUE);
        for(int i=0; i< m; i++) dp[i][n] = Integer.MAX_VALUE;
        
        dp[m-1][n] = 1;
        dp[m][n-1] = 1;
        
        for(int i=m-1; i>=0; i--){
            for(int j=n-1; j>=0; j--){
            	//dungeon[i][j]为负说明会掉血，当前位置减掉负数代表所需要的血量
                dp[i][j] = Math.max(1, Math.min(dp[i+1][j], dp[i][j+1])-dungeon[i][j]);
            }
        }
        
        return dp[0][0];
    }
}