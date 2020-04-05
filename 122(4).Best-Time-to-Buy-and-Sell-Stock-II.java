/* 122. Best Time to Buy and Sell Stock II
Easy: 
Say you have an array for which the ith element is the price 
of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete 
as many transactions as you like (i.e., buy one and sell one 
share of the stock multiple times).

Note: You may not engage in multiple transactions at the same 
time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.

Example 2:
Input: [1,2,3,4,5]
Output: 4
Explanation: 
Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
engaging multiple transactions at the same time. You must sell before buying again.

Example 3:
Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

*/

/*解题思路
题目要求找到和最大的买卖股票方式。并没有要求交易的次数。只是限制每一次只能有一笔交易在进行。
只要是p[i]和p[i-1]呈现上升趋势的话，那就在p[i-1]的时候买入在p[i]的时候卖出。

*/

class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        int res =0;
        for(int i=0; i< prices.length-1; i++){
            if(prices[i] < prices[i+1]) res+=(prices[i+1]-prices[i]);
        }
        return res;
    }
}

//一个意思的写法
class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        int total =0, n = prices.length;
        int fast = 0, slow = 0;
        
        while(fast < n-1 && slow < n){
            if(prices[fast+1] <= prices[fast]){
                total +=(prices[fast] - prices[slow]);
                slow = fast+1;
            }
            fast++;
        }
        if(fast>slow)  total +=(prices[fast] - prices[slow]);
        return total;
    }
}