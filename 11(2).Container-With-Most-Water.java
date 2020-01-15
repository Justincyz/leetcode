/*11. Container With Most Water
链接：https://leetcode.com/problems/container-with-most-water/
Medium: 
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Example:
Input: [1,8,6,2,5,4,8,3,7]
Output: 49， 选择左边的8和右边的7，中间间隔了七个，所以是7x7 = 49
Note: You may not slant the container and n is at least 2.
*/

/*解题思路
这道题目比较神奇，用的是greedy的解法。
首先说一下O(N^2)的解法，其实很直接的办法，就是对每一个高度，我们往右边搜索，
查看可以与之形成最大容积的柱子，因为我们已经针对每一个柱子的左边搜索过了，所以不需要再搜索一次了。
那么我们来看一下O(n)的解法。
流程是，我们使用两个指针首先指向最左边和最右边的两个柱子。每一次我们向内移动
柱子高度较小的指针，直到两个指针相遇，每一次计算一个最大的可能值，最后返回结果。为什么这样子可行呢，解释如下。
首先第一步，对于最外面的两个柱子[i, j]来说，这是我们蓄水池最长的底部空间了。假设各自的高度为hi和hj，此时我们
将较矮的一端向内移动一位，假设此时移动的是i。 我们不需要再计算i到j-1, j-2的
所有位置而直接移动i的原因是，此时如果i不变的话，移动j只会让蓄水的值小于Min(hi, hj)*(j-i)。证明如下。
我们证明Min(hi, hj-1)*(j-i-1)一定小于 hi*(j-i)。因为如果向内移动之后的边界高度高于hi的话，那么容量为hi*(j-i-i),因为高度取决于
较矮的边。如果hj-1 小于 hi的话，那么总的容量 hj-1 *(j-i-1)还是小于 hi*(j-i)，因为此时不仅高度矮了，底边的长度也更短了。此时我们证明移动i是没问题的。
相同的道理也适用于移动j，当hj < hi的情况。

证明参考
https://leetcode.com/problems/container-with-most-water/discuss/200246/Proof-by-formula

*/


class Solution {
    public int maxArea(int[] height) {
        if(height == null || height.length ==0) return 0;
        int l = 0, r = height.length-1;
        int res = 0;
        while(l < r){
            res = Math.max(res, (r-l)*Math.min(height[r], height[l]));
            if(height[l]< height[r]){
                l++;
            }else{
                r--;
            }
        }
        return res;
    }
}

