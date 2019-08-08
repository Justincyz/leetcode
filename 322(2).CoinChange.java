/*322. Coin Change
Medium: 
You are given coins of different denominations and a total amount of 
money amount. Write a function to compute the fewest number of coins 
that you need to make up that amount. If that amount of money cannot 
be made up by any combination of the coins, return -1.

Example 1:
Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.


*/

/*解题思路
这道题目要求我们用最少的钱组合出来给定的amount, 那么用dp的方法是最简单的。首先我们需要一个
1-D array代表的是[0, amount]的每一个数值。将所有的位置初始化为 max值，因为我们对每一个位置
要找到这一位的最小值。然后我们对每一位都遍历一次我们所拥有的coin。假设此时我们在第i位。那么我们
要取得值就是 dp[i-coin]和第dp[i]位的最小值(因为dp[i]位代表的是第i位最小的硬币组合数目)。
最后我们判断dp[amount]位是否被更新过，如果没有的话那么就返回-1;
关于进一步的细节可以看dp_lecture2.pdf

*/

//11ms beats 67%
class Solution {
    public int coinChange(int[] coins, int amount) {

        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=0; i<=amount; i++){
            for(int coin: coins){
                if(i-coin <0 || dp[i-coin] == Integer.MAX_VALUE) continue;
                dp[i] = Math.min(dp[i], dp[i-coin]+1);    
            }
        }
      
        return dp[amount] == Integer.MAX_VALUE? -1 : dp[amount];
    }
}

/*
这一个方法有点投机取巧的意思，但是思维还是一样的。因为最后无非就是不同硬币的组合方式。
每一次我们不需要从0到amount整个去取值了。对于每一个硬币的币值，我们最多可以取 amount/coin
这么多，最少可以取0个。那么对于每一个硬币就在这个范围内取值。同时我们还用上了剪枝的效果，
假设当前取法获得的硬币数目已经大于我们原先取到的最优解res的话，那么直接跳过不处理了。
为了用剪枝法，我们需要先对数组进行排序，然后从大到小取硬币的值。这样才能用剪枝的效果，否则
从小到大的话 count+k永远不会大于res的数目。

*/
//8ms beats 97%
class Solution {
    int res = Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        helper(coins, coins.length-1, amount, 0);
        return res == Integer.MAX_VALUE? -1 : res;
    }
    
    public void helper(int[] coins, int s, int amount, int count){
        int coin = coins[s];
       
        if(s == 0){
            if(amount % coin == 0){
                res = Math.min(res, count+ amount/coin);
            }
        }else{
            for(int k = amount/coin; k >= 0 && count + k < res; k--){
                helper(coins, s-1, amount - k*coin, count+k);
            }
        }
    }
}