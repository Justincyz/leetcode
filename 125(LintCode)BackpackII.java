/*125. Backpack II
Medium: 
有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值.

问最多能装入背包的总价值是多大?

Example
样例 1:

输入: m = 10, A = [2, 3, 5, 7], V = [1, 5, 2, 4]
输出: 9
解释: 装入 A[1] 和 A[3] 可以得到最大价值, V[1] + V[3] = 9 
样例 2:

输入: m = 10, A = [2, 3, 8], V = [2, 5, 8]
输出: 10
解释: 装入 A[0] 和 A[2] 可以得到最大价值, V[0] + V[2] = 10
Challenge
O(nm) 空间复杂度可以通过, 不过你可以尝试 O(m) 空间复杂度吗?
*/

/*解题思路
这道题目是根据九章算术DP章节学习到的，有任何疑惑可以参考九章视频。
这道题也还是背包问题，我还是记得leetcode中有一道类似的问题，忘记是那一题了。
还是比较直接的，可以直接参考代码，注意dp的初始长度是m+1,我们遍历是从第一个物品
到第m个物品
*/

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
        if(A.length ==0) return 0;
        int n = A.length;
        
        int[] dp = new int[m+1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        
        for(int i=0; i<n; i++){
            int weight = A[i], val = V[i];
            
            //从后往前遍历，避免重复遍历
            for(int j=m; j>=weight; j--){
                dp[j] = Math.max(dp[j], dp[j-weight]+val);
            }
        }
        
        int res = Integer.MIN_VALUE;
        //这里必须要遍历所有的结果，因为有一些东西可能重量小且价值高
        for(int v=m; v>=0;v--){
            res = Math.max(res, dp[v]);
        }
        return res;
    }
}