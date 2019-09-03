/*91. Minimum Adjustment Cost
Medium: 
Given an integer array, adjust each integers so that the difference of every adjacent integers are not greater than a given number target.

If the array before adjustment is A, the array after adjustment is B, you should minimize the sum of |A[i]-B[i]|

Example
Example 1:
	Input:  [1,4,2,3], target=1
	Output:  2

Example 2:
	Input:  [3,5,4,7], target=2
	Output:  1
	
Notice
You can assume each number in the array is a positive integer and not greater than 100.
*/

/*解题思路
这道题是九章算法中dp章节的一个题目，具体解法可以参考九章算法视频。
这道题目给出的notice很重要，告诉我们每一个数都是大于等于1小于等于100的。
这个启示告诉我们，通过变化后的每个数字的值也应该处于 1到100之间。我自己的
理解是这样的，list A中所有的值我们都可以将它比作处于区间 1到100的点。所以
任意两个相邻的点(其实所有pair都是这样)差值都不会大于100. 所以即使这个给出的target
非常的大，比如说1000，那我们完全不需要考虑大于100或者小于1的部分，因为超出范围的值已经大大超过了第I位和第I-1位的绝对值了。

我们设dp[i][j]表示将A的前I个元素改成B array的最小代价，确保前I个改好的元素中任意
两个相邻的元素的差不超过target, 并且A[i-1]改成j(j代表的是值的大小)

dp[i][k] 表示第i个数变成k时，前i个数调整的代价和最小值。
dp[i][j] = min( j-target<=k<=j+target, 1<=k<=100){f[i-1][k]+|j-A[i-1]|}
       |                                          |        |
(将A前i个元素改成B的最小代价，A[i-1]改成j)           |  (A[i-1]改成j的最小代价)
								(将A前i-1个元素改成B的最小代价，A[i-2]改成k)
*/


public class Solution {
    public int MinAdjustmentCost(List<Integer> A, int target) {
        int n = A.size();
        int[][] dp = new int[n+1][101];
        
        /*
        初始化条件，对于第一个元素来说，将它变为1-100之间任意一个数所
        付出的代价
        */
        for(int i=1; i<=100; i++){
            dp[1][i] = Math.abs(A.get(0) - i);
        }
        
        for(int i=2; i<=n; i++){
            for(int j=1; j<=100; j++){
                dp[i][j] = Integer.MAX_VALUE;
                //dp[i][k] 表示第i个数变成k时，前i个数调整的代价和的最小值。
                for(int k = j-target; k<= j+target; k++){
                    if(k<1 || k>100) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][k]);
                }
                dp[i][j] += Math.abs(j-A.get(i-1));
            }
        }
        
        int res = Integer.MAX_VALUE;
        for(int i=1; i<=100; i++) res = Math.min(res, dp[n][i]);
        
        return res;
    }
}

/*
如果这道题目给出的范围不是1-100， 那么我们可以先遍历list, 找出List中
数值最大到最小的范围，然后替换100
*/