/*123. Best Time to Buy and Sell Stock III
Hard: 
Say you have an array for which the ith element is the price of 
a given stock on day i.

Design an algorithm to find the maximum profit. You may complete 
at most two transactions.

Note: You may not engage in multiple transactions at the same 
time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: 
Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

Example 2:
Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
engaging multiple transactions at the same time. You must sell before buying again.

Example 3:
Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
*/

/*解题思路
这道题目要求我们最多只能做两次的股票交易。这两个交易不可以同时进行。问两次交易
最大可以赚多少钱。

*/


/* 2ms dp解法
这里我们需要两个递推公式来分别更新两个变量local和global，我们其实可以求至少k次交
易的最大利润，找到通解后可以设定 k = 2，即为本题的解答。我们定义local[i][j]为在到达第
i天时最多可进行j次交易并且最后一次交易在最后一天卖出的最大利润，此为局部最优。然后我们定义
global[i][j]为在到达第i天时最多可进行j次交易的最大利润，此为全局最优。它们的递推式为：

//假设我们一定要在第i天卖出
local[i][j] = max(global[i - 1][j - 1], local[i - 1][j]) + diff 

global[i][j] = max(local[i][j], global[i - 1][j])

其中局部最优值是比较前一天少交易一次的全局最优加上差值，和前一天的局部最优加上差值中取较大值，
而全局最优比较局部最优和前一天的全局最优

进一步解释:
第 i 天卖第 j 支股票的话，一定是下面的一种：

1. 今天刚买的
那么 Local(i, j) = Global(i-1, j-1)
相当于啥都没干

2. 昨天买的
那么 Local(i, j) = Global(i-1, j-1) + diff
等于Global(i-1, j-1) 中的交易，加上今天干的那一票

3. 更早之前买的
那么 Local(i, j) = Local(i-1, j) + diff
昨天别卖了，留到今天卖

但其实第一种情况是不需要考虑的，因为当天买当天卖不会增加利润，完全是重复操作，
这种情况可以归纳在global[i-1][j-1]中，那么由于两项都加上了diff，所以我们可以
把diff抽到max的外面
*/
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len < 2) return 0;
        int[][] global = new int[len][3], local = new int[len][3];
        for(int i = 1; i< len; i++){
            int diff = prices[i] - prices[i-1];
            for(int j = 1; j<=2; j++){
                local[i][j] = Math.max(global[i-1][j-1],local[i-1][j])+diff;
                global[i][j] = Math.max(local[i][j], global[i-1][j]);
            }
        }
        return global[len-1][2];
    }
}


/* 2ms pre-Sum的解法
我们需要一个array来记录在每个节点可以获取的最大报酬。
最大报酬的计算就是当前节点的值减去此节点之前出现过的最小值，和前一个节点的值，两者
之间找到一个较大的值。
然后我们从尾部往前遍历。这一次遍历的方式就是记录一个大于当前位的最大值max。那么对于
位置 i来说，i之后可以取到的最大值就是 max - prices[i]。i之前可以取到的最大值
就存在maxBefore array中。所以两者相加就相当于在第i位可以取到的最大值。将此值
和全局最大值比较。最后返回全局最大值
*/
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len < 2) return 0;
        int[] maxBefore = new int[len];
        int min = prices[0];
        for(int i=1; i< len; i++){  //先找到Pre-sum
            maxBefore[i] = Math.max(maxBefore[i-1], prices[i]-min);
            min = Math.min(min, prices[i]);
        }
        int max = prices[len-1];
        int ret =0;
        for(int i = len-2; i>=0; i--){   //从后往前找最大的，同时加上pre-sum
            max = Math.max(prices[i], max);
            ret = Math.max(ret, max - prices[i]+ maxBefore[i]);
        }
        
        return ret;
    }
}

/* 
开始的金钱资本是0元，首次买入股票会损失掉一定的钱数，假设第i天买入的，
持有资本就是buy1 = -prices[i]，当第j天卖出，第一次交易之后，持有的收益就是 
sel1 =（-prices[i]+prices[j]），第二次买入假设第k天买入，buy2 = sel1-prices[k]，
相应的，假设第m天，第二次交易结束，持有的收益，sel2 = buy2+price[m]，以此类推，
*/
class Solution {
    public int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE;
        int buy2 = Integer.MIN_VALUE;
        int sell1 = 0;
        int sell2 = 0;
        for(int price: prices){
            sell2 = Math.max(sell2, price+buy2);
            buy2 = Math.max(buy2, sell1-price);
            sell1 = Math.max(sell1, price+buy1);
            buy1 = Math.max(buy1, -price);
        }
        return sell2;
    }
}


/* 400ms naive approach
这个用的是naive 的 N^2的办法。对每一个点进行分割。然后用
121题的办法，对分割点前后的subarray各做一个找最优解的遍历。这样将将过了时间
的限制。
*/
class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0|| prices.length ==1) return 0;
        if(prices.length<3) return (prices[1]-prices[0] >0) ?prices[1]-prices[0]:0;
        int max = 0;
        for(int i=1; i<prices.length-1; i++){
            int tempMax1 = 0, tempMax2 = 0;
            int prev = prices[0];
            for(int j=1; j<=i; j++){
                if((prices[j] - prev) > 0){
                    tempMax1 = Math.max(tempMax1, prices[j] - prev);
                }else{
                    prev = prices[j];
                }
            }
            
            prev = prices[i];
            for(int j= i+1; j< prices.length; j++){
                if((prices[j] - prev) > 0){
                    tempMax2 = Math.max(tempMax2, prices[j] - prev);
                }else{
                    prev = prices[j];
                }
            }
            max = Math.max(max, tempMax2+tempMax1);
        }
        
        return max;
    }


 /*参考链接
https://www.cnblogs.com/grandyang/p/4281975.html
 */
}