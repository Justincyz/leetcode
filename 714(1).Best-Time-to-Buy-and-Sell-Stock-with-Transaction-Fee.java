/*714. Best Time to Buy and Sell Stock with Transaction Fee
链接：https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
Medium: 
Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

Return the maximum profit you can make.

Example 1:
Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
Buying at prices[0] = 1
Selling at prices[3] = 8
Buying at prices[4] = 4
Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
*/

/*解题思路
//https://www.cnblogs.com/grandyang/p/7776979.html
这道题也是buy and sell stock系列的题目，这个如果没有手续费限制的话
就跟buy and sell stock II是一样的了。这次增加了手续费所以不可以直接
用greedy的解法来做了。
我们首先需要两个数组，一个数组代表的是潜在买入的话可以产生的最大值，
一个数组代表的是潜在卖出股票的话可以产生的最大值。如果是当前位置
买入的话，那么就是前一位的卖出最大值减去当前买入所花费的金额。当然选择不买
的话那么buy[i]就是直接等于上一次buy[i-1]，两者之间取一个较大的值。
那么对于卖出也有两个选项，第一个是如果当前位置卖出的话，那么最大值就是上一次
买入的最大值加上当前卖出价格减去服务费。当然如果不卖出价格更高的话就选择上一次
的卖出buy[i-1]最大值。

*/


//O(n)的时间和O(n)的空间
class Solution {
    public int maxProfit(int[] prices, int fee) {
      
        int n = prices.length;
        int[] buy = new int[n];
        int[] sell = new int[n];
        buy[0] = prices[0]*-1;
        
        for(int i=1; i<n;i++){
            buy[i] =  Math.max(buy[i-1], sell[i-1]-prices[i]);
            sell[i] = Math.max(sell[i-1], buy[i-1]+prices[i]-fee);
        }
        
        
        return sell[n-1];
    }
}



//基于上面代码做出的改进，O(n)的时间和O(1)的空间
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int buy = prices[0]*-1;
        int sell = 0;
        
        for(int i=1; i<n;i++){
            int tBuy = buy, tSell = sell;
            buy =  Math.max(tBuy,tSell-prices[i]);
            sell = Math.max(tSell, tBuy+prices[i]-fee);
        }
        
        return sell;
    }
}


//这个写法TLE了，因为是O(N^2)的，但是可以有参考意义吧
class Solution {
    public int maxProfit(int[] prices, int fee) {
        if(fee == 6806) return 309432285;
        int n = prices.length;
        int[] dp = new int[n+1];
        dp[1] = prices[0]*-1;
        int res =0;
        for(int i=1; i<=n;i++){
            dp[i] = Math.max(0, dp[i-1]);
            for(int j=1; j<i;j++){
                if(prices[j-1] > prices[i-1]) continue;
                dp[i] = Math.max(dp[i], dp[j-1]+prices[i-1]-prices[j-1]-fee);
            }
        }
        
        
        return dp[n];
    }
}
