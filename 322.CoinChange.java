/*
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [2, 5, 1], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
*/

class Solution {

/*
[0,M,1,M,2,M,3,M,4,M,5,M] 第一轮结束 当coin =2
[0,M,1,M,2,1,3,M,4,M,2,M] 第二轮结束 当coin =5
[0,1,1,2,2,1,3,4,4,5,2,3] 第三轮结束 当coin =1
*/

    public int coinChange(int[] coins, int amount) {
        long[] dp = new long[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] =0;
        for(int coin : coins){
            for(int i= coin; i<= amount; i++){
                dp[i] = Math.min(dp[i], dp[i-coin]+1);       
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : (int) dp[amount];
    }
}



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
