/*
Easy: 
Say you have an array for which the ith element is the price 
of a given stock on day i.

If you were only permitted to complete at most one transaction 
(i.e., buy one and sell one share of the stock), design an 
algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

*/

/*解题思路
这是一个系列类型的题目，考的概率还是比较大的。作为系列的第一题，这个问的是在股票交易中
找到购买一次可以赚取的最大差价问题。我们可以这样考虑这个问题。对于第i天来说，我们要找的
是在j之后最大的值，假设为prices[i] (i>j)。在找到那个最大的差值之前，最好有比prices[j]还小的值。如果存在这样的位置k，那么
prices[k] - prices[j] < 0,如果有，那么我们就更新一下这个最小的值。如果i指针的值一直大于
j指针指向的值，那么我们就取这个差值和max之间较大的值。如果找到了比j小的k，那么我们就更新j指针。

*/

class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0 || prices.length == 1) return 0;
        int max =0;
        int prev = prices[0];
        for(int i =1; i< prices.length; i++){
            if(prices[i] - prev > 0){
               max = Math.max(max, prices[i] - prev);
            }
            else{
                prev = prices[i];
            }
        }
        
        return max;
    }
}


class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length ==0) return 0;
        int slow =0, max = 0;
        for(int i=1; i< prices.length; i++){
            if( prices[i-1] > prices[i])
                max = Math.max(max, prices[i-1] - prices[slow]);
                slow = prices[i] < prices[slow]? i: slow;
            }
        }
        //有可能一直是递增的，那么最后要判断一下
        max = Math.max(max, prices[prices.length-1] - prices[slow]);
        
        return max;
    }
}