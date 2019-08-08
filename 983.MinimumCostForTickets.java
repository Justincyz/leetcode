/*983. Minimum Cost For Tickets
Medium
In a country popular for train travel, you have planned some train travelling one 
year in advance.  The days of the year that you will travel is given as an array days. 
 Each day is an integer from 1 to 365.

Train tickets are sold in 3 different ways:

a 1-day pass is sold for costs[0] dollars;
a 7-day pass is sold for costs[1] dollars;
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.  For example, if we get a 
7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.

Return the minimum number of dollars you need to travel every day in the given list of days.

 
Example 1:
Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
Explanation: 
For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total you spent $11 and covered all the days of your travel.

Example 2:
Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
Output: 17
Explanation: 
For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
In total you spent $17 and covered all the days of your travel.

*/

/*解题思路
题目太长了，其实就是有三种票价，一日游，七日游和三十日游。价格不一样(有可能三十日游的价格低于一日游，
也可能一日游的价格是最高的，这点做题的时候要注意)。同时有一些出行日，这些日子是随意排列的。问，如何在
所有出行日都有票出行，并且价格最低。这道题就是一个dp的问题。首先问题限制在了一年的时间之内。所以
最笨的办法是直接用一个长度为365的int[], 但是其实我们并不需要那么多，只要长度到出行的最后一天就
可以了。假设最后一天是第N天，那我们只需要一个n+1长度的array就可以了。那么每一次取值遇到有出行的那天
，有三种方法。取前一天的值加上一天票价的。前面第七天的值加上七天的票，或者前三十天的值加上三十天的票。
这个票值得是cover前面某一段时间的。有一点要注意，打个比方，在三十天内如果票价已经超过了直接买个
三十天的，那么可以直接买个三十天的票。如果某一天不是出行日，那么直接复制前一天的总花费就可以了。

*/

//这是下面那个复杂版本的简化版
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
    	int n = days[days.length-1];
        int[] dp = new int[n+1];

        int p = 0;
        dp[0] = 0;
        for(int i = 1; i<=n; i++){
        	if(days[p] == i){
                p++;
                dp[i] = Math.min(costs[0] + dp[i - 1], 
                	          Math.min(costs[1] + dp[Math.max(0, i - 7)], 
                              costs[2] + dp[Math.max(0, i - 30)]));
            }else 
                dp[i] = dp[i - 1];
            
        }
        return dp[n];
    }
}


//这是一开始写的复杂版本
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
    	int n = days[days.length-1];
        int[] dp = new int[n+1];

        int p = 0;
        dp[0] = 0;
        for(int i = 1; i<=n; i++){
        	if(days[p] == i){
        		p++;
                int minSingle = Math.min(costs[0], Math.min(costs[1], costs[2]));
        		if(i<30){
        			if(i < 7){
        				dp[i] = Math.min(dp[i-1]+minSingle, costs[1]);
        			}else{
        				dp[i] = Math.min(dp[i-1]+minSingle, Math.min(dp[i-7]+costs[1], costs[2]));
        			}
        		}else{
        			int money = Math.min(dp[i-1]+minSingle, dp[i-7]+costs[1]);
        			money = Math.min(money, dp[i-30] + costs[2]);
        			dp[i] = money;
        		}
        	}else{
        		dp[i] = dp[i-1];
        	}
        }
        return dp[n];
    }
}






