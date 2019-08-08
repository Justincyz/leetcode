/*Hard
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.
Note:

You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
Example:

Input: [3,1,5,8]
Output: 167 
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5 +3*5*8 +1*3*8 + 1*8*1 = 167
*/
/*
思路:
这道题用的是动态规划思想。其实从整个过程来看，最后一步总是剩下一个数字来和两个1相乘，比方说上面例子中的数字8。也就是说，这个数字两边的区域的数字并没有混乘（如果混乘的话，中间的数字8就不会留到最后了），故这个最后的数字可以把整个序列划分为两个部分，这就是动态规划结构的构造基础。

dp[i][j]的含义就是从序列 i 位置到序列 j 位置（不包含边界即(i,j)）的最优值。那么递推式就显而易见了：

对于最后一个被爆开的气球来说，是nums[-1]*nums[i]*nums[n]. nums[-1]和nums[n]被设为1

这个解释了很多细节
https://leetcode.com/problems/burst-balloons/discuss/76229/For-anyone-that-is-still-confused-after-reading-all-kinds-of-explanations...
*/
class Solution {
    public int maxCoins(int[] nums) {
        
        int[] enums = new int[nums.length+2];
        int index = 0;
        enums[index++]  = 1;//前后要加上1以免越界
        for(int i:nums) enums[index++] = i;
        enums[index++]  = 1;
        int n = enums.length;
        
        int [][]dp = new int[n][n];
        for(int i=2; i<n; i++){ //这里i 指的是长度
            for(int left = 0; left <n-i; left++){
                int right = left+i;
                for(int j = left+1; j<right;j++){ //假设j是最后一个被扎破的气球
                    dp[left][right] = Math.max(dp[left][right], dp[left][j]+dp[j][right]+enums[left]*enums[right]*enums[j]);
                }
            }
        }
        return dp[0][n-1];
    }
}