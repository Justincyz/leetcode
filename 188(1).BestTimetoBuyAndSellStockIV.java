/*188. Best Time to Buy and Sell Stock IV
Hard: 
Say you have an array for which the ith element is the price of a 
given stock on day i.

Design an algorithm to find the maximum profit. You may complete 
at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, 
you must sell the stock before you buy again).

Example 1:

Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), 
profit = 4-2 = 2.
Example 2:

Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), 
profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 
(price = 3), profit = 3-0 = 3.
*/

/*解题思路
这道题目是继续前几道买卖股票的题目。这次告诉我们可以买卖K次股票，问在经过K次之后最大的获益是什么。
这道题目的写法是按照九章算法给出的办法写的，实际上也是九章中对买卖股票3的解法的一种通用解。
我们总共最多可以买卖K次股票，假设买和卖都单独算一个动作的话，那么总共可以购买K次股票且可以卖出K次
股票。所以我们需要一个长度为2*K+2的数组来记录每个买卖股票的阶段。我们定义奇数(1,3,5,..2k+1)
为卖出(1,2,3,...,k)次股票的获益，定义偶数为购买第i (1<=i<=k)次股票的收益。那么我们对每一天的
股票价格都要计算这2k+1次获得的利润。详细计算方式可以看九章的PPT和视频讲解

*/

class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        
        //switch to Best Time to Buy and Sell Stock II
        if(k>= n/2){
            int res =0;
            for(int i=1; i<n; i++){
                if(prices[i]>prices[i-1]) res+=(prices[i]-prices[i-1]);
                
            }
            return res;
        }
        
        
        int[][] dp = new int[n+1][2*k+2];
        dp[0][1] = 0;
        for(int i=2; i<=k; i++){
            dp[0][i] = Integer.MIN_VALUE;
        }
        
        for(int i=1 ; i<=n; i++){
        	//手中没有股票的状态
            for(int j=1; j<=(2*k+1);j+=2){
                dp[i][j] = dp[i-1][j];//什么都不做
                if(j>1 && i>=2 && dp[i-1][j-1] != Integer.MIN_VALUE){
                	//昨天没有持有股票 -> dp[i-1][j], 昨天持有股票，今天卖出清仓 ->dp[i-1][j-1]+prices[i-1]-prices[i-2]
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]+prices[i-1]-prices[i-2]);
                }
            }
            //手中有股票的状态
            for(int j=2; j<=2*k; j+=2){
                dp[i][j] = dp[i-1][j-1]; 
                if(i >=2 && dp[i-1][j] !=Integer.MIN_VALUE){
                	//昨天没有持有股票 ->dp[i-1][j-1]，昨天就持有股票，继续持有并且获利 dp[i-1][j] + prices[i-1]-prices[i-2]
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j] + prices[i-1]-prices[i-2]);
                }
            }
        }
        //只需要计算所有手中没有股票的状态
        int res = Integer.MIN_VALUE;
        for(int i=1; i<=2*k+1; i+=2){
            res = Math.max(res, dp[n][i]);
        }
        return res;
    }
}