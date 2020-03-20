/*1140. Stone Game II
链接：https://leetcode.com/problems/stone-game-ii/
Medium: 
Alex and Lee continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones. 

Alex and Lee take turns, with Alex starting first.  Initially, M = 1.

On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).

The game continues until all the stones have been taken.

Assuming Alex and Lee play optimally, return the maximum number of stones Alex can get.

 

Example 1:

Input: piles = [2,7,9,4,4]
Output: 10
Explanation:  If Alex takes one pile at the beginning, Lee takes two piles, then Alex takes 2 piles again. Alex can get 2 + 4 + 4 = 10 piles in total. If Alex takes two piles at the beginning, then Lee can take all three piles left. In this case, Alex get 2 + 7 = 9 piles in total. So we return 10 since it's larger. 
 

Constraints:

1 <= piles.length <= 100
1 <= piles[i] <= 10 ^ 4
*/

/*解题思路 dp
这道题可以参考此视频 https://www.youtube.com/watch?v=e_FrC5xavwI

这里比较难理解的有两个点
第一个是什么叫做play optimally。就是两个人都要用从当前位置开始，
最优的解法找到从当前位到数组的结尾之间可以获得的最大数值。其实我们
并不需要知道确切的每一步是谁走，只需要知道走的这个人最多可以或者
多大的值就可以了。

第二个是这个m是什么意思。意思就是说，每一步当前玩家最多可以拿多少，取决于
上一步的人拿了多少。对于当前的m来说，m = Max(上一个m，上一个取走的堆数i)

dp[pos][m]指得是任一个玩家，在pos这个位置上，且最多可以走2m
这么远的情况下，最多可以获取多少值。
*/

class Solution {
    int[][] dp;
    public int stoneGameII(int[] piles) {
        int m = piles.length;
        dp = new int[m+1][m+1];//最长可以取m步
        int[] preSum = new int[m];
        preSum[m-1] = piles[m-1];
        //这次的pre-sum是从后往前计算了，因为我们是从前往后走的
        for(int i=m-2; i>=0; i--) preSum[i] = (preSum[i+1]+piles[i]);
        
        return helper(preSum, piles, 0, 1);
    }
    
    public int helper(int[] preSum, int[] piles, int pos, int m){
        if(pos >= preSum.length) return 0;
        if(dp[pos][m] != 0) return dp[pos][m];
        
        int max = piles[pos];//直接计算当前位的最大值
        for(int i=1; i<=2*m; i++){
            max = Math.max(max, preSum[pos]-helper(preSum,piles, pos+i, Math.max(m, i)));
        }
        dp[pos][m] = max;
        return max;
    }
}


//或者我们每一步都找下一步可以返回的最小值
class Solution {
    int[][] dp;
    public int stoneGameII(int[] piles) {
        int m = piles.length;
        dp = new int[m+1][m+1];//最长可以取m步
        int[] preSum = new int[m];
        preSum[m-1] = piles[m-1];
        for(int i=m-2; i>=0; i--) preSum[i] = (preSum[i+1]+piles[i]);
        
        return helper(preSum, piles, 0, 1);
    }
    
    public int helper(int[] preSum, int[] piles, int pos, int m){
        if(pos >= preSum.length) return 0;
        if(dp[pos][m] != 0) return dp[pos][m];
        
        //只有这里不一样, 计算下一位的最小值，preSum[pos]-min等于当前最大值
        int min = Integer.MAX_VALUE;
        for(int i=1; i<=2*m; i++){
            min = Math.min(min, helper(preSum,piles, pos+i, Math.max(m, i)));
        }
        dp[pos][m] = preSum[pos]-min;
        return dp[pos][m];
    }
}
