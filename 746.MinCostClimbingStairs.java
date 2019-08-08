/*
Easy
On a staircase, the i-th step has some non-negative cost cost[i] assigned 
(0 indexed).

Once you pay the cost, you can either climb one or two steps. You need 
to find minimum cost to reach the top of the floor, and you can either start 
from the step with index 0, or the step with index 1.

Example 1:
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.

Example 2:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
*/

/*
这道题是简单的 dp 题目，从头往后走的。只需要两个值不停地记录当前值的前-1位或者-2位就可以了。然后
每一次要更新一下。pre1是 i-1, pre2是i-2
*/
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] table = new int[n+1];
        int pre1 = cost[1], pre2 = cost[0], cur=0;
        for(int i=2; i<n; i++){
            cur = cost[i];
            cur = Math.min(pre1, pre2)+cur;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1>pre2? pre2:pre1;
    }
}