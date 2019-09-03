/*92. Backpack
Medium: 
在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A[i]

Example
样例 1:
	输入:  [3,4,8,5], backpack size=10
	输出:  9

样例 2:
	输入:  [2,3,5,7], backpack size=12
	输出:  12

*/

/*解题思路
这道题目还是经典的dp做法，一般来说是用一个二维dp[i][j]数组来储存前i个物品,空间
容量为j时的最大值。最后返回的是dp[A.size()][m]，即考虑A中的所有物品，且背包容量为m时，
背包被占用空间的最大值。但是我们可以直接用一个一维数组就来代替，leetcode中也有类似
的题目(忘记是那一道了= =)。但是我这里直接用了一位数组来做，设dp[0]为true, 就是说空间
为0的情况下，我们可以装重量为0的物品。假设第i位的物品重量是w,我们从后往前遍历数组dp[j](w<=j<=m)
，如果第dp[j-w]为true的话，说明在利用之前的物品我们可以将背包装到质量为dp[j-w]，那么当前位dp[j]
就为true，相当于在原来的基础上我们可以累加到总质量为j。
这里注意我们是从后往前遍历。如果我们是从前往后遍历的话就会出现重复累加的情况，比如说我们只有一个
质量为1的物品，从后往前便利的话只有在 j==1 的地方是true。但是如果是从前往后遍历的话那所有的dp[j]
则都会为true.

*/

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        boolean[] dp = new boolean[m+1];
        dp[0] = true;
        
        for(int a: A){
            if(a >m) continue;
            for(int i=m; i>=a; i--){
                dp[i] = (dp[i] || dp[i-a]);
            }
            if(dp[m] == true) return m;
        }
        
        for(int i=m; i>=0 ;i--){
            if(dp[i]) return i;
        }
        return 0;
    }
}