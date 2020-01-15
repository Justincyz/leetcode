/*70. Climbing Stairs
Easy
链接: https://leetcode.com/problems/climbing-stairs/submissions/
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways 
can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step


*/

/*解题思路
注意这道题问我们总共有多少种到达方式到n，所以有两个base case, 一个是dp[1] = 1
代表到达第一点的走法有一种.同理对于dp[2] = 1代表到达第二个位置的走法也只有一种。

*/
class Solution {
    public int climbStairs(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
  
        int p1 = 1; //往前看两步
        int p2 = 1; //往前看一步
        for(int i=2; i<=n; i++){
            int cur = 0;
            cur += p1;
            cur+=p2;
            p1 = p2;
            p2 = cur;
        }
        return p2;
    }
}

//这是第一次写的，开数组是最简单的方式
class Solution {
    public int climbStairs(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 1;
        for(int i=2; i<=n; i++){
            dp[i] += dp[i-1];
            dp[i] += dp[i-2];
        }
        return dp[n];
    }
}