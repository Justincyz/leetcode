/*89. k Sum
Hard: 
Given n distinct positive integers, integer k (k <= n) and a number target.

Find k numbers where sum is target. Calculate how many solutions there are?


Example 1
Input:
List = [1,2,3,4]
k = 2
target = 5
Output: 2
Explanation: 1 + 4 = 2 + 3 = 5

Example 2
Input:
List = [1,2,3,4,5]
k = 3
target = 6
Output: 1
Explanation: There is only one method. 1 + 2 + 3 = 6

*/

/*解题思路
这道题目是根据九章算术DP章节学习到的，有任何疑惑可以参考九章视频。
题目问的是，在给定整数数组中，找到所有不同方式可以组成target的次数，使用的整数个数不能超过k个。
那我们就要开一个三维的数组，最外层是遍历每个元素。第二层是用来保证不超过K个元素，最里面是保证到达
target。
*/

public class Solution {
    /**
     * @param A: An integer array
     * @param k: A positive integer (k <= length(A))
     * @param target: An integer
     * @return: An integer
     */
    public int kSum(int[] A, int k, int target) {
        int m = A.length;
        
        int[][][] dp = new int[m+1][k+1][target+1];
        
        dp[0][0][0] = 1;
        
        for(int i =1; i<=m; i++){
            for(int j=0; j<=k; j++){
                for(int p =0; p<=target; p++){
                    dp[i][j][p] = dp[i-1][j][p];
                    if(j>0 && p >= A[i-1])
                        dp[i][j][p] += dp[i-1][j-1][p-A[i-1]];
                }
            }
        }
        
        return dp[m][k][target];
    }
}