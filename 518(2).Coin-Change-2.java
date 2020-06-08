/*518. Coin Change 2
Medium: 

You are given coins of different denominations and a total amount of 
money. Write a function to compute the number of combinations that 
make up that amount. You may assume that you have infinite number of 
each kind of coin.

Example 1:
Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

Example 2:
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.

Example 3:
Input: amount = 10, coins = [10] 
Output: 1
*/

/*解题思路
这道题是之前那道Coin Change的拓展，那道题问我们最少能用多少个硬币组成给定的钱数，
而这道题问的是组成给定钱数总共有多少种不同的方法。那么我们还是要使用DP来做，首先我们
来考虑最简单的情况，如果只有一个硬币的话，那么给定钱数的组成方式就最多有1种，就看此钱
数能否整除该硬币值。那么当有两个硬币的话，那么组成某个钱数的方式就可能有多种，比如可能由
每种硬币单独来组成，或者是两种硬币同时来组成。那么我们怎么量化呢，比如我们有两个硬币
[1,2]，钱数为5，那么钱数的5的组成方法是可以看作两部分组成，一种是由硬币1单独组成，那么
仅有一种情况(1+1+1+1+1)；另一种是由1和2共同组成，说明我们的组成方法中至少需要由一个2，
所以此时我们先取出一个硬币2，那么我们只要拼出钱数为3即可，这个3还是可以用硬币1和2来拼，
所以就相当于求由硬币[1,2]组成的钱数为3的总方法。那么我们需要一个二维的dp数组，其中
dp[i][j]表示用前i个硬币组成总和为j的不同组合方法，那么怎么算才不会重复，也不会漏掉呢？
我们采用的方法是每次增加一种新的钱币类型，每增加一种新的硬币，都从1遍历到amount，对于遍历到的
当前总和j，组成方法就是不加上当前硬币可以构成j 总和的dp[i-1][j]，同时加上使用当前硬币，
也就是当前数额减去当前硬币币值的次数。当然钱数j要大于当前硬币值，那么我们的递推公式也在上
面的分析中得到了：

dp[i][j] = dp[i - 1][j] + (j >= coins[i - 1] ? dp[i][j - coins[i - 1]] : 0)

注意我们要初始化每行的第一个位置为0：      

*/

class Solution {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length+1][amount+1];
        
        dp[0][0] =1;
        for(int j=1; j<=coins.length; j++){
            dp[j][0] =1;

            for(int i=1; i<=amount; i++){
                dp[j][i] = dp[j-1][i]+ (i>=coins[j-1] ? dp[j][i-coins[j-1]]: 0); 
            }
        }
        return dp[coins.length][amount];
    }
}

/*
我们可以对空间进行优化，我们发现dp[i][j]仅仅依赖于dp[i - 1][j] 和 dp[i][j - coins[i - 1]] 
这两项，那么我们可以使用一个一维dp数组来代替，此时的dp[i]表示组成钱数i的不同方法。
*/

class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        
        dp[0] =1;
        for(int j=1; j<=coins.length; j++){
            for(int i=1; i<=amount; i++){
                dp[i] += (i>=coins[j-1] ? dp[i-coins[j-1]]: 0); 
            }
        }
        return dp[amount];
    }
}



